package cleanandgo;
import java.sql.*;

public class DBConnection {
    private static Connection connection = null;

    /**
     * Private constructor that will create only one instance
     * of Connection. Singleton pattern
     * */
    private static void DBConnection(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/Clean_and_Go_Shop?serverTimezone=UTC&useSSL=TRUE";
            validate(url);
            boolean quit = false;
        } catch (ClassNotFoundException e) {
            System.out.println("Could not load the driver");
        } catch (SQLException ex) {
            System.out.println("Wrong username or password. Please try again");
            Main.main(null);
        }
    }

    /**
     * This function will return the current connection
     *  if it exists or will create and return the new one
     *  */
    public static Connection getConnected(){
        if(connection == null){
            DBConnection();
        }
        return connection;
    }

    /**
     * This function closes the connection
     * */
    public static void closeConnection(){
        if (connection != null) {
            try {
                connection.close();
                connection = null;
            } catch (SQLException e) {
                System.out.println("---------------------------------------------------------");
                System.out.println("            Wasn't able to close the connection          ");
                System.out.println(e);
                System.out.println("---------------------------------------------------------");
            }
        }
    }

    /**
     * The function will do the authentication of the user
     * */
    private static void validate(String url) throws SQLException {
        System.out.println("---------------------------------------------------------");
        System.out.println("             Please log in into the system               ");
        System.out.println("---------------------------------------------------------");
        String user, pass;
        user = Util.getUsersInput("Username: ");
        pass = Util.getUsersInput("Password: ");
        connection = (Connection) DriverManager.getConnection(url, user, pass);
        System.out.flush();
        System.out.println("---------------------------------------------------------");
        System.out.println("           Welcome to the Clean-and-Go Shop!             ");
        System.out.println("---------------------------------------------------------");
    }
}
