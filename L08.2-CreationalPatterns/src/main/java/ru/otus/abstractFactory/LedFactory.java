package ru.otus.abstractFactory;

/**
 * @author sergey
 * created on 17.09.18.
 */
public class LedFactory implements AbstractFactory {
    @Override
    public Bulb createBulb() {
        return new BulbLed();
    }

    @Override
    public Lampholder createLampholder() {
        return new LampholderLed();
    }
}
