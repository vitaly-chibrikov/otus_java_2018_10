package ru.otus.l42.datasourceCalculator;

/**
 * @author sergey
 * created on 06.08.18.
 */
public interface DataProvider {
    int getDataInteger();
    double getDataDouble(int seed);
}
