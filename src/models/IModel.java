package models;

public interface IModel {

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
    boolean isOver(String guess);

    /**
     * Check if the user wins
     *
     * @param guess the user's guess
     * @return true if the user wins, false otherwise
     */
    boolean isWin(String guess);

    /**
     * Verify the guess
     *
     * @param guess the user's guess
     * @return true if the guess is correct, false otherwise
     */
    boolean guessVerification(String guess) throws CalculationException;

    /**
     * Check the character type
     *
     * @param c     the character
     * @param index the index
     * @return the character type
     */
    CharType checkCharType(char c, int index);

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
     */
    void initializeGame();

    /**
     * Set the show error flag
     *
     * @param showErrorFlag true if the error should be shown, false otherwise
     */
    void setShowErrorFlag(boolean showErrorFlag);

    /**
     * Set the show equation flag
     *
     * @param showEquationFlag true if the equation should be shown, false otherwise
     */
    void setShowEquationFlag(boolean showEquationFlag);

    /**
     * Set the random equation flag
     *
     * @param randomEquationFlag true if the equation should be random, false otherwise
     */
    void setRandomEquationFlag(boolean randomEquationFlag);

    /**
     * Get the show equation flag
     *
     * @return true if the equation should be shown, false otherwise
     */
    boolean isShowEquationFlag();

    /**
     * Get the show error flag
     *
     * @return true if the error should be shown, false otherwise
     */
    boolean isShowErrorFlag();

    /**
     * Get the random equation flag
     *
     * @return true if the equation should be random, false otherwise
     */
    boolean isRandomEquationFlag();
}