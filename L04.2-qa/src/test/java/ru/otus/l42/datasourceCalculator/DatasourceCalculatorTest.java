package ru.otus.l42.datasourceCalculator;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author sergey
 * created on 07.08.18.
 */
class DatasourceCalculatorTest {

    @Test
    void accumulator() {
        DataProvider dataProvider = mock(DataProvider.class);
        when(dataProvider.getDataInteger()).thenReturn(1);

        int result = new DatasourceCalculator(dataProvider).accumulator(5);

        assertEquals(5, result, "accumulator result:");
        verify(dataProvider, times(5)).getDataInteger();
    }


    @Test
    void complexCalc() {
        DataProvider dataProvider = new DataProviderImpl();
        DataProvider dataProviderSpy = spy(dataProvider);
        doReturn(1.0).when(dataProviderSpy).getDataDouble(anyInt());
        double result = new DatasourceCalculator(dataProviderSpy).complexCalc(5);

        assertEquals(5, result, 0.1, "complex calc result:");

        verify(dataProviderSpy, times(1)).getDataDouble(1);
        verify(dataProviderSpy, times(1)).getDataDouble(2);
    }
}