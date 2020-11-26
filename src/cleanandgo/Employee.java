package cleanandgo;

import java.sql.*;

public class Employee {

    /** This function prints the current week's schedule for a specific employee*/
    public static void printSchedule() {
        System.out.println("---------------------------------------------------------");
        System.out.println("    Please, provide employee ID to see the schedule      ");
        Connection conn = DBConnection.getConnected();
        try {
            do {
                String query = "select week_start_date, Mon, Tue, Wed, Thu, Fri, Sat, Sun from Schedule where Employee_idEmployee = ? and week_start_date between DATE_ADD(now(), interval - 50 day) and now()";
                PreparedStatement p = conn.prepareStatement(query);
                String employeeID = Util.getUsersInput("Type in the employee id: ");
                p.clearParameters();
                p.setString(1, employeeID);
                ResultSet r = p.executeQuery();
                while (r.next()) {
                    Date startDate = r.getDate(1);
                    char Mon = ' ';
                    char Tue = ' ';
                    char Wed = ' ';
                    char Thu = ' ';
                    char Fri = ' ';
                    char Sat = ' ';
                    char Sun = ' ';

                    if (r.getBoolean(2)) {
                        Mon = 'x';
                    }
                    if (r.getBoolean(3)) {
                        Tue = 'x';
                    }
                    if (r.getBoolean(4)) {
                        Wed = 'x';
                    }
                    if (r.getBoolean(5)) {
                        Thu = 'x';
                    }
                    if (r.getBoolean(6)) {
                        Fri = 'x';
                    }
                    if (r.getBoolean(7)) {
                        Sat = 'x';
                    }
                    if (r.getBoolean(8)) {
                        Sun = 'x';
                    }
                    System.out.println("---------------------------------------------------------");
                    System.out.println("  Week " + startDate + ". Schedule for employee with id " + employeeID);
                    System.out.println("---------------------------------------------------------");
                    System.out.println("|Mon | Tue | Wed | Thu | Fri | Sat | Sun |");
                    System.out.printf("| %c  |  %c  |  %c  |  %c  |  %c  |  %c  |  %c  |\n", Mon, Tue, Wed, Thu, Fri, Sat, Sun);
                }
            } while (!Util.getUsersInput("Type X to exit or C to search for another schedule: ").equals("X"));
        } catch (SQLException e) {
            System.out.println("Looks like an employee with this id doesn't exist");
            System.out.println(e);
        }
    }
}
