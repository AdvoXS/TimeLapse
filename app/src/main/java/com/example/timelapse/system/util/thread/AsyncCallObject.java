package com.example.timelapse.system.util.thread;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public abstract class AsyncCallObject<T> extends AsyncCallExecutor<T> {
    @Override
    public T execute(){
        try {
            Future<T> result = pool.submit(this::run);
            if (result != null) {
                T obj = result.get(1000, TimeUnit.MILLISECONDS);
                postExecute(obj);
                return obj;
            }
        } catch (TimeoutException e) {
            System.out.println("Вышло время ожидания данных от сервера");
        } catch (ExecutionException | InterruptedException e) {
            System.out.println("Ошибка выполнения асинхронного запроса!");
            System.out.println(e.getMessage());
        }
        return null;
    }
}
