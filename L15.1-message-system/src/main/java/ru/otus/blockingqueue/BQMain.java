package ru.otus.blockingqueue;

public class BQMain {
    public static void main(String[] args) throws InterruptedException {
        final BlockingQueue<String> queue = new BlockingQueue<>(50);

        final Thread readerThread = new Thread(() -> {
            try {
                for (int i = 0; i < 1000; i++) {
                    queue.getFirst();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        readerThread.setName("Reader");
        readerThread.setPriority(1);

        final Thread writerThread = new Thread(() -> {
            try {
                for (int i = 0; i < 1000; i++) {
                    queue.addLast(String.valueOf(i));
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        writerThread.setName("Writer");
        writerThread.setPriority(10);

        writerThread.start();
        readerThread.start();

        writerThread.join();
        readerThread.join();
    }
}
