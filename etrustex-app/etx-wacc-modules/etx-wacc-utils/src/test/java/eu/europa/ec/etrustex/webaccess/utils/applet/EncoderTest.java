/**
 * 
 */
package eu.europa.ec.etrustex.webaccess.utils.applet;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;


/**
 * @author apladap
 *
 */
public class EncoderTest {
	
	@Test
	public void testEncodingSuccess() throws IOException{
		
		List<String> fileList = new ArrayList<>();
		
		String file1= "Filename1";
		String file2= "Filename2";
		
		fileList.add(file1);
		fileList.add(file2);
		
		String encodedString = Encoder.encode(fileList);
		
		assertThat(encodedString, notNullValue());
		//This is here in order to detect any changes at the implementation of the encode method
		assertThat(encodedString, is(equalTo("H4sIAAAAAAAAAFvzloG1nIWBgYGphIHTLTMnNS8xN9UQiW0EAIG/zXMiAAAA")));
	}

}
