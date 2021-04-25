package com.example.timelapse.system.util.thread;

public abstract class AsyncCallVoid extends AsyncCallExecutor<Void> {
    @Override
    public final Void execute() {
        try {
            pool.execute(this::run);
            postExecute(null);
        } catch (Exception e) {
            System.out.println("Ошибка выполнения асинхронного запроса!");
            System.out.println(e.getMessage());
        }
        return null;
    }
}
