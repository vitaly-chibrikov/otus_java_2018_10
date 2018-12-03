package ru.otus.l42;

import java.util.concurrent.TimeUnit;

/**
 * @author sergey
 * created on 06.08.18.
 */
public class Calculator {

    public int add(int x, int y) {
        return x + y;
    }

    public double div(double x, double y) {
        if (y == 0) {
            throw new ArithmeticException("Error divide a number by zero");
        }
        return x / y;
    }

    public void longCalculation() throws InterruptedException {
        Thread.sleep(TimeUnit.SECONDS.toMillis(10));
    }

    /*
     * Задание для TDD:
     * Реализовать функцию сложения двух положительных чисел.
     * Функция должна:
     * 1. Принимать в качестве аргументов только положительные числа.
     * 2. Возвращать сумму переданных чисел.
     */
    public int addPositiv(int x, int y) {
        return 0;
    }
}
