--------------------------------------------------------
--  DDL for Index PK_ATT_ID
--------------------------------------------------------

  CREATE UNIQUE INDEX `PK_ATT_ID` ON `ETX_WEB_ATTACHMENT` (`ATT_ID`);
--------------------------------------------------------
--  DDL for Index IX_WEB__ATT_MSG_ID
--------------------------------------------------------

  CREATE INDEX `IX_WEB__ATT_MSG_ID` ON `ETX_WEB_ATTACHMENT` (`MSG_ID`);
--------------------------------------------------------
--  DDL for Index IX_WEB_ATT_ATT_REF_ID
--------------------------------------------------------

  CREATE INDEX `IX_WEB_ATT_ATT_REF_ID` ON `ETX_WEB_ATTACHMENT` (`ATT_WRAPPER_ID`);
--------------------------------------------------------
--  DDL for Index PK_BUS_ID
--------------------------------------------------------

  CREATE UNIQUE INDEX `PK_BUS_ID` ON `ETX_WEB_BUSINESS` (`BUS_ID`);
--------------------------------------------------------
--  DDL for Index PK_BUC_ID
--------------------------------------------------------

  CREATE UNIQUE INDEX `PK_BUC_ID` ON `ETX_WEB_BUSINESS_USER_CONFIG` (`BUC_ID`);
--------------------------------------------------------
--  DDL for Index ETX_WEB_BUC_USR_IX
--------------------------------------------------------

  CREATE INDEX `ETX_WEB_BUC_USR_IX` ON `ETX_WEB_BUSINESS_USER_CONFIG` (`USR_ID`);
--------------------------------------------------------
--  DDL for Index ETX_WEB_BUC_BUS_IX
--------------------------------------------------------

  CREATE INDEX `ETX_WEB_BUC_BUS_IX` ON `ETX_WEB_BUSINESS_USER_CONFIG` (`BUS_ID`);
--------------------------------------------------------
--  DDL for Index PK_CFG_ID
--------------------------------------------------------

  CREATE UNIQUE INDEX `PK_CFG_ID` ON `ETX_WEB_CONFIG` (`CFG_ID`);
--------------------------------------------------------
--  DDL for Index PK_LTR_ID
--------------------------------------------------------

  CREATE UNIQUE INDEX `PK_LTR_ID` ON `ETX_WEB_LABEL_TRANSLATION` (`LTR_ID`);
--------------------------------------------------------
--  DDL for Index IX_LTR_LNG_ID
--------------------------------------------------------

  CREATE INDEX `IX_LTR_LNG_ID` ON `ETX_WEB_LABEL_TRANSLATION` (`LNG_ID`);
--------------------------------------------------------
--  DDL for Index PK_LNG_ID
--------------------------------------------------------

  CREATE UNIQUE INDEX `PK_LNG_ID` ON `ETX_WEB_LANG` (`LNG_ID`);
--------------------------------------------------------
--  DDL for Index IX_LNG_LNG_CODE
--------------------------------------------------------

  CREATE INDEX `IX_LNG_LNG_CODE` ON `ETX_WEB_LANG` (`LNG_CODE`);
--------------------------------------------------------
--  DDL for Index ETX_WEB_MESSAGE_LOCAL_PARTY_IX
--------------------------------------------------------

  CREATE INDEX `ETX_WEB_MESSAGE_LOCAL_PARTY_IX` ON `ETX_WEB_MESSAGE` (`MSG_LOCAL_PARTY_ID`);
--------------------------------------------------------
--  DDL for Index PK_MSG_ID
--------------------------------------------------------

  CREATE UNIQUE INDEX `PK_MSG_ID` ON `ETX_WEB_MESSAGE` (`MSG_ID`);
--------------------------------------------------------
--  DDL for Index IX_MSG_MSG_STATE
--------------------------------------------------------

  CREATE INDEX `IX_MSG_MSG_STATE` ON `ETX_WEB_MESSAGE` (`MSG_STATE`);
--------------------------------------------------------
--  DDL for Index IX_MSG_MSG_REF_ID
--------------------------------------------------------

  CREATE INDEX `IX_MSG_MSG_REF_ID` ON `ETX_WEB_MESSAGE` (`MSG_REF_ID`);
--------------------------------------------------------
--  DDL for Index PK_MSR_ID
--------------------------------------------------------

  CREATE UNIQUE INDEX `PK_MSR_ID` ON `ETX_WEB_MESSAGE_READ` (`MSR_ID`);
--------------------------------------------------------
--  DDL for Index IX_WEB_MSR_MSG_ID
--------------------------------------------------------

  CREATE INDEX `IX_WEB_MSR_MSG_ID` ON `ETX_WEB_MESSAGE_READ` (`MSG_ID`);
--------------------------------------------------------
--  DDL for Index IX_WEB_MSR_USR_ID
--------------------------------------------------------

  CREATE INDEX `IX_WEB_MSR_USR_ID` ON `ETX_WEB_MESSAGE_READ` (`USR_ID`);
--------------------------------------------------------
--  DDL for Index PK_MTD_ID
--------------------------------------------------------

  CREATE UNIQUE INDEX `PK_MTD_ID` ON `ETX_WEB_METADATA` (`MTD_ID`);
--------------------------------------------------------
--  DDL for Index IX_WEB_MTD_MSG_ID
--------------------------------------------------------

  CREATE UNIQUE INDEX `IX_WEB_MTD_MSG_ID` ON `ETX_WEB_METADATA` (`MSG_ID`);
--------------------------------------------------------
--  DDL for Index SYS_C00116909
--------------------------------------------------------

  CREATE UNIQUE INDEX `SYS_C00116909` ON `ETX_WEB_NOTIFICATION` (`NTF_ID`);
--------------------------------------------------------
--  DDL for Index SYS_C00108815

CREATE UNIQUE INDEX `SYS_C00108815` ON `ETX_WEB_PARTY` (`PAR_NODE_NAME`);
--------------------------------------------------------
--  DDL for Index PK_PAR_ID
--------------------------------------------------------

  CREATE UNIQUE INDEX `PK_PAR_ID` ON `ETX_WEB_PARTY` (`PAR_ID`);
--------------------------------------------------------
--  DDL for Index ETX_WEB_PARTY_BUS_IX
--------------------------------------------------------

  CREATE INDEX `ETX_WEB_PARTY_BUS_IX` ON `ETX_WEB_PARTY` (`BUS_ID`);
--------------------------------------------------------
--  DDL for Index PK_PRV_TYPE
--------------------------------------------------------

  CREATE UNIQUE INDEX `PK_PRV_TYPE` ON `ETX_WEB_PRIVILEGE` (`PRV_TYPE`);
--------------------------------------------------------
--  DDL for Index PK_ROL_TYPE
--------------------------------------------------------

  CREATE UNIQUE INDEX `PK_ROL_TYPE` ON `ETX_WEB_ROLE` (`ROL_TYPE`);
--------------------------------------------------------
--  DDL for Index ETX_WEB_ROLE_PRV_ROL_IX
--------------------------------------------------------

  CREATE INDEX `ETX_WEB_ROLE_PRV_ROL_IX` ON `ETX_WEB_ROLE_PRIVILEGE` (`ROL_TYPE`);
--------------------------------------------------------
--  DDL for Index ETX_WEB_ROLE_PRV_PRV_IX
--------------------------------------------------------

  CREATE INDEX `ETX_WEB_ROLE_PRV_PRV_IX` ON `ETX_WEB_ROLE_PRIVILEGE` (`PRV_TYPE`);
--------------------------------------------------------
--  DDL for Index PK_USR_ID
--------------------------------------------------------

  CREATE UNIQUE INDEX `PK_USR_ID` ON `ETX_WEB_USER` (`USR_ID`);
--------------------------------------------------------
--  DDL for Index IX_USR_USR_LOGIN
--------------------------------------------------------

  CREATE UNIQUE INDEX `IX_USR_USR_LOGIN` ON `ETX_WEB_USER` (`USR_LOGIN`);
--------------------------------------------------------
--  DDL for Index PK_URO_ID
--------------------------------------------------------

  CREATE UNIQUE INDEX `PK_URO_ID` ON `ETX_WEB_USER_ROLE` (`URO_ID`);
--------------------------------------------------------
--  DDL for Index ETX_WEB_USER_ROLE_USR_IX
--------------------------------------------------------

  CREATE INDEX `ETX_WEB_USER_ROLE_USR_IX` ON `ETX_WEB_USER_ROLE` (`USR_ID`);
--------------------------------------------------------
--  DDL for Index ETX_WEB_USER_ROLE_ROL_IX
--------------------------------------------------------

  CREATE INDEX `ETX_WEB_USER_ROLE_ROL_IX` ON `ETX_WEB_USER_ROLE` (`ROL_TYPE`);
--------------------------------------------------------
--  DDL for Index ETX_WEB_USER_ROLE_PAR_IX
--------------------------------------------------------

  CREATE INDEX `ETX_WEB_USER_ROLE_PAR_IX` ON `ETX_WEB_USER_ROLE` (`PAR_ID`);
--------------------------------------------------------
--  DDL for Index ETX_WEB_USER_ROLE_BUS_IX
--------------------------------------------------------

  CREATE INDEX `ETX_WEB_USER_ROLE_BUS_IX` ON `ETX_WEB_USER_ROLE` (`BUS_ID`);


  CREATE UNIQUE INDEX `PK_MST_ID` ON `ETX_WEB_MESSAGE_STATUS` (`MST_ID`);

  CREATE INDEX `ETX_WEB_MESSAGE_STATUS_MSG_IX` ON `ETX_WEB_MESSAGE_STATUS` (`MSG_ID`);

--------------------------------------------------------
--  DDL for Index PK_BCG_ID
--------------------------------------------------------

  CREATE UNIQUE INDEX `PK_BCG_ID` ON `ETX_WEB_BUSINESS_CONFIG` (`BCG_ID`);
