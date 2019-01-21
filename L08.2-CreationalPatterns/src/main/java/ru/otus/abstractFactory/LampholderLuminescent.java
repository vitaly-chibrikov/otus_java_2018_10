package ru.otus.abstractFactory;

/**
 * @author sergey
 * created on 18.09.18.
 */
public class LampholderLuminescent implements Lampholder {
    @Override
    public void hold() {
        System.out.println("Luminescent light");
    }
}
