package eu.europa.ec.etrustex.tools.translation.model;

/**
 * Enumeration of all translation table names.
 *
 * @author keschma
 *
 */
public enum TranslationTable {
	
	LABEL("ETX_WEB_LABEL_TRANSLATION");
		
	private String tableName;
	
	private TranslationTable(String tableName) {
		this.tableName = tableName;
	}
	
	public String getTableName() {
		return tableName;
	}

}
