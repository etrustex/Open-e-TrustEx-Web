package eu.europa.ec.etrustex.tools.translation.transformer.exports;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

/**
 * An output stream decorator which inserts a given binary content
 * after <em>the first</em>occurrence of a given byte pattern when performing
 * its write operations.
 *
 * @author keschma
 *
 */
class ContentInsertingOutputStream extends OutputStream {
	
	private OutputStream os;
	
	private byte[] content;
	
	private byte[] pattern;
	
	private int matchSize = 0; 
	
	/**
	 * Constructs the instance.
	 * @param os the OutputStream enriched by this object
	 * @param content the binary content to insert
	 * @param pattern the byte pattern marking <em>the first</em> place after
	 * which the byte content will be inserted
	 */
	public ContentInsertingOutputStream(OutputStream os, byte[] content, byte[] pattern) {
		this.os = os;
		this.content = content;
		this.pattern = pattern;
	}
	
	/**
	 * Convenience constructor using the UTF-8 representation of strings
	 * both as content and pattern.
	 * @param os the OutputStream enriched by this object
	 * @param content the string content to insert
	 * @param pattern the string pattern marking <em>the first</em> place after
	 * which the string content will be inserted
	 */
	public ContentInsertingOutputStream(OutputStream os, String content, String pattern) {
		this.os = os;
		try {
			this.content = content.getBytes("UTF-8");
			this.pattern = pattern.getBytes("UTF-8");
		} catch(UnsupportedEncodingException e) {
			// cannot ever happen; UTF-8 is always there :-)
		}
	}
	
	@Override
	public void write(int b) throws IOException {
		if (matchSize < pattern.length && b == pattern[matchSize]) {
			matchSize++;
		}
		// delegate to the underlying output stream
		os.write(b);
		
		if (matchSize == pattern.length) {
			// dump the content to the underlying output stream
			// and disable further matchings
			os.write(content);
			matchSize = pattern.length + 1;
		}			
	}
	
}