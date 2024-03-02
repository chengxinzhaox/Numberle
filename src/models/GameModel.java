package models;

import views.Messages;
import java.util.Arrays;

public class GameModel implements IGameModel {

    private static int guessTime = 0;
    private String equation = "";
    private final String[] userLog;

    public GameModel() {
        this.userLog = new String[LIMIT];
    }

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

        if (randomEquation) {
            equation = GetEquation.getRandomEquation();
        } else {
            equation = GetEquation.getFixedEquation();
        }

        Arrays.fill(userLog, Messages.PLACE_HOLDER.repeat(equation.length()));
        guessTime = 0;
    }
}
