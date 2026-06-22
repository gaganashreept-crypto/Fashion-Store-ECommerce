
-- MySQL dump 10.13  Distrib 8.0.46, for Win64 (x86_64)
--
-- Host: localhost    Database: fashion_store
-- ------------------------------------------------------
-- Server version	8.0.46

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `cart`
--

DROP TABLE IF EXISTS `cart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cart` (
  `cart_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int DEFAULT NULL,
  PRIMARY KEY (`cart_id`),
  UNIQUE KEY `user_id` (`user_id`),
  CONSTRAINT `cart_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart`
--

LOCK TABLES `cart` WRITE;
/*!40000 ALTER TABLE `cart` DISABLE KEYS */;
INSERT INTO `cart` VALUES (1,1),(2,2),(3,3),(4,4),(5,5),(6,10),(7,11);
/*!40000 ALTER TABLE `cart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cart_items`
--

DROP TABLE IF EXISTS `cart_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cart_items` (
  `cart_item_id` int NOT NULL AUTO_INCREMENT,
  `cart_id` int NOT NULL,
  `variant_id` int NOT NULL,
  `quantity` int NOT NULL,
  PRIMARY KEY (`cart_item_id`),
  KEY `cart_id` (`cart_id`),
  KEY `variant_id` (`variant_id`),
  CONSTRAINT `cart_items_ibfk_1` FOREIGN KEY (`cart_id`) REFERENCES `cart` (`cart_id`) ON DELETE CASCADE,
  CONSTRAINT `cart_items_ibfk_2` FOREIGN KEY (`variant_id`) REFERENCES `product_variants` (`variant_id`) ON DELETE CASCADE,
  CONSTRAINT `cart_items_chk_1` CHECK ((`quantity` > 0))
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart_items`
--

LOCK TABLES `cart_items` WRITE;
/*!40000 ALTER TABLE `cart_items` DISABLE KEYS */;
INSERT INTO `cart_items` VALUES (1,1,1,2),(2,1,15,1),(3,2,7,1),(4,2,22,1),(5,3,10,2),(6,4,18,1),(7,4,20,2),(8,5,5,1);
/*!40000 ALTER TABLE `cart_items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `categories`
--

DROP TABLE IF EXISTS `categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categories` (
  `category_id` int NOT NULL AUTO_INCREMENT,
  `category_name` varchar(100) NOT NULL,
  PRIMARY KEY (`category_id`),
  UNIQUE KEY `category_name` (`category_name`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categories`
--

LOCK TABLES `categories` WRITE;
/*!40000 ALTER TABLE `categories` DISABLE KEYS */;
INSERT INTO `categories` VALUES (5,'Accessories'),(4,'Footwear'),(3,'Kids'),(1,'Men'),(2,'Women');
/*!40000 ALTER TABLE `categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_items`
--

DROP TABLE IF EXISTS `order_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_items` (
  `order_item_id` int NOT NULL AUTO_INCREMENT,
  `order_id` int NOT NULL,
  `variant_id` int NOT NULL,
  `quantity` int NOT NULL,
  `price` decimal(10,2) NOT NULL,
  PRIMARY KEY (`order_item_id`),
  KEY `order_id` (`order_id`),
  KEY `fk_product` (`variant_id`),
  CONSTRAINT `fk_product` FOREIGN KEY (`variant_id`) REFERENCES `products` (`product_id`) ON DELETE CASCADE,
  CONSTRAINT `order_items_ibfk_1` FOREIGN KEY (`order_id`) REFERENCES `orders` (`order_id`) ON DELETE CASCADE,
  CONSTRAINT `order_items_chk_1` CHECK ((`quantity` > 0))
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_items`
--

LOCK TABLES `order_items` WRITE;
/*!40000 ALTER TABLE `order_items` DISABLE KEYS */;
INSERT INTO `order_items` VALUES (8,1,1,2,799.00),(9,1,15,1,2499.00),(10,2,7,2,1299.00),(11,3,10,1,1699.00),(12,4,18,1,999.00),(13,4,20,1,499.00),(14,5,16,1,2999.00),(15,1,1,2,799.00),(16,1,15,1,2499.00),(17,2,7,2,1299.00),(18,3,10,1,1699.00),(19,4,18,1,999.00),(20,4,20,1,499.00),(21,5,16,1,2999.00),(22,6,20,5,499.00),(23,6,21,4,3499.00),(24,6,2,1,1499.00),(25,6,6,1,1899.00),(26,7,18,1,999.00),(27,8,21,1,3499.00),(28,8,20,1,499.00),(29,9,11,2,599.00),(30,10,19,1,899.00);
/*!40000 ALTER TABLE `order_items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `order_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `total_amount` decimal(10,2) NOT NULL,
  `status` varchar(50) DEFAULT 'PLACED',
  `address` text NOT NULL,
  `order_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`order_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (1,1,4097.00,'Placed','Bangalore','2026-05-07 06:04:14'),(2,2,2598.00,'Delivered','Mysore','2026-05-07 06:04:14'),(3,3,1699.00,'Shipped','Hyderabad','2026-05-07 06:04:14'),(4,4,1498.00,'Placed','Chennai','2026-05-07 06:04:14'),(5,5,2999.00,'Delivered','Delhi','2026-05-07 06:04:14'),(6,10,19889.00,'PLACED','\r\n            BCWD girl Hostel, Avalahalli, Bangalore-560036','2026-05-18 16:08:34'),(7,10,999.00,'PLACED','\r\n            bswd girl hostal,bangalore-560036','2026-05-18 16:42:27'),(8,10,3998.00,'PLACED','\r\n            bangalore-560036','2026-05-18 19:08:37'),(9,11,1198.00,'PLACED','\r\n            kr puram','2026-05-19 02:52:35'),(10,10,899.00,'PLACED','\r\n           BCWD Girl Hostel, Avalahalli,Bangalore-560036','2026-05-19 03:21:05');
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_variants`
--

DROP TABLE IF EXISTS `product_variants`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_variants` (
  `variant_id` int NOT NULL AUTO_INCREMENT,
  `product_id` int NOT NULL,
  `size` varchar(10) NOT NULL,
  `stock_quantity` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`variant_id`),
  KEY `product_id` (`product_id`),
  CONSTRAINT `product_variants_ibfk_1` FOREIGN KEY (`product_id`) REFERENCES `products` (`product_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=93 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_variants`
--

LOCK TABLES `product_variants` WRITE;
/*!40000 ALTER TABLE `product_variants` DISABLE KEYS */;
INSERT INTO `product_variants` VALUES (1,1,'S',20),(2,1,'M',25),(3,1,'L',15),(4,2,'30',10),(5,2,'32',12),(6,2,'34',8),(7,3,'M',20),(8,3,'L',18),(9,3,'XL',10),(10,4,'M',12),(11,4,'L',14),(12,5,'30',10),(13,5,'32',8),(14,6,'S',18),(15,6,'M',20),(16,6,'L',10),(17,7,'M',14),(18,7,'L',16),(19,8,'S',10),(20,8,'M',15),(21,9,'M',10),(22,9,'L',12),(23,10,'28',10),(24,10,'30',12),(25,11,'XS',20),(26,11,'S',15),(27,12,'XS',10),(28,12,'S',12),(29,13,'S',8),(30,13,'M',6),(31,14,'XS',7),(32,14,'S',9),(33,15,'7',10),(34,15,'8',8),(35,15,'9',12),(36,16,'8',14),(37,16,'9',10),(38,17,'5',10),(39,17,'6',8),(40,18,'7',15),(41,18,'8',12),(42,19,'Free Size',25),(43,20,'Free Size',30),(44,21,'Free Size',10),(45,22,'Free Size',18),(46,23,'Free Size',12),(47,1,'S',20),(48,1,'M',25),(49,1,'L',15),(50,2,'30',10),(51,2,'32',12),(52,2,'34',8),(53,3,'M',20),(54,3,'L',18),(55,3,'XL',10),(56,4,'M',12),(57,4,'L',14),(58,5,'30',10),(59,5,'32',8),(60,6,'S',18),(61,6,'M',20),(62,6,'L',10),(63,7,'M',14),(64,7,'L',16),(65,8,'S',10),(66,8,'M',15),(67,9,'M',10),(68,9,'L',12),(69,10,'28',10),(70,10,'30',12),(71,11,'XS',20),(72,11,'S',15),(73,12,'XS',10),(74,12,'S',12),(75,13,'S',8),(76,13,'M',6),(77,14,'XS',7),(78,14,'S',9),(79,15,'7',10),(80,15,'8',8),(81,15,'9',12),(82,16,'8',14),(83,16,'9',10),(84,17,'5',10),(85,17,'6',8),(86,18,'7',15),(87,18,'8',12),(88,19,'Free Size',25),(89,20,'Free Size',30),(90,21,'Free Size',10),(91,22,'Free Size',18),(92,23,'Free Size',12);
/*!40000 ALTER TABLE `product_variants` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `products` (
  `product_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(150) NOT NULL,
  `description` text,
  `price` decimal(10,2) NOT NULL,
  `category_id` int DEFAULT NULL,
  `image_url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`product_id`),
  KEY `category_id` (`category_id`),
  CONSTRAINT `products_ibfk_1` FOREIGN KEY (`category_id`) REFERENCES `categories` (`category_id`) ON DELETE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES (1,'Men Black T-Shirt','Cotton oversized black t-shirt',799.00,1,'men_black_tshirt.webp'),(2,'Men Blue Jeans','Slim fit blue denim jeans',1499.00,1,'men_blue_jeans.webp'),(3,'Men White Shirt','Formal white shirt for men',1299.00,1,'men_white_shirt.webp'),(4,'Men Green Hoodie','Casual winter hoodie',1799.00,1,'men_green_hoodie.webp'),(5,'Men Cargo Pants','Comfortable cargo pants',1599.00,1,'men_cargo_pants.webp'),(6,'Women Floral Dress','Floral printed casual dress',1899.00,2,'women_floral_dress.webp'),(7,'Women Hoodie','Winter oversized hoodie',1299.00,2,'women_hoodie.webp'),(8,'Women Pink Top','Casual stylish pink top',899.00,2,'women_pink_top.webp'),(9,'Women Blue Kurti','Traditional blue kurti',1499.00,2,'women_blue_kurti.webp'),(10,'Women Black Jeans','High waist black jeans',1699.00,2,'women_black_jeans.webp'),(11,'Kids Yellow T-Shirt','Soft cotton kids t-shirt',599.00,3,'kids_yellow_tshirt.jpg'),(12,'Kids Shorts','Comfortable cotton shorts',699.00,3,'kids_shorts.jpg'),(13,'Kids Jacket','Warm winter jacket',1199.00,3,'kids_jacket.webp'),(14,'Kids Track Pants','Soft track pants for kids',899.00,3,'kids_track_pants.webp'),(15,'Running Shoes','Lightweight running shoes',2499.00,4,'running_shoes.webp'),(16,'Sneakers','Casual white sneakers',2999.00,4,'sneakers.webp'),(17,'Women Heels','Stylish women heels',2199.00,4,'women_heels.webp'),(18,'Sandals','Daily wear sandals',999.00,4,'sandals.webp'),(19,'Leather Belt','Premium leather belt',899.00,5,'leather_belt.webp'),(20,'Cap','Stylish black cap',499.00,5,'cap.webp'),(21,'Watch','Luxury analog watch',3499.00,5,'watch.webp'),(22,'Sunglasses','UV protected sunglasses',1299.00,5,'sunglasses.webp'),(23,'Backpack','Travel backpack',1999.00,5,'backpack.webp');
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `email` varchar(150) NOT NULL,
  `password` varchar(255) NOT NULL,
  `phone` varchar(15) DEFAULT NULL,
  `address` text,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'Rahul Sharma','rahul@example.com','rahul123','9876543210','Bangalore','2026-05-07 05:59:13'),(2,'Priya Verma','priya@example.com','priya123','9876501234','Mysore','2026-05-07 05:59:13'),(3,'Arjun Reddy','arjun@example.com','arjun123','9988776655','Hyderabad','2026-05-07 05:59:13'),(4,'Sneha Kapoor','sneha@example.com','sneha123','9123456780','Chennai','2026-05-07 05:59:13'),(5,'Vikram Singh','vikram@example.com','vikram123','9012345678','Delhi','2026-05-07 05:59:13'),(6,'Gagana shree D','gaganashreedg@gmail.com','Gagana@123','08310900343','Doddakirugumbi(v),jhathavara(p),chikkaballapur(d)(t)','2026-05-13 17:40:03'),(8,'sanjay','sanjay@gmail.com','sanji','9353380847','Doddakirugumbi,  jhathavara, chikkaballapur','2026-05-13 18:18:06'),(9,'abc','sanjay123@gmail.com','sanji1','8945207345','bangalore','2026-05-14 00:58:42'),(10,'Gagana shree D','gaganashree.pt@gmail.com','123','1234566786','K.R puram','2026-05-18 07:56:30'),(11,'rakshitha','rakshitha@gmail.com','123','1234567890','K.R puram','2026-05-19 02:50:43');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-06-17 10:41:42

DELETE FROM users
WHERE email = 'gaganashreedg@gmail.com';

DELETE FROM users
WHERE email = 'gaganashree.pt@gmail.com';
