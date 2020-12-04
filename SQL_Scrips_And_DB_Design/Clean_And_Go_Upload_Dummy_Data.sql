USE `Clean_and_Go_Shop` ;

INSERT into Cleaning_Supplies(`name`, `usage_Description`, `current_Inventory`, `safety_Stock_Level`, `type`)
VALUES  ("Detergent", "Cleaned with warm table the whole sets in two machines", 2, 9.32, "soap"),
		("Bleach", "Stay catious ", 6, 2.37, "detergent"),
		("Stain remover", "New detergent, still experimental", 11, 3.02, "detergent"),
		("Freshener", "requested only by loyal customers", 9, 5.2, "detergent"),
		("Hangers", "excellent items", 3, 0.01, "hanger"),
		("Sensitive Detergent", "Cleaned with warm table", 1, 5.32, "detergent"),
		("Softener", "Softner smell was stronger than expected", 21, 14.42, "soap");
        
INSERT INTO Customer (`idCustomer`, `first_name`, `last_name`, `address`, `email`, `phone`, `credit_card_info`, `currentBalance`, `first_service_date`)
VALUES  (131, "Amanda", "Bell", "9241 13th Ave SW Seattle, WA, 98106" , "amanda.bell@gmail.com", "4252085297", "4593296957485120", 125.50, "2018-04-03"),
		(132, "Eric", "Randall", "9514 8th Ave S Seattle, WA, 98108", "e.randall@test.com", "4252130614", "4539306158132491", 25.50, "2018-07-03"),
		(133, "Maria", "Harris", "9668 Rainier Ave S Seattle, WA, 98118", "maria.harris@yahoo.com", "4252219267", "5347370016581926", 0.50, "2018-11-25"),
		(134, "Dylan", "Walker", "9679 46th Ave SW Seattle, WA, 98136", "walker.dylan@gmail.com", "4253511396", "3541744348438287", 5.40, "2019-01-08" ),
		(135, "Megan", "Sharp", "9816 51st Ave SW Seattle, WA, 98136", "megan.sharp@test.com", "4254010742", "6011550958205161", 12.35, "2019-05-19"),
		(136, "Cameron", "Russell", "D Geology For The 21st Seattle, WA, 98146", "russell.c@gmail.com", "4254420972", "30431485529409", 45.50, "2019-10-17"),
		(137, "Colin", "Vance", "8038 14th Ave NW Seattle, WA, 98117", "collin_vance@gmail.com", "4254513122", "0604895625337285", 105.15, "2020-07-13");
        
INSERT INTO Employee (`idEmployee`, `first_name`, `last_name`, `address`, `gender`, `date_of_employment`, `position`, `salary`)
VALUES  (121, "Sam", "Boulder", "835 Laine St. S Seattle, WA 87098", "male", "2019-12-09", "Cashier", 2200.00),
		(122, "Lorey", "Smith", "12788 Clark Ave. SE Bellevue, WA 45789", "non-binary", "2018-06-23", "Cashier", 2200.00),
		(123, "Mia", "Battle", "2338 96th Rainer Ave. E Renton, WA 98178", "female", "2019-03-12", "Cashier", 2200.00),
		(124, "Josh", "Liang", "96750 12th St. Renton, WA 98078", "male", "2020-05-20", "Washer", 2500.00),
		(125, "Rose", "Chen", "3244 Brookes Ave. Bellevue, WA 45896", "female", "2015-02-19", "Washer", 2500.00),
		(126, "Matthew", "Nelson", "56332 Main St.Seattle, WA 87096", "male", "2016-08-16", "Dry Cleaner", 2300.00),
		(127, "Raine", "Yan", "328 Lorewell Ave. SW Tukwila, WA 56787", "female", "2020-04-26", "Dry Cleaner", 2300.00);

INSERT into Equipment (`idEquipment`, `brand_name`, `purchace_date`, `purchace_price`, `type`, `start_using_date`, `max_months_in_use`, `is_in_use`)
VALUES  (111, "Jhonson","2015-12-15", 1350.21, "Cleaning machine", "2015-12-30" , 29, 0),
		(112, "CleanOn","2017-02-15", 1350.21, "Drying machine", "2017-04-30" , 30, 1),
		(113, "HP","2015-12-01", 1350.21, "Dry Cleaning machine", "2015-12-19" , 52, 1),
		(114, "Asus","2020-01-15", 1350.21, "Ironning machine", "2015-02-01" , 10, 1),
		(115, "LG","2014-04-15", 1350.21, "Bleaching machine", "2014-05-30" , 29, 0),
		(116, "HUIN","2015-12-15", 1350.21, "Stain Removal machine", "2015-12-30" , 29, 0),
		(117, "flier","2019-03-15", 1350.21, "Cleaning machine", "2015-03-30" , 20, 1);
    
INSERT INTO Service (`idService`, `name`, `description`, `rate_charged`) 
VALUES  (101, "Washing", "Put a coin, choose the mode, press start", 25.50),
		(102, "Machine Drying", "Put a coin, choose the mode, press start", 15.50),
		(103, "Hang Drying", "Put a coin, schoose the mode, press start", 6.50),
		(104, "Stain Removal", "Ask for assistance", 56.50),
		(105, "Accessories Cleaning", "Ask for assistance", 15.50),
		(106, "Whitening", "Ask for assistance", 5.50),
        (107, "Delivery", "Your clothes delivered to your doorstep", 10.00);
        
INSERT INTO Supplier (`idSupplier`, `first_name`, `last_name`, `address`, `phone_number`, `current_balance`) 
VALUES  (91, "John", "Frye", "34500 Clark Ave. SE Tukwila, WA 76578", "2065437890", 5000.00), 
		(92, "Nina", "Zhen", "234 James St. Everett, WA 54878", "4259986240", 2500.00),
		(93, "Katie", "Gomez", "12389 Woodenville Ave. SW Lynnwood, WA 67564", "4250985467", 3000.00),
		(94, "Megan", "Payne", "7656 Omer St. S Redmond, WA 98789", "2063245786", 4000.00),
		(95, "David", "Lee", "5467 Lakeview Ave. W Tacoma, WA 32456", "4259871235", 3300.00),
		(96, "Erik", "Johnson", "2245 Florine Ave. E Everett, WA 54231", "2067723458", 5000.00),
		(97, "Max", "Cortez", "38876 Georgetown St. SE Seattle, WA 98178", "2068897213", 4500.00);
        
INSERT into Customer_Service_Equipment(`Customer_idCustomer`, `Service_idService`, `service_date`, `amount_charged`, `description`, `duration`, `customer_satisfaction`, `Equipment_idEquipment`)
VALUES  (131, 101, "2020-11-23", 132.14, "Service  of washing was done immediately" , 50, "great", 117),
		(131, 102, "2020-11-23", 182.150, "Service of Air Drying was done later in the week", 40, "good", 112),
		(132, 103, "2020-11-24", 24.50, "Service was done immediately", 15, "bad", null),
		(132, 101, "2020-11-24", 132.14, "Service of washing was done twice" , 45, "great", 117),
		(134, 105, "2020-11-25", 67.14, "Service of Hand Drying was done in the room 5" , 35, "bad", null),
		(135, 104, "2020-11-26", 100.14, "Service of stain removal was done immediately" , 40, "great", 111),
		(133, 106, "2020-11-27", 213.05, "Service of whitening was requested twice" , 20, "great", 116),
		(132, 101, "2020-11-23", 120.00, "Nice service", 35, "great", 117),
        (132, 101, "2020-10-23", 120.00, "Nice service", 35, "great", 117),
        (131, 101, "2020-10-28", 120.00, "Nice service", 65, "great",  117),
        (131, 101, "2020-09-28", 120.00, "Nice service", 55, "great", 117);
        
INSERT INTO Purchased_Equipment (`Supplier_idSupplier`, `Equipment_idEquipment`, `transaction_id`, `date_of_purchase`, `quantityPurchased`, `amount_due`, `due_date`, `description`, `delivery_date`)
VALUES  (91, 111, 71, "2019-01-15", 3, 2500.00, "2015-02-15", "Regular Guarantee", "2015-02-15"),
		(92, 112, 72, "2018-06-03", 7, 22750.00, "2017-01-03", "Extended Guarantee", "2017-01-03"),
		(93, 113, 73, "2017-10-05", 3, 5625.00, "2017-01-05", "Extended Guarantee", "2017-02-05"),
		(94, 114, 74, "2019-01-15", 5, 5745.00, "2018-02-15", "Regular Guarantee", "2018-03-15"),
		(95, 115, 75, "2015-03-06", 4, 4355.00, "2019-04-06", "Regular Guarantee", "2019-05-06"),
		(96, 116, 76, "2019-06-02", 6, 7547.50, "2019-07-02", "Extended Guarantee", "2019-08-02"),
		(97, 117, 77, "2020-03-17", 1, 2450.55, "2020-04-17", "Regular Guarantee", "2020-05-17");

INSERT INTO Purchased_Cleaning_Supplies (`Supplier_idSupplier`, `Cleaning_Supplies_name`, `transaction_id`, `date_of_purchase`, 
`quantity_purchased`, `amount_due`, `due_date`, `description`, `delivery_date`)	
VALUES  (91, "Detergent", 191, "2020-01-20", 200, 1500.00, "2020-03-20", "", "2020-02-01"),
		(92, "Bleach", 192, "2020-05-10", 150, 700.00, "2020-08-10", "", "2020-05-17"),
		(93, "Stain remover", 193, "2020-03-05", 300, 1500.00, "2020-06-05", "Extra strong", "2020-03-10"),
		(94, "Softener", 194, "2020-02-07", 250, 2000.00, "2020-05-07", "Extra soft", "2020-02-14"),
		(95, "Hangers", 195, "2020-10-10", 1000, 800.00, "2020-12-10", "Grip hangers", "2020-10-20"),
		(96, "Sensitive Detergent", 196, "2020-08-03", 150, 500.00, "2020-10-03", "Hypoallergenic", "2020-08-15"),
		(97, "Freshener", 197, "2020-04-21", 300, 1500.00, "2020-06-21", "Fresh scent", "2020-05-01"),
		(93, "Detergent", 1910, "2020-01-20", 200, 1500.00, "2020-03-20", "", "2020-02-01"),
        	(93, "Bleach", 1900, "2020-05-10", 150, 700.00, "2020-08-10", "", "2020-05-17");
        
INSERT into Rent_Record (`first_day_of_month`, `rent_amount`, `due_date`)
VALUES  ("2020-01-01", 31500.00,  "2020-01-01"),
		("2020-02-01", 32700.00, "2020-02-01"),
		("2020-03-01", 31500.67, "2020-03-01"),
		("2020-04-01", 32500.67, "2020-04-01"),
		("2020-05-01", 32500.67, "2020-05-01"),
		("2020-06-01", 32500.67, "2020-06-01"),
		("2020-07-01", 32500.67, "2020-07-01");
        
INSERT INTO Maintenance (`Equipment_idEquipment`, `Employee_idEmployee`, `date`, `cost`, `description`) 
VALUES  (111, 127, "2020-11-23", 1250.00, "Replace air filters"),
        (111, 127, "2020-11-27", 0.00, "Retrieve coins"),
	(112, 126, "2020-11-25", 150.00, "Clean inner parts"),
	(113, 125, "2020-11-25", 50.00, "Annual Check up"),
        (114, 124, "2020-11-28", 0.00, "Clean outside"),
        (115, 123, "2020-11-23", 0.00, "Retrieve coins"),
        (116, 122, "2020-11-26", 50.00, "Annual Check up"),
        (117, 121, "2020-11-29", 1250.00, "Replace water filter");
        
       
INSERT into Schedule (`Employee_idEmployee`, `week_start_date`, `Mon`, `Tue`, `Wed`, `Thu`, `Fri`, `Sat`, `Sun`)
VALUES (121, "2020-11-23", 1, 1, 1, 1,1, 0, 0), 
		(122, "2020-11-23", 1, 1, 1, 0,1, 0, 0),
		(123, "2020-11-23", 0, 0, 1, 1,1, 0, 1),
		(124, "2020-11-23", 1, 1, 0, 1,1, 1, 0),
		(125, "2020-11-23", 1, 1, 0, 1,0, 0, 1),
		(126, "2020-11-23", 1, 1, 1, 1,1, 0, 0),
		(127, "2020-11-23", 0, 1, 1, 1,1, 1, 0),
        (121, "2020-11-30", 1, 1, 1, 1,1, 0, 0), 
		(122, "2020-11-30", 1, 1, 1, 1,1, 0, 0),
		(123, "2020-11-30", 0, 1, 1, 1,1, 0, 1),
		(124, "2020-11-30", 1, 1, 0, 1,1, 1, 0),
		(125, "2020-11-30", 1, 1, 1, 1,0, 0, 1),
		(126, "2020-11-30", 1, 1, 1, 1,1, 0, 0),
		(127, "2020-11-30", 1, 0, 1, 1,1, 1, 0),
        (121, "2020-12-7", 1, 1, 1, 1,1, 0, 0),
		(122, "2020-12-7", 1, 1, 1, 0,1, 0, 0),
		(123, "2020-12-7", 0, 0, 1, 1,1, 0, 1),
		(124, "2020-12-7", 1, 1, 0, 1,1, 1, 0),
		(125, "2020-12-7", 1, 1, 0, 1,0, 0, 1),
		(126, "2020-12-7", 1, 1, 1, 1,1, 0, 0),
		(127, "2020-12-7", 0, 1, 1, 1,1, 1, 0),
		(121, "2020-11-14", 1, 1, 1, 1,1, 0, 0),
		(122, "2020-11-14", 1, 1, 1, 0,1, 0, 0),
		(123, "2020-11-14", 0, 0, 1, 1,1, 0, 1),
		(124, "2020-11-14", 1, 1, 0, 1,1, 1, 0),
		(125, "2020-11-14", 1, 1, 0, 1,0, 0, 1),
		(126, "2020-11-14", 1, 1, 1, 1,1, 0, 0),
		(127, "2020-11-14", 0, 1, 1, 1,1, 1, 0);

INSERT into Service_has_Cleaning_Supplies (`Service_idService`, `Cleaning_Supplies_name`) 
VALUES  (101, "Detergent"),
		(106, "Bleach"),
		(104, "Stain remover"),
		(104, "Softener"),
		(103, "Hangers"),
		(105, "Sensitive Detergent"),
		(101, "Freshener");
    
    
