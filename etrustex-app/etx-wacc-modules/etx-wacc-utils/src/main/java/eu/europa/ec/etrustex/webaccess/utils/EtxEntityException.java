package eu.europa.ec.etrustex.webaccess.utils;

import eu.europa.ec.etrustex.webaccess.model.AbstractEntity;

/**
 * Created by canofcr on 22/05/2017.
 */
public class EtxEntityException extends EtxException {

    private AbstractEntity<Long> entity;

    public EtxEntityException(String message, AbstractEntity<Long> entity) {
        super(message);
        this.entity = entity;
    }

    public AbstractEntity<Long> getEntity() {
        return this.entity;
    }

}
