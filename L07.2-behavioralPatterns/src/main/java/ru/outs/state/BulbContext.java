package ru.outs.state;

/**
 * @author sergey
 * created on 12.09.18.
 */
public class BulbContext  {

    private State state = new OffState();

    public void performAction() {
        state.action();
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}
