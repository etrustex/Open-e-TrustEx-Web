package eu.europa.ec.etrustex.webaccess.web.view.business.egreffe;

import eu.europa.ec.etrustex.test.support.AbstractTest;
import eu.europa.ec.etrustex.webaccess.business.api.AttachmentHandler;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.HashMap;

import static org.mockito.Mockito.when;

/**
 * @author apladap
 */
public class EGREFFEUtilsTest extends AbstractTest {

    private Long messageId = 1L;

    @Mock
    AttachmentHandler attachmentHandler;

    @InjectMocks
    private EGREFFEUtils egreffeUtils = new EGREFFEUtils();

    @Test
    public void testGetListOfWorks_caseProcedureInteristitutional_NonFilter() throws Exception {
        String payloadXml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>"+
                "<ns5:ec_transmission_request xmlns=\"http://publications.europa.eu/resource/core-metadata-transmission\" xmlns:ns2=\"http://publications.europa.eu/resource/core-metadata\" xmlns:ns3=\"urn:eu:ec:cm:common:extensions:v10\" xmlns:ns4=\"urn:eu:ec:publication:v10\" xmlns:ns5=\"urn:eu:ec:transmission:v10\">"+
                "<transmission id=\"193693\">"+
                "<institution>COM</institution>"+
                "<service>SG Greffe A.1</service>"+
                "<context>e-Greffe</context>"+
                "<date_time>2012-11-30T17:17:05.628</date_time>"+
                "<comment/>"+
                "<extension xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"ns3:TransmissionExtensionType\">"+
                "<ns3:sender/>"+
                "</extension>"+
                "<workflow>"+
                "<extension xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"ns3:WorkflowExtensionType\">"+
                "<ns3:workflow_number ns2:public=\"false\">"+
                "<ns3:code>PH</ns3:code>"+
                "<ns3:year>2012</ns3:year>"+
                "<ns3:number>5855</ns3:number>"+
                "</ns3:workflow_number>"+
                "<ns3:adoption_date_time>2012-11-07T15:38:49.000</ns3:adoption_date_time>"+
                "<ns3:eea_relevance>false</ns3:eea_relevance>"+
                "<ns3:codified_version>false</ns3:codified_version>"+
                "<ns3:durability>D</ns3:durability>"+
                "</extension>"+
                "<work_reference ref=\"COM(2012)478/704453\"/>"+
                "</workflow>"+
                "<ns2:procedure_interinstitutional>"+
                "<ns2:identifier_procedure>"+
                "<ns2:year_procedure>2009</ns2:year_procedure>"+
                "<ns2:number_procedure>7</ns2:number_procedure>"+
                "</ns2:identifier_procedure>"+
                "<ns2:extension xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"ns3:ProcedureExtensionType\">"+
                "<ns3:subsidiarity_proportionality_application>"+
                "<ns3:competence_type>EXCLUSIVE</ns3:competence_type>"+
                "<ns3:legal_reference>"+
                "<ns2:document_legal>TFEU_2010</ns2:document_legal>"+
                "<ns2:article>3</ns2:article>"+
                "</ns3:legal_reference>"+
                "</ns3:subsidiarity_proportionality_application>"+
                "</ns2:extension>"+
                "<ns2:event_legal>"+
                "<ns2:type_event>ADP_OPR2AMEPE_byCOM</ns2:type_event>"+
                "<ns2:date_event>2012-11-28</ns2:date_event>"+
                "<ns2:agent_event role=\"AUTHORI\">COM</ns2:agent_event>"+
                "<ns2:agent_event role=\"RESP_CORP\">SG</ns2:agent_event>"+
                "<ns2:agent_event role=\"ADDRESSEE_ACT\">CONSIL</ns2:agent_event>"+
                "<ns2:agent_event role=\"ADDRESSEE_ACT\">EP</ns2:agent_event>"+
                "<ns2:agent_event role=\"ADDRESSEE_CONSULT_MANDATORY\">NP</ns2:agent_event>"+
                "<ns2:type_interinstitutionalprocedure>COD</ns2:type_interinstitutionalprocedure>"+
                "<ns2:basis_legal_procedure>"+
                "<ns2:document_legal>TFEU_2010</ns2:document_legal>"+
                "<ns2:article>74</ns2:article>"+
                "</ns2:basis_legal_procedure>"+
                "<ns2:extension xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"ns3:EventExtensionType\">"+
                "<ns3:agent_person role=\"RESP_PERS\">José Manuel BARROSO</ns3:agent_person>"+
                "</ns2:extension>"+
                "<ns2:work>"+
                "<ns2:type_work>OPIN_AMEND_EP</ns2:type_work>"+
                "<ns2:identifier_work>COM(2012)478/704453</ns2:identifier_work>"+
                "<ns2:agent_work role=\"AUT\">SG</ns2:agent_work>"+
                "<ns2:date_work>2012-11-07</ns2:date_work>"+
                "<ns2:version_work>final</ns2:version_work>"+
                "<ns2:extension xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"ns3:WorkExtensionType\">"+
                "<ns3:category>ACT</ns3:category>"+
                "<ns3:original_language_code>ENG</ns3:original_language_code>"+
                "<ns3:internal_number>"+
                "<ns3:code>COM</ns3:code>"+
                "<ns3:year>2012</ns3:year>"+
                "<ns3:number>478</ns3:number>"+
                "</ns3:internal_number>"+
                "<ns3:sequence>1</ns3:sequence>"+
                "<ns3:security_info>"+
                "<ns3:classification>NORMAL</ns3:classification>"+
                "</ns3:security_info>"+
                "<ns3:summary_title lang=\"ENG FRA\">AVIS DE LA COMMISSION "+
                "conformément à l'article 294, paragraphe 7, point c), du traité sur le fonctionnement de l'Union européenne "+
                "sur l'[les]amendement[s] du Parlement européen "+
                "à la position du Conseil concernant la "+
                "proposition de "+
                "RÈGLEMENT DU PARLEMENT EUROPÉEN ET DU CONSEIL "+
                "concernant la mise sur le marché et l'utilisation des produits biocides "+
                "</ns3:summary_title>"+
                "</ns2:extension>"+
                "<ns2:expression>"+
                "<ns2:language>ENG</ns2:language>"+
                "<ns2:title lang=\"ENG\">OPINION OF THE COMMISSION pursuant to Article 294(7)(c) of the Treaty on the Functioning of the European Union, on the European Parliament's amendment[s] to the Council's position regarding the proposal for a REGULATION OF THE EUROPEAN PARLIAMENT AND OF THE COUNCIL concerning the placing on the market and use of biocidal products. </ns2:title>"+
                "<ns2:extension xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"ns3:ExpressionExtensionType\"/>"+
                "<ns2:manifestation>"+
                "<ns2:reference_manifestation format=\"DOC\" sequence=\"1\" page_count=\"4\">2689090</ns2:reference_manifestation>"+
                "</ns2:manifestation>"+
                "<ns2:manifestation>"+
                "<ns2:reference_manifestation format=\"PDF\" sequence=\"1\" page_count=\"4\">2689096</ns2:reference_manifestation>"+
                "</ns2:manifestation>"+
                "</ns2:expression>"+
                "<ns2:expression>"+
                "<ns2:language>FRA</ns2:language>"+
                "<ns2:title lang=\"FRA\">AVIS DE LA COMMISSION conformément à l'article 294, paragraphe 7, point c), du traité sur le fonctionnement de l'Union européenne sur l'[les]amendement[s] du Parlement européen à la position du Conseil concernant la proposition de RÈGLEMENT DU PARLEMENT EUROPÉEN ET DU CONSEIL concernant la mise sur le marché et l'utilisation des produits biocides </ns2:title>"+
                "<ns2:extension xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"ns3:ExpressionExtensionType\"/>"+
                "<ns2:manifestation>"+
                "<ns2:reference_manifestation format=\"DOC\" sequence=\"1\" page_count=\"3\">2689091</ns2:reference_manifestation>"+
                "</ns2:manifestation>"+
                "<ns2:manifestation>"+
                "<ns2:reference_manifestation format=\"PDF\" sequence=\"1\" page_count=\"4\">2689094</ns2:reference_manifestation>"+
                "</ns2:manifestation>"+
                "</ns2:expression>"+
                "<ns2:expression>"+
                "<ns2:language>DEU</ns2:language>"+
                "<ns2:title lang=\"DEU\">STELLUNGNAHME DER KOMMISSION gemäß Artikel 294 Absatz 7 Buchstabe c des Vertrags über die Arbeitsweise der Europäischen Union zu den Abänderungen des Europäischen Parlaments an dem Standpunkt des Rates betreffend den Vorschlag für eine VERORDNUNG DES EUROPÄISCHEN PARLAMENTS UND DES RATES über das Inverkehrbringen und die Verwendung von Biozidprodukten </ns2:title>"+
                "<ns2:extension xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"ns3:ExpressionExtensionType\"/>"+
                "<ns2:manifestation>"+
                "<ns2:reference_manifestation format=\"DOC\" sequence=\"1\" page_count=\"4\">2689089</ns2:reference_manifestation>"+
                "</ns2:manifestation>"+
                "<ns2:manifestation>"+
                "<ns2:reference_manifestation format=\"PDF\" sequence=\"1\" page_count=\"4\">2689095</ns2:reference_manifestation>"+
                "</ns2:manifestation>"+
                "</ns2:expression>"+
                "</ns2:work>"+
                "</ns2:event_legal>"+
                "</ns2:procedure_interinstitutional>"+
                "</transmission>"+
                "</ns5:ec_transmission_request>";

        HashMap<String,String> messageAttachmentsMap = new HashMap<>();
        messageAttachmentsMap.put("2689090", "Filename1");
        messageAttachmentsMap.put("2689096", "Filename2");
        messageAttachmentsMap.put("2689091", "Filename3");
        messageAttachmentsMap.put("2689094", "Filename4");
        messageAttachmentsMap.put("2689089", "Filename5");
        messageAttachmentsMap.put("2689095", "Filename6");

        when(attachmentHandler.getMapOfAttachmentFilenamesPerReferenceIds(messageId)).thenReturn(messageAttachmentsMap);

        EgreffeTransmissionVO egreffeTransmission = egreffeUtils.getListOfWorks(payloadXml, messageId);


        assertThat(egreffeTransmission, notNullValue());
        assertThat(egreffeTransmission.getComments(), is(""));
        assertThat(egreffeTransmission.getAdoptionDate().toString(), is("Wed Nov 07 15:38:49 CET 2012"));
        assertThat(egreffeTransmission.getListOfWorks(), notNullValue());
        assertThat(egreffeTransmission.getListOfWorks().size(), is(1));

        //Check the order of the works and the proper order of the languages (aka sort was successful)
        assertThat(egreffeTransmission.getListOfWorks().get(0).getLanguages().get(0), is("DEU"));
        assertThat(egreffeTransmission.getListOfWorks().get(0).getLanguages().get(1), is("ENG"));
        assertThat(egreffeTransmission.getListOfWorks().get(0).getLanguages().get(2), is("FRA"));

        //Check the order of the works and the proper order of the documents/languages (aka sort was successful)
        assertThat(egreffeTransmission.getListOfWorks().get(0).getDocumentsList().get("DEU").get(1).size(), is(2));
        assertThat(egreffeTransmission.getListOfWorks().get(0).getDocumentsList().get("DEU").get(1).get(0).getValue(), is("2689089"));
        assertThat(egreffeTransmission.getListOfWorks().get(0).getDocumentsList().get("ENG").get(1).size(), is(2));
        assertThat(egreffeTransmission.getListOfWorks().get(0).getDocumentsList().get("ENG").get(1).get(0).getValue(), is("2689090"));
        assertThat(egreffeTransmission.getListOfWorks().get(0).getDocumentsList().get("FRA").get(1).size(), is(2));
        assertThat(egreffeTransmission.getListOfWorks().get(0).getDocumentsList().get("FRA").get(1).get(0).getValue(), is("2689091"));


        assertThat(messageAttachmentsMap.size(), is(6));
    }

    @Test
    public void testGetListOfWorks_caseProcedureInteristitutional_Filter_MoreAttachments() throws Exception {
        String payloadXml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>"+
                "<ns5:ec_transmission_request xmlns=\"http://publications.europa.eu/resource/core-metadata-transmission\" xmlns:ns2=\"http://publications.europa.eu/resource/core-metadata\" xmlns:ns3=\"urn:eu:ec:cm:common:extensions:v10\" xmlns:ns4=\"urn:eu:ec:publication:v10\" xmlns:ns5=\"urn:eu:ec:transmission:v10\">"+
                "<transmission id=\"193693\">"+
                "<institution>COM</institution>"+
                "<service>SG Greffe A.1</service>"+
                "<context>e-Greffe</context>"+
                "<date_time>2012-11-30T17:17:05.628</date_time>"+
                "<comment/>"+
                "<extension xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"ns3:TransmissionExtensionType\">"+
                "<ns3:sender/>"+
                "</extension>"+
                "<workflow>"+
                "<extension xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"ns3:WorkflowExtensionType\">"+
                "<ns3:workflow_number ns2:public=\"false\">"+
                "<ns3:code>PH</ns3:code>"+
                "<ns3:year>2012</ns3:year>"+
                "<ns3:number>5855</ns3:number>"+
                "</ns3:workflow_number>"+
                "<ns3:adoption_date_time>2012-11-07T15:38:49.000</ns3:adoption_date_time>"+
                "<ns3:eea_relevance>false</ns3:eea_relevance>"+
                "<ns3:codified_version>false</ns3:codified_version>"+
                "<ns3:durability>D</ns3:durability>"+
                "</extension>"+
                "<work_reference ref=\"COM(2012)478/704453\"/>"+
                "</workflow>"+
                "<ns2:procedure_interinstitutional>"+
                "<ns2:identifier_procedure>"+
                "<ns2:year_procedure>2009</ns2:year_procedure>"+
                "<ns2:number_procedure>7</ns2:number_procedure>"+
                "</ns2:identifier_procedure>"+
                "<ns2:extension xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"ns3:ProcedureExtensionType\">"+
                "<ns3:subsidiarity_proportionality_application>"+
                "<ns3:competence_type>EXCLUSIVE</ns3:competence_type>"+
                "<ns3:legal_reference>"+
                "<ns2:document_legal>TFEU_2010</ns2:document_legal>"+
                "<ns2:article>3</ns2:article>"+
                "</ns3:legal_reference>"+
                "</ns3:subsidiarity_proportionality_application>"+
                "</ns2:extension>"+
                "<ns2:event_legal>"+
                "<ns2:type_event>ADP_OPR2AMEPE_byCOM</ns2:type_event>"+
                "<ns2:date_event>2012-11-28</ns2:date_event>"+
                "<ns2:agent_event role=\"AUTHORI\">COM</ns2:agent_event>"+
                "<ns2:agent_event role=\"RESP_CORP\">SG</ns2:agent_event>"+
                "<ns2:agent_event role=\"ADDRESSEE_ACT\">CONSIL</ns2:agent_event>"+
                "<ns2:agent_event role=\"ADDRESSEE_ACT\">EP</ns2:agent_event>"+
                "<ns2:agent_event role=\"ADDRESSEE_CONSULT_MANDATORY\">NP</ns2:agent_event>"+
                "<ns2:type_interinstitutionalprocedure>COD</ns2:type_interinstitutionalprocedure>"+
                "<ns2:basis_legal_procedure>"+
                "<ns2:document_legal>TFEU_2010</ns2:document_legal>"+
                "<ns2:article>74</ns2:article>"+
                "</ns2:basis_legal_procedure>"+
                "<ns2:extension xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"ns3:EventExtensionType\">"+
                "<ns3:agent_person role=\"RESP_PERS\">José Manuel BARROSO</ns3:agent_person>"+
                "</ns2:extension>"+
                "<ns2:work>"+
                "<ns2:type_work>OPIN_AMEND_EP</ns2:type_work>"+
                "<ns2:identifier_work>COM(2012)478/704453</ns2:identifier_work>"+
                "<ns2:agent_work role=\"AUT\">SG</ns2:agent_work>"+
                "<ns2:date_work>2012-11-07</ns2:date_work>"+
                "<ns2:version_work>final</ns2:version_work>"+
                "<ns2:extension xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"ns3:WorkExtensionType\">"+
                "<ns3:category>ACT</ns3:category>"+
                "<ns3:original_language_code>ENG</ns3:original_language_code>"+
                "<ns3:internal_number>"+
                "<ns3:code>COM</ns3:code>"+
                "<ns3:year>2012</ns3:year>"+
                "<ns3:number>478</ns3:number>"+
                "</ns3:internal_number>"+
                "<ns3:sequence>1</ns3:sequence>"+
                "<ns3:security_info>"+
                "<ns3:classification>NORMAL</ns3:classification>"+
                "</ns3:security_info>"+
                "<ns3:summary_title lang=\"ENG FRA\">AVIS DE LA COMMISSION "+
                "conformément à l'article 294, paragraphe 7, point c), du traité sur le fonctionnement de l'Union européenne "+
                "sur l'[les]amendement[s] du Parlement européen "+
                "à la position du Conseil concernant la "+
                "proposition de "+
                "RÈGLEMENT DU PARLEMENT EUROPÉEN ET DU CONSEIL "+
                "concernant la mise sur le marché et l'utilisation des produits biocides "+
                "</ns3:summary_title>"+
                "</ns2:extension>"+
                "<ns2:expression>"+
                "<ns2:language>ENG</ns2:language>"+
                "<ns2:title lang=\"ENG\">OPINION OF THE COMMISSION pursuant to Article 294(7)(c) of the Treaty on the Functioning of the European Union, on the European Parliament's amendment[s] to the Council's position regarding the proposal for a REGULATION OF THE EUROPEAN PARLIAMENT AND OF THE COUNCIL concerning the placing on the market and use of biocidal products. </ns2:title>"+
                "<ns2:extension xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"ns3:ExpressionExtensionType\"/>"+
                "<ns2:manifestation>"+
                "<ns2:reference_manifestation format=\"DOC\" sequence=\"1\" page_count=\"4\">2689090</ns2:reference_manifestation>"+
                "</ns2:manifestation>"+
                "<ns2:manifestation>"+
                "<ns2:reference_manifestation format=\"PDF\" sequence=\"1\" page_count=\"4\">2689096</ns2:reference_manifestation>"+
                "</ns2:manifestation>"+
                "</ns2:expression>"+
                "<ns2:expression>"+
                "<ns2:language>FRA</ns2:language>"+
                "<ns2:title lang=\"FRA\">AVIS DE LA COMMISSION conformément à l'article 294, paragraphe 7, point c), du traité sur le fonctionnement de l'Union européenne sur l'[les]amendement[s] du Parlement européen à la position du Conseil concernant la proposition de RÈGLEMENT DU PARLEMENT EUROPÉEN ET DU CONSEIL concernant la mise sur le marché et l'utilisation des produits biocides </ns2:title>"+
                "<ns2:extension xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"ns3:ExpressionExtensionType\"/>"+
                "<ns2:manifestation>"+
                "<ns2:reference_manifestation format=\"DOC\" sequence=\"1\" page_count=\"3\">2689091</ns2:reference_manifestation>"+
                "</ns2:manifestation>"+
                "<ns2:manifestation>"+
                "<ns2:reference_manifestation format=\"PDF\" sequence=\"1\" page_count=\"4\">2689094</ns2:reference_manifestation>"+
                "</ns2:manifestation>"+
                "</ns2:expression>"+
                "<ns2:expression>"+
                "<ns2:language>DEU</ns2:language>"+
                "<ns2:title lang=\"DEU\">STELLUNGNAHME DER KOMMISSION gemäß Artikel 294 Absatz 7 Buchstabe c des Vertrags über die Arbeitsweise der Europäischen Union zu den Abänderungen des Europäischen Parlaments an dem Standpunkt des Rates betreffend den Vorschlag für eine VERORDNUNG DES EUROPÄISCHEN PARLAMENTS UND DES RATES über das Inverkehrbringen und die Verwendung von Biozidprodukten </ns2:title>"+
                "<ns2:extension xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"ns3:ExpressionExtensionType\"/>"+
                "<ns2:manifestation>"+
                "<ns2:reference_manifestation format=\"DOC\" sequence=\"1\" page_count=\"4\">2689089</ns2:reference_manifestation>"+
                "</ns2:manifestation>"+
                "<ns2:manifestation>"+
                "<ns2:reference_manifestation format=\"PDF\" sequence=\"1\" page_count=\"4\">2689095</ns2:reference_manifestation>"+
                "</ns2:manifestation>"+
                "</ns2:expression>"+
                "</ns2:work>"+
                "</ns2:event_legal>"+
                "</ns2:procedure_interinstitutional>"+
                "</transmission>"+
                "</ns5:ec_transmission_request>";

        HashMap<String,String> messageAttachmentsMap = new HashMap<>();
        messageAttachmentsMap.put("2689090", "Filename1");
        messageAttachmentsMap.put("2689096", "Filename2");
        messageAttachmentsMap.put("2689091", "Filename3");
        messageAttachmentsMap.put("2689094", "Filename4");
        messageAttachmentsMap.put("2689089", "Filename5");
        messageAttachmentsMap.put("2689095", "Filename6");
        messageAttachmentsMap.put("4344245", "Filename7");

        when(attachmentHandler.getMapOfAttachmentFilenamesPerReferenceIds(messageId)).thenReturn(messageAttachmentsMap);

        EgreffeTransmissionVO egreffeTransmission = egreffeUtils.getListOfWorks(payloadXml, messageId);


        assertThat(egreffeTransmission, notNullValue());
        assertThat(egreffeTransmission.getComments(), is(""));
        assertThat(egreffeTransmission.getAdoptionDate().toString(), is("Wed Nov 07 15:38:49 CET 2012"));
        assertThat(egreffeTransmission.getListOfWorks(), notNullValue());
        assertThat(egreffeTransmission.getListOfWorks().size(), is(1));

        //Check the order of the works and the proper order of the languages (aka sort was successful)
        assertThat(egreffeTransmission.getListOfWorks().get(0).getLanguages().get(0), is("DEU"));
        assertThat(egreffeTransmission.getListOfWorks().get(0).getLanguages().get(1), is("ENG"));
        assertThat(egreffeTransmission.getListOfWorks().get(0).getLanguages().get(2), is("FRA"));

        //Check the order of the works and the proper order of the documents/languages (aka sort was successful)
        assertThat(egreffeTransmission.getListOfWorks().get(0).getDocumentsList().get("DEU").get(1).size(), is(2));
        assertThat(egreffeTransmission.getListOfWorks().get(0).getDocumentsList().get("DEU").get(1).get(0).getValue(), is("2689089"));
        assertThat(egreffeTransmission.getListOfWorks().get(0).getDocumentsList().get("ENG").get(1).size(), is(2));
        assertThat(egreffeTransmission.getListOfWorks().get(0).getDocumentsList().get("ENG").get(1).get(0).getValue(), is("2689090"));
        assertThat(egreffeTransmission.getListOfWorks().get(0).getDocumentsList().get("FRA").get(1).size(), is(2));
        assertThat(egreffeTransmission.getListOfWorks().get(0).getDocumentsList().get("FRA").get(1).get(0).getValue(), is("2689091"));


        assertThat(messageAttachmentsMap.size(), is(7));
    }

    @Test
    public void testGetListOfWorks_caseProcedureInteristitutional_Filter_LessAttachments() throws Exception {
        String payloadXml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>"+
                "<ns5:ec_transmission_request xmlns=\"http://publications.europa.eu/resource/core-metadata-transmission\" xmlns:ns2=\"http://publications.europa.eu/resource/core-metadata\" xmlns:ns3=\"urn:eu:ec:cm:common:extensions:v10\" xmlns:ns4=\"urn:eu:ec:publication:v10\" xmlns:ns5=\"urn:eu:ec:transmission:v10\">"+
                "<transmission id=\"193693\">"+
                "<institution>COM</institution>"+
                "<service>SG Greffe A.1</service>"+
                "<context>e-Greffe</context>"+
                "<date_time>2012-11-30T17:17:05.628</date_time>"+
                "<comment/>"+
                "<extension xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"ns3:TransmissionExtensionType\">"+
                "<ns3:sender/>"+
                "</extension>"+
                "<workflow>"+
                "<extension xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"ns3:WorkflowExtensionType\">"+
                "<ns3:workflow_number ns2:public=\"false\">"+
                "<ns3:code>PH</ns3:code>"+
                "<ns3:year>2012</ns3:year>"+
                "<ns3:number>5855</ns3:number>"+
                "</ns3:workflow_number>"+
                "<ns3:adoption_date_time>2012-11-07T15:38:49.000</ns3:adoption_date_time>"+
                "<ns3:eea_relevance>false</ns3:eea_relevance>"+
                "<ns3:codified_version>false</ns3:codified_version>"+
                "<ns3:durability>D</ns3:durability>"+
                "</extension>"+
                "<work_reference ref=\"COM(2012)478/704453\"/>"+
                "</workflow>"+
                "<ns2:procedure_interinstitutional>"+
                "<ns2:identifier_procedure>"+
                "<ns2:year_procedure>2009</ns2:year_procedure>"+
                "<ns2:number_procedure>7</ns2:number_procedure>"+
                "</ns2:identifier_procedure>"+
                "<ns2:extension xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"ns3:ProcedureExtensionType\">"+
                "<ns3:subsidiarity_proportionality_application>"+
                "<ns3:competence_type>EXCLUSIVE</ns3:competence_type>"+
                "<ns3:legal_reference>"+
                "<ns2:document_legal>TFEU_2010</ns2:document_legal>"+
                "<ns2:article>3</ns2:article>"+
                "</ns3:legal_reference>"+
                "</ns3:subsidiarity_proportionality_application>"+
                "</ns2:extension>"+
                "<ns2:event_legal>"+
                "<ns2:type_event>ADP_OPR2AMEPE_byCOM</ns2:type_event>"+
                "<ns2:date_event>2012-11-28</ns2:date_event>"+
                "<ns2:agent_event role=\"AUTHORI\">COM</ns2:agent_event>"+
                "<ns2:agent_event role=\"RESP_CORP\">SG</ns2:agent_event>"+
                "<ns2:agent_event role=\"putRESSEE_ACT\">CONSIL</ns2:agent_event>"+
                "<ns2:agent_event role=\"ADDRESSEE_ACT\">EP</ns2:agent_event>"+
                "<ns2:agent_event role=\"ADDRESSEE_CONSULT_MANDATORY\">NP</ns2:agent_event>"+
                "<ns2:type_interinstitutionalprocedure>COD</ns2:type_interinstitutionalprocedure>"+
                "<ns2:basis_legal_procedure>"+
                "<ns2:document_legal>TFEU_2010</ns2:document_legal>"+
                "<ns2:article>74</ns2:article>"+
                "</ns2:basis_legal_procedure>"+
                "<ns2:extension xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"ns3:EventExtensionType\">"+
                "<ns3:agent_person role=\"RESP_PERS\">José Manuel BARROSO</ns3:agent_person>"+
                "</ns2:extension>"+
                "<ns2:work>"+
                "<ns2:type_work>OPIN_AMEND_EP</ns2:type_work>"+
                "<ns2:identifier_work>COM(2012)478/704453</ns2:identifier_work>"+
                "<ns2:agent_work role=\"AUT\">SG</ns2:agent_work>"+
                "<ns2:date_work>2012-11-07</ns2:date_work>"+
                "<ns2:version_work>final</ns2:version_work>"+
                "<ns2:extension xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"ns3:WorkExtensionType\">"+
                "<ns3:category>ACT</ns3:category>"+
                "<ns3:original_language_code>ENG</ns3:original_language_code>"+
                "<ns3:internal_number>"+
                "<ns3:code>COM</ns3:code>"+
                "<ns3:year>2012</ns3:year>"+
                "<ns3:number>478</ns3:number>"+
                "</ns3:internal_number>"+
                "<ns3:sequence>1</ns3:sequence>"+
                "<ns3:security_info>"+
                "<ns3:classification>NORMAL</ns3:classification>"+
                "</ns3:security_info>"+
                "<ns3:summary_title lang=\"ENG FRA\">AVIS DE LA COMMISSION "+
                "conformément à l'article 294, paragraphe 7, point c), du traité sur le fonctionnement de l'Union européenne "+
                "sur l'[les]amendement[s] du Parlement européen "+
                "à la position du Conseil concernant la "+
                "proposition de "+
                "RÈGLEMENT DU PARLEMENT EUROPÉEN ET DU CONSEIL "+
                "concernant la mise sur le marché et l'utilisation des produits biocides "+
                "</ns3:summary_title>"+
                "</ns2:extension>"+
                "<ns2:expression>"+
                "<ns2:language>ENG</ns2:language>"+
                "<ns2:title lang=\"ENG\">OPINION OF THE COMMISSION pursuant to Article 294(7)(c) of the Treaty on the Functioning of the European Union, on the European Parliament's amendment[s] to the Council's position regarding the proposal for a REGULATION OF THE EUROPEAN PARLIAMENT AND OF THE COUNCIL concerning the placing on the market and use of biocidal products. </ns2:title>"+
                "<ns2:extension xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"ns3:ExpressionExtensionType\"/>"+
                "<ns2:manifestation>"+
                "<ns2:reference_manifestation format=\"DOC\" sequence=\"1\" page_count=\"4\">2689090</ns2:reference_manifestation>"+
                "</ns2:manifestation>"+
                "<ns2:manifestation>"+
                "<ns2:reference_manifestation format=\"PDF\" sequence=\"1\" page_count=\"4\">2689096</ns2:reference_manifestation>"+
                "</ns2:manifestation>"+
                "</ns2:expression>"+
                "<ns2:expression>"+
                "<ns2:language>FRA</ns2:language>"+
                "<ns2:title lang=\"FRA\">AVIS DE LA COMMISSION conformément à l'article 294, paragraphe 7, point c), du traité sur le fonctionnement de l'Union européenne sur l'[les]amendement[s] du Parlement européen à la position du Conseil concernant la proposition de RÈGLEMENT DU PARLEMENT EUROPÉEN ET DU CONSEIL concernant la mise sur le marché et l'utilisation des produits biocides </ns2:title>"+
                "<ns2:extension xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"ns3:ExpressionExtensionType\"/>"+
                "<ns2:manifestation>"+
                "<ns2:reference_manifestation format=\"DOC\" sequence=\"1\" page_count=\"3\">2689091</ns2:reference_manifestation>"+
                "</ns2:manifestation>"+
                "<ns2:manifestation>"+
                "<ns2:reference_manifestation format=\"PDF\" sequence=\"1\" page_count=\"4\">2689094</ns2:reference_manifestation>"+
                "</ns2:manifestation>"+
                "</ns2:expression>"+
                "<ns2:expression>"+
                "<ns2:language>DEU</ns2:language>"+
                "<ns2:title lang=\"DEU\">STELLUNGNAHME DER KOMMISSION gemäß Artikel 294 Absatz 7 Buchstabe c des Vertrags über die Arbeitsweise der Europäischen Union zu den Abänderungen des Europäischen Parlaments an dem Standpunkt des Rates betreffend den Vorschlag für eine VERORDNUNG DES EUROPÄISCHEN PARLAMENTS UND DES RATES über das Inverkehrbringen und die Verwendung von Biozidprodukten </ns2:title>"+
                "<ns2:extension xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"ns3:ExpressionExtensionType\"/>"+
                "<ns2:manifestation>"+
                "<ns2:reference_manifestation format=\"DOC\" sequence=\"1\" page_count=\"4\">2689089</ns2:reference_manifestation>"+
                "</ns2:manifestation>"+
                "<ns2:manifestation>"+
                "<ns2:reference_manifestation format=\"PDF\" sequence=\"1\" page_count=\"4\">2689095</ns2:reference_manifestation>"+
                "</ns2:manifestation>"+
                "</ns2:expression>"+
                "</ns2:work>"+
                "</ns2:event_legal>"+
                "</ns2:procedure_interinstitutional>"+
                "</transmission>"+
                "</ns5:ec_transmission_request>";

        HashMap<String,String> messageAttachmentsMap = new HashMap<>();
        messageAttachmentsMap.put("2689090", "Filename1");
        messageAttachmentsMap.put("2689096", "Filename2");
        messageAttachmentsMap.put("2689091", "Filename3");

        when(attachmentHandler.getMapOfAttachmentFilenamesPerReferenceIds(messageId)).thenReturn(messageAttachmentsMap);

        EgreffeTransmissionVO egreffeTransmission = egreffeUtils.getListOfWorks(payloadXml, messageId);


        assertThat(egreffeTransmission, notNullValue());
        assertThat(egreffeTransmission.getComments(), is(""));
        assertThat(egreffeTransmission.getAdoptionDate().toString(), is("Wed Nov 07 15:38:49 CET 2012"));
        assertThat(egreffeTransmission.getListOfWorks(), notNullValue());
        assertThat(egreffeTransmission.getListOfWorks().size(), is(1));

        //Check the order of the works and the proper order of the languages (aka sort was successful)
        assertThat(egreffeTransmission.getListOfWorks().get(0).getLanguages().get(0), is("DEU"));
        assertThat(egreffeTransmission.getListOfWorks().get(0).getLanguages().get(1), is("ENG"));
        assertThat(egreffeTransmission.getListOfWorks().get(0).getLanguages().get(2), is("FRA"));

        //Check the order of the works and the proper order of the documents/languages (aka sort was successful)
        assertThat(egreffeTransmission.getListOfWorks().get(0).getDocumentsList().get("DEU").get(1).size(), is(0));
        assertThat(egreffeTransmission.getListOfWorks().get(0).getDocumentsList().get("ENG").get(1).size(), is(2));
        assertThat(egreffeTransmission.getListOfWorks().get(0).getDocumentsList().get("ENG").get(1).get(0).getValue(), is("2689090"));
        assertThat(egreffeTransmission.getListOfWorks().get(0).getDocumentsList().get("FRA").get(1).size(), is(1));
        assertThat(egreffeTransmission.getListOfWorks().get(0).getDocumentsList().get("FRA").get(1).get(0).getValue(), is("2689091"));


        assertThat(messageAttachmentsMap.size(), is(3));
    }

    @Test
    public void testGetListOfWorks_caseProcedureInteristitutional_NoAttachments() throws Exception {
        String payloadXml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>"+
                "<ns5:ec_transmission_request xmlns=\"http://publications.europa.eu/resource/core-metadata-transmission\" xmlns:ns2=\"http://publications.europa.eu/resource/core-metadata\" xmlns:ns3=\"urn:eu:ec:cm:common:extensions:v10\" xmlns:ns4=\"urn:eu:ec:publication:v10\" xmlns:ns5=\"urn:eu:ec:transmission:v10\">"+
                "<transmission id=\"193693\">"+
                "<institution>COM</institution>"+
                "<service>SG Greffe A.1</service>"+
                "<context>e-Greffe</context>"+
                "<date_time>2012-11-30T17:17:05.628</date_time>"+
                "<comment/>"+
                "<extension xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"ns3:TransmissionExtensionType\">"+
                "<ns3:sender/>"+
                "</extension>"+
                "<workflow>"+
                "<extension xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"ns3:WorkflowExtensionType\">"+
                "<ns3:workflow_number ns2:public=\"false\">"+
                "<ns3:code>PH</ns3:code>"+
                "<ns3:year>2012</ns3:year>"+
                "<ns3:number>5855</ns3:number>"+
                "</ns3:workflow_number>"+
                "<ns3:adoption_date_time>2012-11-07T15:38:49.000</ns3:adoption_date_time>"+
                "<ns3:eea_relevance>false</ns3:eea_relevance>"+
                "<ns3:codified_version>false</ns3:codified_version>"+
                "<ns3:durability>D</ns3:durability>"+
                "</extension>"+
                "<work_reference ref=\"COM(2012)478/704453\"/>"+
                "</workflow>"+
                "<ns2:procedure_interinstitutional>"+
                "<ns2:identifier_procedure>"+
                "<ns2:year_procedure>2009</ns2:year_procedure>"+
                "<ns2:number_procedure>7</ns2:number_procedure>"+
                "</ns2:identifier_procedure>"+
                "<ns2:extension xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"ns3:ProcedureExtensionType\">"+
                "<ns3:subsidiarity_proportionality_application>"+
                "<ns3:competence_type>EXCLUSIVE</ns3:competence_type>"+
                "<ns3:legal_reference>"+
                "<ns2:document_legal>TFEU_2010</ns2:document_legal>"+
                "<ns2:article>3</ns2:article>"+
                "</ns3:legal_reference>"+
                "</ns3:subsidiarity_proportionality_application>"+
                "</ns2:extension>"+
                "<ns2:event_legal>"+
                "<ns2:type_event>ADP_OPR2AMEPE_byCOM</ns2:type_event>"+
                "<ns2:date_event>2012-11-28</ns2:date_event>"+
                "<ns2:agent_event role=\"AUTHORI\">COM</ns2:agent_event>"+
                "<ns2:agent_event role=\"RESP_CORP\">SG</ns2:agent_event>"+
                "<ns2:agent_event role=\"ADDRESSEE_ACT\">CONSIL</ns2:agent_event>"+
                "<ns2:agent_event role=\"ADDRESSEE_ACT\">EP</ns2:agent_event>"+
                "<ns2:agent_event role=\"ADDRESSEE_CONSULT_MANDATORY\">NP</ns2:agent_event>"+
                "<ns2:type_interinstitutionalprocedure>COD</ns2:type_interinstitutionalprocedure>"+
                "<ns2:basis_legal_procedure>"+
                "<ns2:document_legal>TFEU_2010</ns2:document_legal>"+
                "<ns2:article>74</ns2:article>"+
                "</ns2:basis_legal_procedure>"+
                "<ns2:extension xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"ns3:EventExtensionType\">"+
                "<ns3:agent_person role=\"RESP_PERS\">José Manuel BARROSO</ns3:agent_person>"+
                "</ns2:extension>"+
                "<ns2:work>"+
                "<ns2:type_work>OPIN_AMEND_EP</ns2:type_work>"+
                "<ns2:identifier_work>COM(2012)478/704453</ns2:identifier_work>"+
                "<ns2:agent_work role=\"AUT\">SG</ns2:agent_work>"+
                "<ns2:date_work>2012-11-07</ns2:date_work>"+
                "<ns2:version_work>final</ns2:version_work>"+
                "<ns2:extension xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"ns3:WorkExtensionType\">"+
                "<ns3:category>ACT</ns3:category>"+
                "<ns3:original_language_code>ENG</ns3:original_language_code>"+
                "<ns3:internal_number>"+
                "<ns3:code>COM</ns3:code>"+
                "<ns3:year>2012</ns3:year>"+
                "<ns3:number>478</ns3:number>"+
                "</ns3:internal_number>"+
                "<ns3:sequence>1</ns3:sequence>"+
                "<ns3:security_info>"+
                "<ns3:classification>NORMAL</ns3:classification>"+
                "</ns3:security_info>"+
                "<ns3:summary_title lang=\"ENG FRA\">AVIS DE LA COMMISSION "+
                "conformément à l'article 294, paragraphe 7, point c), du traité sur le fonctionnement de l'Union européenne "+
                "sur l'[les]amendement[s] du Parlement européen "+
                "à la position du Conseil concernant la "+
                "proposition de "+
                "RÈGLEMENT DU PARLEMENT EUROPÉEN ET DU CONSEIL "+
                "concernant la mise sur le marché et l'utilisation des produits biocides "+
                "</ns3:summary_title>"+
                "</ns2:extension>"+
                "<ns2:expression>"+
                "<ns2:language>ENG</ns2:language>"+
                "<ns2:title lang=\"ENG\">OPINION OF THE COMMISSION pursuant to Article 294(7)(c) of the Treaty on the Functioning of the European Union, on the European Parliament's amendment[s] to the Council's position regarding the proposal for a REGULATION OF THE EUROPEAN PARLIAMENT AND OF THE COUNCIL concerning the placing on the market and use of biocidal products. </ns2:title>"+
                "<ns2:extension xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"ns3:ExpressionExtensionType\"/>"+
                "<ns2:manifestation>"+
                "<ns2:reference_manifestation format=\"DOC\" sequence=\"1\" page_count=\"4\">2689090</ns2:reference_manifestation>"+
                "</ns2:manifestation>"+
                "<ns2:manifestation>"+
                "<ns2:reference_manifestation format=\"PDF\" sequence=\"1\" page_count=\"4\">2689096</ns2:reference_manifestation>"+
                "</ns2:manifestation>"+
                "</ns2:expression>"+
                "<ns2:expression>"+
                "<ns2:language>FRA</ns2:language>"+
                "<ns2:title lang=\"FRA\">AVIS DE LA COMMISSION conformément à l'article 294, paragraphe 7, point c), du traité sur le fonctionnement de l'Union européenne sur l'[les]amendement[s] du Parlement européen à la position du Conseil concernant la proposition de RÈGLEMENT DU PARLEMENT EUROPÉEN ET DU CONSEIL concernant la mise sur le marché et l'utilisation des produits biocides </ns2:title>"+
                "<ns2:extension xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"ns3:ExpressionExtensionType\"/>"+
                "<ns2:manifestation>"+
                "<ns2:reference_manifestation format=\"DOC\" sequence=\"1\" page_count=\"3\">2689091</ns2:reference_manifestation>"+
                "</ns2:manifestation>"+
                "<ns2:manifestation>"+
                "<ns2:reference_manifestation format=\"PDF\" sequence=\"1\" page_count=\"4\">2689094</ns2:reference_manifestation>"+
                "</ns2:manifestation>"+
                "</ns2:expression>"+
                "<ns2:expression>"+
                "<ns2:language>DEU</ns2:language>"+
                "<ns2:title lang=\"DEU\">STELLUNGNAHME DER KOMMISSION gemäß Artikel 294 Absatz 7 Buchstabe c des Vertrags über die Arbeitsweise der Europäischen Union zu den Abänderungen des Europäischen Parlaments an dem Standpunkt des Rates betreffend den Vorschlag für eine VERORDNUNG DES EUROPÄISCHEN PARLAMENTS UND DES RATES über das Inverkehrbringen und die Verwendung von Biozidprodukten </ns2:title>"+
                "<ns2:extension xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"ns3:ExpressionExtensionType\"/>"+
                "<ns2:manifestation>"+
                "<ns2:reference_manifestation format=\"DOC\" sequence=\"1\" page_count=\"4\">2689089</ns2:reference_manifestation>"+
                "</ns2:manifestation>"+
                "<ns2:manifestation>"+
                "<ns2:reference_manifestation format=\"PDF\" sequence=\"1\" page_count=\"4\">2689095</ns2:reference_manifestation>"+
                "</ns2:manifestation>"+
                "</ns2:expression>"+
                "</ns2:work>"+
                "</ns2:event_legal>"+
                "</ns2:procedure_interinstitutional>"+
                "</transmission>"+
                "</ns5:ec_transmission_request>";

        HashMap<String,String> messageAttachmentsMap = new HashMap<>();
        when(attachmentHandler.getMapOfAttachmentFilenamesPerReferenceIds(messageId)).thenReturn(messageAttachmentsMap);

        EgreffeTransmissionVO egreffeTransmission = egreffeUtils.getListOfWorks(payloadXml, messageId);


        assertThat(egreffeTransmission, notNullValue());
        assertThat(egreffeTransmission.getComments(), is(""));
        assertThat(egreffeTransmission.getAdoptionDate().toString(), is("Wed Nov 07 15:38:49 CET 2012"));
        assertThat(egreffeTransmission.getListOfWorks(), notNullValue());
        assertThat(egreffeTransmission.getListOfWorks().size(), is(1));

        //Check the order of the works and the proper order of the languages (aka sort was successful)
        assertThat(egreffeTransmission.getListOfWorks().get(0).getLanguages().get(0), is("DEU"));
        assertThat(egreffeTransmission.getListOfWorks().get(0).getLanguages().get(1), is("ENG"));
        assertThat(egreffeTransmission.getListOfWorks().get(0).getLanguages().get(2), is("FRA"));

        //Check the order of the works and the proper order of the documents/languages (aka sort was successful)
        assertThat(egreffeTransmission.getListOfWorks().get(0).getDocumentsList().get("DEU").get(1).size(), is(0));
        assertThat(egreffeTransmission.getListOfWorks().get(0).getDocumentsList().get("ENG").get(1).size(), is(0));
        assertThat(egreffeTransmission.getListOfWorks().get(0).getDocumentsList().get("FRA").get(1).size(), is(0));


        assertThat(messageAttachmentsMap.size(), is(0));
    }

    @Test
    public void testGetListOfWorks_caseEC_Workflow_NonFilter() throws Exception {
        String payloadXml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>"+
                "<ns5:ec_transmission_request xmlns=\"http://publications.europa.eu/resource/core-metadata-transmission\" xmlns:ns2=\"http://publications.europa.eu/resource/core-metadata\" xmlns:ns3=\"urn:eu:ec:cm:common:extensions:v10\" xmlns:ns4=\"urn:eu:ec:publication:v10\" xmlns:ns5=\"urn:eu:ec:transmission:v10\">"+
                "<transmission id=\"193695\">"+
                "<institution>COM</institution>"+
                "<service>SG Greffe A.1</service>"+
                "<context>e-Greffe</context>"+
                "<date_time>2012-11-30T17:23:43.192</date_time>"+
                "<comment>langue originale : EN. Les autres versions linguistiques seront transmises dès que possible</comment>"+
                "<extension xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"ns3:TransmissionExtensionType\">"+
                "<ns3:sender/>"+
                "</extension>"+
                "<workflow>"+
                "<legal_basis_list/>"+
                "<addressee_list>"+
                "<agent role=\"ADDRESSEE_ACT\">EP</agent>"+
                "<agent role=\"ADDRESSEE_CONSULT_MANDATORY\">NP</agent>"+
                "<agent role=\"ADDRESSEE_ACT\">CONSIL</agent>"+
                "</addressee_list>"+
                "<extension xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"ns3:WorkflowExtensionType\">"+
                "<ns3:workflow_number ns2:public=\"false\">"+
                "<ns3:code>PO</ns3:code>"+
                "<ns3:year>2012</ns3:year>"+
                "<ns3:number>5039</ns3:number>"+
                "</ns3:workflow_number>"+
                "<ns3:adoption_date_time>2012-07-18T09:00:00.000</ns3:adoption_date_time>"+
                "<ns3:greffe_manager ns2:public=\"false\">"+
                "<ns3:first_name>Stefanie</ns3:first_name>"+
                "<ns3:last_name>HEILEMANN</ns3:last_name>"+
                "<ns3:person_details>"+
                "<ns3:phone_number>61797</ns3:phone_number>"+
                "<ns3:email_address>DIGIT-EGREFFE-TEST@ec.europa.eu</ns3:email_address>"+
                "</ns3:person_details>"+
                "</ns3:greffe_manager>"+
                "<ns3:agent_person_list>"+
                "<ns3:agent_person role=\"RESP_PERS\">José Manuel BARROSO</ns3:agent_person>"+
                "</ns3:agent_person_list>"+
                "<ns3:eea_relevance>false</ns3:eea_relevance>"+
                "<ns3:codified_version>false</ns3:codified_version>"+
                "<ns3:durability>D</ns3:durability>"+
                "</extension>"+
                "<ns2:work>"+
                "<ns2:type_work>REPORT</ns2:type_work>"+
                "<ns2:identifier_work>COM(2012)411/700701</ns2:identifier_work>"+
                "<ns2:agent_work role=\"AUT\">SG</ns2:agent_work>"+
                "<ns2:date_work>2012-07-18</ns2:date_work>"+
                "<ns2:version_work>final</ns2:version_work>"+
                "<ns2:extension xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"ns3:WorkExtensionType\">"+
                "<ns3:category>ACT</ns3:category>"+
                "<ns3:original_language_code>ENG</ns3:original_language_code>"+
                "<ns3:internal_number>"+
                "<ns3:code>COM</ns3:code>"+
                "<ns3:year>2012</ns3:year>"+
                "<ns3:number>411</ns3:number>"+
                "</ns3:internal_number>"+
                "<ns3:sequence>1</ns3:sequence>"+
                "<ns3:security_info>"+
                "<ns3:classification>NORMAL</ns3:classification>"+
                "</ns3:security_info>"+
                "<ns3:summary_title lang=\"ENG FRA\">Rapport de la Commission au Parlement européen et au Conseil sur les progrès en Bulgarie dans le cadre du mécanisme de coopération et de vérification</ns3:summary_title>"+
                "</ns2:extension>"+
                "<ns2:expression>"+
                "<ns2:language>FIN</ns2:language>"+
                "<ns2:title lang=\"FIN\">KOMISSION KERTOMUS EUROOPAN PARLAMENTILLE JA NEUVOSTOLLE yhteistyö- ja seurantamekanismin puitteissa saavutetusta edistyksestä Bulgariassa </ns2:title>"+
                "<ns2:extension xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"ns3:ExpressionExtensionType\"/>"+
                "<ns2:manifestation>"+
                "<ns2:reference_manifestation format=\"DOC\" sequence=\"1\" page_count=\"3\">2671687</ns2:reference_manifestation>"+
                "</ns2:manifestation>"+
                "<ns2:manifestation>"+
                "<ns2:reference_manifestation format=\"PDF\" sequence=\"1\" page_count=\"23\">2671716</ns2:reference_manifestation>"+
                "</ns2:manifestation>"+
                "</ns2:expression>"+
                "<ns2:expression>"+
                "<ns2:language>SWE</ns2:language>"+
                "<ns2:title lang=\"SWE\">RAPPORT FRÅN KOMMISSIONEN TILL EUROPAPARLAMENTET OCH RÅDET om framstegen i Bulgarien inom ramen för samarbets- och kontrollmekanismen </ns2:title>"+
                "<ns2:extension xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"ns3:ExpressionExtensionType\"/>"+
                "<ns2:manifestation>"+
                "<ns2:reference_manifestation format=\"DOC\" sequence=\"1\" page_count=\"3\">2671680</ns2:reference_manifestation>"+
                "</ns2:manifestation>"+
                "<ns2:manifestation>"+
                "<ns2:reference_manifestation format=\"PDF\" sequence=\"1\" page_count=\"22\">2671714</ns2:reference_manifestation>"+
                "</ns2:manifestation>"+
                "</ns2:expression>"+
                "<ns2:expression>"+
                "<ns2:language>POR</ns2:language>"+
                "<ns2:title lang=\"POR\">RELATÓRIO DA COMISSÃO AO PARLAMENTO EUROPEU E AO CONSELHO sobre os progressos realizados pela Bulgária no âmbito do mecanismo de cooperação e de verificação </ns2:title>"+
                "<ns2:extension xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"ns3:ExpressionExtensionType\"/>"+
                "<ns2:manifestation>"+
                "<ns2:reference_manifestation format=\"DOC\" sequence=\"1\" page_count=\"28\">2671675</ns2:reference_manifestation>"+
                "</ns2:manifestation>"+
                "<ns2:manifestation>"+
                "<ns2:reference_manifestation format=\"PDF\" sequence=\"1\" page_count=\"24\">2671706</ns2:reference_manifestation>"+
                "</ns2:manifestation>"+
                "</ns2:expression>"+
                "</ns2:work>"+
                "<ns2:work>"+
                "<ns2:type_work>SWD</ns2:type_work>"+
                "<ns2:identifier_work>SWD(2012)232/700860</ns2:identifier_work>"+
                "<ns2:agent_work role=\"AUT\">SG</ns2:agent_work>"+
                "<ns2:date_work>2012-07-18</ns2:date_work>"+
                "<ns2:version_work>final</ns2:version_work>"+
                "<ns2:extension xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"ns3:WorkExtensionType\">"+
                "<ns3:category>DOCUMENT</ns3:category>"+
                "<ns3:original_language_code>ENG</ns3:original_language_code>"+
                "<ns3:internal_number>"+
                "<ns3:code>SWD</ns3:code>"+
                "<ns3:year>2012</ns3:year>"+
                "<ns3:number>232</ns3:number>"+
                "</ns3:internal_number>"+
                "<ns3:parent_work_reference ref=\"COM(2012)411/700701\"/>"+
                "<ns3:sequence>1</ns3:sequence>"+
                "<ns3:security_info>"+
                "<ns3:classification>NORMAL</ns3:classification>"+
                "</ns3:security_info>"+
                "<ns3:summary_title lang=\"ENG FRA\">Bulgaria Technical Report</ns3:summary_title>"+
                "</ns2:extension>"+
                "<ns2:expression>"+
                "<ns2:language>ENG</ns2:language>"+
                "<ns2:title lang=\"ENG\">COMMISSION STAFF WORKING DOCUMENT BULGARIA: Technical Report Accompanying the document REPORT FROM THE COMMISSION TO THE EUROPEAN PARLIAMENT AND THE COUNCIL On Progress in Bulgaria under the Co-operation and Verification mechanism</ns2:title>"+
                "<ns2:extension xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"ns3:ExpressionExtensionType\"/>"+
                "<ns2:manifestation>"+
                "<ns2:reference_manifestation format=\"DOC\" sequence=\"1\" page_count=\"7\">2669097</ns2:reference_manifestation>"+
                "</ns2:manifestation>"+
                "<ns2:manifestation>"+
                "<ns2:reference_manifestation format=\"PDF\" sequence=\"1\" page_count=\"37\">2670433</ns2:reference_manifestation>"+
                "</ns2:manifestation>"+
                "</ns2:expression>"+
                "<ns2:expression>"+
                "<ns2:language>BUL</ns2:language>"+
                "<ns2:title/>"+
                "<ns2:extension xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"ns3:ExpressionExtensionType\"/>"+
                "</ns2:expression>"+
                "</ns2:work>"+
                "</workflow>"+
                "</transmission>"+
                "</ns5:ec_transmission_request>";

        HashMap<String,String> messageAttachmentsMap = new HashMap<>();
        messageAttachmentsMap.put("2671687", "Filename1");
        messageAttachmentsMap.put("2671716", "Filename2");
        messageAttachmentsMap.put("2671680", "Filename3");
        messageAttachmentsMap.put("2671714", "Filename4");
        messageAttachmentsMap.put("2671675", "Filename5");
        messageAttachmentsMap.put("2671706", "Filename6");
        messageAttachmentsMap.put("2669097", "Filename7");
        messageAttachmentsMap.put("2670433", "Filename8");

        when(attachmentHandler.getMapOfAttachmentFilenamesPerReferenceIds(messageId)).thenReturn(messageAttachmentsMap);

        EgreffeTransmissionVO egreffeTransmission = egreffeUtils.getListOfWorks(payloadXml, messageId);


        assertThat(egreffeTransmission, notNullValue());
        assertThat(egreffeTransmission.getComments(), is("langue originale : EN. Les autres versions linguistiques seront transmises dès que possible"));
        assertThat(egreffeTransmission.getAdoptionDate().toString(), is("Wed Jul 18 09:00:00 CEST 2012"));
        assertThat(egreffeTransmission.getListOfWorks(), notNullValue());
        assertThat(egreffeTransmission.getListOfWorks().size(), is(2));

        //Check the order of the works and the proper order of the languages (aka sort was successful)
        assertThat(egreffeTransmission.getListOfWorks().get(0).getLanguages().get(0), is("FIN"));
        assertThat(egreffeTransmission.getListOfWorks().get(0).getLanguages().get(1), is("POR"));
        assertThat(egreffeTransmission.getListOfWorks().get(0).getLanguages().get(2), is("SWE"));

        assertThat(egreffeTransmission.getListOfWorks().get(1).getLanguages().get(0), is("BUL"));
        assertThat(egreffeTransmission.getListOfWorks().get(1).getLanguages().get(1), is("ENG"));


        //Check the order of the works and the proper order of the documents/languages (aka sort was successful)
        assertThat(egreffeTransmission.getListOfWorks().get(0).getDocumentsList().get("FIN").get(1).size(), is(2));
        assertThat(egreffeTransmission.getListOfWorks().get(0).getDocumentsList().get("POR").get(1).size(), is(2));
        assertThat(egreffeTransmission.getListOfWorks().get(0).getDocumentsList().get("SWE").get(1).size(), is(2));

        assertThat(egreffeTransmission.getListOfWorks().get(1).getDocumentsList().get("BUL").get(1), is(nullValue()));
        assertThat(egreffeTransmission.getListOfWorks().get(1).getDocumentsList().get("ENG").get(1).size(), is(2));


        assertThat(egreffeTransmission.getListOfWorks().get(0).getDocumentsList().get("FIN").get(1).get(1).getValue(), is("2671716"));
        assertThat(egreffeTransmission.getListOfWorks().get(0).getDocumentsList().get("POR").get(1).get(1).getValue(), is("2671706"));
        assertThat(egreffeTransmission.getListOfWorks().get(0).getDocumentsList().get("SWE").get(1).get(1).getValue(), is("2671714"));

        assertThat(egreffeTransmission.getListOfWorks().get(1).getDocumentsList().get("ENG").get(1).get(1).getValue(), is("2670433"));

        assertThat(messageAttachmentsMap.size(), is(8));
    }

    @Test
    public void testGetListOfWorks_caseEC_Workflow10_Filter_MoreAttachments() throws Exception {
        String payloadXml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>"+
                "<ns5:ec_transmission_request xmlns=\"http://publications.europa.eu/resource/core-metadata-transmission\" xmlns:ns2=\"http://publications.europa.eu/resource/core-metadata\" xmlns:ns3=\"urn:eu:ec:cm:common:extensions:v10\" xmlns:ns4=\"urn:eu:ec:publication:v10\" xmlns:ns5=\"urn:eu:ec:transmission:v10\">"+
                "<transmission id=\"193695\">"+
                "<institution>COM</institution>"+
                "<service>SG Greffe A.1</service>"+
                "<context>e-Greffe</context>"+
                "<date_time>2012-11-30T17:23:43.192</date_time>"+
                "<comment>langue originale : EN. Les autres versions linguistiques seront transmises dès que possible</comment>"+
                "<extension xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"ns3:TransmissionExtensionType\">"+
                "<ns3:sender/>"+
                "</extension>"+
                "<workflow>"+
                "<legal_basis_list/>"+
                "<addressee_list>"+
                "<agent role=\"ADDRESSEE_ACT\">EP</agent>"+
                "<agent role=\"ADDRESSEE_CONSULT_MANDATORY\">NP</agent>"+
                "<agent role=\"ADDRESSEE_ACT\">CONSIL</agent>"+
                "</addressee_list>"+
                "<extension xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"ns3:WorkflowExtensionType\">"+
                "<ns3:workflow_number ns2:public=\"false\">"+
                "<ns3:code>PO</ns3:code>"+
                "<ns3:year>2012</ns3:year>"+
                "<ns3:number>5039</ns3:number>"+
                "</ns3:workflow_number>"+
                "<ns3:adoption_date_time>2012-07-18T09:00:00.000</ns3:adoption_date_time>"+
                "<ns3:greffe_manager ns2:public=\"false\">"+
                "<ns3:first_name>Stefanie</ns3:first_name>"+
                "<ns3:last_name>HEILEMANN</ns3:last_name>"+
                "<ns3:person_details>"+
                "<ns3:phone_number>61797</ns3:phone_number>"+
                "<ns3:email_address>DIGIT-EGREFFE-TEST@ec.europa.eu</ns3:email_address>"+
                "</ns3:person_details>"+
                "</ns3:greffe_manager>"+
                "<ns3:agent_person_list>"+
                "<ns3:agent_person role=\"RESP_PERS\">José Manuel BARROSO</ns3:agent_person>"+
                "</ns3:agent_person_list>"+
                "<ns3:eea_relevance>false</ns3:eea_relevance>"+
                "<ns3:codified_version>false</ns3:codified_version>"+
                "<ns3:durability>D</ns3:durability>"+
                "</extension>"+
                "<ns2:work>"+
                "<ns2:type_work>REPORT</ns2:type_work>"+
                "<ns2:identifier_work>COM(2012)411/700701</ns2:identifier_work>"+
                "<ns2:agent_work role=\"AUT\">SG</ns2:agent_work>"+
                "<ns2:date_work>2012-07-18</ns2:date_work>"+
                "<ns2:version_work>final</ns2:version_work>"+
                "<ns2:extension xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"ns3:WorkExtensionType\">"+
                "<ns3:category>ACT</ns3:category>"+
                "<ns3:original_language_code>ENG</ns3:original_language_code>"+
                "<ns3:internal_number>"+
                "<ns3:code>COM</ns3:code>"+
                "<ns3:year>2012</ns3:year>"+
                "<ns3:number>411</ns3:number>"+
                "</ns3:internal_number>"+
                "<ns3:sequence>1</ns3:sequence>"+
                "<ns3:security_info>"+
                "<ns3:classification>NORMAL</ns3:classification>"+
                "</ns3:security_info>"+
                "<ns3:summary_title lang=\"ENG FRA\">Rapport de la Commission au Parlement européen et au Conseil sur les progrès en Bulgarie dans le cadre du mécanisme de coopération et de vérification</ns3:summary_title>"+
                "</ns2:extension>"+
                "<ns2:expression>"+
                "<ns2:language>FIN</ns2:language>"+
                "<ns2:title lang=\"FIN\">KOMISSION KERTOMUS EUROOPAN PARLAMENTILLE JA NEUVOSTOLLE yhteistyö- ja seurantamekanismin puitteissa saavutetusta edistyksestä Bulgariassa </ns2:title>"+
                "<ns2:extension xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"ns3:ExpressionExtensionType\"/>"+
                "<ns2:manifestation>"+
                "<ns2:reference_manifestation format=\"DOC\" sequence=\"1\" page_count=\"3\">2671687</ns2:reference_manifestation>"+
                "</ns2:manifestation>"+
                "<ns2:manifestation>"+
                "<ns2:reference_manifestation format=\"PDF\" sequence=\"1\" page_count=\"23\">2671716</ns2:reference_manifestation>"+
                "</ns2:manifestation>"+
                "</ns2:expression>"+
                "<ns2:expression>"+
                "<ns2:language>SWE</ns2:language>"+
                "<ns2:title lang=\"SWE\">RAPPORT FRÅN KOMMISSIONEN TILL EUROPAPARLAMENTET OCH RÅDET om framstegen i Bulgarien inom ramen för samarbets- och kontrollmekanismen </ns2:title>"+
                "<ns2:extension xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"ns3:ExpressionExtensionType\"/>"+
                "<ns2:manifestation>"+
                "<ns2:reference_manifestation format=\"DOC\" sequence=\"1\" page_count=\"3\">2671680</ns2:reference_manifestation>"+
                "</ns2:manifestation>"+
                "<ns2:manifestation>"+
                "<ns2:reference_manifestation format=\"PDF\" sequence=\"1\" page_count=\"22\">2671714</ns2:reference_manifestation>"+
                "</ns2:manifestation>"+
                "</ns2:expression>"+
                "<ns2:expression>"+
                "<ns2:language>POR</ns2:language>"+
                "<ns2:title lang=\"POR\">RELATÓRIO DA COMISSÃO AO PARLAMENTO EUROPEU E AO CONSELHO sobre os progressos realizados pela Bulgária no âmbito do mecanismo de cooperação e de verificação </ns2:title>"+
                "<ns2:extension xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"ns3:ExpressionExtensionType\"/>"+
                "<ns2:manifestation>"+
                "<ns2:reference_manifestation format=\"DOC\" sequence=\"1\" page_count=\"28\">2671675</ns2:reference_manifestation>"+
                "</ns2:manifestation>"+
                "<ns2:manifestation>"+
                "<ns2:reference_manifestation format=\"PDF\" sequence=\"1\" page_count=\"24\">2671706</ns2:reference_manifestation>"+
                "</ns2:manifestation>"+
                "</ns2:expression>"+
                "</ns2:work>"+
                "<ns2:work>"+
                "<ns2:type_work>SWD</ns2:type_work>"+
                "<ns2:identifier_work>SWD(2012)232/700860</ns2:identifier_work>"+
                "<ns2:agent_work role=\"AUT\">SG</ns2:agent_work>"+
                "<ns2:date_work>2012-07-18</ns2:date_work>"+
                "<ns2:version_work>final</ns2:version_work>"+
                "<ns2:extension xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"ns3:WorkExtensionType\">"+
                "<ns3:category>DOCUMENT</ns3:category>"+
                "<ns3:original_language_code>ENG</ns3:original_language_code>"+
                "<ns3:internal_number>"+
                "<ns3:code>SWD</ns3:code>"+
                "<ns3:year>2012</ns3:year>"+
                "<ns3:number>232</ns3:number>"+
                "</ns3:internal_number>"+
                "<ns3:parent_work_reference ref=\"COM(2012)411/700701\"/>"+
                "<ns3:sequence>1</ns3:sequence>"+
                "<ns3:security_info>"+
                "<ns3:classification>NORMAL</ns3:classification>"+
                "</ns3:security_info>"+
                "<ns3:summary_title lang=\"ENG FRA\">Bulgaria Technical Report</ns3:summary_title>"+
                "</ns2:extension>"+
                "<ns2:expression>"+
                "<ns2:language>ENG</ns2:language>"+
                "<ns2:title lang=\"ENG\">COMMISSION STAFF WORKING DOCUMENT BULGARIA: Technical Report Accompanying the document REPORT FROM THE COMMISSION TO THE EUROPEAN PARLIAMENT AND THE COUNCIL On Progress in Bulgaria under the Co-operation and Verification mechanism</ns2:title>"+
                "<ns2:extension xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"ns3:ExpressionExtensionType\"/>"+
                "<ns2:manifestation>"+
                "<ns2:reference_manifestation format=\"DOC\" sequence=\"1\" page_count=\"7\">2669097</ns2:reference_manifestation>"+
                "</ns2:manifestation>"+
                "<ns2:manifestation>"+
                "<ns2:reference_manifestation format=\"PDF\" sequence=\"1\" page_count=\"37\">2670433</ns2:reference_manifestation>"+
                "</ns2:manifestation>"+
                "</ns2:expression>"+
                "<ns2:expression>"+
                "<ns2:language>BUL</ns2:language>"+
                "<ns2:title/>"+
                "<ns2:extension xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"ns3:ExpressionExtensionType\"/>"+
                "</ns2:expression>"+
                "</ns2:work>"+
                "</workflow>"+
                "</transmission>"+
                "</ns5:ec_transmission_request>";

        HashMap<String,String> messageAttachmentsMap = new HashMap<>();
        messageAttachmentsMap.put("2671687", "Filename1");
        messageAttachmentsMap.put("2671716", "Filename2");
        messageAttachmentsMap.put("2671680", "Filename3");
        messageAttachmentsMap.put("2671714", "Filename4");
        messageAttachmentsMap.put("2671675", "Filename5");
        messageAttachmentsMap.put("2671706", "Filename6");
        messageAttachmentsMap.put("2669097", "Filename7");
        messageAttachmentsMap.put("2670433", "Filename8");
        messageAttachmentsMap.put("7546435", "Filename9");
        messageAttachmentsMap.put("7362522", "Filename10");

        when(attachmentHandler.getMapOfAttachmentFilenamesPerReferenceIds(messageId)).thenReturn(messageAttachmentsMap);

        EgreffeTransmissionVO egreffeTransmission = egreffeUtils.getListOfWorks(payloadXml, messageId);


        assertThat(egreffeTransmission, notNullValue());
        assertThat(egreffeTransmission.getComments(), is("langue originale : EN. Les autres versions linguistiques seront transmises dès que possible"));
        assertThat(egreffeTransmission.getAdoptionDate().toString(), is("Wed Jul 18 09:00:00 CEST 2012"));
        assertThat(egreffeTransmission.getListOfWorks(), notNullValue());
        assertThat(egreffeTransmission.getListOfWorks().size(), is(2));

        //Check the order of the works and the proper order of the languages (aka sort was successful)
        assertThat(egreffeTransmission.getListOfWorks().get(0).getLanguages().get(0), is("FIN"));
        assertThat(egreffeTransmission.getListOfWorks().get(0).getLanguages().get(1), is("POR"));
        assertThat(egreffeTransmission.getListOfWorks().get(0).getLanguages().get(2), is("SWE"));

        assertThat(egreffeTransmission.getListOfWorks().get(1).getLanguages().get(0), is("BUL"));
        assertThat(egreffeTransmission.getListOfWorks().get(1).getLanguages().get(1), is("ENG"));


        //Check the order of the works and the proper order of the documents/languages (aka sort was successful)
        assertThat(egreffeTransmission.getListOfWorks().get(0).getDocumentsList().get("FIN").get(1).size(), is(2));
        assertThat(egreffeTransmission.getListOfWorks().get(0).getDocumentsList().get("POR").get(1).size(), is(2));
        assertThat(egreffeTransmission.getListOfWorks().get(0).getDocumentsList().get("SWE").get(1).size(), is(2));

        assertThat(egreffeTransmission.getListOfWorks().get(1).getDocumentsList().get("BUL").get(1), is(nullValue()));
        assertThat(egreffeTransmission.getListOfWorks().get(1).getDocumentsList().get("ENG").get(1).size(), is(2));


        assertThat(egreffeTransmission.getListOfWorks().get(0).getDocumentsList().get("FIN").get(1).get(1).getValue(), is("2671716"));
        assertThat(egreffeTransmission.getListOfWorks().get(0).getDocumentsList().get("POR").get(1).get(1).getValue(), is("2671706"));
        assertThat(egreffeTransmission.getListOfWorks().get(0).getDocumentsList().get("SWE").get(1).get(1).getValue(), is("2671714"));

        assertThat(egreffeTransmission.getListOfWorks().get(1).getDocumentsList().get("ENG").get(1).get(1).getValue(), is("2670433"));

        assertThat(messageAttachmentsMap.size(), is(10));
    }

    @Test
    public void testGetListOfWorks_caseEC_Workflow11_Filter_MoreAttachments() throws Exception {
        String payloadXml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>"+
                "<ns5:ec_transmission_request xmlns=\"http://publications.europa.eu/resource/core-metadata-transmission\" xmlns:ns2=\"http://publications.europa.eu/resource/core-metadata\" xmlns:ns3=\"urn:eu:ec:cm:common:extensions:v11\" xmlns:ns4=\"urn:eu:ec:publication:v11\" xmlns:ns5=\"urn:eu:ec:transmission:v11\">"+
                "<transmission id=\"193695\">"+
                "<institution>COM</institution>"+
                "<service>SG Greffe A.1</service>"+
                "<context>e-Greffe</context>"+
                "<date_time>2012-11-30T17:23:43.192</date_time>"+
                "<comment>langue originale : EN. Les autres versions linguistiques seront transmises dès que possible</comment>"+
                "<extension xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"ns3:TransmissionExtensionType\">"+
                "<ns3:sender/>"+
                "</extension>"+
                "<workflow>"+
                "<legal_basis_list/>"+
                "<addressee_list>"+
                "<agent role=\"ADDRESSEE_ACT\">EP</agent>"+
                "<agent role=\"ADDRESSEE_CONSULT_MANDATORY\">NP</agent>"+
                "<agent role=\"ADDRESSEE_ACT\">CONSIL</agent>"+
                "</addressee_list>"+
                "<extension xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"ns3:WorkflowExtensionType\">"+
                "<ns3:workflow_number ns2:public=\"false\">"+
                "<ns3:code>PO</ns3:code>"+
                "<ns3:year>2012</ns3:year>"+
                "<ns3:number>5039</ns3:number>"+
                "</ns3:workflow_number>"+
                "<ns3:adoption_date_time>2012-07-18T09:00:00.000</ns3:adoption_date_time>"+
                "<ns3:greffe_manager ns2:public=\"false\">"+
                "<ns3:first_name>Stefanie</ns3:first_name>"+
                "<ns3:last_name>HEILEMANN</ns3:last_name>"+
                "<ns3:person_details>"+
                "<ns3:phone_number>61797</ns3:phone_number>"+
                "<ns3:email_address>DIGIT-EGREFFE-TEST@ec.europa.eu</ns3:email_address>"+
                "</ns3:person_details>"+
                "</ns3:greffe_manager>"+
                "<ns3:agent_person_list>"+
                "<ns3:agent_person role=\"RESP_PERS\">José Manuel BARROSO</ns3:agent_person>"+
                "</ns3:agent_person_list>"+
                "<ns3:eea_relevance>false</ns3:eea_relevance>"+
                "<ns3:codified_version>false</ns3:codified_version>"+
                "<ns3:durability>D</ns3:durability>"+
                "</extension>"+
                "<ns2:work>"+
                "<ns2:type_work>REPORT</ns2:type_work>"+
                "<ns2:identifier_work>COM(2012)411/700701</ns2:identifier_work>"+
                "<ns2:agent_work role=\"AUT\">SG</ns2:agent_work>"+
                "<ns2:date_work>2012-07-18</ns2:date_work>"+
                "<ns2:version_work>final</ns2:version_work>"+
                "<ns2:extension xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"ns3:WorkExtensionType\">"+
                "<ns3:category>ACT</ns3:category>"+
                "<ns3:original_language_code>ENG</ns3:original_language_code>"+
                "<ns3:internal_number>"+
                "<ns3:code>COM</ns3:code>"+
                "<ns3:year>2012</ns3:year>"+
                "<ns3:number>411</ns3:number>"+
                "</ns3:internal_number>"+
                "<ns3:sequence>1</ns3:sequence>"+
                "<ns3:security_info>"+
                "<ns3:classification>NORMAL</ns3:classification>"+
                "</ns3:security_info>"+
                "<ns3:summary_title lang=\"ENG FRA\">Rapport de la Commission au Parlement européen et au Conseil sur les progrès en Bulgarie dans le cadre du mécanisme de coopération et de vérification</ns3:summary_title>"+
                "</ns2:extension>"+
                "<ns2:expression>"+
                "<ns2:language>FIN</ns2:language>"+
                "<ns2:title lang=\"FIN\">KOMISSION KERTOMUS EUROOPAN PARLAMENTILLE JA NEUVOSTOLLE yhteistyö- ja seurantamekanismin puitteissa saavutetusta edistyksestä Bulgariassa </ns2:title>"+
                "<ns2:extension xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"ns3:ExpressionExtensionType\"/>"+
                "<ns2:manifestation>"+
                "<ns2:reference_manifestation format=\"DOC\" sequence=\"1\" page_count=\"3\">2671687</ns2:reference_manifestation>"+
                "</ns2:manifestation>"+
                "<ns2:manifestation>"+
                "<ns2:reference_manifestation format=\"PDF\" sequence=\"1\" page_count=\"23\">2671716</ns2:reference_manifestation>"+
                "</ns2:manifestation>"+
                "</ns2:expression>"+
                "<ns2:expression>"+
                "<ns2:language>SWE</ns2:language>"+
                "<ns2:title lang=\"SWE\">RAPPORT FRÅN KOMMISSIONEN TILL EUROPAPARLAMENTET OCH RÅDET om framstegen i Bulgarien inom ramen för samarbets- och kontrollmekanismen </ns2:title>"+
                "<ns2:extension xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"ns3:ExpressionExtensionType\"/>"+
                "<ns2:manifestation>"+
                "<ns2:reference_manifestation format=\"DOC\" sequence=\"1\" page_count=\"3\">2671680</ns2:reference_manifestation>"+
                "</ns2:manifestation>"+
                "<ns2:manifestation>"+
                "<ns2:reference_manifestation format=\"PDF\" sequence=\"1\" page_count=\"22\">2671714</ns2:reference_manifestation>"+
                "</ns2:manifestation>"+
                "</ns2:expression>"+
                "<ns2:expression>"+
                "<ns2:language>POR</ns2:language>"+
                "<ns2:title lang=\"POR\">RELATÓRIO DA COMISSÃO AO PARLAMENTO EUROPEU E AO CONSELHO sobre os progressos realizados pela Bulgária no âmbito do mecanismo de cooperação e de verificação </ns2:title>"+
                "<ns2:extension xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"ns3:ExpressionExtensionType\"/>"+
                "<ns2:manifestation>"+
                "<ns2:reference_manifestation format=\"DOC\" sequence=\"1\" page_count=\"28\">2671675</ns2:reference_manifestation>"+
                "</ns2:manifestation>"+
                "<ns2:manifestation>"+
                "<ns2:reference_manifestation format=\"PDF\" sequence=\"1\" page_count=\"24\">2671706</ns2:reference_manifestation>"+
                "</ns2:manifestation>"+
                "</ns2:expression>"+
                "</ns2:work>"+
                "<ns2:work>"+
                "<ns2:type_work>SWD</ns2:type_work>"+
                "<ns2:identifier_work>SWD(2012)232/700860</ns2:identifier_work>"+
                "<ns2:agent_work role=\"AUT\">SG</ns2:agent_work>"+
                "<ns2:date_work>2012-07-18</ns2:date_work>"+
                "<ns2:version_work>final</ns2:version_work>"+
                "<ns2:extension xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"ns3:WorkExtensionType\">"+
                "<ns3:category>DOCUMENT</ns3:category>"+
                "<ns3:original_language_code>ENG</ns3:original_language_code>"+
                "<ns3:internal_number>"+
                "<ns3:code>SWD</ns3:code>"+
                "<ns3:year>2012</ns3:year>"+
                "<ns3:number>232</ns3:number>"+
                "</ns3:internal_number>"+
                "<ns3:parent_work_reference ref=\"COM(2012)411/700701\"/>"+
                "<ns3:sequence>1</ns3:sequence>"+
                "<ns3:security_info>"+
                "<ns3:classification>NORMAL</ns3:classification>"+
                "</ns3:security_info>"+
                "<ns3:summary_title lang=\"ENG FRA\">Bulgaria Technical Report</ns3:summary_title>"+
                "</ns2:extension>"+
                "<ns2:expression>"+
                "<ns2:language>ENG</ns2:language>"+
                "<ns2:title lang=\"ENG\">COMMISSION STAFF WORKING DOCUMENT BULGARIA: Technical Report Accompanying the document REPORT FROM THE COMMISSION TO THE EUROPEAN PARLIAMENT AND THE COUNCIL On Progress in Bulgaria under the Co-operation and Verification mechanism</ns2:title>"+
                "<ns2:extension xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"ns3:ExpressionExtensionType\"/>"+
                "<ns2:manifestation>"+
                "<ns2:reference_manifestation format=\"DOC\" sequence=\"1\" page_count=\"7\">2669097</ns2:reference_manifestation>"+
                "</ns2:manifestation>"+
                "<ns2:manifestation>"+
                "<ns2:reference_manifestation format=\"PDF\" sequence=\"1\" page_count=\"37\">2670433</ns2:reference_manifestation>"+
                "</ns2:manifestation>"+
                "</ns2:expression>"+
                "<ns2:expression>"+
                "<ns2:language>BUL</ns2:language>"+
                "<ns2:title/>"+
                "<ns2:extension xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"ns3:ExpressionExtensionType\"/>"+
                "</ns2:expression>"+
                "</ns2:work>"+
                "</workflow>"+
                "</transmission>"+
                "</ns5:ec_transmission_request>";

        HashMap<String,String> messageAttachmentsMap = new HashMap<>();
        messageAttachmentsMap.put("2671687", "Filename1");
        messageAttachmentsMap.put("2671716", "Filename2");
        messageAttachmentsMap.put("2671680", "Filename3");
        messageAttachmentsMap.put("2671714", "Filename4");
        messageAttachmentsMap.put("2671675", "Filename5");
        messageAttachmentsMap.put("2671706", "Filename6");
        messageAttachmentsMap.put("2669097", "Filename7");
        messageAttachmentsMap.put("2670433", "Filename8");
        messageAttachmentsMap.put("7546435", "Filename9");
        messageAttachmentsMap.put("7362522", "Filename10");

        when(attachmentHandler.getMapOfAttachmentFilenamesPerReferenceIds(messageId)).thenReturn(messageAttachmentsMap);

        EgreffeTransmissionVO egreffeTransmission = egreffeUtils.getListOfWorks(payloadXml, messageId);


        assertThat(egreffeTransmission, notNullValue());
        assertThat(egreffeTransmission.getComments(), is("langue originale : EN. Les autres versions linguistiques seront transmises dès que possible"));
        assertThat(egreffeTransmission.getAdoptionDate().toString(), is("Wed Jul 18 09:00:00 CEST 2012"));
        assertThat(egreffeTransmission.getListOfWorks(), notNullValue());
        assertThat(egreffeTransmission.getListOfWorks().size(), is(2));

        //Check the order of the works and the proper order of the languages (aka sort was successful)
        assertThat(egreffeTransmission.getListOfWorks().get(0).getLanguages().get(0), is("FIN"));
        assertThat(egreffeTransmission.getListOfWorks().get(0).getLanguages().get(1), is("POR"));
        assertThat(egreffeTransmission.getListOfWorks().get(0).getLanguages().get(2), is("SWE"));

        assertThat(egreffeTransmission.getListOfWorks().get(1).getLanguages().get(0), is("BUL"));
        assertThat(egreffeTransmission.getListOfWorks().get(1).getLanguages().get(1), is("ENG"));


        //Check the order of the works and the proper order of the documents/languages (aka sort was successful)
        assertThat(egreffeTransmission.getListOfWorks().get(0).getDocumentsList().get("FIN").get(1).size(), is(2));
        assertThat(egreffeTransmission.getListOfWorks().get(0).getDocumentsList().get("POR").get(1).size(), is(2));
        assertThat(egreffeTransmission.getListOfWorks().get(0).getDocumentsList().get("SWE").get(1).size(), is(2));

        assertThat(egreffeTransmission.getListOfWorks().get(1).getDocumentsList().get("BUL").get(1), is(nullValue()));
        assertThat(egreffeTransmission.getListOfWorks().get(1).getDocumentsList().get("ENG").get(1).size(), is(2));


        assertThat(egreffeTransmission.getListOfWorks().get(0).getDocumentsList().get("FIN").get(1).get(1).getValue(), is("2671716"));
        assertThat(egreffeTransmission.getListOfWorks().get(0).getDocumentsList().get("POR").get(1).get(1).getValue(), is("2671706"));
        assertThat(egreffeTransmission.getListOfWorks().get(0).getDocumentsList().get("SWE").get(1).get(1).getValue(), is("2671714"));

        assertThat(egreffeTransmission.getListOfWorks().get(1).getDocumentsList().get("ENG").get(1).get(1).getValue(), is("2670433"));

        assertThat(messageAttachmentsMap.size(), is(10));
    }

    @Test
    public void testGetListOfWorks_caseEC_Workflow_Filter_LessAttachments() throws Exception {
        String payloadXml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>"+
                "<ns5:ec_transmission_request xmlns=\"http://publications.europa.eu/resource/core-metadata-transmission\" xmlns:ns2=\"http://publications.europa.eu/resource/core-metadata\" xmlns:ns3=\"urn:eu:ec:cm:common:extensions:v10\" xmlns:ns4=\"urn:eu:ec:publication:v10\" xmlns:ns5=\"urn:eu:ec:transmission:v10\">"+
                "<transmission id=\"193695\">"+
                "<institution>COM</institution>"+
                "<service>SG Greffe A.1</service>"+
                "<context>e-Greffe</context>"+
                "<date_time>2012-11-30T17:23:43.192</date_time>"+
                "<comment>langue originale : EN. Les autres versions linguistiques seront transmises dès que possible</comment>"+
                "<extension xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"ns3:TransmissionExtensionType\">"+
                "<ns3:sender/>"+
                "</extension>"+
                "<workflow>"+
                "<legal_basis_list/>"+
                "<addressee_list>"+
                "<agent role=\"ADDRESSEE_ACT\">EP</agent>"+
                "<agent role=\"ADDRESSEE_CONSULT_MANDATORY\">NP</agent>"+
                "<agent role=\"ADDRESSEE_ACT\">CONSIL</agent>"+
                "</addressee_list>"+
                "<extension xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"ns3:WorkflowExtensionType\">"+
                "<ns3:workflow_number ns2:public=\"false\">"+
                "<ns3:code>PO</ns3:code>"+
                "<ns3:year>2012</ns3:year>"+
                "<ns3:number>5039</ns3:number>"+
                "</ns3:workflow_number>"+
                "<ns3:adoption_date_time>2012-07-18T09:00:00.000</ns3:adoption_date_time>"+
                "<ns3:greffe_manager ns2:public=\"false\">"+
                "<ns3:first_name>Stefanie</ns3:first_name>"+
                "<ns3:last_name>HEILEMANN</ns3:last_name>"+
                "<ns3:person_details>"+
                "<ns3:phone_number>61797</ns3:phone_number>"+
                "<ns3:email_address>DIGIT-EGREFFE-TEST@ec.europa.eu</ns3:email_address>"+
                "</ns3:person_details>"+
                "</ns3:greffe_manager>"+
                "<ns3:agent_person_list>"+
                "<ns3:agent_person role=\"RESP_PERS\">José Manuel BARROSO</ns3:agent_person>"+
                "</ns3:agent_person_list>"+
                "<ns3:eea_relevance>false</ns3:eea_relevance>"+
                "<ns3:codified_version>false</ns3:codified_version>"+
                "<ns3:durability>D</ns3:durability>"+
                "</extension>"+
                "<ns2:work>"+
                "<ns2:type_work>REPORT</ns2:type_work>"+
                "<ns2:identifier_work>COM(2012)411/700701</ns2:identifier_work>"+
                "<ns2:agent_work role=\"AUT\">SG</ns2:agent_work>"+
                "<ns2:date_work>2012-07-18</ns2:date_work>"+
                "<ns2:version_work>final</ns2:version_work>"+
                "<ns2:extension xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"ns3:WorkExtensionType\">"+
                "<ns3:category>ACT</ns3:category>"+
                "<ns3:original_language_code>ENG</ns3:original_language_code>"+
                "<ns3:internal_number>"+
                "<ns3:code>COM</ns3:code>"+
                "<ns3:year>2012</ns3:year>"+
                "<ns3:number>411</ns3:number>"+
                "</ns3:internal_number>"+
                "<ns3:sequence>1</ns3:sequence>"+
                "<ns3:security_info>"+
                "<ns3:classification>NORMAL</ns3:classification>"+
                "</ns3:security_info>"+
                "<ns3:summary_title lang=\"ENG FRA\">Rapport de la Commission au Parlement européen et au Conseil sur les progrès en Bulgarie dans le cadre du mécanisme de coopération et de vérification</ns3:summary_title>"+
                "</ns2:extension>"+
                "<ns2:expression>"+
                "<ns2:language>FIN</ns2:language>"+
                "<ns2:title lang=\"FIN\">KOMISSION KERTOMUS EUROOPAN PARLAMENTILLE JA NEUVOSTOLLE yhteistyö- ja seurantamekanismin puitteissa saavutetusta edistyksestä Bulgariassa </ns2:title>"+
                "<ns2:extension xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"ns3:ExpressionExtensionType\"/>"+
                "<ns2:manifestation>"+
                "<ns2:reference_manifestation format=\"DOC\" sequence=\"1\" page_count=\"3\">2671687</ns2:reference_manifestation>"+
                "</ns2:manifestation>"+
                "<ns2:manifestation>"+
                "<ns2:reference_manifestation format=\"PDF\" sequence=\"1\" page_count=\"23\">2671716</ns2:reference_manifestation>"+
                "</ns2:manifestation>"+
                "</ns2:expression>"+
                "<ns2:expression>"+
                "<ns2:language>SWE</ns2:language>"+
                "<ns2:title lang=\"SWE\">RAPPORT FRÅN KOMMISSIONEN TILL EUROPAPARLAMENTET OCH RÅDET om framstegen i Bulgarien inom ramen för samarbets- och kontrollmekanismen </ns2:title>"+
                "<ns2:extension xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"ns3:ExpressionExtensionType\"/>"+
                "<ns2:manifestation>"+
                "<ns2:reference_manifestation format=\"DOC\" sequence=\"1\" page_count=\"3\">2671680</ns2:reference_manifestation>"+
                "</ns2:manifestation>"+
                "<ns2:manifestation>"+
                "<ns2:reference_manifestation format=\"PDF\" sequence=\"1\" page_count=\"22\">2671714</ns2:reference_manifestation>"+
                "</ns2:manifestation>"+
                "</ns2:expression>"+
                "<ns2:expression>"+
                "<ns2:language>POR</ns2:language>"+
                "<ns2:title lang=\"POR\">RELATÓRIO DA COMISSÃO AO PARLAMENTO EUROPEU E AO CONSELHO sobre os progressos realizados pela Bulgária no âmbito do mecanismo de cooperação e de verificação </ns2:title>"+
                "<ns2:extension xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"ns3:ExpressionExtensionType\"/>"+
                "<ns2:manifestation>"+
                "<ns2:reference_manifestation format=\"DOC\" sequence=\"1\" page_count=\"28\">2671675</ns2:reference_manifestation>"+
                "</ns2:manifestation>"+
                "<ns2:manifestation>"+
                "<ns2:reference_manifestation format=\"PDF\" sequence=\"1\" page_count=\"24\">2671706</ns2:reference_manifestation>"+
                "</ns2:manifestation>"+
                "</ns2:expression>"+
                "</ns2:work>"+
                "<ns2:work>"+
                "<ns2:type_work>SWD</ns2:type_work>"+
                "<ns2:identifier_work>SWD(2012)232/700860</ns2:identifier_work>"+
                "<ns2:agent_work role=\"AUT\">SG</ns2:agent_work>"+
                "<ns2:date_work>2012-07-18</ns2:date_work>"+
                "<ns2:version_work>final</ns2:version_work>"+
                "<ns2:extension xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"ns3:WorkExtensionType\">"+
                "<ns3:category>DOCUMENT</ns3:category>"+
                "<ns3:original_language_code>ENG</ns3:original_language_code>"+
                "<ns3:internal_number>"+
                "<ns3:code>SWD</ns3:code>"+
                "<ns3:year>2012</ns3:year>"+
                "<ns3:number>232</ns3:number>"+
                "</ns3:internal_number>"+
                "<ns3:parent_work_reference ref=\"COM(2012)411/700701\"/>"+
                "<ns3:sequence>1</ns3:sequence>"+
                "<ns3:security_info>"+
                "<ns3:classification>NORMAL</ns3:classification>"+
                "</ns3:security_info>"+
                "<ns3:summary_title lang=\"ENG FRA\">Bulgaria Technical Report</ns3:summary_title>"+
                "</ns2:extension>"+
                "<ns2:expression>"+
                "<ns2:language>ENG</ns2:language>"+
                "<ns2:title lang=\"ENG\">COMMISSION STAFF WORKING DOCUMENT BULGARIA: Technical Report Accompanying the document REPORT FROM THE COMMISSION TO THE EUROPEAN PARLIAMENT AND THE COUNCIL On Progress in Bulgaria under the Co-operation and Verification mechanism</ns2:title>"+
                "<ns2:extension xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"ns3:ExpressionExtensionType\"/>"+
                "<ns2:manifestation>"+
                "<ns2:reference_manifestation format=\"DOC\" sequence=\"1\" page_count=\"7\">2669097</ns2:reference_manifestation>"+
                "</ns2:manifestation>"+
                "<ns2:manifestation>"+
                "<ns2:reference_manifestation format=\"PDF\" sequence=\"1\" page_count=\"37\">2670433</ns2:reference_manifestation>"+
                "</ns2:manifestation>"+
                "</ns2:expression>"+
                "<ns2:expression>"+
                "<ns2:language>BUL</ns2:language>"+
                "<ns2:title/>"+
                "<ns2:extension xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"ns3:ExpressionExtensionType\"/>"+
                "</ns2:expression>"+
                "</ns2:work>"+
                "</workflow>"+
                "</transmission>"+
                "</ns5:ec_transmission_request>";

        HashMap<String,String> messageAttachmentsMap = new HashMap<>();
        messageAttachmentsMap.put("2671687", "Filename1");
        messageAttachmentsMap.put("2671716", "Filename2");
        messageAttachmentsMap.put("2671680", "Filename3");
        messageAttachmentsMap.put("2671714", "Filename4");

        when(attachmentHandler.getMapOfAttachmentFilenamesPerReferenceIds(messageId)).thenReturn(messageAttachmentsMap);

        EgreffeTransmissionVO egreffeTransmission = egreffeUtils.getListOfWorks(payloadXml, messageId);


        assertThat(egreffeTransmission, notNullValue());
        assertThat(egreffeTransmission.getComments(), is("langue originale : EN. Les autres versions linguistiques seront transmises dès que possible"));
        assertThat(egreffeTransmission.getAdoptionDate().toString(), is("Wed Jul 18 09:00:00 CEST 2012"));
        assertThat(egreffeTransmission.getListOfWorks(), notNullValue());
        assertThat(egreffeTransmission.getListOfWorks().size(), is(2));

        //Check the order of the works and the proper order of the languages (aka sort was successful)
        assertThat(egreffeTransmission.getListOfWorks().get(0).getLanguages().get(0), is("FIN"));
        assertThat(egreffeTransmission.getListOfWorks().get(0).getLanguages().get(1), is("POR"));
        assertThat(egreffeTransmission.getListOfWorks().get(0).getLanguages().get(2), is("SWE"));

        assertThat(egreffeTransmission.getListOfWorks().get(1).getLanguages().get(0), is("BUL"));
        assertThat(egreffeTransmission.getListOfWorks().get(1).getLanguages().get(1), is("ENG"));


        //Check the order of the works and the proper order of the documents/languages (aka sort was successful)
        assertThat(egreffeTransmission.getListOfWorks().get(0).getDocumentsList().get("FIN").get(1).size(), is(2));
        assertThat(egreffeTransmission.getListOfWorks().get(0).getDocumentsList().get("POR").get(1).size(), is(0));
        assertThat(egreffeTransmission.getListOfWorks().get(0).getDocumentsList().get("SWE").get(1).size(), is(2));

        assertThat(egreffeTransmission.getListOfWorks().get(1).getDocumentsList().get("BUL").get(1), is(nullValue()));
        assertThat(egreffeTransmission.getListOfWorks().get(1).getDocumentsList().get("ENG").get(1).size(), is(0));


        assertThat(egreffeTransmission.getListOfWorks().get(0).getDocumentsList().get("FIN").get(1).get(1).getValue(), is("2671716"));
        assertThat(egreffeTransmission.getListOfWorks().get(0).getDocumentsList().get("SWE").get(1).get(1).getValue(), is("2671714"));


        assertThat(messageAttachmentsMap.size(), is(4));
    }

    @Test
    public void testGetListOfWorks_caseEC_Workflow_NoAttachments() throws Exception {
        String payloadXml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>"+
                "<ns5:ec_transmission_request xmlns=\"http://publications.europa.eu/resource/core-metadata-transmission\" xmlns:ns2=\"http://publications.europa.eu/resource/core-metadata\" xmlns:ns3=\"urn:eu:ec:cm:common:extensions:v10\" xmlns:ns4=\"urn:eu:ec:publication:v10\" xmlns:ns5=\"urn:eu:ec:transmission:v10\">"+
                "<transmission id=\"193695\">"+
                "<institution>COM</institution>"+
                "<service>SG Greffe A.1</service>"+
                "<context>e-Greffe</context>"+
                "<date_time>2012-11-30T17:23:43.192</date_time>"+
                "<comment>langue originale : EN. Les autres versions linguistiques seront transmises dès que possible</comment>"+
                "<extension xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"ns3:TransmissionExtensionType\">"+
                "<ns3:sender/>"+
                "</extension>"+
                "<workflow>"+
                "<legal_basis_list/>"+
                "<addressee_list>"+
                "<agent role=\"ADDRESSEE_ACT\">EP</agent>"+
                "<agent role=\"ADDRESSEE_CONSULT_MANDATORY\">NP</agent>"+
                "<agent role=\"ADDRESSEE_ACT\">CONSIL</agent>"+
                "</addressee_list>"+
                "<extension xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"ns3:WorkflowExtensionType\">"+
                "<ns3:workflow_number ns2:public=\"false\">"+
                "<ns3:code>PO</ns3:code>"+
                "<ns3:year>2012</ns3:year>"+
                "<ns3:number>5039</ns3:number>"+
                "</ns3:workflow_number>"+
                "<ns3:adoption_date_time>2012-07-18T09:00:00.000</ns3:adoption_date_time>"+
                "<ns3:greffe_manager ns2:public=\"false\">"+
                "<ns3:first_name>Stefanie</ns3:first_name>"+
                "<ns3:last_name>HEILEMANN</ns3:last_name>"+
                "<ns3:person_details>"+
                "<ns3:phone_number>61797</ns3:phone_number>"+
                "<ns3:email_address>DIGIT-EGREFFE-TEST@ec.europa.eu</ns3:email_address>"+
                "</ns3:person_details>"+
                "</ns3:greffe_manager>"+
                "<ns3:agent_person_list>"+
                "<ns3:agent_person role=\"RESP_PERS\">José Manuel BARROSO</ns3:agent_person>"+
                "</ns3:agent_person_list>"+
                "<ns3:eea_relevance>false</ns3:eea_relevance>"+
                "<ns3:codified_version>false</ns3:codified_version>"+
                "<ns3:durability>D</ns3:durability>"+
                "</extension>"+
                "<ns2:work>"+
                "<ns2:type_work>REPORT</ns2:type_work>"+
                "<ns2:identifier_work>COM(2012)411/700701</ns2:identifier_work>"+
                "<ns2:agent_work role=\"AUT\">SG</ns2:agent_work>"+
                "<ns2:date_work>2012-07-18</ns2:date_work>"+
                "<ns2:version_work>final</ns2:version_work>"+
                "<ns2:extension xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"ns3:WorkExtensionType\">"+
                "<ns3:category>ACT</ns3:category>"+
                "<ns3:original_language_code>ENG</ns3:original_language_code>"+
                "<ns3:internal_number>"+
                "<ns3:code>COM</ns3:code>"+
                "<ns3:year>2012</ns3:year>"+
                "<ns3:number>411</ns3:number>"+
                "</ns3:internal_number>"+
                "<ns3:sequence>1</ns3:sequence>"+
                "<ns3:security_info>"+
                "<ns3:classification>NORMAL</ns3:classification>"+
                "</ns3:security_info>"+
                "<ns3:summary_title lang=\"ENG FRA\">Rapport de la Commission au Parlement européen et au Conseil sur les progrès en Bulgarie dans le cadre du mécanisme de coopération et de vérification</ns3:summary_title>"+
                "</ns2:extension>"+
                "<ns2:expression>"+
                "<ns2:language>FIN</ns2:language>"+
                "<ns2:title lang=\"FIN\">KOMISSION KERTOMUS EUROOPAN PARLAMENTILLE JA NEUVOSTOLLE yhteistyö- ja seurantamekanismin puitteissa saavutetusta edistyksestä Bulgariassa </ns2:title>"+
                "<ns2:extension xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"ns3:ExpressionExtensionType\"/>"+
                "<ns2:manifestation>"+
                "<ns2:reference_manifestation format=\"DOC\" sequence=\"1\" page_count=\"3\">2671687</ns2:reference_manifestation>"+
                "</ns2:manifestation>"+
                "<ns2:manifestation>"+
                "<ns2:reference_manifestation format=\"PDF\" sequence=\"1\" page_count=\"23\">2671716</ns2:reference_manifestation>"+
                "</ns2:manifestation>"+
                "</ns2:expression>"+
                "<ns2:expression>"+
                "<ns2:language>SWE</ns2:language>"+
                "<ns2:title lang=\"SWE\">RAPPORT FRÅN KOMMISSIONEN TILL EUROPAPARLAMENTET OCH RÅDET om framstegen i Bulgarien inom ramen för samarbets- och kontrollmekanismen </ns2:title>"+
                "<ns2:extension xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"ns3:ExpressionExtensionType\"/>"+
                "<ns2:manifestation>"+
                "<ns2:reference_manifestation format=\"DOC\" sequence=\"1\" page_count=\"3\">2671680</ns2:reference_manifestation>"+
                "</ns2:manifestation>"+
                "<ns2:manifestation>"+
                "<ns2:reference_manifestation format=\"PDF\" sequence=\"1\" page_count=\"22\">2671714</ns2:reference_manifestation>"+
                "</ns2:manifestation>"+
                "</ns2:expression>"+
                "<ns2:expression>"+
                "<ns2:language>POR</ns2:language>"+
                "<ns2:title lang=\"POR\">RELATÓRIO DA COMISSÃO AO PARLAMENTO EUROPEU E AO CONSELHO sobre os progressos realizados pela Bulgária no âmbito do mecanismo de cooperação e de verificação </ns2:title>"+
                "<ns2:extension xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"ns3:ExpressionExtensionType\"/>"+
                "<ns2:manifestation>"+
                "<ns2:reference_manifestation format=\"DOC\" sequence=\"1\" page_count=\"28\">2671675</ns2:reference_manifestation>"+
                "</ns2:manifestation>"+
                "<ns2:manifestation>"+
                "<ns2:reference_manifestation format=\"PDF\" sequence=\"1\" page_count=\"24\">2671706</ns2:reference_manifestation>"+
                "</ns2:manifestation>"+
                "</ns2:expression>"+
                "</ns2:work>"+
                "<ns2:work>"+
                "<ns2:type_work>SWD</ns2:type_work>"+
                "<ns2:identifier_work>SWD(2012)232/700860</ns2:identifier_work>"+
                "<ns2:agent_work role=\"AUT\">SG</ns2:agent_work>"+
                "<ns2:date_work>2012-07-18</ns2:date_work>"+
                "<ns2:version_work>final</ns2:version_work>"+
                "<ns2:extension xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"ns3:WorkExtensionType\">"+
                "<ns3:category>DOCUMENT</ns3:category>"+
                "<ns3:original_language_code>ENG</ns3:original_language_code>"+
                "<ns3:internal_number>"+
                "<ns3:code>SWD</ns3:code>"+
                "<ns3:year>2012</ns3:year>"+
                "<ns3:number>232</ns3:number>"+
                "</ns3:internal_number>"+
                "<ns3:parent_work_reference ref=\"COM(2012)411/700701\"/>"+
                "<ns3:sequence>1</ns3:sequence>"+
                "<ns3:security_info>"+
                "<ns3:classification>NORMAL</ns3:classification>"+
                "</ns3:security_info>"+
                "<ns3:summary_title lang=\"ENG FRA\">Bulgaria Technical Report</ns3:summary_title>"+
                "</ns2:extension>"+
                "<ns2:expression>"+
                "<ns2:language>ENG</ns2:language>"+
                "<ns2:title lang=\"ENG\">COMMISSION STAFF WORKING DOCUMENT BULGARIA: Technical Report Accompanying the document REPORT FROM THE COMMISSION TO THE EUROPEAN PARLIAMENT AND THE COUNCIL On Progress in Bulgaria under the Co-operation and Verification mechanism</ns2:title>"+
                "<ns2:extension xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"ns3:ExpressionExtensionType\"/>"+
                "<ns2:manifestation>"+
                "<ns2:reference_manifestation format=\"DOC\" sequence=\"1\" page_count=\"7\">2669097</ns2:reference_manifestation>"+
                "</ns2:manifestation>"+
                "<ns2:manifestation>"+
                "<ns2:reference_manifestation format=\"PDF\" sequence=\"1\" page_count=\"37\">2670433</ns2:reference_manifestation>"+
                "</ns2:manifestation>"+
                "</ns2:expression>"+
                "<ns2:expression>"+
                "<ns2:language>BUL</ns2:language>"+
                "<ns2:title/>"+
                "<ns2:extension xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"ns3:ExpressionExtensionType\"/>"+
                "</ns2:expression>"+
                "</ns2:work>"+
                "</workflow>"+
                "</transmission>"+
                "</ns5:ec_transmission_request>";

        HashMap<String,String> messageAttachmentsMap = new HashMap<>();
        when(attachmentHandler.getMapOfAttachmentFilenamesPerReferenceIds(messageId)).thenReturn(messageAttachmentsMap);

        EgreffeTransmissionVO egreffeTransmission = egreffeUtils.getListOfWorks(payloadXml, messageId);


        assertThat(egreffeTransmission, notNullValue());
        assertThat(egreffeTransmission.getComments(), is("langue originale : EN. Les autres versions linguistiques seront transmises dès que possible"));
        assertThat(egreffeTransmission.getAdoptionDate().toString(), is("Wed Jul 18 09:00:00 CEST 2012"));
        assertThat(egreffeTransmission.getListOfWorks(), notNullValue());
        assertThat(egreffeTransmission.getListOfWorks().size(), is(2));

        //Check the order of the works and the proper order of the languages (aka sort was successful)
        assertThat(egreffeTransmission.getListOfWorks().get(0).getLanguages().get(0), is("FIN"));
        assertThat(egreffeTransmission.getListOfWorks().get(0).getLanguages().get(1), is("POR"));
        assertThat(egreffeTransmission.getListOfWorks().get(0).getLanguages().get(2), is("SWE"));

        assertThat(egreffeTransmission.getListOfWorks().get(1).getLanguages().get(0), is("BUL"));
        assertThat(egreffeTransmission.getListOfWorks().get(1).getLanguages().get(1), is("ENG"));


        //Check the order of the works and the proper order of the documents/languages (aka sort was successful)
        assertThat(egreffeTransmission.getListOfWorks().get(0).getDocumentsList().get("FIN").get(1).size(), is(0));
        assertThat(egreffeTransmission.getListOfWorks().get(0).getDocumentsList().get("POR").get(1).size(), is(0));
        assertThat(egreffeTransmission.getListOfWorks().get(0).getDocumentsList().get("SWE").get(1).size(), is(0));

        assertThat(egreffeTransmission.getListOfWorks().get(1).getDocumentsList().get("BUL").get(1), is(nullValue()));
        assertThat(egreffeTransmission.getListOfWorks().get(1).getDocumentsList().get("ENG").get(1).size(), is(0));


        assertThat(messageAttachmentsMap.size(), is(0));

    }

    @Test
    public void testGetListOfWorks_caseEC_Workflow_NonFilter_OneLanguageIsEmpty() throws Exception {
        String payloadXml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>"+
                "<ns5:ec_transmission_request xmlns=\"http://publications.europa.eu/resource/core-metadata-transmission\" xmlns:ns2=\"http://publications.europa.eu/resource/core-metadata\" xmlns:ns3=\"urn:eu:ec:cm:common:extensions:v10\" xmlns:ns4=\"urn:eu:ec:publication:v10\" xmlns:ns5=\"urn:eu:ec:transmission:v10\">"+
                "<transmission id=\"193695\">"+
                "<institution>COM</institution>"+
                "<service>SG Greffe A.1</service>"+
                "<context>e-Greffe</context>"+
                "<date_time>2012-11-30T17:23:43.192</date_time>"+
                "<comment>langue originale : EN. Les autres versions linguistiques seront transmises dès que possible</comment>"+
                "<extension xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"ns3:TransmissionExtensionType\">"+
                "<ns3:sender/>"+
                "</extension>"+
                "<workflow>"+
                "<legal_basis_list/>"+
                "<addressee_list>"+
                "<agent role=\"ADDRESSEE_ACT\">EP</agent>"+
                "<agent role=\"ADDRESSEE_CONSULT_MANDATORY\">NP</agent>"+
                "<agent role=\"ADDRESSEE_ACT\">CONSIL</agent>"+
                "</addressee_list>"+
                "<extension xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"ns3:WorkflowExtensionType\">"+
                "<ns3:workflow_number ns2:public=\"false\">"+
                "<ns3:code>PO</ns3:code>"+
                "<ns3:year>2012</ns3:year>"+
                "<ns3:number>5039</ns3:number>"+
                "</ns3:workflow_number>"+
                "<ns3:adoption_date_time>2012-07-18T09:00:00.000</ns3:adoption_date_time>"+
                "<ns3:greffe_manager ns2:public=\"false\">"+
                "<ns3:first_name>Stefanie</ns3:first_name>"+
                "<ns3:last_name>HEILEMANN</ns3:last_name>"+
                "<ns3:person_details>"+
                "<ns3:phone_number>61797</ns3:phone_number>"+
                "<ns3:email_address>DIGIT-EGREFFE-TEST@ec.europa.eu</ns3:email_address>"+
                "</ns3:person_details>"+
                "</ns3:greffe_manager>"+
                "<ns3:agent_person_list>"+
                "<ns3:agent_person role=\"RESP_PERS\">José Manuel BARROSO</ns3:agent_person>"+
                "</ns3:agent_person_list>"+
                "<ns3:eea_relevance>false</ns3:eea_relevance>"+
                "<ns3:codified_version>false</ns3:codified_version>"+
                "<ns3:durability>D</ns3:durability>"+
                "</extension>"+
                "<ns2:work>"+
                "<ns2:type_work>REPORT</ns2:type_work>"+
                "<ns2:identifier_work>COM(2012)411/700701</ns2:identifier_work>"+
                "<ns2:agent_work role=\"AUT\">SG</ns2:agent_work>"+
                "<ns2:date_work>2012-07-18</ns2:date_work>"+
                "<ns2:version_work>final</ns2:version_work>"+
                "<ns2:extension xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"ns3:WorkExtensionType\">"+
                "<ns3:category>ACT</ns3:category>"+
                "<ns3:original_language_code>ENG</ns3:original_language_code>"+
                "<ns3:internal_number>"+
                "<ns3:code>COM</ns3:code>"+
                "<ns3:year>2012</ns3:year>"+
                "<ns3:number>411</ns3:number>"+
                "</ns3:internal_number>"+
                "<ns3:sequence>1</ns3:sequence>"+
                "<ns3:security_info>"+
                "<ns3:classification>NORMAL</ns3:classification>"+
                "</ns3:security_info>"+
                "<ns3:summary_title lang=\"ENG FRA\">Rapport de la Commission au Parlement européen et au Conseil sur les progrès en Bulgarie dans le cadre du mécanisme de coopération et de vérification</ns3:summary_title>"+
                "</ns2:extension>"+
                "<ns2:expression>"+
                "<ns2:language>FIN</ns2:language>"+
                "<ns2:title lang=\"FIN\">KOMISSION KERTOMUS EUROOPAN PARLAMENTILLE JA NEUVOSTOLLE yhteistyö- ja seurantamekanismin puitteissa saavutetusta edistyksestä Bulgariassa </ns2:title>"+
                "<ns2:extension xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"ns3:ExpressionExtensionType\"/>"+
                "<ns2:manifestation>"+
                "<ns2:reference_manifestation format=\"DOC\" sequence=\"1\" page_count=\"3\">2671687</ns2:reference_manifestation>"+
                "</ns2:manifestation>"+
                "<ns2:manifestation>"+
                "<ns2:reference_manifestation format=\"PDF\" sequence=\"1\" page_count=\"23\">2671716</ns2:reference_manifestation>"+
                "</ns2:manifestation>"+
                "</ns2:expression>"+
                "<ns2:expression>"+
                "<ns2:language></ns2:language>"+
                "<ns2:title lang=\"SWE\">RAPPORT FRÅN KOMMISSIONEN TILL EUROPAPARLAMENTET OCH RÅDET om framstegen i Bulgarien inom ramen för samarbets- och kontrollmekanismen </ns2:title>"+
                "<ns2:extension xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"ns3:ExpressionExtensionType\"/>"+
                "<ns2:manifestation>"+
                "<ns2:reference_manifestation format=\"DOC\" sequence=\"1\" page_count=\"3\">2671680</ns2:reference_manifestation>"+
                "</ns2:manifestation>"+
                "<ns2:manifestation>"+
                "<ns2:reference_manifestation format=\"PDF\" sequence=\"1\" page_count=\"22\">2671714</ns2:reference_manifestation>"+
                "</ns2:manifestation>"+
                "</ns2:expression>"+
                "<ns2:expression>"+
                "<ns2:language>POR</ns2:language>"+
                "<ns2:title lang=\"POR\">RELATÓRIO DA COMISSÃO AO PARLAMENTO EUROPEU E AO CONSELHO sobre os progressos realizados pela Bulgária no âmbito do mecanismo de cooperação e de verificação </ns2:title>"+
                "<ns2:extension xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"ns3:ExpressionExtensionType\"/>"+
                "<ns2:manifestation>"+
                "<ns2:reference_manifestation format=\"DOC\" sequence=\"1\" page_count=\"28\">2671675</ns2:reference_manifestation>"+
                "</ns2:manifestation>"+
                "<ns2:manifestation>"+
                "<ns2:reference_manifestation format=\"PDF\" sequence=\"1\" page_count=\"24\">2671706</ns2:reference_manifestation>"+
                "</ns2:manifestation>"+
                "</ns2:expression>"+
                "</ns2:work>"+
                "<ns2:work>"+
                "<ns2:type_work>SWD</ns2:type_work>"+
                "<ns2:identifier_work>SWD(2012)232/700860</ns2:identifier_work>"+
                "<ns2:agent_work role=\"AUT\">SG</ns2:agent_work>"+
                "<ns2:date_work>2012-07-18</ns2:date_work>"+
                "<ns2:version_work>final</ns2:version_work>"+
                "<ns2:extension xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"ns3:WorkExtensionType\">"+
                "<ns3:category>DOCUMENT</ns3:category>"+
                "<ns3:original_language_code>ENG</ns3:original_language_code>"+
                "<ns3:internal_number>"+
                "<ns3:code>SWD</ns3:code>"+
                "<ns3:year>2012</ns3:year>"+
                "<ns3:number>232</ns3:number>"+
                "</ns3:internal_number>"+
                "<ns3:parent_work_reference ref=\"COM(2012)411/700701\"/>"+
                "<ns3:sequence>1</ns3:sequence>"+
                "<ns3:security_info>"+
                "<ns3:classification>NORMAL</ns3:classification>"+
                "</ns3:security_info>"+
                "<ns3:summary_title lang=\"ENG FRA\">Bulgaria Technical Report</ns3:summary_title>"+
                "</ns2:extension>"+
                "<ns2:expression>"+
                "<ns2:language>ENG</ns2:language>"+
                "<ns2:title lang=\"ENG\">COMMISSION STAFF WORKING DOCUMENT BULGARIA: Technical Report Accompanying the document REPORT FROM THE COMMISSION TO THE EUROPEAN PARLIAMENT AND THE COUNCIL On Progress in Bulgaria under the Co-operation and Verification mechanism</ns2:title>"+
                "<ns2:extension xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"ns3:ExpressionExtensionType\"/>"+
                "<ns2:manifestation>"+
                "<ns2:reference_manifestation format=\"DOC\" sequence=\"1\" page_count=\"7\">2669097</ns2:reference_manifestation>"+
                "</ns2:manifestation>"+
                "<ns2:manifestation>"+
                "<ns2:reference_manifestation format=\"PDF\" sequence=\"1\" page_count=\"37\">2670433</ns2:reference_manifestation>"+
                "</ns2:manifestation>"+
                "</ns2:expression>"+
                "<ns2:expression>"+
                "<ns2:language>BUL</ns2:language>"+
                "<ns2:title/>"+
                "<ns2:extension xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"ns3:ExpressionExtensionType\"/>"+
                "</ns2:expression>"+
                "</ns2:work>"+
                "</workflow>"+
                "</transmission>"+
                "</ns5:ec_transmission_request>";

        HashMap<String,String> messageAttachmentsMap = new HashMap<>();
        messageAttachmentsMap.put("2671687", "Filename1");
        messageAttachmentsMap.put("2671716", "Filename2");
        messageAttachmentsMap.put("2671680", "Filename3");
        messageAttachmentsMap.put("2671714", "Filename4");
        messageAttachmentsMap.put("2671675", "Filename5");
        messageAttachmentsMap.put("2671706", "Filename6");
        messageAttachmentsMap.put("2669097", "Filename7");
        messageAttachmentsMap.put("2670433", "Filename8");

        when(attachmentHandler.getMapOfAttachmentFilenamesPerReferenceIds(messageId)).thenReturn(messageAttachmentsMap);

        EgreffeTransmissionVO egreffeTransmission = egreffeUtils.getListOfWorks(payloadXml, messageId);


        assertThat(egreffeTransmission, notNullValue());
        assertThat(egreffeTransmission.getComments(), is("langue originale : EN. Les autres versions linguistiques seront transmises dès que possible"));
        assertThat(egreffeTransmission.getAdoptionDate().toString(), is("Wed Jul 18 09:00:00 CEST 2012"));
        assertThat(egreffeTransmission.getListOfWorks(), notNullValue());
        assertThat(egreffeTransmission.getListOfWorks().size(), is(2));

        //Check the order of the works and the proper order of the languages (aka sort was successful)
        assertThat(egreffeTransmission.getListOfWorks().get(0).getLanguages().get(0), is(""));
        assertThat(egreffeTransmission.getListOfWorks().get(0).getLanguages().get(1), is("FIN"));
        assertThat(egreffeTransmission.getListOfWorks().get(0).getLanguages().get(2), is("POR"));

        assertThat(egreffeTransmission.getListOfWorks().get(1).getLanguages().get(0), is("BUL"));
        assertThat(egreffeTransmission.getListOfWorks().get(1).getLanguages().get(1), is("ENG"));


        //Check the order of the works and the proper order of the documents/languages (aka sort was successful)
        assertThat(egreffeTransmission.getListOfWorks().get(0).getDocumentsList().get("").get(1).size(), is(2));
        assertThat(egreffeTransmission.getListOfWorks().get(0).getDocumentsList().get("FIN").get(1).size(), is(2));
        assertThat(egreffeTransmission.getListOfWorks().get(0).getDocumentsList().get("POR").get(1).size(), is(2));

        assertThat(egreffeTransmission.getListOfWorks().get(1).getDocumentsList().get("BUL").get(1), is(nullValue()));
        assertThat(egreffeTransmission.getListOfWorks().get(1).getDocumentsList().get("ENG").get(1).size(), is(2));

        assertThat(egreffeTransmission.getListOfWorks().get(0).getDocumentsList().get("").get(1).get(1).getValue(), is("2671714"));
        assertThat(egreffeTransmission.getListOfWorks().get(0).getDocumentsList().get("FIN").get(1).get(1).getValue(), is("2671716"));
        assertThat(egreffeTransmission.getListOfWorks().get(0).getDocumentsList().get("POR").get(1).get(1).getValue(), is("2671706"));

        assertThat(egreffeTransmission.getListOfWorks().get(1).getDocumentsList().get("ENG").get(1).get(1).getValue(), is("2670433"));

        assertThat(messageAttachmentsMap.size(), is(8));
    }

    @Test
    public void testGetListOfWorks_caseEC_Workflow_NonFilter_OneLanguageIsNull() throws Exception {
        String payloadXml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>"+
                "<ns5:ec_transmission_request xmlns=\"http://publications.europa.eu/resource/core-metadata-transmission\" xmlns:ns2=\"http://publications.europa.eu/resource/core-metadata\" xmlns:ns3=\"urn:eu:ec:cm:common:extensions:v10\" xmlns:ns4=\"urn:eu:ec:publication:v10\" xmlns:ns5=\"urn:eu:ec:transmission:v10\">"+
                "<transmission id=\"193695\">"+
                "<institution>COM</institution>"+
                "<service>SG Greffe A.1</service>"+
                "<context>e-Greffe</context>"+
                "<date_time>2012-11-30T17:23:43.192</date_time>"+
                "<comment>langue originale : EN. Les autres versions linguistiques seront transmises dès que possible</comment>"+
                "<extension xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"ns3:TransmissionExtensionType\">"+
                "<ns3:sender/>"+
                "</extension>"+
                "<workflow>"+
                "<legal_basis_list/>"+
                "<addressee_list>"+
                "<agent role=\"ADDRESSEE_ACT\">EP</agent>"+
                "<agent role=\"ADDRESSEE_CONSULT_MANDATORY\">NP</agent>"+
                "<agent role=\"ADDRESSEE_ACT\">CONSIL</agent>"+
                "</addressee_list>"+
                "<extension xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"ns3:WorkflowExtensionType\">"+
                "<ns3:workflow_number ns2:public=\"false\">"+
                "<ns3:code>PO</ns3:code>"+
                "<ns3:year>2012</ns3:year>"+
                "<ns3:number>5039</ns3:number>"+
                "</ns3:workflow_number>"+
                "<ns3:adoption_date_time>2012-07-18T09:00:00.000</ns3:adoption_date_time>"+
                "<ns3:greffe_manager ns2:public=\"false\">"+
                "<ns3:first_name>Stefanie</ns3:first_name>"+
                "<ns3:last_name>HEILEMANN</ns3:last_name>"+
                "<ns3:person_details>"+
                "<ns3:phone_number>61797</ns3:phone_number>"+
                "<ns3:email_address>DIGIT-EGREFFE-TEST@ec.europa.eu</ns3:email_address>"+
                "</ns3:person_details>"+
                "</ns3:greffe_manager>"+
                "<ns3:agent_person_list>"+
                "<ns3:agent_person role=\"RESP_PERS\">José Manuel BARROSO</ns3:agent_person>"+
                "</ns3:agent_person_list>"+
                "<ns3:eea_relevance>false</ns3:eea_relevance>"+
                "<ns3:codified_version>false</ns3:codified_version>"+
                "<ns3:durability>D</ns3:durability>"+
                "</extension>"+
                "<ns2:work>"+
                "<ns2:type_work>REPORT</ns2:type_work>"+
                "<ns2:identifier_work>COM(2012)411/700701</ns2:identifier_work>"+
                "<ns2:agent_work role=\"AUT\">SG</ns2:agent_work>"+
                "<ns2:date_work>2012-07-18</ns2:date_work>"+
                "<ns2:version_work>final</ns2:version_work>"+
                "<ns2:extension xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"ns3:WorkExtensionType\">"+
                "<ns3:category>ACT</ns3:category>"+
                "<ns3:original_language_code>ENG</ns3:original_language_code>"+
                "<ns3:internal_number>"+
                "<ns3:code>COM</ns3:code>"+
                "<ns3:year>2012</ns3:year>"+
                "<ns3:number>411</ns3:number>"+
                "</ns3:internal_number>"+
                "<ns3:sequence>1</ns3:sequence>"+
                "<ns3:security_info>"+
                "<ns3:classification>NORMAL</ns3:classification>"+
                "</ns3:security_info>"+
                "<ns3:summary_title lang=\"ENG FRA\">Rapport de la Commission au Parlement européen et au Conseil sur les progrès en Bulgarie dans le cadre du mécanisme de coopération et de vérification</ns3:summary_title>"+
                "</ns2:extension>"+
                "<ns2:expression>"+
                "<ns2:language>FIN</ns2:language>"+
                "<ns2:title lang=\"FIN\">KOMISSION KERTOMUS EUROOPAN PARLAMENTILLE JA NEUVOSTOLLE yhteistyö- ja seurantamekanismin puitteissa saavutetusta edistyksestä Bulgariassa </ns2:title>"+
                "<ns2:extension xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"ns3:ExpressionExtensionType\"/>"+
                "<ns2:manifestation>"+
                "<ns2:reference_manifestation format=\"DOC\" sequence=\"1\" page_count=\"3\">2671687</ns2:reference_manifestation>"+
                "</ns2:manifestation>"+
                "<ns2:manifestation>"+
                "<ns2:reference_manifestation format=\"PDF\" sequence=\"1\" page_count=\"23\">2671716</ns2:reference_manifestation>"+
                "</ns2:manifestation>"+
                "</ns2:expression>"+
                "<ns2:expression>"+
                "<ns2:title lang=\"SWE\">RAPPORT FRÅN KOMMISSIONEN TILL EUROPAPARLAMENTET OCH RÅDET om framstegen i Bulgarien inom ramen för samarbets- och kontrollmekanismen </ns2:title>"+
                "<ns2:extension xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"ns3:ExpressionExtensionType\"/>"+
                "<ns2:manifestation>"+
                "<ns2:reference_manifestation format=\"DOC\" sequence=\"1\" page_count=\"3\">2671680</ns2:reference_manifestation>"+
                "</ns2:manifestation>"+
                "<ns2:manifestation>"+
                "<ns2:reference_manifestation format=\"PDF\" sequence=\"1\" page_count=\"22\">2671714</ns2:reference_manifestation>"+
                "</ns2:manifestation>"+
                "</ns2:expression>"+
                "<ns2:expression>"+
                "<ns2:language>POR</ns2:language>"+
                "<ns2:title lang=\"POR\">RELATÓRIO DA COMISSÃO AO PARLAMENTO EUROPEU E AO CONSELHO sobre os progressos realizados pela Bulgária no âmbito do mecanismo de cooperação e de verificação </ns2:title>"+
                "<ns2:extension xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"ns3:ExpressionExtensionType\"/>"+
                "<ns2:manifestation>"+
                "<ns2:reference_manifestation format=\"DOC\" sequence=\"1\" page_count=\"28\">2671675</ns2:reference_manifestation>"+
                "</ns2:manifestation>"+
                "<ns2:manifestation>"+
                "<ns2:reference_manifestation format=\"PDF\" sequence=\"1\" page_count=\"24\">2671706</ns2:reference_manifestation>"+
                "</ns2:manifestation>"+
                "</ns2:expression>"+
                "</ns2:work>"+
                "<ns2:work>"+
                "<ns2:type_work>SWD</ns2:type_work>"+
                "<ns2:identifier_work>SWD(2012)232/700860</ns2:identifier_work>"+
                "<ns2:agent_work role=\"AUT\">SG</ns2:agent_work>"+
                "<ns2:date_work>2012-07-18</ns2:date_work>"+
                "<ns2:version_work>final</ns2:version_work>"+
                "<ns2:extension xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"ns3:WorkExtensionType\">"+
                "<ns3:category>DOCUMENT</ns3:category>"+
                "<ns3:original_language_code>ENG</ns3:original_language_code>"+
                "<ns3:internal_number>"+
                "<ns3:code>SWD</ns3:code>"+
                "<ns3:year>2012</ns3:year>"+
                "<ns3:number>232</ns3:number>"+
                "</ns3:internal_number>"+
                "<ns3:parent_work_reference ref=\"COM(2012)411/700701\"/>"+
                "<ns3:sequence>1</ns3:sequence>"+
                "<ns3:security_info>"+
                "<ns3:classification>NORMAL</ns3:classification>"+
                "</ns3:security_info>"+
                "<ns3:summary_title lang=\"ENG FRA\">Bulgaria Technical Report</ns3:summary_title>"+
                "</ns2:extension>"+
                "<ns2:expression>"+
                "<ns2:language>ENG</ns2:language>"+
                "<ns2:title lang=\"ENG\">COMMISSION STAFF WORKING DOCUMENT BULGARIA: Technical Report Accompanying the document REPORT FROM THE COMMISSION TO THE EUROPEAN PARLIAMENT AND THE COUNCIL On Progress in Bulgaria under the Co-operation and Verification mechanism</ns2:title>"+
                "<ns2:extension xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"ns3:ExpressionExtensionType\"/>"+
                "<ns2:manifestation>"+
                "<ns2:reference_manifestation format=\"DOC\" sequence=\"1\" page_count=\"7\">2669097</ns2:reference_manifestation>"+
                "</ns2:manifestation>"+
                "<ns2:manifestation>"+
                "<ns2:reference_manifestation format=\"PDF\" sequence=\"1\" page_count=\"37\">2670433</ns2:reference_manifestation>"+
                "</ns2:manifestation>"+
                "</ns2:expression>"+
                "<ns2:expression>"+
                "<ns2:language>BUL</ns2:language>"+
                "<ns2:title/>"+
                "<ns2:extension xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"ns3:ExpressionExtensionType\"/>"+
                "</ns2:expression>"+
                "</ns2:work>"+
                "</workflow>"+
                "</transmission>"+
                "</ns5:ec_transmission_request>";

        HashMap<String,String> messageAttachmentsMap = new HashMap<>();
        messageAttachmentsMap.put("2671687", "Filename1");
        messageAttachmentsMap.put("2671716", "Filename2");
        messageAttachmentsMap.put("2671680", "Filename3");
        messageAttachmentsMap.put("2671714", "Filename4");
        messageAttachmentsMap.put("2671675", "Filename5");
        messageAttachmentsMap.put("2671706", "Filename6");
        messageAttachmentsMap.put("2669097", "Filename7");
        messageAttachmentsMap.put("2670433", "Filename8");

        when(attachmentHandler.getMapOfAttachmentFilenamesPerReferenceIds(messageId)).thenReturn(messageAttachmentsMap);

        EgreffeTransmissionVO egreffeTransmission = egreffeUtils.getListOfWorks(payloadXml, messageId);


        assertThat(egreffeTransmission, notNullValue());
        assertThat(egreffeTransmission.getComments(), is("langue originale : EN. Les autres versions linguistiques seront transmises dès que possible"));
        assertThat(egreffeTransmission.getAdoptionDate().toString(), is("Wed Jul 18 09:00:00 CEST 2012"));
        assertThat(egreffeTransmission.getListOfWorks(), notNullValue());
        assertThat(egreffeTransmission.getListOfWorks().size(), is(2));

        //Check the order of the works and the proper order of the languages (aka sort was successful)
        assertThat(egreffeTransmission.getListOfWorks().get(0).getLanguages().get(0), is("FIN"));
        assertThat(egreffeTransmission.getListOfWorks().get(0).getLanguages().get(1), is("POR"));

        assertThat(egreffeTransmission.getListOfWorks().get(1).getLanguages().get(0), is("BUL"));
        assertThat(egreffeTransmission.getListOfWorks().get(1).getLanguages().get(1), is("ENG"));


        //Check the order of the works and the proper order of the documents/languages (aka sort was successful)
        assertThat(egreffeTransmission.getListOfWorks().get(0).getDocumentsList().get("FIN").get(1).size(), is(2));
        assertThat(egreffeTransmission.getListOfWorks().get(0).getDocumentsList().get("POR").get(1).size(), is(2));

        assertThat(egreffeTransmission.getListOfWorks().get(1).getDocumentsList().get("BUL").get(1), is(nullValue()));
        assertThat(egreffeTransmission.getListOfWorks().get(1).getDocumentsList().get("ENG").get(1).size(), is(2));


        assertThat(egreffeTransmission.getListOfWorks().get(0).getDocumentsList().get("FIN").get(1).get(1).getValue(), is("2671716"));
        assertThat(egreffeTransmission.getListOfWorks().get(0).getDocumentsList().get("POR").get(1).get(1).getValue(), is("2671706"));

        assertThat(egreffeTransmission.getListOfWorks().get(1).getDocumentsList().get("ENG").get(1).get(1).getValue(), is("2670433"));

        assertThat(messageAttachmentsMap.size(), is(8));
    }

    @Test
    public void testGetListOfWorks_caseProcedureInteristitutional_NonFilter_WorkElementWithoutManifestations() throws Exception {
        String payloadXml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>"+
                "<ns5:ec_transmission_request xmlns=\"http://publications.europa.eu/resource/core-metadata-transmission\" xmlns:ns2=\"http://publications.europa.eu/resource/core-metadata\" xmlns:ns3=\"urn:eu:ec:cm:common:extensions:v10\" xmlns:ns4=\"urn:eu:ec:publication:v10\" xmlns:ns5=\"urn:eu:ec:transmission:v10\">"+
                "<transmission id=\"193693\">"+
                "<institution>COM</institution>"+
                "<service>SG Greffe A.1</service>"+
                "<context>e-Greffe</context>"+
                "<date_time>2012-11-30T17:17:05.628</date_time>"+
                "<comment/>"+
                "<extension xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"ns3:TransmissionExtensionType\">"+
                "<ns3:sender/>"+
                "</extension>"+
                "<workflow>"+
                "<extension xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"ns3:WorkflowExtensionType\">"+
                "<ns3:workflow_number ns2:public=\"false\">"+
                "<ns3:code>PH</ns3:code>"+
                "<ns3:year>2012</ns3:year>"+
                "<ns3:number>5855</ns3:number>"+
                "</ns3:workflow_number>"+
                "<ns3:adoption_date_time>2012-11-07T15:38:49.000</ns3:adoption_date_time>"+
                "<ns3:eea_relevance>false</ns3:eea_relevance>"+
                "<ns3:codified_version>false</ns3:codified_version>"+
                "<ns3:durability>D</ns3:durability>"+
                "</extension>"+
                "<work_reference ref=\"COM(2012)478/704453\"/>"+
                "</workflow>"+
                "<ns2:procedure_interinstitutional>"+
                "<ns2:identifier_procedure>"+
                "<ns2:year_procedure>2009</ns2:year_procedure>"+
                "<ns2:number_procedure>7</ns2:number_procedure>"+
                "</ns2:identifier_procedure>"+
                "<ns2:extension xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"ns3:ProcedureExtensionType\">"+
                "<ns3:subsidiarity_proportionality_application>"+
                "<ns3:competence_type>EXCLUSIVE</ns3:competence_type>"+
                "<ns3:legal_reference>"+
                "<ns2:document_legal>TFEU_2010</ns2:document_legal>"+
                "<ns2:article>3</ns2:article>"+
                "</ns3:legal_reference>"+
                "</ns3:subsidiarity_proportionality_application>"+
                "</ns2:extension>"+
                "<ns2:event_legal>"+
                "<ns2:type_event>ADP_OPR2AMEPE_byCOM</ns2:type_event>"+
                "<ns2:date_event>2012-11-28</ns2:date_event>"+
                "<ns2:agent_event role=\"AUTHORI\">COM</ns2:agent_event>"+
                "<ns2:agent_event role=\"RESP_CORP\">SG</ns2:agent_event>"+
                "<ns2:agent_event role=\"ADDRESSEE_ACT\">CONSIL</ns2:agent_event>"+
                "<ns2:agent_event role=\"ADDRESSEE_ACT\">EP</ns2:agent_event>"+
                "<ns2:agent_event role=\"ADDRESSEE_CONSULT_MANDATORY\">NP</ns2:agent_event>"+
                "<ns2:type_interinstitutionalprocedure>COD</ns2:type_interinstitutionalprocedure>"+
                "<ns2:basis_legal_procedure>"+
                "<ns2:document_legal>TFEU_2010</ns2:document_legal>"+
                "<ns2:article>74</ns2:article>"+
                "</ns2:basis_legal_procedure>"+
                "<ns2:extension xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"ns3:EventExtensionType\">"+
                "<ns3:agent_person role=\"RESP_PERS\">José Manuel BARROSO</ns3:agent_person>"+
                "</ns2:extension>"+
                "<ns2:work>"+
                "<ns2:type_work>OPIN_AMEND_EP</ns2:type_work>"+
                "<ns2:identifier_work>COM(2012)478/704453</ns2:identifier_work>"+
                "<ns2:agent_work role=\"AUT\">SG</ns2:agent_work>"+
                "<ns2:date_work>2012-11-07</ns2:date_work>"+
                "<ns2:version_work>final</ns2:version_work>"+
                "<ns2:extension xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"ns3:WorkExtensionType\">"+
                "<ns3:category>ACT</ns3:category>"+
                "<ns3:original_language_code>ENG</ns3:original_language_code>"+
                "<ns3:internal_number>"+
                "<ns3:code>COM</ns3:code>"+
                "<ns3:year>2012</ns3:year>"+
                "<ns3:number>478</ns3:number>"+
                "</ns3:internal_number>"+
                "<ns3:sequence>1</ns3:sequence>"+
                "<ns3:security_info>"+
                "<ns3:classification>NORMAL</ns3:classification>"+
                "</ns3:security_info>"+
                "<ns3:summary_title lang=\"ENG FRA\">AVIS DE LA COMMISSION "+
                "conformément à l'article 294, paragraphe 7, point c), du traité sur le fonctionnement de l'Union européenne "+
                "sur l'[les]amendement[s] du Parlement européen "+
                "à la position du Conseil concernant la "+
                "proposition de "+
                "RÈGLEMENT DU PARLEMENT EUROPÉEN ET DU CONSEIL "+
                "concernant la mise sur le marché et l'utilisation des produits biocides "+
                "</ns3:summary_title>"+
                "</ns2:extension>"+
                "<ns2:expression>"+
                "<ns2:language>ENG</ns2:language>"+
                "<ns2:title lang=\"ENG\">OPINION OF THE COMMISSION pursuant to Article 294(7)(c) of the Treaty on the Functioning of the European Union, on the European Parliament's amendment[s] to the Council's position regarding the proposal for a REGULATION OF THE EUROPEAN PARLIAMENT AND OF THE COUNCIL concerning the placing on the market and use of biocidal products. </ns2:title>"+
                "<ns2:extension xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"ns3:ExpressionExtensionType\"/>"+
                "<ns2:manifestation>"+
                "<ns2:reference_manifestation format=\"DOC\" sequence=\"1\" page_count=\"4\">2689090</ns2:reference_manifestation>"+
                "</ns2:manifestation>"+
                "<ns2:manifestation>"+
                "<ns2:reference_manifestation format=\"PDF\" sequence=\"1\" page_count=\"4\">2689096</ns2:reference_manifestation>"+
                "</ns2:manifestation>"+
                "</ns2:expression>"+
                "<ns2:expression>"+
                "<ns2:language>FRA</ns2:language>"+
                "<ns2:title lang=\"FRA\">AVIS DE LA COMMISSION conformément à l'article 294, paragraphe 7, point c), du traité sur le fonctionnement de l'Union européenne sur l'[les]amendement[s] du Parlement européen à la position du Conseil concernant la proposition de RÈGLEMENT DU PARLEMENT EUROPÉEN ET DU CONSEIL concernant la mise sur le marché et l'utilisation des produits biocides </ns2:title>"+
                "<ns2:extension xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"ns3:ExpressionExtensionType\"/>"+
                "<ns2:manifestation>"+
                "<ns2:reference_manifestation format=\"DOC\" sequence=\"1\" page_count=\"3\">2689091</ns2:reference_manifestation>"+
                "</ns2:manifestation>"+
                "<ns2:manifestation>"+
                "<ns2:reference_manifestation format=\"PDF\" sequence=\"1\" page_count=\"4\">2689094</ns2:reference_manifestation>"+
                "</ns2:manifestation>"+
                "</ns2:expression>"+
                "<ns2:expression>"+
                "<ns2:language>DEU</ns2:language>"+
                "<ns2:title lang=\"DEU\">STELLUNGNAHME DER KOMMISSION gemäß Artikel 294 Absatz 7 Buchstabe c des Vertrags über die Arbeitsweise der Europäischen Union zu den Abänderungen des Europäischen Parlaments an dem Standpunkt des Rates betreffend den Vorschlag für eine VERORDNUNG DES EUROPÄISCHEN PARLAMENTS UND DES RATES über das Inverkehrbringen und die Verwendung von Biozidprodukten </ns2:title>"+
                "<ns2:extension xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"ns3:ExpressionExtensionType\"/>"+
                "<ns2:manifestation>"+
                "<ns2:reference_manifestation format=\"DOC\" sequence=\"1\" page_count=\"4\">2689089</ns2:reference_manifestation>"+
                "</ns2:manifestation>"+
                "<ns2:manifestation>"+
                "<ns2:reference_manifestation format=\"PDF\" sequence=\"1\" page_count=\"4\">2689095</ns2:reference_manifestation>"+
                "</ns2:manifestation>"+
                "</ns2:expression>"+
                "</ns2:work>"+
                "<ns2:work>"+
                "<ns2:type_work>DOC</ns2:type_work>"+
                "<ns2:identifier_work>C(2012)6/516038</ns2:identifier_work>"+
                "<ns2:agent_work role=\"AUT\">EURATOM</ns2:agent_work>"+
                "<ns2:date_work>2012-01-26</ns2:date_work>"+
                "<ns2:version_work>final</ns2:version_work>"+
                "<ns2:extension xsi:type=\"ns4:WorkExtensionType\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">"+
                "<ns4:category>DOCUMENT</ns4:category>"+
                "<ns4:original_language_code>ENG</ns4:original_language_code>"+
                "<ns4:internal_number code=\"C\" year=\"2012\" number=\"6\"/>"+
                "<ns4:parent_work_reference ref=\"C(2012)6/515200\"/>"+
                "<ns4:sequence>5</ns4:sequence>"+
                "<ns4:security_info>"+
                "<ns4:classification>NORMAL</ns4:classification>"+
                "</ns4:security_info>"+
                "<ns4:summary_title/>"+
                "</ns2:extension>"+
                "<ns2:expression>"+
                "<ns2:language>DEU</ns2:language>"+
                "<ns2:title/>"+
                "<ns2:extension xsi:type=\"ns4:ExpressionExtensionType\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"/>"+
                "</ns2:expression>"+
                "<ns2:expression>"+
                "<ns2:language>ENG</ns2:language>"+
                "<ns2:title/>"+
                "<ns2:extension xsi:type=\"ns4:ExpressionExtensionType\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"/>"+
                "</ns2:expression>"+
                "<ns2:expression>"+
                "<ns2:language>FRA</ns2:language>"+
                "<ns2:title/>"+
                "<ns2:extension xsi:type=\"ns4:ExpressionExtensionType\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"/>"+
                "</ns2:expression>"+
                "</ns2:work>"+
                "</ns2:event_legal>"+
                "</ns2:procedure_interinstitutional>"+
                "</transmission>"+
                "</ns5:ec_transmission_request>";

        HashMap<String,String> messageAttachmentsMap = new HashMap<>();
        messageAttachmentsMap.put("2689090", "Filename1");
        messageAttachmentsMap.put("2689096", "Filename2");
        messageAttachmentsMap.put("2689091", "Filename3");
        messageAttachmentsMap.put("2689094", "Filename4");
        messageAttachmentsMap.put("2689089", "Filename5");
        messageAttachmentsMap.put("2689095", "Filename6");

        when(attachmentHandler.getMapOfAttachmentFilenamesPerReferenceIds(messageId)).thenReturn(messageAttachmentsMap);

        EgreffeTransmissionVO egreffeTransmission = egreffeUtils.getListOfWorks(payloadXml, messageId);


        assertThat(egreffeTransmission, notNullValue());
        assertThat(egreffeTransmission.getComments(), is(""));
        assertThat(egreffeTransmission.getAdoptionDate().toString(), is("Wed Nov 07 15:38:49 CET 2012"));
        assertThat(egreffeTransmission.getListOfWorks(), notNullValue());
        assertThat(egreffeTransmission.getListOfWorks().size(), is(2));

        //Check the order of the works and the proper order of the languages (aka sort was successful)
        assertThat(egreffeTransmission.getListOfWorks().get(0).getLanguages().get(0), is("DEU"));
        assertThat(egreffeTransmission.getListOfWorks().get(0).getLanguages().get(1), is("ENG"));
        assertThat(egreffeTransmission.getListOfWorks().get(0).getLanguages().get(2), is("FRA"));

        //Check the order of the works and the proper order of the documents/languages (aka sort was successful)
        assertThat(egreffeTransmission.getListOfWorks().get(0).getDocumentsList().get("DEU").get(1).size(), is(2));
        assertThat(egreffeTransmission.getListOfWorks().get(0).getDocumentsList().get("DEU").get(1).get(0).getValue(), is("2689089"));
        assertThat(egreffeTransmission.getListOfWorks().get(0).getDocumentsList().get("ENG").get(1).size(), is(2));
        assertThat(egreffeTransmission.getListOfWorks().get(0).getDocumentsList().get("ENG").get(1).get(0).getValue(), is("2689090"));
        assertThat(egreffeTransmission.getListOfWorks().get(0).getDocumentsList().get("FRA").get(1).size(), is(2));
        assertThat(egreffeTransmission.getListOfWorks().get(0).getDocumentsList().get("FRA").get(1).get(0).getValue(), is("2689091"));


        assertThat(messageAttachmentsMap.size(), is(6));
    }

}
