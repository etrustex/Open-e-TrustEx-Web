package eu.europa.ec.etrustex.webaccess.business;

import eu.europa.ec.etrustex.test.support.AbstractTest;
import eu.europa.ec.etrustex.webaccess.business.mail.PayloadMailContentData;
import org.junit.Test;
import org.mockito.InjectMocks;

public class PayloadMailContentExtractorImplTest extends AbstractTest {

    @InjectMocks
    private PayloadMailContentExtractorImpl payloadMailContentExtractor;

    @Test
    public void testExtractPayload() {
        PayloadMailContentData payloadContentData;
        StringBuilder payload = new StringBuilder()
                .append("<ns1:procedure_interinstitutional><ns1:event_legal><ns1:extension xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"ns2:EventExtensionType\">")
                .append("<ns2:agent_person role=\"RESP_PERS\">Dimitris AVRAMOPOULOS</ns2:agent_person></ns1:extension><ns1:work><ns1:type_work>PROP_DEC</ns1:type_work>")
                .append("<ns1:identifier_work>COM(2017)207/883236</ns1:identifier_work><ns1:agent_work role=\"AUT\">HOME</ns1:agent_work><ns1:date_work>2017-05-08</ns1:date_work>")
                .append("<ns1:version_work>final</ns1:version_work><ns1:extension xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"ns2:WorkExtensionType\">")
                .append("<ns2:category>ACT</ns2:category><ns2:original_language_code>ENG</ns2:original_language_code><ns2:summary_title lang=\"ENG FRA\">This is a test title</ns2:summary_title>")
                .append("</ns1:extension><ns1:expression><ns1:language>ENG</ns1:language><ns1:title lang=\"ENG\">This is a test title</ns1:title>")
                .append("<ns1:extension xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"ns2:ExpressionExtensionType\"/><ns2:manifestation>")
                .append("<ns2:reference_manifestation format=\"DOC\" sequence=\"1\" page_count=\"1\">AT108529445089528100046</ns2:reference_manifestation></ns2:manifestation>)")
                .append("</ns1:expression></ns1:work></ns1:event_legal></ns1:procedure_interinstitutional>");


        payloadContentData = payloadMailContentExtractor.extractPayloadMailContentDataFromPayload(payload.toString());

        assertThat(payloadContentData.getTitle(), is("This is a test title"));
        assertThat(payloadContentData.getLinguisticVersions(), is("ENG"));
        assertThat(payloadContentData.getSubsidiarityCheck(), is(false));
    }


}
