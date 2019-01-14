package ru.outs.chain;

import java.util.ArrayList;
import java.util.List;

/**
 * @author sergey
 * created on 11.09.18.
 */
public class Application {
    private List<String> history = new ArrayList<>();

    public void addHistoryRecord(String record) {
        history.add(record);
    }

    public void printHistory() {
        System.out.println(history);
    }
}
