package model.observer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class DownloadTask implements DownloadSubject {

    private List<DownloadObserver> observers;
    private DownloadStatus status;
    private String fileName;
    private String url;
    private int downloadSpeed; // в килобайтах в секунду
    private boolean isInProgress;

    public void start() {
        isInProgress = true;
        //Todo: тут надо будет сделать реальную загрузку файла по параметрам
    }

    public void pause() {
        isInProgress = false;
        //todo: тут надо будет остановить загрузку файла
    }

    public void delete() {
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

    @Override
    public void notifyObservers() {
        for (DownloadObserver observer : observers) {
            observer.update(this);
        }
    }

    public void setStatus(DownloadStatus status) {
        this.status = status;
        notifyObservers();
    }
}
