-- MySQL dump 10.13  Distrib 8.0.30, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: workorderdb
-- ------------------------------------------------------
-- Server version	8.0.30

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
-- Table structure for table `incident`
--

DROP TABLE IF EXISTS `incident`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `incident` (
  `User` varchar(45) NOT NULL,
  `Phone` varchar(45) NOT NULL,
  `CC` varchar(45) NOT NULL,
  `Department` varchar(45) NOT NULL,
  `Building` varchar(45) NOT NULL,
  `Subject` varchar(45) NOT NULL,
  `id` int NOT NULL AUTO_INCREMENT,
  `setdate` varchar(45) NOT NULL,
  `user_id` bigint DEFAULT NULL,
  `tech_notes` varchar(45) DEFAULT 'IN-PROGRESS',
  PRIMARY KEY (`id`),
  KEY `user_id_idx` (`user_id`),
  CONSTRAINT `user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=110 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `incident`
--

LOCK TABLES `incident` WRITE;
/*!40000 ALTER TABLE `incident` DISABLE KEYS */;
INSERT INTO `incident` VALUES ('John','4127823672','admin','Science','C','Password',78,'10/10',9,'some notes'),('Karen','4123456733','admin','Math','D','Software',79,'10/15',10,''),('Luke','4125667248','admin','Science','A','Email',80,'10/15',9,''),('Jack','7245667865','admin','Psychology','C','Computer',81,'10/16',9,''),('Kelly','7243135481','admin','Buisness','B','Hardware',82,'10/16',9,''),('Jin','7249334383','admin','Computer Science','B','Email',86,'10/6',9,''),('James','4125762453','admin','Buisness','A','Computer',87,'10/6',9,''),('John','4127823672','admin','Science','A','Password',89,'10/10',9,''),('Karen','4123456733','admin','Math','D','Software',90,'10/15',9,''),('Luke','4125667248','admin','Science','A','Email',91,'10/15',10,''),('Jack','7245667865','admin','Psychology','C','Computer',92,'10/16',9,''),('Kelly','7243135481','admin','Buisness','B','Hardware',93,'10/16',10,''),('Linda','4129007000','admin','Computer Science','D','Computer',94,'10/17',9,''),('Jeff','4123725432','admin','Math','A','Printer',95,'10/19',9,''),('Bob','4127354352','admin','Science','C','Software',96,'10/19',9,''),('Jin','7249334383','admin','Computer Science','B','Email',97,'10/6',9,''),('James','4125762453','admin','Buisness','A','Computer',98,'10/6',9,''),('Evan','4128997000','admin','Psychology','C','Printer',99,'10/8',9,''),('John','4127823672','admin','Science','A','Password',100,'10/10',9,''),('Karen','4123456733','admin','Math','D','Software',101,'10/15',9,''),('Luke','4125667248','admin','Science','A','Email',102,'10/15',9,''),('Jack','7245667865','admin','Psychology','C','Computer',103,'10/16',9,''),('Kelly','7243135481','admin','Buisness','B','Hardware',104,'10/16',9,''),('Linda','4129007000','admin','Computer Science','D','Computer',105,'10/17',NULL,''),('Jeff','4123725432','admin','Math','A','Printer',106,'10/19',NULL,''),('Bob','4127354352','admin','Science','C','Software',107,'10/19',NULL,''),('Jack','4128884657','admin','Computer Science','C','Email',108,'11/14/2022',NULL,''),('James','4129566170','Admin','Computer Science','D','Report',109,'11/14',NULL,'');
/*!40000 ALTER TABLE `incident` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(45) NOT NULL,
  `first_name` varchar(20) NOT NULL,
  `last_name` varchar(20) NOT NULL,
  `password` varchar(64) NOT NULL,
  `role` varchar(30) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'jnp1010@sru.edu','James','Pham','$2a$10$.rk13mhm3hpwMgvBuU1j2..6motvposWUzIsa0gxkXMcs9iPsrGKq','ADMIN'),(8,'abc1111@sru.edu','John','Doe','$2a$10$uYEK2tXJNgVfu0nvH9HgUuk6pXVm4s6jMTVuX.BkwKglXkEShZx9C','USER'),(9,'tech@sru.edu','Evan','White','$2a$10$0XS3jepF1Dr6LX7eMrS/..IuBoIPEYTZ4BLtA19fSfhy1nBtE8Fn.','TECHASSIST'),(10,'James@sru.edu','James','Pham','$2a$10$O6xTVyD3QFIR2s2ygLFpL.YflKTw88miZ5YGdFXmI3pHBwj6r3HIi','TECHASSIST'),(11,'user@sru.edu','Bruce','Wayne','$2a$10$ZUOPF3jU.9kIVRtQkANDbeGbNb3tNAFSJkWih9/J.trlTO4UGeK7u','USER'),(12,'admin@sru.edu','admin','Pham','$2a$10$Xg5F7EiKHU99v.zih6CP8u1Jlw1iF.Jmd/E584k9Am3xP20bHyEbG','ADMIN');
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

-- Dump completed on 2022-11-15 13:41:42
