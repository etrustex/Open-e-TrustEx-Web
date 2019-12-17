package eu.europa.ec.etrustex.webaccess.web.views.pdf.message;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import eu.europa.ec.etrustex.webaccess.model.Attachment;
import eu.europa.ec.etrustex.webaccess.model.LabelTranslation;
import eu.europa.ec.etrustex.webaccess.model.Message;
import eu.europa.ec.etrustex.webaccess.model.Metadata;
import eu.europa.ec.etrustex.webaccess.persistence.LabelTranslationDAO;
import eu.europa.ec.etrustex.webaccess.web.payload.edma.*;
import eu.europa.ec.etrustex.webaccess.web.utils.ApplicationContextProvider;
import eu.europa.ec.etrustex.webaccess.web.views.pdf.AbstractITextPdfView;
import org.apache.commons.lang.StringUtils;
import org.springframework.context.ApplicationContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

/**
 * Message PDF view.
 * As it is a subclass of AbstractITextPdfView, the buildPdfDocument method is implemented to create a summary of the message in PDF format.
 */


public class MessagePdfView extends AbstractITextPdfView {

    private static final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer, HttpServletRequest request, HttpServletResponse response) throws Exception {

        ApplicationContext applicationContext = ApplicationContextProvider.getApplicationContext();
        LabelTranslationDAO labelTranslationDAO = applicationContext.getBean(LabelTranslationDAO.class);

        Message message = (Message) model.get("message");
        List<Attachment> attachments = (List<Attachment>) model.get("attachments");
        Metadata metadata = (Metadata) model.get("metadata");


        FontFactory.register("fonts/FreeSans.ttf");
        Font freeSans = FontFactory.getFont("freesans", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);

        //Fonts
        Font titleFont = new Font(freeSans);
        titleFont.setSize(14);
        titleFont.setStyle(Font.BOLD);

        Font titleLabel = new Font(freeSans);
        titleLabel.setSize(11);
        titleLabel.setStyle(Font.BOLD);

        Font itemHeader = new Font (freeSans);
        itemHeader.setSize(10);
        itemHeader.setStyle(Font.BOLD);

        Font titleValue = new Font(freeSans);
        titleValue.setSize(10);
        itemHeader.setStyle(Font.NORMAL);


        //Paragraphs
        Paragraph titleParagraph = new Paragraph("Message Summary", titleFont);
        titleParagraph.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(titleParagraph);

        Paragraph bundleIdParagraph = new Paragraph("Bundle Id", titleLabel);
        bundleIdParagraph.setSpacingBefore(10F);
        document.add(bundleIdParagraph);
        document.add(new Paragraph(message.getBundleId(), titleValue));

        ETrustExEdmaMd edmaMetadata = null;
        if(metadata!=null) {
            edmaMetadata = this.unmarshallEdmaMetadata(metadata.getContent());

            if(edmaMetadata.getInboundDate() != null) {
                LabelTranslation deliveredOnLabel = labelTranslationDAO.findEnglishLabelByKey("receipt.deliveredOn");
                Paragraph timeStampParagraph = new Paragraph(deliveredOnLabel.getMessage(), titleLabel);
                timeStampParagraph.setSpacingBefore(5F);
                document.add(timeStampParagraph);
                document.add(new Paragraph(formatter.format(edmaMetadata.getInboundDate().toGregorianCalendar().getTime()), titleValue));
            }
        }

        Paragraph senderParagraph = new Paragraph("Sender", titleLabel);
        senderParagraph.setSpacingBefore(5F);
        document.add(senderParagraph);
        document.add(new Paragraph(message.getLocalParty() != null ? message.getLocalParty().getNodeName() : "", titleValue));

        Paragraph recipientParagraph = new Paragraph("Recipient", titleLabel);
        recipientParagraph.setSpacingBefore(5F);
        document.add(recipientParagraph);
        document.add(new Paragraph(message.getRemoteParty() != null ? message.getRemoteParty().getNodeName() : "", titleValue));


        if (edmaMetadata != null) {
            Paragraph metadataParagraph = new Paragraph("Metadata", titleLabel);
            metadataParagraph.setSpacingBefore(5F);
            document.add(metadataParagraph);

            document.add(publishMetadataAsTree(edmaMetadata, itemHeader, titleValue, labelTranslationDAO));
        }

        Paragraph attachmentParagraph = new Paragraph("Attachments", titleLabel);
        attachmentParagraph.setSpacingBefore(5F);
        document.add(attachmentParagraph);

        for (Attachment attachment : attachments) {
            document.add(new Paragraph("- " + attachment.getFilePath() + attachment.getFileName() + " (" + attachment.getFileSize() + " bytes)", titleValue));
        }

        Paragraph commentsParagraph = new Paragraph("Comments", titleLabel);
        commentsParagraph.setSpacingBefore(5F);
        document.add(commentsParagraph);
        document.add(new Paragraph(message.getContent(), titleValue));

        //Metadata
        document.addAuthor("eTrustEx");
        document.addTitle("Message summary");
        document.addSubject(message.getSubject());
        document.addCreationDate();
    }

    private ETrustExEdmaMd unmarshallEdmaMetadata(String metadata) {
        return MetadataConverter.unmarshallMetadata(metadata);
    }


    private Paragraph publishMetadataAsTree(ETrustExEdmaMd om, Font itemHeaderFont, Font itemValueFont, LabelTranslationDAO labelTranslationDAO ) {

        Paragraph res = new Paragraph();
        addToParagraph(res, toParagraph("Subject", itemHeaderFont, om.getSubject(), itemValueFont));
        addToParagraph(res, toParagraph("Comment", itemHeaderFont, om.getComment(), itemValueFont));
        addToParagraph(res, toParagraph("Case number", itemHeaderFont, om.getFileNumber(), itemValueFont));
        addToParagraph(res, toParagraph("In attention of", itemHeaderFont, om.getInAttentionOf(), itemValueFont));
        addToParagraph(res, toParagraph("Language", itemHeaderFont, om.getLanguage(), itemValueFont));
        addToParagraph(res, toParagraph("Request for info number", itemHeaderFont, om.getReqForInfoNumber(), itemValueFont));


        publishInboundMetadata(res, om.getInboundMetaData(), itemHeaderFont, itemValueFont);

        return res;
    }

    private static void publishInboundMetadata(Paragraph paragraph, ETrustExEdmaMdInbound im, Font itemHeaderFont, Font itemValueFont) {
        if(im != null) {

            paragraph.add(new Paragraph("Inbound metadata", itemHeaderFont));
            com.itextpdf.text.List l = new com.itextpdf.text.List();

            addToList(l,toParagraph("Distribution list", itemHeaderFont, im.getDistributionList(), itemValueFont));
            addToList(l,toParagraph("Email", itemHeaderFont, im.getEMail(), itemValueFont));
            addToList(l,toParagraph("From", itemHeaderFont, im.getFrom(), itemValueFont));
            addToList(l,toParagraph("Instrument", itemHeaderFont,  im.getInstrument(), itemValueFont));
            addToList(l,toParagraph("Transfer status", itemHeaderFont, im.getTransferStatus(), itemValueFont));
            addToList(l,toParagraph("Your reference", itemHeaderFont, im.getYourReference(), itemValueFont));

            addToList(l, toParagraph(im.getSender(), "Sender", itemHeaderFont, itemValueFont));
            addToList(l, toParagraph(im.getOnBehalfOf(), "On behalf of", itemHeaderFont, itemValueFont));


            Documents docs = im.getDocuments();
            if(docs!=null&&docs.getETrustExEdmaMdDocument()!=null) {
                Paragraph p = new Paragraph();
                addToParagraph(p,  new Paragraph("Documents", itemHeaderFont));
                com.itextpdf.text.List il = new com.itextpdf.text.List();
                for (ETrustExEdmaMdDocument doc : im.getDocuments().getETrustExEdmaMdDocument()) {
                    addToList(il, toParagraph(doc, itemHeaderFont, itemValueFont));
                }
                p.add(il);
                addToList(l,p);
            }


            paragraph.add(l);

        }
    }

    private static void addToParagraph(Paragraph outer, Paragraph inner) {
        if(inner!= null) {
            outer.add(inner);
        }
    }
    private static void addToList(com.itextpdf.text.List list, Paragraph inner) {
        if(inner!= null) {
            ListItem li = new ListItem(inner);
            li.setListSymbol(new Chunk("  "));
            list.add(li);
        }
    }

    private static Paragraph toParagraph(ETrustExEdmaMdDocument doc, Font itemHeaderFont, Font itemValueFont) {
        Paragraph res = null;
        com.itextpdf.text.List l = new com.itextpdf.text.List();

        addToList(l, toParagraph("File reference id", itemHeaderFont, doc.getFileReferenceId(), itemValueFont));
        addToList(l, toParagraph("Path", itemHeaderFont, doc.getPath(), itemValueFont));
        addToList(l, toParagraph("Comment", itemHeaderFont, doc.getDocumentComment(), itemValueFont));
        addToList(l, toParagraph("Confidential", itemHeaderFont, doc.getConfidential(), itemValueFont));
        addToList(l, toParagraph("Original file name", itemHeaderFont, doc.getOriginalFilename(), itemValueFont));

        if(!l.isEmpty()) {
            res=new Paragraph();
            res.add(new Paragraph(doc.getFile(), itemHeaderFont));
            res.add(l);
        }
        return res;
    }

    private static Paragraph toParagraph (ETrustExEdmaMdCompany sender, String header, Font itemHeaderFont, Font itemValueFont) {
        Paragraph res = null;

        if(sender != null) {

            com.itextpdf.text.List l = new com.itextpdf.text.List();

            addToList(l,toParagraph("Company", itemHeaderFont, sender.getCompany(), itemValueFont));
            addToList(l,toParagraph("Email", itemHeaderFont, sender.getEMail(), itemValueFont));
            addToList(l,toParagraph("City", itemHeaderFont, sender.getCity(), itemValueFont));
            addToList(l,toParagraph("Contact person", itemHeaderFont, sender.getContactPerson(), itemValueFont));
            addToList(l,toParagraph("National reg. number", itemHeaderFont, sender.getNationalRegNumber(), itemValueFont));
            addToList(l,toParagraph("Country", itemHeaderFont, sender.getCountry(), itemValueFont));
            addToList(l,toParagraph("Phone", itemHeaderFont, sender.getPhone(), itemValueFont));
            addToList(l,toParagraph("Position", itemHeaderFont, sender.getPosition(), itemValueFont));
            addToList(l,toParagraph("Street", itemHeaderFont, sender.getStreet(), itemValueFont));
            addToList(l,toParagraph("Zip", itemHeaderFont, sender.getZip(), itemValueFont));

            if(!l.isEmpty()) {
                res = new Paragraph();
                res.add(new Paragraph(header, itemHeaderFont));
                res.add(l);
            }
        }

        return res;
    }

    private static Paragraph toParagraph(String item, Font headerFont, String value, Font valueFont) {
        Paragraph p = null;
        if (StringUtils.isNotEmpty(value)) {
            p = new Paragraph(String.format("%s: ", item), headerFont);
            p.add(new Chunk(value, valueFont));

        }
        return p;
    }
}