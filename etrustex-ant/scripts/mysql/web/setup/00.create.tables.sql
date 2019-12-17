--------------------------------------------------------
--  DDL for Table ETX_WEB_ATTACHMENT
--------------------------------------------------------

CREATE TABLE `ETX_WEB_ATTACHMENT` (
  `ATT_ID` bigint(19) NOT NULL AUTO_INCREMENT,
  `ATT_CHECKSUM` tinyblob,
  `ATT_CHECKSUM_METHOD` varchar(10),
  `ATT_MIME_TYPE` varchar(255),
  `ATT_FILE_NAME` varchar(255),
  `ATT_FILE_SIZE` bigint(19),
  `ATT_TYPE` varchar(50),
  `ATT_WRAPPER_ID` varchar(255),
  `MSG_ID` bigint(19),
  PRIMARY KEY (`ATT_ID`)
);

--------------------------------------------------------
--  DDL for Table ETX_WEB_BUSINESS
--------------------------------------------------------

CREATE TABLE `ETX_WEB_BUSINESS` (
  `BUS_ID` bigint(19) NOT NULL AUTO_INCREMENT,
  `BUS_NAME` varchar(50),
  `BUS_DESCRIPTION` varchar(255),
  `BUS_CREATED_BY` bigint(19),
  `BUS_CREATED_ON` datetime,
  `BUS_UPDATED_BY` bigint(19),
  `BUS_UPDATED_ON` datetime,
  `BUS_ACTIVE_STATE` TINYINT(1),
  PRIMARY KEY (`BUS_ID`)
);

--------------------------------------------------------
--  DDL for Table ETX_WEB_BUSINESS_USER_CONFIG
--------------------------------------------------------

CREATE TABLE `ETX_WEB_BUSINESS_USER_CONFIG` (
  `BUC_ID` bigint(19) NOT NULL AUTO_INCREMENT,
  `USR_ID` bigint(19),
  `BUS_ID` bigint(19),
  `BUC_NAME` varchar(64),
  `BUC_EMAIL` varchar(64),
  `BUC_NOTIFICATIONS_ENABLED` TINYINT(1) DEFAULT 0,
  `BUC_CREATED_BY` bigint(19),
  `BUC_CREATED_ON` datetime,
  `BUC_UPDATED_BY` bigint(19),
  `BUC_UPDATED_ON` datetime,
  `BUC_ACTIVE_STATE` TINYINT(1),
  PRIMARY KEY (`BUC_ID`)
);

--------------------------------------------------------
--  DDL for Table ETX_WEB_CONFIG
--------------------------------------------------------

CREATE TABLE `ETX_WEB_CONFIG` (
  `CFG_ID` bigint(19) NOT NULL AUTO_INCREMENT,
  `CFG_PROP_NAME` varchar(255),
  `CFG_PROP_VALUE` varchar(255),
  PRIMARY KEY (`CFG_ID`)
);

--------------------------------------------------------
--  DDL for Table ETX_WEB_LABEL_TRANSLATION
--------------------------------------------------------

CREATE TABLE `ETX_WEB_LABEL_TRANSLATION` (
  `LTR_ID` bigint(19) NOT NULL AUTO_INCREMENT,
  `LNG_ID` bigint(19),
  `LTR_KEY` varchar(255),
  `LTR_MESSAGE` text,
  `LTR_SCREEN_ID` bigint(19),
  `LTR_SCREEN_POSITION_X` bigint(10),
  `LTR_SCREEN_POSITION_Y` bigint(10),
  `LTR_EXPORT_DATE` datetime,
  `LTR_EXPORT_CHECKSUM` varchar(32),
  `LTR_IMPORT_DATE` datetime,
  `LTR_IMPORT_CHECKSUM` varchar(32),
  `LTR_CREATION_DATE` datetime DEFAULT CURRENT_TIMESTAMP,
  `LTR_MODIFICATION_DATE` datetime,
  `LTR_VERSION` bigint(22) DEFAULT '0',
  PRIMARY KEY (`LTR_ID`)
);

--------------------------------------------------------
--  DDL for Table ETX_WEB_LANG
--------------------------------------------------------

CREATE TABLE `ETX_WEB_LANG` (
  `LNG_ID` bigint(19) NOT NULL AUTO_INCREMENT,
  `LNG_CODE` varchar(255),
  `LNG_NAME` varchar(255),
  PRIMARY KEY (`LNG_ID`)
);

--------------------------------------------------------
--  DDL for Table ETX_WEB_MESSAGE
--------------------------------------------------------

CREATE TABLE `ETX_WEB_MESSAGE` (
  `MSG_ID` bigint(19) NOT NULL AUTO_INCREMENT,
  `MSG_CONTENT` text,
  `MSG_ISSUE_DATE` datetime,
  `MSG_REF_ID` varchar(255),
  `MSG_SUBJECT` varchar(255),
  `MSG_STATE` varchar(50),
  `MSG_BUNDLE_ID` varchar(255),
  `MSG_SIGNATURE` longtext,
  `MSG_SIGNED_DATA` longtext,
  `MSG_LOCAL_PARTY_ID` bigint(19),
  `MSG_REMOTE_PARTY_NAME` varchar(64),
  `MSG_CREATED_BY` bigint(19),
  `MSG_CREATED_ON` datetime,
  `MSG_UPDATED_BY` bigint(19),
  `MSG_UPDATED_ON` datetime,
  `MSG_ACTIVE_STATE` TINYINT(1),
  PRIMARY KEY (`MSG_ID`)
);

--------------------------------------------------------
--  DDL for Table ETX_WEB_MESSAGE_READ
--------------------------------------------------------

CREATE TABLE `ETX_WEB_MESSAGE_READ` (
  `MSR_ID` bigint(19) NOT NULL AUTO_INCREMENT,
  `MSG_ID` bigint(19),
  `USR_ID` bigint(19),
  PRIMARY KEY (`MSR_ID`)
);

--------------------------------------------------------
--  DDL for Table ETX_WEB_METADATA
--------------------------------------------------------

CREATE TABLE `ETX_WEB_METADATA` (
  `MTD_CONTENT` longtext,
  `MTD_ID` bigint(19) NOT NULL AUTO_INCREMENT,
  `MSG_ID` bigint(19),
  `MTD_STATE` varchar(50),
  PRIMARY KEY (`MTD_ID`)
);

--------------------------------------------------------
--  DDL for Table ETX_WEB_NOTIFICATION
--------------------------------------------------------

CREATE TABLE `ETX_WEB_NOTIFICATION` (
  `NTF_ID` bigint(19) NOT NULL AUTO_INCREMENT,
  `MSG_ID` bigint(19),
  `BUC_ID` bigint(19),
  `PAR_ID` bigint(19),
  `NTF_EMAIL` varchar(64),
  `NTF_TYPE` varchar(50),
  `NTF_STATE` varchar(50),
  `NTF_CREATED_ON` datetime,
  `NTF_UPDATED_ON` datetime,
  PRIMARY KEY (`NTF_ID`)
);

--------------------------------------------------------
--  DDL for Table ETX_WEB_PARTY
--------------------------------------------------------

CREATE TABLE `ETX_WEB_PARTY` (
  `PAR_ID` bigint(19) NOT NULL AUTO_INCREMENT,
  `PAR_EMAIL` varchar(64),
  `PAR_CREATED_ON` datetime DEFAULT CURRENT_TIMESTAMP,
  `PAR_NODE_NAME` varchar(64),
  `PAR_NODE_USR` varchar(255),
  `PAR_NODE_PASS` varchar(255),
  `PAR_DISPLAY_NAME` varchar(255),
  `BUS_ID` bigint(19),
  `PAR_NOTIFICATIONS_ENABLED` TINYINT(1) DEFAULT 0,
  `PAR_CREATED_BY` bigint(19),
  `PAR_UPDATED_BY` bigint(19),
  `PAR_UPDATED_ON` datetime,
  `PAR_ACTIVE_STATE` TINYINT(1),
  PRIMARY KEY (`PAR_ID`)
);

--------------------------------------------------------
--  DDL for Table ETX_WEB_PRIVILEGE
--------------------------------------------------------

CREATE TABLE `ETX_WEB_PRIVILEGE` (
  `PRV_NAME` varchar(255),
  `PRV_TYPE` varchar(50),
  PRIMARY KEY (`PRV_TYPE`)
);

--------------------------------------------------------
--  DDL for Table ETX_WEB_ROLE
--------------------------------------------------------

CREATE TABLE `ETX_WEB_ROLE` (
  `ROL_NAME` varchar(255),
  `ROL_TYPE` varchar(50),
  `ROL_SCOPE` varchar(50),
  PRIMARY KEY (`ROL_TYPE`)
);

--------------------------------------------------------
--  DDL for Table ETX_WEB_ROLE_PRIVILEGE
--------------------------------------------------------

CREATE TABLE `ETX_WEB_ROLE_PRIVILEGE` (
  `ROL_TYPE` varchar(50),
  `PRV_TYPE` varchar(50)
);

--------------------------------------------------------
--  DDL for Table ETX_WEB_USER
--------------------------------------------------------

CREATE TABLE `ETX_WEB_USER` (
  `USR_ID` bigint(19) NOT NULL AUTO_INCREMENT,
  `USR_LOGIN` varchar(255),
  `USR_NAME` varchar(255),
  `USR_CREATED_BY` bigint(19),
  `USR_CREATED_ON` datetime,
  `USR_UPDATED_BY` bigint(19),
  `USR_UPDATED_ON` datetime,
  `USR_ACTIVE_STATE` TINYINT(1),
  PRIMARY KEY (`USR_ID`)
);

--------------------------------------------------------
--  DDL for Table ETX_WEB_USER_ROLE
--------------------------------------------------------

CREATE TABLE `ETX_WEB_USER_ROLE` (
  `URO_ID` bigint(19) NOT NULL AUTO_INCREMENT,
  `USR_ID` bigint(19),
  `ROL_TYPE` varchar(50),
  `PAR_ID` bigint(19),
  `BUS_ID` bigint(19),
  `URO_CREATED_BY` bigint(19),
  `URO_CREATED_ON` datetime,
  `URO_UPDATED_BY` bigint(19),
  `URO_UPDATED_ON` datetime,
  `URO_ACTIVE_STATE` TINYINT(1),
  PRIMARY KEY (`URO_ID`)
);

--------------------------------------------------------
--  DDL for Table ETX_WEB_MESSAGE_STATUS
--------------------------------------------------------

CREATE TABLE `ETX_WEB_MESSAGE_STATUS` (
`MST_ID` bigint(19) NOT NULL AUTO_INCREMENT,
`MSG_ID` bigint(19),
`MST_UUID` varchar(255),
`MST_STATE` varchar(50),
`MST_STATUS` varchar(50),
`MST_STATUS_CODE` varchar(50),
`MST_LOGIN` varchar(255),
`MST_CREATED_ON` datetime,
`MST_UPDATED_ON` datetime,
PRIMARY KEY (`MST_ID`)
);

--------------------------------------------------------
--  DDL for Table ETX_WEB_BUSINESS_CONFIG
--------------------------------------------------------

CREATE TABLE `ETX_WEB_BUSINESS_CONFIG` (
`BCG_ID` bigint(19) NOT NULL AUTO_INCREMENT,
`BUS_ID` bigint(19) NOT NULL,
`BCG_PROPERTY_NAME` varchar(50),
`BCG_PROPERTY_VALUE` varchar(250),
PRIMARY KEY (`BCG_ID`)
);
