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
-- Table structure for table `contain`
--

DROP TABLE IF EXISTS `contain`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `contain` (
  `OrderID` int(10) unsigned NOT NULL,
  `ItemID` int(10) unsigned NOT NULL,
  `Quantity` tinyint(3) unsigned DEFAULT NULL,
  PRIMARY KEY (`OrderID`,`ItemID`),
  KEY `fk_Order_has_Menu_Item_Menu_Item1_idx` (`ItemID`),
  KEY `fk_Order_has_Menu_Item_Order1_idx` (`OrderID`),
  CONSTRAINT `fk_Order_has_Menu_Item_Menu_Item1` FOREIGN KEY (`ItemID`) REFERENCES `menu_item` (`ItemID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Order_has_Menu_Item_Order1` FOREIGN KEY (`OrderID`) REFERENCES `order` (`OrderID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contain`
--

LOCK TABLES `contain` WRITE;
/*!40000 ALTER TABLE `contain` DISABLE KEYS */;
INSERT INTO `contain` VALUES (1,2,2),(1,5,3),(1,7,15),(1,11,32),(1,12,1),(2,1,2),(3,1,1),(3,2,1),(3,7,2),(3,11,1),(5,7,2),(5,11,1),(6,2,2),(6,7,1),(6,11,1),(6,12,1),(7,5,2),(8,6,3),(10,7,2),(10,8,3),(11,9,3),(13,10,2),(14,11,3),(15,1,1),(15,12,2),(17,2,1),(17,3,3),(18,4,3),(19,5,3),(20,6,3),(20,7,2),(20,8,1),(21,9,4),(21,10,2),(23,11,3),(24,1,4),(24,2,2),(24,12,3),(25,3,3),(25,4,4),(28,5,1),(29,6,3),(30,7,4),(30,8,3),(32,9,4),(33,10,2),(33,11,4),(37,12,2),(38,1,3),(43,2,2),(43,3,2),(43,4,2),(44,5,1),(45,6,3),(49,7,3),(50,8,3),(50,9,2),(51,10,2),(51,11,4),(54,1,3),(54,2,3),(54,3,2),(54,12,2),(55,4,4),(55,5,1),(56,6,3),(58,7,3),(58,8,4),(58,9,2),(60,10,2),(60,11,4),(61,12,3),(62,1,2),(62,2,3),(64,3,2),(66,4,1),(71,5,3),(72,6,1),(74,7,3),(76,8,2),(76,9,3),(77,10,2),(79,11,1),(81,1,1),(81,12,3),(82,2,4),(83,3,4),(83,4,2),(84,5,4),(84,6,4),(84,7,4),(86,8,2),(87,9,1),(88,10,3),(89,11,3),(89,12,4),(90,1,4),(91,2,3),(91,3,4),(91,4,3),(94,5,4),(95,6,2),(95,7,3),(95,8,4),(98,9,2),(98,10,2),(99,1,3),(99,11,2),(99,12,4),(100,2,4),(101,1,1),(101,5,2),(102,5,2),(103,5,1),(104,1,1),(105,3,1),(106,1,1);
/*!40000 ALTER TABLE `contain` ENABLE KEYS */;
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
