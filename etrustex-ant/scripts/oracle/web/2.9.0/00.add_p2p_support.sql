INSERT INTO ETX_WEB_CONFIG (CFG_ID, CFG_PROP_NAME, CFG_PROP_VALUE)
VALUES ((SELECT NVL(MAX(CFG_ID), 0) + 1 from ETX_WEB_CONFIG), 'etx.node.security.p2p.enabled', 'false')
/