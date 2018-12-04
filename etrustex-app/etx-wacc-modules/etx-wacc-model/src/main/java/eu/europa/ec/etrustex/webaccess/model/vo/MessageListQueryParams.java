package eu.europa.ec.etrustex.webaccess.model.vo;

import java.util.HashMap;
import java.util.Map;

public class MessageListQueryParams extends QueryParams {

    private int page = 1;
    private int offset = 10;
    private Map<MessageColumn, String> filters = new HashMap<>();
    private SortDirection sortDirection;
    private boolean unreadOnly = false;
    private MessageStatus messageStatus = MessageStatus.ALL;

    public enum SortDirection {
        ASC, DESC;

        public boolean isAscending() {
            return this.equals(ASC);
        }
    }

    public enum MessageColumn {
        SUBJECT, RECEIVED
    }

    public enum MessageStatus {
        ALL("label.status.all", null),
        NONE("label.status.none", null),
        DELIVERED("label.status.delivered", eu.europa.ec.etrustex.webaccess.model.MessageStatus.Status.AVAILABLE),
        READ("label.status.read", eu.europa.ec.etrustex.webaccess.model.MessageStatus.Status.READ),
        FAILED("label.status.failed", eu.europa.ec.etrustex.webaccess.model.MessageStatus.Status.FAILED);

        MessageStatus(String msgCode, eu.europa.ec.etrustex.webaccess.model.MessageStatus.Status msgStatus) {
            this.msgCode = msgCode;
            this.msgStatus = msgStatus;
        }

        private String msgCode;
        private eu.europa.ec.etrustex.webaccess.model.MessageStatus.Status msgStatus;

        public static MessageStatus parse(eu.europa.ec.etrustex.webaccess.model.MessageStatus.Status status) {
            if (status != null) {
                for (MessageStatus ms : values()) {
                    if (ms.msgStatus == status) {
                        return ms;
                    }
                }
            }
            return null;
        }

        public String getMsgCode() {
            return msgCode;
        }

        public eu.europa.ec.etrustex.webaccess.model.MessageStatus.Status getMsgStatus() {
            return msgStatus;
        }
    }

    public int getStart() {
        return (page - 1) * offset + 1;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public void setFilters(Map<MessageColumn, String> filters) {
        this.filters = filters;
    }

    public Map<MessageColumn, String> getFilters() {
        return filters;
    }

    public SortDirection getSortDirection() {
        return sortDirection;
    }

    public void setSortDirection(SortDirection descending) {
        this.sortDirection = descending;
    }

    public boolean isUnreadOnly() {
        return unreadOnly;
    }

    public void setUnreadOnly(boolean unreadOnly) {
        this.unreadOnly = unreadOnly;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public MessageStatus getMessageStatus() {
        return messageStatus;
    }

    public void setMessageStatus(MessageStatus messageStatus) {
        this.messageStatus = messageStatus;
    }

    @Override
    public String toString() {
        return "MessageListQueryParams [page=" + page + ", offset=" + offset + ", unread=" + unreadOnly + ", filters=" + filters + "]";
    }
}
