package models;

/**
 * Custom exception for the calculator
 */
public class CalculationException extends Exception{
    public CalculationException(String message) {
        super(message);
    }
}