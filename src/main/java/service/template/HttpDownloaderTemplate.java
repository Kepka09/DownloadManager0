package service.template;

public abstract class HttpDownloaderTemplate {
    // Шаблонный метод
    public final void download(String url) {
        prepareDownload();
        executeDownload(url);
        finalizeDownload();
    }

    protected abstract void prepareDownload(); // Подготовка к загрузке
    protected abstract void executeDownload(String url); // Выполнение загрузки
    protected abstract void finalizeDownload(); // Завершение загрузки
}
