ALTER TABLE ETX_WEB_PARTY ADD PAR_CONSUME_NODE_MSG TINYINT(1)
/

UPDATE ETX_WEB_PARTY SET PAR_CONSUME_NODE_MSG = 1
/

ALTER TABLE ETX_WEB_PARTY MODIFY PAR_CONSUME_NODE_MSG TINYINT(1) DEFAULT 1 NOT NULL
/

ALTER TABLE ETX_WEB_PARTY MODIFY PAR_DISPLAY_NAME varchar(75)
/