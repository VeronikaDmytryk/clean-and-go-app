/*
 * This is the Customer class that contains all the methods that relates to retrieving or updating data in the Customer table of our DB
 */

package cleanandgo;
import java.sql.*;
import java.util.Calendar;
import java.util.Date;

public class Customer {

    /**
     * This function interacts with the user in order to add a new Customer
     * to the system
     * */
    public static void addNewCustomer() {
        System.out.println("---------------------------------------------------------");
        System.out.println("                    Add new customer                     ");
        System.out.println("---------------------------------------------------------");
        // Get connected to the DB
        Connection conn = DBConnection.getConnected();
        try {
            do {
                int newCustomerId = getLastCustomerId(conn);

                String query = "insert into Customer (idCustomer, first_name, last_name, address, email, phone, credit_card_info, currentBalance, first_service_date) values (?, ?, ?, ?, ?, ? ,?, ?, ?);";
                // prepare a new query
                PreparedStatement p = conn.prepareStatement(query);
                p.clearParameters();

                // Fill the query
                p.setInt(1, newCustomerId);

                String first_name = Util.getUsersInput("Type in the customer's first name: ");
                p.setString(2, first_name);

                String last_name = Util.getUsersInput("Type in the customer's last name: ");
                p.setString(3, last_name);

                String address = Util.getUsersInput("Type in the customer's address: ");
                p.setString(4, address);

                String email = Util.getUsersInput("Type in the customer's email: ");
                p.setString(5, email);

                String phone = Util.getUsersInput("Type in the customer's phone: ");
                p.setString(6, phone);

                String credit_card_info = Util.getUsersInput("Type in the customer's credit card number: ");
                p.setString(7, credit_card_info);

                Double currentBalance =  Double.parseDouble(Util.getUsersInput("Type in the customer's balance: "));
                p.setDouble(8, currentBalance);

                // Get today's date
                Calendar today = Calendar.getInstance();
                today.set(Calendar.HOUR_OF_DAY, 0);
                Date first_service_date = today.getTime();
                p.setDate(9, new java.sql.Date(first_service_date.getTime()));

                p.executeUpdate();

                System.out.println("---------------------------------------------------------");
                System.out.println("    The new customer was successfully added to the DB    ");
                System.out.println("---------------------------------------------------------");
                p.close();
            } while (!Util.getUsersInput("Type X to exit or any button to add another customer: ").equals("X"));
        } catch (SQLException e) {
            System.out.println("---------------------------------------------------------");
            System.out.println("                Something went wrong                     ");
            System.out.println(e);
            System.out.println("---------------------------------------------------------");
        }
    }

    /**
     * Returns an id for a new Customer
     * @param conn connection to the DB
     * @throws SQLException
     * */
    private static int getLastCustomerId(Connection conn) throws SQLException {
        String queryMaxID = "select max(idCustomer) from Customer";
        Statement s = conn.createStatement();
        ResultSet res = s.executeQuery(queryMaxID);
        res.next();
        int newCustomerId = res.getInt(1) + 1;
        return newCustomerId;
    }
}
