package ru.otus.queues;

import java.util.concurrent.PriorityBlockingQueue;

/*
 * Implement the compareTo() method in order. Print orders in ReadOrderThread so that order with priority==true is the first.
 */

public class PriorityQueueExample {
    private final StringBuffer buf = new StringBuffer();
    private final PriorityBlockingQueue<Order> orderQueue = new PriorityBlockingQueue<>();
    private boolean priorityAhead = false;

    public static void main(String[] args) throws Exception {
        new PriorityQueueExample().testName();
    }

    void log(String s) {
        buf.append(s + "\n");
    }

    public void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void testName() throws Exception {
        Thread addOrderThread = new Thread(new AddOrderThread());
        Thread readOrderThread = new Thread(new ReadOrderThread());
        addOrderThread.start();
        addOrderThread.join();

        readOrderThread.start();

        readOrderThread.join();

        System.out.println(priorityAhead);
    }

    class Order implements Comparable<Order> {
        public String title;
        public boolean priority;

        public Order(String title, boolean priority) {
            this.title = title;
            this.priority = priority;
        }

        @Override
        public String toString() {
            return "Order " + title + ", priority=" + priority;
        }

        @Override
        public int compareTo(Order o) {
            return 0;
        }

//        @Override
//        public int compareTo(Order o) {
//            if (this.priority && !o.priority) {
//                return -1;
//            } else if (!this.priority && o.priority) {
//                return 1;
//            }
//            return 0;
//        }

    }

    class AddOrderThread implements Runnable {
        @Override
        public void run() {
            orderQueue.put(new Order("books", false));
            sleep(10);
            orderQueue.put(new Order("table", false));
            sleep(10);
            orderQueue.put(new Order("computer", true));
            sleep(10);
            orderQueue.put(new Order("dog", false));
        }
    }

    class ReadOrderThread implements Runnable {
        int orderNum = 0;

        @Override
        public void run() {
            while (orderNum < 4) {
                try {
                    Order order = orderQueue.take();
                    // check that first taken order has priority==true
                    if (order.priority && orderNum == 0) {
                        priorityAhead = true;
                    }
                    log(order.toString());
                    orderNum++;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }


}
