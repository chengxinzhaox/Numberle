package models;

import views.Messages;

import java.util.Arrays;
import java.util.Observable;

public class Model extends Observable implements IModel {

    private String equation;
    private String[] userLog;
    private int guessTime;

    private boolean showErrorFlag = true;
    private boolean showEquationFlag = false;
    private boolean randomEquationFlag = true;

    public Model() {
        initializeGame();
    }

    @Override
    public void initializeGame() {

        userLog = new String[LIMIT];
        guessTime = 0;

        if (randomEquationFlag) {
            equation = GetEquation.getRandomEquation();
        } else {
            equation = GetEquation.getFixedEquation();
        }

        System.out.println(equation);

        Arrays.fill(userLog, Messages.PLACE_HOLDER.repeat(equation.length()));

        setChanged();
        notifyObservers();
    }

    @Override
    public void updateUserLog(String guess) {
        if (guessTime < this.equation.length()) {
            userLog[guessTime++] = guess;
        }
        setChanged();
        notifyObservers();
    }

    @Override
    public boolean isOver(String guess) {
        return (guessTime == LIMIT) || isWin(guess);
    }

    @Override
    public boolean isWin(String guess) {
        return this.equation.equals(guess);
    }

    @Override
    public boolean guessVerification(String guess) throws CalculationException {
        return Calculator.validateAndCompute(guess);
    }

    @Override
    public CharType checkCharType(char c, int index) {
        if (c == equation.charAt(index)) {
            return CharType.GREEN;
        } else if (equation.contains(String.valueOf(c))) {
            return CharType.ORANGE;
        } else {
            return CharType.GRAY;
        }
    }

    @Override
    public String[] getUserLog() {
        return userLog;
    }

    @Override
    public String getEquation() {
        return equation;
    }

    @Override
    public void setShowErrorFlag(boolean showErrorFlag) {
        this.showErrorFlag = showErrorFlag;
    }

    @Override
    public void setShowEquationFlag(boolean showEquationFlag) {
        this.showEquationFlag = showEquationFlag;
    }

    @Override
    public void setRandomEquationFlag(boolean randomEquationFlag) {
        this.randomEquationFlag = randomEquationFlag;
    }

    @Override
    public boolean isShowErrorFlag() {
        return showErrorFlag;
    }

    @Override
    public boolean isShowEquationFlag() {
        return showEquationFlag;
    }

    @Override
    public boolean isRandomEquationFlag() {
        return randomEquationFlag;
    }
}