package eu.europa.ec.etrustex.webaccess.security;

import eu.europa.ec.etrustex.test.hamcrest.EtxCommonMatchers;
import eu.europa.ec.etrustex.test.support.AbstractTest;
import org.hamcrest.Matchers;
import org.junit.Test;
import xades4j.providers.ValidationData;

import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;
import java.util.List;

/**
 * @author: micleva
 * @date: 7/31/12 4:35 PM
 * @project: ETX
 */
public class DirectCertificateValidationProviderTest extends AbstractTest {

    private DirectCertificateValidationProvider directCertificateValidationProvider;
    private X509Certificate x509Certificate;

    @Override
    protected void onSetUp() {
        EtxSecurityProvider.init();
        URI fileURI;
        try {
            fileURI = this.getClass().getResource("/testStore.p12").toURI();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        List<KeyStoreEntry<PrivateKey>> entries = null;
        try (InputStream inputStream = Files.newInputStream(Paths.get(fileURI))) {
            entries = EtxSecurityProvider.getInstance().getCertificateService().getKeyStoreEntries(inputStream, "PKCS12", "test123".toCharArray(), PrivateKey.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (entries != null) {
            KeyStoreEntry<PrivateKey> signatureEntry = null;
            for (KeyStoreEntry<PrivateKey> entry : entries) {
                if (entry.getAlias().equals("key1")) {
                    signatureEntry = entry;
                }
            }
            x509Certificate = signatureEntry.getX509Certificate();
            directCertificateValidationProvider = new DirectCertificateValidationProvider(x509Certificate);
        }
    }

    @Test
    public void testValidate() throws Exception {
        ValidationData validationData = directCertificateValidationProvider.validate(null, null, null);

        EtxCommonMatchers.assertThat(validationData, Matchers.is(Matchers.notNullValue()));
        EtxCommonMatchers.assertThat(validationData.getCerts(), Matchers.is(Matchers.notNullValue()));
        EtxCommonMatchers.assertThat(validationData.getCerts().size(), Matchers.is(1));
        EtxCommonMatchers.assertThat(validationData.getCerts().get(0), Matchers.is(Matchers.sameInstance(x509Certificate)));
    }
}
