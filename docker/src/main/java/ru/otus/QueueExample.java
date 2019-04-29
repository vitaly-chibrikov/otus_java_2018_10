package ru.otus;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class QueueExample {
    private static Logger logger = LoggerFactory.getLogger(QueueExample.class);

    private BlockingQueue<String> toAgent1 = new ArrayBlockingQueue<>(100);
    private BlockingQueue<String> toAgent2 = new ArrayBlockingQueue<>(100);

    public static void main(String[] args) throws InterruptedException {
        new QueueExample().start();
    }

    private void start() throws InterruptedException {
        agent1();
        agent2();
    }

    private void agent1() {
        new Thread(() -> {
            while(true) {
                try {
                    String request = toAgent1.take();
                    logger.info("got request:{}", request);
                    toAgent2.put(request.toUpperCase() + "_OK");
                } catch (InterruptedException e) {
                    logger.error(e.getMessage(), e);
                }
            }
        }).start();
    }

    private void agent2() throws InterruptedException {
        for (int idx = 1; idx < 50; idx++) {
            toAgent1.put("request:" + idx);
            sleep();
            String replay = toAgent2.take();
            logger.info("replay:{}", replay);
        }
    }

    private void sleep() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
