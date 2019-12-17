package eu.europa.ec.etrustex.webaccess.persistence.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by lubiesz on 24/05/2016.
 */
@RunWith(Parameterized.class)
public class MessageUtilTest {

    private String input;
    private String expected;

    public MessageUtilTest(String input, String expected) {
        this.input = input;
        this.expected = expected;
    }

    @Test
    public void shouldNormalizeSting() {
        assertThat(MessageUtil.normalizeString(input), is(expected));
    }

    @Parameterized.Parameters
    public static Collection primeNumbers() {
        return Arrays.asList(new Object[][] {
                { "ADM/śśŻŻ (1232),dd [3883-000] ĘĄŻL/777", "admsszz1232dd3883000eazl777" },
                { "  -1/AG82[kjk $009.ABC,XYZ", "1ag82kjk009abcxyz"},
                { "Et ça sera sa moitié.", "etcaserasamoitie"},
                { null, null },
                { "", "" },
                { " ", "" }

        });
    }

}