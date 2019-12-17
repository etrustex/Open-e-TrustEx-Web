package eu.europa.ec.etrustex.webaccess.model;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

@Entity
@Table(name="ETX_WEB_CONFIG")
@NamedQueries({
        @NamedQuery(name = "findAllConfigs", query = "SELECT o FROM Config o",
                hints = { @QueryHint(name = "org.hibernate.cacheable", value = "true") })
})
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class Config extends AbstractEntity<Long> {

	private static final long serialVersionUID = 7884816635459429320L;

	@Id
	@Column(name="CFG_ID")
	private Long id;
	
	@Column(name="CFG_PROP_NAME")
	private String name;
	
	@Column(name="CFG_PROP_VALUE")
	private String value;

    @Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("Config{");
		sb.append("id=").append(id);
		sb.append(", name='").append(name).append('\'');
		sb.append(", value='").append(value).append('\'');
		sb.append('}');
		return sb.toString();
	}
}
