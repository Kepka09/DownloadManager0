package com.example.downloadmanager0;


import lombok.AllArgsConstructor;
import service.composite.DownloadQueueComponent;

import java.util.List;

@AllArgsConstructor
public class DownloadTask extends DownloadQueueComponent implements DownloadSubject {

    private List<DownloadObserver> observers;
    private DownloadStatus status;
    private String fileName;
    private String url;
    private int downloadSpeed; // в килобайтах в секунду
    private boolean isInProgress;

    @Override
    public void start() {
        isInProgress = true;
        //Todo: тут надо будет сделать реальную загрузку файла по параметрам
    }

    @Override
    public void pause() {
        isInProgress = false;
        //todo: тут надо будет остановить загрузку файла
    }

    @Override
    public void cancel() {
        isInProgress = false;
        //todo: тут надо будет удалить загрузку
    }

    @Override
    public void attach(DownloadObserver observer) {
        observers.add(observer);
    }

    @Override
    public void detach(DownloadObserver observer) {
        observers.remove(observer);
    }

    public List<DownloadObserver> getObservers() {
        return observers;
    }

    public void setObservers(List<DownloadObserver> observers) {
        this.observers = observers;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getDownloadSpeed() {
        return downloadSpeed;
    }

    public void setDownloadSpeed(int downloadSpeed) {
        this.downloadSpeed = downloadSpeed;
    }

    public boolean isInProgress() {
        return isInProgress;
    }

    public void setInProgress(boolean inProgress) {
        isInProgress = inProgress;
    }

    @Override
    public void notifyObservers() {
        for (DownloadObserver observer : observers) {
            observer.update(this);
        }
    }

    private void setStatus(DownloadStatus status) {
        this.status = status;
        notifyObservers();
    }

    @Override
    public DownloadStatus getStatus() {
        return status;
    }
}
