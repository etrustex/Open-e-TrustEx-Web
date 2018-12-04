package eu.europa.ec.etrustex.tools;

import eu.europa.ec.etrustex.webaccess.utils.XMLHelper;
import eu.europa.ec.etrustex.webaccess.security.CertificateService;
import eu.europa.ec.etrustex.webaccess.security.EtxSecurityProvider;
import eu.europa.ec.etrustex.webaccess.security.SignatureService;
import org.w3c.dom.Element;

import java.io.FileReader;
import java.security.cert.X509Certificate;

/**
 * @author: micleva
 * @date: 8/9/12 10:46 AM
 * @project: ETX
 */
public class SignatureValidator {

    private static SignatureService signatureService;
    private static CertificateService certificateService;

    static {
        EtxSecurityProvider.init();
        signatureService = EtxSecurityProvider.getInstance().getSignatureService();
        certificateService = EtxSecurityProvider.getInstance().getCertificateService();
    }

    public static void main(String[] args) throws Exception {
        if (args.length != 2) {
            printUsage();
            return;
        }

        String signedDataPath = args[0];
        String signaturePath = args[1];

        String signedData = getFileContent(signedDataPath);
        String signature = getFileContent(signaturePath);

        //normalize the signature data content as per http://www.w3.org/TR/REC-xml/#sec-line-ends) when reading it from local file system
        signedData = signedData.replaceAll(System.getProperty("line.separator"), "\n");

        Element signatureElement = XMLHelper.stringToDocument(signature.trim()).getDocumentElement();

        X509Certificate x509Certificate = certificateService.extractSignatureCertificateFromRootNode(signatureElement);

//        KeyStoreEntry<PrivateKey> entry = loadKey("e:\\Projects\\E-Trust-Ex\\web\\party_certificate\\party_client.p12", "test123", "etx_client_party" );
//        X509Certificate x509Certificate = entry.getX509Certificate();

        boolean isSignatureValid = signatureService.verifySignature(x509Certificate, signedData, signatureElement);

        System.out.println("isSignatureValid = " + isSignatureValid);
    }

    private static String getFileContent(String signedDataPath) throws Exception {
        FileReader in = new FileReader(signedDataPath);
        StringBuilder contents = new StringBuilder();
        char[] buffer = new char[4096];
        int read;
        while ((read = in.read(buffer)) > 0) {
            contents.append(buffer, 0, read);
        }
        return contents.toString();
    }

    private static void printUsage() {
        StringBuilder usage = new StringBuilder();
        usage.append("Usage: ").append(SignatureValidator.class.getName()).append(" <signedDataPath> <signaturePath>");
        usage.append('\n').append("Where:");
        usage.append('\n').append("<signedDataPath> - The file containing the signed data");
        usage.append('\n').append("<signaturePath> - The file containing the actual signature");

        System.out.println(usage.toString());
    }
}
