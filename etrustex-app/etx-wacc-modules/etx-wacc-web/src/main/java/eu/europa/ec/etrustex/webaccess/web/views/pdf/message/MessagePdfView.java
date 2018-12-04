package eu.europa.ec.etrustex.webaccess.web.views.pdf.message;

import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import eu.europa.ec.etrustex.webaccess.model.Attachment;
import eu.europa.ec.etrustex.webaccess.model.Message;
import eu.europa.ec.etrustex.webaccess.model.Metadata;
import eu.europa.ec.etrustex.webaccess.web.views.pdf.AbstractITextPdfView;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

/**
 * Message PDF view.
 * As it is a subclass of AbstractITextPdfView, the buildPdfDocument method is implemented to create a summary of the message in PDF format.
 */

public class MessagePdfView extends AbstractITextPdfView {

    private static final SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:SS");

    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer, HttpServletRequest request, HttpServletResponse response) throws Exception {
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

        Font titleValue = new Font(freeSans);
        titleValue.setSize(10);

        //Paragraphs
        Paragraph titleParagraph = new Paragraph("Message Summary", titleFont);
        titleParagraph.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(titleParagraph);

        Paragraph bundleIdParagraph = new Paragraph("Bundle Id", titleLabel);
        bundleIdParagraph.setSpacingBefore(10F);
        document.add(bundleIdParagraph);
        document.add(new Paragraph(message.getBundleId(), titleValue));

        Paragraph timeStampParagraph = new Paragraph("Timestamp", titleLabel);
        timeStampParagraph.setSpacingBefore(5F);
        document.add(timeStampParagraph);
        document.add(new Paragraph(message.getSentOn() != null ? formatter.format(message.getSentOn()) : "", titleValue));

        Paragraph senderParagraph = new Paragraph("Sender", titleLabel);
        senderParagraph.setSpacingBefore(5F);
        document.add(senderParagraph);
        document.add(new Paragraph(message.getLocalParty() != null ? message.getLocalParty().getNodeName() : "", titleValue));

        Paragraph recipientParagraph = new Paragraph("Recipient", titleLabel);
        recipientParagraph.setSpacingBefore(5F);
        document.add(recipientParagraph);
        document.add(new Paragraph(message.getRemoteParty() != null ? message.getRemoteParty().getNodeName() : "", titleValue));

        Paragraph metadataParagraph = new Paragraph("Metadata", titleLabel);
        metadataParagraph.setSpacingBefore(5F);
        document.add(metadataParagraph);

        if (metadata != null) {
            document.add(new Paragraph(metadata.getContent(), titleValue));
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

}