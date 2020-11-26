package cleanandgo;

import java.sql.*;

public class Equipment {

    /** Prints a report for the average daily usage, for each equipment, during the current year */
    public static void printEquipmentUsageAnualReport() {
        System.out.println("---------------------------------------------------------");
        System.out.println("  Average daily usage of equipment for the current year  ");
        System.out.println("---------------------------------------------------------");
        Connection conn = DBConnection.getConnected();
        try {
            do {
                String query = "to be implementated";
                PreparedStatement p = conn.prepareStatement(query);
                String employeeID = Util.getUsersInput("Type in the employee id: ");
                p.clearParameters();
                p.setString(1, employeeID);
                ResultSet r = p.executeQuery();
                while (r.next()) {

                }
            } while (!Util.getUsersInput("Type X to exit or any button to search for another schedule: ").equals("X"));
        } catch (SQLException e) {
            System.out.println("Looks like an employee with this id doesn't exist");
            System.out.println(e);
        }
    }
}
