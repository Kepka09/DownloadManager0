package service.template.impl;


import service.template.HttpDownloaderTemplate;

public class HttpDownloader extends HttpDownloaderTemplate {
    @Override
    protected void prepareDownload() {
        // Логика для подготовки к загрузке (например, проверка URL)
    }

    @Override
    protected void executeDownload(String url) {
        // Реальная логика для загрузки файла по HTTP
    }

    @Override
    protected void finalizeDownload() {
        // Завершающие действия (например, проверка целостности файла)
    }
}
