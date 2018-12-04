package eu.europa.ec.etrustex.webaccess.business.config;

import xades4j.utils.StringUtils;

/**
 * The configuration table for GUI web application runtime
 *
 * @author dzierma
 */
public class Configuration {

    /**
     * The constant representing Node's Document Wrapper Service URI
     */
    protected static final String CONFIG_DOC_WRAPPER_SERVICE_URI = "etrustex.doc.wrapper.service.uri";
    public static final String CONFIG_NODE_DOCUMENT_BUNDLE_SERVICE_URI = "etrustex.doc.bundle.service.uri";
    public static final String CONFIG_NODE_INTERCHANGEAGREEMENT_SERVICE_URI = "etrustex.doc.agreement.service.uri";
    public static final String CONFIG_NODE_APP_RESPONSE_SERVICE_URI = "etx.node.services.application.response.url";
    public static final String CONFIG_NODE_SECURITY_P2P_ENABLED = "etx.node.security.p2p.enabled";
    protected static final String CONFIG_WEBSERVICE_LOGGING_ENABLED = "webservice.logging.enabled";
    protected static final String CONFIG_APPLICATION_URL = "application_url";
    protected static final String CONFIG_RETENTION_POLICY_WEEKS = "etrustex.retentionPolicy.weeks";
    protected static final String CONFIG_RETENTION_POLICY_WARNINGS_BEFORE_DAYS = "etrustex.retentionPolicy.warning.before.days";
    protected static final String CONFIG_RETRIEVE_REQUEST_URI = "etx.node.services.RetrieveRequestService.uri";
    protected static final String CONFIG_LOOKUP_FAILS_DEFAULT_ENABLED = "etrustex.translations.missingLabel.defaultsToEnglish";
    protected static final String CONFIG_WORKSPACE_ROOT_PATH = "etx.web.workspace.path";
    protected static final String CONFIG_WORKSPACE_WS_TTL_IN_DAYS = "etx.web.workspace.ws.ttl.days";
    protected static final String CONFIG_ANNOUNCEMENTS_CONTENT = "etrustex.announcements.content";
    protected static final String CONFIG_MAIL_DISTRIBUTION = "etrustex.mail.distribution";

    private String documentWrapperServiceURI;
    private String documentBundleServiceURI;
    private String interchangeAgreementURI;
    private String retrieveRequestURI;
    private String applicationResponseURI;
    private boolean nodeSecurityP2pEnabled;
    private String applicationUrl;
    private boolean webserviceLoggingEnabled;
    private int retentionPolicyWeeks;
    private int retentionPolicyWarningBeforeDays;
    private boolean lookupFailsDefaultEnabled;
    private String workspaceRootPath;
    private int workspaceWSTimeToLive;
    private String announcementsContent;
    private String configMailDistribution;

    /**
     * @return Document Wrapper Service URI
     */
    public String getDocumentWrapperServiceURI() {
        return documentWrapperServiceURI;
    }

    public String getDocumentBundleServiceURI() {
        return documentBundleServiceURI;
    }

    public void setDocumentBundleServiceURI(String documentBundleServiceURI) {
        this.documentBundleServiceURI = documentBundleServiceURI;
    }

    public void setDocumentWrapperServiceURI(String documentWrapperServiceURI) {
        this.documentWrapperServiceURI = documentWrapperServiceURI;
    }

    public String getInterchangeAgreementURI() {
        return interchangeAgreementURI;
    }

    public void setInterchangeAgreementURI(String interchangeAgreementURI) {
        this.interchangeAgreementURI = interchangeAgreementURI;
    }

    public String getRetrieveRequestURI() {
        return retrieveRequestURI;
    }

    public void setRetrieveRequestURI(String retrieveRequestURI) {
        this.retrieveRequestURI = retrieveRequestURI;
    }

    public String getApplicationResponseURI() {
        return applicationResponseURI;
    }

    public void setApplicationResponseURI(String applicationResponseURI) {
        this.applicationResponseURI = applicationResponseURI;
    }

    public boolean isNodeSecurityP2pEnabled() {
        return nodeSecurityP2pEnabled;
    }

    public void setNodeSecurityP2pEnabled(boolean nodeSecurityP2pEnabled) {
        this.nodeSecurityP2pEnabled = nodeSecurityP2pEnabled;
    }

    public String getApplicationUrl() {
        return applicationUrl;
    }

    public void setApplicationUrl(String applicationUrl) {
        this.applicationUrl = applicationUrl;
    }

    public boolean isWebserviceLoggingEnabled() {
        return webserviceLoggingEnabled;
    }

    public void setWebserviceLoggingEnabled(boolean webserviceLoggingEnabled) {
        this.webserviceLoggingEnabled = webserviceLoggingEnabled;
    }

    public int getRetentionPolicyWeeks() {
        return retentionPolicyWeeks;
    }

    public void setRetentionPolicyWeeks(int retentionPolicyWeeks) {
        this.retentionPolicyWeeks = retentionPolicyWeeks;
    }

    public int getRetentionPolicyWarningBeforeDays() {
        return retentionPolicyWarningBeforeDays;
    }

    public void setRetentionPolicyWarningBeforeDays(int retentionPolicyWarningBeforeDays) {
        this.retentionPolicyWarningBeforeDays = retentionPolicyWarningBeforeDays;
    }

    public boolean isLookupFailsDefaultEnabled() {
        return lookupFailsDefaultEnabled;
    }

    public void setLookupFailsDefaultEnabled(boolean lookupFailsDefaultEnabled) {
        this.lookupFailsDefaultEnabled = lookupFailsDefaultEnabled;
    }

    public String getWorkspaceRootPath() {
        return workspaceRootPath;
    }

    public void setWorkspaceRootPath(String workspaceRootPath) {
        this.workspaceRootPath = workspaceRootPath;
    }

    public int getWorkspaceWSTimeToLive() {
        return workspaceWSTimeToLive;
    }

    public void setWorkspaceWSTimeToLive(int workspaceWSTimeToLive) {
        this.workspaceWSTimeToLive = workspaceWSTimeToLive;
    }

    public String getAnnouncementsContent() {
        return announcementsContent;
    }

    public void setAnnouncementsContent(String announcementsContent) {
        this.announcementsContent = announcementsContent;
    }

    public String getConfigMailDistribution() {
        return configMailDistribution;
    }

    public void setConfigMailDistribution(String configMailDistribution) {
        this.configMailDistribution = configMailDistribution;
    }
}
