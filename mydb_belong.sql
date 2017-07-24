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
-- Table structure for table `belong`
--

DROP TABLE IF EXISTS `belong`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `belong` (
  `OrderID` int(10) unsigned NOT NULL,
  `TableNo` int(10) unsigned NOT NULL,
  PRIMARY KEY (`OrderID`,`TableNo`),
  KEY `fk_Order_has_Table_Table1_idx` (`TableNo`),
  KEY `fk_Order_has_Table_Order1_idx` (`OrderID`),
  CONSTRAINT `fk_Order_has_Table_Order1` FOREIGN KEY (`OrderID`) REFERENCES `order` (`OrderID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Order_has_Table_Table1` FOREIGN KEY (`TableNo`) REFERENCES `table` (`TableID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `belong`
--

LOCK TABLES `belong` WRITE;
/*!40000 ALTER TABLE `belong` DISABLE KEYS */;
INSERT INTO `belong` VALUES (1,1),(21,1),(61,1),(83,1),(89,1),(2,2),(18,2),(24,2),(87,2),(88,2),(90,2),(101,2),(4,3),(25,3),(50,3),(58,3),(79,3),(105,3),(3,4),(13,4),(14,4),(33,4),(45,4),(55,4),(84,4),(11,5),(17,5),(23,5),(29,5),(60,5),(71,5),(104,5),(10,6),(20,6),(30,6),(56,6),(62,6),(77,6),(82,6),(95,6),(98,6),(102,6),(6,7),(37,7),(51,7),(94,7),(99,7),(100,7),(19,8),(43,8),(49,8),(72,8),(74,8),(76,8),(106,8),(8,9),(28,9),(64,9),(86,9),(91,9),(103,9),(5,10),(9,10),(15,10),(32,10),(38,10),(44,10),(54,10),(66,10),(81,10);
/*!40000 ALTER TABLE `belong` ENABLE KEYS */;
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
