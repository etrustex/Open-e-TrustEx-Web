/**
 *
 */
package eu.europa.ec.etrustex.webaccess.utils.applet;

import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;


/**
 * @author apladap
 */
public class DecoderTest {

    @Test
    public void testDecodingSuccesss() throws IOException, ClassNotFoundException {
        List<String> fileList = new ArrayList<>();

        String file1 = "Filename1";
        String file2 = "Filename2";

        fileList.add(file1);
        fileList.add(file2);

        String string2Decode = Encoder.encode(fileList);

        List<String> decodeResult = Decoder.decode(string2Decode, String.class);

        assertThat(decodeResult, notNullValue());
        assertThat(decodeResult.isEmpty(), is(Boolean.FALSE));
        assertThat(decodeResult.toString(), notNullValue());
        assertThat(decodeResult.size(), is(2));
        assertThat(decodeResult.get(0), is("Filename1"));
        assertThat(decodeResult.get(1), is("Filename2"));
    }

    @Test(expected = IOException.class)
    public void testDecodingFailure() throws IOException, ClassNotFoundException {
        String str2Decode = "Filename1,Filename2";

        Decoder.decode(str2Decode, String.class);

    }

}
