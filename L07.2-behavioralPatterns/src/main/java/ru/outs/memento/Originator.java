package ru.outs.memento;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 * @author sergey
 * created on 11.09.18.
 */
public class Originator {
    private final Stack<Memento> stack = new Stack<>();

    public void saveState(State state) {
        stack.push(new Memento(state));
    }

    public State restoreState() {
        return stack.pop().getState();
    }
}
