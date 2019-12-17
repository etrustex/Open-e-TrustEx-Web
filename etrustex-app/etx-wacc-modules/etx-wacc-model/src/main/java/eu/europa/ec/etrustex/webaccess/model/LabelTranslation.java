/**
 * 
 */
package eu.europa.ec.etrustex.webaccess.model;

import javax.persistence.*;

@Entity
@Table(name="ETX_WEB_LABEL_TRANSLATION")
public class LabelTranslation extends AbstractEntity<Long> {

	private static final long serialVersionUID = 2392928836567673010L;

    @Id
    @Column(name="LTR_ID", updatable=false)
    private Long id;

    @Column(name="LTR_KEY", insertable=false, updatable=false)
    private String key;

    @Column(name="LTR_MESSAGE")
    private String message;

    // this is used in a JQL query
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="LNG_ID")
    private Language language;

    @Column(name="LTR_SCREEN_ID", insertable=false, updatable=false)
	private Long screenId;
    
    @Column(name="LTR_SCREEN_POSITION_X")
	private Integer screenPositionX;

	@Column(name="LTR_SCREEN_POSITION_Y")
	private Integer screenPositionY;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getScreenId() {
		return screenId;
	}

	public void setScreenId(Long screenId) {
		this.screenId = screenId;
	}
	
	public Integer getScreenPositionX() {
		return this.screenPositionX;
	}

	public void setScreenPositionX(Integer screenPositionX) {
		this.screenPositionX = screenPositionX;
	}

	public Integer getScreenPositionY() {
		return this.screenPositionY;
	}

	public void setScreenPositionY(Integer screenPositionY) {
		this.screenPositionY = screenPositionY;
	}

}