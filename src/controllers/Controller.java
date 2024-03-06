package controllers;

import models.CalculationException;
import models.Model;

/**
 * The controller class
 */
public class Controller {

    private final Model model;

    /**
     * Constructor
     *
     * @param model the model
     */
    public Controller(Model model) {
        this.model = model;
    }

    /**
     * Get the flag
     *
     * @param guess the guess
     * @return the flag
     */
    public boolean guessVerification(String guess) throws CalculationException {
        return model.guessVerification(guess);
    }

    /**
     * Initialize the game
     */
    public void initializeGame() {
        model.initializeGame();
    }

    /**
     * Get the user log
     */
    public void printUserLog() {
        String[] log = model.getUserLog();
        for (String s : log) {
            System.out.println(s);
        }
    }


    /**
     * Get show error flag
     *
     * @return if the error flag is shown
     */
    public boolean isShowErrorFlag() {
        return model.isShowErrorFlag();
    }

    /**
     * Get show equation flag
     *
     * @return if the equation flag is shown
     */
    public boolean isShowEquationFlag() {
        return model.isShowEquationFlag();
    }

    /**
     * Get random equation flag
     *
     * @return if random equation
     */
    public boolean isRandomEquationFlag() {
        return model.isRandomEquationFlag();
    }

    /**
     * Set the error flag
     *
     * @param showErrorFlag set the error flag
     */
    public void setShowErrorFlag(boolean showErrorFlag) {
        model.setShowErrorFlag(showErrorFlag);
    }

    /**
     * Set the equation flag
     *
     * @param showEquationFlag set the equation flag
     */
    public void setShowEquationFlag(boolean showEquationFlag) {
        model.setShowEquationFlag(showEquationFlag);
    }

    /**
     * Set the random equation flag
     *
     * @param randomEquationFlag set the random equation flag
     */
    public void setRandomEquationFlag(boolean randomEquationFlag) {
        model.setRandomEquationFlag(randomEquationFlag);
    }

    /**
     * Update the user log
     *
     * @param guess the guess
     */
    public void updateUserLog(String guess) {
        model.updateUserLog(guess);
    }

    /**
     * Get the equation
     *
     * @return the equation
     */
    public String getEquation() {
        return model.getEquation();
    }

    /**
     * Get the guess time
     *
     * @return the guess time
     */
    public int getGuessTime() {
        return model.getGuessTime();
    }

    /**
     * Check if win
     *
     * @param guess the guess
     * @return if win
     */
    public boolean isWin(String guess) {
        return model.isWin(guess);
    }

    /**
     * Check if over
     *
     * @param guess the guess
     * @return if over
     */
    public boolean isOver(String guess) {
        return model.isOver(guess);
    }
}