package cleanandgo;

import java.sql.*;

public class EquipmentAndSupplies {

    /**
     * Prints menu for Equipment and Supplies section
     * */
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
                	printSubmenu1();
                    
                    break;
                case '2':
                    Supplies.printMenu();
                    break;
                case '3':
                	Equipment.printMenu();
                    break;
                case '4':
                    quit = true;
                    break;
                default:
                    System.out.println(" Not a valid option ");
            }
        } while (!quit);
    }
    
    
    /*
     * SubMenu1.1
     * 
     */
    public static void printSubmenu1(){
    	boolean quit = false;
    	do {
    		System.out.println("****************************************************************************************");
    		System.out.println("\t\t\t\t\t******");
    		System.out.println("\t\t\t\tWelcome to Clean and Go Shop");
    		System.out.println("\t\t\t\t\t******");
    		System.out.println("\t\t\t\tEquipment & Supplies");
    		System.out.println("\t\t\t\tAnalyze the annual expenses of the business");
    		System.out.println("****************************************************************************************");

    		System.out.println("Option A: Annual expenses from cleaning supplies.");
    		System.out.println("Option B: Annual expenses Report");
    		System.out.println("4. Back");
    		System.out.println("****************************************************************************************");
    		String ch = Util.getUsersInput("Type in your option: ");
            System.out.println();
    		switch (ch.charAt(0)) {
    		case 'A':
    		case 'a':
    			Supplies.printAnnualCleaningSuppliesExpenses();
    			break;
    		case 'B':
    		case 'b':
    			printAnnualExpensesReport();
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
     * Prints the annual expenses from the following: rent, salaries,
     *  equipment maintenance, purchasing new equipment
     *  */
    private static void printAnnualExpensesReport () {
        try{
            // get connected to the DB
            Connection conn = DBConnection.getConnected();
            String query = "select sum(Amount) as Amount "+
                    "FROM( ( select sum(rent_amount) as Amount from Rent_Record "+
                    "WHERE first_day_of_month between DATE_ADD(now(), interval -12 month) and now()) " +
                    "union all ( select sum(salary) * 12 as Amount from Employee ) "+
                    "union all ( select sum(cost) as Amount from Maintenance "+
                    "WHERE date between DATE_ADD(now(), interval -12 month) and now() ) " +
                    "union all ( select sum(amount_due) as Amount from Purchased_Equipment " +
                    "WHERE date_of_purchase between DATE_ADD(now(), interval -12 month) and now() ) ) as A; ";
            Statement s = conn.createStatement();
            ResultSet res = s.executeQuery(query);
            while(res.next()){
                System.out.println("---------------------------------------------------------");
                System.out.println("                      Annual Expenses                    ");
                System.out.println("---------------------------------------------------------");
                System.out.println("The amount paid for \n * rent \n * salaries \n * equipment purchases \n * maintaince \n is $" + res.getDouble(1));
            }
        } catch (SQLException e){
            System.out.println("---------------------------------------------------------");
            System.out.println("Wasn't able to retrieve the data");
            System.out.println(e);
            System.out.println("---------------------------------------------------------");
        }
    }
}
