package models;

import views.Messages;

import java.util.Arrays;
import java.util.Observable;

public class Model extends Observable implements IModel {

    //@ invariant LIMIT == 6;
    //@ private invariant 0 <= guessTime && guessTime <= 6;
    //@ private invariant equation != null;
    //@ private invariant userLog != null;

    private String equation;
    private String[] userLog;
    private int guessTime;

    private boolean showErrorFlag = true;
    private boolean showEquationFlag = false;
    private boolean randomEquationFlag = true;

    public Model() {
        initializeGame();
    }

    /*@
        requires true;
        assignable equation, userLog, guessTime;
        ensures equation != null;
        ensures userLog != null;
        ensures guessTime == 0;
     */
    @Override
    public void initializeGame() {

        userLog = new String[LIMIT];
        guessTime = 0;

        if (randomEquationFlag) {
            equation = GetEquation.getRandomEquation();
        } else {
            equation = GetEquation.getFixedEquation();
        }

        final String placeHolder = Messages.PLACE_HOLDER.repeat(equation.length());

        Arrays.fill(userLog, placeHolder);

        setChanged();
        notifyObservers();
    }

    /*@
        requires guess != null;
        assignable userLog, guessTime;
        ensures userLog[guessTime] == guess;
        ensures guessTime == \old(guessTime) + 1;
     */
    @Override
    public void updateUserLog(String guess) {
        assert guess != null;
        if (guessTime < this.equation.length()) {
            userLog[guessTime++] = guess;
        }
        setChanged();
        notifyObservers();
    }

    /*@
        requires guess != null;
        ensures \result == (guessTime == LIMIT) || isWin(guess);
     */
    @Override
    public boolean isOver(String guess) {
        assert guess != null;
        return (guessTime == LIMIT) || isWin(guess);
    }

    /*@
        requires guess != null;
        ensures \result == equation.equals(guess);
        pure;
     */
    @Override
    public boolean isWin(String guess) {
        assert guess != null;
        return this.equation.equals(guess);
    }

    /*@
        requires guess != null;
        ensures \result == Calculator.validateAndCompute(guess);
        signals_only CalculationException;
     */
    @Override
    public boolean guessVerification(String guess) throws CalculationException {
        assert guess != null;
        return Calculator.validateAndCompute(guess);
    }

    /*@
        requires true;
        ensures \result == CharType.GREEN ==> c == equation.charAt(index);
        ensures \result == CharType.ORANGE ==> equation.contains(String.valueOf(c));
        ensures \result == CharType.GRAY ==> true;
     */
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
    public int getGuessTime() {
        return guessTime;
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