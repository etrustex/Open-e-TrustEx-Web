package eu.europa.ec.etrustex.test.hamcrest.matchers;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

import java.io.IOException;
import java.io.InputStream;

/**
 * 
 * Compares the content of two input streams
 *
 * -------------------------------------------------
 * @author:  micleva
 * @created: Dec 11, 2008 2:16:31 PM
 * @project: argus
 * -------------------------------------------------
 *
 */
public class InputStreamMatcher extends TypeSafeMatcher<InputStream>{
	
	private InputStream expectedStream;
    
    public InputStreamMatcher(InputStream expectedStream) {
        this.expectedStream = expectedStream;
    }
    
    /* (non-Javadoc)
     * @see org.hamcrest.TypeSafeMatcher#matchesSafely(java.lang.Object)
     */
    @Override
    public boolean matchesSafely(InputStream actualStream) {
        if (this.expectedStream == null) {
            return actualStream == null;
        }
        else if (actualStream == null) {
            return false;
        } 
        
        boolean result = true;
        boolean end = false;
        int expectedChar = 0;
        int actualChar = 0;
        while (!end) {
            try {
                expectedChar = expectedStream.read();
                actualChar = actualStream.read();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            if (expectedChar < 0 && actualChar < 0) {
                end = true;
            }
            else if (expectedChar != actualChar) {
                result = false;
                end = true;
            }
        }

        return result;
    }

    /* (non-Javadoc)
     * @see org.hamcrest.SelfDescribing#describeTo(org.hamcrest.Description)
     */
    public void describeTo(Description description) {
        try {
            description.appendValueList("InputStream with\n", "\n", "",
                "lenght=" + expectedStream.available());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
