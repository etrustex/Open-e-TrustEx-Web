--------------------------------------------------------
--  Constraints for Table ETX_WEB_BUSINESS
--------------------------------------------------------

  ALTER TABLE `ETX_WEB_BUSINESS` MODIFY `BUS_ACTIVE_STATE` TINYINT(1) NOT NULL;
  ALTER TABLE `ETX_WEB_BUSINESS` MODIFY `BUS_CREATED_ON` datetime NOT NULL;
  ALTER TABLE `ETX_WEB_BUSINESS` MODIFY `BUS_CREATED_BY` bigint(19) NOT NULL;
  ALTER TABLE `ETX_WEB_BUSINESS` MODIFY `BUS_NAME` varchar(50) NOT NULL;

--------------------------------------------------------
--  Constraints for Table ETX_WEB_BUSINESS_USER_CONFIG
--------------------------------------------------------

  ALTER TABLE `ETX_WEB_BUSINESS_USER_CONFIG` MODIFY `BUC_ACTIVE_STATE` TINYINT(1) NOT NULL;
  ALTER TABLE `ETX_WEB_BUSINESS_USER_CONFIG` MODIFY `BUC_CREATED_ON` datetime NOT NULL;
  ALTER TABLE `ETX_WEB_BUSINESS_USER_CONFIG` MODIFY `BUC_CREATED_BY` bigint(19) NOT NULL;
  ALTER TABLE `ETX_WEB_BUSINESS_USER_CONFIG` MODIFY `BUC_NOTIFICATIONS_ENABLED` TINYINT(1) NOT NULL;
  ALTER TABLE `ETX_WEB_BUSINESS_USER_CONFIG` MODIFY `BUC_NAME` text NOT NULL;
  ALTER TABLE `ETX_WEB_BUSINESS_USER_CONFIG` MODIFY `BUS_ID` bigint(19) NOT NULL;
  ALTER TABLE `ETX_WEB_BUSINESS_USER_CONFIG` MODIFY `USR_ID` bigint(19) NOT NULL;

--------------------------------------------------------
--  Constraints for Table ETX_WEB_MESSAGE
--------------------------------------------------------

  ALTER TABLE `ETX_WEB_MESSAGE` MODIFY `MSG_REMOTE_PARTY_NAME` varchar(64) NOT NULL;
  ALTER TABLE `ETX_WEB_MESSAGE` MODIFY `MSG_LOCAL_PARTY_ID` bigint(19) NOT NULL;
  ALTER TABLE `ETX_WEB_MESSAGE` MODIFY `MSG_ACTIVE_STATE` TINYINT(1) NOT NULL;
  ALTER TABLE `ETX_WEB_MESSAGE` MODIFY `MSG_CREATED_ON` datetime NOT NULL;

--------------------------------------------------------
--  Constraints for Table ETX_WEB_METADATA
--------------------------------------------------------

  ALTER TABLE `ETX_WEB_METADATA` MODIFY `MTD_STATE` varchar(50) NOT NULL;

--------------------------------------------------------
--  Constraints for Table ETX_WEB_NOTIFICATION
--------------------------------------------------------

  ALTER TABLE `ETX_WEB_NOTIFICATION` MODIFY `NTF_CREATED_ON` datetime NOT NULL;

--------------------------------------------------------
--  Constraints for Table ETX_WEB_PARTY
--------------------------------------------------------

  ALTER TABLE `ETX_WEB_PARTY` MODIFY `PAR_ACTIVE_STATE` TINYINT(1) NOT NULL;
  ALTER TABLE `ETX_WEB_PARTY` MODIFY `PAR_NOTIFICATIONS_ENABLED` TINYINT(1) NOT NULL;
  ALTER TABLE `ETX_WEB_PARTY` MODIFY `PAR_CREATED_ON` datetime NOT NULL;
  ALTER TABLE `ETX_WEB_PARTY` MODIFY `PAR_CREATED_BY` bigint(19) NOT NULL;
  ALTER TABLE `ETX_WEB_PARTY` MODIFY `BUS_ID` bigint(19) NOT NULL;
  ALTER TABLE `ETX_WEB_PARTY` MODIFY `PAR_DISPLAY_NAME` varchar(255) NOT NULL;
  ALTER TABLE `ETX_WEB_PARTY` MODIFY `PAR_NODE_NAME` varchar(64) NOT NULL;
  ALTER TABLE `ETX_WEB_PARTY` MODIFY `PAR_NODE_PASS` varchar(255) NOT NULL;
  ALTER TABLE `ETX_WEB_PARTY` MODIFY `PAR_NODE_USR` varchar(255) NOT NULL;

--------------------------------------------------------
--  Constraints for Table ETX_WEB_PRIVILEGE
--------------------------------------------------------

  ALTER TABLE `ETX_WEB_PRIVILEGE` MODIFY `PRV_TYPE` varchar(50) NOT NULL;
  ALTER TABLE `ETX_WEB_PRIVILEGE` MODIFY `PRV_NAME` varchar(255) NOT NULL;

--------------------------------------------------------
--  Constraints for Table ETX_WEB_ROLE
--------------------------------------------------------

  ALTER TABLE `ETX_WEB_ROLE` MODIFY `ROL_SCOPE` varchar(50) NOT NULL;
  ALTER TABLE `ETX_WEB_ROLE` MODIFY `ROL_TYPE` varchar(50) NOT NULL;
  ALTER TABLE `ETX_WEB_ROLE` MODIFY `ROL_NAME` varchar(255) NOT NULL;

--------------------------------------------------------
--  Constraints for Table ETX_WEB_ROLE_PRIVILEGE
--------------------------------------------------------

  ALTER TABLE `ETX_WEB_ROLE_PRIVILEGE` MODIFY `PRV_TYPE` varchar(50) NOT NULL;
  ALTER TABLE `ETX_WEB_ROLE_PRIVILEGE` MODIFY `ROL_TYPE` varchar(50) NOT NULL;

--------------------------------------------------------
--  Constraints for Table ETX_WEB_USER
--------------------------------------------------------

  ALTER TABLE `ETX_WEB_USER` MODIFY `USR_LOGIN` varchar(255) NOT NULL;
  ALTER TABLE `ETX_WEB_USER` MODIFY `USR_ACTIVE_STATE` TINYINT(1) NOT NULL;
  ALTER TABLE `ETX_WEB_USER` MODIFY `USR_CREATED_ON` datetime NOT NULL;
  ALTER TABLE `ETX_WEB_USER` MODIFY `USR_CREATED_BY` bigint(19) NOT NULL;

--------------------------------------------------------
--  Constraints for Table ETX_WEB_USER_ROLE
--------------------------------------------------------

  ALTER TABLE `ETX_WEB_USER_ROLE` MODIFY `URO_ACTIVE_STATE` TINYINT(1) NOT NULL;
  ALTER TABLE `ETX_WEB_USER_ROLE` MODIFY `URO_CREATED_ON` datetime NOT NULL;
  ALTER TABLE `ETX_WEB_USER_ROLE` MODIFY `URO_CREATED_BY` bigint(19) NOT NULL;
  ALTER TABLE `ETX_WEB_USER_ROLE` MODIFY `ROL_TYPE` varchar(50) NOT NULL;
  ALTER TABLE `ETX_WEB_USER_ROLE` MODIFY `USR_ID` bigint(19) NOT NULL;

--------------------------------------------------------
--  Constraints for Table ETX_WEB_MESSAGE_STATUS
--------------------------------------------------------

  ALTER TABLE `ETX_WEB_MESSAGE_STATUS` MODIFY `MST_UUID` varchar(255) NOT NULL;
  ALTER TABLE `ETX_WEB_MESSAGE_STATUS` MODIFY `MST_STATE` varchar(50) NOT NULL;
  ALTER TABLE `ETX_WEB_MESSAGE_STATUS` MODIFY `MST_STATUS` varchar(50) NOT NULL;
  ALTER TABLE `ETX_WEB_MESSAGE_STATUS` MODIFY `MST_LOGIN` varchar(255) NOT NULL;
  ALTER TABLE `ETX_WEB_MESSAGE_STATUS` MODIFY `MST_CREATED_ON` datetime NOT NULL;

--------------------------------------------------------
--  Ref Constraints for Table ETX_WEB_MESSAGE_STATUS
--------------------------------------------------------
  ALTER TABLE `ETX_WEB_MESSAGE_STATUS` ADD CONSTRAINT `ETX_WEB_MESSAGE_STATUS_FK_MSG` FOREIGN KEY (`MSG_ID`)
	  REFERENCES `ETX_WEB_MESSAGE` (`MSG_ID`);

--------------------------------------------------------
--  Ref Constraints for Table ETX_WEB_ATTACHMENT
--------------------------------------------------------

  ALTER TABLE `ETX_WEB_ATTACHMENT` ADD CONSTRAINT `FK_ATT_MSG_ID` FOREIGN KEY (`MSG_ID`)
	  REFERENCES `ETX_WEB_MESSAGE` (`MSG_ID`);

--------------------------------------------------------
--  Ref Constraints for Table ETX_WEB_BUSINESS_USER_CONFIG
--------------------------------------------------------

  ALTER TABLE `ETX_WEB_BUSINESS_USER_CONFIG` ADD CONSTRAINT `ETX_WEB_BUC_FK_BUSINESS` FOREIGN KEY (`BUS_ID`)
	  REFERENCES `ETX_WEB_BUSINESS` (`BUS_ID`);
  ALTER TABLE `ETX_WEB_BUSINESS_USER_CONFIG` ADD CONSTRAINT `ETX_WEB_BUC_FK_USER` FOREIGN KEY (`USR_ID`)
	  REFERENCES `ETX_WEB_USER` (`USR_ID`);

--------------------------------------------------------
--  Ref Constraints for Table ETX_WEB_LABEL_TRANSLATION
--------------------------------------------------------

  ALTER TABLE `ETX_WEB_LABEL_TRANSLATION` ADD CONSTRAINT `FK_LTR_LNG_ID` FOREIGN KEY (`LNG_ID`)
	  REFERENCES `ETX_WEB_LANG` (`LNG_ID`);

--------------------------------------------------------
--  Ref Constraints for Table ETX_WEB_MESSAGE
--------------------------------------------------------

  ALTER TABLE `ETX_WEB_MESSAGE` ADD CONSTRAINT `ETX_WEB_MESSAGE_FK_PARTY` FOREIGN KEY (`MSG_LOCAL_PARTY_ID`)
	  REFERENCES `ETX_WEB_PARTY` (`PAR_ID`);

--------------------------------------------------------
--  Ref Constraints for Table ETX_WEB_MESSAGE_READ
--------------------------------------------------------

  ALTER TABLE `ETX_WEB_MESSAGE_READ` ADD CONSTRAINT `FK_MSR_MSG_ID` FOREIGN KEY (`MSG_ID`)
	  REFERENCES `ETX_WEB_MESSAGE` (`MSG_ID`);
  ALTER TABLE `ETX_WEB_MESSAGE_READ` ADD CONSTRAINT `FK_MSR_USR_ID` FOREIGN KEY (`USR_ID`)
	  REFERENCES `ETX_WEB_USER` (`USR_ID`);

--------------------------------------------------------
--  Ref Constraints for Table ETX_WEB_METADATA
--------------------------------------------------------

  ALTER TABLE `ETX_WEB_METADATA` ADD CONSTRAINT `FK_MTD_MSG_ID` FOREIGN KEY (`MSG_ID`)
	  REFERENCES `ETX_WEB_MESSAGE` (`MSG_ID`);

--------------------------------------------------------
--  Ref Constraints for Table ETX_WEB_NOTIFICATION
--------------------------------------------------------

  ALTER TABLE `ETX_WEB_NOTIFICATION` ADD CONSTRAINT `NOTIFICATION_FK_MESSAGE` FOREIGN KEY (`MSG_ID`)
	  REFERENCES `ETX_WEB_MESSAGE` (`MSG_ID`);
  ALTER TABLE `ETX_WEB_NOTIFICATION` ADD CONSTRAINT `NOTIFICATION_FK_PARTY` FOREIGN KEY (`PAR_ID`)
	  REFERENCES `ETX_WEB_PARTY` (`PAR_ID`);
  ALTER TABLE `ETX_WEB_NOTIFICATION` ADD CONSTRAINT `NOTIFICATION_FK_BUC` FOREIGN KEY (`BUC_ID`)
	  REFERENCES `ETX_WEB_BUSINESS_USER_CONFIG` (`BUC_ID`);

--------------------------------------------------------
--  Ref Constraints for Table ETX_WEB_PARTY
--------------------------------------------------------

  ALTER TABLE `ETX_WEB_PARTY` ADD CONSTRAINT `ETX_WEB_PARTY_FK_BUSINESS` FOREIGN KEY (`BUS_ID`)
	  REFERENCES `ETX_WEB_BUSINESS` (`BUS_ID`);

--------------------------------------------------------
--  Ref Constraints for Table ETX_WEB_ROLE_PRIVILEGE
--------------------------------------------------------

  ALTER TABLE `ETX_WEB_ROLE_PRIVILEGE` ADD CONSTRAINT `ETX_WEB_ROLE_PRV_FK_PRV` FOREIGN KEY (`PRV_TYPE`)
	  REFERENCES `ETX_WEB_PRIVILEGE` (`PRV_TYPE`);
  ALTER TABLE `ETX_WEB_ROLE_PRIVILEGE` ADD CONSTRAINT `ETX_WEB_ROLE_PRV_FK_ROLE` FOREIGN KEY (`ROL_TYPE`)
	  REFERENCES `ETX_WEB_ROLE` (`ROL_TYPE`);

--------------------------------------------------------
--  Ref Constraints for Table ETX_WEB_USER_ROLE
--------------------------------------------------------

  ALTER TABLE `ETX_WEB_USER_ROLE` ADD CONSTRAINT `ETX_WEB_USER_ROLE_FK_BUSINESS` FOREIGN KEY (`BUS_ID`)
	  REFERENCES `ETX_WEB_BUSINESS` (`BUS_ID`);
  ALTER TABLE `ETX_WEB_USER_ROLE` ADD CONSTRAINT `ETX_WEB_USER_ROLE_FK_PARTY` FOREIGN KEY (`PAR_ID`)
	  REFERENCES `ETX_WEB_PARTY` (`PAR_ID`);
  ALTER TABLE `ETX_WEB_USER_ROLE` ADD CONSTRAINT `ETX_WEB_USER_ROLE_FK_ROLE` FOREIGN KEY (`ROL_TYPE`)
	  REFERENCES `ETX_WEB_ROLE` (`ROL_TYPE`);
  ALTER TABLE `ETX_WEB_USER_ROLE` ADD CONSTRAINT `ETX_WEB_USER_ROLE_FK_USER` FOREIGN KEY (`USR_ID`)
	  REFERENCES `ETX_WEB_USER` (`USR_ID`);

--------------------------------------------------------
--  Constraints for Table ETX_WEB_BUSINESS_CONFIG
--------------------------------------------------------

  ALTER TABLE `ETX_WEB_BUSINESS_CONFIG` MODIFY `BCG_ID` bigint(19) NOT NULL;
  ALTER TABLE `ETX_WEB_BUSINESS_CONFIG` MODIFY `BUS_ID` bigint(19) NOT NULL;
  ALTER TABLE `ETX_WEB_BUSINESS_CONFIG` ADD CONSTRAINT `ETX_WEB_BCG_ID_FK` FOREIGN KEY (`BUS_ID`)
	  REFERENCES `ETX_WEB_BUSINESS` (`BUS_ID`);
  ALTER TABLE `ETX_WEB_BUSINESS_CONFIG` ADD UNIQUE `ETX_WEB_BCG_ID_NAME` (`BUS_ID`, `BCG_PROPERTY_NAME`);