package ru.otus.l012;

/*
javac ByteCode.java
javap -v ByteCode.class
javap -private ByteCode.class
*/

public class ByteCode {
    private static int increment = 1;
    public void inc(int a) {
        //Получится ли одинаковый byteCode?
        a = a + increment;
        a = a++;
        a = a + 1;
    }

    public void bools() {
        boolean f = true;
        boolean x = f;
    }
}