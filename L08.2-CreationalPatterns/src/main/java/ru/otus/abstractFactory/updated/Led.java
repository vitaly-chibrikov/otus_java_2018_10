package ru.otus.abstractFactory.updated;

import ru.otus.abstractFactory.AbstractFactory;
import ru.otus.abstractFactory.LedFactory;

/**
 * @author sergey
 * created on 17.09.18.
 */
public class Led implements Strategy {
    @Override
    public AbstractFactory configuration() {
        return new LedFactory();
    }
}
