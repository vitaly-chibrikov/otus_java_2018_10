package ru.otus.l091.io;

import java.io.Serializable;

/**
 * @author sergey
 * created on 27.01.19.
 */
//implements Serializable - обязательное условие для сериализации
public class Person implements Serializable {
    private final int age;
    private final String name;
    private transient final String hidden = "hiddenField"; //transient - поле будет пропущено при сериализации

    // Обратите внимание на то, сколько раз вызывается констурктор и сколько объектов создается
    Person(int age, String name) {
        System.out.println("new Person");
        this.age = age;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Name: " + name + " age: " + age + " hidden:" + hidden;
    }
}