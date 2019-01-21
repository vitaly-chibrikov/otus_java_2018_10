package ru.otus.objectPool;

/**
 * @author sergey
 * created on 19.09.18.
 */
public class Demo {
    public static void main(String[] args) {
        ConnectionPool pool = new ConnectionPool(5, new ConnectionFactory());
        pool.get().select();
        pool.get().select();
        pool.get().select();
        pool.get().select();
        pool.get().select();
        pool.get().select();
    }
}
