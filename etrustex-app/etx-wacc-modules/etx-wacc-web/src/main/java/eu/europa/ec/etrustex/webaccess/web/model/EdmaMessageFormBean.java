package eu.europa.ec.etrustex.webaccess.web.model;

import eu.europa.ec.etrustex.webaccess.web.view.business.edma.EdmaMessage;

public class EdmaMessageFormBean extends MessageFormBean {

    public EdmaMessageFormBean() {
        edmaMessage = new EdmaMessage();
    }

    public EdmaMessageFormBean(EdmaMessage edmaMessage) {
        this.edmaMessage = edmaMessage;
    }

    private EdmaMessage edmaMessage;

    public EdmaMessage getEdmaMessage() {
        return edmaMessage;
    }

    public void setEdmaMessage(EdmaMessage edmaMessage) {
        this.edmaMessage = edmaMessage;
    }
}
