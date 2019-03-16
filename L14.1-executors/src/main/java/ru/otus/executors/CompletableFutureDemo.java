package ru.otus.executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class CompletableFutureDemo {
    private static Logger logger = LoggerFactory.getLogger(CompletableFutureDemo.class);

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        new CompletableFutureDemo().go();
    }

    private void go() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> task(1));
        logger.info("result:{}", future1.get());

        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> task(2));
        future2.thenAccept(val -> logger.info("result:{}", val));

        CompletableFuture<String> future3 = CompletableFuture.supplyAsync(this::errorAction);
        future3.exceptionally(Throwable::getMessage).thenAccept(msg -> logger.info("msg:{}", msg));
    }

    private String errorAction() {
        throw new RuntimeException("error");
    }


    private String task(int id) {
        sleep();
        logger.info("task is done: {}", id);
        return "done" + id;
    }

    private static void sleep() {
        try {
            Thread.sleep(TimeUnit.SECONDS.toMillis(3));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
