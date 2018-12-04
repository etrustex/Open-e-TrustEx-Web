package eu.europa.ec.etrustex.tools.translation.model;

import java.util.Comparator;

/**
 * Translation of items that are positioned on screens.
 *
 * @author keschma
 *
 */
public class ScreenTranslation extends Translation {
	
	public static class ScreenOrderComparator implements Comparator<ScreenTranslation> {
		@Override
		public int compare(ScreenTranslation o1, ScreenTranslation o2) {
			int cmpY = Integer.valueOf(o1.screenPositionY).compareTo(Integer.valueOf(o2.screenPositionY));
			if (cmpY != 0) {
				return cmpY;
			}
			return Integer.valueOf(o1.screenPositionX).compareTo(Integer.valueOf(o2.screenPositionX));
		}
	}
	
	private int screenId;
	
	private int screenPositionX;
	
	private int screenPositionY;
	
	public ScreenTranslation(String key, String message, String languageCode,
			String tableName, int screenId, int screenPositionX, int screenPositionY) {
		super(key, message, languageCode, tableName);
		this.screenId = screenId;
		this.screenPositionX = screenPositionX;
		this.screenPositionY = screenPositionY;
	}
	
	public int getScreenId() {
		return screenId;
	}
	
	public int getScreenPositionX() {
		return screenPositionX;
	}
	
	public int getScreenPositionY() {
		return screenPositionY;
	}

}
