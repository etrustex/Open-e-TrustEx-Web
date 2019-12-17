package eu.europa.ec.etrustex.webaccess.persistence;

import java.io.Serializable;

/**
 * @author: micleva
 * @date: 9/23/13 10:13 AM
 * @project: ETX
 */
public interface AbstractDAO<T, ID extends Serializable> {

    /**
     * Find by primary key.
     *
     * @param id the primary key of the entity being loaded
     * @return the found entity instance or null if the entity does not exist
     */
    T findById(ID id);

    /**
     * Make an entity instance managed and persistent
     *
     * @param entity the entity to be persisted
     */
    void save(T entity);

    /**
     * Merge the state of the given entity into the current persistence context.
     *
     * @param entity the entity which needs to be updated
     * @return the updated entity
     */
    T update(T entity);
}
