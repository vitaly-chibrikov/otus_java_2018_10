package ru.otus.l41.reflection;

import ru.otus.l41.annotations.Email;

import java.lang.annotation.Native;

/**
 * @author sergey
 * created on 30.11.18.
 */
public class DemoClass {

    public int publicFieldForDemo;

    private String value = "initValue";

    public DemoClass(String value) {
        this.value = value;
    }


    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }


    private void privateMethod() {
        System.out.println("privateMethod executed");
    }

    @Override
    @SimpleAnnotation
    public String toString() {
        return "DemoClass{" +
                "publicFieldForDemo=" + publicFieldForDemo +
                ", value='" + value + '\'' +
                '}';
    }
}
