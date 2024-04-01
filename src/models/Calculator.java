package models;

import views.Messages;

import java.util.Stack;

public class Calculator {

    /*@
        requires guess != null;
        ensures \result == true;
        signals (CalculationException e) false;
        pure;
    */
    public static boolean validateAndCompute(String guess) throws CalculationException {

        //Validate if too long
        if (InputValidator.validateTooLong(guess)) {
            throw new CalculationException(Messages.TOO_LONG);
        }

        //Validate if too short
        if (InputValidator.validateTooShort(guess)) {
            throw new CalculationException(Messages.TOO_SHORT);
        }

        // upper case the input
        if (!InputValidator.validateCharacters(guess)) {
            throw new CalculationException(Messages.INVALID_CHARACTER);
        }

        // check have equal sign
        if (!InputValidator.validateEqualSign(guess)) {
            throw new CalculationException(Messages.NO_EQUAL_SIGN);
        }

        // remove all white spaces
        guess = guess.replaceAll("\\s", "");

        // separate the input by the equal sign
        String[] parts = guess.split("=");
        if (parts.length != 2) {
            throw new CalculationException(Messages.NOT_EQUAL);
        }

        try {
            // compute the left and right side of the equation
            double leftResult = evaluateExpression(parts[0]);
            double rightResult = evaluateExpression(parts[1]);

            // because of the precision of double, we need to use a small value to compare
            boolean ifEqual = Math.abs(leftResult - rightResult) < 0.0001;
            if (!ifEqual) {
                throw new CalculationException(Messages.NOT_EQUAL);
            } else {
                return true;
            }
        } catch (Exception e) {
            throw new CalculationException(Messages.NOT_EQUAL);
        }
    }

    /**
     * Evaluate the expression
     *
     * @param expression the expression
     * @return the result
     */
    private static double evaluateExpression(String expression) throws CalculationException {
        Stack<Double> numbers = new Stack<>();
        Stack<Character> operations = new Stack<>();

        for (int i = 0; i < expression.length(); i++) {
            char current = expression.charAt(i);
            if (Character.isDigit(current)) {
                int number = 0;
                while (i < expression.length() && Character.isDigit(expression.charAt(i))) {
                    number = number * 10 + (expression.charAt(i) - '0');
                    i++;
                }
                i--; // Decrement i because it will be incremented by for loop after continuing
                numbers.push((double) number);
            } else if (current == '+' || current == '-' || current == '*' || current == '/') {
                while (!operations.isEmpty() && hasPrecedence(current, operations.peek())) {
                    // Correct the order of operands when applying the operation
                    double b = numbers.pop();
                    double a = numbers.pop();
                    numbers.push(applyOp(operations.pop(), b, a));
                }
                operations.push(current);
            }
        }

        // Apply remaining operations to remaining numbers
        while (!operations.isEmpty()) {
            double b = numbers.pop();
            double a = numbers.pop();
            numbers.push(applyOp(operations.pop(), b, a));
        }

        return numbers.pop();
    }


    /**
     * Check if the first operation has precedence over the second operation
     *
     * @param op1 the first operation
     * @param op2 the second operation
     * @return true if the first operation has precedence over the second operation, false otherwise
     */
    private static boolean hasPrecedence(char op1, char op2) {
        return (op1 != '*' && op1 != '/') || (op2 != '+' && op2 != '-');
    }

    /**
     * Apply the operation
     *
     * @param op the operation
     * @param b  the second number
     * @param a  the first number
     * @return the result
     */
    private static double applyOp(char op, double b, double a) throws CalculationException {
        return switch (op) {
            case '+' -> a + b;
            case '-' -> a - b;
            case '*' -> a * b;
            case '/' -> {
                if (b == 0)
                    throw new CalculationException(Messages.NOT_EQUAL);
                yield a / b;
            }
            default -> throw new CalculationException(Messages.INVALID_INPUT);
        };
    }
}