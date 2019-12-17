package eu.europa.ec.etrustex.tools.db.common;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Some utilities for executable classes.
 *
 * @author keschma
 *
 */
class MainUtils {
	
	private final Logger logger;
	
	private final Class<?> mainClass;
	
	private final String[] mainArgs;
	
	private final String usageMessage;
	
	/**
	 * The constructor.
	 * @param mainClass the main class (the caller)
	 * @param mainArgs the arguments to the main class
	 * @param usageMessage the message to display as usage information,
	 * i.e. the parameters for calling the main class
	 */
	MainUtils(Class<?> mainClass, String[] mainArgs, String usageMessage) {
		this.logger = Logger.getLogger(mainClass);
		this.mainClass = mainClass;
		this.mainArgs = mainArgs;
		this.usageMessage = usageMessage;
				
		logger.info("### ========================================================================================================================= ###");
		logger.info("### call: " + mainClass.getName());
		logger.info("### args: " + Arrays.toString(mainArgs));
 		logger.info("### ========================================================================================================================= ###");
	}
	
	/**
	 * Displays usage information and exits the runtime.
	 */
	void usage() {
		System.err.println("Usage: " + mainClass.getName() + " " + usageMessage);
		System.exit(1);
	}
	
	/**
	 * Gets a command line argument value with the given name.
	 * This method expects the command line to use a format
	 * &lt;param1&gt;=&lt;value1&gt; ... &lt;paramN&gt;=&lt;valueN&gt;.
	 * @param name the name of the argument
	 * @return the argument value, "", if the argument is empty or
	 * exiting the runtime, if not found
	 */
	String getRequiredValue(String name) {
		final String value = getOptionalValue(name);
		
		if (value == null) {
			logger.error("No option found with name: " + name);
			usage();
			return null; // never called; will exit through usage() 
		}
		return value; 
	}
	
	/**
	 * Gets a command line argument value with the given name.
	 * This method expects the command line to use a format
	 * &lt;param1&gt;=&lt;value1&gt; ... &lt;paramN&gt;=&lt;valueN&gt;.
	 * @param name the name of the argument
	 * @return the argument value, "", if the argument is empty, or null,
	 * if not found
	 */
	String getOptionalValue(String name) {
		for (String arg : mainArgs) {
			String[] splitArg = arg.split("=");
			
			if (splitArg[0].equals(name)) {
				if (splitArg.length < 1 || splitArg.length > 2) {
					logger.error("Wrong command line options: " + Arrays.toString(mainArgs));
					usage();
				}
				return splitArg.length == 2 ? splitArg[1] : "";
			}			
		}
		
		return null;
	}
	
	/**
	 * Parses a string of the format <code>S1,...,...Sn</code>, where
	 * Si is a substring, a list of the corresponding substrings.
	 * @param listString a string representation of an string list
	 * @return the corresponding string list
	 */
	List<String> parseStringList(String listString) {
		if (listString.equals("")) {
			return Collections.emptyList();
		}
		
		final String[] subStrings = listString.split("\\s*,\\s*");
		return new ArrayList<>(Arrays.asList(subStrings));
	}	
	
	/**
	 * Parses a string of the format <code>N1,...,...Nn</code>, where
	 * Ni is the numeric representation of an integer, into
	 * a list of the corresponding integers.
	 * @param listString a string representation of an integer list
	 * @return the corresponding integer list
	 */
	List<Integer> parseIntegerList(String listString) {
		final List<String> stringList = parseStringList(listString);
		final List<Integer> result = new ArrayList<>(stringList.size());
		
		for (String subString : stringList) {
			result.add(Integer.valueOf(subString));
		}		
		return result;		
	}
}
