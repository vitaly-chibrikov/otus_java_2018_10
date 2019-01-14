package ru.outs.memento;

/**
 * @author sergey
 * created on 11.09.18.
 */
public class Memento {
    private final State state;

    public Memento(State state) {
        this.state = new State(state);
    }

    public State getState() {
        return state;
    }
}
