package cleanandgo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * This is the Service class that contains all the methods that relates to retrieving or updating data in the Service table of our DB
 */
public class Service {

    /**
     * Will allow user to add a new service into the database,
     * prompting the values needs to be inputed by user
     */
    public static void addNewService() {
        System.out.println("---------------------------------------------------------");
        System.out.println("                    Add new Service                      ");
        System.out.println("---------------------------------------------------------");
        // Get connected to the DB
        Connection conn = DBConnection.getConnected();
        try {
            do {
                int newServiceId = getLastServiceId(conn);

                String query = "INSERT INTO Service (idService, name, description, rate_charged) VALUES (?, ?, ?, ?); ";
                // prepare a new query
                PreparedStatement p = conn.prepareStatement(query);
                p.clearParameters();

                // Fill the query
                p.setInt(1, newServiceId);

                String name = Util.getUsersInput("Type in name of service: ");
                p.setString(2, name);

                String description = Util.getUsersInput("Type in description of service: ");
                p.setString(3, description);

                Double rate = Double.parseDouble(Util.getUsersInput("Type in rate charged for service: "));
                p.setDouble(4, rate);

                p.executeUpdate();

                System.out.println("---------------------------------------------------------");
                System.out.println("    The new service was successfully added to the DB    ");
                System.out.println("---------------------------------------------------------");
                p.close();
            } while (!Util.getUsersInput("Type X to exit or any button to add another service: ").equals("X"));
        } catch (SQLException e) {
            System.out.println("---------------------------------------------------------");
            System.out.println("                Something went wrong                     ");
            System.out.println(e);
            System.out.println("---------------------------------------------------------");
        }

    }

    /**
     * Returns an id for a new service
     * This has risk of collisions under concurrency, but for now we will keep it simple
     * A better way is to use auto_icrement feature in MySQL and then a simple insert will just work
     *
     * @param conn connection to the DB
     * @throws SQLException
     */
    private static int getLastServiceId(Connection conn) throws SQLException {
        String queryMaxID = "select max(idService) from Service";
        Statement s = conn.createStatement();
        ResultSet res = s.executeQuery(queryMaxID);
        res.next();
        return res.getInt(1) + 1;
    }
}
