package model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class DownloadTask {


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

}
