package ru.outs.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author sergey
 * created on 11.09.18.
 */
public class EventProducer {
    public EventProducer() {

    }

    private final List<Listener> listeners = new ArrayList<>();

    public void addListener(Listener listener) {
        listeners.add(listener);
    }

    public void removeListener(Listener listener) {
        listeners.remove(listener);
    }

    public void event(String data) {
        listeners.forEach(listener -> listener.onUpdate(data));
    }
}
