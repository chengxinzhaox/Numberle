package test;

import models.CalculationException;
import models.Calculator;
import org.junit.jupiter.api.Test;
import views.Messages;

import static org.junit.jupiter.api.Assertions.*;

public class CalculatorTest {

    @Test
    public void testValidateAndCompute() throws CalculationException {
        assertTrue(Calculator.validateAndCompute("2+1*2=4"));
        assertTrue(Calculator.validateAndCompute("4-1*3=1"));
        assertTrue(Calculator.validateAndCompute("5/1+2=7"));
        assertTrue(Calculator.validateAndCompute("6*1-2=4"));
    }

    @Test
    public void testValidateAndComputeThrowsExceptionWithSpecificMessage() {
        CalculationException thrown = assertThrows(CalculationException.class, () -> {
            Calculator.validateAndCompute("2+1*2=5");
        }, "Expected validateAndCompute() to throw CalculationException, but it didn't");

        assertEquals(Messages.NOT_EQUAL, thrown.getMessage(), "The exception message was not as expected");
    }
}