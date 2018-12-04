package eu.europa.ec.etrustex.webaccess.business;

import eu.europa.ec.etrustex.webaccess.business.mail.MailContentData;
import eu.europa.ec.etrustex.webaccess.business.mail.PayloadMailContentData;
import eu.europa.ec.etrustex.webaccess.business.mail.PayloadDossier;
import eu.europa.ec.etrustex.webaccess.business.api.PayloadMailContentExtractor;
import eu.europa.ec.etrustex.webaccess.utils.EtxException;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.*;
import java.nio.charset.Charset;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;

@Service
public class PayloadMailContentExtractorImpl implements PayloadMailContentExtractor {

    private static final Logger LOGGER = Logger.getLogger(PayloadMailContentExtractorImpl.class);

    /**
     * The extractPayloadMailContentDataFromPayload will extract specific data from the payload to be used
     * in the payload templates used for the emails to be send
     *
     * @param payload
     * @return The payloadMailContentData object containing the information retrieved from the payload
     */
    public PayloadMailContentData extractPayloadMailContentDataFromPayload(String payload) {

        PayloadMailContentData payloadMailContentData = new PayloadMailContentData();

        try (InputStream inputStream = new ByteArrayInputStream(payload.getBytes(Charset.forName("UTF-8")))) {
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            Document xmlDocument = builder.parse(inputStream);
            XPath xPath = XPathFactory.newInstance().newXPath();

            List<PayloadDossier> payloadDossierList = new LinkedList<PayloadDossier>();

            //Title
            String expression = "//transmission/workflow/extension/short_title";
            String title = xPath.compile(expression).evaluate(xmlDocument);

            String expressionTitle;
            String expressionLanguage;

            if (title != null && !"".equals(title.trim())) {
                expressionTitle = "//transmission/workflow/extension/short_title";
                expressionLanguage = "//work[extension/category=\"ACT\" and extension/short_title=\"{0}\"]/expression[manifestation]/language";
                payloadDossierList = getTitle_languages(expressionTitle, expressionLanguage, xPath, xmlDocument);
            } else {
                expression = "//procedure_interinstitutional";
                String procedure_interinstitutional = xPath.compile(expression).evaluate(xmlDocument);

                if (procedure_interinstitutional != null) {
                    expressionTitle = "//work[extension/category=\"ACT\"]/extension/summary_title";
                    expressionLanguage = "//work[extension/category=\"ACT\" and extension/summary_title=\"{0}\"]/expression[manifestation]/language";
                    payloadDossierList = getTitle_languages(expressionTitle, expressionLanguage, xPath, xmlDocument);
                } else {
                    expressionTitle = "//workflow/work[extension/category=\"ACT\"]/extension/summary_title";
                    expressionLanguage = "//workflow/work[extension/category=\"ACT\" and extension/summary_title=\"{0}\"]/expression[manifestation]/language";
                    payloadDossierList = getTitle_languages(expressionTitle, expressionLanguage, xPath, xmlDocument);
                }
            }


            if (payloadDossierList.size() > 0) {
                payloadMailContentData.setPayloadDossierList(payloadDossierList);
                PayloadDossier payloadDossier = payloadDossierList.get(0);
                payloadMailContentData.setTitle(payloadDossier.getTitle());
                payloadMailContentData.setLinguisticVersions(payloadDossier.getLinguisticVersions());
                String expressionUnderSubsidairyCheck = "//transmission/procedure_interinstitutional/extension/subsidiarity_proportionality_application/competence_type";
                payloadMailContentData.setSubsidiarityCheck(getUnderSubsidairyCheck(expressionUnderSubsidairyCheck, xPath, xmlDocument));
            }

        } catch (SAXException | IOException | ParserConfigurationException | XPathExpressionException ex) {
            LOGGER.error("Error extracting payload: " + ex);
        }

        if (payloadMailContentData.getTitle() != null && payloadMailContentData.getLinguisticVersions() != null) {
            payloadMailContentData.setValid(true);
        }

        return payloadMailContentData;

    }

    private static List<PayloadDossier> getTitle_languages(String expressionTitle, String expressionLanguage, XPath xPath, Document xmlDocument) throws XPathExpressionException {
        List<PayloadDossier> payloadDossierList = new LinkedList<PayloadDossier>();
        NodeList nodeList = (NodeList) xPath.compile(expressionTitle).evaluate(xmlDocument, XPathConstants.NODESET);
        for (int i = 0; i < nodeList.getLength(); i++) {
            PayloadDossier payloadDossier = new PayloadDossier();
            payloadDossier.setTitle(nodeList.item(i).getFirstChild().getNodeValue());
            expressionLanguage = expressionLanguage.replace("{0}", payloadDossier.getTitle());
            payloadDossier.setLinguisticVersions(getLanguages(expressionLanguage, xPath, xmlDocument));
            payloadDossierList.add(payloadDossier);
        }
        return payloadDossierList;
    }

    private static boolean getUnderSubsidairyCheck(String expression, XPath xPath, Document xmlDocument) throws XPathExpressionException {
        boolean underSubsidiaryCheck = false;

        String foundString = xPath.compile(expression).evaluate(xmlDocument);
        if (("SHARED").equals(foundString)) {
            underSubsidiaryCheck = true;
        }
        return underSubsidiaryCheck;
    }

    private static String getLanguages(String expression, XPath xPath, Document xmlDocument) throws XPathExpressionException {
        StringBuilder avLanguages = new StringBuilder();
        NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(xmlDocument, XPathConstants.NODESET);
        for (int i = 0; i < nodeList.getLength(); i++) {
            if (i == 0) {
                avLanguages.append(nodeList.item(i).getFirstChild().getNodeValue());
            } else {
                avLanguages.append("/").append(nodeList.item(i).getFirstChild().getNodeValue());
            }
        }
        return avLanguages.toString();
    }
}
