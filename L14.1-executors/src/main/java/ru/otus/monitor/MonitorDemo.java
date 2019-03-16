package ru.otus.monitor;

public class MonitorDemo {
    private int count = 0;
    private final static int limit = 100_000_000;
    private final Object monitor1 = new Object();
    private final Object monitor2 = new Object();
    private final Object monitor3 = new Object();

    public static void main(String[] args) throws InterruptedException {
        MonitorDemo counter = new MonitorDemo();
        counter.go();
    }

    //ошибочное импользование мониторов - у каждого потока свой монитор.
    private void inc1() {
        synchronized (monitor1) {
            for (int i = 0; i < limit; i++) {
                count++;
            }
        }
    }

    private void inc2() {
        synchronized (monitor2) {
            for (int i = 0; i < limit; i++) {
                count++;
            }
        }
    }

    private void inc3() {
        synchronized (monitor3) {
            for (int i = 0; i < limit; i++) {
                count++;
            }
        }
    }

    private void go() throws InterruptedException {
        Thread thread1 = new Thread(this::inc1);
        Thread thread2 = new Thread(this::inc2);
        Thread thread3 = new Thread(this::inc3);

        thread1.start();
        thread2.start();
        thread3.start();

        thread1.join();
        thread2.join();
        thread3.join();
        System.out.println("CounterBroken:" + count);
    }
}
