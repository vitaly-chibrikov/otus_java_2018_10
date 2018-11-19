package ru.otus.l022.jol;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.vm.VM;

/**
 * @author sergey
 * created on 15.07.18.
 */

//VM option: -Djdk.attach.allowAttachSelf
public class JolExample {

    public static void main(String[] args)  {

        System.out.println("boolean:" + VM.current().sizeOfField("boolean"));

        System.out.println(ClassLayout.parseClass(TestB.class).toPrintable());

        System.out.println(ClassLayout.parseClass(TestB2.class).toPrintable());
        System.out.println(ClassLayout.parseClass(TestB4.class).toPrintable());
        System.out.println(ClassLayout.parseClass(TestInt.class).toPrintable());

        System.out.println(ClassLayout.parseClass(TestInt2.class).toPrintable());
        System.out.println(ClassLayout.parseClass(TestInt4.class).toPrintable());

        System.out.println(ClassLayout.parseClass(Mix.class).toPrintable());

    }

    public class TestB {
        boolean valBool;
    }

    public class TestB2 {
        boolean valBool1;
        boolean valBool2;
    }

    public class TestB4 {
        boolean valInt1;
        boolean valInt2;
        boolean valInt3;
        boolean valInt4;
    }

    public class TestInt {
        int valInt1;
    }

    public class TestInt2 {
        int valInt1;
        int valInt2;
    }

    public class TestInt4 {
        int valInt1;
        int valInt2;
        int valInt3;
        int valInt4;
    }

    public class Mix {
        boolean boolVal;
        int intVal;
        boolean isBoolVal;
    }
}
