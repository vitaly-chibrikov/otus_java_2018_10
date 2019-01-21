package ru.otus.singleton;

/**
 * @author sergey
 * created on 19.09.18.
 */
public class Singleton {
    private Singleton() {
        System.out.println("lazy creation");
    }

    private static class SingletonHolder {
        public static final Singleton instance = new Singleton();
    }

    public static Singleton getInstance() {
        return SingletonHolder.instance;
    }
}
