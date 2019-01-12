package ru.otus.l72.examples;

/**
 * @author sergey
 * created on 09.09.19.
 */
public class DependencyInversion {

    /*Правильное применение принципа*/
    class Car {
        private SteeringWheel steeringWheel;
        private Engine engine;

        public Car(SteeringWheel steeringWheel, Engine engine) {
            this.steeringWheel = steeringWheel;
            this.engine = engine;
        }
    }

    interface SteeringWheel {

    }

    interface Engine {

    }
}
