import models.CalculationException;
import models.CharType;
import models.Model;
import models.IModel;
import views.Messages;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class CLIApp {

    private final IModel model;
    private final Scanner scanner;

    private final List<String> orangeList;
    private final List<String> greenList;
    private final List<String> grayList;
    private final List<String> withoutUse;

    private CLIApp() {
        this.model = new Model();
        this.scanner = new Scanner(System.in);

        model.setShowErrorFlag(getFlag(Messages.SHOW_ERROR_MESSAGE));
        model.setShowEquationFlag(getFlag(Messages.SHOW_EQUATION_MESSAGE));
        model.setRandomEquationFlag(getFlag(Messages.RANDOM_EQUATION_MESSAGE));

        orangeList = new ArrayList<>();
        greenList = new ArrayList<>();
        grayList = new ArrayList<>();
        withoutUse = new ArrayList<>(Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "0", "+", "-", "*", "/"));
    }

    /**
     * Start the game, the main logic of the game
     */
    private void startGame() {
        model.initializeGame();
        String guess;
        printGameStart();

        do {
            if (model.isShowEquationFlag()) {
                printMessage(Messages.TARGET_EQUATION_MESSAGE + model.getEquation());
            }
            printMessage(Messages.ENTER_GUESS);
            guess = scanner.nextLine();

            try {
                if (model.guessVerification(guess)) {
                    model.updateUserLog(guess);
                    printCategories(guess);
                    printUserLog(model.getUserLog(), model.getEquation());
                }
            } catch (CalculationException e) {
                if (model.isShowErrorFlag()) {
                    printWarn(e.getMessage());
                }
            }
        } while (!model.isOver(guess));

        if (model.isWin(guess)) {
            printGreen(Messages.WIN_MESSAGE);
        } else {
            printWarn(Messages.LOSE_MESSAGE);
        }
    }

    /**
     * getFlag
     *
     * @param message the message to print
     * @return true if the input is "y" or "yes", false if the input is "n" or "no"
     */
    private boolean getFlag(String message) {
        printMessage(message);
        String input = scanner.nextLine().trim().toLowerCase();
        return switch (input) {
            case "y", "yes" -> true;
            case "n", "no" -> false;
            default -> {
                printMessage(Messages.INVALID_INPUT);
                yield getFlag(message);
            }
        };
    }

    /**
     * print
     *
     * @param text  the text to print
     * @param color the color to print the text
     */
    private void print(String text, CLIColors color) {
        System.out.print(color.getCode() + text + CLIColors.RESET.getCode());
    }

    /**
     * printOrange
     *
     * @param text the text to print
     */
    private void printOrange(String text) {
        print(text, CLIColors.ORANGE);
    }

    /**
     * printGreen
     *
     * @param text the text to print
     */
    private void printGreen(String text) {
        print(text, CLIColors.GREEN);
    }

    /**
     * printGray
     *
     * @param text the text to print
     */
    private void printGray(String text) {
        print(text, CLIColors.GRAY);
    }

    /**
     * printWarn
     *
     * @param text the text to print
     */
    private void printWarn(String text) {
        print(text, CLIColors.RED);
        System.out.println();
    }

    /**
     * printGameStart
     */
    private void printGameStart() {
        System.out.println(Messages.CUT_OFF + Messages.GAME_START + Messages.CUT_OFF);
        printGreen(Messages.GREEN_INFO);
        printOrange(Messages.ORANGE_INFO);
        printGray(Messages.GRAY_INFO);
    }

    /**
     * printMessage
     *
     * @param message the message to print
     */
    private void printMessage(String message) {
        System.out.println(message);
    }

    /**
     * printUserLog
     *
     * @param userLog  the user's log
     * @param equation the equation
     */
    private void printUserLog(String[] userLog, String equation) {
        System.out.println(Messages.LOG_HEADER);
        for (String logList : userLog) {
            printSingleList(logList, equation);
            System.out.println();
        }
        System.out.println(Messages.CUT_OFF);
    }

    /**
     * PrintCategories to CLI output
     *
     * @param guess the user's guess
     */
    private void printCategories(String guess) {
        System.out.println(Messages.CUT_OFF);
        updateCategories(guess);
        removeConflicts();
        printResults();
        System.out.println(Messages.CUT_OFF);
    }

    /**
     * Update the categories
     *
     * @param guess the user's guess
     */
    private void updateCategories(String guess) {
        for (int i = 0; i < guess.length(); i++) {
            char guessedChar = guess.charAt(i);
            CharType charType = model.checkCharType(guessedChar, i);
            addCharToCategory(String.valueOf(guessedChar), charType);
        }
    }

    /**
     * Add the character to the category
     *
     * @param character the character to add
     * @param charType  the type of the character
     */
    private void addCharToCategory(String character, CharType charType) {
        switch (charType) {
            case GREEN:
                if (addToCategoryIfAbsent(greenList, character)) {
                    withoutUse.remove(character);
                }
                break;
            case ORANGE:
                if (!greenList.contains(character) && addToCategoryIfAbsent(orangeList, character)) {
                    withoutUse.remove(character);
                }
                break;
            case GRAY:
                if (!greenList.contains(character) && !orangeList.contains(character) && addToCategoryIfAbsent(grayList, character)) {
                    withoutUse.remove(character);
                }
                break;
        }
    }

    /**
     * Add to category if absent
     *
     * @param category  the category to add to
     * @param character the character to add
     * @return true if the character is added, false otherwise
     */
    private boolean addToCategoryIfAbsent(List<String> category, String character) {
        if (!category.contains(character)) {
            category.add(character);
            return true;
        }
        return false;
    }

    /**
     * Remove duplicates from the categories
     */
    private void removeConflicts() {
        orangeList.removeAll(greenList);
        grayList.removeAll(greenList);
        grayList.removeAll(orangeList);
    }

    /**
     * Print the results
     */
    private void printResults() {
        printGreen("Green: " + greenList + "\n");
        printOrange("Orange: " + orangeList + "\n");
        printGray("Gray: " + grayList + "\n");
        System.out.println("Without Use: " + withoutUse);
    }


    /**
     * printSingleList
     *
     * @param list     the list to print
     * @param equation the equation
     */
    private void printSingleList(String list, String equation) {
        for (int i = 0; i < equation.length(); i++) {
            CharType charType = model.checkCharType(list.charAt(i), i);
            switch (charType) {
                case GREEN -> printGreen(String.valueOf(list.charAt(i)));
                case ORANGE -> printOrange(String.valueOf(list.charAt(i)));
                case GRAY -> printGray(String.valueOf(list.charAt(i)));
            }
        }
    }

    /**
     * CLIColors
     */
    private enum CLIColors {
        ORANGE("\033[33m"),
        GREEN("\033[32m"),
        GRAY("\033[37m"),
        RED("\033[31m"),
        RESET("\033[0m");

        private final String code;

        CLIColors(String code) {
            this.code = code;
        }

        public String getCode() {
            return code;
        }
    }

    public static void main(String[] args) {
        CLIApp numberleCLI = new CLIApp();
        numberleCLI.startGame();
    }
}