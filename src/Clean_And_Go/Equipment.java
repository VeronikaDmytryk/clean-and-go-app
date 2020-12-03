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
                System.out.printf("%13s|%15s %5s|%15s|%13s|%25s\n", "idEquipment", "Employee", "Name", "Date", "Cost", "Description");
                System.out.println("-------------------------------------------------------------------------------------------------");
                while (rset.next()) {
                    int idEquipment = rset.getInt(1);
                    String date = rset.getString(2);
                    Double cost = rset.getDouble(3);
                    String descr = rset.getString(4);
                    String fname = rset.getString(5);
                    String lname = rset.getString(6);
                    System.out.printf("%13d|%15s %5s|%15s|%13s|%25s\n", idEquipment, fname, lname, date, cost, descr);
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

    public static void addNewEquipment() {
		System.out.println("---------------------------------------------------------");
		System.out.println("                    Add new Equipmemnt                   ");
		System.out.println("---------------------------------------------------------");
		// Get connected to the DB
		Connection conn = DBConnection.getConnected();
		try (conn) {
			do {
				int newEquipmentId = getLastEquipmenmtId(conn);

				String query = "insert into Equipment (idEquipment, brand_name, purchace_date, purchace_price, type, start_using_date, max_months_in_use, is_in_use) values (?, ?, ?, ?, ?, ? ,?, ?)";
				// prepare a new query
				PreparedStatement p = conn.prepareStatement(query);
				p.clearParameters();

				// Fill the query
				p.setInt(1, newEquipmentId);

				String input = Util.getUsersInput("Type in the brand name: ");
				p.setString(2, input);

				input = Util.getUsersInput("Type in the purchase date (yyyy-mm-dd): ");
				p.setString(3, input);

				input = Util.getUsersInput("Type in the purchse price: ");
				p.setString(4, input);

				input = Util.getUsersInput("Type in the type: ");
				p.setString(5, input);

				input = Util.getUsersInput("Type in the use start date (yyyy-mm-dd): ");
				p.setString(6, input);

				input = Util.getUsersInput("Type in the max months in use (integer): ");
				p.setString(7, input);

				int used =  Integer.parseInt(Util.getUsersInput("Is in use? (1 if used 0 if not)): "));
				p.setDouble(8, used);

				p.executeUpdate();

				System.out.println("---------------------------------------------------------");
				System.out.println("    The new equipment was successfully added to the DB    ");
				System.out.println("---------------------------------------------------------");
				p.close();
			} while (!Util.getUsersInput("Type X to exit or any button to add another equipment: ").equals("X"));
		} catch (SQLException e) {
			System.out.println("---------------------------------------------------------");
			System.out.println("                Something went wrong                     ");
			System.out.println(e);
			System.out.println("---------------------------------------------------------");
		}

	}
	/**
	 * Returns an id for a new equipment
	 * This has risk of collisions under concurrency, but for now we will keep it simple
	 * A better way is to use auto_icrement feature in MySQL and then a simple insert will just work
	 * @param conn connection to the DB
	 * @throws SQLException
	 * */
	private static int getLastEquipmenmtId(Connection conn) throws SQLException {
		String queryMaxID = "select max(idEquipment) from Equipment";
		Statement s = conn.createStatement();
		ResultSet res = s.executeQuery(queryMaxID);
		res.next();
		return res.getInt(1) + 1;
    }
    
}


