package cleanandgo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Employee {

	/**
	 * print Employee menu options. Might be redundant since there is only one option
	 * but fort now following the same pattern.
	 */
	public static void printMenu() {
		boolean quit = false;
        do {
            System.out.println("---------------------------------------------------------");
            System.out.println("                  Employee                              ");
            System.out.println("---------------------------------------------------------");
            System.out.println("    A. Show Schedule                         ");
            System.out.println("    4. Back                                              ");
            System.out.flush();
            String ch = Util.getUsersInput("Type in your option: ");
            System.out.println();
    		switch (ch.charAt(0)) {
	    		case 'A':
	    		case 'a':
	    			printSchedule();
	    			break;
	    		case '4':
	    			quit = true;
	    			break;
	    		default:
	    			System.out.println(" Not a valid option ");
    		}
        } while (!quit);
	}
	
    /**
     * This function prints the current week's schedule for a specific
     * employee
     **/
    public static void printSchedule() {
        System.out.println("---------------------------------------------------------");
        System.out.println("    Please, provide employee ID to see the schedule      ");
        Connection conn = DBConnection.getConnected();
        try {
            do {
                String query = "select week_start_date, Mon, Tue, Wed, Thu, Fri, Sat, Sun "+
                        "from Schedule where Employee_idEmployee = ? and "+
                        "week_start_date between DATE_ADD(now(), interval - 50 day) and now()";
                PreparedStatement p = conn.prepareStatement(query);
                String employeeID = Util.getUsersInput("Type in the employee id: ");
                p.clearParameters();
                p.setString(1, employeeID);
                ResultSet r = p.executeQuery();
                if(r.next() == false){
                    System.out.println("---------------------------------------------------------");
                    System.out.println("Looks like an employee with this id doesn't exist");
                    System.out.println("---------------------------------------------------------");
                } else {
                    do {
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
                    } while (r.next());
                }
            } while (!Util.getUsersInput("Type X to exit or any other key to search for another schedule: ").equals("X"));
        } catch (SQLException e) {
            System.out.println("---------------------------------------------------------");
            System.out.println("Looks like an employee with this id doesn't exist");
            System.out.println(e);
            System.out.println("---------------------------------------------------------");
        }
    }
}
