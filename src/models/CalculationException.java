package models;

public class CalculationException extends Exception {

    /*@
        requires message != null;
    */

    /**
     * Custom exception for the calculator
     */
    public CalculationException(String message) {
        super(message);
    }
}