package test;

import models.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ModelTest {
    IModel model;
    private final String fixedEquation = "4-1*3=1";
    private final String notEqual = "4-1*3=0";
    private final String wrongEquation = "5-1*3=2";

    /**
     * Set up the model before each test
     */
    @BeforeEach
    public void setUp() {
        model = new Model();
        model.setRandomEquationFlag(false);
        model.initializeGame();
    }

    public void verify_and_upload(String guess) throws CalculationException {
        if (model.guessVerification(guess)) {
            model.updateUserLog(guess);
        }
    }

    /**
     * Test scenario to verify the model's behavior with multiple user inputs: two incorrect equations followed by one correct equation.
     * <p>
     * Preconditions:
     * 1. The game is initialized.
     * 2. The random equation flag is set to false.
     * 3. 'notEuqal' represents an equation which left side not equal to the right side.
     * 4. 'fixedEquation' represents a correct equation.
     * 5. 'wrongEquation' represents an incorrect equation to fixed one.
     * <p>
     * Test Steps:
     * 1. Submit the 'notEqual' equation to the model, and verify that the game is not over and the user has not won.
     * 2. Submit the 'wrong' equation to the model, and verify that the game is not over and the user has not won.
     * 3. Submit the 'fixedEquation' equation to the model, and verify that the game is over and the user has won.
     * <p>
     * Expected Results:
     * The game should be over and the user should have won after submitting the correct equation.
     */
    @Test
    public void testScenario1() throws CalculationException {
        // Submit the 'Wrong' equation to the model, and verify that the game is not over and the user has not won.
        verify_and_upload(wrongEquation);
        assertFalse(model.isOver(wrongEquation));
        assertFalse(model.isWin(wrongEquation));

        // Submit the 'Wrong' equation to the model, and verify that the game is not over and the user has not won.
        verify_and_upload(wrongEquation);
        assertFalse(model.isOver(wrongEquation));
        assertFalse(model.isWin(wrongEquation));

        // Submit the 'Fixed' equation to the model, and verify that the game is over and the user has won.
        verify_and_upload(fixedEquation);
        assertTrue(model.isOver(fixedEquation));
        assertTrue(model.isWin(fixedEquation));
    }

    /**
     * Test scenario to verify the model's behavior with multiple user inputs: sex incorrect equations.
     * <p>
     * Preconditions:
     * 1. The game is initialized.
     * 2. The random equation flag is set to false.
     * 3. 'notEuqal' represents an equation which left side not equal to the right side.
     * 4. 'fixedEquation' represents a correct equation.
     * 5. 'wrongEquation' represents an incorrect equation to fixed one.
     * <p>
     * Test Steps:
     * 1. Submit the 'wrong' equation to the model, and verify that the game is not over and the user has not won.
     * 2. Submit the 'wrong' equation to the model, and verify that the game is not over and the user has not won.
     * 3. Submit the 'wrong' equation to the model, and verify that the game is not over and the user has not won.
     * 4. Submit the 'wrong' equation to the model, and verify that the game is not over and the user has not won.
     * 5. Submit the 'wrong' equation to the model, and verify that the game is not over and the user has not won.
     * 6. Submit the 'wrong' equation to the model, and verify that the game is over and the user has not won.
     * <p>
     * Expected Results:
     * The game should be over and the user should have not won after submitting the incorrect equation six times.
     */
    @Test
    public void testScenario2() throws CalculationException {
        // Submit the 'wrong' equation to the model, and verify that the game is not over and the user has not won.
        verify_and_upload(wrongEquation);
        assertFalse(model.isOver(wrongEquation));
        assertFalse(model.isWin(wrongEquation));

        // Submit the 'wrong' equation to the model, and verify that the game is not over and the user has not won.
        verify_and_upload(wrongEquation);
        assertFalse(model.isOver(wrongEquation));
        assertFalse(model.isWin(wrongEquation));

        // Submit the 'wrong' equation to the model, and verify that the game is not over and the user has not won.
        verify_and_upload(wrongEquation);
        assertFalse(model.isOver(wrongEquation));
        assertFalse(model.isWin(wrongEquation));

        // Submit the 'wrong' equation to the model, and verify that the game is not over and the user has not won.
        verify_and_upload(wrongEquation);
        assertFalse(model.isOver(wrongEquation));
        assertFalse(model.isWin(wrongEquation));

        // Submit the 'wrong' equation to the model, and verify that the game is not over and the user has not won.
        verify_and_upload(wrongEquation);
        assertFalse(model.isOver(wrongEquation));
        assertFalse(model.isWin(wrongEquation));

        // Submit the 'wrong' equation to the model, and verify that the game is not over and the user has not won.
        verify_and_upload(wrongEquation);
        assertTrue(model.isOver(wrongEquation));
        assertFalse(model.isWin(wrongEquation));
    }

    /**
     * Test scenario to verify the model's behavior with multiple user inputs: nine incorrect equations followed by one correct equation. Ensure that not equal equations are not tallied in the count.
     * <p>
     * Preconditions:
     * 1. The game is initialized.
     * 2. The random equation flag is set to false.
     * 3. 'notEuqal' represents an equation which left side not equal to the right side.
     * 4. 'fixedEquation' represents a correct equation.
     * 5. 'wrongEquation' represents an incorrect equation to fixed one.
     * <p>
     * Test Steps:
     * 1. Submit the 'notEqual' equation to the model, and verify that the game is not over and the user has not won.
     * 2. Submit the 'notEqual' equation to the model, and verify that the game is not over and the user has not won.
     * 3. Submit the 'notEqual' equation to the model, and verify that the game is not over and the user has not won.
     * 4. Submit the 'notEqual' equation to the model, and verify that the game is not over and the user has not won.
     * 5. Submit the 'notEqual' equation to the model, and verify that the game is not over and the user has not won.
     * 6. Submit the 'notEqual' equation to the model, and verify that the game is not over and the user has not won.
     * 7. Submit the 'notEqual' equation to the model, and verify that the game is not over and the user has not won.
     * 8. Submit the 'notEqual' equation to the model, and verify that the game is not over and the user has not won.
     * 9. Submit the 'notEqual' equation to the model, and verify that the game is not over and the user has not won.
     * 10. Submit the 'fixedEquation' equation to the model, and verify that the game is over and the user has won.
     * <p>
     * Expected Results:
     * The game should not over after submitting the not equal equation nine times, and the user should have won after submitting the correct equation.
     */
    @Test
    public void testScenario3() throws CalculationException {
        assertThrows(CalculationException.class, () -> verify_and_upload(notEqual));
        assertFalse(model.isOver(notEqual));
        assertFalse(model.isWin(notEqual));

        assertThrows(CalculationException.class, () -> verify_and_upload(notEqual));
        assertFalse(model.isOver(notEqual));
        assertFalse(model.isWin(notEqual));

        assertThrows(CalculationException.class, () -> verify_and_upload(notEqual));
        assertFalse(model.isOver(notEqual));
        assertFalse(model.isWin(notEqual));

        assertThrows(CalculationException.class, () -> verify_and_upload(notEqual));
        assertFalse(model.isOver(notEqual));
        assertFalse(model.isWin(notEqual));

        assertThrows(CalculationException.class, () -> verify_and_upload(notEqual));
        assertFalse(model.isOver(notEqual));
        assertFalse(model.isWin(notEqual));

        assertThrows(CalculationException.class, () -> verify_and_upload(notEqual));
        assertFalse(model.isOver(notEqual));
        assertFalse(model.isWin(notEqual));

        assertThrows(CalculationException.class, () -> verify_and_upload(notEqual));
        assertFalse(model.isOver(notEqual));
        assertFalse(model.isWin(notEqual));

        assertThrows(CalculationException.class, () -> verify_and_upload(notEqual));
        assertFalse(model.isOver(notEqual));
        assertFalse(model.isWin(notEqual));

        assertThrows(CalculationException.class, () -> verify_and_upload(notEqual));
        assertFalse(model.isOver(notEqual));
        assertFalse(model.isWin(notEqual));

        verify_and_upload(fixedEquation);
        assertTrue(model.isOver(fixedEquation));
        assertTrue(model.isWin(fixedEquation));
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