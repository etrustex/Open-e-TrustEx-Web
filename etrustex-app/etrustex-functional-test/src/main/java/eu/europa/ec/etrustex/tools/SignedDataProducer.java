package eu.europa.ec.etrustex.tools;

import eu.europa.ec.etrustex.webaccess.utils.HashHelper;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.bind.DatatypeConverter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileOutputStream;
import java.io.StringReader;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * This class creates a new document with the checksum for every file in file metadata list document.
 */
public class SignedDataProducer {
    private static final String NS_URI = "*";
    private static final String EMPTY_SIGNED_BUNDLE = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><signedBundle xmlns=\"urn:eu:europa:ec:etrustex:signature:v1.0\"></signedBundle>";
    static final String OUTPUT_FILE_NAME = "file_list_to_sign.xml";

    private static HashHelper hashHelper = new HashHelper();


    public static void main(String[] args) throws Exception {
        if (args.length != 2) {
            printUsage();
            return;
        }

        String unencryptedFolderPath = args[0];
        String fileMetadataListFile = args[1];

        produce(unencryptedFolderPath, fileMetadataListFile);
    }

    static void produce(String unencryptedFolderPath, String fileMetadataListFile) throws Exception {
        Document fileMetadataListDocument = prepareFileMetadataListDocument(fileMetadataListFile);
        Element fileMetadataList = (Element) fileMetadataListDocument.getElementsByTagNameNS(NS_URI, "fileMetadataList").item(0);
        if (fileMetadataList == null) {
            System.err.println("Invalid file: " + fileMetadataListFile + "; fileMetadataList element cannot be found inside it !");
            return;
        }

        Document signedBundleDocument = prepareSignedBundleDocument();
        NodeList files = fileMetadataList.getElementsByTagNameNS(NS_URI, "file");

        System.out.println("Start hashing ...");
        for (int i = 0; i < files.getLength(); i++) {
            Element file = (Element) files.item(i);
            String id = file.getElementsByTagNameNS(NS_URI, "id").item(0).getTextContent();
            String name = file.getElementsByTagNameNS(NS_URI, "name").item(0).getTextContent();
            String checksumMethod = HashHelper.HashMethodType.SHA_512.getCode();
            String unencryptedFilePath = unencryptedFolderPath + FileSystems.getDefault().getSeparator() + name;

            System.out.println("hashing: " + name + ", using method: " + checksumMethod);
            byte[] checksum = hashHelper.hash(Paths.get(unencryptedFilePath), checksumMethod);

            addDocumentElement(signedBundleDocument, id, checksumMethod, checksum);
        }

        System.out.println("... end hashing.");
        outputNodeDocument(signedBundleDocument, OUTPUT_FILE_NAME);

        Path signatureFile = Paths.get(OUTPUT_FILE_NAME);
        if (Files.exists(signatureFile)) {
            System.out.println("Data for sign successfully written in the file: " + signatureFile.toString());
        } else {
            System.out.println("Failed to write data for sign in the file: " + signatureFile.toString());
        }
    }

    protected static Document prepareFileMetadataListDocument(String fileMetadataListFile) throws Exception {
        File xmlFile = new File(fileMetadataListFile);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        DocumentBuilder documentBuilder = factory.newDocumentBuilder();
        Document document = documentBuilder.parse(xmlFile);
        document.getDocumentElement().normalize();
        return document;
    }

    protected static void outputNodeDocument(Node node, String fileName) throws Exception {
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer = tf.newTransformer();
        transformer.setOutputProperty(OutputKeys.STANDALONE, "yes");
        try (FileOutputStream out = new FileOutputStream(fileName)) {
            transformer.transform(new DOMSource(node), new StreamResult(out));
        }
    }

    protected static Document prepareSignedBundleDocument() throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = factory.newDocumentBuilder();
        InputSource is = new InputSource(new StringReader(EMPTY_SIGNED_BUNDLE));
        return documentBuilder.parse(is);
    }

    protected static void addDocumentElement(Document dataForSignDocument, String id, String checksumMethod, byte[] checksum) {
        Element signedData = dataForSignDocument.getDocumentElement();

        Element dataForSignDocumentElement = dataForSignDocument.createElement("document");
        signedData.appendChild(dataForSignDocumentElement);

        Element signId = dataForSignDocument.createElement("id");
        signId.appendChild(dataForSignDocument.createTextNode(id));
        dataForSignDocumentElement.appendChild(signId);

        Element signChecksumMethod = dataForSignDocument.createElement("digestMethod");
        signChecksumMethod.appendChild(dataForSignDocument.createTextNode(checksumMethod));
        dataForSignDocumentElement.appendChild(signChecksumMethod);

        Element signChecksum = dataForSignDocument.createElement("digestValue");
        signChecksum.appendChild(dataForSignDocument.createTextNode(DatatypeConverter.printHexBinary(checksum)));

        dataForSignDocumentElement.appendChild(signChecksum);
    }

    private static void printUsage() {
        StringBuilder usage = new StringBuilder();
        usage.append("Usage: ").append(SignedDataProducer.class.getName()).append(" <unencryptedFolderPath> <fileMetadataListFile>");
        usage.append('\n').append("Where:");
        usage.append('\n').append("<unencryptedFolderPath> - The folder with unencrypted files for which checksum will be computed");
        usage.append('\n').append("<fileMetadataListFile> - The XML file with files metadata list defined as in OutboxService wsdl");
        usage.append('\n').append("NOTE: check result in ").append(OUTPUT_FILE_NAME);
        System.out.println(usage.toString());
    }
}
