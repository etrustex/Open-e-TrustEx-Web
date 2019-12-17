package eu.europa.ec.etrustex.webaccess.web.utils;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

/**
 * Error handling related utilities.
 * 
 * @author chiridl
 * 
 */
public class ErrorHandlingUtils {

	/**
	 * Returns the entire stacktrace of the given {@link Exception} as a string.
	 * 
	 * @param e
	 *            the exception
	 * @return the stacktrace
	 */
	public static String getStackTrace(Exception e) {
		final Writer result = new StringWriter();
		final PrintWriter printWriter = new PrintWriter(result);
		e.printStackTrace(printWriter);

		return result.toString();
	}

}
