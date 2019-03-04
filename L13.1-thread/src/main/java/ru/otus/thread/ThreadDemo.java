package ru.otus.thread;

public class ThreadDemo {
    public static void main(String[] args) {
    //    case1();
        case2();
    }

    private static void case1() {
        System.out.println(Thread.currentThread().getName() +". Main program started");

        Thread thread = new Thread(
                ()-> System.out.println("from thread:" + Thread.currentThread().getName()));
        thread.start();

        System.out.println(Thread.currentThread().getName() + ". Main program finished");
    }

    private static void case2() {
        System.out.println(Thread.currentThread().getName() +". Main program started");

        CustomThread thread = new CustomThread();
        thread.start();

        System.out.println(Thread.currentThread().getName() + ". Main program finished");
    }


    static class CustomThread extends Thread {
        @Override
        public void run() {
            System.out.println("from thread:" + Thread.currentThread().getName());
        }
    }
}
