package com.example.timelapse.system.util.thread;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public abstract class AsyncCallObject<T> extends AsyncCallExecutor<T> {
    @Override
    public T execute(){
        try {
            Future<T> result = pool.submit(this::run);
            if (result != null) {
                if (result.isDone()) {
                    T obj = result.get();
                    postExecute(obj);
                    return obj;
                } else if (result.isCancelled())
                    System.out.println("Операция отменена");
            }
        }
        catch (ExecutionException | InterruptedException e){
            System.out.println("Ошибка выполнения асинхронного запроса!");
            System.out.println(e.getMessage());
        }
        return null;
    }
}
