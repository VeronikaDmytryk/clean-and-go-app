package cleanandgo;

import java.io.IOException;
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
                    Customer.addNewCustomer();
                    System.out.println("This option is not implemented, please select another one.");
                    break;
                case '3':
                    printMenu3();
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

        DBConnection.closeConnection();
    }



    private static void printMenu3() {
        Employee.printSchedule();
    }

    private static void printMainMenu() {
        System.out.println("Main menu");
    }


}