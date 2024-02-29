package Models;

/**
 * CLIColorPrinter
 */
public class CLIColorPrinter {
    private static final String ORANGE = "\u001B[33m";
    private static final String GREEN = "\u001B[32m";
    private static final String GRAY = "\u001B[90m";
    private static final String RED = "\u001B[31m";

    /**
     * printOrange
     * @param text the text to print
     */
    public static void printOrange(String text) {
        System.out.print(ORANGE + text + "\u001B[0m");
    }

    /**
     * printGreen
     * @param text the text to print
     */
    public static void printGreen(String text) {
        System.out.print(GREEN + text + "\u001B[0m");
    }

    /**
     * printGray
     * @param text the text to print
     */
    public static void printGray(String text) {
        System.out.print(GRAY + text + "\u001B[0m");
    }

    /**
     * printWarn
     * @param text the text to print
     */
    public static void printWarn(String text) {
        System.out.println(RED + text + "\u001B[0m");
    }
}
