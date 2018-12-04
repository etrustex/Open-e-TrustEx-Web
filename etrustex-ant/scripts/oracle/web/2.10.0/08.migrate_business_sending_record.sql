DELETE FROM ETX_WEB_BUSINESS_CONFIG
WHERE BCG_PROPERTY_NAME = 'etx.business.receiver.node.party.name' and BCG_PROPERTY_VALUE is null;
/
INSERT into ETX_WEB_BUSINESS_CONFIG (BCG_ID, BUS_ID, BCG_PROPERTY_NAME, BCG_PROPERTY_VALUE)
SELECT ETX_WEB_BCGSEQ.nextval, BUS_ID,'etx.business.sending.enabled','true'
FROM ETX_WEB_BUSINESS_CONFIG
WHERE BCG_PROPERTY_NAME = 'etx.business.receiver.node.party.name'
and NOT exists (select BCG_ID from ETX_WEB_BUSINESS_CONFIG where BCG_PROPERTY_NAME = 'etx.business.sending.enabled');
/
