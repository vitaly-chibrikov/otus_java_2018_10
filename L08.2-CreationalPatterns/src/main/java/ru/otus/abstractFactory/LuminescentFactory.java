package ru.otus.abstractFactory;

/**
 * @author sergey
 * created on 17.09.18.
 */
public class LuminescentFactory implements AbstractFactory {
    @Override
    public Bulb createBulb() {
        return new BulbLuminescent();
    }

    @Override
    public Lampholder createLampholder() {
        return new LampholderLuminescent();
    }
}
