-- ------------------------------------------------------
--  DDL for Table ETX_WEB_MESSAGE_SIG
-- ------------------------------------------------------
CREATE TABLE `ETX_WEB_MESSAGE_SIG`
( `MSS_ID` bigint(19) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `MSG_ID` bigint(19) NOT NULL UNIQUE,
  `MSG_SIGNATURE` longtext NOT NULL,
  `MSG_SIGNED_DATA` longtext NOT NULL,
  `MSG_CERTIFICATE` longtext NOT NULL,
  `MSG_CERTIFICATE_EXPIRED` TINYINT(1) DEFAULT 1,
  `MSG_SIGNATURE_VALID` TINYINT(1) DEFAULT 0
);
