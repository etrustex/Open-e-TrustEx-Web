package eu.europa.ec.etrustex.webaccess.persistence.jpa;

/**
 * @author dzierma
 *
 */
public enum MessageColumn {

	SUBJECT("subject"), ID("id"), SENDER("sender.name"), DOSSIER("referenceId"), UNREAD("unread"), RECEIVED("createdOn"),
	SAVED("savedOn"), SENT("sentOn");

	private String column;

	private MessageColumn(String column) {
		this.column = column;
	}

	public String getColumn() {
		return column;
	}

}
