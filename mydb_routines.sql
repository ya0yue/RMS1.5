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
-- Temporary view structure for view `orderinfo`
--

DROP TABLE IF EXISTS `orderinfo`;
/*!50001 DROP VIEW IF EXISTS `orderinfo`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `orderinfo` AS SELECT 
 1 AS `OrderID`,
 1 AS `ItemID`,
 1 AS `OrderStatus`,
 1 AS `OrderDate`,
 1 AS `OrderTime`,
 1 AS `OrderInfo`,
 1 AS `StaffID`,
 1 AS `ItemName`,
 1 AS `ItemQuantity`,
 1 AS `ItemPrice`,
 1 AS `ItemCode`,
 1 AS `ItemInfo`,
 1 AS `CategoryID`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `staff_authority`
--

DROP TABLE IF EXISTS `staff_authority`;
/*!50001 DROP VIEW IF EXISTS `staff_authority`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `staff_authority` AS SELECT 
 1 AS `StaffID`,
 1 AS `StaffName`,
 1 AS `Age`,
 1 AS `ContactNumber`,
 1 AS `Role`,
 1 AS `AccountNo`,
 1 AS `Password`,
 1 AS `ManagerID`,
 1 AS `AuthorityID`,
 1 AS `AuthorityName`,
 1 AS `AuthorityInfo`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `checkoutinfo`
--

DROP TABLE IF EXISTS `checkoutinfo`;
/*!50001 DROP VIEW IF EXISTS `checkoutinfo`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `checkoutinfo` AS SELECT 
 1 AS `OrderID`,
 1 AS `ItemID`,
 1 AS `Quantity`,
 1 AS `CheckOutID`,
 1 AS `Amount`,
 1 AS `Payment`,
 1 AS `PaymentType`,
 1 AS `ChangeMoney`,
 1 AS `Discount`,
 1 AS `CheckoutDate`,
 1 AS `CheckoutTime`,
 1 AS `CheckoutInfo`,
 1 AS `StaffID`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `table_order`
--

DROP TABLE IF EXISTS `table_order`;
/*!50001 DROP VIEW IF EXISTS `table_order`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `table_order` AS SELECT 
 1 AS `tableid`,
 1 AS `tablestatus`,
 1 AS `tableinfo`,
 1 AS `orderid`,
 1 AS `orderstatus`,
 1 AS `orderdate`,
 1 AS `ordertime`,
 1 AS `orderinfo`,
 1 AS `staffid`*/;
SET character_set_client = @saved_cs_client;

--
-- Final view structure for view `orderinfo`
--

/*!50001 DROP VIEW IF EXISTS `orderinfo`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `orderinfo` AS select `a`.`OrderID` AS `OrderID`,`a`.`ItemID` AS `ItemID`,`b`.`OrderStatus` AS `OrderStatus`,`b`.`OrderDate` AS `OrderDate`,`b`.`OrderTime` AS `OrderTime`,`b`.`OrderInfo` AS `OrderInfo`,`b`.`StaffID` AS `StaffID`,`c`.`ItemName` AS `ItemName`,`c`.`ItemQuantity` AS `ItemQuantity`,`c`.`ItemPrice` AS `ItemPrice`,`c`.`ItemCode` AS `ItemCode`,`c`.`ItemInfo` AS `ItemInfo`,`c`.`CategoryID` AS `CategoryID` from ((`contain` `a` join `order` `b`) join `menu_item` `c`) where ((`a`.`ItemID` = `c`.`ItemID`) and (`a`.`OrderID` = `b`.`OrderID`)) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `staff_authority`
--

/*!50001 DROP VIEW IF EXISTS `staff_authority`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `staff_authority` AS select `a`.`StaffID` AS `StaffID`,`a`.`StaffName` AS `StaffName`,`a`.`Age` AS `Age`,`a`.`ContactNumber` AS `ContactNumber`,`a`.`Role` AS `Role`,`a`.`AccountNo` AS `AccountNo`,`a`.`Password` AS `Password`,`a`.`ManagerID` AS `ManagerID`,`c`.`AuthorityID` AS `AuthorityID`,`c`.`AuthorityName` AS `AuthorityName`,`c`.`AuthorityInfo` AS `AuthorityInfo` from ((`staff` `a` join `own` `b`) join `authority` `c`) where ((`a`.`StaffID` = `b`.`StaffID`) and (`b`.`AuthorityID` = `c`.`AuthorityID`)) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `checkoutinfo`
--

/*!50001 DROP VIEW IF EXISTS `checkoutinfo`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `checkoutinfo` AS select `a`.`OrderID` AS `OrderID`,`a`.`ItemID` AS `ItemID`,`a`.`Quantity` AS `Quantity`,`b`.`CheckOutID` AS `CheckOutID`,`b`.`Amount` AS `Amount`,`b`.`Payment` AS `Payment`,`b`.`PaymentType` AS `PaymentType`,`b`.`ChangeMoney` AS `ChangeMoney`,`b`.`Discount` AS `Discount`,`b`.`CheckoutDate` AS `CheckoutDate`,`b`.`CheckoutTime` AS `CheckoutTime`,`b`.`CheckoutInfo` AS `CheckoutInfo`,`b`.`StaffID` AS `StaffID` from (`contain` `a` join `checkout` `b`) where (`a`.`OrderID` = `b`.`OrderID`) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `table_order`
--

/*!50001 DROP VIEW IF EXISTS `table_order`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `table_order` AS select `a`.`TableID` AS `tableid`,`a`.`TableStatus` AS `tablestatus`,`a`.`TableInfo` AS `tableinfo`,`c`.`OrderID` AS `orderid`,`c`.`OrderStatus` AS `orderstatus`,`c`.`OrderDate` AS `orderdate`,`c`.`OrderTime` AS `ordertime`,`c`.`OrderInfo` AS `orderinfo`,`c`.`StaffID` AS `staffid` from ((`table` `a` join `belong` `b`) join `order` `c`) where ((`a`.`TableID` = `b`.`TableNo`) and (`b`.`OrderID` = `c`.`OrderID`)) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-12-01 15:23:07
