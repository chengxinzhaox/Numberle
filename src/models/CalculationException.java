package models;

public class CalculationException extends Exception {

    /*@
        requires message != null;
    */
    public CalculationException(String message) {
        super(message);
    }
}