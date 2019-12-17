package eu.europa.ec.etrustex.webaccess.utils;

import eu.europa.ec.etrustex.test.support.AbstractFileTest;
import org.junit.Test;

import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class HashHelperTest extends AbstractFileTest {

    private HashHelper hashHelper;

    @Override
    protected void onSetUp() {
        hashHelper = new HashHelper();
    }

    @Test
    public void testHashWithSha512_File() throws Exception {
        // given
        String msg = "MessageBundle";

        String path2Save = System.getProperty("java.io.tmpdir");
        String fileName = "testHashWithSha512_File.txt";
        String absoluteFileName = path2Save + FileSystems.getDefault().getSeparator() + fileName;

        Path path = Paths.get(absoluteFileName);
        Files.write(path, msg.getBytes());

        try {
            // when
            byte[] hash = hashHelper.hash(path, HashHelper.HashMethodType.SHA_512.getCode());

            // then
            byte[] expectedResult = new byte[]{117, 58, 14, -93, 84, -109, -124, 120, -81, -30, -111, -122, 12, 27, 2, 126, 115,
                    25, -50, 87, -65, 78, -71, 15, -45, -55, 36, -60, -95, -74, -12, 57, 0, 89, 4, 31, -43, 127, -97, -123, 127,
                    57, 44, 72, 82, -122, 45, 13, 109, 4, 122, 83, -10, -80, 86, -16, -122, -108, -44, 18, -13, -80, 44, 64};

            assertThat(hash, is(expectedResult));
        } finally {
            cleanTmpFile(absoluteFileName);
        }
    }

    @Test
    public void testHashWithSha512_ArrayDataSource() throws Exception {
        // given
        String msg = "MessageBundle";

        // when
        byte[] hash = hashHelper.hash(msg.getBytes(), HashHelper.HashMethodType.SHA_512.getCode());

        // then
        byte[] expectedResult = new byte[]{117, 58, 14, -93, 84, -109, -124, 120, -81, -30, -111, -122, 12, 27, 2, 126, 115,
                25, -50, 87, -65, 78, -71, 15, -45, -55, 36, -60, -95, -74, -12, 57, 0, 89, 4, 31, -43, 127, -97, -123, 127,
                57, 44, 72, 82, -122, 45, 13, 109, 4, 122, 83, -10, -80, 86, -16, -122, -108, -44, 18, -13, -80, 44, 64};

        assertThat(hash, is(expectedResult));
    }

}