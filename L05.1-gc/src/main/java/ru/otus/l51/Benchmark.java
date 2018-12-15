package ru.otus.l51;


/**
 * Created by tully.
 */
class Benchmark implements BenchmarkMBean {
    private volatile int size = 0;
    private final int loopCounter;

    public Benchmark(int loopCounter) {
        this.loopCounter = loopCounter;
    }

    void run() throws InterruptedException {
        for(int idx = 0; idx < loopCounter; idx ++) {
            int local = size;
            Object[] array = new Object[local];
            for (int i = 0; i < local; i++) {
                array[i] = new String(new char[0]);
                Thread.sleep(100);
            }
            Thread.sleep(1_000);
        }
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void setSize(int size) {
        System.out.println("new size:" + size);
        this.size = size;
    }

}
