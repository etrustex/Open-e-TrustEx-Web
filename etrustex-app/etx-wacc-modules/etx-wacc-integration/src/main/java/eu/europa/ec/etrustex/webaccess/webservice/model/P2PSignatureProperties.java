package eu.europa.ec.etrustex.webaccess.webservice.model;

import java.io.Serializable;

public class P2PSignatureProperties implements Serializable {
    private String keystoreAlias;
    private String keystorePassword;
    private String keystorePrivatePassword;
    private String keystoreFile;
    private String truststorePassword;
    private String truststoreFile;

    public P2PSignatureProperties(String keystoreAlias, String keystorePassword, String keystorePrivatePassword, String keystoreFile,
                                  String truststorePassword, String truststoreFile) {
        this.keystoreAlias = keystoreAlias;
        this.keystorePassword = keystorePassword;
        this.keystorePrivatePassword = keystorePrivatePassword;
        this.keystoreFile = keystoreFile;
        this.truststorePassword = truststorePassword;
        this.truststoreFile = truststoreFile;
    }

    public String getKeystoreAlias() {
        return keystoreAlias;
    }

    public String getKeystorePassword() {
        return keystorePassword;
    }

    public String getKeystorePrivatePassword() {
        return keystorePrivatePassword;
    }

    public String getKeystoreFile() {
        return keystoreFile;
    }

    public String getTruststorePassword() {
        return truststorePassword;
    }

    public String getTruststoreFile() {
        return truststoreFile;
    }
}
