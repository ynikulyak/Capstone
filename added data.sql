-- Team 11 Order Application
-- Yauheniya Nikulyak, Thach Doan
-- Final Project, CST 449 SUM2020

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- Schema FlightReservation
DROP SCHEMA IF EXISTS OrderApp;
CREATE SCHEMA IF NOT EXISTS `OrderApp` DEFAULT CHARACTER SET utf8 ;
USE `OrderApp` ;

-- Table `Category`
DROP TABLE IF EXISTS `Categories`;
CREATE TABLE IF NOT EXISTS `Categories` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`id`)
)
ENGINE = InnoDB;


-- Add 5 more categories from screenshot I sent you yesturday
INSERT INTO `Categories` (`id`, `name`) VALUES (1, "coffee");
INSERT INTO `Categories` (`id`, `name`) VALUES (2, "Milk teas");
INSERT INTO `Categories` (`id`, `name`) VALUES (3, "Slushy Freeze");
INSERT INTO `Categories` (`id`, `name`) VALUES (4, "Smoothies");
INSERT INTO `Categories` (`id`, `name`) VALUES (5, "SpecialTeas");
INSERT INTO `Categories` (`id`, `name`) VALUES (6, "Food");




-- Table `Product`
DROP TABLE IF EXISTS `Products`;
CREATE TABLE IF NOT EXISTS `Products` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `category_id` INT(11) NOT NULL,
  `name` VARCHAR(30) NOT NULL,
  `description` TEXT,
  `thumb` VARCHAR(100),
  `full_image` VARCHAR(100),
  PRIMARY KEY (`id`),
  KEY `category_id` (`category_id`),
  CONSTRAINT `category_idfk_1` FOREIGN KEY (`category_id`) REFERENCES `Categories` (`id`)

)
ENGINE = InnoDB;



-- Insert all products of each category, be careful with ids of categories
INSERT INTO `Products` (`id`, `category_id`,`name`,`description`,`thum`,`full_image`) 
VALUES(1, 1,"House Special","slow drip coffee, condensed milk & salted cream","","");
INSERT INTO `Products` (`id`, `category_id`,`name`,`description`,`thum`,`full_image`) 
VALUES(2, 1,"Vietnamese Ice Coffee","dark roast slow drip coffee with cream & condensed milk","","");
INSERT INTO `Products` (`id`, `category_id`,`name`,`description`,`thum`,`full_image`) 
VALUES(3, 1,"Matcha Coffee","slow drip coffee layered with matcha green milk tea","","");
INSERT INTO `Products` (`id`, `category_id`,`name`,`description`,`thum`,`full_image`) 
VALUES(4, 1,"Black Vietnamese Iced Coffee","Slow drip black coffee with cane ","","");
INSERT INTO `Products` (`id`, `category_id`,`name`,`description`,`thum`,`full_image`) 
VALUES(5, 2,"Classic Milk Tea","formosa black tea with choice of dairy","","");
INSERT INTO `Products` (`id`, `category_id`,`name`,`description`,`thum`,`full_image`) 
VALUES(6, 2,"Jasmine Milk Tea","jasmine green tea with choice of dairy","","");
INSERT INTO `Products` (`id`, `category_id`,`name`,`description`,`thum`,`full_image`) 
VALUES(7, 2,"Winter Melon Green Mikl Tea","Our all-new winter melon tea with your choice of dairy","","");
INSERT INTO `Products` (`id`, `category_id`,`name`,`description`,`thum`,`full_image`) 
VALUES(8, 2,"Winter Melon Cheese Foam","Our all-new winter melon tea topped with freshly made cream cheese foam","","");
INSERT INTO `Products` (`id`, `category_id`,`name`,`description`,`thum`,`full_image`) 
VALUES(9, 2,"Golden Milk Tea","assam black tea with choice of dairy","","");
INSERT INTO `Products` (`id`, `category_id`,`name`,`description`,`thum`,`full_image`) 
VALUES(10, 2,"Matcha Milk Tea","matcha jasmine green tea with choice of dairy","","");
INSERT INTO `Products` (`id`, `category_id`,`name`,`description`,`thum`,`full_image`) 
VALUES(11, 2,"Thai Milk Tea","thai tea with cream" ,"","");
INSERT INTO `Products` (`id`, `category_id`,`name`,`description`,`thum`,`full_image`) 
VALUES(12, 2,"Matcha Thai Milk Tea","matcha thai tea with choice of dairy" ,"","");
INSERT INTO `Products` (`id`, `category_id`,`name`,`description`,`thum`,`full_image`) 
VALUES(13, 2,"Taro Milk Tea","taro black tea with choice of dairy" ,"","");
INSERT INTO `Products` (`id`, `category_id`,`name`,`description`,`thum`,`full_image`) 
VALUES(14, 2,"Honeydew Milk Tea","honeydew black tea with choice of dairy" ,"","");
INSERT INTO `Products` (`id`, `category_id`,`name`,`description`,`thum`,`full_image`) 
VALUES(15, 2,"Coconut Milk Tea","coconut black tea with choice of dairy" ,"","");
INSERT INTO `Products` (`id`, `category_id`,`name`,`description`,`thum`,`full_image`) 
VALUES(16, 2,"Almond Milk Tea","almond black tea with choice of dairy" ,"","");
INSERT INTO `Products` (`id`, `category_id`,`name`,`description`,`thum`,`full_image`) 
VALUES(17, 2,"Strawberry Milk Tea","strawberry black tea with choice of dairy" ,"","");
INSERT INTO `Products` (`id`, `category_id`,`name`,`description`,`thum`,`full_image`) 
VALUES(18, 3,"Mango All The Way","mango & raspberry with green mango bits" ,"","");
INSERT INTO `Products` (`id`, `category_id`,`name`,`description`,`thum`,`full_image`) 
VALUES(19, 3,"Peach Me Freezie","peach & strawberry with peach & strawberry bits" ,"","");
INSERT INTO `Products` (`id`, `category_id`,`name`,`description`,`thum`,`full_image`) 
VALUES(20, 3,"Lychee Freeze","lychee with lychee bits & lychee jelly" ,"","");
INSERT INTO `Products` (`id`, `category_id`,`name`,`description`,`thum`,`full_image`) 
VALUES(21, 3,"Amazing Strawburst","strawberry, raspberry & honeydew with rainbow jelly" ,"","");
INSERT INTO `Products` (`id`, `category_id`,`name`,`description`,`thum`,`full_image`) 
VALUES(22, 4,"Strawberry Sensation","strawberry with strawberry bits" ,"","");
INSERT INTO `Products` (`id`, `category_id`,`name`,`description`,`thum`,`full_image`) 
VALUES(23, 4,"ohh La La","coconut & pineapple with strawberry bits" ,"","");
INSERT INTO `Products` (`id`, `category_id`,`name`,`description`,`thum`,`full_image`) 
VALUES(24, 4,"Avocado Avalanche","made with fresh avocado" ,"","");
INSERT INTO `Products` (`id`, `category_id`,`name`,`description`,`thum`,`full_image`) 
VALUES(25, 4,"Krypteanite","matcha green tea" ,"","");
INSERT INTO `Products` (`id`, `category_id`,`name`,`description`,`thum`,`full_image`) 
VALUES(26, 4,"Taro Fantasy","blended taru" ,"","");
INSERT INTO `Products` (`id`, `category_id`,`name`,`description`,`thum`,`full_image`) 
VALUES(27, 4,"strawberry Bonanza","strawberry & banana" ,"","");
INSERT INTO `Products` (`id`, `category_id`,`name`,`description`,`thum`,`full_image`) 
VALUES(28, 4,"Yummy Gummy","orange, raspberry & passion fruit with peach bits" ,"","");
INSERT INTO `Products` (`id`, `category_id`,`name`,`description`,`thum`,`full_image`) 
VALUES(29, 5,"Peach Me Sweetea","peach & strawberry black tea with peach and strawberry bits" ,"","");
INSERT INTO `Products` (`id`, `category_id`,`name`,`description`,`thum`,`full_image`) 
VALUES(30, 5,"Lechee Peachee","peach black tea with diced lychee" ,"","");
INSERT INTO `Products` (`id`, `category_id`,`name`,`description`,`thum`,`full_image`) 
VALUES(31, 5,"Bootea Shaker","mango, peach & pineapple tea with black jelly" ,"","");
INSERT INTO `Products` (`id`, `category_id`,`name`,`description`,`thum`,`full_image`) 
VALUES(32, 5,"Sunset Peach","peach & raspberry black tea with mango bits" ,"","");
INSERT INTO `Products` (`id`, `category_id`,`name`,`description`,`thum`,`full_image`) 
VALUES(33, 5,"Strawberry Ecsteasy","strawberry black tea with black jelly" ,"","");
INSERT INTO `Products` (`id`, `category_id`,`name`,`description`,`thum`,`full_image`) 
VALUES(34, 5,"Passion Attraction","passion fruit black tea with coconut jelly" ,"","");
INSERT INTO `Products` (`id`, `category_id`,`name`,`description`,`thum`,`full_image`) 
VALUES(35, 5,"Tootea Fruitea","kiwi & pineapple black tea with diced jackfruit, lychee & longan" ,"","");
INSERT INTO `Products` (`id`, `category_id`,`name`,`description`,`thum`,`full_image`) 
VALUES(36, 5,"The Unknown","mango & passion fruit black tea with diced longan" ,"","");
INSERT INTO `Products` (`id`, `category_id`,`name`,`description`,`thum`,`full_image`) 
VALUES(37, 6,"Karate chicken","all white meat popcorn chicken freshly hand battered in our own secret seasonings. served at 5 spicy levels" ,"","");
INSERT INTO `Products` (`id`, `category_id`,`name`,`description`,`thum`,`full_image`) 
VALUES(38, 6,"Buttload'a Fries","fresh cut idaho potatoes lightly hand battered and seasoned with Tastea Dust" ,"","");
INSERT INTO `Products` (`id`, `category_id`,`name`,`description`,`thum`,`full_image`) 
VALUES(39, 6,"Shrimp on It!","plump tail on shrimp freshly hand battered in our own secret seasonings. served at 5 spicy levels" ,"","");






-- Table `Sizes`
DROP TABLE IF EXISTS `Sizes`;
CREATE TABLE IF NOT EXISTS `Sizes` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `volume` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`id`)
)
ENGINE = InnoDB;




-- Should be one more size
INSERT INTO `Sizes` (`id`, `volume`) 
VALUES(1, "20oz");
INSERT INTO `Sizes` (`id`, `volume`) 
VALUES(2, "32oz");
INSERT INTO `Sizes` (`id`, `volume`) 
VALUES(3, "Regular");
INSERT INTO `Sizes` (`id`, `volume`) 
VALUES(4, "Large");




-- Table `Product_size`
DROP TABLE IF EXISTS `Product_sizes`;
CREATE TABLE IF NOT EXISTS `Product_sizes` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `size_id` INT(11) NOT NULL,
  `product_id` INT(11) NOT NULL,
  `price` DECIMAL(5, 2) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `size_id` (`size_id`),
  CONSTRAINT `size_idfk_1` FOREIGN KEY (`size_id`) REFERENCES `Sizes` (`id`),
  KEY `product_id` (`product_id`),
  CONSTRAINT `product_idfk_2` FOREIGN KEY (`product_id`) REFERENCES `Products` (`id`)
)
ENGINE = InnoDB;



-- This is for House Special coffee
INSERT INTO `Product_sizes` (`id`, `size_id`,`product_id`,`price`) 
VALUES(1, 1, 1, 4.25);
INSERT INTO `Product_sizes` (`id`, `size_id`,`product_id`,`price`) 
VALUES(2, 2, 1, 6.00);
-- this is for Vietnamese Iced Coffee
INSERT INTO `Product_sizes` (`id`, `size_id`,`product_id`,`price`) 
VALUES(3, 1, 2, 4.25);
INSERT INTO `Product_sizes` (`id`, `size_id`,`product_id`,`price`) 
VALUES(4, 2, 2, 6.00);
-- this is for Matcha Coffee
INSERT INTO `Product_sizes` (`id`, `size_id`,`product_id`,`price`) 
VALUES(5, 1, 3, 4.85);
INSERT INTO `Product_sizes` (`id`, `size_id`,`product_id`,`price`) 
VALUES(6, 2, 3, 6.60);
-- this is for Black Vietnamese Coffee
INSERT INTO `Product_sizes` (`id`, `size_id`,`product_id`,`price`) 
VALUES(7, 1, 4, 4.00);
-- this is for classic milk tea
INSERT INTO `Product_sizes` (`id`, `size_id`,`product_id`,`price`) 
VALUES(8, 1, 5, 4.85);
INSERT INTO `Product_sizes` (`id`, `size_id`,`product_id`,`price`) 
VALUES(9, 2, 5, 6.60);
-- this is for Jasmine Milk Tea
INSERT INTO `Product_sizes` (`id`, `size_id`,`product_id`,`price`) 
VALUES(10, 1, 6, 4.85);
INSERT INTO `Product_sizes` (`id`, `size_id`,`product_id`,`price`) 
VALUES(11, 2, 6, 6.60);
-- this is for Winter melon
INSERT INTO `Product_sizes` (`id`, `size_id`,`product_id`,`price`) 
VALUES(12, 1, 7, 4.85);
INSERT INTO `Product_sizes` (`id`, `size_id`,`product_id`,`price`) 
VALUES(13, 2, 7, 6.60);
-- this is for winter melon cheese foam
INSERT INTO `Product_sizes` (`id`, `size_id`,`product_id`,`price`) 
VALUES(14, 1, 8, 4.85);
INSERT INTO `Product_sizes` (`id`, `size_id`,`product_id`,`price`) 
VALUES(15, 2, 8, 6.60);
-- this is for golden Milk Tea
INSERT INTO `Product_sizes` (`id`, `size_id`,`product_id`,`price`) 
VALUES(16, 1, 9, 4.85);
INSERT INTO `Product_sizes` (`id`, `size_id`,`product_id`,`price`) 
VALUES(17, 2, 9, 6.60);
-- this is for Matcha milk tea
INSERT INTO `Product_sizes` (`id`, `size_id`,`product_id`,`price`) 
VALUES(18, 1, 10, 4.85);
INSERT INTO `Product_sizes` (`id`, `size_id`,`product_id`,`price`) 
VALUES(19, 2, 10, 6.60);
-- this is for Thai milk tea
INSERT INTO `Product_sizes` (`id`, `size_id`,`product_id`,`price`) 
VALUES(20, 1, 11, 4.85);
INSERT INTO `Product_sizes` (`id`, `size_id`,`product_id`,`price`) 
VALUES(21, 2, 11, 6.60);
-- this is for Matcha Thai Milk tea
INSERT INTO `Product_sizes` (`id`, `size_id`,`product_id`,`price`) 
VALUES(22, 1, 12, 4.85);
INSERT INTO `Product_sizes` (`id`, `size_id`,`product_id`,`price`) 
VALUES(23, 2, 12, 6.60);
-- this is for Taro milk tea
INSERT INTO `Product_sizes` (`id`, `size_id`,`product_id`,`price`) 
VALUES(24, 1, 13, 4.85);
INSERT INTO `Product_sizes` (`id`, `size_id`,`product_id`,`price`) 
VALUES(25, 2, 13, 6.60);
-- this is for Honeydew milk tea
INSERT INTO `Product_sizes` (`id`, `size_id`,`product_id`,`price`) 
VALUES(26, 1, 14, 4.85);
INSERT INTO `Product_sizes` (`id`, `size_id`,`product_id`,`price`) 
VALUES(27, 2, 14, 6.60);
-- this is for Coconut milk tea
INSERT INTO `Product_sizes` (`id`, `size_id`,`product_id`,`price`) 
VALUES(28, 1, 15, 4.85);
INSERT INTO `Product_sizes` (`id`, `size_id`,`product_id`,`price`) 
VALUES(29, 2, 15, 6.60);
-- this is for almond milk tea
INSERT INTO `Product_sizes` (`id`, `size_id`,`product_id`,`price`) 
VALUES(30, 1, 16, 4.85);
INSERT INTO `Product_sizes` (`id`, `size_id`,`product_id`,`price`) 
VALUES(31, 2, 16, 6.60);
-- this is for Strawberry milk tea
INSERT INTO `Product_sizes` (`id`, `size_id`,`product_id`,`price`) 
VALUES(32, 1, 17, 4.55);
INSERT INTO `Product_sizes` (`id`, `size_id`,`product_id`,`price`) 
VALUES(33, 2, 17, 6.30);
-- this is for Mango All The Way
INSERT INTO `Product_sizes` (`id`, `size_id`,`product_id`,`price`) 
VALUES(34, 1, 18, 5.00);
INSERT INTO `Product_sizes` (`id`, `size_id`,`product_id`,`price`) 
VALUES(35, 2, 18, 6.75);
-- this is for Peach Me Freezie
INSERT INTO `Product_sizes` (`id`, `size_id`,`product_id`,`price`) 
VALUES(36, 1, 19, 5.00);
INSERT INTO `Product_sizes` (`id`, `size_id`,`product_id`,`price`) 
VALUES(37, 2, 19, 6.75);
-- this is for Lychee Freeze
INSERT INTO `Product_sizes` (`id`, `size_id`,`product_id`,`price`) 
VALUES(38, 1, 20, 5.00);
INSERT INTO `Product_sizes` (`id`, `size_id`,`product_id`,`price`) 
VALUES(39, 2, 20, 6.75);
-- this is for Amazing Strawberry
INSERT INTO `Product_sizes` (`id`, `size_id`,`product_id`,`price`) 
VALUES(40, 1, 21, 5.00);
INSERT INTO `Product_sizes` (`id`, `size_id`,`product_id`,`price`) 
VALUES(41, 2, 21, 6.75);
-- this is for Strawberry Sensation
INSERT INTO `Product_sizes` (`id`, `size_id`,`product_id`,`price`) 
VALUES(42, 1, 22, 5.25);
INSERT INTO `Product_sizes` (`id`, `size_id`,`product_id`,`price`) 
VALUES(43, 2, 22, 7.00);
-- this is for Ooh La La
INSERT INTO `Product_sizes` (`id`, `size_id`,`product_id`,`price`) 
VALUES(44, 1, 23, 5.25);
INSERT INTO `Product_sizes` (`id`, `size_id`,`product_id`,`price`) 
VALUES(45, 2, 23, 7.00);
-- this is for Avocado Avalanche
INSERT INTO `Product_sizes` (`id`, `size_id`,`product_id`,`price`) 
VALUES(46, 1, 24, 6.40);
INSERT INTO `Product_sizes` (`id`, `size_id`,`product_id`,`price`) 
VALUES(47, 2, 24, 8.15);
-- this is for Krypteanite
INSERT INTO `Product_sizes` (`id`, `size_id`,`product_id`,`price`) 
VALUES(48, 1, 25, 5.25);
INSERT INTO `Product_sizes` (`id`, `size_id`,`product_id`,`price`) 
VALUES(49, 2, 25, 7.00);
-- this is for Taro fantasy
INSERT INTO `Product_sizes` (`id`, `size_id`,`product_id`,`price`) 
VALUES(50, 1, 26, 5.25);
INSERT INTO `Product_sizes` (`id`, `size_id`,`product_id`,`price`) 
VALUES(51, 2, 26, 7.00);
-- this is for Strawberry Bananza
INSERT INTO `Product_sizes` (`id`, `size_id`,`product_id`,`price`) 
VALUES(52, 1, 27, 5.25);
INSERT INTO `Product_sizes` (`id`, `size_id`,`product_id`,`price`) 
VALUES(53, 2, 27, 7.00);
-- this is for Yummy gummy
INSERT INTO `Product_sizes` (`id`, `size_id`,`product_id`,`price`) 
VALUES(54, 1, 28, 5.25);
INSERT INTO `Product_sizes` (`id`, `size_id`,`product_id`,`price`) 
VALUES(55, 2, 28, 7.00);
-- this is for Peach Me Sweetea
INSERT INTO `Product_sizes` (`id`, `size_id`,`product_id`,`price`) 
VALUES(56, 1, 29, 4.85);
INSERT INTO `Product_sizes` (`id`, `size_id`,`product_id`,`price`) 
VALUES(57, 2, 29, 6.60);
-- this is for Lychee Peachee
INSERT INTO `Product_sizes` (`id`, `size_id`,`product_id`,`price`) 
VALUES(58, 1, 30, 4.85);
INSERT INTO `Product_sizes` (`id`, `size_id`,`product_id`,`price`) 
VALUES(59, 2, 30, 6.60);
-- this is for Bootea shaker
INSERT INTO `Product_sizes` (`id`, `size_id`,`product_id`,`price`) 
VALUES(60, 1, 31, 4.85);
INSERT INTO `Product_sizes` (`id`, `size_id`,`product_id`,`price`) 
VALUES(61, 2, 31, 6.60);
-- this is for sunset Peach
INSERT INTO `Product_sizes` (`id`, `size_id`,`product_id`,`price`) 
VALUES(62, 1, 32, 4.85);
INSERT INTO `Product_sizes` (`id`, `size_id`,`product_id`,`price`) 
VALUES(63, 2, 32, 6.60);
-- this is for Strawberry Ecsteasy
INSERT INTO `Product_sizes` (`id`, `size_id`,`product_id`,`price`) 
VALUES(64, 1, 33, 4.85);
INSERT INTO `Product_sizes` (`id`, `size_id`,`product_id`,`price`) 
VALUES(65, 2, 33, 6.60);
-- this is for Passion Attraction
INSERT INTO `Product_sizes` (`id`, `size_id`,`product_id`,`price`) 
VALUES(66, 1, 34, 4.85);
INSERT INTO `Product_sizes` (`id`, `size_id`,`product_id`,`price`) 
VALUES(67, 2, 34, 6.60);
-- this is for tootea Fruitea
INSERT INTO `Product_sizes` (`id`, `size_id`,`product_id`,`price`) 
VALUES(68, 1, 35, 4.85);
INSERT INTO `Product_sizes` (`id`, `size_id`,`product_id`,`price`) 
VALUES(69, 2, 35, 6.60);
-- this is for The Unknown
INSERT INTO `Product_sizes` (`id`, `size_id`,`product_id`,`price`) 
VALUES(70, 1, 36, 4.85);
INSERT INTO `Product_sizes` (`id`, `size_id`,`product_id`,`price`) 
VALUES(71, 2, 36, 6.60);
-- this is for Karate chicken
INSERT INTO `Product_sizes` (`id`, `size_id`,`product_id`,`price`) 
VALUES(72, 3, 37, 6.25);
INSERT INTO `Product_sizes` (`id`, `size_id`,`product_id`,`price`) 
VALUES(73, 4, 37, 9.00);
-- this is for Buttload'a Fries
INSERT INTO `Product_sizes` (`id`, `size_id`,`product_id`,`price`) 
VALUES(74, 3, 38, 4.95);
-- this is for Shrimpt on It!
INSERT INTO `Product_sizes` (`id`, `size_id`,`product_id`,`price`) 
VALUES(75, 3, 39, 6.95);
INSERT INTO `Product_sizes` (`id`, `size_id`,`product_id`,`price`) 
VALUES(76, 4, 39, 11.00);

-- Table `Attributes`
DROP TABLE IF EXISTS `Attributes`;
CREATE TABLE IF NOT EXISTS `Attributes` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`id`)
)
ENGINE = InnoDB;




-- Add 5 more attr and extra add ons
INSERT INTO `Attributes` (`id`, `name`) 
VALUES(1, "Boba");
INSERT INTO `Attributes` (`id`, `name`) 
VALUES(2, "Jelly");
INSERT INTO `Attributes` (`id`, `name`) 
VALUES(3, "Fruit Bits");
INSERT INTO `Attributes` (`id`, `name`) 
VALUES(4, "Misc");



-- Table `Attribute_values`
DROP TABLE IF EXISTS `Attribute_values`;
CREATE TABLE IF NOT EXISTS `Attribute_values` (
  `attribute_value_id` INT(11) NOT NULL AUTO_INCREMENT,
  `attribute_id` INT(11) NOT NULL,
  `attribute_value_name` VARCHAR(50) NOT NULL,
  `price` DECIMAL(5, 2) NOT NULL,
  PRIMARY KEY (`attribute_value_id`),
  KEY `attribute_id` (`attribute_id`),
  CONSTRAINT `attribute_idfk` FOREIGN KEY (`attribute_id`) REFERENCES `Attributes` (`id`)
)
ENGINE = InnoDB;



-- Insert all values for all attributes, would be a lot, this one for boba
INSERT INTO `Attribute_values` (`attribute_value_id`, `attribute_id`,`attribute_value_name`, `price`) 
VALUES(1, 1, "Original Boba", 0.70);
INSERT INTO `Attribute_values` (`attribute_value_id`, `attribute_id`,`attribute_value_name`, `price`) 
VALUES(2, 1, "Honey Boba", 0.70);
INSERT INTO `Attribute_values` (`attribute_value_id`, `attribute_id`,`attribute_value_name`, `price`) 
VALUES(3, 1, "Crystal Boba", 0.90);
INSERT INTO `Attribute_values` (`attribute_value_id`, `attribute_id`,`attribute_value_name`, `price`) 
VALUES(4, 2, "Lychee Jelly", 0.70);
INSERT INTO `Attribute_values` (`attribute_value_id`, `attribute_id`,`attribute_value_name`, `price`) 
VALUES(5, 2, "Coconut Jelly", 0.70);
INSERT INTO `Attribute_values` (`attribute_value_id`, `attribute_id`,`attribute_value_name`, `price`) 
VALUES(6, 2, "Rainbow Jelly", 0.70);
INSERT INTO `Attribute_values` (`attribute_value_id`, `attribute_id`,`attribute_value_name`, `price`) 
VALUES(7, 2, "Black Jelly", 0.70);
INSERT INTO `Attribute_values` (`attribute_value_id`, `attribute_id`,`attribute_value_name`, `price`) 
VALUES(8, 2, "Aloe Jelly", 0.70);
INSERT INTO `Attribute_values` (`attribute_value_id`, `attribute_id`,`attribute_value_name`, `price`) 
VALUES(9, 3, "Peach Bits", 0.70);
INSERT INTO `Attribute_values` (`attribute_value_id`, `attribute_id`,`attribute_value_name`, `price`) 
VALUES(10, 3, "Strawberry Bits", 0.70);
INSERT INTO `Attribute_values` (`attribute_value_id`, `attribute_id`,`attribute_value_name`, `price`) 
VALUES(11, 3, "Lychee Bits", 0.70);
INSERT INTO `Attribute_values` (`attribute_value_id`, `attribute_id`,`attribute_value_name`, `price`) 
VALUES(12, 3, "Longan Bits", 0.70);
INSERT INTO `Attribute_values` (`attribute_value_id`, `attribute_id`,`attribute_value_name`, `price`) 
VALUES(13, 3, "Mango Bits", 0.70);
INSERT INTO `Attribute_values` (`attribute_value_id`, `attribute_id`,`attribute_value_name`, `price`) 
VALUES(14, 3, "Jackfruit Bits", 0.70);
INSERT INTO `Attribute_values` (`attribute_value_id`, `attribute_id`,`attribute_value_name`, `price`) 
VALUES(15, 4, "Creme Brulee", 0.70);
INSERT INTO `Attribute_values` (`attribute_value_id`, `attribute_id`,`attribute_value_name`, `price`) 
VALUES(16, 4, "Cheese Foam", 0.30);
INSERT INTO `Attribute_values` (`attribute_value_id`, `attribute_id`,`attribute_value_name`, `price`) 
VALUES(17, 4, "Salted Cream", 0.30);
INSERT INTO `Attribute_values` (`attribute_value_id`, `attribute_id`,`attribute_value_name`, `price`) 
VALUES(18, 4, "Basil Seeds", 0.70);





SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;