package Models;

import java.util.Stack;

public class Calculator {

    /**
     * Validate the input and compute the result
     *
     * @param guss the input
     * @return true if the input is valid and the result is correct
     */
    public static boolean validateAndCompute(String guss) {

        //Validate the length of the input
        if (guss.length() < 7) {
            CLIColorPrinter.printWarn("Too Shot");
            return false;
        }

        //Validate the length of the input
        if (guss.length() > 7) {
            CLIColorPrinter.printWarn("Too Long");
            return false;
        }

        // upper case the input
        if (!guss.matches("[0-9+\\-*/= ]+")) {
            CLIColorPrinter.printWarn("Invalid character");
            return false;
        }

        // check have equal sign
        if (guss.indexOf('=') == -1) {
            CLIColorPrinter.printWarn("no equal \"=\" sign");
            return false;
        }

        // remove all white spaces
        guss = guss.replaceAll("\\s", "");

        // separate the input by the equal sign
        String[] parts = guss.split("=");
        if (parts.length != 2) {
            CLIColorPrinter.printWarn("The left side is not equal to the right side");
            return false;
        }

        try {
            // compute the left and right side of the equation
            double leftResult = evaluateExpression(parts[0]);
            double rightResult = evaluateExpression(parts[1]);

            // because of the precision of double, we need to use a small value to compare
            boolean ifEqual = Math.abs(leftResult - rightResult) < 0.0001;
            if (!ifEqual) {
                CLIColorPrinter.printWarn("The left side is not equal to the right side");
                return false;
            } else {
                return true;
            }
        } catch (Exception e) {
            CLIColorPrinter.printWarn("The left side is not equal to the right side");
            return false;
        }
    }

    /**
     * Evaluate the expression
     *
     * @param expression the expression
     * @return the result
     */
    private static double evaluateExpression(String expression) {
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
                i--;
                numbers.push((double) number);
            } else if (current == '+' || current == '-' || current == '*' || current == '/') {
                while (!operations.isEmpty() && hasPrecedence(current, operations.peek())) {
                    numbers.push(applyOp(operations.pop(), numbers.pop(), numbers.pop()));
                }
                operations.push(current);
            }
        }

        while (!operations.isEmpty()) {
            numbers.push(applyOp(operations.pop(), numbers.pop(), numbers.pop()));
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
        if (op2 == '(' || op2 == ')') {
            return false;
        }
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
    private static double applyOp(char op, double b, double a) {
        return switch (op) {
            case '+' -> a + b;
            case '-' -> a - b;
            case '*' -> a * b;
            case '/' -> {
                if (b == 0)
                    throw new UnsupportedOperationException("Cannot divide by zero");
                yield a / b;
            }
            default -> 0;
        };
    }
}