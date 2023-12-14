package service.command;

import model.DownloadTask;

public class PauseDownloadCommand implements Command {
    private DownloadTask downloadTask;

    public PauseDownloadCommand(DownloadTask downloadTask) {
        this.downloadTask = downloadTask;
    }

    @Override
    public void execute() {
        downloadTask.pause();
    }
}
