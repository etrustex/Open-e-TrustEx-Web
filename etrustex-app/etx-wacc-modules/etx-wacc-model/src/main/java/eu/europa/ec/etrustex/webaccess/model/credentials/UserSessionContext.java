package eu.europa.ec.etrustex.webaccess.model.credentials;

import eu.europa.ec.etrustex.webaccess.model.Party;
import eu.europa.ec.etrustex.webaccess.model.User;
import eu.europa.ec.etrustex.webaccess.model.UserRole;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;


/**
 * @author: micleva
 * @date: 10/17/13 10:37 AM
 * @project: ETX
 */
@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserSessionContext {
    private Logger logger = Logger.getLogger(UserSessionContext.class);
    private User user;
    private List<UserRole> userRoleList;
    private CountDownLatch messagePartiesReady = new CountDownLatch(1);

    /**
     * The list of parties that have the privilege to access messages
     */
    private Collection<Party> messageParties;

    /**
     * The business user config for the message parties of the logged in user
     */
    private Map<Long, String> businessUserNameByParty;

    private boolean usingWebStart = false;


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<UserRole> getUserRoleList() {
        if (userRoleList == null){
            userRoleList = new ArrayList<>();
        }
        return userRoleList;
    }

    public void setUserRoleList(List<UserRole> userRoleList) {
        this.userRoleList = userRoleList;
    }

    public Collection<Party> getMessageParties() {
        if(CollectionUtils.isEmpty(messageParties)) {
            try {
                messagePartiesReady.await(1, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                logger.error("Error retrieving user parties", e);
            }
        }

        return messageParties;
    }

    public void setMessageParties(Collection<Party> messageParties) {
        this.messageParties = messageParties;
        messagePartiesReady.countDown();
    }

    public Party getMessagePartyById(Long partyId) {
        for (Party messageParty : getMessageParties()) {
            if (messageParty.getId().equals(partyId)) {
                return messageParty;
            }
        }
        return null;
    }

    public Party getDefaultMessageParty() {
        return messageParties.iterator().hasNext() ? messageParties.iterator().next() : null;
    }

    public Map<Long, String> getBusinessUserNameByParty() {
        return businessUserNameByParty;
    }


    public void setBusinessUserNameByParty(Map<Long, String> businessUserNameByParty) {
        this.businessUserNameByParty = businessUserNameByParty;
    }

    public boolean isUsingWebStart() {
        return usingWebStart;
    }

    public void setUsingWebStart(boolean usingWebStart) {
        this.usingWebStart = usingWebStart;
    }
}
