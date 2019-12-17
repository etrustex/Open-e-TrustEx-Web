package eu.europa.ec.etrustex.webaccess.model;

import java.io.Serializable;

/**
 * An abstract entity. This should be the superclass of
 * every persistent object in the project. The type parameter
 * is the type of the entity's primary key and should be,
 * in most cases, Long. 
 *
 * @author apladap
 *
 */
public abstract class AbstractEntity<T extends Serializable> implements Serializable {

	private static final long serialVersionUID = 8756769536600986608L;
	
	// Note: we cannot create an id field in here
	// (we must connect it to a specific sequence generator for each
	// actual entity, and this cannot be overridden)
	
	public abstract T getId();
	
	public void setId(T id){};

    protected AbstractEntity() {
    }

    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AbstractEntity<?> other = (AbstractEntity<?>) obj;
		if (getId() == null || other.getId() == null)
			return false;
		if (!getId().equals(other.getId()))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(getClass().getSimpleName()).append("[id=");
		builder.append(getId());
		builder.append("]");
		return builder.toString();
	}
	
}
