package eu.europa.ec.etrustex.webaccess.utils;

import eu.europa.ec.etrustex.webaccess.model.vo.CertificateData;
import org.apache.log4j.Logger;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.x500.RDN;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x500.style.BCStyle;
import org.bouncycastle.asn1.x500.style.IETFUtils;
import org.bouncycastle.cert.jcajce.JcaX509CertificateHolder;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateEncodingException;
import java.security.cert.X509Certificate;

public class BuildCertificateData {

    private static final Logger logger = Logger.getLogger(BuildCertificateData.class);

    private static String getASN1ObjectName(final X500Name name, final ASN1ObjectIdentifier asn1Name) {
        RDN[] rdn = name.getRDNs(asn1Name);
        String s = null;
        if ((rdn != null) && (rdn.length > 0)) {
            s = IETFUtils.valueToString(rdn[0].getFirst().getValue());
        } else {
            s = "";
        }
        return s;
    }

    public static CertificateData retrieveCertificateData(X509Certificate certificate) {
        CertificateData certData = null;

        X500Name x500name = null;
        try {
            x500name = new JcaX509CertificateHolder(certificate).getSubject();

            certData = new CertificateData(certificate.getSerialNumber(),
                    getASN1ObjectName(x500name, BCStyle.CN),
                    getASN1ObjectName(x500name, BCStyle.OU),
                    getASN1ObjectName(x500name, BCStyle.O),
                    getASN1ObjectName(x500name, BCStyle.L),
                    getASN1ObjectName(x500name, BCStyle.ST),
                    getASN1ObjectName(x500name, BCStyle.C),
                    getASN1ObjectName(x500name, BCStyle.E),
                    getThumbPrint(certificate.getEncoded()),
                    certificate.getNotAfter()
            );

        } catch (CertificateEncodingException | NullPointerException e) {
            logger.warn("Loading certificate name failed", e);
        }

        return certData;
    }

    private static String getThumbPrint(byte[] encoded) {
        try {
            final MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
            messageDigest.update(encoded);
            final byte[] digest = messageDigest.digest();
            return DatatypeConverter.printHexBinary(digest);
        } catch (NoSuchAlgorithmException e) {
            logger.warn("Could not instantiate SHA-1 digest algorithm", e);
            throw new IllegalStateException(e);
        }
    }

    public static boolean isExpired(X509Certificate certificate) {
        try {
            certificate.checkValidity();
        } catch (Exception e) {
            return true;
        }
        return false;
    }
}