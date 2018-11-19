package ru.otus.l022.MemoryHW;

/**
 * @author sergey
 * created on 18.11.18.
 */
public class Demo {
    public static void main(String[] args) {

        System.out.println("int:" + Measurer.measureInt());
        System.out.println("long:" + Measurer.measureLong());

        System.out.println("String char:" + Measurer.measure(() -> new String(new char[0])));
        System.out.println("String byte:" + Measurer.measure(() -> new String(new byte[0])));
    }
}
