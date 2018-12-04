--REM INSERTING into ETX_WEB_CONFIG
Insert into ETX_WEB_CONFIG (CFG_ID,CFG_PROP_NAME,CFG_PROP_VALUE)
select MAX(CFG_ID) + 1, 'application_url','https://webgate.development.ec.europa.eu/e-trustex/'
from ETX_WEB_CONFIG
/

Insert into ETX_WEB_CONFIG (CFG_ID,CFG_PROP_NAME,CFG_PROP_VALUE) 
select MAX(CFG_ID) + 1, 'etrustex.doc.wrapper.service.uri','https://webgate.acceptance.ec.europa.eu/e-trustexnode/services/DocumentWrapper-2.0'
from ETX_WEB_CONFIG
/

Insert into ETX_WEB_CONFIG (CFG_ID,CFG_PROP_NAME,CFG_PROP_VALUE) 
select MAX(CFG_ID) + 1, 'etrustex.doc.bundle.service.uri','https://webgate.acceptance.ec.europa.eu/e-trustexnode/services/DocumentBundle-2.0'
from ETX_WEB_CONFIG
/

Insert into ETX_WEB_CONFIG (CFG_ID,CFG_PROP_NAME,CFG_PROP_VALUE)
select MAX(CFG_ID) + 1, 'etrustex.doc.agreement.service.uri','https://webgate.acceptance.ec.europa.eu/e-trustexnode/RetrieveInterchangeAgreementsRequest-2.0'
from ETX_WEB_CONFIG
/

Insert into ETX_WEB_CONFIG (CFG_ID,CFG_PROP_NAME,CFG_PROP_VALUE)
select MAX(CFG_ID) + 1, 'webservice.logging.enabled','true'
from ETX_WEB_CONFIG
/

Insert into ETX_WEB_CONFIG (CFG_ID,CFG_PROP_NAME,CFG_PROP_VALUE)
select MAX(CFG_ID) + 1, 'etrustex.retentionPolicy.weeks','2'
from ETX_WEB_CONFIG
/

Insert into ETX_WEB_CONFIG (CFG_ID,CFG_PROP_NAME,CFG_PROP_VALUE)
select MAX(CFG_ID) + 1, 'etrustex.retentionPolicy.warning.before.days','3'
from ETX_WEB_CONFIG
/

Insert into ETX_WEB_CONFIG (CFG_ID,CFG_PROP_NAME,CFG_PROP_VALUE)
select MAX(CFG_ID) + 1, 'etx.node.services.RetrieveRequestService.uri','https://webgate.acceptance.ec.europa.eu/e-trustexnode/services/RetrieveRequest-2.0'
from ETX_WEB_CONFIG
/

Insert into ETX_WEB_CONFIG (CFG_ID,CFG_PROP_NAME,CFG_PROP_VALUE)
select MAX(CFG_ID) + 1, 'etrustex.translations.missingLabel.defaultsToEnglish','false'
from ETX_WEB_CONFIG
/

Insert into ETX_WEB_CONFIG (CFG_ID,CFG_PROP_NAME,CFG_PROP_VALUE)
select MAX(CFG_ID) + 1, 'etx.node.services.application.response.url','https://webgate.acceptance.ec.europa.eu/e-trustexnode/services/ApplicationResponse-2.0'
from ETX_WEB_CONFIG
/

