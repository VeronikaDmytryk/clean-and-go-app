package cleanandgo;

/**
 * This is the Update class will be managing calls of methods that will be updating data in our DB
 */
public class Update {
    public static void printMenu() {
        boolean quit = false;
        do {
            printUpdateMenu();
            String ch = Util.getUsersInput("Type in your option: ");
            System.out.println();
            switch (ch.charAt(0)) {
                case '1':
                    insert();
                    break;
                case '2':
                    // delete();
                    break;
                case '3':
                    // update();
                    break;
                case '4':
                    quit = true;
                    break;
                default:
                    System.out.println(" Not a valid option ");
            }
        } while (!quit);
    }

    private static void insert() {
        boolean quit = false;
        do {
            insertMenu();
            String ch = Util.getUsersInput("Type in your option: ");
            System.out.println();
            switch (ch.charAt(0)) {
                case '1':
                    Equipment.addNewEquipment();
                    break;
                case '2':
                    Service.addNewService();
                    break;
                case '3':
                    Customer.addNewCustomer();
                    break;
                case '4':
                    Employee.addNewEmployee();
                    break;
                case '5':
                    quit = true;
                    break;
                default:
                    System.out.println(" Not a valid option ");
            }
        } while (!quit);
    }

    public static void printUpdateMenu() {
        System.out.println("****************************************************************************************");
        System.out.println("                                       ******");
        System.out.println("                                   UPDATE MENU ");
        System.out.println("                                       ******");
        System.out.println("****************************************************************************************");
        System.out.println("1. Insert.");
        System.out.println("2. Delete.");
        System.out.println("3. Update.");
        System.out.println("4. Back.");
        System.out.println("****************************************************************************************");
    }

    public static void insertMenu() {
        System.out.println("****************************************************************************************");
        System.out.println("                                         ******");
        System.out.println("                                      INSERT MENU");
        System.out.println("                                         ******");
        System.out.println("*****************************************************************************************");
        System.out.println("1. Insert a new equipment.");
        System.out.println("2. Insert a specific service.");
        System.out.println("3. Insert a new customer information.");
        System.out.println("4. Insert a new employee information.");
        System.out.println("5. Back.");
        System.out.println("****************************************************************************************");
    }
}
