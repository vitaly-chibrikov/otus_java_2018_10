package ru.outs.observer;

/**
 * @author sergey
 * created on 11.09.18.
 */
public class ConsumerB implements Listener {
    @Override
    public void onUpdate(String data) {
        System.out.println("ConsumerB data:" + data);
    }
}
