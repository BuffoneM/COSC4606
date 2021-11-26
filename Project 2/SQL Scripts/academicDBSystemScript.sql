-- MySQL dump 10.13  Distrib 8.0.27, for Win64 (x86_64)
--
-- Host: localhost    Database: academicdb
-- ------------------------------------------------------
-- Server version	8.0.27

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
-- Table structure for table `course_enrollment`
--

DROP TABLE IF EXISTS `course_enrollment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `course_enrollment` (
  `course_id` varchar(45) NOT NULL,
  `student_id` varchar(45) NOT NULL,
  `section_id` varchar(45) NOT NULL,
  PRIMARY KEY (`course_id`,`student_id`,`section_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course_enrollment`
--

LOCK TABLES `course_enrollment` WRITE;
/*!40000 ALTER TABLE `course_enrollment` DISABLE KEYS */;
INSERT INTO `course_enrollment` VALUES ('COSC1046','110617644','1'),('COSC1046','113192860','1'),('COSC1046','119352673','1'),('COSC1047','126293548','1'),('COSC1047','135001493','2'),('COSC1047','142531308','1'),('COSC2006','164399245','3'),('COSC2007','172525649','1'),('COSC2007','177252841','1'),('COSC2007','179918802','2'),('COSC2406','149514909','2'),('COSC2406','179918802','2'),('COSC2406','184072320','1'),('COSC2406','184225365','1'),('COSC2406','190801497','2');
/*!40000 ALTER TABLE `course_enrollment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course_info`
--

DROP TABLE IF EXISTS `course_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `course_info` (
  `course_id` varchar(45) NOT NULL,
  `course_name` varchar(45) DEFAULT NULL,
  `section_id` varchar(45) NOT NULL,
  `term` varchar(45) NOT NULL,
  PRIMARY KEY (`course_id`,`section_id`,`term`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course_info`
--

LOCK TABLES `course_info` WRITE;
/*!40000 ALTER TABLE `course_info` DISABLE KEYS */;
INSERT INTO `course_info` VALUES ('COSC1046','Introduction to Java','1','2021F'),('COSC1047','Advanced Java','1','2022W'),('COSC1047','Advanced Java','2','2021F'),('COSC2006','Data Structures 1','3','2021F'),('COSC2007','Data Structures 2','1','2021F'),('COSC2007','Data Structures 2','2','2022W'),('COSC2406','Assembly Language','1','2021F'),('COSC2406','Assembly Language','2','2022W');
/*!40000 ALTER TABLE `course_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course_staff`
--

DROP TABLE IF EXISTS `course_staff`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `course_staff` (
  `course_id` varchar(45) NOT NULL,
  `employee_number` varchar(45) NOT NULL,
  `section_id` varchar(45) NOT NULL,
  PRIMARY KEY (`course_id`,`employee_number`,`section_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course_staff`
--

LOCK TABLES `course_staff` WRITE;
/*!40000 ALTER TABLE `course_staff` DISABLE KEYS */;
INSERT INTO `course_staff` VALUES ('COSC1046','206284373','1'),('COSC1046','290012791','1'),('COSC1047','206284373','1'),('COSC1047','224606575','1'),('COSC2006','206284373','3'),('COSC2006','280281430','3'),('COSC2007','206284373','1'),('COSC2007','290012791','1'),('COSC2406','206284373','1'),('COSC2406','296583945','1');
/*!40000 ALTER TABLE `course_staff` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee` (
  `employee_number` varchar(45) NOT NULL,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `phone_number` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`employee_number`),
  UNIQUE KEY `employee_number_UNIQUE` (`employee_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES ('206284373','Devon','Quinn','230-737-3077'),('210504553','Stephanus','Gilardone','746-643-1620'),('214563309','Bjorn','Housaman','511-641-9471'),('224606575','Shoshanna','Regenhardt','516-134-9933'),('226049793','Stacie','Sauter','977-839-6980'),('227683175','Saunders','Ravenshear','181-172-8827'),('230726780','Phineas','Fynes','454-677-5688'),('234380854','Mela','Lochrie','154-435-7280'),('244361995','Aldus','Sargerson','641-725-8280'),('263545560','Wildon','Pladen','106-424-6469'),('267219418','Jewel','Kittiman','263-723-3480'),('273432073','Elwyn','Screeton','453-625-3601'),('274479751','Kelli','Jeduch','320-587-6284'),('277841714','Shaun','Greated','853-886-8527'),('280281430','Fin','Earengey','286-719-4344'),('283579427','Riobard','Heather','682-605-0555'),('290012791','Fields','Arkley','800-673-9742'),('295983049','Jeremias','Ragsdall','384-393-5336'),('296583945','Lorrie','Sollam','695-588-1211'),('299492855','Archibaldo','Cruddace','834-228-8375'),('987654321','Test','Staff','555-555-3333');
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `student` (
  `student_id` varchar(45) NOT NULL,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `phone_number` varchar(45) DEFAULT NULL,
  `program_name` varchar(45) DEFAULT NULL,
  `term_Status` varchar(45) DEFAULT NULL,
  `co-op` int DEFAULT NULL,
  `full_or_part` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`student_id`),
  UNIQUE KEY `student_id_UNIQUE` (`student_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` VALUES ('102092066','Auroora','Seal','812-919-9575','B.Sc.4 COSC','0',1,'full'),('110617644','Shelden','Wilshere','676-785-2186','B.Sc.4 COSC','1',1,'full'),('111222333','Test','Student','123-999-0000','B.Sc.4 COSC','1',1,'full'),('113192860','Corabella','Folkerts','976-781-4135','B.Sc.4 COSC','1',0,'full'),('119352673','Edgardo','Duffield','238-831-3473','B.Sc.4 COSC','1',1,'full'),('126293548','Lina','McMichell','594-152-7101','B.Sc.3 COSC','1',1,'full'),('133706887','Mervin','Meriel','982-499-7103','B.Sc.4 COSC','0',0,'full'),('135001493','Shirley','Birchill','451-589-3285','B.Sc.4 COSC','1',1,'full'),('135490765','Retha','Keeves','266-986-1108','B.Sc.4 CHEM','0',0,'part'),('142531308','Cristy','Udie','522-232-2502','B.Sc.4 CHEM','1',1,'full'),('149514909','Wye','Malt','269-174-2488','B.Sc.4 CHEM','1',0,'full'),('158900905','Pammi','Dalston','791-690-5034','B.Sc.4 COSC','0',0,'full'),('164399245','Lindsey','Michelle','161-448-9685','B.Sc.3 COSC','1',1,'part'),('165816940','Lezley','Dodworth','165-259-5954','B.Sc.3 COSC','0',1,'part'),('172525649','Etti','Bradnick','470-871-4905','B.Sc.4 COSC','1',1,'full'),('172681923','Nickolas','Faldo','873-908-2033','B.Sc.4 COSC','0',0,'full'),('177252841','Ermengarde','Camm','660-560-2504','B.Sc.4 COSC','1',0,'full'),('179918802','Martyn','Minifie','433-467-6818','B.Sc.4 COSC','1',1,'full'),('184072320','Ralf','Auden','691-458-3117','B.Sc.3 COSC','1',0,'part'),('184225365','Vinson','Trathen','372-653-9784','B.Sc.4 COSC','1',1,'full'),('190801497','Solomon','Tyce','512-308-5927','B.Sc.4 CHEM','1',0,'full');
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student_grades`
--

DROP TABLE IF EXISTS `student_grades`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `student_grades` (
  `student_id` varchar(45) NOT NULL,
  `course_id` varchar(45) NOT NULL,
  `section_id` varchar(45) NOT NULL,
  `grade` varchar(45) NOT NULL,
  PRIMARY KEY (`student_id`,`course_id`,`section_id`,`grade`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student_grades`
--

LOCK TABLES `student_grades` WRITE;
/*!40000 ALTER TABLE `student_grades` DISABLE KEYS */;
INSERT INTO `student_grades` VALUES ('126293548','COSC1046','1','87'),('135001493','COSC1046','1','23'),('142531308','COSC1046','2','Dropped'),('149514909','COSC1047','1','51'),('149514909','COSC1047','1','72'),('149514909','COSC2006','3','90'),('164399245','COSC1047','1','94'),('172525649','COSC2006','3','83'),('177252841','COSC2006','3','88'),('179918802','COSC2006','3','65'),('184072320','COSC1047','1','95'),('184225365','COSC1047','1','86'),('190801497','COSC2006','3','95');
/*!40000 ALTER TABLE `student_grades` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-11-25 22:11:50
