package ru.otus.l091.io;

import java.io.Serializable;

/**
 * @author sergey
 * created on 27.01.19.
 */
public class Person implements Serializable {
    private final int age;
    private final String name;

    Person(int age, String name) {
        this.age = age;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Name: " + this.name + " age: " + this.age;
    }
}