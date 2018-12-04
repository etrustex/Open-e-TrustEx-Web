package eu.europa.ec.etrustex.webaccess.business;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileRepositoryUtil {
    private static final Logger logger = Logger.getLogger(FileRepositoryUtil.class);

    public static long getAvailableSpace(Path path) {
        long availableSpace = -1;
        try {
            availableSpace = Files.getFileStore(path).getUsableSpace();
        } catch (IOException e) {
            logger.warn("Failed to retrieve available disk space!", e);
        }
        return availableSpace;
    }

    public static long getTotalSpace(Path path) {
        long totalSpace = -1;
        try {
            totalSpace = Files.getFileStore(path).getTotalSpace();
        } catch (IOException e) {
            logger.warn("Failed to retrieve total disk space!", e);
        }
        return totalSpace;
    }

    public static String getSymbolicLinkTarget(Path path) {
        String linkTarget = null;
        if (Files.isSymbolicLink(path)) {
            try {
                linkTarget = Files.readSymbolicLink(path).toAbsolutePath().toString();
            } catch (IOException e) {
                logger.warn("Could not read symbolic link!", e);
            }
        }
        return linkTarget;
    }

    public static String getHumanReadableByteCount(long bytes, boolean useStandardizedUnits) {
        int unit = useStandardizedUnits ? 1000 : 1024;
        String formatted;
        if (bytes < unit) {
            formatted = bytes + " B";
        } else {
            int exp = (int) (Math.log(bytes) / Math.log(unit));
            String pre = (useStandardizedUnits ? "kMGTPE" : "KMGTPE").charAt(exp - 1) + (useStandardizedUnits ? "" : "i");
            formatted = String.format("%.1f %sB", bytes / Math.pow(unit, exp), pre);
        }

        return formatted;
    }

    public static String getHumanReadableStorageInfo(Path path) {
        final String availableSpace = getHumanReadableByteCount(getAvailableSpace(path), true);
        final String totalSpace = getHumanReadableByteCount(getTotalSpace(path), true);

        return availableSpace + "; " + totalSpace;
    }
}
