package test;

import models.InputValidator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class InputValidatorTest {

    @Test
    public void testValidateTooLong() {
        assertTrue(InputValidator.validateTooLong("12345678"));
        assertFalse(InputValidator.validateTooLong("1234567"));
    }

    @Test
    public void testValidateTooShort() {
        assertTrue(InputValidator.validateTooShort("123456"));
        assertFalse(InputValidator.validateTooShort("12345678"));
    }

    @Test
    public void testValidateCharacters() {
        assertTrue(InputValidator.validateCharacters("12345678"));
        assertTrue(InputValidator.validateCharacters("12345678+"));
        assertTrue(InputValidator.validateCharacters("12345678-"));
        assertTrue(InputValidator.validateCharacters("12345678*"));
        assertTrue(InputValidator.validateCharacters("12345678/"));
        assertTrue(InputValidator.validateCharacters("12345678="));
        assertTrue(InputValidator.validateCharacters("12345678 "));
        assertFalse(InputValidator.validateCharacters("12345678a"));
    }

    @Test
    public void testValidateEqualSign() {
        assertTrue(InputValidator.validateEqualSign("12345678="));
        assertFalse(InputValidator.validateEqualSign("12345678"));
    }
}
