CREATE DATABASE  IF NOT EXISTS `orion_mdd` /*!40100 DEFAULT CHARACTER SET utf8mb3 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `orion_mdd`;
-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
--
-- Host: localhost    Database: orion_mdd
-- ------------------------------------------------------
-- Server version	8.0.32

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
-- Table structure for table `comments`
--

DROP TABLE IF EXISTS `comments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comments` (
  `comment_id` int NOT NULL AUTO_INCREMENT,
  `post_id` int NOT NULL,
  `user_id` int NOT NULL,
  `content` longtext,
  `created_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`comment_id`),
  KEY `user_id_idx` (`user_id`) /*!80000 INVISIBLE */,
  KEY `fk_comments_posts1_idx` (`post_id`),
  CONSTRAINT `fk_comments_posts1` FOREIGN KEY (`post_id`) REFERENCES `posts` (`post_id`),
  CONSTRAINT `fk_comments_users1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comments`
--

LOCK TABLES `comments` WRITE;
/*!40000 ALTER TABLE `comments` DISABLE KEYS */;
INSERT INTO `comments` VALUES (1,1,1,'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam bibendum id libero et rutrum. Suspendisse id neque venenatis, mattis massa non, ornare eros.','2023-05-19 13:11:02'),(5,1,1,'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam bibendum id libero et rutrum. Suspendisse id neque venenatis, mattis massa non, ornare eros.','2023-05-19 13:11:02'),(7,1,1,'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam bibendum id libero et rutrum. Suspendisse id neque venenatis, mattis massa non, ornare eros.','2023-05-19 13:11:02'),(8,1,1,'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam bibendum id libero et rutrum. Suspendisse id neque venenatis, mattis massa non, ornare eros.','2023-05-19 13:11:02'),(9,1,2,'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam bibendum id libero et rutrum. Suspendisse id neque venenatis, mattis massa non, ornare eros.','2023-05-19 13:11:02'),(10,2,2,'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam bibendum id libero et rutrum. Suspendisse id neque venenatis, mattis massa non, ornare eros.','2023-05-19 13:11:02'),(11,6,2,'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam bibendum id libero et rutrum. Suspendisse id neque venenatis, mattis massa non, ornare eros.','2023-05-19 13:11:02'),(12,2,3,'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam bibendum id libero et rutrum. Suspendisse id neque venenatis, mattis massa non, ornare eros.','2023-05-19 13:11:02'),(13,3,1,'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam bibendum id libero et rutrum. Suspendisse id neque venenatis, mattis massa non, ornare eros.','2023-05-21 00:53:56'),(14,7,1,'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam bibendum id libero et rutrum. Suspendisse id neque venenatis, mattis massa non, ornare eros.','2023-05-21 00:54:48'),(15,7,1,'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam bibendum id libero et rutrum. Suspendisse id neque venenatis, mattis massa non, ornare eros.','2023-05-21 00:55:18'),(16,7,1,'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam bibendum id libero et rutrum. Suspendisse id neque venenatis, mattis massa non, ornare eros.','2023-05-21 00:57:51'),(17,7,1,'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam bibendum id libero et rutrum. Suspendisse id neque venenatis, mattis massa non, ornare eros.','2023-05-21 00:59:39'),(18,7,1,'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam bibendum id libero et rutrum. Suspendisse id neque venenatis, mattis massa non, ornare eros.','2023-05-21 01:00:15');
/*!40000 ALTER TABLE `comments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `posts`
--

DROP TABLE IF EXISTS `posts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `posts` (
  `post_id` int NOT NULL AUTO_INCREMENT,
  `topic_id` int NOT NULL,
  `user_id` int NOT NULL,
  `title` varchar(255) DEFAULT NULL,
  `content` longtext NOT NULL,
  `created_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`post_id`),
  KEY `topic_id_idx` (`topic_id`),
  KEY `user_id_idx` (`user_id`),
  CONSTRAINT `topic_id` FOREIGN KEY (`topic_id`) REFERENCES `topics` (`topic_id`),
  CONSTRAINT `user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `posts`
--

LOCK TABLES `posts` WRITE;
/*!40000 ALTER TABLE `posts` DISABLE KEYS */;
INSERT INTO `posts` VALUES (1,1,1,'Article 1','Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam bibendum id libero et rutrum. Suspendisse id neque venenatis, mattis massa non, ornare eros. Donec vitae interdum dui. Vivamus ante dolor, mattis vitae mauris sit amet, aliquam tempus duis.','2023-05-19 00:09:34'),(2,2,3,'Article 2','Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam bibendum id libero et rutrum. Suspendisse id neque venenatis, mattis massa non, ornare eros. Donec vitae interdum dui. Vivamus ante dolor, mattis vitae mauris sit amet, aliquam tempus duis.','2023-05-18 23:51:32'),(3,3,4,'Article 3','Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam bibendum id libero et rutrum. Suspendisse id neque venenatis, mattis massa non, ornare eros. Donec vitae interdum dui. Vivamus ante dolor, mattis vitae mauris sit amet, aliquam tempus duis.','2023-05-19 00:51:32'),(4,4,5,'Article 4','Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam bibendum id libero et rutrum. Suspendisse id neque venenatis, mattis massa non, ornare eros. Donec vitae interdum dui. Vivamus ante dolor, mattis vitae mauris sit amet, aliquam tempus duis.','2023-05-18 23:51:32'),(5,1,1,'Article 5','Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam bibendum id libero et rutrum. Suspendisse id neque venenatis, mattis massa non, ornare eros. Donec vitae interdum dui. Vivamus ante dolor, mattis vitae mauris sit amet, aliquam tempus duis.','2023-05-18 23:51:32'),(6,5,1,'Article 6','Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam bibendum id libero et rutrum. Suspendisse id neque venenatis, mattis massa non, ornare eros. Donec vitae interdum dui. Vivamus ante dolor, mattis vitae mauris sit amet, aliquam tempus duis.Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam bibendum id libero et rutrum. Suspendisse id neque venenatis, mattis massa non, ornare eros. Donec vitae interdum dui. Vivamus ante dolor, mattis vitae mauris sit amet, aliquam tempus duis.Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam bibendum id libero et rutrum. Suspendisse id neque venenatis, mattis massa non, ornare eros. Donec vitae interdum dui. Vivamus ante dolor, mattis vitae mauris sit amet, aliquam tempus duis.','2023-05-18 23:51:32'),(7,2,1,'Article 7','Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam bibendum id libero et rutrum. Suspendisse id neque venenatis, mattis massa non, ornare eros. Donec vitae interdum dui. Vivamus ante dolor, mattis vitae mauris sit amet, aliquam tempus duis.','2023-05-21 00:39:07');
/*!40000 ALTER TABLE `posts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subscribe`
--

DROP TABLE IF EXISTS `subscribe`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `subscribe` (
  `user_id` int DEFAULT NULL,
  `topic_id` int DEFAULT NULL,
  KEY `topic_id` (`topic_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `fk_subscriptions_topics1` FOREIGN KEY (`topic_id`) REFERENCES `topics` (`topic_id`),
  CONSTRAINT `fk_subscriptions_users1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subscribe`
--

LOCK TABLES `subscribe` WRITE;
/*!40000 ALTER TABLE `subscribe` DISABLE KEYS */;
INSERT INTO `subscribe` VALUES (5,2),(5,4),(5,8),(1,7),(1,5),(1,3),(1,6),(12,2),(12,6),(12,9),(12,8);
/*!40000 ALTER TABLE `subscribe` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `topics`
--

DROP TABLE IF EXISTS `topics`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `topics` (
  `topic_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`topic_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `topics`
--

LOCK TABLES `topics` WRITE;
/*!40000 ALTER TABLE `topics` DISABLE KEYS */;
INSERT INTO `topics` VALUES (1,'Python','Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam bibendum id libero et rutrum. Suspendisse id neque venenatis, mattis massa non, ornare eros. Donec vitae interdum dui. Vivamus ante dolor, mattis vitae mauris sit amet, aliquam tempus duis.'),(2,'Angular','Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam bibendum id libero et rutrum. Suspendisse id neque venenatis, mattis massa non, ornare eros. Donec vitae interdum dui. Vivamus ante dolor, mattis vitae mauris sit amet, aliquam tempus duis.'),(3,'JavaScript','Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam bibendum id libero et rutrum. Suspendisse id neque venenatis, mattis massa non, ornare eros. Donec vitae interdum dui. Vivamus ante dolor, mattis vitae mauris sit amet, aliquam tempus duis.'),(4,'Java','Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam bibendum id libero et rutrum. Suspendisse id neque venenatis, mattis massa non, ornare eros. Donec vitae interdum dui. Vivamus ante dolor, mattis vitae mauris sit amet, aliquam tempus duis.'),(5,'C#','Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam bibendum id libero et rutrum. Suspendisse id neque venenatis, mattis massa non, ornare eros. Donec vitae interdum dui. Vivamus ante dolor, mattis vitae mauris sit amet, aliquam tempus duis.'),(6,'Rubis On Rail','Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam bibendum id libero et rutrum. Suspendisse id neque venenatis, mattis massa non, ornare eros. Donec vitae interdum dui. Vivamus ante dolor, mattis vitae mauris sit amet, aliquam tempus duis.'),(7,'SQL','Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam bibendum id libero et rutrum. Suspendisse id neque venenatis, mattis massa non, ornare eros. Donec vitae interdum dui. Vivamus ante dolor, mattis vitae mauris sit amet, aliquam tempus duis.'),(8,'PHP','Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam bibendum id libero et rutrum. Suspendisse id neque venenatis, mattis massa non, ornare eros. Donec vitae interdum dui. Vivamus ante dolor, mattis vitae mauris sit amet, aliquam tempus duis.'),(9,'TypeScript','Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam bibendum id libero et rutrum. Suspendisse id neque venenatis, mattis massa non, ornare eros. Donec vitae interdum dui. Vivamus ante dolor, mattis vitae mauris sit amet, aliquam tempus duis.'),(10,'Kotlin','Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam bibendum id libero et rutrum. Suspendisse id neque venenatis, mattis massa non, ornare eros. Donec vitae interdum dui. Vivamus ante dolor, mattis vitae mauris sit amet, aliquam tempus duis.');
/*!40000 ALTER TABLE `topics` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  `created_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'mario@mail.com','$2a$10$BvMJ4BQWj1D/cWrjPxPVx.F7VchtiETZOu3DT2GEZuQ6RGsOGC7RG','Mario','2023-05-21 04:38:27','2023-05-21 04:38:27'),(2,'luigi@mail.com','$2a$10$vYgMi0S/gNVWcWYTf5Bnce2kFCzrQShgdwyBwlskXJO8cIMsVIWr.','Luigi','2023-05-21 04:38:27','2023-05-21 04:39:43'),(3,'yoshi@mail.com','$2a$10$vYgMi0S/gNVWcWYTf5Bnce2kFCzrQShgdwyBwlskXJO8cIMsVIWr.','Yoshi','2023-05-21 04:38:27','2023-05-21 04:38:27'),(4,'wario@mail.com','$2a$10$vYgMi0S/gNVWcWYTf5Bnce2kFCzrQShgdwyBwlskXJO8cIMsVIWr.','Wario','2023-05-21 04:38:27','2023-05-21 04:38:27'),(5,'peach@mail.com','$2a$10$MRHzkAYu/Y.Wa1j6KUzbdeZgQzWb1IMz3jplstnTab8CjCr8K5YcC','Peach','2023-05-21 04:38:27','2023-05-21 04:38:27'),(11,'bowser@mail.com','$2a$10$2pFrptPilUAcVHtdD0K4DuG/176Z.YMEvoteLQ1llUFwNk.RnH7ny','Bowser','2023-05-21 04:38:27','2023-05-21 04:38:27'),(12,'toad@mail.com','$2a$10$4RBZePCFrPvOAi5auKIEJ.kKQpIfu9WY.Y6CpTXs5evVs7Nt287xy','Toad','2023-05-21 04:38:27','2023-05-22 19:39:22');
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

-- Dump completed on 2023-05-22 22:59:04
