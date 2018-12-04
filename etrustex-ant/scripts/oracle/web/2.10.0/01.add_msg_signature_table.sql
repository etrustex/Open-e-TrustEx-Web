--------------------------------------------------------
--  DDL for Table ETX_WEB_MESSAGE_SIG
--------------------------------------------------------
CREATE TABLE "ETX_WEB_MESSAGE_SIG"
( "MSS_ID" NUMBER(19,0) NOT NULL,
  "MSG_ID" NUMBER(19,0) NOT NULL,
  "MSG_SIGNATURE" CLOB NOT NULL,
  "MSG_SIGNED_DATA" CLOB NOT NULL,
  "MSG_CERTIFICATE" CLOB NOT NULL,
  "MSG_CERTIFICATE_EXPIRED" NUMBER(1,0) DEFAULT 1,
  "MSG_SIGNATURE_VALID" NUMBER(1,0) DEFAULT 0,

  CONSTRAINT "PK_MSS_ID" PRIMARY KEY ("MSS_ID"),
  CONSTRAINT "UN_MSG_ID" UNIQUE ("MSG_ID")
)
/

--------------------------------------------------------
--  DDL for Sequence ETX_WEB_MSGSIGSEQ
--------------------------------------------------------
CREATE SEQUENCE  "ETX_WEB_MSGSIGSEQ"  MINVALUE 1 NOMAXVALUE INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE
/
