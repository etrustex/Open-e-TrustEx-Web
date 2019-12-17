/**
 *
 */
package eu.europa.ec.etrustex.webaccess.webservice.model;

import java.io.Serializable;

/**
 * @author apladap
 */
public class ConnectionConfiguration implements Serializable {

    private static final long serialVersionUID = 1496534853664050384L;

    private String documentWrapperServiceUrl;
    private String documentBundleServiceUrl;
    private String interchangeAgreementUrl;
    private String retrieveRequestUrl;
    private String applicationResponseUrl;
    private int chunkSize = 32 * 1024;
    boolean webserviceLoggingEnabled;
    private boolean p2pEnabled;

    public String getDocumentWrapperServiceUrl() {
        return documentWrapperServiceUrl;
    }

    public void setDocumentWrapperServiceUrl(String documentWrapperServiceUrl) {
        this.documentWrapperServiceUrl = documentWrapperServiceUrl;
    }

    public String getDocumentBundleServiceUrl() {
        return documentBundleServiceUrl;
    }

    public void setDocumentBundleServiceUrl(String documentBundleServiceUrl) {
        this.documentBundleServiceUrl = documentBundleServiceUrl;
    }

    public String getInterchangeAgreementUrl() {
        return interchangeAgreementUrl;
    }

    public void setInterchangeAgreementUrl(String interchangeAgreementUrl) {
        this.interchangeAgreementUrl = interchangeAgreementUrl;
    }

    public String getRetrieveRequestUrl() {
        return retrieveRequestUrl;
    }

    public void setRetrieveRequestUrl(String retrieveRequestUrl) {
        this.retrieveRequestUrl = retrieveRequestUrl;
    }

    public String getApplicationResponseUrl() {
        return applicationResponseUrl;
    }

    public void setApplicationResponseUrl(String applicationResponseUrl) {
        this.applicationResponseUrl = applicationResponseUrl;
    }

    public int getChunkSize() {
        return chunkSize;
    }

    public boolean isWebserviceLoggingEnabled() {
        return webserviceLoggingEnabled;
    }

    public void setWebserviceLoggingEnabled(boolean webserviceLoggingEnabled) {
        this.webserviceLoggingEnabled = webserviceLoggingEnabled;
    }

    public boolean isP2pEnabled() {
        return p2pEnabled;
    }

    public void setP2pEnabled(boolean p2pEnabled) {
        this.p2pEnabled = p2pEnabled;
    }
}
