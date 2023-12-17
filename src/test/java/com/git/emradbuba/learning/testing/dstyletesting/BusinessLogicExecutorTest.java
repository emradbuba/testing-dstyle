package com.git.emradbuba.learning.testing.dstyletesting;

import com.git.emradbuba.learning.testing.dstyletesting.legacywrapper.BusinessLogicExecutor;
import com.git.emradbuba.learning.testing.dstyletesting.legacywrapper.LegacyBusinessServiceWrapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class BusinessLogicExecutorTest {

    private LegacyBusinessServiceWrapper legacyBusinessServiceWrapper;
    private BusinessLogicExecutor businessLogicExecutor;


    @BeforeEach
    void setUp() {
        legacyBusinessServiceWrapper = mock(LegacyBusinessServiceWrapper.class);
        businessLogicExecutor = new BusinessLogicExecutor(legacyBusinessServiceWrapper);
    }

    @Test
    void shouldExecutorReturnTrueWhenBothLogicActionReturnTrue() {
        when(legacyBusinessServiceWrapper.runLongBusinessLogic1()).thenReturn(true);
        when(legacyBusinessServiceWrapper.runLongBusinessLogic2()).thenReturn(true);

        boolean result = businessLogicExecutor.performLongRunningBusinessLogics();

        assertThat(result).isTrue();
    }

    @Test
    void shouldExecutorReturnFalseWhenBothLogicActionReturnFalse() {
        when(legacyBusinessServiceWrapper.runLongBusinessLogic1()).thenReturn(false);
        when(legacyBusinessServiceWrapper.runLongBusinessLogic2()).thenReturn(false);

        boolean result = businessLogicExecutor.performLongRunningBusinessLogics();

        assertThat(result).isFalse();
    }

    @Test
    void shouldExecutorReturnFalseWhenFirstLogicActionReturnsTrueAndSecondFalse() {
        when(legacyBusinessServiceWrapper.runLongBusinessLogic1()).thenReturn(true);
        when(legacyBusinessServiceWrapper.runLongBusinessLogic2()).thenReturn(false);

        boolean result = businessLogicExecutor.performLongRunningBusinessLogics();

        assertThat(result).isFalse();
    }

    @Test
    void shouldExecutorReturnFalseWhenFirstLogicActionReturnsFalseAndSecondTrue() {
        when(legacyBusinessServiceWrapper.runLongBusinessLogic1()).thenReturn(false);
        when(legacyBusinessServiceWrapper.runLongBusinessLogic2()).thenReturn(true);

        boolean result = businessLogicExecutor.performLongRunningBusinessLogics();

        assertThat(result).isFalse();
    }
}