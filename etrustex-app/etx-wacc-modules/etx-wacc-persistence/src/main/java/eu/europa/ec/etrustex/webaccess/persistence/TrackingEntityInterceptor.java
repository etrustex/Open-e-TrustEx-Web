package eu.europa.ec.etrustex.webaccess.persistence;

import eu.europa.ec.etrustex.webaccess.model.StateAndTrackingInformation;
import eu.europa.ec.etrustex.webaccess.persistence.support.UserAccessor;
import org.hibernate.CallbackException;
import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;

import java.io.Serializable;
import java.util.Date;

/**
 * @author: micleva
 * @date: 10/14/13 8:35 AM
 * @project: ETX
 */
public class TrackingEntityInterceptor extends EmptyInterceptor {

    @Override
    public boolean onSave(Object entity,
                          Serializable id,
                          Object[] state,
                          String[] propertyNames,
                          Type[] types)
            throws CallbackException {

        boolean modified = false;
        if (entity instanceof StateAndTrackingInformation) {
            StateAndTrackingInformation trackingInformation = (StateAndTrackingInformation) entity;
            if (trackingInformation.getCreatedBy() == null) {
                trackingInformation.setCreatedBy(UserAccessor.getUser());
                trackingInformation.setCreatedOn(new Date());
                trackingInformation.setActiveState(Boolean.TRUE);
                modified = true;
            }
        }
        if (modified) {
            StateAndTrackingInformation trackingInformation = (StateAndTrackingInformation) entity;
            for (int i = 0; i < propertyNames.length; i++) {
                if ("createdBy".equals(propertyNames[i])) {
                    state[i] = trackingInformation.getCreatedBy();
                }
                if ("createdOn".equals(propertyNames[i])) {
                    state[i] = trackingInformation.getCreatedOn();
                }
                if ("activeState".equals(propertyNames[i])) {
                    state[i] = trackingInformation.getActiveState();
                }
            }
        }
        return modified;
    }

    @Override
    public boolean onFlushDirty(Object entity,
                                Serializable id,
                                Object[] currentState,
                                Object[] previousState,
                                String[] propertyNames,
                                Type[] types)
            throws CallbackException {

        boolean modified = false;
        if (entity instanceof StateAndTrackingInformation) {
            StateAndTrackingInformation trackingInformation = (StateAndTrackingInformation) entity;

            trackingInformation.setUpdatedBy(UserAccessor.getUser());
            trackingInformation.setUpdatedOn(new Date());

            modified = true;
        }
        if (modified) {
            StateAndTrackingInformation trackingInformation = (StateAndTrackingInformation) entity;
            for (int i = 0; i < propertyNames.length; i++) {
                if ("updatedBy".equals(propertyNames[i])) {
                    currentState[i] = trackingInformation.getUpdatedBy();
                }
                if ("updatedOn".equals(propertyNames[i])) {
                    currentState[i] = trackingInformation.getUpdatedOn();
                }
            }
        }
        return modified;
    }
}
