INSERT INTO ETX_WEB_CONFIG (CFG_ID, CFG_PROP_NAME, CFG_PROP_VALUE)
  SELECT MAX(CFG_ID) + 1,  'etrustex.announcements.content', null
    FROM ETX_WEB_CONFIG
    WHERE NOT exists(select * from ETX_WEB_CONFIG where CFG_PROP_NAME = 'etrustex.announcements.content')
    HAVING max(CFG_ID) is not null
/
ALTER TABLE ETX_WEB_CONFIG
MODIFY (CFG_PROP_VALUE VARCHAR2(4000 CHAR) )
/