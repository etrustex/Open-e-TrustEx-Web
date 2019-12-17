package eu.europa.ec.etrustex.webaccess.business.workspace;

import eu.europa.ec.etrustex.test.support.AbstractTest;
import eu.europa.ec.etrustex.webaccess.business.config.Configuration;
import eu.europa.ec.etrustex.webaccess.business.config.ConfigurationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class WorkspaceServiceImplTest extends AbstractTest {

    @Mock
    private ConfigurationService configurationService;

    @InjectMocks
    private WorkspaceServiceImpl workspaceService = new WorkspaceServiceImpl();

    private Path tempRoot;

    @Override
    protected void onSetUp() {
        try {
            tempRoot = Files.createTempDirectory(this.getClass().toString());
        } catch (IOException e) {
            fail(e.getMessage());
        }
    }

    @Override
    protected void onTearDown() {
        if (Files.exists(tempRoot)) {
            try {
                Files.walkFileTree(tempRoot, new SimpleFileVisitor<Path>() {
                    @Override
                    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                        Files.delete(file);
                        return FileVisitResult.CONTINUE;
                    }

                    @Override
                    public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                        Files.delete(dir);
                        return FileVisitResult.CONTINUE;
                    }
                });
            } catch (IOException e) {
                fail(e.getMessage());
            }
        }
    }

    @Test(expected = IllegalStateException.class)
    public void shouldThrowExceptionWhenRootFolderNotConfigured() {
        // given
        Configuration configuration = prepareConfiguration(null);
        when(configurationService.getConfiguration()).thenReturn(configuration);

        // when
        workspaceService.loadConfiguration();
    }

    @Test
    public void shouldCreateAllFolders() {
        // given
        Configuration configuration = prepareConfiguration(tempRoot.toString());
        when(configurationService.getConfiguration()).thenReturn(configuration);

        // when
        workspaceService.loadConfiguration();

        // then
        assertThat(Files.exists(workspaceService.getRootFolder()), is(true));
        assertThat(Files.exists(workspaceService.getWebserviceFolder()), is(true));
    }

    private Configuration prepareConfiguration(String rootPath) {
        Configuration configuration = new Configuration();
        configuration.setWorkspaceRootPath(rootPath);

        return configuration;
    }

}