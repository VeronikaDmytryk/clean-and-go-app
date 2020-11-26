package cleanandgo;
import com.mysql.cj.protocol.Resultset;

import java.sql.*;

public class EquipmentAndSupplies {
    public static void printMenu1() {
        boolean quit = false;
        do {
            System.out.println("---------------------------------------------------------");
            System.out.println("                  Equipment & Supplies                   ");
            System.out.println("---------------------------------------------------------");
            System.out.println("    1. Analyze the annual expenses of the business       ");
            System.out.println("    2. Supplies                                          ");
            System.out.println("    3. Equipment                                         ");
            System.out.println("    4. Back                                              ");
            System.out.flush();
            String ch = Util.getUsersInput("Type in your option: ");
            System.out.println();
            switch (ch.charAt(0)) {
                case '1':
                    printAnnualExpensesReport();
//                  printSubmenu1();
                    System.out.println("Submenu 1");
                    break;
                case '2':
                    printSubmenu2();
                    System.out.println("Submenu 2");
                    break;
                case '3':
//                  printSubmenu3();
                    System.out.println("Submenu 3");
                    System.out.println("Menu 3");
                    break;
                case '4':
                    quit = true;
                    break;
                default:
                    System.out.println(" Not a valid option ");
            }
        } while (!quit);
    }

    private static void printSubmenu2() {
        boolean quit = false;
        do {
            System.out.println("---------------------------------------------------------");
            System.out.println("                        Supplies                         ");
            System.out.println("---------------------------------------------------------");
            System.out.println("    1. List products from a supplier                     ");
            System.out.println("    2. Supplies usage report                             ");
            System.out.println("    3. Back                                              ");
            System.out.flush();
            String ch = Util.getUsersInput("Type in your option: ");
            System.out.println();
            switch (ch.charAt(0)) {
                case '1':
//                  listProducts();
                    System.out.println("List products");
                    break;
                case '2':
//                  suppliesUsageReport();
                    System.out.println("Supplies Usage Report");
                    break;
                case '3':
                    quit = true;
                    break;
                default:
                    System.out.println(" Not a valid option ");
            }
        } while (!quit);
    }

    /** Prints the annual expenses from the following: rent, salaries, equipment maintenance, purchasing new equipment */
    private static void printAnnualExpensesReport () {
        try{
            Connection conn = DBConnection.getConnected();
            // FOR NOW RETURNS ONLY RENT EXPENSES
            String query = "select sum(rent_amount) from Rent_Record WHERE first_day_of_month between DATE_ADD(now(), interval -12 month) and now()";
            Statement s = conn.createStatement();
            ResultSet res = s.executeQuery(query);
            while(res.next()){
                System.out.println("Rent amount is " + res.getString(1));
            }
        } catch (SQLException e){
            System.out.println("Wasn't able to retrieve the data");
            System.out.println(e);
        }
    }
}
