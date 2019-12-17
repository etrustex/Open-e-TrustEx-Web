package eu.europa.ec.etrustex.webaccess.persistence.jpa;

import eu.europa.ec.etrustex.webaccess.model.*;
import eu.europa.ec.etrustex.webaccess.model.Message.MessageState;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public abstract class DAOTest {

    private static EntityManager entityManager;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    static {
        entityManager = Persistence.createEntityManagerFactory("etrust").createEntityManager();
    }

    @Before
    public void setUp() throws ParseException {
        entityManager.getTransaction().begin();
        onSetUp(entityManager);
    }

    @After
    public void cleanUp() {
        entityManager.getTransaction().rollback();
    }

    protected abstract void onSetUp(EntityManager entityManager);

    protected int random(int min, int max) {
        Random rnd = new Random();
        return rnd.nextInt(max) + min;
    }

    protected <T> T random(T[] values) {
        Random rnd = new Random();
        int index = rnd.nextInt(values.length - 1);
        return values[index];
    }

    protected String randomString(int lenght) {
        System.out.println("RandomStringUtils: " + lenght);
        return UUID.randomUUID().toString();
    }

    protected Message generateMessage(Party localParty, Party remoteParty, MessageState messageState) {
        Message md = new Message();
        md.setMsgState(messageState);
        md.setCreatedOn(new Date());
        md.setIssueDate(new Date());
        md.setContent(randomString(random(30, 250)));
        md.setSubject(randomString(random(3, 30)));
        md.setReferenceId("dossier" + random(1, 1000));

        md.setLocalParty(localParty);
        md.setRemoteParty(remoteParty);
        return md;
    }

    protected void populateAttachmant(Attachment ad) {
        ad.setTransmissionChecksumMethod("md5");
        ad.setTransmissionChecksum("weirdchecksum".getBytes());
        ad.setType(random(new AttachmentType[]{AttachmentType.BINARY, AttachmentType.METADATA}));
        ad.setWrapperId(random(new String[]{"ref1", "ref2", "ref3", "ref4"}));
        ad.setFileName("fileName" + random(1, 300));
        ad.setFileSize((long) random(1, 10000));
        ad.setMimeType(random(new String[]{"application/xml", "txt/xml", "txt/plain"}));
    }

    protected static void executeQuery(String query) {
        Query q = entityManager.createNativeQuery(query);
        @SuppressWarnings("unused")
        int resCode = q.executeUpdate();
        System.out.println("executed query: " + query);
    }

    protected static void insertParty(Long id, String email, String displayName, String nodeName, Long businessId, Date createDate, Long createdBy, boolean isActive, boolean isWebManaged) {
        executeQuery(
                "Insert into ETX_WEB_PARTY (PAR_ID,PAR_EMAIL,PAR_DISPLAY_NAME,PAR_NODE_NAME,BUS_ID,PAR_CREATED_ON,PAR_CREATED_BY, PAR_ACTIVE_STATE, PAR_WEB_MANAGED) values (" +
                        id + ", '" + email + "', '" + displayName + "', '" + nodeName + "', " + businessId + ", " +
                        getDate(createDate) + ", " + createdBy + ", " + (isActive ? 1 : 0) + ", " + (isWebManaged ? 1 : 0) + ")");
    }

    protected static void insertPartyIca(Long id, Long partyId, Long remotePartyId, Long createdBy, Date loadedOn, Date createdOn, Date updatedOn, Long updatedBy, boolean isActive) {
        executeQuery("INSERT INTO ETX_WEB_PARTY_ICA (PCA_ID, PAR_ID, REMOTE_PAR_ID, PCA_CREATED_BY, PCA_LOADED_ON, PCA_INTEGRITY, PCA_CONFIDENTIALITY," +
                " PCA_CREATED_ON, PCA_UPDATED_ON, PCA_UPDATED_BY, PCA_STATE) values (" + id + ", " + partyId + ", " + remotePartyId + ", " + createdBy + ", " + getDate(loadedOn) +
                " , null, null, " + getDate(createdOn) + ", " + getDate(updatedOn) + ", " + updatedBy + ", " + (isActive ? 1 : 0) + ")");
    }

    protected static void insertUserRole(Long id, Long userId, Role.Type roleType, Long partyId, Long businessId, Date createDate, Long createdBy, boolean isActive) {
        executeQuery(
                "Insert into ETX_WEB_USER_ROLE (URO_ID,USR_ID,ROL_TYPE,PAR_ID,BUS_ID,URO_CREATED_ON,URO_CREATED_BY,URO_ACTIVE_STATE) values (" +
                        id + ", " + userId + ", '" + roleType + "', " + partyId + ", " + businessId + ", " + getDate(createDate) + ", " + createdBy + ", " + (isActive ? 1 : 0) + ")");
    }

    protected static void insertBusinessConfig(Long id, Long busId, String name, String value) {
        executeQuery(
                "Insert into ETX_WEB_BUSINESS_CONFIG (BCG_ID,BUS_ID,BCG_PROPERTY_NAME,BCG_PROPERTY_VALUE) values (" +
                        id + ", " + busId + ", '" + name + "', '" + value + "')");
    }

    protected static void insertBusiness(Long id, String name, Date createDate, Long createdBy, boolean isActive) {
        executeQuery(
                "Insert into ETX_WEB_BUSINESS (BUS_ID,BUS_NAME,BUS_CREATED_ON,BUS_CREATED_BY, BUS_ACTIVE_STATE) values (" +
                        id + ", '" + name + "', " + getDate(createDate) + ", " + createdBy + ", " + (isActive ? 1 : 0) + ")");
    }

    protected static void insertBusinessUserConfig(Long id, Long userId, Long businessId, String name, String email, Date createDate, Long createdBy, boolean isActive) {
        executeQuery(
                "Insert into ETX_WEB_BUSINESS_USER_CONFIG (BUC_ID,USR_ID,BUS_ID,BUC_NAME,BUC_EMAIL,BUC_CREATED_ON,BUC_CREATED_BY,BUC_ACTIVE_STATE) values (" +
                        id + ", " + userId + ", " + businessId + ", '" + name + "', '" + email + "', " + getDate(createDate) + ", '" + createdBy + "'," + (isActive ? 1 : 0) + ")");
    }

    protected static void insertRole(Role.Type roleType) {
        executeQuery(
                "Insert into ETX_WEB_ROLE (ROL_TYPE, ROL_NAME) values (" +
                        " '" + roleType + "','NAME')");
    }

    protected void insertPrivilege(Privilege.Type privilegeType) {
        executeQuery(
                "Insert into ETX_WEB_PRIVILEGE (PRV_TYPE, PRV_NAME) values (" +
                        " '" + privilegeType + "','NAME')");
    }

    protected void insertRoleWithPrivilege(Role.Type roleType, Privilege.Type... privilegeTypes) {
        insertRole(roleType);
        for (Privilege.Type privilegeType : privilegeTypes) {
            insertPrivilege(privilegeType);
            assignPrivilegeToRole(roleType, privilegeType);
        }
    }

    protected void assignPrivilegeToRole(Role.Type roleType, Privilege.Type privilegeType) {
        executeQuery(
                "Insert into ETX_WEB_ROLE_PRIVILEGE (ROL_TYPE, PRV_TYPE) values (" +
                        " '" + roleType + "','" + privilegeType + "')");
    }

    protected static void insertLanguage(Long id, String code, String name) {
        executeQuery(
                "Insert into ETX_WEB_LANG (LNG_ID,LNG_CODE,LNG_NAME) values (" +
                        id + ", '" + code + "', '" + name + "')"
        );
    }


    protected static void insertUser(Long id, String login, String name, Date createDate, Long createdBy, boolean isActive) {

        executeQuery(
                "Insert into ETX_WEB_USER (USR_ID,USR_LOGIN,USR_NAME,USR_CREATED_ON,USR_CREATED_BY, USR_ACTIVE_STATE) values (" +
                        id + ", '" + login + "', '" + name + "', " + getDate(createDate) + ", " + createdBy + ", " + (isActive ? 1 : 0) + ")");
    }

    protected static void insertMessageRead(Long id, Long msgId, Long userId) {
        executeQuery(
                "Insert into ETX_WEB_MESSAGE_READ (MSR_ID,MSG_ID,USR_ID) values (" +
                        id.longValue() + ", " + msgId.longValue() + ", " + userId.longValue() + ")"
        );
    }

    protected static void insertMessage(Long id, String msgContent, Date issueDate, String refId, String subject, Long localPartyId, Long remotePartyId, String messageState, String bundleId, Date createdOn, Long createdBy, boolean isActive) {
        executeQuery(
                "Insert into ETX_WEB_MESSAGE (MSG_ID, MSG_CONTENT, MSG_ISSUE_DATE, MSG_REF_ID, MSG_SUBJECT, MSG_LOCAL_PARTY_ID, MSG_REMOTE_PARTY_ID, MSG_STATE, MSG_BUNDLE_ID, MSG_CREATED_ON, MSG_CREATED_BY, MSG_ACTIVE_STATE) values (" +
                        id + "," +
                        "'" + msgContent + "', " +
                        "" + getDate(issueDate) + ", " +
                        "'" + refId + "', " +
                        "'" + subject + "', " +
                        "'" + localPartyId + "', " +
                        "" + remotePartyId + ", " +
                        "'" + messageState + "', " +
                        (bundleId != null ? "'" + bundleId + "', " : null + ", ") +
                        getDate(createdOn) + ",  " +
                        createdBy + ",  " +
                        (isActive ? 1 : 0) +
                        ")");
    }


    protected static void insertAttachment(Long id, String checkSum, String checkSumMethod, String mimeType, String fileName, Long fileSize, String attachmentType, String referenceId, Long msgId) {
        executeQuery(
                "Insert into ETX_WEB_ATTACHMENT (ATT_ID, ATT_TRANSMISSION_CHK, ATT_TRANSMISSION_CHK_METHOD, ATT_MIME_TYPE, ATT_FILE_NAME, ATT_FILE_SIZE, ATT_TYPE, ATT_WRAPPER_ID, MSG_ID) values (" +
                        id + ", " + null + ", '" + checkSumMethod + "', '" + mimeType + "', '" + fileName + "', " + fileSize.longValue() + ", '" + attachmentType + "', '" + referenceId + "', " + msgId.longValue() + ")");
    }

    protected static void insertNotification(Long id, Long messageId, Long bucId, Long partyId, String email, Notification.NotificationType type, Notification.NotificationState state, Date createdOn) {
        executeQuery("INSERT INTO ETX_WEB_NOTIFICATION (NTF_ID, MSG_ID, BUC_ID, PAR_ID, NTF_EMAIL, NTF_TYPE, NTF_STATE, NTF_CREATED_ON) VALUES (" +
                id + ", " + messageId + ", " + bucId + ", " + partyId + ", '" + email + "', '" + type + "', '" + state + "', " + getDate(createdOn) + ")");
    }

    protected static void insertMessageStatus(Long id, Long msgId, String statusUuid, MessageStatus.State msgState, MessageStatus.Status mstStatus, String mstStatusCode, String login, Date createdOn, Date updatedOn, Long parentId) {
        executeQuery("Insert into ETX_WEB_MESSAGE_STATUS (MST_ID,MSG_ID,MST_UUID,MST_STATE,MST_STATUS,MST_STATUS_CODE,MST_LOGIN,MST_CREATED_ON,MST_UPDATED_ON,MST_PARENT_ID) values (" +
                id + ", " + msgId + ", '" + statusUuid + "', " + "'" + msgState.name() + "', " + "'" + mstStatus.name() + "', " + "'" + mstStatusCode + "', " + "'" + login + "', " + getDate(createdOn) + ", " + (updatedOn == null ? null : getDate(updatedOn)) + ", " + parentId + ")");
    }

    protected Message createMessageGraph(Long localPartyId, Long remotePartyId, Long remotePartyIca, MessageState messageState) {
        Party localParty = new Party();
        localParty.setDisplayName("Party1");
        localParty.setNodeName("ref1");
        localParty.setId(localPartyId);

        PartyIca partyIca = new PartyIca();
        Party remoteParty = new Party();
        remoteParty.setNodeName("partySender");
        remoteParty.setId(remotePartyId);
        partyIca.setRemoteParty(remoteParty);
        partyIca.setId(remotePartyIca);

        Message msg = generateMessage(localParty, remoteParty, messageState);

        List<Attachment> atts = new ArrayList<>();
        for (int j = 0; j < 3; j++) {

            Attachment ad = new Attachment();
            ad.setMessage(msg);
            populateAttachmant(ad);
            atts.add(ad);
        }

        msg.setAttachments(atts);
        return msg;
    }

    public static String getDate(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yy HH:mm:ss");

        return "to_Date('" + formatter.format(date) + "', 'DD-MM-YY HH:MI:SS')";
    }
}
