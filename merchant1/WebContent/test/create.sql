# ---------------------------------------------------------------------- #
# Script generated with: DeZign for Databases v6.3.4                     #
# Target DBMS:           MySQL 5                                         #
# Project file:          inventory.dez                                   #
# Project name:                                                          #
# Author:                                                                #
# Script type:           Database creation script                        #
# Created on:            2012-01-18 20:29                                #
# ---------------------------------------------------------------------- #


# ---------------------------------------------------------------------- #
# Tables                                                                 #
# ---------------------------------------------------------------------- #

# ---------------------------------------------------------------------- #
# Add table "type_product"                                               #
# ---------------------------------------------------------------------- #

CREATE TABLE `type_product` (
    `type_product_id` INTEGER NOT NULL AUTO_INCREMENT,
    `type_product_name` VARCHAR(40),
    CONSTRAINT `PK_type_product` PRIMARY KEY (`type_product_id`)
);

# ---------------------------------------------------------------------- #
# Add table "brand_product"                                              #
# ---------------------------------------------------------------------- #

CREATE TABLE `brand_product` (
    `brand_product_id` INTEGER(3) NOT NULL,
    `brand_product_name` VARCHAR(40),
    CONSTRAINT `PK_brand_product` PRIMARY KEY (`brand_product_id`)
);

# ---------------------------------------------------------------------- #
# Add table "supplier"                                                   #
# ---------------------------------------------------------------------- #

CREATE TABLE `supplier` (
    `supp_id` INTEGER(5) NOT NULL AUTO_INCREMENT,
    `supp_company_name` VARCHAR(255),
    `supp_contact_name` VARCHAR(255),
    `supp_email` VARCHAR(255),
    `supp_tel` VARCHAR(40),
    `supp_mobile` VARCHAR(40),
    `supp_address` VARCHAR(255),
    `supp_desc` VARCHAR(255),
    CONSTRAINT `PK_supplier` PRIMARY KEY (`supp_id`)
);

# ---------------------------------------------------------------------- #
# Add table "customers"                                                  #
# ---------------------------------------------------------------------- #

CREATE TABLE `customers` (
    `cus_id` VARCHAR(10) NOT NULL,
    `cus_prefix` VARCHAR(10),
    `cus_fname` VARCHAR(40),
    `cus_lname` VARCHAR(40),
    `cus_tel` VARCHAR(40),
    `cus_mobile` VARCHAR(40),
    `cus_email` VARCHAR(40),
    `cus_address` VARCHAR(255),
    `cus_flag` VARCHAR(1),
    `cus_start_date` DATETIME,
    `cus_comment` VARCHAR(255),
    CONSTRAINT `PK_customers` PRIMARY KEY (`cus_id`)
);

# ---------------------------------------------------------------------- #
# Add table "user"                                                       #
# ---------------------------------------------------------------------- #

CREATE TABLE `user` (
    `usr_id` VARCHAR(10) NOT NULL,
    `usr_prefix` VARCHAR(10),
    `usr_fname` VARCHAR(40),
    `usr_lname` VARCHAR(40),
    `usr_tel` VARCHAR(40),
    `usr_mobile` VARCHAR(40),
    `usr_email` VARCHAR(40),
    `usr_address` VARCHAR(40),
    `usr_login` VARCHAR(40),
    `usr_password` VARCHAR(40),
    `usr_activate` VARCHAR(40),
    `usr_citizen_id` VARCHAR(40),
    `usr_date` DATETIME,
    CONSTRAINT `PK_user` PRIMARY KEY (`usr_id`)
);

# ---------------------------------------------------------------------- #
# Add table "claim"                                                      #
# ---------------------------------------------------------------------- #

CREATE TABLE `claim` (
    `c_invoice` VARCHAR(10) NOT NULL,
    `c_date` DATETIME,
    `total_price` FLOAT,
    `total_unit` INTEGER,
    `supp_id` INTEGER(5),
    `usr_id` VARCHAR(10),
    CONSTRAINT `PK_claim` PRIMARY KEY (`c_invoice`)
);

# ---------------------------------------------------------------------- #
# Add table "product"                                                    #
# ---------------------------------------------------------------------- #

CREATE TABLE `product` (
    `product_id` VARCHAR(10) NOT NULL,
    `product_name` VARCHAR(255),
    `type_product_id` INTEGER NOT NULL,
    `brand_product_id` INTEGER(3) NOT NULL,
    `price_cost` FLOAT,
    `price_sale` FLOAT,
    `product_item` INTEGER(7),
    `product_date_start` DATE,
    `product_date_expire` DATE,
    `product_desc` VARCHAR(255),
    CONSTRAINT `PK_product` PRIMARY KEY (`product_id`)
);

# ---------------------------------------------------------------------- #
# Add table "purchase"                                                   #
# ---------------------------------------------------------------------- #

CREATE TABLE `purchase` (
    `p_invoice` VARCHAR(10) NOT NULL,
    `p_date` DATE,
    `total_unit` INTEGER,
    `total_price` FLOAT,
    `supp_id` INTEGER(5),
    `usr_id` VARCHAR(10),
    PRIMARY KEY (`p_invoice`)
);

# ---------------------------------------------------------------------- #
# Add table "purchase_item"                                              #
# ---------------------------------------------------------------------- #

CREATE TABLE `purchase_item` (
    `unit` INTEGER,
    `price` FLOAT,
    `tdate` DATETIME,
    `product_id` VARCHAR(10),
    `p_invoice` VARCHAR(10)
);

# ---------------------------------------------------------------------- #
# Add table "oders"                                                      #
# ---------------------------------------------------------------------- #

CREATE TABLE `oders` (
    `o_invoice` VARCHAR(40) NOT NULL,
    `o_date` DATETIME,
    `total_price` FLOAT,
    `total_unit` INTEGER,
    `cus_id` VARCHAR(10),
    `usr_id` VARCHAR(10),
    CONSTRAINT `PK_oders` PRIMARY KEY (`o_invoice`)
);

# ---------------------------------------------------------------------- #
# Add table "order_item"                                                 #
# ---------------------------------------------------------------------- #

CREATE TABLE `order_item` (
    `unit` INTEGER,
    `price` FLOAT,
    `tdate` DATETIME,
    `o_invoice` VARCHAR(40),
    `product_id` VARCHAR(10)
);

# ---------------------------------------------------------------------- #
# Add table "claim_item"                                                 #
# ---------------------------------------------------------------------- #

CREATE TABLE `claim_item` (
    `unit` INTEGER,
    `price` FLOAT,
    `tdate` DATETIME,
    `c_invoice` VARCHAR(10),
    `product_id` VARCHAR(10)
);

# ---------------------------------------------------------------------- #
# Foreign key constraints                                                #
# ---------------------------------------------------------------------- #

ALTER TABLE `product` ADD CONSTRAINT `type_product_product` 
    FOREIGN KEY (`type_product_id`) REFERENCES `type_product` (`type_product_id`);

ALTER TABLE `product` ADD CONSTRAINT `brand_product_product` 
    FOREIGN KEY (`brand_product_id`) REFERENCES `brand_product` (`brand_product_id`);

ALTER TABLE `purchase` ADD CONSTRAINT `supplier_purchase` 
    FOREIGN KEY (`supp_id`) REFERENCES `supplier` (`supp_id`);

ALTER TABLE `purchase` ADD CONSTRAINT `user_purchase` 
    FOREIGN KEY (`usr_id`) REFERENCES `user` (`usr_id`);

ALTER TABLE `purchase_item` ADD CONSTRAINT `product_purchase_item` 
    FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`);

ALTER TABLE `purchase_item` ADD CONSTRAINT `purchase_purchase_item` 
    FOREIGN KEY (`p_invoice`) REFERENCES `purchase` (`p_invoice`);

ALTER TABLE `oders` ADD CONSTRAINT `customers_oders` 
    FOREIGN KEY (`cus_id`) REFERENCES `customers` (`cus_id`);

ALTER TABLE `oders` ADD CONSTRAINT `user_oders` 
    FOREIGN KEY (`usr_id`) REFERENCES `user` (`usr_id`);

ALTER TABLE `order_item` ADD CONSTRAINT `oders_order_item` 
    FOREIGN KEY (`o_invoice`) REFERENCES `oders` (`o_invoice`);

ALTER TABLE `order_item` ADD CONSTRAINT `product_order_item` 
    FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`);

ALTER TABLE `claim` ADD CONSTRAINT `supplier_claim` 
    FOREIGN KEY (`supp_id`) REFERENCES `supplier` (`supp_id`);

ALTER TABLE `claim` ADD CONSTRAINT `user_claim` 
    FOREIGN KEY (`usr_id`) REFERENCES `user` (`usr_id`);

ALTER TABLE `claim_item` ADD CONSTRAINT `claim_claim_item` 
    FOREIGN KEY (`c_invoice`) REFERENCES `claim` (`c_invoice`);

ALTER TABLE `claim_item` ADD CONSTRAINT `product_claim_item` 
    FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`);
