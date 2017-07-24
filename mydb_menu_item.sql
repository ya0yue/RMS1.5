-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: mydb
-- ------------------------------------------------------
-- Server version	5.7.15-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `menu_item`
--

DROP TABLE IF EXISTS `menu_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `menu_item` (
  `ItemID` int(10) unsigned NOT NULL,
  `ItemName` varchar(45) DEFAULT NULL,
  `ItemQuantity` tinyint(3) unsigned DEFAULT NULL,
  `ItemPrice` decimal(32,2) DEFAULT NULL,
  `ItemCode` varchar(20) DEFAULT NULL,
  `ItemInfo` varchar(45) DEFAULT NULL,
  `CategoryID` int(10) unsigned NOT NULL,
  PRIMARY KEY (`ItemID`),
  KEY `fk_Menu_Item_Category_idx` (`CategoryID`),
  CONSTRAINT `fk_Menu_Item_Category` FOREIGN KEY (`CategoryID`) REFERENCES `category` (`CategoryID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `menu_item`
--

LOCK TABLES `menu_item` WRITE;
/*!40000 ALTER TABLE `menu_item` DISABLE KEYS */;
INSERT INTO `menu_item` VALUES (1,'western omelette',1,4.50,'WO','',2),(2,'mushroom omelette',1,4.25,'MO','',2),(3,'butter toast',1,1.50,'BT','nothing',3),(4,'peanut butter',1,1.50,'PB','',3),(5,'shrimp w. potato',1,5.45,'SWP','',4),(6,'fresh fruit salad',1,4.00,'FFS','',4),(7,'ham, bacon ',1,4.00,'HBHD','',1),(8,'instant noodle soup',1,4.00,'SBINS','',1),(9,'kowloon cafe soup',1,3.50,'KCS','',5),(10,'borsch soup',1,3.50,'BS','',5),(11,'garlic bread',2,2.50,'GB','',6),(12,'Pizza Bread',2,3.50,'PB','',6),(13,'garlic bread',2,2.50,'GB','',6);
/*!40000 ALTER TABLE `menu_item` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-12-01 15:23:06
