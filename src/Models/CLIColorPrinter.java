package Models;

/**
 * CLIColorPrinter
 * This class is used to print colored text to the console of the CIL interface
 */
public class CLIColorPrinter {
    private static final String ORANGE = "\u001B[33m";
    private static final String GREEN = "\u001B[32m";
    private static final String GRAY = "\u001B[90m";
    private static final String RED = "\u001B[31m";
    private static final String RESET = "\u001B[0m";

    /**
     * printOrange
     * @param text the text to print
     */
    public static void printOrange(String text) {
        print(text, ORANGE);
    }

    /**
     * printGreen
     * @param text the text to print
     */
    public static void printGreen(String text) {
        print(text, GREEN);
    }

    /**
     * printGray
     * @param text the text to print
     */
    public static void printGray(String text) {
        print(text, GRAY);
    }

    /**
     * printWarn
     * @param text the text to print
     */
    public static void printWarn(String text) {
        print(text, RED);
        System.out.println();
    }

    /**
     * print
     * @param text the text to print
     * @param color the color to print the text
     */
    private static void print(String text, String color) {
        System.out.print(color + text + RESET);
    }
}
