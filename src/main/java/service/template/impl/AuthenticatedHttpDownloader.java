package service.template.impl;


import service.template.HttpDownloaderTemplate;

public class AuthenticatedHttpDownloader extends HttpDownloaderTemplate {
    private String username;
    private String password;

    public AuthenticatedHttpDownloader(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    protected void prepareDownload() {
        // Подготовка к загрузке с аутентификацией
    }

    @Override
    protected void executeDownload(String url) {
        // Логика загрузки с аутентификацией
    }

    @Override
    protected void finalizeDownload() {
        // Завершение загрузки с аутентификацией
    }
}
