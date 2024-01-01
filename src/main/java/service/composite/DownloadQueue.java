package service.composite;


import lombok.Getter;
import lombok.Setter;

import java.util.Queue;

@Getter
@Setter
public class DownloadQueue extends DownloadQueueComponent {

    Queue<DownloadQueueComponent> downloadQueue;
    private DownloadQueueComponent currentTask;
    private DownloadStatus status;

    public void addDownloadComponent(DownloadQueueComponent downloadTask) {
        downloadQueue.add(downloadTask);
    }

    public void removeDownloadComponent(DownloadQueueComponent downloadTask) {
        if(downloadTask.status == DownloadStatus.STARTED){
            downloadTask.cancel();
        }
        downloadQueue.remove(downloadTask); //TODO if
    }


    @Override
    public void start() {

        //TODO here download queue by cascade - When First task is completed then second task will start
        //TODO here download queue by cascade - When Second task is completed then third task will start etc.

        if (currentTask == null || (
                currentTask.getStatus() == DownloadStatus.COMPLETED ||
                        currentTask.getStatus() == DownloadStatus.CANCELLED)) {
            currentTask = downloadQueue.poll(); // Retrieves and removes the head of this queue, or returns null if this queue is empty.
            if (currentTask != null) {
                currentTask.start();
            }
        }
    }

    @Override
    public void pause() {
        if (currentTask != null) {
            currentTask.pause();
            currentTask.status = DownloadStatus.PAUSED;
        }
    }

    @Override
    public void cancel() {
        if (currentTask != null) {
            currentTask.cancel();
            currentTask = null;
        }
        downloadQueue.clear();
    }

    @Override
    public DownloadStatus getStatus() {
        if (currentTask != null) {
            return currentTask.getStatus();
        }
        return DownloadStatus.COMPLETED;
    }
}
