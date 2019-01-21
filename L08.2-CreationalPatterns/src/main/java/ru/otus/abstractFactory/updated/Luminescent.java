package ru.otus.abstractFactory.updated;

import ru.otus.abstractFactory.AbstractFactory;
import ru.otus.abstractFactory.LuminescentFactory;

/**
 * @author sergey
 * created on 18.09.18.
 */
public class Luminescent implements Strategy {
    @Override
    public AbstractFactory configuration() {
        return new LuminescentFactory();
    }
}
