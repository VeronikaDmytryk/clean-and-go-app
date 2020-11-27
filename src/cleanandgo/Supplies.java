package cleanandgo;

public class Supplies {

    /**
     * Prints Supplies menu
     * */
    public static void printMenu() {
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
}
