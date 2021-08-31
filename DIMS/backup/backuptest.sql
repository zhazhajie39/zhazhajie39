-- MySQL dump 10.13  Distrib 8.0.18, for Win64 (x86_64)
--
-- Host: localhost    Database: db_dims
-- ------------------------------------------------------
-- Server version	8.0.18

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
-- Table structure for table `tb_mm`
--

DROP TABLE IF EXISTS `tb_mm`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_mm` (
  `mid` int(8) unsigned NOT NULL AUTO_INCREMENT,
  `mname` varchar(12) NOT NULL,
  `mcate` varchar(10) NOT NULL,
  `mupri` float NOT NULL,
  `mbid` float NOT NULL,
  `mpaddr` varchar(20) NOT NULL,
  `mspacs` varchar(10) NOT NULL,
  `mnum` int(11) NOT NULL,
  `mpdate` date NOT NULL,
  `mexpitydate` date NOT NULL,
  `msup` varchar(20) NOT NULL,
  `mremark` varchar(40) DEFAULT NULL,
  `munit` varchar(10) NOT NULL,
  PRIMARY KEY (`mid`)
) ENGINE=InnoDB AUTO_INCREMENT=111111122 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_mm`
--

LOCK TABLES `tb_mm` WRITE;
/*!40000 ALTER TABLE `tb_mm` DISABLE KEYS */;
INSERT INTO `tb_mm` VALUES (111111112,'小苏','化学药',2,0.5,'','',12,'2020-02-01','2022-02-11','湖南省xx市xx药业有限公司','','支'),(111111114,'小苏打','化学药',2.5,0.5,'','',100,'2020-02-11','2021-02-11','湖南省xx市xx药业有限公司','','支'),(111111115,'苏打','处方药',10,4,'XXXXXX','300ml/瓶',12,'2017-02-01','2019-02-01','湖南省邵阳市xx药业有限公司','无','毫升'),(111111116,'橘皮','非处方药',2,0.5,'无','1斤/袋',20,'2018-03-01','2019-03-01','湖南省邵阳市xx药业有限公司','无','斤'),(111111117,'血清','非处方药',20,10,'无','30ml/瓶',32,'2020-06-05','2021-07-05','广东省广州市XX药业有限公司','无','瓶');
/*!40000 ALTER TABLE `tb_mm` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_msell`
--

DROP TABLE IF EXISTS `tb_msell`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_msell` (
  `selid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `selmid` int(8) NOT NULL,
  `selmname` varchar(12) NOT NULL,
  `selnum` int(11) NOT NULL,
  `totalpri` float NOT NULL,
  `oprator` varchar(12) NOT NULL,
  PRIMARY KEY (`selid`,`selmid`)
) ENGINE=InnoDB AUTO_INCREMENT=2222222223 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_msell`
--

LOCK TABLES `tb_msell` WRITE;
/*!40000 ALTER TABLE `tb_msell` DISABLE KEYS */;
INSERT INTO `tb_msell` VALUES (2222222222,11111111,'肾上腺素',2,4,'张三');
/*!40000 ALTER TABLE `tb_msell` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_mstock`
--

DROP TABLE IF EXISTS `tb_mstock`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_mstock` (
  `kmid` int(8) unsigned NOT NULL,
  `ksid` int(6) NOT NULL,
  `kmname` varchar(12) NOT NULL,
  `kmnum` int(11) NOT NULL,
  `kmupri` float NOT NULL,
  `ktype` int(11) NOT NULL,
  PRIMARY KEY (`kmid`,`ksid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_mstock`
--

LOCK TABLES `tb_mstock` WRITE;
/*!40000 ALTER TABLE `tb_mstock` DISABLE KEYS */;
INSERT INTO `tb_mstock` VALUES (111111112,210002,'小苏',12,2,0),(111111114,210002,'小苏打',100,2.5,0);
/*!40000 ALTER TABLE `tb_mstock` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_msupplier`
--

DROP TABLE IF EXISTS `tb_msupplier`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_msupplier` (
  `sid` int(6) unsigned NOT NULL AUTO_INCREMENT,
  `sname` varchar(20) NOT NULL,
  `saddr` varchar(20) NOT NULL,
  `sremark` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`sid`)
) ENGINE=InnoDB AUTO_INCREMENT=210011 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_msupplier`
--

LOCK TABLES `tb_msupplier` WRITE;
/*!40000 ALTER TABLE `tb_msupplier` DISABLE KEYS */;
INSERT INTO `tb_msupplier` VALUES (210000,'湖南省衡阳市xx药业有限公司','湖南省衡阳市珠晖区师范','没有'),(210001,'湖南省邵阳市xx药业有限公司','湖南省邵阳市XX区','无'),(210002,'湖南省长沙市xx药业有限公司','湖南省','无'),(210007,'江西省XX市XX制药有限公司','eweewaawq','wwewqwwewqq'),(210008,'撒旦圣诞节','eweewaawq','wwewqwwewqq'),(210009,'十多年时间','傻逼','wwewqwwewqq'),(210010,'十多大胜对手','eweewaawq','wwewqwwewqq');
/*!40000 ALTER TABLE `tb_msupplier` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_muser`
--

DROP TABLE IF EXISTS `tb_muser`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_muser` (
  `id` int(8) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `xb` varchar(2) NOT NULL,
  `pwd` varchar(12) NOT NULL,
  `sub_date` date DEFAULT NULL,
  `tel` varchar(20) DEFAULT NULL,
  `mail` varchar(20) DEFAULT NULL,
  `khyh` varchar(20) DEFAULT NULL,
  `ism` int(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21314020 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_muser`
--

LOCK TABLES `tb_muser` WRITE;
/*!40000 ALTER TABLE `tb_muser` DISABLE KEYS */;
INSERT INTO `tb_muser` VALUES (21314001,'张三','男','abc123','2021-03-02','162533312','323283728@qq.com','交通银行衡阳支行',1),(21314003,'李小芳','男','123456a','2021-03-01','12233112','342312322@qq.com','建设银行永州支行',0),(21314006,'王舞','女','wwqe123','2021-03-15','162533312','1625533488@qq.com','无',0),(21314015,'小芳','女','123456','2021-03-21','13875643389','tggf233@gmail.com','无',0),(21314019,'校长','男','123456','2021-03-29','14232455161','5675675888@gmail.com','无',1);
/*!40000 ALTER TABLE `tb_muser` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-04-08 12:23:12
