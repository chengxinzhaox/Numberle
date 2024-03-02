package models;

public interface IGameModel {
    int LIMIT = 6;

    /**
     * Update the user log
     *
     * @param guess the user's guess
     */
    void updateUserLog(String guess);

    /**
     * Check if the game is over
     *
     * @param guess the user's guess
     * @return true if the game is over, false otherwise
     */
    boolean ifOver(String guess);

    /**
     * Check if the user wins
     *
     * @param guess the user's guess
     * @return true if the user wins, false otherwise
     */
    boolean ifWin(String guess);

    /**
     * Verify the guess
     *
     * @param guess the user's guess
     * @return true if the guess is correct, false otherwise
     */
    boolean guessVerification(String guess) throws CalculationException;

    /**
     * Get the user log
     *
     * @return the user log
     */
    String[] getUserLog();

    /**
     * Get the equation
     *
     * @return the equation
     */
    String getEquation();

    /**
     * Initialize the game
     *
     * @param randomEquation true if the equation is random, false otherwise
     */
    void initializeGame(boolean randomEquation);
}
