package com.amadeus.bid.be.fwk;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * read localized string from property files
 * @author sumit
 *
 */
public class LocalizationUtil {

	/**
	 *  static instance of {@link ResourceBundle}
	 */
	private static ResourceBundle bundle;

	static {
		bundle = ResourceBundle.getBundle("properties.ResourceBundle");
	}

	/**
	 * returns a localized string based on key
	 * @param key {@link String}
	 * @return localized {@link String}
	 */
	public static String getString(String key) {
		if (key == null) {
			return "";
		}

		try{
			return bundle.getString(key);
		} catch (MissingResourceException e) {
			return "???" + key + "???";
		} catch (ClassCastException e) {
			return "???" + key + "???";
		}

	}
}