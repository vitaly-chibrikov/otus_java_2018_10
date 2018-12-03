package ru.otus.l42;


import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static java.time.Duration.ofSeconds;
import static org.junit.jupiter.api.Assertions.*;


/**
 * @author sergey
 * created on 02.12.18.
 */

@DisplayName("DEMO test Class C")
class CalculatorTest {

    @Test
    @DisplayName("test method add")
    void add() {
        int x = 2;
        int y = 3;
        int result = new Calculator().add(x, y);
        assertEquals(x + y, result,"add result");
    }


    @Test
    void divByZero() {
        int x = 2;
        int y = 0;

        assertThrows(ArithmeticException.class, () -> {
            new Calculator().div(x, y);
        });
    }


    @Disabled("Test is ignored as a demonstration")
    @Test
    void testVoid() {

    }


    @Test
    @Disabled
    void testLongCalculation() throws InterruptedException {
        assertTimeout(ofSeconds(5), () -> {
            new Calculator().longCalculation();
        });
    }


    @BeforeAll
    static void beforeClass() {
        System.out.println("before class");
    }

    @BeforeEach
    void init() {
        System.out.println("before");
    }

    @AfterEach
    void tearDown() {
    }

    @AfterAll
    static void afterClass(){
        System.out.println("after class");
    }


    @ParameterizedTest
    @MethodSource("dividerDataProvider")
    void test(double param1, double param2, double result) {
        assertEquals(result, new Calculator().div(param1, param2), 0.1, "параметрический тест:");
    }

    private static Object[][] dividerDataProvider() {
        return new Object[][] {
                { 4, 2, 2.0},
                { 100, 10, 10.0},
                { 40, 5, 8.0},
                { 3, 1, 3.0},
                { 50, 5, 10.0}
        };
    }



}