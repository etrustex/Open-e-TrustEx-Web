package eu.europa.ec.etrustex.webaccess.utils;

public interface TransferProgressNotifiable {
    void notifyProgress(long bytesProcessed);

    long getExpectedFileSize();

    void setExpectedFileSize(long expectedFileSize);

}
