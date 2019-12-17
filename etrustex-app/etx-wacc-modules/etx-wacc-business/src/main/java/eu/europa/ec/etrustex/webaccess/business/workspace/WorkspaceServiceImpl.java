package eu.europa.ec.etrustex.webaccess.business.workspace;

import eu.europa.ec.etrustex.webaccess.business.FileRepositoryUtil;
import eu.europa.ec.etrustex.webaccess.business.administration.CacheService;
import eu.europa.ec.etrustex.webaccess.business.config.ConfigurationService;
import eu.europa.ec.etrustex.webaccess.business.facade.administration.CacheResetListener;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class WorkspaceServiceImpl implements WorkspaceService, CacheResetListener {

    private Logger logger = Logger.getLogger(WorkspaceServiceImpl.class);

    @Autowired
    private ConfigurationService configurationService;

    @Autowired
    private CacheService cacheService;

    private Path rootFolder;
    private Path webserviceFolder;

    @PostConstruct
    private void init() {
        loadConfiguration();
        cacheService.registerCacheResetListener(this);
    }

    @Override
    public void onCacheReset() {
        loadConfiguration();
    }

    protected void loadConfiguration() {
        logger.info("Loading Workspace configuration");
        rootFolder = prepareRootFolder();
        webserviceFolder = prepareWebserviceFolder();
        logFolderInfo("Workspace", rootFolder);
        logFolderInfo("Webservice temporary storage", webserviceFolder);
    }

    private void logFolderInfo(String folderName, Path folderPath) {
        boolean isSymbolicLink = Files.isSymbolicLink(folderPath);
        String symbolicLinkTarget = isSymbolicLink ? FileRepositoryUtil.getSymbolicLinkTarget(folderPath) : null;

        logger.info(folderName + " folder initialized on path: " + folderPath.toAbsolutePath().toString() +
                "; Folder exists: " + Files.exists(folderPath) +
                "; Is symbolic link: " + (isSymbolicLink ? "true - target: " + symbolicLinkTarget : "false") +
                "; " + FileRepositoryUtil.getHumanReadableStorageInfo(folderPath));
    }

    @Override
    public Path getRootFolder() {
        return rootFolder;
    }

    @Override
    public Path getWebserviceFolder() {
        return webserviceFolder;
    }

    private Path prepareWebserviceFolder() {
        Path path = rootFolder.resolve(WorkspaceService.WEBSERVICE_STORAGE_FOLDER);
        if (Files.notExists(path)) {
            try {
                path = Files.createDirectory(path);
            } catch (IOException e) {
                throw new IllegalStateException("Failed to create workspace storage folder", e);
            }
            logger.info("Webservice storage folder created after was missing");
        }

        return path;
    }

    private Path prepareRootFolder() {
        final String workspaceRootPath = configurationService.getConfiguration().getWorkspaceRootPath();
        if (workspaceRootPath == null) {
            throw new IllegalStateException("Workspace rootFolder path is not configured");
        }

        Path path = Paths.get(workspaceRootPath);
        if (Files.notExists(path)) {
            try {
                path = Files.createDirectories(path);
            } catch (IOException e) {
                logger.warn("Failed to create rootFolder folder: " + path.toAbsolutePath().toString(), e);
                throw new IllegalStateException("Failed to create rootFolder workspace", e);
            }
            logger.info("Workspace rootFolder folder created after was missing");
        }
        return path;
    }
}
