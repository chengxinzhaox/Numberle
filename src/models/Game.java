package models;

import views.Messages;

import java.util.Arrays;

public class Game {

    private static final int LIMIT = 6;
    private static int guessTime = 0;
    private String equation= "";
    private final String[] userLog;

    /**
     * Constructor
     */
    public Game() {
        this.userLog = new String[LIMIT];
    }

    /**
     * Update the user log
     *
     * @param guess the user's guess
     */
    public void updateUserLog(String guess) {
        if (guessTime < this.equation.length()) {
            userLog[guessTime++] = guess;
        }
    }

    /**
     * Check if the game is over
     *
     * @param guess the user's guess
     * @return true if the game is over, false otherwise
     */
    public Boolean ifOver(String guess) {
        return (guessTime == LIMIT) || ifWin(guess);
    }

    /**
     * Check if the user wins
     *
     * @param guess the user's guess
     * @return true if the user wins, false otherwise
     */
    public boolean ifWin(String guess) {
        return this.equation.equals(guess);
    }

    /**
     * Verify the guess
     *
     * @param guess the user's guess
     * @return true if the guess is correct, false otherwise
     */
    public boolean guessVerification(String guess) throws CalculationException {
        return Calculator.validateAndCompute(guess);
    }

    /**
     * Get the user log
     *
     * @return the user log
     */
    public String[] getUserLog() {
        return userLog;
    }

    /**
     * Get the equation
     *
     * @return the equation
     */
    public String getEquation() {
        return equation;
    }

    /**
     * Initialize the game
     */
    public void initializeGame(boolean randomEquation) {

        if (randomEquation) {
            equation = GetEquation.getRandomEquation();
        } else {
            equation = GetEquation.getFixedEquation(1);
        }

        Arrays.fill(userLog, Messages.PLACE_HOLDER.repeat(equation.length()));
        guessTime = 0;
    }
}
