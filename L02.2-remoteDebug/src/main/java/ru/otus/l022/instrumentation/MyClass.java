package ru.otus.l022.instrumentation;


/**
 * @author sergey
 * created on 17.07.18.
 */

/*
    cd L022-remoteDebug/target/
    java -javaagent:L022-agentDemo.jar -jar L022-agentDemo.jar
*/

public class MyClass {

    private int value = 10;

    public int getValue() {
        return value;
    }

    public static void main(String[] args) throws Exception {
        System.out.println("main");
        MyClass demo = new MyClass();
        System.out.println(demo.getValue());
        modifyPrivateValue(demo);
        System.out.println(demo.getValue());
    }

    private static void modifyPrivateValue(MyClass demo) throws Exception {
        demo.getClass().getMethod("setValue", int.class).invoke(demo, -4);
    }
}
