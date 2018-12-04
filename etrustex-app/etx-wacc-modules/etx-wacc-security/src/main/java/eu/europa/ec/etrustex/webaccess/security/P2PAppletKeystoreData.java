package eu.europa.ec.etrustex.webaccess.security;

public class P2PAppletKeystoreData {
    private final String keystoreFile;
    private final String keystorePassword;
    private final String keystorePrivatePassword;
    private final String keystoreAlias;

    public P2PAppletKeystoreData(String keystoreFile, String keystorePassword, String keystorePrivatePassword, String keystoreAlias) {
        this.keystoreFile = keystoreFile;
        this.keystorePassword = keystorePassword;
        this.keystorePrivatePassword = keystorePrivatePassword;
        this.keystoreAlias = keystoreAlias;
    }

    public String getKeystoreFile() {
        return keystoreFile;
    }

    public String getKeystorePassword() {
        return keystorePassword;
    }

    public String getKeystorePrivatePassword() {
        return keystorePrivatePassword;
    }

    public String getKeystoreAlias() {
        return keystoreAlias;
    }
}
