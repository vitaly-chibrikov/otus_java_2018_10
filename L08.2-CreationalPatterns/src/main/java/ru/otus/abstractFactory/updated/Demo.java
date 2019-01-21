package ru.otus.abstractFactory.updated;

import ru.otus.abstractFactory.AbstractFactory;
import ru.otus.abstractFactory.Bulb;
import ru.otus.abstractFactory.Lampholder;

/**
 * @author sergey
 * created on 17.09.18.
 */
public class Demo {

    public Demo(AbstractFactory abstractFactory) {
        Bulb bulb = abstractFactory.createBulb();
        Lampholder lampholder = abstractFactory.createLampholder();

        bulb.light();
        lampholder.hold();
    }

    public static AbstractFactory configuration(Strategy strategy) {
        return strategy.configuration();
    }

    public static void main(String[] args) {
        new Demo(configuration(new Led()));
        new Demo(configuration(new Luminescent()));
    }

}
