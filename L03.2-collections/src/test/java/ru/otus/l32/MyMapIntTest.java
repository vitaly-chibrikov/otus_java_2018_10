package ru.otus.l32;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author sergey
 * created on 24.07.18.
 */
public class MyMapIntTest {

    @Test
    public void putAndGet() {
        MyMapInt map = new MyMapInt(1);
        int value = 88;
        String key = "key";

        map.put(key, value);
        assertEquals("value", value, map.get(key));
    }

    @Test
    public void putAndGetSequence() {
        int size = 10;
        String keyStr = "k";
        MyMapInt map = new MyMapInt(size);

        for (int idx = 0; idx < size; idx++) {
            map.put(keyStr + idx, idx);
        }

        for (int idx = 0; idx < size; idx++) {
            assertEquals("value idx:" + idx, idx, map.get(keyStr + idx));
        }
    }

}