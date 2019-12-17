package eu.europa.ec.etrustex.test.support;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Paths;

public abstract class AbstractFileTest extends AbstractTest {

    protected void cleanTmpFile(String filePath) {
        try {
            Files.deleteIfExists(Paths.get(filePath));
        } catch (IOException e) {
            throw new IllegalStateException("The file appears to be locked!", e);
        }
    }

    protected void cleanTmpFile(String path2Save, String fileName) {
        cleanTmpFile(path2Save + FileSystems.getDefault().getSeparator() + fileName);
    }
}
