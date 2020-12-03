package cleanandgo;

import java.sql.*;

public class Equipment {

	public static void printMenu() {
		boolean quit = false;
        do {
            System.out.println("---------------------------------------------------------");
            System.out.println("                  Equipment                              ");
            System.out.println("---------------------------------------------------------");
            System.out.println("    A. Total Number of Equipment                         ");
            System.out.println("    B. Maintenance Schedule                              ");
            System.out.println("    C. Average Monthly Usage                             ");
            System.out.println("    4. Back                                              ");
            System.out.flush();
            String ch = Util.getUsersInput("Type in your option: ");
            System.out.println();
    		switch (ch.charAt(0)) {
	    		case 'A':
	    		case 'a':
	    			totalNumberOfEquipment();
	    			break;
	    		case 'B':
	    		case 'b':
	    			maintenanceSchedule();
	    			break;
	    		case 'C':
	    		case 'c':
	    			averageDailyUsage();
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
	 * Query for the total number of equipment owned by the company.
	 * Grouped by equipment type.
	 */
    private static void totalNumberOfEquipment() {
    	try{
            // get connected to the DB
            Connection conn = DBConnection.getConnected();
            String query = "SELECT type, count(type) FROM Clean_and_Go_Shop.Equipment group by type";
            Statement s = conn.createStatement();
            ResultSet res = s.executeQuery(query);
            System.out.println("---------------------------------------------------------");
            System.out.println("          Total number of equipment grouped by type      ");
            System.out.println("---------------------------------------------------------");
            System.out.println("Type\t\t\tCount");
            while(res.next()){
                System.out.println(res.getString(1) + "\t\t" + res.getInt(2));
            }
            res.close();
            s.close();
//            conn.close();   <- Don't close the connection
        } catch (SQLException e) {
            System.out.println("---------------------------------------------------------");
            System.out.println("          Wasn't able to retrieve the data");
            System.out.println(e);
            System.out.println("---------------------------------------------------------");
        }
	}

    /**
     * Prints weekly maintenance schedule for the equipment id 
     * entered by user
     */
	private static void maintenanceSchedule() {
        System.out.println("---------------------------------------------------------");
        System.out.println("       Weekly Maintenance Schedule for Equipment           ");
        System.out.println("---------------------------------------------------------");
        Connection conn = DBConnection.getConnected();
        try {
            do {
                String query = "SELECT Equipment_idEquipment, date, cost, description, first_name, last_name FROM Maintenance, Employee WHERE date BETWEEN DATE_ADD(now(), interval -7 DAY) and now() "
                + "AND Employee_idEmployee = idEmployee AND Equipment_idEquipment = ?";
                PreparedStatement p = conn.prepareStatement(query);
                String equipmentId = Util.getUsersInput("Type in the equipment id: ");
                p.clearParameters();
                p.setString(1, equipmentId);
                ResultSet rset = p.executeQuery();
                System.out.println("Equipment  | " + " Employee  | " + " Date  | " + " Cost  |" + " Description  |");
                while (rset.next()) {
                    int idEquipment = rset.getInt(1);
                    java.sql.Date date = rset.getDate(2);
                    Double cost = rset.getDouble(3);
                    String descr = rset.getString(4);
                    String fname = rset.getString(5);
                    String lname = rset.getString(6);
                    System.out.print(" " + idEquipment + "  | ");
                    System.out.print(fname + " " + lname + " | ");
                    System.out.print(date + " | ");
                    System.out.print(cost + " | " );   
                    System.out.print(descr + "\n");

                    /**Original Version:
                     *  System.out.println("idEquipment: " + idEquipment);
	                    System.out.println("Employee: " + fname + " " + lname);
	                    System.out.println("Date: " + date);
	                    System.out.println("Cost: " + cost);   
	                    System.out.println("Description: " + descr + "\n");
                     */
                }
                p.close();
            } while (!Util.getUsersInput("Type X to exit or any button to search for another schedule: ").equals("X"));
        } catch (SQLException e) {
            System.out.println("---------------------------------------------------------");
            System.out.println("Looks like an equipment with this id doesn't exist");
            System.out.println(e);
            System.out.println("---------------------------------------------------------");
        }
    }

	/**
     * Prints a report for the average daily usage, for each equipment,
     *  during the current year
     *  */
    public static void averageDailyUsage() {
        System.out.println("---------------------------------------------------------");
        System.out.println("  Average daily usage of equipment for the current year  ");
        System.out.println("---------------------------------------------------------");
        Connection conn = DBConnection.getConnected();
        try {
            do {
                String query = "";
                PreparedStatement p = conn.prepareStatement(query);
                String equipmentId = Util.getUsersInput("Type in the equipment id: ");
                p.clearParameters();
                p.setString(1, equipmentId);
                ResultSet r = p.executeQuery();
                while (r.next()) {
                    int idEquipment = r.getInt(1);
                	java.sql.Date date = r.getDate(2);
                	System.out.println("idEquipment: " + idEquipment);
                	System.out.println("* Date: " + date + "\n");
                }
                p.close();
            } while (!Util.getUsersInput("Type X to exit or any button to search for another schedule: ").equals("X"));
        } catch (SQLException e) {
            System.out.println("---------------------------------------------------------");
            System.out.println("Looks like an employee with this id doesn't exist");
            System.out.println(e);
            System.out.println("---------------------------------------------------------");
        }
    }
}


