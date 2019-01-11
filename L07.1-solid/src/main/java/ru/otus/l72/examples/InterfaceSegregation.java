package ru.otus.l72.examples;


/**
 * @author sergey
 * created on 09.09.19.
 */
public class InterfaceSegregation {

    /*
    Чтобы ехать на велосипеде надо:
        - крутить педали
        - держать равновесие
        - звонить в звоночек
     */
    interface rideBicycle {
        void pedal();
        void keepBalance();
        void ringTheBell();
    }

    //У крутого трюкового велика нет звоночка
    class StuntBicycle implements rideBicycle {

        @Override
        public void pedal() {
            //что-то делается
        }

        @Override
        public void keepBalance() {
            //что-то делается
        }

        @Override
        public void ringTheBell() {
            //ну нет звоночка...
            throw new RuntimeException("Not implemented");
        }
    }

    //Правильный вариант:

    //Выделяем базовый функционал
    interface rideBicycleOk {
        void pedal();
        void keepBalance();
    }

    //Доп. функции выносит в отдельный интерфейс
    interface ringingBicycle {
        void ringTheBell();
    }
}
