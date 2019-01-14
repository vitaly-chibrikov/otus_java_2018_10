package ru.outs.state;

/**
 * @author sergey
 * created on 12.09.18.
 */
public class BulbContext implements State {

    private State state = new OffState();

    @Override
    public void performAction() {
        state.performAction();
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}
