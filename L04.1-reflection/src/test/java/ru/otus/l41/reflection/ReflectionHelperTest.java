package ru.otus.l41.reflection;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.otus.l41.reflection.ReflectionHelper;
import ru.otus.l41.reflection.TestClass;

/**
 * Created by tully.
 */
public class ReflectionHelperTest {

    @Before
    public void beforeTheTest(){

    }

    @SuppressWarnings("ConstantConditions")
    @Test
    public void instantiate() {
        TestClass testClass = ReflectionHelper.instantiate(TestClass.class);
        Assert.assertEquals(TestClass.DEFAULT_A, testClass.getA());
        Assert.assertEquals(TestClass.DEFAULT_S, testClass.getS());

        Assert.assertEquals(1, ReflectionHelper.instantiate(TestClass.class, 1).getA());
        Assert.assertEquals("A", ReflectionHelper.instantiate(TestClass.class, 1, "A").getS());
    }

    @Test
    public void getFieldValue() {
        Assert.assertEquals("A", ReflectionHelper.getFieldValue(new TestClass(1, "A"), "s"));
        Assert.assertEquals(1, ReflectionHelper.getFieldValue(new TestClass(1, "B"), "a"));
    }

    @Test
    public void setFieldValue() {
        TestClass test = new TestClass(1, "A");
        Assert.assertEquals("A", test.getS());
        ReflectionHelper.setFieldValue(test, "s", "B");
        Assert.assertEquals("B", test.getS());
    }

    @Test
    public void callMethod() {
        Assert.assertEquals("A", ReflectionHelper.callMethod(new TestClass(1, "A"), "getS"));

        TestClass test = new TestClass(1, "A");
        ReflectionHelper.callMethod(test, "setDefault");
        Assert.assertEquals("", test.getS());
    }

}
