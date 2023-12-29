package com.example.downloadmanager0;
/**
 * Інтерфейс суб'єкта для моделі спостереження у системі завантаження.
 * Визначає основні методи для управління спостерігачами.
 */
public interface DownloadSubject {
    /**
     * Приєднує спостерігача до суб'єкта.
     *
     * @param observer спостерігач, який потрібно приєднати
     */
    void attach(DownloadObserver observer);
    /**
     * Від'єднує спостерігача від суб'єкта.
     *
     * @param observer спостерігач, який потрібно від'єднати
     */
    void detach(DownloadObserver observer);
    /**
     * Повідомляє всіх приєднаних спостерігачів про зміну стану.
     */
    void notifyObservers();
}
