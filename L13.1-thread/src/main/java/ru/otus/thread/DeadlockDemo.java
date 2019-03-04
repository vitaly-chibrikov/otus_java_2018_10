package ru.otus.thread;

import java.util.concurrent.TimeUnit;

public class DeadlockDemo {

    private final Resource r1 = new Resource("R1");
    private final Resource r2 = new Resource("R2");

    public static void main(String[] args) {
        new DeadlockDemo().demo();
    }

    private void demo() {
        Thread t1 = new Thread(() -> action(r1, r2));
        t1.setName("t1");

        Thread t2 = new Thread(() -> action(r2, r1));
        t2.setName("t2");

        t1.start();
        t2.start();
    }


    private static void action(Resource has, Resource need) {
        System.out.println(Thread.currentThread().getName() + " has:" + has);
        synchronized (has) {
            sleep();
            System.out.println(Thread.currentThread().getName() + " taking:" + need);
            synchronized (need) {
                System.out.println("taken by " + Thread.currentThread().getName());
            }
        }
    }


    private static void sleep() {
        try {
            Thread.sleep(TimeUnit.SECONDS.toMillis(10));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    class Resource {
        private final String name;

        Resource(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Resource{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }
}
