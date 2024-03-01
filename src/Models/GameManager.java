package Models;

import java.util.Arrays;

public class GameManager {

    private static final int LIMIT = 6;
    private static int guessTime = 0;
    private static final String equation = GetEquation.getEquation();
    private static final String[] userLog = new String[LIMIT];

    /**
     * Print the user log
     */
    public static void printUserLog() {
        System.out.println("-YOUR LOG-");
        for (String logList : userLog) {
            printSingleList(logList);
            System.out.println();
        }
        System.out.println("-".repeat(10));
     }

    /**
     * Update the user log
     * @param guess the user's guess
     */
    public static void updateUserLog(String guess) {
            if (guessTime < equation.length()) {
                userLog[guessTime++] = guess;
            }
    }

    /**
     * Check if the game is over
     * @param guess the user's guess
     * @return true if the game is over, false otherwise
     */
    public static Boolean ifOver(String guess) {
        return (guessTime == LIMIT) || ifWin(guess);
    }

    /**
     * Check if the user wins
     * @param guess the user's guess
     * @return true if the user wins, false otherwise
     */
    public static boolean ifWin(String guess) {
        return equation.equals(guess);
    }

    /**
     * Verify the guess
     * @param guess the user's guess
     * @return true if the guess is correct, false otherwise
     */
    public static boolean guessVerification(String guess) {
        return Calculator.validateAndCompute(guess);
    }

    /**
     * Print a single list
     * @param list the list to print
     */
    private static void printSingleList(String list) {
    for (int i = 0; i < equation.length(); i++) {
        char equationChar = equation.charAt(i);
        char listChar = list.charAt(i);
        if (equationChar == listChar) {
            CLIColorPrinter.printGreen(Character.toString(listChar));
        } else if (equation.contains(Character.toString(listChar))) {
            CLIColorPrinter.printOrange(Character.toString(listChar));
        } else {
            CLIColorPrinter.printGray(Character.toString(listChar));
        }
    }
}

    /**
     * Initialize the game
     */
    public static void initializeGame() {

        System.out.println("_".repeat(40));
        System.out.println("GAME BEGINS");

        Arrays.fill(userLog, "_".repeat(equation.length()));
        System.out.println(equation);
        printUserLog();
    }
}
