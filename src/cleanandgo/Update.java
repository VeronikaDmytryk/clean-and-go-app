package cleanandgo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Update {
	public static void printMenu() {
		boolean quit = false;
		do {
			printUpdateMenu();
			String ch = Util.getUsersInput("Type in your option: ");
			System.out.println();
			switch (ch.charAt(0)) {
			case '1':
				insert();
				break;
			case '2':
				// delete();
				break;
			case '3':
				// update();
				break;
			case '4':
				quit = true;
				break;
			default:
				System.out.println(" Not a valid option ");
			}
		} while (!quit);

	}

	private static void insert() {
		boolean quit = false;
		do {
			insertMenu();
			String ch = Util.getUsersInput("Type in your option: ");
			System.out.println();
			switch (ch.charAt(0)) {
			case '1':
				addNewEquipment();
				break;
			case '2':
				 addNewService();
				break;
			case '3':
				// addNewCustomer();
				break;
			case '4':
				 addNewEmployee();
				break;
			case '5':
				quit = true;
				break;
			default:
				System.out.println(" Not a valid option ");
			}
		} while (!quit);
	}

	private static void addNewEquipment() {
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

	//I have not tested any beyond this point:
	private static void addNewEmployee() {
		System.out.println("---------------------------------------------------------");
		System.out.println("                    Add new Employee                     ");
		System.out.println("---------------------------------------------------------");
		// Get connected to the DB
		Connection conn = DBConnection.getConnected();
		try (conn) {
			do {
				int newEmployeeId = getLastEmployeeId(conn);

				String query = "INSERT INTO Employee (idEmployee, first_name, last_name, address, gender, date_of_employment, position, salary) VALUES (?, ?, ?, ?, ?, ? ,?, ?); ";
				// prepare a new query
				PreparedStatement p = conn.prepareStatement(query);
				p.clearParameters();

				// Fill the query
				p.setInt(1, newEmployeeId);

				String first_name = Util.getUsersInput("Type in your fist name: ");
				p.setString(2, first_name);

				String last_name = Util.getUsersInput("Type in your last name: ");
				p.setString(3, last_name);

				String address = Util.getUsersInput("Type in your address: ");
				p.setString(4, address);

				String gender = Util.getUsersInput("Type in your gender (male, female, transgender, non-binary, genderqueer): ");
				p.setString(5, gender);

				//please try this date works, if not I will change back to string data type
				java.sql.Date date_of_employment = java.sql.Date.valueOf(Util.getUsersInput("Type in your date of employement (YYYY-MM-DD): "));
				p.setDate(6, date_of_employment);

				String position = Util.getUsersInput("Type in your position: ");
				p.setString(7, position);

				Double salary = Double.parseDouble(Util.getUsersInput("Type in your salary: "));
				p.setDouble(8, salary);

				p.executeUpdate();

				System.out.println("---------------------------------------------------------");
				System.out.println("    The new employee was successfully added to the DB    ");
				System.out.println("---------------------------------------------------------");
			} while (!Util.getUsersInput("Type X to exit or any button to add another employee: ").equals("X"));
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
	private static int getLastEmployeeId(Connection conn) throws SQLException {
		String queryMaxID = "select max(idEmployee) from Employee";
		Statement s = conn.createStatement();
		ResultSet res = s.executeQuery(queryMaxID);
		res.next();
		return res.getInt(1) + 1;
	}


	private static void addNewService() {
		System.out.println("---------------------------------------------------------");
		System.out.println("                    Add new Service                      ");
		System.out.println("---------------------------------------------------------");
		// Get connected to the DB
		Connection conn = DBConnection.getConnected();
		try (conn) {
			do {
				int newServiceId = getLastServiceId(conn);

				String query = "INSERT INTO Service (idService, name, description, duration, rate_charged) VALUES (?, ?, ?, ?, ?); ";
				// prepare a new query
				PreparedStatement p = conn.prepareStatement(query);
				p.clearParameters();

				// Fill the query
				p.setInt(1, newServiceId);

				String name = Util.getUsersInput("Type in name of service: ");
				p.setString(2, name);

				String description = Util.getUsersInput("Type in description of service: ");
				p.setString(3, description);

				int duration = Integer.parseInt(Util.getUsersInput("Type in duration of service: "));
				p.setInt(4, duration);

				Double rate = Double.parseDouble(Util.getUsersInput("Type in rate charged for service: "));
				p.setDouble(5, rate);

				p.executeUpdate();

				System.out.println("---------------------------------------------------------");
				System.out.println("    The new service was successfully added to the DB    ");
				System.out.println("---------------------------------------------------------");
			} while (!Util.getUsersInput("Type X to exit or any button to add another service: ").equals("X"));
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
	private static int getLastServiceId(Connection conn) throws SQLException {
		String queryMaxID = "select max(idService) from Service";
		Statement s = conn.createStatement();
		ResultSet res = s.executeQuery(queryMaxID);
		res.next();
		return res.getInt(1) + 1;
	}

	public static void printUpdateMenu(){
		System.out.println("****************************************************************************************");
		System.out.println("\t\t\t\t\t******");
		System.out.println("\t\t\t\tWelcome to Clean and Go Shop");
		System.out.println("\t\t\t\tUPDATE MENU :");
		System.out.println("\t\t\t\t\t******");
		System.out.println("****************************************************************************************");
		System.out.println("1. Insert.");
		System.out.println("2. Delete.");
		System.out.println("3. Update.");
		System.out.println("4. Quit.");
		System.out.println("****************************************************************************************");
	}

	public static void insertMenu() {
		System.out.println("****************************************************************************************");
		System.out.println("\t\t\t\t\t******");
		System.out.println("\t\t\t\tWelcome to Clean and Go Shop");
		System.out.println("\t\t\t\t    INSERT MENU");
		System.out.println("\t\t\t\t\t******");
		System.out.println("***********************a*****************************************************************");
		System.out.println("1. Insert a new equipment.");
		System.out.println("2. Insert a specific service.");
		System.out.println("3. Insert a new customer information.");
		System.out.println("4. Insert a new employee information.");
		System.out.println("5. Quit. \n");
		System.out.println("****************************************************************************************");
	}
}
