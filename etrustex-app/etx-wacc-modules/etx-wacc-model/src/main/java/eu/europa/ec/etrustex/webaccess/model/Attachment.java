package eu.europa.ec.etrustex.webaccess.model;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

@Entity
@Table(name = "ETX_WEB_ATTACHMENT")
@Inheritance(strategy = InheritanceType.JOINED)
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Attachment extends AbstractEntity<Long> {

    private static final long serialVersionUID = 4340522280499878312L;


    @SequenceGenerator(name = "ETX_WEB_ATTSEQ", sequenceName = "ETX_WEB_ATTSEQ", allocationSize = 1)
    @Id
    @Column(name = "ATT_ID")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "ETX_WEB_ATTSEQ")
    private Long id;

    @Column(name = "ATT_WRAPPER_ID")
    private String wrapperId;

    @Column(name = "ATT_FILE_NAME")
    private String fileName;

    @Column(name = "ATT_FILE_PATH")
    private String filePath;

    @Column(name = "ATT_MIME_TYPE")
    private String mimeType;

    @Column(name = "ATT_TYPE")
    @Enumerated(EnumType.STRING)
    private AttachmentType type;

    @Column(name = "ATT_FILE_SIZE")
    private Long fileSize;

    @Column(name = "ATT_TRANSMISSION_CHK")
    private byte[] transmissionChecksum;

    @Column(name = "ATT_TRANSMISSION_CHK_METHOD")
    private String transmissionChecksumMethod;

    @Column(name = "ATT_CONTENT_CHK")
    private byte[] contentChecksum;

    @Column(name = "ATT_CONTENT_CHK_METHOD")
    private String contentChecksumMethod;

    @ManyToOne
    @JoinColumn(name = "MSG_ID")
    private Message message;

    public Attachment() {
    }

    public String getWrapperId() {
        return wrapperId;
    }

    public void setWrapperId(String referenceId) {
        this.wrapperId = referenceId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public AttachmentType getType() {
        return type;
    }

    public void setType(AttachmentType type) {
        this.type = type;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    public byte[] getTransmissionChecksum() {
        return transmissionChecksum;
    }

    public void setTransmissionChecksum(byte[] transmissionChecksum) {
        this.transmissionChecksum = transmissionChecksum;
    }

    public String getTransmissionChecksumMethod() {
        return transmissionChecksumMethod;
    }

    public void setTransmissionChecksumMethod(String transmissionChecksumMethod) {
        this.transmissionChecksumMethod = transmissionChecksumMethod;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public byte[] getContentChecksum() {
        return contentChecksum;
    }

    public void setContentChecksum(byte[] contentChecksum) {
        this.contentChecksum = contentChecksum;
    }

    public String getContentChecksumMethod() {
        return contentChecksumMethod;
    }

    public void setContentChecksumMethod(String contentChecksumMethod) {
        this.contentChecksumMethod = contentChecksumMethod;
    }


}
