package service.template.impl;


import service.template.HttpDownloaderTemplate;

public class TransactionalHttpDownloader extends HttpDownloaderTemplate {

    //Загрузку нельзя остановить - только отменить

    @Override
    protected void prepareDownload() {
        // Подготовка к загрузке с транзакцией
    }

    @Override
    protected void executeDownload(String url) {
        // Логика загрузки с трназакцией
    }

    @Override
    protected void finalizeDownload() {
        // Завершение загрузки с трназакцией
    }
}
