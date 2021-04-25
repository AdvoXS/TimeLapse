package com.example.timelapse.system.util.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public abstract class AsyncCallExecutor<T> implements AsyncCall<T> {
    protected final ExecutorService pool;

    protected AsyncCallExecutor() {
        pool = Executors.newSingleThreadExecutor();
    }

    protected AsyncCallExecutor(int countThreads) {
        pool = Executors.newFixedThreadPool(countThreads);
    }

    protected abstract T run();

    protected void postExecute(T object) {
    }
}
