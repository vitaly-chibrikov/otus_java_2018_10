package ru.otus.objectPool;

/**
 * @author sergey
 * created on 19.09.18.
 */
public class ConnectionFactory {
    public Connection create() {
        return new ConnectionOracle();
    }
}
