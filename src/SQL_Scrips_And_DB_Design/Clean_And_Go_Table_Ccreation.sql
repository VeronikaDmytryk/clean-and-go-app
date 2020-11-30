CREATE SCHEMA IF NOT EXISTS `Clean_and_Go_Shop`;
USE `Clean_and_Go_Shop` ;

CREATE TABLE IF NOT EXISTS `Cleaning_Supplies`(
  `name` VARCHAR(45) NOT NULL PRIMARY KEY,
  `usage_Description` VARCHAR(500) NULL,
  `current_Inventory` INT NOT NULL,
  `safety_Stock_Level` DECIMAL(8,2) NOT NULL,
  `type` ENUM('soap', 'detergent', 'hanger') NOT NULL
);

CREATE TABLE IF NOT EXISTS `Customer` (
  `idCustomer` INT NOT NULL PRIMARY KEY,
  `first_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  `address` VARCHAR(250) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `phone` VARCHAR(15) NOT NULL,
  `credit_card_info` VARCHAR(19) NOT NULL,
  `currentBalance` DECIMAL(8,2) NOT NULL,
  `first_service_date` DATE NOT NULL
);

CREATE TABLE IF NOT EXISTS `Employee` (
  `idEmployee` INT NOT NULL PRIMARY KEY,
  `first_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  `address` VARCHAR(250) NOT NULL,
  `gender` ENUM('male', 'female', 'transgender', 'non-binary', 'genderqueer') NOT NULL,
  `date_of_employment` DATE NOT NULL,
  `position` VARCHAR(45) NOT NULL,
  `salary` DECIMAL(8,2) NOT NULL
);

CREATE TABLE IF NOT EXISTS `Equipment` (
  `idEquipment` INT NOT NULL PRIMARY KEY,
  `brand_name` VARCHAR(45) NOT NULL,
  `purchace_date` DATETIME NOT NULL,
  `purchace_price` DECIMAL(8,2) NOT NULL,
  `type` VARCHAR(45) NOT NULL,
  `start_using_date` VARCHAR(45) NOT NULL,
  `max_months_in_use` INT NOT NULL,
  `is_in_use` SMALLINT NOT NULL
);

CREATE TABLE IF NOT EXISTS `Service` (
  `idService` INT NOT NULL PRIMARY KEY,
  `name` VARCHAR(45) NOT NULL,
  `description` VARCHAR(500) NOT NULL,
  `rate_charged` DECIMAL(5,2) NOT NULL
);

CREATE TABLE IF NOT EXISTS `Supplier` (
  `idSupplier` INT NOT NULL PRIMARY KEY,
  `first_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  `address` VARCHAR(45) NOT NULL,
  `phone_number` VARCHAR(45) NOT NULL,
  `current_balance` DECIMAL(8,2) NOT NULL
);

CREATE TABLE IF NOT EXISTS `Customer_Service_Equipment` (
  `Customer_idCustomer` INT NOT NULL,
  `Service_idService` INT NOT NULL,
  `service_date` DATE NOT NULL,
  `amount_charged` DECIMAL(8,2) NOT NULL,
  `description` VARCHAR(500) NULL,
  `duration` INT NOT NULL,
  `customer_satisfaction` ENUM('good', 'bad', 'awful', 'great') NOT NULL,
  `Equipment_idEquipment` INT,
  PRIMARY KEY (`Customer_idCustomer`, `Service_idService`, `service_date`),
  FOREIGN KEY (`Customer_idCustomer`) REFERENCES `Customer` (`idCustomer`) ON DELETE RESTRICT ON UPDATE CASCADE,
  FOREIGN KEY (`Service_idService`) REFERENCES `Service` (`idService`) ON DELETE RESTRICT ON UPDATE CASCADE,
  FOREIGN KEY (`Equipment_idEquipment`) REFERENCES `Equipment` (`idEquipment`) ON DELETE RESTRICT ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS `Purchased_Equipment` (
  `Supplier_idSupplier` INT NOT NULL,
  `Equipment_idEquipment` INT NOT NULL,
  `transaction_id` INT NOT NULL,
  `date_of_purchase` DATE NOT NULL,
  `quantityPurchased` INT NOT NULL,
  `amount_due` DECIMAL(8,2) NOT NULL,
  `due_date` DATE NOT NULL,
  `description` VARCHAR(500) NULL,
  `delivery_date` DATE NOT NULL,
	PRIMARY KEY (`Supplier_idSupplier`, `Equipment_idEquipment`, `transaction_id`),
    FOREIGN KEY (`Supplier_idSupplier`) REFERENCES `Supplier` (`idSupplier`) ON DELETE RESTRICT ON UPDATE CASCADE,
    FOREIGN KEY (`Equipment_idEquipment`) REFERENCES `Equipment` (`idEquipment`)ON DELETE RESTRICT ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS `Purchased_Cleaning_Supplies` (
  `Supplier_idSupplier` INT NOT NULL,
  `Cleaning_Supplies_name` VARCHAR(45) NOT NULL,
  `transaction_id` INT NOT NULL,
  `date_of_purchase` DATE NOT NULL,
  `quantity_purchased` INT NOT NULL,
  `amount_due` DECIMAL(8,2) NOT NULL,
  `due_date` DATE NOT NULL,
  `description` VARCHAR(500) NULL,
  `delivery_date` DATE NOT NULL,
  PRIMARY KEY (`Supplier_idSupplier`, `Cleaning_Supplies_name`, `transaction_id`),
  FOREIGN KEY (`Supplier_idSupplier`) REFERENCES `Supplier` (`idSupplier`) ON DELETE RESTRICT ON UPDATE CASCADE,
  FOREIGN KEY (`Cleaning_Supplies_name`) REFERENCES `Cleaning_Supplies` (`name`) ON DELETE RESTRICT ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS `Rent_Record` (
  `first_day_of_month` DATE NOT NULL PRIMARY KEY,
  `rent_amount` DECIMAL(8,2) NOT NULL,
  `due_date` DATE NOT NULL
);

CREATE TABLE IF NOT EXISTS `Maintenance` (
  `Equipment_idEquipment` INT NOT NULL,
  `Employee_idEmployee` INT NOT NULL,
  `date` DATE NOT NULL,
  `cost` DECIMAL(8,2) NOT NULL,
  `description` VARCHAR(500) NULL,
  PRIMARY KEY (`Equipment_idEquipment`, `Employee_idEmployee`, `date`),
  FOREIGN KEY (`Equipment_idEquipment`) REFERENCES `Equipment` (`idEquipment`) ON DELETE RESTRICT ON UPDATE CASCADE,
  FOREIGN KEY (`Employee_idEmployee`) REFERENCES `Employee` (`idEmployee`) ON DELETE RESTRICT ON UPDATE CASCADE
);


CREATE TABLE IF NOT EXISTS `Schedule` (
  `Employee_idEmployee` INT NOT NULL,
  `week_start_date` DATE NOT NULL,
  `Mon` SMALLINT NOT NULL,
  `Tue` SMALLINT NOT NULL,
  `Wed` SMALLINT NOT NULL,
  `Thu` SMALLINT NOT NULL,
  `Fri` SMALLINT NOT NULL,
  `Sat` SMALLINT NOT NULL,
  `Sun` SMALLINT NOT NULL,
  PRIMARY KEY (`Employee_idEmployee`, `week_start_date`),
  FOREIGN KEY (`Employee_idEmployee`) REFERENCES `Employee` (`idEmployee`) ON DELETE RESTRICT ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS `Service_has_Cleaning_Supplies` (
  `Service_idService` INT NOT NULL,
  `Cleaning_Supplies_name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`Service_idService`, `Cleaning_Supplies_name`),
    FOREIGN KEY (`Service_idService`) REFERENCES `Service` (`idService`) ON DELETE RESTRICT ON UPDATE CASCADE,
    FOREIGN KEY (`Cleaning_Supplies_name`) REFERENCES `Cleaning_Supplies` (`name`) ON DELETE RESTRICT ON UPDATE CASCADE
);

DROP TABLE IF EXISTS `Service_has_Cleaning_Supplies`;
DROP TABLE IF EXISTS `Schedule`;
DROP TABLE IF EXISTS `Maintenance`;
DROP TABLE IF EXISTS `Rent_Record`;
DROP TABLE IF EXISTS `Purchased_Cleaning_Supplies`;
DROP TABLE IF EXISTS `Purchased_Equipment`;
DROP TABLE IF EXISTS `Customer_Service_Equipment`;
DROP TABLE IF EXISTS `Supplier`;
DROP TABLE IF EXISTS `Service` ;
DROP TABLE IF EXISTS `Equipment` ;
DROP TABLE IF EXISTS `Employee` ;
DROP TABLE IF EXISTS `Customer`;
DROP TABLE IF EXISTS `Cleaning_Supplies`;

DROP SCHEMA IF EXISTS `Clean_and_Go_Shop`;
