package cleanandgo;

import java.sql.*;
import java.util.Calendar;
import java.util.Date;

public class Customer {

    public static void addNewCustomer() {
        System.out.println("---------------------------------------------------------");
        System.out.println("                    Add new customer                     ");
        System.out.println("---------------------------------------------------------");
        Connection conn = DBConnection.getConnected();
        try {
            do {
                String queryMaxID = "select max(idCustomer) from Customer";
                Statement s = conn.createStatement();
                ResultSet res = s.executeQuery(queryMaxID);
                res.next();
                int newCustomerId = res.getInt(1) + 1;

                String query = "insert into Customer (idCustomer, first_name, last_name, address, email, phone, credit_card_info, currentBalance, first_service_date) values (?, ?, ?, ?, ?, ? ,?, ?, ?);";
                PreparedStatement p = conn.prepareStatement(query);
                p.clearParameters();

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

                Calendar today = Calendar.getInstance();
                today.set(Calendar.HOUR_OF_DAY, 0);
                Date first_service_date = today.getTime();
                p.setDate(9, new java.sql.Date(first_service_date.getTime()));

                p.executeUpdate();
                System.out.println("---------------------------------------------------------");
                System.out.println("    The new customer was successfully added to the DB    ");
                System.out.println("---------------------------------------------------------");
            } while (!Util.getUsersInput("Type X to exit or any button to add another customer: ").equals("X"));
        } catch (SQLException e) {
            System.out.println("---------------------------------------------------------");
            System.out.println("                Something went wrong                     ");
            System.out.println(e);
            System.out.println("---------------------------------------------------------");
        }
    }
}
