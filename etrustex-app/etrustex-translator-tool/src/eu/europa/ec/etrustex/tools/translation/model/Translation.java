package eu.europa.ec.etrustex.tools.translation.model;


/**
 * A lightweight translation model object.
 *
 * @author keschma
 *
 */
public class Translation {
	
	private String key;
	
	private String message;
	
	private String languageCode;
	
	private String tableName;
	
	private int batchIndex;
	
	public Translation(String key, String message, String languageCode, String tableName) {
		this.key = key;
		this.message = message;
		this.languageCode = languageCode;
		this.tableName = tableName;
	}
	
	public String getKey() {
		return key;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getLanguageCode() {
		return languageCode;
	}
	
	public String getTableName() {
		return tableName;
	}
	
	public int getBatchIndex() {
		return batchIndex;
	}
	
	public void setBatchIndex(int batchIndex) {
		this.batchIndex = batchIndex;
	}
	
	/**
	 * Checks whether this translation is from one of a given
	 * set of translation tables.
	 * @param tables some tables
	 * @return true, if this translation is from any of the given
	 * tables
	 */
	public boolean isFromTable(TranslationTable... tables) {
		for (TranslationTable table : tables) {
			if (table.getTableName().equalsIgnoreCase(tableName)) {
				return true;
			}
		}
		
		return false;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((key == null) ? 0 : key.hashCode());
		result = prime * result
				+ ((languageCode == null) ? 0 : languageCode.hashCode());
		result = prime * result
				+ ((tableName == null) ? 0 : tableName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Translation other = (Translation) obj;
		if (key == null) {
			if (other.key != null)
				return false;
		} else if (!key.equals(other.key))
			return false;
		if (languageCode == null) {
			if (other.languageCode != null)
				return false;
		} else if (!languageCode.equals(other.languageCode))
			return false;
		if (tableName == null) {
			if (other.tableName != null)
				return false;
		} else if (!tableName.equals(other.tableName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Translation [key=");
		builder.append(key);
		builder.append(", message=");
		builder.append(message);
		builder.append(", languageCode=");
		builder.append(languageCode);
		builder.append(", tableName=");
		builder.append(tableName);
		builder.append("]");
		return builder.toString();
	}

}
