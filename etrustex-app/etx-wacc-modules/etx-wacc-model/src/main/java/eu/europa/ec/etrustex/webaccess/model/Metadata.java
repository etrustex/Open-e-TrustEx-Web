/**
 * 
 */
package eu.europa.ec.etrustex.webaccess.model;


import javax.persistence.*;

/**
 * @author apladap
 *
 */
@Entity
@Table(name="ETX_WEB_METADATA")
public class Metadata extends AbstractEntity<Long> {

    public enum MetadataState {
        CREATED, UPLOADED, DOWNLOADED, FAILED
    }

	private static final long serialVersionUID = 1L;

	@SequenceGenerator(name = "ETX_WEB_METADATASEQ", sequenceName = "ETX_WEB_METADATASEQ", allocationSize = 1)
	@Id
	@Column(name="MTD_ID")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "ETX_WEB_METADATASEQ")
	private Long id;

	@Column(name="MSG_ID")
	private Long messageId;
	
	@Column(name="MTD_CONTENT", columnDefinition="CLOB")
	@Lob 
	private String content;

    @Column(name = "MTD_STATE")
    @Enumerated(EnumType.STRING)
    private MetadataState metadataState;

    @Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public Long getMessageId() {
		return messageId;
	}

	public void setMessageId(Long messageId) {
		this.messageId = messageId;
	}

    public MetadataState getMetadataState() {
        return metadataState;
    }

    public void setMetadataState(MetadataState metadataState) {
        this.metadataState = metadataState;
    }
}