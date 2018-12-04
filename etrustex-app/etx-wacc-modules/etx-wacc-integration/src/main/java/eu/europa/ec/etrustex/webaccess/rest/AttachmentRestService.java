package eu.europa.ec.etrustex.webaccess.rest;

import org.apache.cxf.jaxrs.ext.multipart.Multipart;
import org.apache.cxf.jaxrs.model.wadl.Description;
import org.apache.cxf.jaxrs.model.wadl.Descriptions;
import org.apache.cxf.jaxrs.model.wadl.DocTarget;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.io.InputStream;

@Path("/attachment")
public interface AttachmentRestService {
    String TRANSMISSION_CHECKSUM_HEADER = "Transmission-Checksum";
    String TRANSMISSION_CHECKSUM_METHOD_HEADER = "Transmission-Checksum-Method";
    String CONTENT_CHECKSUM_HEADER = "Content-Checksum";
    String CONTENT_CHECKSUM_METHOD_HEADER = "Content-Checksum-Method";
    String CONTENT_LENGTH_HEADER = "Content-Length";
    String SESSION_KEY_HEADER = "Session-Key";
    String CONTENT_DISPOSITION_HEADER = "Content-Disposition";
    String IS_SIGNED = "Is-Signed";

    @GET
    @Path(value = "/{id}")
    @Produces({MediaType.APPLICATION_OCTET_STREAM})
    @Descriptions({
            @Description(value = "Read file attachment", target = DocTarget.METHOD),
            @Description(value = "File stream", target = DocTarget.RETURN)
    })
    Response read(@Description("attachment id") @PathParam("id") Long id) throws IOException;

    @POST
    @Path(value = "/upload")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Descriptions({
            @Description(value = "Upload file attachment", target = DocTarget.METHOD),
            @Description(value = "Attachment ID", target = DocTarget.RETURN)
    })
    Long upload(@Multipart(value = "localPartyId", type = MediaType.TEXT_PLAIN) long localPartyId,
                @Multipart(value = "referenceId", type = MediaType.TEXT_PLAIN) String referenceId,
                @Multipart(value = "name", type = MediaType.TEXT_PLAIN) String name,
                @Multipart(value = "size", type = MediaType.TEXT_PLAIN) long size,
                @Multipart(value = "mimeType", type = MediaType.TEXT_PLAIN) String mimeType,
                @Multipart(value = "attachmentType", type = MediaType.TEXT_PLAIN) String attachmentType,
                @Multipart(value = "contentChecksum", type = MediaType.APPLICATION_OCTET_STREAM) byte[] contentChecksum,
                @Multipart(value = "contentChecksumMethod", type = MediaType.TEXT_PLAIN) String contentChecksumMethod,
                @Multipart(value = "transmissionChecksum", type = MediaType.APPLICATION_OCTET_STREAM) byte[] transmissionChecksum,
                @Multipart(value = "transmissionChecksumMethod", type = MediaType.TEXT_PLAIN) String transmissionChecksumMethod,
                @Multipart(value = "encryptedKey", type = MediaType.APPLICATION_OCTET_STREAM, required = false) byte[] encryptedKey,
                @Multipart(value = "encryptionCertificateX509SubjectName", type = MediaType.TEXT_PLAIN, required = false) String encryptionCertificateX509SubjectName,
                @Multipart(value = "file", type = MediaType.APPLICATION_OCTET_STREAM) InputStream inputStream) throws Exception;
}