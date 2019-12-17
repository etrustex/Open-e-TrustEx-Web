--
-- Table structure for table `_sequences`
--

DROP TABLE IF EXISTS `_sequences`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `_sequences` (
  `name` varchar(70) NOT NULL,
  `next` int(11) NOT NULL,
  `inc` int(11) NOT NULL,
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `_sequences`
--

LOCK TABLES `_sequences` WRITE;
/*!40000 ALTER TABLE `_sequences` DISABLE KEYS */;
INSERT INTO `_sequences` VALUES ('`DBOBJECTID_SEQUENCE`',1,50),('`ETX_ADT_ATT_SQ`',883454,1),('`ETX_ADT_CONFIG_SQ`',13,1),('`ETX_ADT_ERR_SQ`',10781,1),('`ETX_ADT_KSE_SQ`',1,1),('`ETX_ADT_MSG_SQ`',156191,1),('`ETX_ADT_MSJ_SQ`',49377,1),('`ETX_ADT_PAR_SQ`',1,1),('`ETX_ADT_SCG_SQ`',1,1),('`ETX_ADT_SKS_SQ`',1,1),('`ETX_ADT_SYS_SQ`',1,1),('`ETX_ADT_USR_SQ`',1,1),('`ETX_WEB_ATTSEQ`',567089,1),('`ETX_WEB_ATTSTSEQ`',5000,1),('`ETX_WEB_BCGSEQ`',206,1),('`ETX_WEB_BUCSEQ`',4263,1),('`ETX_WEB_BUSSEQ`',34,1),('`ETX_WEB_LNGSEQ`',5000,1),('`ETX_WEB_METADATASEQ`',65401,1),('`ETX_WEB_MSGSEQ`',68575,1),('`ETX_WEB_MSGSIGSEQ`',4711,1),('`ETX_WEB_MSG_READSEQ`',18351,1),('`ETX_WEB_MSTSEQ`',9416,1),('`ETX_WEB_NOTIFICATIONSEQ`',43244,1),('`ETX_WEB_PARTYICASEQ`',741,1),('`ETX_WEB_PRTSEQ`',22264,1),('`ETX_WEB_UROSEQ`',4868,1),('`ETX_WEB_USRSEQ`',8680,1);
/*!40000 ALTER TABLE `_sequences` ENABLE KEYS */;
UNLOCK TABLES;


LOCK TABLES `ETX_WEB_USER` WRITE;
/*!40000 ALTER TABLE `ETX_WEB_USER` DISABLE KEYS */;
INSERT INTO `ETX_WEB_USER` VALUES (-1,1,'2017-09-28 00:00:00',NULL,'admin','Admin',-1,NULL);
/*!40000 ALTER TABLE `ETX_WEB_USER` ENABLE KEYS */;
UNLOCK TABLES;

LOCK TABLES `ETX_WEB_BUSINESS` WRITE;
/*!40000 ALTER TABLE `ETX_WEB_BUSINESS` DISABLE KEYS */;
INSERT INTO `ETX_WEB_BUSINESS` VALUES (3,1,'2013-10-29 00:00:00',NULL,NULL,'GENERIC',-1,NULL);
/*!40000 ALTER TABLE `ETX_WEB_BUSINESS` ENABLE KEYS */;
UNLOCK TABLES;


LOCK TABLES `ETX_WEB_BUSINESS_CONFIG` WRITE;
/*!40000 ALTER TABLE `ETX_WEB_BUSINESS_CONFIG` DISABLE KEYS */;
INSERT INTO `ETX_WEB_BUSINESS_CONFIG` VALUES (7,3,'etx.business.custom.view.name','generic'),(8,3,'etx.business.warn.email.notif.enabled','false'),(41,3,'etx.business.sending.enabled','true'),(193,3,'etx.business.folder.structure.enabled','false');
/*!40000 ALTER TABLE `ETX_WEB_BUSINESS_CONFIG` ENABLE KEYS */;
UNLOCK TABLES;


LOCK TABLES `ETX_WEB_BUSINESS_USER_CONFIG` WRITE;
/*!40000 ALTER TABLE `ETX_WEB_BUSINESS_USER_CONFIG` DISABLE KEYS */;
INSERT INTO `ETX_WEB_BUSINESS_USER_CONFIG` VALUES (4030,1,'2018-04-16 17:45:46',NULL,'','admin',0,'',0,-1,NULL,3,-1),(4031,1,'2018-05-17 17:12:32',NULL,'','guerrpa',0,'',0,-1,NULL,3,-2);
/*!40000 ALTER TABLE `ETX_WEB_BUSINESS_USER_CONFIG` ENABLE KEYS */;
UNLOCK TABLES;

LOCK TABLES `ETX_WEB_CONFIG` WRITE;
/*!40000 ALTER TABLE `ETX_WEB_CONFIG` DISABLE KEYS */;
INSERT INTO `ETX_WEB_CONFIG` VALUES (1,'application_url','http://localhost:8080/e-trustex'),(2,'etrustex.doc.wrapper.service.uri','http://10.5.0.5:8080/etrustex/services/DocumentWrapper-2.0'),(3,'etrustex.doc.bundle.service.uri','http://10.5.0.5:8080/etrustex/services/DocumentBundle-2.0'),(4,'etrustex.doc.agreement.service.uri','http://10.5.0.5:8080/etrustex/services/RetrieveInterchangeAgreementsRequest-2.0'),(5,'webservice.logging.enabled','true'),(6,'etrustex.retentionPolicy.weeks','8'),(7,'etrustex.retentionPolicy.warning.before.days','7'),(8,'etx.node.services.RetrieveRequestService.uri','http://10.5.0.5:8080/etrustex/services/RetrieveRequest-2.0'),(9,'etrustex.translations.missingLabel.defaultsToEnglish','false'),(10,'etx.node.services.application.response.url','http://10.5.0.5:8080/etrustex/services/ApplicationResponse-2.0'),(11,'etx.node.security.p2p.enabled','false'),(13,'etx.web.workspace.path','_workspace/etx_web'),(14,'etrustex.announcements.content',''),(15,'etrustex.mail.distribution','@ec.europa.eu;@ext.ec.europa.eu');
/*!40000 ALTER TABLE `ETX_WEB_CONFIG` ENABLE KEYS */;
UNLOCK TABLES;



LOCK TABLES `ETX_WEB_LANG` WRITE;
/*!40000 ALTER TABLE `ETX_WEB_LANG` DISABLE KEYS */;
INSERT INTO `ETX_WEB_LANG` VALUES (1,'en','English'),(2,'el','Greek'),(3,'ro','Romanian'),(4,'pl','Polish'),(5,'it','Italian'),(6,'N/A','Unknown'),(7,'bg','?????????'),(8,'cs','?eÃ…Â¡tina'),(9,'da','dansk'),(10,'de','Deutsch'),(11,'es','espaÃƒÂ±ol'),(12,'et','eesti keel'),(13,'fi','suomi'),(14,'fr','franÃƒÂ§ais'),(15,'hu','magyar'),(16,'lt','lietuvi? kalba'),(17,'lv','latvieÃ…Â¡u valoda'),(18,'mt','Malti'),(19,'nl','Nederlands'),(20,'pt','portuguÃƒÂªs'),(21,'sk','sloven?ina'),(22,'sl','slovenÃ…Â¡?ina'),(23,'sv','svenska'),(24,'hr','hrvatski jezik');
/*!40000 ALTER TABLE `ETX_WEB_LANG` ENABLE KEYS */;
UNLOCK TABLES;


LOCK TABLES `ETX_WEB_PARTY` WRITE;
/*!40000 ALTER TABLE `ETX_WEB_PARTY` DISABLE KEYS */;
INSERT INTO `ETX_WEB_PARTY` VALUES (1,1,'2017-08-22 00:00:00','2018-04-16 17:45:56',NULL,'OpenSourceGUI1','','OpenSourceGUI1','OpenSourceGUI1','OpenSourceGUI1',0,'',0,1,-1,NULL,3),(2,1,'2017-08-22 00:00:00','2018-04-16 17:46:42',NULL,'OpenSourceGUI2','','OpenSourceGUI2','OpenSourceGUI2','OpenSourceGUI2',0,'',0,1,-1,NULL,3);
/*!40000 ALTER TABLE `ETX_WEB_PARTY` ENABLE KEYS */;
UNLOCK TABLES;


LOCK TABLES `ETX_WEB_PRIVILEGE` WRITE;
/*!40000 ALTER TABLE `ETX_WEB_PRIVILEGE` DISABLE KEYS */;
INSERT INTO `ETX_WEB_PRIVILEGE` VALUES ('CAN_ACCESS_OWN_PARTY_MESSAGES','Can access own party messages'),('CAN_ASSIGN_BUSINESS_SCOPE_ROLES','Can assign business roles'),('CAN_ASSIGN_PARTY_SCOPE_ROLES','Can assign party roles'),('CAN_ASSIGN_SYSTEM_SCOPE_ROLES','Can assign system administrator'),('CAN_DEFINE_BUSINESS','Can define business'),('CAN_MANAGE_APP_CONFIGURATIONS','Can manage app configurations'),('CAN_MANAGE_BUSINESS_CONFIGURATIONS','Can manage business configurations'),('CAN_MANAGE_PARTIES_FOR_BUSINESS','Can manage parties for business');
/*!40000 ALTER TABLE `ETX_WEB_PRIVILEGE` ENABLE KEYS */;
UNLOCK TABLES;


LOCK TABLES `ETX_WEB_ROLE` WRITE;
/*!40000 ALTER TABLE `ETX_WEB_ROLE` DISABLE KEYS */;
INSERT INTO `ETX_WEB_ROLE` VALUES ('BUSINESS_ADMIN','Business Administrator'),('OPERATOR','Operator'),('PARTY_ADMIN','Party Administrator'),('SYSTEM_ADMIN','System Administrator');
/*!40000 ALTER TABLE `ETX_WEB_ROLE` ENABLE KEYS */;
UNLOCK TABLES;


LOCK TABLES `ETX_WEB_ROLE_PRIVILEGE` WRITE;
/*!40000 ALTER TABLE `ETX_WEB_ROLE_PRIVILEGE` DISABLE KEYS */;
INSERT INTO `ETX_WEB_ROLE_PRIVILEGE` VALUES ('OPERATOR','CAN_ACCESS_OWN_PARTY_MESSAGES'),('PARTY_ADMIN','CAN_ACCESS_OWN_PARTY_MESSAGES'),('BUSINESS_ADMIN','CAN_ASSIGN_BUSINESS_SCOPE_ROLES'),('SYSTEM_ADMIN','CAN_ASSIGN_BUSINESS_SCOPE_ROLES'),('BUSINESS_ADMIN','CAN_ASSIGN_PARTY_SCOPE_ROLES'),('PARTY_ADMIN','CAN_ASSIGN_PARTY_SCOPE_ROLES'),('SYSTEM_ADMIN','CAN_ASSIGN_PARTY_SCOPE_ROLES'),('SYSTEM_ADMIN','CAN_ASSIGN_SYSTEM_SCOPE_ROLES'),('SYSTEM_ADMIN','CAN_DEFINE_BUSINESS'),('SYSTEM_ADMIN','CAN_MANAGE_APP_CONFIGURATIONS'),('SYSTEM_ADMIN','CAN_MANAGE_BUSINESS_CONFIGURATIONS'),('SYSTEM_ADMIN','CAN_MANAGE_PARTIES_FOR_BUSINESS');
/*!40000 ALTER TABLE `ETX_WEB_ROLE_PRIVILEGE` ENABLE KEYS */;
UNLOCK TABLES;


LOCK TABLES `ETX_WEB_USER_ROLE` WRITE;
/*!40000 ALTER TABLE `ETX_WEB_USER_ROLE` DISABLE KEYS */;
INSERT INTO `ETX_WEB_USER_ROLE` VALUES (4,1,'2017-08-22 00:00:00',NULL,-1,NULL,3,NULL,'SYSTEM_ADMIN',-1),(4859,0,'2018-05-17 17:12:32','2018-05-17 17:12:51',-1,-1,NULL,1,'PARTY_ADMIN',-2),(4860,1,'2018-05-17 17:13:14',NULL,-1,NULL,NULL,1,'PARTY_ADMIN',-1),(4861,1,'2018-05-17 17:13:30',NULL,-1,NULL,NULL,2,'PARTY_ADMIN',-1),(4862,1,'2018-05-17 17:17:00',NULL,-1,NULL,NULL,1,'OPERATOR',-1),(4863,1,'2018-05-17 17:17:14',NULL,-1,NULL,NULL,2,'OPERATOR',-1);
/*!40000 ALTER TABLE `ETX_WEB_USER_ROLE` ENABLE KEYS */;
UNLOCK TABLES;

