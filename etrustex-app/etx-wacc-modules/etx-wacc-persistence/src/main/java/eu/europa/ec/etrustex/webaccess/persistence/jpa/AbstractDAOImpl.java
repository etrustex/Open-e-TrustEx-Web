package eu.europa.ec.etrustex.webaccess.persistence.jpa;

import eu.europa.ec.etrustex.webaccess.model.AbstractEntity;
import eu.europa.ec.etrustex.webaccess.persistence.AbstractDAO;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;


/**
 * An abstract JPA-based DAO. Each DAO instance is bound
 * to a specific Entity type, which is managed by the
 * given DAO.
 *
 * @param <T>  the entity type which is handled by this DAO
 * @param <ID> the primary key class type of the T-entities (most often: Long)
 * @author apladap
 */
public abstract class AbstractDAOImpl<T extends AbstractEntity<ID>, ID extends Serializable> implements AbstractDAO<T, ID> {

    private Logger logger = Logger.getLogger(AbstractDAOImpl.class.getName());

    private Class<T> entityClass;

    @PersistenceContext(unitName = "etrust")
    protected EntityManager entityManager;

    protected static final int ORACLE_MAX_IN_STATEMENT_VALUES = 1000;

    @SuppressWarnings("unchecked")
    protected AbstractDAOImpl() {
        ParameterizedType genericSuperclass = (ParameterizedType) getClass()
                .getGenericSuperclass();

        Type type = genericSuperclass.getActualTypeArguments()[0];
        if (type instanceof ParameterizedType) {
            //due to proxyed objects, we need to get the rawType of a parameterizedType
            this.entityClass = (Class<T>) ((ParameterizedType) type).getRawType();
        } else {
            this.entityClass = (Class<T>) type;
        }
    }

    public void save(T instance) {
        logger.debug("saving " + entityClass.getSimpleName() + " instance");
        entityManager.persist(instance);
    }

    public T update(T entity) {
        logger.debug("updating " + entityClass.getSimpleName() + " instance");
        return entityManager.merge(entity);
    }

    public T findById(ID id) {
        return entityManager.find(entityClass, id);
    }
}
