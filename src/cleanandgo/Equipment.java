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
            conn.close();
        } catch (SQLException e) {
            System.out.println("---------------------------------------------------------");
            System.out.println("Wasn't able to retrieve the data");
            System.out.println(e);
            System.out.println("---------------------------------------------------------");
        } finally {
        	
        }
	}

	private static void maintenanceSchedule() {
        System.out.println("---------------------------------------------------------");
        System.out.println("       Weekly Maintainance Schedule for Equipment           ");
        System.out.println("---------------------------------------------------------");
        Connection conn = DBConnection.getConnected();
        try {
            do {
                String query = "SELECT * FROM Maintainance WHERE date BETWEEN DATE_ADD(now(), interval -7 DAY) and now() "
                + "AND Equipment_idEquipment = ?";
                PreparedStatement p = conn.prepareStatement(query);
                String EquipmentId = Util.getUsersInput("Type in the equipment id: ");
                p.clearParameters();
                p.setString(1, EquipmentId);
                ResultSet rset = p.executeQuery();
                while (rset.next()) {
                    int idEquipment = rset.getInt(1);
                    int idEmployee = rset.getInt(2);
                    java.sql.Date date = rset.getDate(3);
                    Double cost = rset.getDouble(4);
                    String descr = rset.getString(5);
                    System.out.println("idEquipment: " + idEquipment);
                    System.out.println("idEmployee: " + idEmployee);
                    System.out.println("Date: " + date);
                    System.out.println("Cost: " + cost);
                    System.out.println("Description: " + descr + "\n");
                    
                }
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
                //currently this queries shows all dates in a year, per equipment
                String query = "SELECT  S.Equipment_idEquipment, R.service_date FROM Customer_Receives_Service AS R, Equipment_has_Service AS S " + 
                "WHERE R.service_date BETWEEN '2020-01-01' AND '2020-12-31' " + 
                "AND S.Service_idService = R.Service_idService AND S.Equipment_idEquipment = ?";
        
        /* I originally had this, although confused how to get "average" of daily usage
        //This prints the count per equipment in a year
         * SELECT count(A.Equipment_idEquipment) FROM Customer_Receives_Service AS R, Equipment_has_Service AS A
            WHERE R.service_date BETWEEN '2020-01-01' AND '2020-12-31' 
            AND A.Service_idService = R.Service_idService AND A.Equipment_idEquipment = ?";
         * 
         */

         /* Or if we had duration in Equipment_has_Supplies (psuedo):
         SELECT A.Equipment_idEquipment, ((SUM(duration))/31) FROM Customer_Receives_Service AS R, Equipment_has_Service AS A
         WHERE duration IN (SELECT SUM(duration) FROM Customer_Receives_Service AS R, Equipment_has_Service AS A
         WHERE A.Service_idService = R.Service_idService AND R.service_date BETWEEN '2020-01-01' AND '2020-12-31' 
         GROUP BY A.Equipment_idEquipment) AND R.service_date BETWEEN DATE_ADD(now(), interval -31 days) and now() 
         AND A.Equipment_idEquipment = ?;
         */
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
            } while (!Util.getUsersInput("Type X to exit or any button to search for another schedule: ").equals("X"));
        } catch (SQLException e) {
            System.out.println("---------------------------------------------------------");
            System.out.println("Looks like an employee with this id doesn't exist");
            System.out.println(e);
            System.out.println("---------------------------------------------------------");
        }
    }
}


