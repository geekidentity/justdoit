CREATE DATABASE  IF NOT EXISTS `justdoit` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `justdoit`;
-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: 211.87.147.139    Database: justdoit
-- ------------------------------------------------------
-- Server version	5.7.10

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
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(15) NOT NULL,
  `password` varchar(32) NOT NULL,
  `telephone` varchar(14) DEFAULT NULL,
  `employeeId` int(11) NOT NULL,
  `address` varchar(50) DEFAULT NULL,
  `lng` float DEFAULT NULL,
  `lat` float DEFAULT NULL,
  `lastLogin` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `gmtCreate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `gmtModified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `createById` int(11) NOT NULL,
  `lastModifiedById` int(11) NOT NULL,
  `birthday` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `cust_employee_idx` (`employeeId`),
  CONSTRAINT `cust_employee` FOREIGN KEY (`employeeId`) REFERENCES `employee` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (3,'侯法超','111','',0,NULL,NULL,NULL,'2016-05-01 07:18:00','2016-05-01 07:25:49','2016-05-01 07:25:49',0,0,NULL),(4,'Leo','justdoit','18363997802',0,NULL,NULL,NULL,'2016-05-02 06:24:05','2016-05-02 06:24:08','2016-05-02 06:24:08',0,0,'1994-05-02');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `department`
--

DROP TABLE IF EXISTS `department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `department` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `no` varchar(10) CHARACTER SET latin1 NOT NULL,
  `name` varchar(40) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `department`
--

LOCK TABLES `department` WRITE;
/*!40000 ALTER TABLE `department` DISABLE KEYS */;
INSERT INTO `department` VALUES (0,'002','FBI'),(2,'3','department1'),(10,'003','hello');
/*!40000 ALTER TABLE `department` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `employee` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `password` varchar(32) CHARACTER SET latin1 NOT NULL,
  `department` int(11) NOT NULL,
  `position` int(11) NOT NULL,
  `status` tinyint(4) NOT NULL DEFAULT '1',
  `lastLogin` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `gmtCreate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `gmtModified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `createById` int(11) NOT NULL DEFAULT '0',
  `lastModifiedById` int(11) NOT NULL DEFAULT '0',
  `birthday` date DEFAULT NULL,
  `eno` varchar(10) NOT NULL,
  `lastPosition` varchar(50) DEFAULT NULL,
  `lng` float DEFAULT NULL,
  `lat` float DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `eno_UNIQUE` (`eno`),
  KEY `employee_createById_idx` (`password`),
  KEY `employee_createById_idx1` (`createById`),
  KEY `employee_lastModifiedById_idx` (`lastModifiedById`),
  KEY `employee_department_idx` (`department`),
  KEY `employee_position_idx` (`position`),
  CONSTRAINT `createbyid` FOREIGN KEY (`createById`) REFERENCES `employee` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `department_id` FOREIGN KEY (`department`) REFERENCES `department` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `lastModifiedById` FOREIGN KEY (`lastModifiedById`) REFERENCES `employee` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `position_id` FOREIGN KEY (`position`) REFERENCES `position` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES (0,'Admin','justdoit',0,0,1,'2016-05-03 05:41:56','2016-03-31 05:19:32','2016-03-31 05:19:32',0,0,NULL,'001','',116.415,39.9106),(17,'fzq','fzq',0,0,1,'2016-05-03 06:03:55','2016-05-01 02:40:00','2016-05-01 02:40:00',0,0,NULL,'110','山东省青岛市崂山区科大支路',120.48,36.1202),(18,'李傲','liao',0,0,1,'2016-05-02 14:52:40','2016-05-01 06:29:31','2016-05-01 06:29:31',0,0,NULL,'005','山东省菏泽市牡丹区永昌路',115.492,35.2497),(19,'陈金','chenjin',0,0,1,'2016-05-03 03:26:51','2016-05-01 06:34:47','2016-05-01 06:34:47',0,0,NULL,'008','山东省青岛市崂山区科大支路57',120.48,36.1184),(20,'侯法超','geek',0,0,1,'2016-05-03 06:04:44','2016-05-01 06:48:44','2016-05-01 07:52:36',0,0,NULL,'007','山东省青岛市崂山区科大支路',120.477,36.1211);
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `legwork`
--

DROP TABLE IF EXISTS `legwork`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `legwork` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `employee` int(11) NOT NULL,
  `reason` text NOT NULL,
  `reachTime` timestamp NULL DEFAULT NULL,
  `leaveTime` timestamp NULL DEFAULT NULL,
  `position` varchar(100) NOT NULL,
  `lng` float NOT NULL,
  `lat` float NOT NULL,
  `ip` varchar(15) CHARACTER SET latin1 NOT NULL,
  `applyTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `status` tinyint(4) NOT NULL DEFAULT '0',
  `reachLng` float DEFAULT NULL,
  `reachLat` float DEFAULT NULL,
  `backLng` float DEFAULT NULL,
  `backLat` float DEFAULT NULL,
  `title` varchar(20) CHARACTER SET latin1 NOT NULL,
  PRIMARY KEY (`id`),
  KEY `employeeId_idx` (`employee`),
  CONSTRAINT `legwork_employee` FOREIGN KEY (`employee`) REFERENCES `employee` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `legwork`
--

LOCK TABLES `legwork` WRITE;
/*!40000 ALTER TABLE `legwork` DISABLE KEYS */;
INSERT INTO `legwork` VALUES (9,19,'ABC',NULL,NULL,'山东省青岛市市南区沂水路4a',0,0,'123.103.8.82','2016-05-02 02:34:26',-1,NULL,NULL,NULL,NULL,'ABC'),(10,19,'DDF',NULL,NULL,'山东省青岛市市南区沂水路4a',0,0,'223.81.66.35','2016-05-02 02:37:14',-1,NULL,NULL,NULL,NULL,'DDF'),(11,17,'dfsfsd',NULL,NULL,'山东省青岛市市南区沂水路4a',0,0,'112.226.195.83','2016-05-02 03:36:23',-1,NULL,NULL,NULL,NULL,'hehe'),(12,17,'kjhkjhkj',NULL,NULL,'山东省青岛市市南区沂水路4a',0,0,'112.226.195.83','2016-05-02 03:40:00',-1,NULL,NULL,NULL,NULL,'uuoihn'),(13,20,'dfafa',NULL,NULL,'山东省青岛市市南区沂水路4a',120.475,36.1245,'0:0:0:0:0:0:0:1','2016-05-02 04:33:46',0,NULL,NULL,NULL,NULL,'hehe'),(14,20,'g',NULL,NULL,'山东省青岛市市南区沂水路4a',0,0,'10.9.3.150','2016-05-02 04:55:41',0,NULL,NULL,NULL,NULL,'g'),(15,20,'cjfjfnfjfjx',NULL,NULL,'山东省青岛市市南区沂水路4a',0,0,'10.9.3.150','2016-05-02 05:26:22',-1,NULL,NULL,NULL,NULL,'fjf'),(16,20,'cjfjfnfjfjx',NULL,NULL,'山东省青岛市市南区沂水路4a',0,0,'10.9.3.150','2016-05-02 05:26:22',0,NULL,NULL,NULL,NULL,'fjf'),(17,18,'cieneociwgd',NULL,NULL,'山东省青岛市市南区沂水路4a',0,0,'106.39.189.202','2016-05-02 05:52:40',0,NULL,NULL,NULL,NULL,'jdosnejv'),(18,18,'123',NULL,NULL,'山东省青岛市市南区沂水路4a',0,0,'106.39.189.230','2016-05-02 05:56:52',0,NULL,NULL,NULL,NULL,'123'),(19,18,'1',NULL,NULL,'山东省青岛市市南区沂水路4a',0,0,'106.39.189.231','2016-05-02 05:57:54',0,NULL,NULL,NULL,NULL,'1'),(20,18,'2',NULL,NULL,'山东省青岛市市南区沂水路4a',0,0,'106.39.189.205','2016-05-02 06:06:46',0,NULL,NULL,NULL,NULL,'2'),(21,19,'111',NULL,NULL,'',0,0,'123.103.8.96','2016-05-03 02:22:41',-1,NULL,NULL,NULL,NULL,'123'),(22,19,'11111',NULL,NULL,'',0,0,'123.103.8.96','2016-05-03 02:22:58',-1,NULL,NULL,NULL,NULL,'111'),(23,17,'',NULL,NULL,'山东省青岛市崂山区科大支路',120.478,36.1207,'112.224.65.137','2016-05-03 02:38:43',-1,NULL,NULL,NULL,NULL,''),(24,17,'gdfgfd',NULL,NULL,'山东省青岛市崂山区科大支路',120.478,36.1206,'112.224.65.137','2016-05-03 02:39:02',-1,NULL,NULL,NULL,NULL,'fgdgkdfkldfgdf'),(25,19,'ABC',NULL,NULL,'山东省青岛市崂山区科大支路',120.485,36.1205,'112.224.65.101','2016-05-03 02:42:00',-1,NULL,NULL,NULL,NULL,'ABC'),(26,19,'1',NULL,NULL,'山东省青岛市崂山区云岭路',120.485,36.1185,'112.224.65.101','2016-05-03 02:46:44',0,NULL,NULL,NULL,NULL,'1'),(27,19,'2',NULL,NULL,'山东省青岛市崂山区云岭路',120.485,36.1185,'112.224.65.101','2016-05-03 02:47:04',0,NULL,NULL,NULL,NULL,'2'),(28,19,'3',NULL,NULL,'山东省青岛市崂山区云岭路',120.485,36.1185,'112.224.65.101','2016-05-03 02:47:12',0,NULL,NULL,NULL,NULL,'3'),(29,19,'4',NULL,NULL,'山东省青岛市崂山区科大支路',120.485,36.1205,'112.224.65.101','2016-05-03 02:47:20',0,NULL,NULL,NULL,NULL,'4'),(30,19,'5',NULL,NULL,'山东省青岛市崂山区科大支路',120.485,36.1205,'112.224.65.101','2016-05-03 02:47:28',0,NULL,NULL,NULL,NULL,'5'),(31,19,'6',NULL,NULL,'山东省青岛市崂山区科大支路',120.485,36.1205,'112.224.65.101','2016-05-03 02:47:39',0,NULL,NULL,NULL,NULL,'6'),(32,19,'7',NULL,NULL,'山东省青岛市崂山区科大支路',120.485,36.1205,'112.224.65.101','2016-05-03 02:47:47',-1,NULL,NULL,NULL,NULL,'7'),(33,19,'8',NULL,NULL,'山东省青岛市崂山区科大支路',120.485,36.1205,'112.224.65.101','2016-05-03 02:47:56',-1,NULL,NULL,NULL,NULL,'8'),(34,19,'9',NULL,NULL,'山东省青岛市崂山区科大支路',120.485,36.1205,'112.224.65.101','2016-05-03 02:48:04',-1,NULL,NULL,NULL,NULL,'9'),(35,19,'10',NULL,NULL,'山东省青岛市崂山区科大支路',120.485,36.1205,'112.224.65.101','2016-05-03 02:48:18',-1,NULL,NULL,NULL,NULL,'10'),(36,19,'8',NULL,NULL,'山东省青岛市崂山区科大支路57',120.48,36.1184,'112.224.65.101','2016-05-03 03:10:00',0,NULL,NULL,NULL,NULL,'8'),(37,19,'7',NULL,NULL,'山东省青岛市崂山区科大支路',120.48,36.1203,'112.224.65.101','2016-05-03 03:12:50',0,NULL,NULL,NULL,NULL,'7'),(38,17,'	  ',NULL,NULL,'山东省青岛市崂山区科大支路',120.478,36.1208,'112.224.65.137','2016-05-03 03:25:11',-1,NULL,NULL,NULL,NULL,''),(39,19,'10',NULL,NULL,'山东省青岛市崂山区银川东路',120.478,36.1158,'112.224.65.101','2016-05-03 03:27:12',-1,NULL,NULL,NULL,NULL,'10');
/*!40000 ALTER TABLE `legwork` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `position`
--

DROP TABLE IF EXISTS `position`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `position` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(40) NOT NULL,
  `department` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `department_id_idx` (`department`),
  CONSTRAINT `position_department_id` FOREIGN KEY (`department`) REFERENCES `department` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `position`
--

LOCK TABLES `position` WRITE;
/*!40000 ALTER TABLE `position` DISABLE KEYS */;
INSERT INTO `position` VALUES (0,'探员',0),(5,'记者',0);
/*!40000 ALTER TABLE `position` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reach`
--

DROP TABLE IF EXISTS `reach`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reach` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `employee` int(11) NOT NULL,
  `reachTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `position` varchar(50) NOT NULL,
  `lng` float NOT NULL,
  `lat` float NOT NULL,
  `ip` varchar(15) NOT NULL,
  `type` tinyint(4) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `employeeId_idx` (`employee`),
  CONSTRAINT `employeeId` FOREIGN KEY (`employee`) REFERENCES `employee` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=162 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reach`
--

LOCK TABLES `reach` WRITE;
/*!40000 ALTER TABLE `reach` DISABLE KEYS */;
INSERT INTO `reach` VALUES (103,0,'2016-05-01 12:38:10','',138.253,36.2048,'112.226.195.83',1),(104,0,'2016-05-01 12:38:12','',138.253,36.2048,'112.226.195.83',1),(105,17,'2016-05-01 12:40:13','',138.253,36.2048,'112.226.195.83',1),(106,17,'2016-05-01 12:44:42','',0,0,'112.226.195.83',1),(107,17,'2016-05-01 12:45:42','',0,0,'112.226.195.83',0),(108,17,'2016-05-01 12:45:47','',0,0,'112.226.195.83',1),(109,17,'2016-05-01 13:42:26','',103.868,1.35538,'112.226.195.83',1),(110,17,'2016-05-01 15:00:27','山东省青岛市市南区沂水路4a',120.334,36.0711,'112.226.195.83',1),(111,20,'2016-05-01 15:07:28','山东省青岛市崂山区科大支路',120.477,36.1217,'10.5.1.144',1),(112,20,'2016-05-01 15:11:50','山东省青岛市崂山区科大支路',120.477,36.1211,'10.5.1.144',0),(113,19,'2016-05-01 15:15:35','山东省青岛市崂山区同安路',120.475,36.124,'123.103.8.82',1),(114,19,'2016-05-01 15:15:41','山东省青岛市崂山区同安路',120.475,36.124,'123.103.8.82',0),(115,19,'2016-05-01 15:20:16','山东省青岛市崂山区同安路',120.475,36.1245,'223.81.111.199',1),(116,19,'2016-05-01 15:20:18','山东省青岛市崂山区同安路',120.475,36.1245,'223.81.111.199',0),(117,18,'2016-05-02 02:17:44','山东省菏泽市单县向阳西路',116.087,34.7982,'111.37.25.165',1),(118,18,'2016-05-02 04:03:01','山东省菏泽市单县文化路',116.099,34.7748,'112.224.21.107',1),(119,18,'2016-05-02 04:04:09','山东省菏泽市单县君子路',116.102,34.7734,'112.224.21.107',0),(120,18,'2016-05-02 04:04:14','山东省菏泽市单县君子路',116.102,34.7734,'112.224.21.107',1),(121,17,'2016-05-02 04:46:11','上海市静安区成都北路500号',121.474,31.2304,'112.226.195.83',1),(122,18,'2016-05-02 05:15:30','山东省菏泽市单县湖西南路',116.087,34.7811,'112.224.21.107',1),(123,18,'2016-05-02 05:15:35','山东省菏泽市单县湖西南路',116.087,34.7811,'112.224.21.107',0),(124,18,'2016-05-02 05:16:10','山东省菏泽市单县湖西南路',116.087,34.7811,'112.224.21.107',1),(125,18,'2016-05-02 05:17:15','山东省菏泽市单县文化路',116.093,34.7801,'112.224.21.107',1),(126,18,'2016-05-02 05:17:16','山东省菏泽市单县文化路',116.093,34.7801,'112.224.21.107',1),(127,18,'2016-05-02 05:29:58','山东省菏泽市单县文化路',116.093,34.7801,'112.224.21.107',1),(128,18,'2016-05-02 05:29:59','山东省菏泽市单县文化路',116.093,34.7801,'112.224.21.107',1),(129,18,'2016-05-02 05:47:29','山东省菏泽市单县向阳路东段',116.107,34.7916,'112.224.21.107',1),(130,18,'2016-05-02 05:50:22','山东省菏泽市单县向阳路',116.1,34.7924,'106.39.189.201',1),(131,18,'2016-05-02 05:50:22','山东省菏泽市单县向阳路',116.1,34.7924,'106.39.189.232',1),(132,18,'2016-05-02 05:50:22','山东省菏泽市单县向阳路',116.1,34.7924,'106.39.189.207',1),(134,18,'2016-05-02 05:50:22','山东省菏泽市单县向阳路',116.1,34.7924,'106.39.189.234',0),(135,18,'2016-05-02 05:50:30','山东省菏泽市单县向阳路',116.1,34.7924,'106.39.189.233',1),(136,18,'2016-05-02 05:50:31','山东省菏泽市单县向阳路',116.1,34.7924,'106.39.189.194',1),(137,18,'2016-05-02 05:50:32','山东省菏泽市单县向阳路',116.1,34.7924,'106.39.189.212',1),(138,18,'2016-05-02 05:50:34','山东省菏泽市单县向阳路',116.1,34.7924,'106.39.189.210',0),(139,18,'2016-05-02 05:50:36','山东省菏泽市单县向阳路',116.1,34.7924,'106.39.189.231',0),(140,18,'2016-05-02 05:50:39','山东省菏泽市单县向阳路',116.1,34.7924,'106.39.189.200',1),(141,18,'2016-05-02 05:52:23','山东省菏泽市单县文化路',116.094,34.7926,'106.39.189.201',1),(142,18,'2016-05-02 05:52:24','山东省菏泽市单县文化路',116.094,34.7926,'106.39.189.229',1),(143,18,'2016-05-02 05:59:28','山东省菏泽市单县文化路',116.094,34.7926,'106.39.189.227',1),(144,18,'2016-05-02 05:59:29','山东省菏泽市单县文化路',116.094,34.7926,'106.39.189.226',1),(145,18,'2016-05-02 06:05:46','山东省菏泽市单县胜利路',116.097,34.7997,'106.39.189.208',1),(146,18,'2016-05-02 06:06:13','山东省菏泽市单县胜利路',116.097,34.7997,'106.39.189.202',1),(147,18,'2016-05-02 06:06:16','山东省菏泽市单县胜利路',116.097,34.7997,'106.39.189.213',0),(148,18,'2016-05-02 06:06:18','山东省菏泽市单县胜利路',116.097,34.7997,'106.39.189.195',0),(149,18,'2016-05-02 06:06:18','山东省菏泽市单县胜利路',116.097,34.7997,'106.39.189.210',1),(150,18,'2016-05-02 06:06:25','山东省菏泽市单县胜利路',116.097,34.7997,'106.39.189.228',1),(151,19,'2016-05-02 07:00:01','山东省青岛市崂山区同安路',120.475,36.1241,'123.103.8.80',1),(152,19,'2016-05-02 07:00:08','山东省青岛市崂山区同安路',120.475,36.1241,'123.103.8.80',0),(153,18,'2016-05-02 14:48:23','山东省菏泽市牡丹区永昌路',115.492,35.2497,'106.39.189.197',1),(154,18,'2016-05-02 14:48:37','山东省菏泽市牡丹区永昌路',115.492,35.2497,'106.39.189.232',0),(155,18,'2016-05-02 14:51:18','山东省菏泽市牡丹区永昌路',115.492,35.2497,'106.39.189.194',1),(156,18,'2016-05-02 14:53:43','山东省菏泽市牡丹区永昌路',115.492,35.2497,'106.39.189.229',0),(157,18,'2016-05-02 14:55:13','山东省菏泽市牡丹区永昌路',115.492,35.2497,'106.39.189.227',1),(158,17,'2016-05-03 02:32:37','山东省青岛市崂山区科大支路',120.478,36.1207,'112.224.65.137',1),(159,19,'2016-05-03 03:09:44','山东省青岛市崂山区科大支路57',120.48,36.1184,'112.224.65.101',1),(160,19,'2016-05-03 03:09:46','山东省青岛市崂山区科大支路57',120.48,36.1184,'112.224.65.101',0),(161,17,'2016-05-03 03:11:27','山东省青岛市崂山区科大支路',120.48,36.1202,'112.224.65.137',1);
/*!40000 ALTER TABLE `reach` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `token`
--

DROP TABLE IF EXISTS `token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `token` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `token` varchar(32) NOT NULL,
  `remote_ip` varchar(15) DEFAULT NULL,
  `timeOut` timestamp NULL DEFAULT NULL,
  `uid` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `token_employee_idx` (`uid`),
  CONSTRAINT `token_employee` FOREIGN KEY (`uid`) REFERENCES `employee` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=457 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `token`
--

LOCK TABLES `token` WRITE;
/*!40000 ALTER TABLE `token` DISABLE KEYS */;
INSERT INTO `token` VALUES (419,'F3E231D6AE1B451FAF56653C003EB013','106.39.189.230','2016-05-02 15:52:39',18),(447,'172CAE0ED1FE823FD09F0F92CA294048','112.224.65.101','2016-05-03 04:26:51',19),(456,'571DDB820F11FFDA008AA406707DF9E2','10.9.3.150','2016-05-03 07:04:43',20);
/*!40000 ALTER TABLE `token` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'justdoit'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-05-03 14:20:00
