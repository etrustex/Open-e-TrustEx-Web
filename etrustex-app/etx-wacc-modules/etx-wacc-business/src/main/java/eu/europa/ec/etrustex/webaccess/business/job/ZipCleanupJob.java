package eu.europa.ec.etrustex.webaccess.business.job;

import eu.europa.ec.etrustex.webaccess.business.FileRepositoryUtil;
import eu.europa.ec.etrustex.webaccess.business.api.EtxJob;
import eu.europa.ec.etrustex.webaccess.business.config.ConfigurationService;
import eu.europa.ec.etrustex.webaccess.business.workspace.WorkspaceService;
import org.apache.commons.lang.time.StopWatch;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.util.Calendar;

public class ZipCleanupJob implements EtxJob {
    private static final Logger logger = Logger.getLogger(ZipCleanupJob.class);

    @Autowired
    private ConfigurationService configurationService;
    @Autowired
    private WorkspaceService workspaceService;

    @Override
    public void execute() {
        final int ttlInDays = configurationService.getConfiguration().getWorkspaceWSTimeToLive();

        Path cleanupPath = workspaceService.getWebserviceFolder();
        if (ttlInDays > 0 && Files.exists(cleanupPath)) {
            String availableSpace = FileRepositoryUtil.getHumanReadableByteCount(FileRepositoryUtil.getAvailableSpace(cleanupPath), true);
            logger.info("Repository cleanup started [TTL: " + ttlInDays + " day(s); path: " + cleanupPath.toString() + ", available disk space: " + availableSpace + "]");

            StopWatch stopWatch = new StopWatch();
            stopWatch.start();

            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DATE, -ttlInDays);
            final FileTime dayBefore = FileTime.fromMillis(calendar.getTimeInMillis());

            try {
                Files.walkFileTree(cleanupPath, new SimpleFileVisitor<Path>() {
                    @Override
                    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                        if (attrs.isRegularFile() && attrs.lastModifiedTime().compareTo(dayBefore) <= 0) {
                            logger.info("Deleting old file: " + file.toString());
                            try {
                                Files.deleteIfExists(file);
                            } catch (IOException ex) {
                                logger.error("Failed to delete old file: " + file.toString(), ex);
                            }
                        }
                        return FileVisitResult.CONTINUE;
                    }
                });
            } catch (IOException ex) {
                logger.error("Zip cleanup job failed!", ex);
            }

            stopWatch.stop();
            availableSpace = FileRepositoryUtil.getHumanReadableByteCount(FileRepositoryUtil.getAvailableSpace(cleanupPath), true);
            logger.info("Zip cleanup job done. Execution time: " + stopWatch.toString() + ", available disk space: " + availableSpace + "]");
        }
    }
}
