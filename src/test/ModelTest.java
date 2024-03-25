package test;

import models.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ModelTest {
    Model model;
    private final String fixedEquation = "4-1*3=1";
    private final String notEqual = "4-1*3=0";

    /**
     * Set up the model before each test
     */
    @BeforeEach
    public void setUp() {
        model = new Model();
        model.setRandomEquationFlag(false);
        model.initializeGame();
    }

    /**
     * Test if the game is initialized correctly
     */
    @Test
    public void testInitializeGame() {
        assertEquals(0, model.getGuessTime());
        assertNotNull(model.getUserLog());
        assertTrue(model.isShowErrorFlag());
        assertFalse(model.isShowEquationFlag());
        assertFalse(model.isRandomEquationFlag());
    }

    /**
     * Test if the update user log works correctly
     */
    @Test
    public void testUpdateUserLog() {
        String wrongGuess = "4-1*4=0";
        model.updateUserLog(wrongGuess);
        assertEquals(1, model.getGuessTime());
        assertEquals(wrongGuess, (model.getUserLog()[0]));
    }

    /**
     * Test if the verification of the guess works correctly
     */
    @Test
    public void testGuessVerificationDoesNotThrowException() throws CalculationException {
        assertDoesNotThrow(() -> model.guessVerification(fixedEquation));
        assertTrue(model.guessVerification(fixedEquation));
    }

    /**
     * Test if the verification of the guess throws an exception for invalid input
     */
    @Test
    public void testGuessVerificationThrowsExceptionForInvalidInput() {
        assertThrows(CalculationException.class, () -> model.guessVerification(notEqual));
    }

    /**
     * Test if the checkCharType works correctly
     */
    @Test
    public void testCheckCharType() {
        assertEquals(CharType.GREEN, model.checkCharType('4', 0));
        assertEquals(CharType.ORANGE, model.checkCharType('1', 0));
        assertEquals(CharType.GRAY, model.checkCharType('5', 0));
    }

    /**
     * Test if setting and getting the flags work correctly
     */
    @Test
    public void testFlagSettersAndGetters() {
        model.setShowErrorFlag(true);
        assertTrue(model.isShowErrorFlag());

        model.setShowEquationFlag(true);
        assertTrue(model.isShowEquationFlag());

        model.setRandomEquationFlag(true);
        assertTrue(model.isRandomEquationFlag());
    }

    /**
     * Test if the game is over with the correct guess
     */
    @Test
    public void testIsOverWithCorrectGuess() {
        model.updateUserLog(notEqual);
        assertFalse(model.isOver(notEqual));
        model.updateUserLog(fixedEquation);
        assertTrue(model.isOver(fixedEquation));
    }

    /**
     * Test if the game is over with the incorrect guess
     */
    @Test
    public void testIsWinWithCorrectGuess() {
        assertTrue(model.isWin(fixedEquation));
    }
}