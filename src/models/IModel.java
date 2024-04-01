package models;

public interface IModel {

    int LIMIT = 6;


    void updateUserLog(String guess);


    boolean isOver(String guess);


    boolean isWin(String guess);

    //@ pure
    boolean guessVerification(String guess) throws CalculationException;


    CharType checkCharType(char c, int index);


    String[] getUserLog();


    String getEquation();


    void initializeGame();

    //@ pure
    int getGuessTime();

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