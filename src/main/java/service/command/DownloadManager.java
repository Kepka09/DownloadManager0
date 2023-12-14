package service.command;

public class DownloadManager {
    private Command startCommand;
    private Command pauseCommand;

    public DownloadManager(Command startCommand, Command pauseCommand) {
        this.startCommand = startCommand;
        this.pauseCommand = pauseCommand;
    }

    public void startDownload() {
        startCommand.execute();
    }

    public void pauseDownload() {
        pauseCommand.execute();
    }

    // Методы для возобновления и отмены
}
