package service.iterator;


import model.DownloadTask;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class InProgressDownloadIterator implements Iterator<DownloadTask> {
    private final List<DownloadTask> tasks;
    private int currentIndex = 0;

    public InProgressDownloadIterator(List<DownloadTask> tasks) {
        this.tasks = tasks.stream()
                          .filter(DownloadTask::isInProgress)
                          .collect(Collectors.toList());
    }

    @Override
    public boolean hasNext() {
        return currentIndex < tasks.size();
    }

    @Override
    public DownloadTask next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return tasks.get(currentIndex++);
    }
}
