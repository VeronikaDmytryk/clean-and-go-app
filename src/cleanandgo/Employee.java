/*
 * This is the Employee class that contains all the methods that relates to retrieving or updating data in the Employee table of our DB
 */

package cleanandgo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;
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
                        "week_start_date between DATE_ADD(now(), interval - 7 day) and now()";
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
                    r.close();
                    p.close();
                }
            } while (!Util.getUsersInput("Type X to exit or any other key to search for another schedule: ").equals("X"));
        } catch (SQLException e) {
            System.out.println("---------------------------------------------------------");
            System.out.println("Looks like an employee with this id doesn't exist");
            System.out.println(e);
            System.out.println("---------------------------------------------------------");
        }
    }

    //Not tested:
    /**
     * Will allow user to add a new employee into the database,
     * prompting the values needs to be inputed by user
     */
	public static void addNewEmployee() {
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

				p.close();
			} while (!Util.getUsersInput("Type X to exit or any button to add another employee: ").equals("X"));
		} catch (SQLException e) {
			System.out.println("---------------------------------------------------------");
			System.out.println("                Something went wrong                     ");
			System.out.println(e);
			System.out.println("---------------------------------------------------------");
		}

	}

	/**
	 * Returns an id for a new employee
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
}
