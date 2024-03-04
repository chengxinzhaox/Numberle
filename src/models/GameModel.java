package models;

import views.Messages;

import java.util.Arrays;
import java.util.Observable;

public class GameModel extends Observable implements IGameModel {

    private String equation;
    private String[] userLog;
    private int guessTime;

    @Override
    public void updateUserLog(String guess) {
        if (guessTime < this.equation.length()) {
            userLog[guessTime++] = guess;
        }
    }

    @Override
    public boolean ifOver(String guess) {
        return (guessTime == LIMIT) || ifWin(guess);
    }

    @Override
    public boolean ifWin(String guess) {
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
    public void initializeGame(boolean randomEquation) {

        userLog = new String[LIMIT];
        guessTime = 0;

        if (randomEquation) {
            equation = GetEquation.getRandomEquation();
        } else {
            equation = GetEquation.getFixedEquation();
        }

        Arrays.fill(userLog, Messages.PLACE_HOLDER.repeat(equation.length()));

        setChanged();
        notifyObservers();
    }
}