update ETX_WEB_BUSINESS_CONFIG
set BCG_PROPERTY_VALUE = 'generic' 
where BCG_PROPERTY_NAME = 'etx.business.custom.view.name'
and ((BCG_PROPERTY_VALUE != 'egreffe' and BCG_PROPERTY_VALUE != 'edma') or BCG_PROPERTY_VALUE is null);
/
