package model.observer;

public interface DownloadSubject {
    void attach(DownloadObserver observer);
    void detach(DownloadObserver observer);
    void notifyObservers();
}
