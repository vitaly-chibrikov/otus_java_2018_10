package ru.outs.strategy;

/**
 * @author sergey
 * created on 11.09.18.
 */
public class Context {
    private Strategy strategy;

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public void applyStrategy() {
        strategy.transportation();
    }
}
