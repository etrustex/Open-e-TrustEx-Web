ALTER TABLE ETX_WEB_BUSINESS_USER_CONFIG ADD (
  BUC_STS_NOTIF_ENABLED NUMBER(1),
  BUC_STS_NOTIF_EMAIL VARCHAR2(64 CHAR)
)
/

UPDATE ETX_WEB_BUSINESS_USER_CONFIG SET BUC_STS_NOTIF_ENABLED = 0
/

ALTER TABLE ETX_WEB_BUSINESS_USER_CONFIG MODIFY (BUC_STS_NOTIF_ENABLED NUMBER(1) NOT NULL)
/

ALTER TABLE ETX_WEB_PARTY ADD (
  PAR_STS_NOTIF_ENABLED NUMBER(1),
  PAR_STS_NOTIF_EMAIL VARCHAR2(64 CHAR)
)
/

UPDATE ETX_WEB_PARTY SET PAR_STS_NOTIF_ENABLED = 0
/

ALTER TABLE ETX_WEB_PARTY MODIFY (PAR_STS_NOTIF_ENABLED NUMBER(1) NOT NULL)
/

ALTER TABLE ETX_WEB_NOTIFICATION ADD MST_ID NUMBER(19, 0)
/
