package service.composite;

public abstract class DownloadQueueComponent {

    DownloadStatus status;
    public abstract void start();

    public abstract void pause();

    public abstract void cancel();

    public abstract DownloadStatus getStatus();
}
