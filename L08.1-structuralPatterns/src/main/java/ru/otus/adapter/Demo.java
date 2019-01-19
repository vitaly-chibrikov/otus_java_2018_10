package ru.otus.adapter;

/**
 * @author sergey
 * created on 16.01.19.
 */
public class Demo {
    public static void main(String[] args) {
        RotaryHammer rotaryHammer = new RotaryHammer();
        Drill drill = new Drill();
        rotaryHammer.drill(new SDSadapter(drill));
    }
}
