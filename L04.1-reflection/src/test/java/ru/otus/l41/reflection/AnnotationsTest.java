package ru.otus.l41.reflection;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AnnotationsTest {

    public AnnotationsTest() {
        System.out.println("Call of the constructor");
    }

    @Before
    public void before(){
        System.out.println("Before");
    }

    @Test
    public void testOne(){
        System.out.println("testOne");
    }

    @Test
    public void testTwo(){
        System.out.println("testTwo");
    }

    @After
    public void after(){
        System.out.println("After");
    }

}
