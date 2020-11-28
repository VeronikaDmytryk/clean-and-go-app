package cleanandgo;
import java.sql.*;

public class Main {
    public static void main(String[] args) {
        // Open the connection with the DB
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
                    Supplies.printMenu();
                    break;
                case '3':
                    Employee.printMenu();
                    break;
                case '4':
                    Update.printMenu4();
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
        System.out.println("\t\t\t\t\t******");
        System.out.println("\t\t\t\tWelcome to Clean and Go Shop");
        System.out.println("\t\t\t\t\t******");
        System.out.println("****************************************************************************************");
        System.out.println("1. Equipment & Supplies");
        System.out.println("2. Customers & Services");
        System.out.println("3. Employees");
        System.out.println("4. Updates");
        System.out.println("5. Quit");
        System.out.println("****************************************************************************************");

    }

}