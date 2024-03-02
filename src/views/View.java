package views;

public class View {

    private static final String ORANGE = "\u001B[33m";
    private static final String GREEN = "\u001B[32m";
    private static final String GRAY = "\u001B[90m";
    private static final String RED = "\u001B[31m";
    private static final String RESET = "\u001B[0m";

    /**
     * print
     * @param text the text to print
     * @param color the color to print the text
     */
    private void print(String text, String color) {
        System.out.print(color + text + RESET);
    }

    /**
     * printOrange
     * @param text the text to print
     */
    public void printOrange(String text) {
        print(text, ORANGE);
    }

    /**
     * printGreen
     * @param text the text to print
     */
    public void printGreen(String text) {
        print(text, GREEN);
    }

    /**
     * printGray
     * @param text the text to print
     */
    public void printGray(String text) {
        print(text, GRAY);
    }

    /**
     * printWarn
     * @param text the text to print
     */
    public void printWarn(String text) {
        print(text, RED);
        System.out.println();
    }

    /**
     * printGameStart
     */
    public void printGameStart() {
        System.out.println(Messages.CUT_OFF + Messages.GAME_START + Messages.CUT_OFF);
    }

    /**
     * printMessage
     * @param message the message to print
     */
    public void printMessage(String message) {
        System.out.println(message);
    }

    public void printUserLog(String[] userLog, String equation) {
        System.out.println(Messages.LOG_HEADER);
        for (String logList : userLog) {
            printSingleList(logList, equation);
            System.out.println();
        }
        System.out.println(Messages.CUT_OFF);
    }

    private void printSingleList(String list, String equation) {
        for (int i = 0; i < equation.length(); i++) {
            char equationChar = equation.charAt(i);
            char listChar = list.charAt(i);
            if (equationChar == listChar) {
                printGreen(Character.toString(listChar));
            } else if (equation.contains(Character.toString(listChar))) {
                printOrange(Character.toString(listChar));
            } else {
                printGray(Character.toString(listChar));
            }
        }
    }
}