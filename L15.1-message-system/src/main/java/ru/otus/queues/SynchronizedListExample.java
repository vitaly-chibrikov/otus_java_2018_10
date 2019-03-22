package ru.otus.queues;

import java.util.ArrayList;
import java.util.List;

/*
 *  Why does the program (periodically) fail with ArrayIndexOutOfBoundException?
 *  What should you do to prevent this happening?
 *  An exception is not always thrown, you might need to run the program multiple times.
 */

public class SynchronizedListExample {
    private final StringBuffer buf = new StringBuffer();
    private final String[] animals = {"Cow", "Goose", "Cat", "Dog", "Elephant", "Rabbit", "Snake", "Chicken", "Horse", "Human"};
    private final List<String> randomAnimals = new ArrayList<>();

    public static void main(String[] args) {
        new SynchronizedListExample().testThread();
    }

    private void testThread() {
        List<Thread> threads = new ArrayList<Thread>();
        for (int i = 0; i < 100; i++) {
            threads.add(new Thread(new TestThread("t" + i)));
        }
        System.out.println("Starting threads");
        for (int i = 0; i < 100; i++) {
            threads.get(i).start();
        }
        System.out.println("Waiting for threads");
        try {
            for (int i = 0; i < 100; i++) {
                threads.get(i).join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(buf);

    }

    private void err(String s) {
        buf.append("<span style='color:red'><b>" + s + "</b></span>\n");
    }

    private String getRandomAnimal() {
        int index = (int) (Math.random() * animals.length);
        return animals[index];
    }

    private class TestThread implements Runnable {
        String threadName;

        public TestThread(String threadName) {
            this.threadName = threadName;
        }

        @Override
        public void run() {
            try {
                for (int i = 0; i < 50000; i++) {
                    randomAnimals.add(getRandomAnimal());
                }
            } catch (Exception e) {
                err(e.getClass().getName());
            }
        }
    }


}
