package Test;

import Models.InputValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class InputValidatorTest {

    @Test
    public void testValidateLengthWithValidLength() {
        Assertions.assertTrue(InputValidator.validateLength("1+2*3=7"));
    }

    @Test
    public void testValidateLengthWithInvalidLength() {
        assertFalse(InputValidator.validateLength("1+2=3"));
    }

    @Test
    public void testValidateCharactersWithValidCharacters() {
        assertTrue(InputValidator.validateCharacters("1+2*3=7"));
    }

    @Test
    public void testValidateCharactersWithInvalidCharacters() {
        assertFalse(InputValidator.validateCharacters("1+2*3=a"));
    }

    @Test
    public void testValidateEqualSignWithPresentEqualSign() {
        assertTrue(InputValidator.validateEqualSign("1+2=3"));
    }

    @Test
    public void testValidateEqualSignWithAbsentEqualSign() {
        assertFalse(InputValidator.validateEqualSign("123+456"));
    }
}
