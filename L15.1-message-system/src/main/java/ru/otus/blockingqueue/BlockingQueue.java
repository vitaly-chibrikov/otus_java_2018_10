package ru.otus.blockingqueue;

import java.util.LinkedList;
import java.util.Queue;

public class BlockingQueue<T> {
    private final Queue<T> queue;

    private final int maxSize;
    private int currentSize = 0;

    public BlockingQueue(int maxSize) {
        this.maxSize = maxSize;
        queue = new LinkedList<>();
    }

    public synchronized void addLast(T element) throws InterruptedException {
        while (currentSize == maxSize) {
            System.out.println(Thread.currentThread().getName() + " waiting because queue is full");
            wait();
        }

        final boolean wasEmpty = currentSize == 0;
        queue.add(element);
        currentSize++;
        System.out.println(Thread.currentThread().getName() + " added element " + element + ". Queue size: " + currentSize);

        if (wasEmpty) {
            System.out.println(Thread.currentThread().getName() + " notifying that queue is not empty anymore");
            notifyAll();
        }
    }

    public synchronized T getFirst() throws InterruptedException {
        while (currentSize == 0) {
            System.out.println(Thread.currentThread().getName() + " waiting because queue is empty");
            wait();
        }

        final boolean wasFull = currentSize == maxSize;
        final T element = queue.remove();
        currentSize--;
        System.out.println(Thread.currentThread().getName() + " removed element " + element + ". Queue size: " + currentSize);

        if (wasFull) {
            System.out.println(Thread.currentThread().getName() + " notifying that queue is not full anymore");
            notifyAll();
        }

        return element;
    }
}
