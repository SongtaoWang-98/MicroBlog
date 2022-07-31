-- MySQL dump 10.13  Distrib 8.0.23, for macos10.15 (x86_64)
--
-- Host: 127.0.0.1    Database: microblog
-- ------------------------------------------------------
-- Server version	8.0.23

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `blog`
--

DROP TABLE IF EXISTS `blog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `blog` (
  `id` int NOT NULL AUTO_INCREMENT,
  `publisher_id` int NOT NULL,
  `time` datetime NOT NULL,
  `scope` enum('PUBLIC','FOLLOW','FRIEND','PRIVATE') DEFAULT 'PUBLIC',
  `content` varchar(255) DEFAULT NULL,
  `photo_id` int DEFAULT NULL,
  `like_num` int DEFAULT '0',
  `collect_num` int DEFAULT '0',
  `forward_num` int DEFAULT '0',
  `state` enum('NORMAL','BANNED') DEFAULT 'NORMAL',
  `type` enum('ORIGINAL','FORWARD') DEFAULT 'ORIGINAL',
  `source_id` int DEFAULT NULL,
  `heat` int DEFAULT '0',
  `deleted` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `blog`
--

LOCK TABLES `blog` WRITE;
/*!40000 ALTER TABLE `blog` DISABLE KEYS */;
INSERT INTO `blog` VALUES (1,1,'2022-07-31 14:56:36','PUBLIC','今天天气真好！',1,3,1,0,'NORMAL','ORIGINAL',NULL,7,0),(2,2,'2022-07-31 14:58:54','PUBLIC','天气阴沉，上班好困~',NULL,2,1,0,'NORMAL','ORIGINAL',NULL,1,0),(3,3,'2022-07-31 15:04:34','PUBLIC','每天做核酸真的谢0.0',NULL,1,0,0,'NORMAL','ORIGINAL',NULL,-3,0),(4,4,'2022-07-31 15:12:20','PUBLIC','健身房都关门了',NULL,1,0,0,'NORMAL','ORIGINAL',NULL,0,0),(5,1,'2022-07-31 15:30:24','PUBLIC','放大范德萨',NULL,0,0,0,'NORMAL','ORIGINAL',NULL,0,0),(9,1,'2022-07-31 15:38:28','FOLLOW','asdfdasfdsaf',NULL,1,1,0,'NORMAL','ORIGINAL',NULL,0,0),(11,1,'2022-07-31 20:12:10','PUBLIC','d撒旦飞洒',NULL,0,0,0,'NORMAL','ORIGINAL',NULL,0,0),(12,1,'2022-07-31 20:12:23','PUBLIC','加大数据发送方',NULL,0,0,0,'NORMAL','ORIGINAL',NULL,0,0),(13,1,'2022-07-31 20:12:45','PUBLIC','大沙发沙发沙发',NULL,0,0,0,'NORMAL','ORIGINAL',NULL,0,0),(14,1,'2022-07-31 09:54:31','PUBLIC','大师傅',NULL,0,0,0,'NORMAL','ORIGINAL',NULL,0,0),(15,1,'2022-07-31 09:57:06','PUBLIC','正文',NULL,0,0,0,'NORMAL','ORIGINAL',NULL,0,0),(16,1,'2022-07-31 10:04:47','PUBLIC','正文测试测试',NULL,0,0,0,'NORMAL','ORIGINAL',NULL,0,0),(17,1,'2022-07-31 17:23:54','PUBLIC','1234',NULL,1,1,0,'NORMAL','ORIGINAL',NULL,0,0),(18,4,'2022-07-31 20:08:16','PUBLIC','eseseses',NULL,0,0,0,'NORMAL','ORIGINAL',NULL,0,0);
/*!40000 ALTER TABLE `blog` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `blog_topic`
--

DROP TABLE IF EXISTS `blog_topic`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `blog_topic` (
  `id` int NOT NULL AUTO_INCREMENT,
  `blog_id` int NOT NULL,
  `topic_id` int NOT NULL,
  `deleted` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `blog_topic`
--

LOCK TABLES `blog_topic` WRITE;
/*!40000 ALTER TABLE `blog_topic` DISABLE KEYS */;
INSERT INTO `blog_topic` VALUES (1,1,1,0),(2,2,1,0),(3,2,2,0),(4,3,3,0),(5,4,3,0),(7,1,4,0),(14,16,11,0),(15,16,12,0),(16,17,13,0);
/*!40000 ALTER TABLE `blog_topic` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `collection`
--

DROP TABLE IF EXISTS `collection`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `collection` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `blog_id` int NOT NULL,
  `deleted` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `collection`
--

LOCK TABLES `collection` WRITE;
/*!40000 ALTER TABLE `collection` DISABLE KEYS */;
INSERT INTO `collection` VALUES (1,3,1,0),(2,1,1,1),(3,1,2,0),(4,1,3,1),(5,1,8,1),(6,1,9,0),(7,1,16,1),(8,1,17,0),(9,5,1,1);
/*!40000 ALTER TABLE `collection` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comment` (
  `id` int NOT NULL AUTO_INCREMENT,
  `blog_id` int DEFAULT NULL,
  `sender_id` int DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  `is_reply` tinyint(1) DEFAULT '0',
  `reply_comment_id` int DEFAULT NULL,
  `deleted` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
INSERT INTO `comment` VALUES (1,1,2,'确实','2022-07-18 17:01:53',0,NULL,0),(2,1,3,'哈哈哈！','2022-07-21 09:04:31',0,NULL,0),(3,1,2,'第三条评论','2022-07-23 23:14:49',0,NULL,0),(4,1,4,'第四条评论','2022-07-23 23:15:24',0,NULL,0),(5,1,1,' 666','2022-07-27 13:52:59',0,NULL,0),(6,1,1,' 64654646','2022-07-27 13:53:41',0,NULL,0),(7,3,1,'testtest','2022-07-27 13:58:30',0,NULL,0),(8,16,1,'测试测试','2022-07-28 13:16:44',0,NULL,0);
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `concern_group`
--

DROP TABLE IF EXISTS `concern_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `concern_group` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `name` varchar(255) DEFAULT '默认分组',
  `deleted` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `concern_group`
--

LOCK TABLES `concern_group` WRITE;
/*!40000 ALTER TABLE `concern_group` DISABLE KEYS */;
INSERT INTO `concern_group` VALUES (1,1,'默认分组',0),(2,2,'默认分组',0),(3,3,'默认分组',0),(4,1,'朋友',0),(5,1,'同事',0),(6,4,'默认分组',0);
/*!40000 ALTER TABLE `concern_group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `detailed_info`
--

DROP TABLE IF EXISTS `detailed_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `detailed_info` (
  `user_id` int NOT NULL,
  `real_name` varchar(255) DEFAULT NULL,
  `gender` enum('MALE','FEMALE') DEFAULT 'MALE',
  `birthday` date DEFAULT NULL,
  `company` varchar(255) DEFAULT NULL,
  `job` varchar(255) DEFAULT NULL,
  `university` varchar(255) DEFAULT NULL,
  `tel_number` varchar(255) DEFAULT NULL,
  `hobby` varchar(255) DEFAULT NULL,
  `province` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detailed_info`
--

LOCK TABLES `detailed_info` WRITE;
/*!40000 ALTER TABLE `detailed_info` DISABLE KEYS */;
INSERT INTO `detailed_info` VALUES (1,'王松涛','MALE','1998-03-12','成都东方财富信息技术公司','研发中台后端开发','华中科技大学','18215257896','看书','甘肃');
/*!40000 ALTER TABLE `detailed_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `follow`
--

DROP TABLE IF EXISTS `follow`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `follow` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int DEFAULT NULL,
  `following_id` int DEFAULT NULL,
  `deleted` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `follow`
--

LOCK TABLES `follow` WRITE;
/*!40000 ALTER TABLE `follow` DISABLE KEYS */;
INSERT INTO `follow` VALUES (1,1,2,0),(2,2,1,0),(3,3,1,0),(4,1,3,0),(5,4,1,0),(6,1,5,0);
/*!40000 ALTER TABLE `follow` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `group_follow`
--

DROP TABLE IF EXISTS `group_follow`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `group_follow` (
  `id` int NOT NULL AUTO_INCREMENT,
  `group_id` int NOT NULL,
  `follow_id` int NOT NULL,
  `deleted` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `group_follow`
--

LOCK TABLES `group_follow` WRITE;
/*!40000 ALTER TABLE `group_follow` DISABLE KEYS */;
INSERT INTO `group_follow` VALUES (1,4,1,0),(2,5,4,0),(3,2,2,0),(4,3,3,0),(5,6,5,0),(6,1,6,0);
/*!40000 ALTER TABLE `group_follow` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `heat_time`
--

DROP TABLE IF EXISTS `heat_time`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `heat_time` (
  `id` int NOT NULL AUTO_INCREMENT,
  `topic_id` int NOT NULL,
  `heat` int DEFAULT '0',
  `time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `heat_time`
--

LOCK TABLES `heat_time` WRITE;
/*!40000 ALTER TABLE `heat_time` DISABLE KEYS */;
/*!40000 ALTER TABLE `heat_time` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `like_set`
--

DROP TABLE IF EXISTS `like_set`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `like_set` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `blog_id` int NOT NULL,
  `deleted` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `like_set`
--

LOCK TABLES `like_set` WRITE;
/*!40000 ALTER TABLE `like_set` DISABLE KEYS */;
INSERT INTO `like_set` VALUES (1,2,1,0),(2,1,2,0),(3,1,1,0),(4,1,3,0),(5,1,9,0),(6,1,7,0),(7,1,16,1),(8,1,17,0),(9,1,4,0),(10,5,1,0);
/*!40000 ALTER TABLE `like_set` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `message`
--

DROP TABLE IF EXISTS `message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `message` (
  `id` int NOT NULL AUTO_INCREMENT,
  `sender_id` int NOT NULL,
  `receiver_id` int NOT NULL,
  `time` datetime DEFAULT NULL,
  `state` enum('UNREAD','READ') DEFAULT 'UNREAD',
  `content` int DEFAULT NULL,
  `deleted` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message`
--

LOCK TABLES `message` WRITE;
/*!40000 ALTER TABLE `message` DISABLE KEYS */;
/*!40000 ALTER TABLE `message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `photograph`
--

DROP TABLE IF EXISTS `photograph`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `photograph` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `deleted` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `photograph`
--

LOCK TABLES `photograph` WRITE;
/*!40000 ALTER TABLE `photograph` DISABLE KEYS */;
INSERT INTO `photograph` VALUES (1,'苏格兰',0);
/*!40000 ALTER TABLE `photograph` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `picture`
--

DROP TABLE IF EXISTS `picture`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `picture` (
  `id` int NOT NULL AUTO_INCREMENT,
  `photograph_id` int DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `deleted` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `picture`
--

LOCK TABLES `picture` WRITE;
/*!40000 ALTER TABLE `picture` DISABLE KEYS */;
INSERT INTO `picture` VALUES (1,1,'Stewart/1.jpg',0),(2,1,'Stewart/2.jpg',0),(3,NULL,'Default/1.png',0),(4,NULL,'Default/2.png',0),(5,NULL,'Default/3.png',0),(6,NULL,'Default/4.png',0),(7,NULL,'Default/5.png',0),(8,NULL,'Default/6.png',0),(9,NULL,'Default/7.png',0);
/*!40000 ALTER TABLE `picture` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `report_feedback`
--

DROP TABLE IF EXISTS `report_feedback`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `report_feedback` (
  `id` int NOT NULL AUTO_INCREMENT,
  `sender_id` int NOT NULL,
  `time` datetime DEFAULT NULL,
  `type` enum('REPORT','FEEDBACK') DEFAULT NULL,
  `state` enum('NOT PROCESSED','PROCESSED') DEFAULT 'NOT PROCESSED',
  `content` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `report_feedback`
--

LOCK TABLES `report_feedback` WRITE;
/*!40000 ALTER TABLE `report_feedback` DISABLE KEYS */;
/*!40000 ALTER TABLE `report_feedback` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_action`
--

DROP TABLE IF EXISTS `sys_action`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_action` (
  `id` int NOT NULL AUTO_INCREMENT,
  `remote_address` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `method` enum('POST','GET','PUT','DELETE') DEFAULT NULL,
  `params` varchar(255) DEFAULT NULL,
  `local_port` int DEFAULT NULL,
  `session_id` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `func_desc` varchar(255) DEFAULT NULL,
  `type` enum('CONTROLLER','SERVICE') DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=82 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_action`
--

LOCK TABLES `sys_action` WRITE;
/*!40000 ALTER TABLE `sys_action` DISABLE KEYS */;
INSERT INTO `sys_action` VALUES (1,'0:0:0:0:0:0:0:1','http://localhost:8181/home/hot','GET',NULL,8181,'318F90C0DD1FCDD35C82168504A33368','songtao_wang@qq.com','展示热门博文','CONTROLLER',NULL),(2,'0:0:0:0:0:0:0:1','http://localhost:8181/home/group','GET','groupId=4',8181,'318F90C0DD1FCDD35C82168504A33368','songtao_wang@qq.com','按分组展示博文','CONTROLLER',NULL),(3,'0:0:0:0:0:0:0:1','http://localhost:8181/home/hot','GET',NULL,8181,'7974586CBDB80C02C7F3CF34B62BDA08','songtao_wang@qq.com','展示热门博文','CONTROLLER','2022-07-29 16:02:56'),(4,'0:0:0:0:0:0:0:1','http://localhost:8181/blog/dislike','POST','blogId=1',8181,'7974586CBDB80C02C7F3CF34B62BDA08','songtao_wang@qq.com','取消点赞博文','CONTROLLER','2022-07-29 16:03:14'),(5,'0:0:0:0:0:0:0:1','http://localhost:8181/home/hot','GET',NULL,8181,'7974586CBDB80C02C7F3CF34B62BDA08','songtao_wang@qq.com','展示热门博文','CONTROLLER','2022-07-29 16:03:14'),(6,'0:0:0:0:0:0:0:1','http://localhost:8181/home/hot','GET',NULL,8181,'7974586CBDB80C02C7F3CF34B62BDA08','songtao_wang@qq.com','展示热门博文','CONTROLLER','2022-07-29 16:03:29'),(7,'0:0:0:0:0:0:0:1','http://localhost:8181/blog/like','POST','blogId=1',8181,'7974586CBDB80C02C7F3CF34B62BDA08','songtao_wang@qq.com','点赞博文','CONTROLLER','2022-07-29 16:03:48'),(8,'0:0:0:0:0:0:0:1','http://localhost:8181/home/hot','GET',NULL,8181,'7974586CBDB80C02C7F3CF34B62BDA08','songtao_wang@qq.com','展示热门博文','CONTROLLER','2022-07-29 16:03:48'),(9,'0:0:0:0:0:0:0:1','http://localhost:8181/blog/disCollect','POST','blogId=3',8181,'7974586CBDB80C02C7F3CF34B62BDA08','songtao_wang@qq.com','取消收藏博文','CONTROLLER','2022-07-29 16:04:01'),(10,'0:0:0:0:0:0:0:1','http://localhost:8181/home/hot','GET',NULL,8181,'7974586CBDB80C02C7F3CF34B62BDA08','songtao_wang@qq.com','展示热门博文','CONTROLLER','2022-07-29 16:04:02'),(11,'0:0:0:0:0:0:0:1','http://localhost:8181/home/hot','GET',NULL,8181,'0159D7BA0AB5434D244B7832AFD05238','gogogo@go.com','展示热门博文','CONTROLLER','2022-07-31 18:58:09'),(12,'0:0:0:0:0:0:0:1','http://localhost:8181/home/hot','GET',NULL,8181,'0159D7BA0AB5434D244B7832AFD05238','gogogo@go.com','展示热门博文','CONTROLLER','2022-07-31 18:58:20'),(13,'0:0:0:0:0:0:0:1','http://localhost:8181/user/personalStat','GET',NULL,8181,'0159D7BA0AB5434D244B7832AFD05238','gogogo@go.com','展示个人统计','CONTROLLER','2022-07-31 18:58:30'),(14,'0:0:0:0:0:0:0:1','http://localhost:8181/home/hot','GET',NULL,8181,'70B2E32D63E5AE83D49660BA41E871E6','songtao_wang@qq.com','展示热门博文','CONTROLLER','2022-07-31 19:00:37'),(15,'0:0:0:0:0:0:0:1','http://localhost:8181/home/hot','GET',NULL,8181,'E8F7FCB193CCDC4EEB1E64888F94888D','gogogo@go.com','展示热门博文','CONTROLLER','2022-07-31 19:01:21'),(16,'0:0:0:0:0:0:0:1','http://localhost:8181/user/personalStat','GET',NULL,8181,'E8F7FCB193CCDC4EEB1E64888F94888D','gogogo@go.com','展示个人统计','CONTROLLER','2022-07-31 19:01:23'),(17,'0:0:0:0:0:0:0:1','http://localhost:8181/home/hot','GET',NULL,8181,'E8F7FCB193CCDC4EEB1E64888F94888D','gogogo@go.com','展示热门博文','CONTROLLER','2022-07-31 19:02:29'),(18,'0:0:0:0:0:0:0:1','http://localhost:8181/home/hot','GET',NULL,8181,'AB7F3EABD81DE7A776B318E4B6BFEE0E','gogogo@go.com','展示热门博文','CONTROLLER','2022-07-31 19:05:22'),(19,'0:0:0:0:0:0:0:1','http://localhost:8181/user/personalStat','GET',NULL,8181,'AB7F3EABD81DE7A776B318E4B6BFEE0E','gogogo@go.com','展示个人统计','CONTROLLER','2022-07-31 19:05:30'),(20,'0:0:0:0:0:0:0:1','http://localhost:8181/user/personalStat','GET',NULL,8181,'AB7F3EABD81DE7A776B318E4B6BFEE0E','gogogo@go.com','展示个人统计','CONTROLLER','2022-07-31 19:06:25'),(21,'0:0:0:0:0:0:0:1','http://localhost:8181/user/personalStat','GET',NULL,8181,'AB7F3EABD81DE7A776B318E4B6BFEE0E','gogogo@go.com','展示个人统计','CONTROLLER','2022-07-31 19:06:28'),(22,'0:0:0:0:0:0:0:1','http://localhost:8181/user/personalStat','GET',NULL,8181,'AB7F3EABD81DE7A776B318E4B6BFEE0E','gogogo@go.com','展示个人统计','CONTROLLER','2022-07-31 19:08:48'),(23,'0:0:0:0:0:0:0:1','http://localhost:8181/home/hot','GET',NULL,8181,'AB7F3EABD81DE7A776B318E4B6BFEE0E','gogogo@go.com','展示热门博文','CONTROLLER','2022-07-31 19:09:05'),(24,'0:0:0:0:0:0:0:1','http://localhost:8181/home/hot','GET',NULL,8181,'AB7F3EABD81DE7A776B318E4B6BFEE0E','gogogo@go.com','展示热门博文','CONTROLLER','2022-07-31 19:12:11'),(25,'0:0:0:0:0:0:0:1','http://localhost:8181/home/hot','GET',NULL,8181,'AB7F3EABD81DE7A776B318E4B6BFEE0E','gogogo@go.com','展示热门博文','CONTROLLER','2022-07-31 19:13:57'),(26,'0:0:0:0:0:0:0:1','http://localhost:8181/home/hot','GET',NULL,8181,'AB7F3EABD81DE7A776B318E4B6BFEE0E','gogogo@go.com','展示热门博文','CONTROLLER','2022-07-31 19:15:33'),(27,'0:0:0:0:0:0:0:1','http://localhost:8181/home/hot','GET',NULL,8181,'AB7F3EABD81DE7A776B318E4B6BFEE0E','gogogo@go.com','展示热门博文','CONTROLLER','2022-07-31 19:15:39'),(28,'0:0:0:0:0:0:0:1','http://localhost:8181/home/hot','GET',NULL,8181,'AB7F3EABD81DE7A776B318E4B6BFEE0E','gogogo@go.com','展示热门博文','CONTROLLER','2022-07-31 19:15:57'),(29,'0:0:0:0:0:0:0:1','http://localhost:8181/home/hot','GET',NULL,8181,'AB7F3EABD81DE7A776B318E4B6BFEE0E','gogogo@go.com','展示热门博文','CONTROLLER','2022-07-31 19:19:19'),(30,'0:0:0:0:0:0:0:1','http://localhost:8181/home/hot','GET',NULL,8181,'AB7F3EABD81DE7A776B318E4B6BFEE0E','gogogo@go.com','展示热门博文','CONTROLLER','2022-07-31 19:19:25'),(31,'0:0:0:0:0:0:0:1','http://localhost:8181/home/hot','GET',NULL,8181,'AB7F3EABD81DE7A776B318E4B6BFEE0E','gogogo@go.com','展示热门博文','CONTROLLER','2022-07-31 19:19:31'),(32,'0:0:0:0:0:0:0:1','http://localhost:8181/home/hot','GET',NULL,8181,'AB7F3EABD81DE7A776B318E4B6BFEE0E','gogogo@go.com','展示热门博文','CONTROLLER','2022-07-31 19:20:06'),(33,'0:0:0:0:0:0:0:1','http://localhost:8181/home/hot','GET',NULL,8181,'AB7F3EABD81DE7A776B318E4B6BFEE0E','gogogo@go.com','展示热门博文','CONTROLLER','2022-07-31 19:20:14'),(34,'0:0:0:0:0:0:0:1','http://localhost:8181/home/hot','GET',NULL,8181,'AB7F3EABD81DE7A776B318E4B6BFEE0E','gogogo@go.com','展示热门博文','CONTROLLER','2022-07-31 19:20:24'),(35,'0:0:0:0:0:0:0:1','http://localhost:8181/blog/comment','POST','blogId=1&content=',8181,'AB7F3EABD81DE7A776B318E4B6BFEE0E','gogogo@go.com','评论博文','CONTROLLER','2022-07-31 19:20:26'),(36,'0:0:0:0:0:0:0:1','http://localhost:8181/home/hot','GET',NULL,8181,'AB7F3EABD81DE7A776B318E4B6BFEE0E','gogogo@go.com','展示热门博文','CONTROLLER','2022-07-31 19:20:26'),(37,'0:0:0:0:0:0:0:1','http://localhost:8181/home/hot','GET',NULL,8181,'AB7F3EABD81DE7A776B318E4B6BFEE0E','gogogo@go.com','展示热门博文','CONTROLLER','2022-07-31 19:20:31'),(38,'0:0:0:0:0:0:0:1','http://localhost:8181/home/hot','GET',NULL,8181,'AB7F3EABD81DE7A776B318E4B6BFEE0E','gogogo@go.com','展示热门博文','CONTROLLER','2022-07-31 19:30:45'),(39,'0:0:0:0:0:0:0:1','http://localhost:8181/home/hot','GET',NULL,8181,'AB7F3EABD81DE7A776B318E4B6BFEE0E','gogogo@go.com','展示热门博文','CONTROLLER','2022-07-31 19:34:28'),(40,'0:0:0:0:0:0:0:1','http://localhost:8181/home/hot','GET',NULL,8181,'AB7F3EABD81DE7A776B318E4B6BFEE0E','gogogo@go.com','展示热门博文','CONTROLLER','2022-07-31 19:34:31'),(41,'0:0:0:0:0:0:0:1','http://localhost:8181/home/hot','GET',NULL,8181,'AB7F3EABD81DE7A776B318E4B6BFEE0E','gogogo@go.com','展示热门博文','CONTROLLER','2022-07-31 19:34:57'),(42,'0:0:0:0:0:0:0:1','http://localhost:8181/home/hot','GET',NULL,8181,'AB7F3EABD81DE7A776B318E4B6BFEE0E','gogogo@go.com','展示热门博文','CONTROLLER','2022-07-31 19:35:40'),(43,'0:0:0:0:0:0:0:1','http://localhost:8181/blog/like','POST','blogId=1',8181,'AB7F3EABD81DE7A776B318E4B6BFEE0E','gogogo@go.com','点赞博文','CONTROLLER','2022-07-31 19:35:48'),(44,'0:0:0:0:0:0:0:1','http://localhost:8181/blog/dislike','POST','blogId=1',8181,'AB7F3EABD81DE7A776B318E4B6BFEE0E','gogogo@go.com','取消点赞博文','CONTROLLER','2022-07-31 19:36:40'),(45,'0:0:0:0:0:0:0:1','http://localhost:8181/blog/like','POST','blogId=1',8181,'AB7F3EABD81DE7A776B318E4B6BFEE0E','gogogo@go.com','点赞博文','CONTROLLER','2022-07-31 19:36:42'),(46,'0:0:0:0:0:0:0:1','http://localhost:8181/blog/dislike','POST','blogId=1',8181,'AB7F3EABD81DE7A776B318E4B6BFEE0E','gogogo@go.com','取消点赞博文','CONTROLLER','2022-07-31 19:36:43'),(47,'0:0:0:0:0:0:0:1','http://localhost:8181/blog/collect','POST','blogId=1',8181,'AB7F3EABD81DE7A776B318E4B6BFEE0E','gogogo@go.com','收藏博文','CONTROLLER','2022-07-31 19:36:47'),(48,'0:0:0:0:0:0:0:1','http://localhost:8181/blog/disCollect','POST','blogId=1',8181,'AB7F3EABD81DE7A776B318E4B6BFEE0E','gogogo@go.com','取消收藏博文','CONTROLLER','2022-07-31 19:36:47'),(49,'0:0:0:0:0:0:0:1','http://localhost:8181/home/hot','GET',NULL,8181,'AB7F3EABD81DE7A776B318E4B6BFEE0E','gogogo@go.com','展示热门博文','CONTROLLER','2022-07-31 19:37:01'),(50,'0:0:0:0:0:0:0:1','http://localhost:8181/blog/like','POST','blogId=1',8181,'AB7F3EABD81DE7A776B318E4B6BFEE0E','gogogo@go.com','点赞博文','CONTROLLER','2022-07-31 19:37:04'),(51,'0:0:0:0:0:0:0:1','http://localhost:8181/home/hot','GET',NULL,8181,'AB7F3EABD81DE7A776B318E4B6BFEE0E','gogogo@go.com','展示热门博文','CONTROLLER','2022-07-31 19:37:04'),(52,'0:0:0:0:0:0:0:1','http://localhost:8181/blog/dislike','POST','blogId=1',8181,'AB7F3EABD81DE7A776B318E4B6BFEE0E','gogogo@go.com','取消点赞博文','CONTROLLER','2022-07-31 19:37:04'),(53,'0:0:0:0:0:0:0:1','http://localhost:8181/home/hot','GET',NULL,8181,'AB7F3EABD81DE7A776B318E4B6BFEE0E','gogogo@go.com','展示热门博文','CONTROLLER','2022-07-31 19:37:04'),(54,'0:0:0:0:0:0:0:1','http://localhost:8181/blog/collect','POST','blogId=1',8181,'AB7F3EABD81DE7A776B318E4B6BFEE0E','gogogo@go.com','收藏博文','CONTROLLER','2022-07-31 19:37:05'),(55,'0:0:0:0:0:0:0:1','http://localhost:8181/home/hot','GET',NULL,8181,'AB7F3EABD81DE7A776B318E4B6BFEE0E','gogogo@go.com','展示热门博文','CONTROLLER','2022-07-31 19:37:05'),(56,'0:0:0:0:0:0:0:1','http://localhost:8181/blog/disCollect','POST','blogId=1',8181,'AB7F3EABD81DE7A776B318E4B6BFEE0E','gogogo@go.com','取消收藏博文','CONTROLLER','2022-07-31 19:37:06'),(57,'0:0:0:0:0:0:0:1','http://localhost:8181/home/hot','GET',NULL,8181,'AB7F3EABD81DE7A776B318E4B6BFEE0E','gogogo@go.com','展示热门博文','CONTROLLER','2022-07-31 19:37:06'),(58,'0:0:0:0:0:0:0:1','http://localhost:8181/blog/like','POST','blogId=1',8181,'AB7F3EABD81DE7A776B318E4B6BFEE0E','gogogo@go.com','点赞博文','CONTROLLER','2022-07-31 19:37:22'),(59,'0:0:0:0:0:0:0:1','http://localhost:8181/blog/dislike','POST','blogId=1',8181,'AB7F3EABD81DE7A776B318E4B6BFEE0E','gogogo@go.com','取消点赞博文','CONTROLLER','2022-07-31 19:37:23'),(60,'0:0:0:0:0:0:0:1','http://localhost:8181/blog/like','POST','blogId=1',8181,'AB7F3EABD81DE7A776B318E4B6BFEE0E','gogogo@go.com','点赞博文','CONTROLLER','2022-07-31 19:38:10'),(61,'0:0:0:0:0:0:0:1','http://localhost:8181/blog/dislike','POST','blogId=1',8181,'AB7F3EABD81DE7A776B318E4B6BFEE0E','gogogo@go.com','取消点赞博文','CONTROLLER','2022-07-31 19:38:12'),(62,'0:0:0:0:0:0:0:1','http://localhost:8181/blog/like','POST','blogId=1',8181,'AB7F3EABD81DE7A776B318E4B6BFEE0E','gogogo@go.com','点赞博文','CONTROLLER','2022-07-31 19:38:34'),(63,'0:0:0:0:0:0:0:1','http://localhost:8181/home/hot','GET',NULL,8181,'AB7F3EABD81DE7A776B318E4B6BFEE0E','gogogo@go.com','展示热门博文','CONTROLLER','2022-07-31 19:38:36'),(64,'0:0:0:0:0:0:0:1','http://localhost:8181/home/hot','GET',NULL,8181,'AB7F3EABD81DE7A776B318E4B6BFEE0E','gogogo@go.com','展示热门博文','CONTROLLER','2022-07-31 19:38:50'),(65,'0:0:0:0:0:0:0:1','http://localhost:8181/home/hot','GET',NULL,8181,'AB7F3EABD81DE7A776B318E4B6BFEE0E','gogogo@go.com','展示热门博文','CONTROLLER','2022-07-31 19:45:43'),(66,'0:0:0:0:0:0:0:1','http://localhost:8181/home/hot','GET',NULL,8181,'AB7F3EABD81DE7A776B318E4B6BFEE0E','gogogo@go.com','展示热门博文','CONTROLLER','2022-07-31 19:45:55'),(67,'0:0:0:0:0:0:0:1','http://localhost:8181/home/hot','GET',NULL,8181,'AB7F3EABD81DE7A776B318E4B6BFEE0E','gogogo@go.com','展示热门博文','CONTROLLER','2022-07-31 19:46:04'),(68,'0:0:0:0:0:0:0:1','http://localhost:8181/home/hot','GET',NULL,8181,'809DA46F9CA34D1CF8EEDC85175610C1','songtao_wang@qq.com','展示热门博文','CONTROLLER','2022-07-31 19:52:38'),(69,'0:0:0:0:0:0:0:1','http://localhost:8181/blog/dislike','POST','blogId=1',8181,'809DA46F9CA34D1CF8EEDC85175610C1','songtao_wang@qq.com','取消点赞博文','CONTROLLER','2022-07-31 19:52:41'),(70,'0:0:0:0:0:0:0:1','http://localhost:8181/home/hot','GET',NULL,8181,'809DA46F9CA34D1CF8EEDC85175610C1','songtao_wang@qq.com','展示热门博文','CONTROLLER','2022-07-31 19:52:41'),(71,'0:0:0:0:0:0:0:1','http://localhost:8181/blog/like','POST','blogId=1',8181,'809DA46F9CA34D1CF8EEDC85175610C1','songtao_wang@qq.com','点赞博文','CONTROLLER','2022-07-31 19:52:41'),(72,'0:0:0:0:0:0:0:1','http://localhost:8181/home/hot','GET',NULL,8181,'809DA46F9CA34D1CF8EEDC85175610C1','songtao_wang@qq.com','展示热门博文','CONTROLLER','2022-07-31 19:52:42'),(73,'0:0:0:0:0:0:0:1','http://localhost:8181/home/hot','GET',NULL,8181,'809DA46F9CA34D1CF8EEDC85175610C1','songtao_wang@qq.com','展示热门博文','CONTROLLER','2022-07-31 19:52:54'),(74,'0:0:0:0:0:0:0:1','http://localhost:8181/home/hot','GET',NULL,8181,'61A8788CD3E3CA2AC6325D835315FB22','songtao_wang@qq.com','展示热门博文','CONTROLLER','2022-07-31 19:54:23'),(75,'0:0:0:0:0:0:0:1','http://localhost:8181/home/hot','GET',NULL,8181,'61A8788CD3E3CA2AC6325D835315FB22','songtao_wang@qq.com','展示热门博文','CONTROLLER','2022-07-31 19:54:35'),(76,'0:0:0:0:0:0:0:1','http://localhost:8181/blog/dislike','POST','blogId=1',8181,'61A8788CD3E3CA2AC6325D835315FB22','songtao_wang@qq.com','取消点赞博文','CONTROLLER','2022-07-31 19:54:41'),(77,'0:0:0:0:0:0:0:1','http://localhost:8181/blog/like','POST','blogId=1',8181,'61A8788CD3E3CA2AC6325D835315FB22','songtao_wang@qq.com','点赞博文','CONTROLLER','2022-07-31 19:54:42'),(78,'0:0:0:0:0:0:0:1','http://localhost:8181/home/hot','GET',NULL,8181,'61A8788CD3E3CA2AC6325D835315FB22','songtao_wang@qq.com','展示热门博文','CONTROLLER','2022-07-31 19:56:05'),(79,'0:0:0:0:0:0:0:1','http://localhost:8181/home/hot','GET',NULL,8181,'61A8788CD3E3CA2AC6325D835315FB22','songtao_wang@qq.com','展示热门博文','CONTROLLER','2022-07-31 19:56:17'),(80,'0:0:0:0:0:0:0:1','http://localhost:8181/home/hot','GET',NULL,8181,'20BED6196BEF759202C8BD3BB843E1EA','songtao_wang@qq.com','展示热门博文','CONTROLLER','2022-07-31 19:59:25'),(81,'0:0:0:0:0:0:0:1','http://localhost:8181/home/hot','GET',NULL,8181,'20BED6196BEF759202C8BD3BB843E1EA','songtao_wang@qq.com','展示热门博文','CONTROLLER','2022-07-31 19:59:45');
/*!40000 ALTER TABLE `sys_action` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `topic`
--

DROP TABLE IF EXISTS `topic`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `topic` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `heat` int DEFAULT '0',
  `deleted` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `topic`
--

LOCK TABLES `topic` WRITE;
/*!40000 ALTER TABLE `topic` DISABLE KEYS */;
INSERT INTO `topic` VALUES (1,'成都天气',0,0),(2,'上班第一周',0,0),(3,'24小时核酸',0,0),(4,'加班',0,0),(5,'今天',0,0),(6,'大师傅撒法',0,0),(7,'接口连接链接',0,0),(8,'话题1',0,0),(9,'话题2',0,0),(10,'话题3',0,0),(11,'话题a',0,0),(12,'话题b',0,0),(13,'新话题',0,0);
/*!40000 ALTER TABLE `topic` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_info`
--

DROP TABLE IF EXISTS `user_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_info` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `nickname` varchar(255) DEFAULT '微博客用户',
  `password` varchar(255) NOT NULL,
  `type` enum('USER','MANAGER') DEFAULT 'USER',
  `state` enum('NORMAL','BANNED') DEFAULT 'NORMAL',
  `photo_id` int DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_info`
--

LOCK TABLES `user_info` WRITE;
/*!40000 ALTER TABLE `user_info` DISABLE KEYS */;
INSERT INTO `user_info` VALUES (1,'songtao_wang@qq.com','Stewart','123456','USER','NORMAL',1),(2,'zhangsan@yeah.net','TT','123456','USER','NORMAL',3),(3,'lisi@eastmoney.com','Jack','123456','USER','NORMAL',4),(4,'123456789@qq.com','Mr.Ba','123456','USER','NORMAL',2),(5,'gogogo@go.com','heihei','123456','USER','NORMAL',5),(6,'admin','admin','123456','MANAGER','NORMAL',1);
/*!40000 ALTER TABLE `user_info` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-07-31 20:01:17
