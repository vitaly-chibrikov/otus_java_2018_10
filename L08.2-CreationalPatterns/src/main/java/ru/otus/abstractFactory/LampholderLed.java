package ru.otus.abstractFactory;

/**
 * @author sergey
 * created on 18.09.18.
 */
public class LampholderLed implements Lampholder {
    @Override
    public void hold() {
        System.out.println("Led hold");
    }
}
