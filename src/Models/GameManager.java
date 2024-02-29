package Models;

public class GameManager {

    static String equation = GetEquation.getEquation();
    static String[] userLog = new String[equation.length()];
    static int guessTime = 0;

    /**
     * Print the user log
     */
    public static void printUserLog() {
        System.out.println("-YOUR LOG-");
        for (int i = 0; i < equation.length(); i++) {
            printSingleList(userLog[i]);
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
                userLog[guessTime] = guess;
                guessTime++;
            }
    }

    /**
     * Check if the game is over
     * @param guess the user's guess
     * @return true if the game is over, false otherwise
     */
    public static Boolean ifOver(String guess) {
        return (guessTime == equation.length()) || ifWin(guess);
    }

    /**
     * Check if the user wins
     * @param guess the user's guess
     * @return true if the user wins, false otherwise
     */
    public static Boolean ifWin(String guess) {
        return equation.equals(guess);
    }

    /**
     * Verify the guess
     * @param guess the user's guess
     * @return true if the guess is correct, false otherwise
     */
    public static Boolean gussVerification(String guess) {
        return Calculator.validateAndCompute(guess);
    }

    /**
     * Print a single list
     * @param list the list to print
     */
    public static void printSingleList(String list) {
        String[] equationMatrix = stringToMatrix(equation);
        String[] listMatrix = stringToMatrix(list);
        for (int i = 0; i < equation.length(); i++) {
            if (equationMatrix[i].equals(listMatrix[i])) {
                CLIColorPrinter.printGreen(listMatrix[i]);
            } else if (equation.contains(listMatrix[i])) {
                CLIColorPrinter.printOrange(listMatrix[i]);
            } else {
                CLIColorPrinter.printGray(listMatrix[i]);
            }
        }
    }

    /**
     * Convert a string to a matrix
     * @param string the string to convert
     * @return the matrix
     */
    private static String[] stringToMatrix(String string) {
        String[] matrix = new String[string.length()];
        for (int i = 0; i < string.length(); i++) {
            matrix[i] = String.valueOf(string.charAt(i));
        }
        return matrix;
    }

    /**
     * Initialize the game
     */
    public static void initializeGame() {

        System.out.println("_".repeat(40));
        System.out.println("GAME BEGINS");

        for (int i = 0; i < equation.length(); i++) {
            userLog[i] = "_______";
        }
        System.out.println(equation);
        printUserLog();
    }
}
