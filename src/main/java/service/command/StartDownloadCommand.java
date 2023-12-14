package service.command;

import model.DownloadTask;

public class StartDownloadCommand implements Command {
    private DownloadTask downloadTask;

    public StartDownloadCommand(DownloadTask downloadTask) {
        this.downloadTask = downloadTask;
    }

    @Override
    public void execute() {
        downloadTask.start();
    }
}
