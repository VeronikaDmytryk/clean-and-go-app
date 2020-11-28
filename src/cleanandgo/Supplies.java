package cleanandgo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
            System.out.println("    A. Products purchased by supplier                    ");
            System.out.println("    B. Cleaning supplies alert                           ");
            System.out.println("    3. Back                                              ");
            System.out.flush();
            String ch = Util.getUsersInput("Type in your option: ");
            System.out.println();
            switch (ch.charAt(0)) {
                case 'A':
                case 'a':
                	listProducts();
                    break;
                case 'B':
                case 'b':
//                  suppliesUsageReport();
                    System.out.println("Supplies Usage Report (needs implementation)");
                    break;
                case '3':
                    quit = true;
                    break;
                default:
                    System.out.println(" Not a valid option ");
            }
        } while (!quit);
    }
    
    /**
     * Takes name of a supplier and returns a list of products purchased by suplier  
     */
	private static void listProducts() {
		try{
            // get connected to the DB
            Connection conn = DBConnection.getConnected();
            
            System.out.println("---------------------------------------------------------");
            System.out.println("          Products purchased by supplier     ");
            System.out.println("---------------------------------------------------------");
            // get supplier ID
            String supplierId = null;
            final String query = "SELECT idSupplier FROM Clean_and_Go_Shop.Supplier where lower(first_name) = ? and lower(last_name) = ?";
            PreparedStatement p = conn.prepareStatement(query);
            String fname;
        	String lname;
            do {
            	fname = Util.getUsersInput("Provide supplier first name: ");
            	lname = Util.getUsersInput("Provide supplier last name: ");
            	if (fname.isEmpty() && lname.isEmpty()) {
            		// allow user to go back
            		return;
            	}
            	System.out.println();
            	p.clearParameters();
            	p.setString(1, fname.toLowerCase());
            	p.setString(2, lname.toLowerCase());
            	ResultSet r = p.executeQuery();
            	if(r.next()) { // get firstsupplier ID as result
            		supplierId = r.getString(1);
            		if (r.next()) {
            			System.out.println("Additinal matches found, but ignoring.");
            		}
            	} else {
            		System.out.println("Supplier not found, please try again or leave names empty to go back");
            	}
            	r.close();
            } while (supplierId == null);
            p.close();
            
            System.out.println("Supplier ID: " + supplierId);
            // query for supplies purchased by that supplier
            String query2 = "SELECT Purchased_Cleaning_Supplies.Cleaning_Supplies_name, Purchased_Cleaning_Supplies.`description`\n" + 
            		"from Clean_and_Go_Shop.Purchased_Cleaning_Supplies " + 
            		"where Supplier_idSupplier = ?";
            p = conn.prepareStatement(query2);
            p.setString(1, supplierId);
            ResultSet r = p.executeQuery();
            while(r.next()){
                System.out.println("---------------------------------------------------------");
                System.out.println("              Supplies by supplier " + fname + " " + lname);
                System.out.println("---------------------------------------------------------");
                System.out.println(r.getString(1) + "\t\t" + r.getString(2));
            }
            r.close();
            p.close();
            p.close();
            
            // if we want to print equipment by supplier then here is the query, which I initially used by mistake
            /*
             SELECT Purchased_Equipment.Equipment_idEquipment, Equipment.brand_name, `Equipment`.`type`
			from Clean_and_Go_Shop.Purchased_Equipment join Clean_and_Go_Shop.Equipment
			on Equipment.idEquipment = Purchased_Equipment.Equipment_idEquipment
			where Supplier_idSupplier = ?;
             */
            
        } catch (SQLException e) {
            System.out.println("---------------------------------------------------------");
            System.out.println("Wasn't able to retrieve the data");
            System.out.println(e);
            System.out.println("---------------------------------------------------------");
        }
	}
}
