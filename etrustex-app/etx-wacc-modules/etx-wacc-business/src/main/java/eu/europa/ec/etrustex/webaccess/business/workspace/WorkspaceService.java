package eu.europa.ec.etrustex.webaccess.business.workspace;

import java.nio.file.Path;

public interface WorkspaceService {

    String WEBSERVICE_STORAGE_FOLDER = "ETX_WEB_WS";

    /**
     * Root workspace folder
     * @return Path
     */
    Path getRootFolder();

    /**
     * Folder where temporary files used by web services are stored,
     * e.g. zipped files created by ZipTask.
     * @return Path
     */
    Path getWebserviceFolder();
}
