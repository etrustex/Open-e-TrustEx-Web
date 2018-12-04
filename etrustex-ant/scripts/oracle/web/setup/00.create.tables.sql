--------------------------------------------------------
--  DDL for Table ETX_WEB_ATTACHMENT
--------------------------------------------------------

CREATE TABLE "ETX_WEB_ATTACHMENT"
(	"ATT_ID" NUMBER(19,0),
"ATT_CHECKSUM" RAW(255),
"ATT_CHECKSUM_METHOD" VARCHAR2(10 CHAR),
"ATT_MIME_TYPE" VARCHAR2(255 CHAR),
"ATT_FILE_NAME" VARCHAR2(255 CHAR),
"ATT_FILE_SIZE" NUMBER(19,0),
"ATT_TYPE" VARCHAR2(50 CHAR),
"ATT_WRAPPER_ID" VARCHAR2(255 CHAR),
"MSG_ID" NUMBER(19,0)
)
/

--------------------------------------------------------
--  DDL for Table ETX_WEB_BUSINESS
--------------------------------------------------------

CREATE TABLE "ETX_WEB_BUSINESS"
(	"BUS_ID" NUMBER(19,0),
"BUS_NAME" VARCHAR2(50 CHAR),
"BUS_DESCRIPTION" VARCHAR2(255 CHAR),
"BUS_CREATED_BY" NUMBER(19,0),
"BUS_CREATED_ON" DATE,
"BUS_UPDATED_BY" NUMBER(19,0),
"BUS_UPDATED_ON" DATE,
"BUS_ACTIVE_STATE" NUMBER(1,0)
)
/

--------------------------------------------------------
--  DDL for Table ETX_WEB_BUSINESS_USER_CONFIG
--------------------------------------------------------

CREATE TABLE "ETX_WEB_BUSINESS_USER_CONFIG"
(	"BUC_ID" NUMBER(19,0),
"USR_ID" NUMBER(19,0),
"BUS_ID" NUMBER(19,0),
"BUC_NAME" VARCHAR2(64 CHAR),
"BUC_EMAIL" VARCHAR2(64 CHAR),
"BUC_NOTIFICATIONS_ENABLED" NUMBER(1,0) DEFAULT 0,
"BUC_CREATED_BY" NUMBER(19,0),
"BUC_CREATED_ON" DATE,
"BUC_UPDATED_BY" NUMBER(19,0),
"BUC_UPDATED_ON" DATE,
"BUC_ACTIVE_STATE" NUMBER(1,0)
)
/

--------------------------------------------------------
--  DDL for Table ETX_WEB_CONFIG
--------------------------------------------------------

CREATE TABLE "ETX_WEB_CONFIG"
(	"CFG_ID" NUMBER(19,0),
"CFG_PROP_NAME" VARCHAR2(255 CHAR),
"CFG_PROP_VALUE" VARCHAR2(255 CHAR)
)
/

--------------------------------------------------------
--  DDL for Table ETX_WEB_LABEL_TRANSLATION
--------------------------------------------------------

CREATE TABLE "ETX_WEB_LABEL_TRANSLATION"
(	"LTR_ID" NUMBER(19,0),
"LNG_ID" NUMBER(19,0),
"LTR_KEY" VARCHAR2(255 BYTE),
"LTR_MESSAGE" VARCHAR2(1000 CHAR),
"LTR_SCREEN_ID" NUMBER(19,0),
"LTR_SCREEN_POSITION_X" NUMBER(10,0),
"LTR_SCREEN_POSITION_Y" NUMBER(10,0),
"LTR_EXPORT_DATE" DATE,
"LTR_EXPORT_CHECKSUM" CHAR(32 CHAR),
"LTR_IMPORT_DATE" DATE,
"LTR_IMPORT_CHECKSUM" CHAR(32 CHAR),
"LTR_CREATION_DATE" DATE DEFAULT SYSDATE,
"LTR_MODIFICATION_DATE" DATE,
"LTR_VERSION" NUMBER(*,0) DEFAULT 0
)
/

--------------------------------------------------------
--  DDL for Table ETX_WEB_LANG
--------------------------------------------------------

CREATE TABLE "ETX_WEB_LANG"
(	"LNG_ID" NUMBER(19,0),
"LNG_CODE" VARCHAR2(255 CHAR),
"LNG_NAME" VARCHAR2(255 CHAR)
)
/

--------------------------------------------------------
--  DDL for Table ETX_WEB_MESSAGE
--------------------------------------------------------

CREATE TABLE "ETX_WEB_MESSAGE"
(	"MSG_ID" NUMBER(19,0),
"MSG_CONTENT" VARCHAR2(4000 CHAR),
"MSG_ISSUE_DATE" DATE,
"MSG_REF_ID" VARCHAR2(255 CHAR),
"MSG_SUBJECT" VARCHAR2(255 CHAR),
"MSG_STATE" VARCHAR2(50 CHAR),
"MSG_BUNDLE_ID" VARCHAR2(255 CHAR),
"MSG_SIGNATURE" CLOB,
"MSG_SIGNED_DATA" CLOB,
"MSG_LOCAL_PARTY_ID" NUMBER(19,0),
"MSG_REMOTE_PARTY_NAME" VARCHAR2(64 CHAR),
"MSG_CREATED_BY" NUMBER(19,0),
"MSG_CREATED_ON" DATE,
"MSG_UPDATED_BY" NUMBER(19,0),
"MSG_UPDATED_ON" DATE,
"MSG_ACTIVE_STATE" NUMBER(1,0)
)
/

--------------------------------------------------------
--  DDL for Table ETX_WEB_MESSAGE_READ
--------------------------------------------------------

CREATE TABLE "ETX_WEB_MESSAGE_READ"
(	"MSR_ID" NUMBER(19,0),
"MSG_ID" NUMBER(19,0),
"USR_ID" NUMBER(19,0)
)
/

--------------------------------------------------------
--  DDL for Table ETX_WEB_METADATA
--------------------------------------------------------

CREATE TABLE "ETX_WEB_METADATA"
(	"MTD_CONTENT" CLOB,
"MTD_ID" NUMBER(19,0),
"MSG_ID" NUMBER(19,0),
"MTD_STATE" VARCHAR2(50 CHAR)
)
/

--------------------------------------------------------
--  DDL for Table ETX_WEB_NOTIFICATION
--------------------------------------------------------

CREATE TABLE "ETX_WEB_NOTIFICATION"
(	"NTF_ID" NUMBER(19,0),
"MSG_ID" NUMBER(19,0),
"BUC_ID" NUMBER(19,0),
"PAR_ID" NUMBER(19,0),
"NTF_EMAIL" VARCHAR2(64 CHAR),
"NTF_TYPE" VARCHAR2(50 CHAR),
"NTF_STATE" VARCHAR2(50 CHAR),
"NTF_CREATED_ON" DATE,
"NTF_UPDATED_ON" DATE
)
/

--------------------------------------------------------
--  DDL for Table ETX_WEB_PARTY
--------------------------------------------------------

CREATE TABLE "ETX_WEB_PARTY"
(	"PAR_ID" NUMBER(19,0),
"PAR_EMAIL" VARCHAR2(64 CHAR),
"PAR_CREATED_ON" DATE DEFAULT SYSDATE,
"PAR_NODE_NAME" VARCHAR2(64 CHAR),
"PAR_NODE_USR" VARCHAR2(255 CHAR),
"PAR_NODE_PASS" VARCHAR2(255 CHAR),
"PAR_DISPLAY_NAME" VARCHAR2(255 CHAR),
"BUS_ID" NUMBER(19,0),
"PAR_NOTIFICATIONS_ENABLED" NUMBER(1,0) DEFAULT 0,
"PAR_CREATED_BY" NUMBER(19,0),
"PAR_UPDATED_BY" NUMBER(19,0),
"PAR_UPDATED_ON" DATE,
"PAR_ACTIVE_STATE" NUMBER(1,0)
)
/

--------------------------------------------------------
--  DDL for Table ETX_WEB_PRIVILEGE
--------------------------------------------------------

CREATE TABLE "ETX_WEB_PRIVILEGE"
(	"PRV_NAME" VARCHAR2(255 CHAR),
"PRV_TYPE" VARCHAR2(50 CHAR)
)
/

--------------------------------------------------------
--  DDL for Table ETX_WEB_ROLE
--------------------------------------------------------

CREATE TABLE "ETX_WEB_ROLE"
(	"ROL_NAME" VARCHAR2(255 CHAR),
"ROL_TYPE" VARCHAR2(50 CHAR),
"ROL_SCOPE" VARCHAR2(50 CHAR)
)
/

--------------------------------------------------------
--  DDL for Table ETX_WEB_ROLE_PRIVILEGE
--------------------------------------------------------

CREATE TABLE "ETX_WEB_ROLE_PRIVILEGE"
(	"ROL_TYPE" VARCHAR2(50 CHAR),
"PRV_TYPE" VARCHAR2(50 CHAR)
)
/

--------------------------------------------------------
--  DDL for Table ETX_WEB_USER
--------------------------------------------------------

CREATE TABLE "ETX_WEB_USER"
(	"USR_ID" NUMBER(19,0),
"USR_LOGIN" VARCHAR2(255 CHAR),
"USR_NAME" VARCHAR2(255 CHAR),
"USR_CREATED_BY" NUMBER(19,0),
"USR_CREATED_ON" DATE,
"USR_UPDATED_BY" NUMBER(19,0),
"USR_UPDATED_ON" DATE,
"USR_ACTIVE_STATE" NUMBER(1,0)
)
/

--------------------------------------------------------
--  DDL for Table ETX_WEB_USER_ROLE
--------------------------------------------------------

CREATE TABLE "ETX_WEB_USER_ROLE"
(	"URO_ID" NUMBER(19,0),
"USR_ID" NUMBER(19,0),
"ROL_TYPE" VARCHAR2(50 CHAR),
"PAR_ID" NUMBER(19,0),
"BUS_ID" NUMBER(19,0),
"URO_CREATED_BY" NUMBER(19,0),
"URO_CREATED_ON" DATE,
"URO_UPDATED_BY" NUMBER(19,0),
"URO_UPDATED_ON" DATE,
"URO_ACTIVE_STATE" NUMBER(1,0)
)
/

--------------------------------------------------------
--  DDL for Table ETX_WEB_MESSAGE_STATUS
--------------------------------------------------------

CREATE TABLE "ETX_WEB_MESSAGE_STATUS"
(	"MST_ID" NUMBER(19,0),
"MSG_ID" NUMBER(19,0),
"MST_UUID" VARCHAR2(255 CHAR),
"MST_STATE" VARCHAR2(50 CHAR),
"MST_STATUS" VARCHAR2(50 CHAR),
"MST_STATUS_CODE" VARCHAR2(50 CHAR),
"MST_LOGIN" VARCHAR2(255 CHAR),
"MST_CREATED_ON" DATE,
"MST_UPDATED_ON" DATE
)
/

--------------------------------------------------------
--  DDL for Table ETX_WEB_BUSINESS_CONFIG
--------------------------------------------------------

CREATE TABLE "ETX_WEB_BUSINESS_CONFIG"
(
"BCG_ID" NUMBER(19,0),
"BUS_ID" NUMBER(19,0),
"BCG_PROPERTY_NAME" VARCHAR2(50 CHAR),
"BCG_PROPERTY_VALUE" VARCHAR2(250 CHAR)
)
/