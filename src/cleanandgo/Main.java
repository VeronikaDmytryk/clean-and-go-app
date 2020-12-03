package cleanandgo;
import java.sql.*;

/**
 * This is the Main class of the application that would run the program until user choose to exit
 */
public class Main {
    public static void main(String[] args) {
        // Open a connection with the DB
        Connection conn = DBConnection.getConnected();

        boolean quit = false;
        do {
            printMainMenu();
            System.out.flush();
            String ch = Util.getUsersInput("Type in your option: ");
            System.out.println();
            switch (ch.charAt(0)) {
                case '1':
                    EquipmentAndSupplies.printMenu1();
                    break;
                case '2':
                    System.out.println("This option is not implemented yet");
                    break;
                case '3':
                    Employee.printMenu();
                    break;
                case '4':
                    Update.printMenu();
                    break;
                case '5':
                    System.out.println("                Thank you for visiting!                  ");
                    System.out.println("---------------------------------------------------------");
                    quit = true;
                    break;
                default:
                    System.out.println(" Not a valid option ");
            }
        } while (!quit);

        // close connection
        DBConnection.closeConnection();
    }


    public static void printMainMenu(){
        System.out.println("****************************************************************************************");
        System.out.println("                                      ******");
        System.out.println("                          Welcome to Clean and Go Shop");
        System.out.println("                                      ******");
        System.out.println("****************************************************************************************");
        System.out.println("1. Equipment & Supplies");
        System.out.println("2. Customers & Services");
        System.out.println("3. Employees");
        System.out.println("4. Updates");
        System.out.println("5. Quit");
        System.out.println("****************************************************************************************");

    }

}