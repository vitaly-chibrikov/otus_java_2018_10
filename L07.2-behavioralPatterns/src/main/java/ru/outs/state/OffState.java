package ru.outs.state;

/**
 * @author sergey
 * created on 12.09.18.
 */
public class OffState implements State {
    @Override
    public void performAction() {
        System.out.println("темно");
    }
}
