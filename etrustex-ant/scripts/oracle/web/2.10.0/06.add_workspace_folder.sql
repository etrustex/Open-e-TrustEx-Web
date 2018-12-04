INSERT INTO ETX_WEB_CONFIG (CFG_ID, CFG_PROP_NAME, CFG_PROP_VALUE)
SELECT MAX(CFG_ID) + 1,  'etx.web.workspace.path', '_workspace/etx_web'
FROM ETX_WEB_CONFIG
WHERE 
NOT exists(select * from ETX_WEB_CONFIG where CFG_PROP_NAME = 'etx.web.workspace.path')
HAVING max(CFG_ID) is not null
/