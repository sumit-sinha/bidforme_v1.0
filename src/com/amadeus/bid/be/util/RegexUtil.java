package com.amadeus.bid.be.util;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

/**
 * utility class to validate different parameters
 * @author ssinha
 *
 */
public class RegexUtil {
	
	/**
	 * instance of {@link Logger} class
	 */
	private static Logger logger;
	
	/**
	 * {@link Pattern} for email
	 */
	private static Pattern emailPattern;
	
	static {
		logger = Logger.getLogger(RegexUtil.class.getName());
		emailPattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
				+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
	}
	
	public static boolean isValidEmail(String email) {
		if (email == null) {
			logger.log(Level.FINE, "No email provided to validate");
			return false;
		}
		
		return emailPattern.matcher(email).matches();
	}
}
