package eu.europa.ec.etrustex.webaccess.model;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

@Entity
@Table(name="ETX_WEB_MESSAGE_READ")
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class MessageRead extends AbstractEntity<Long> {
	
	@SequenceGenerator(name = "ETX_WEB_MSG_READSEQ", sequenceName = "ETX_WEB_MSG_READSEQ", allocationSize = 1)
    @Id
    @Column(name = "MSR_ID")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "ETX_WEB_MSG_READSEQ")
    private Long id;

	@Column(name="MSG_ID")
	private Long messageId;
	
	@Column(name="USR_ID")
	private Long userId;

    public Long getMessageId() {
		return messageId;
	}

	public void setMessageId(Long messageId) {
		this.messageId = messageId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
