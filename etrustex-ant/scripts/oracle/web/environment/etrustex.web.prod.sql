-- ========================================
-- Sets environment-dependent DB parameters
-- environment: PROD
-- ========================================
-- SYSTEMS
INSERT INTO ETX_WEB_SYSTEM (SYS_ID, SYS_USERNAME, SYS_PASSWORD, SYS_COMMENT)
VALUES (1, 'ETX-TEST-NP', 'I65874qjs5j5669', 'eTrustEx Web platform')
/

-- PARTIES
INSERT INTO ETX_WEB_PARTY (PAR_ID, PAR_EMAIL, PAR_INSTITUTION, PAR_NAME, PAR_REF_ID, PAR_CAN_SEND, PAR_STANDARD_VIEW)
VALUES (1, null, 'EGREFFE', 'EGREFFE-APP-PARTY', 'EGREFFE-APP-PARTY', 0, 1, null)
/

INSERT INTO ETX_WEB_PARTY (PAR_ID, PAR_EMAIL, PAR_INSTITUTION, PAR_NAME, PAR_REF_ID, PAR_CAN_SEND, PAR_STANDARD_VIEW, SYS_ID)
VALUES (2, 'foo@europa.eu', 'EGREFFE', 'ETX-TEST-NP-PARTY', 'ETX-TEST-NP-PARTY', 0, 1, 1)
/

-- USERS
INSERT INTO ETX_WEB_USER (USR_ID,USR_LOGIN,USR_NAME,PAR_ID) VALUES (ETX_WEB_USRSEQ.nextval,'cholaar','Armen CHOLAKIAN',2)
/

-- CONFIG
INSERT INTO ETX_WEB_CONFIG (CFG_ID,CFG_PROP_NAME,CFG_PROP_VALUE) values (1,'application_url','https://webgate.ec.europa.eu/e-trustex/')
/
INSERT INTO ETX_WEB_CONFIG (CFG_ID,CFG_PROP_NAME,CFG_PROP_VALUE) values (2,'etrustex.doc.wrapper.service.uri','https://webgate.ec.europa.eu/e-trustexnode/DocumentWrapper-2.0')
/
INSERT INTO ETX_WEB_CONFIG (CFG_ID,CFG_PROP_NAME,CFG_PROP_VALUE) values (3,'etrustex.doc.bundle.service.uri','https://webgate.ec.europa.eu/e-trustexnode/DocumentBundle-2.0')
/
INSERT INTO ETX_WEB_CONFIG (CFG_ID,CFG_PROP_NAME,CFG_PROP_VALUE) values (4,'etrustex.doc.agreement.service.uri','https://webgate.ec.europa.eu/e-trustexnode/RetrieveInterchangeAgreementsRequest-2.0')
/
INSERT INTO ETX_WEB_CONFIG (CFG_ID,CFG_PROP_NAME,CFG_PROP_VALUE) values (5,'edma.app.party','EDMA-APP-PARTY')
/
INSERT INTO ETX_WEB_CONFIG (CFG_ID,CFG_PROP_NAME,CFG_PROP_VALUE) values (6,'etrustex.doc.wrapper.service.internal.uri','https://digtrusp.cc.cec.eu.int:1122/etrustex/external/DocumentWrapper-2.0')
/
INSERT INTO ETX_WEB_CONFIG (CFG_ID,CFG_PROP_NAME,CFG_PROP_VALUE) values (7,'etx.node.services.RetrieveRequestService.uri','https://webgate.ec.europa.eu/e-trustexnode/RetrieveRequest-2.0')
/

