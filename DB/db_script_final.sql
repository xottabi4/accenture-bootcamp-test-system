
CREATE DATABASE  IF NOT EXISTS `onlinetestDB` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `onlinetestDB`;
-- MySQL dump 10.13  Distrib 5.5.49, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: onlinetestDB
-- ------------------------------------------------------
-- Server version	5.5.49-0+deb8u1

--Den
--SET FOREIGN_KEY_CHECKS = 0



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
-- Table structure for table `answer_text`
--

DROP TABLE IF EXISTS `answer_text`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `answer_text` (
  `at_id` int(8) NOT NULL AUTO_INCREMENT,
  `question_id` int(8) NOT NULL,
  `question_no` int(4) NOT NULL COMMENT 'q number.',
  `answer_text` text NOT NULL COMMENT 'answer to question in text form.',
  PRIMARY KEY (`at_id`),
  KEY `fk_question_id2_idx` (`question_id`),
  CONSTRAINT `fk_question_id2` FOREIGN KEY (`at_id`) REFERENCES `questions` (`q_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `answer_text`
--

LOCK TABLES `answer_text` WRITE;
/*!40000 ALTER TABLE `answer_text` DISABLE KEYS */;
INSERT INTO `answer_text` VALUES (7,1,1,'Sunlight reaches Earth\'s atmosphere and is scattered in all directions by all the gases and particles in the air. Blue light is scattered in all directions by the tiny molecules of air in Earth\'s atmosphere. Blue is scattered more than other colors because it travels as shorter, smaller waves. This is why we see a blue sky most of the time.'),(8,3,3,'Yes'),(9,5,2,'class is an extensible program-code-template for creating objects'),(10,6,3,'Food, medicine');
/*!40000 ALTER TABLE `answer_text` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `question_option`
--

DROP TABLE IF EXISTS `question_option`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `question_option` (
  `qop_id` int(8) NOT NULL AUTO_INCREMENT,
  `question_id` int(8) NOT NULL,
  `question_no` int(4) NOT NULL,
  `option_val` varchar(10) NOT NULL,
  `is_answer` tinyint(1) NOT NULL,
  PRIMARY KEY (`qop_id`),
  KEY `question_no_fk_idx` (`question_no`),
  KEY `fk_question_id1_idx` (`question_id`),
  CONSTRAINT `fk_question_id1` FOREIGN KEY (`question_id`) REFERENCES `questions` (`q_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `question_option`
--

LOCK TABLES `question_option` WRITE;
/*!40000 ALTER TABLE `question_option` DISABLE KEYS */;
INSERT INTO `question_option` VALUES (8,8,2,'Dog',1),(10,10,1,'4',1);
/*!40000 ALTER TABLE `question_option` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `questions`
--

DROP TABLE IF EXISTS `questions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `questions` (
  `q_id` int(8) NOT NULL AUTO_INCREMENT,
  `question_id` int(8) NOT NULL,
  `question_no` int(4) NOT NULL,
  `test_id` int(8) NOT NULL,
  `question_text` text NOT NULL COMMENT 'question text',
  PRIMARY KEY (`q_id`),
  KEY `testidfk123321_idx` (`test_id`),
  CONSTRAINT `testidfk123321` FOREIGN KEY (`test_id`) REFERENCES `test` (`test_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `questions`
--

LOCK TABLES `questions` WRITE;
/*!40000 ALTER TABLE `questions` DISABLE KEYS */;
INSERT INTO `questions` VALUES (7,1,1,1,'Why is the sky blue?'),(8,2,2,1,'What is your favourite pet?'),(9,3,3,1,'Do you even lift?'),(10,4,1,2,'2+2?'),(11,5,2,2,'Write the definition of class'),(12,6,3,2,'Where NaCl is used?');
/*!40000 ALTER TABLE `questions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `roles` (
  `role_id` int(8) NOT NULL AUTO_INCREMENT,
  `role_type` varchar(45) NOT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'grader'),(2,'recruiter'),(3,'applicant');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `test`
--

DROP TABLE IF EXISTS `test`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `test` (
  `test_id` int(8) NOT NULL AUTO_INCREMENT,
  `test_name` varchar(45) NOT NULL,
  `duration` int(11) NOT NULL,
  `is_alive` tinyint(1) NOT NULL,
  PRIMARY KEY (`test_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `test`
--

LOCK TABLES `test` WRITE;
/*!40000 ALTER TABLE `test` DISABLE KEYS */;
INSERT INTO `test` VALUES (1,'Language',3600000,1),(2,'Technical',3600000,1);
/*!40000 ALTER TABLE `test` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `user_id` int(8) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `surname` varchar(60) NOT NULL,
  `email` varchar(100) NOT NULL,
  `security_code` varchar(128) NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'A','A','a','a'),(2,'B','B','b','b'),(3,'C','D','c','c'),(4,'D','D','d','d');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_response`
--

DROP TABLE IF EXISTS `user_response`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_response` (
  `user_response_id` int(8) NOT NULL AUTO_INCREMENT,
  `question_id` int(8) NOT NULL,
  `user_id` int(8) NOT NULL,
  `test_id` int(8) NOT NULL,
  `option_val` varchar(100) NOT NULL,
  `answered_text` text NOT NULL,
  PRIMARY KEY (`user_response_id`),
  KEY `test_id_fk111_idx` (`test_id`),
  KEY `question_id_fk1_idx` (`question_id`),
  CONSTRAINT `question_id_fk1` FOREIGN KEY (`question_id`) REFERENCES `question_option` (`qop_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `question_id_fk2` FOREIGN KEY (`question_id`) REFERENCES `answer_text` (`at_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `test_id_fk111` FOREIGN KEY (`test_id`) REFERENCES `user_test` (`test_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_response`
--

LOCK TABLES `user_response` WRITE;
/*!40000 ALTER TABLE `user_response` DISABLE KEYS */;
INSERT INTO `user_response` VALUES (7,1,1,1,'text answer','Blue light is shattered in all directions, like it goes through the prism'),(8,8,1,1,'Cat','selected option'),(9,3,1,1,'text answer','Yes'),(10,10,4,2,'4','selected option'),(11,5,4,2,'text answer','Class is a template for creating, or instantiating, specific objects within'),(12,6,4,2,'text answer','It is used as food');
/*!40000 ALTER TABLE `user_response` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_roles`
--

DROP TABLE IF EXISTS `user_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_roles` (
  `user_id` int(8) NOT NULL AUTO_INCREMENT,
  `role_id` int(8) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `fkroleid_idx` (`role_id`),
  CONSTRAINT `fkroleid` FOREIGN KEY (`role_id`) REFERENCES `roles` (`role_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fkuserid` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_roles`
--

LOCK TABLES `user_roles` WRITE;
/*!40000 ALTER TABLE `user_roles` DISABLE KEYS */;
INSERT INTO `user_roles` VALUES (3,1),(2,2),(1,3),(4,3);
/*!40000 ALTER TABLE `user_roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_test`
--

DROP TABLE IF EXISTS `user_test`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_test` (
  `user_test_id` int(8) NOT NULL AUTO_INCREMENT,
  `user_id` int(8) NOT NULL,
  `test_id` int(8) NOT NULL,
  `date` date NOT NULL,
  PRIMARY KEY (`user_test_id`),
  KEY `user_id` (`user_id`),
  KEY `test_id_idx` (`test_id`),
  CONSTRAINT `user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_test`
--

LOCK TABLES `user_test` WRITE;
/*!40000 ALTER TABLE `user_test` DISABLE KEYS */;
INSERT INTO `user_test` VALUES (1,1,1,'2016-01-01'),(7,1,1,'2016-01-01'),(8,1,1,'2016-01-01'),(9,4,2,'2016-01-05'),(10,4,2,'2016-01-05'),(11,4,2,'2016-01-05');
/*!40000 ALTER TABLE `user_test` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-07-08  9:46:55
