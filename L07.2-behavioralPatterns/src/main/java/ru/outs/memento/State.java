package ru.outs.memento;

import java.util.Arrays;

/**
 * @author sergey
 * created on 11.09.18.
 */
public class State {
    private final String[] array;

    public State(String[] array) {
        this.array = array;
    }

    public State(State state) {
        this.array = Arrays.copyOf(state.getArray(), state.getArray().length);
    }

    public String[] getArray() {
        return array;
    }

    @Override
    public String toString() {
        return "State{" +
                "array=" + (array == null ? null : Arrays.asList(array)) +
                '}';
    }
}
