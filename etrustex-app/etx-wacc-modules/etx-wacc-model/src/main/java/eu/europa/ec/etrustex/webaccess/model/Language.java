package eu.europa.ec.etrustex.webaccess.model;

import javax.persistence.*;

@Entity
@Table(name="ETX_WEB_LANG")
public class Language extends AbstractEntity<Long> {

	private static final long serialVersionUID = -7257565211316182727L;

	@SequenceGenerator(name = "ETX_WEB_LNGSEQ", sequenceName = "ETX_WEB_LNGSEQ", allocationSize = 1)
    @Id
    @Column(name = "LNG_ID")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "ETX_WEB_LNGSEQ")
    private Long id;

	@Column(name="LNG_CODE")		
	private String code;
	
	@Column(name="LNG_NAME")		
	private String name;

    @Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
