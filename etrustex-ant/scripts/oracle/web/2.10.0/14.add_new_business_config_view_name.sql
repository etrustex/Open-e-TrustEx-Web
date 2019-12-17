INSERT INTO ETX_WEB_BUSINESS_CONFIG (BCG_ID, BUS_ID, BCG_PROPERTY_NAME, BCG_PROPERTY_VALUE)
(select ETX_WEB_BCGSEQ.nextval, bus_id, 'etx.business.custom.view.name', 'generic'
  from etx_web_business 
  where bus_id not in 
  (select bus_id from ETX_WEB_BUSINESS_CONFIG where BCG_PROPERTY_NAME = 'etx.business.custom.view.name')
);

