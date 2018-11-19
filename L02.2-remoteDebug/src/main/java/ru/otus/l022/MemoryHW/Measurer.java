package ru.otus.l022.MemoryHW;


import java.util.function.Supplier;

/**
 * @author sergey
 * created on 18.11.18.
 */
class Measurer {
    private final static int ARRAY_SIZE = 20_000_000;

    static long measureInt()  {
        long mem1 = getMem();
        int[] array = new int[ARRAY_SIZE];

        for (int i = 0; i < array.length; i++) {
            array[i] = i;
        }

        long size = (getMem() - mem1) / array.length;
        System.out.println("int size: " + size + " len:" + array.length);
        return size;
    }


    static long measureLong()  {
        long mem1 = getMem();
        long[] array = new long[ARRAY_SIZE];
        System.out.println("after array:" + getMem());

        for (int i = 0; i < array.length; i++) {
            array[i] = i;
        }

        long size = (getMem() - mem1) / array.length;
        System.out.println("Long size: " + size + " len:" + array.length);
        return size;
    }


    static <T> long measure(Supplier<T> objectGetter)  {
        Object[] array = new Object[ARRAY_SIZE];
        long mem1 = getMem();

        for (int i = 0; i < array.length; i++) {
            array[i] = objectGetter.get();
        }

        long size = (getMem() - mem1) / array.length;
        System.out.println("Element size: " + size + " len:" + array.length);
        return size;
    }

    private static long getMem() {
        System.gc();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Runtime runtime = Runtime.getRuntime();
        return runtime.totalMemory() - runtime.freeMemory();
    }
}
