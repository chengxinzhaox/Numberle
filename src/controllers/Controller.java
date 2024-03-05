package controllers;

import models.CalculationException;
import models.Model;

public class Controller {

    private final Model model;

    public Controller(Model model) {
        this.model = model;
    }

    public boolean guessVerification(String guess) throws CalculationException {
        return model.guessVerification(guess);
    }

    public void initializeGame() {
        model.initializeGame();
    }

    public void printUserLog() {
        String[] log = model.getUserLog();
        for (String s : log) {
            System.out.println(s);
        }
    }

    public boolean isShowErrorFlag() {
        return model.isShowErrorFlag();
    }

    public boolean isShowEquationFlag() {
        return model.isShowEquationFlag();
    }

    public boolean isRandomEquationFlag() {
        return model.isRandomEquationFlag();
    }

    public void setShowErrorFlag(boolean showErrorFlag) {
        model.setShowErrorFlag(showErrorFlag);
    }

    public void setShowEquationFlag(boolean showEquationFlag) {
        model.setShowEquationFlag(showEquationFlag);
    }

    public void setRandomEquationFlag(boolean randomEquationFlag) {
        model.setRandomEquationFlag(randomEquationFlag);
    }

    public void updateUserLog(String guess) {
        model.updateUserLog(guess);
    }

    public String getEquation() {
        return model.getEquation();
    }

    public boolean isWin(String guess) {
        return model.isWin(guess);
    }

    public boolean isOver(String guess) {
        return model.isOver(guess);
    }
}