import models.CalculationException;
import models.CharType;
import models.Model;
import models.IModel;
import views.Messages;

import java.util.Scanner;

public class CLIApp {

    private final IModel model;
    private final Scanner scanner;

    private CLIApp() {
        this.model = new Model();
        this.scanner = new Scanner(System.in);

        model.setShowErrorFlag(getFlag(Messages.SHOW_ERROR_MESSAGE));
        model.setShowEquationFlag(getFlag(Messages.SHOW_EQUATION_MESSAGE));
        model.setRandomEquationFlag(getFlag(Messages.RANDOM_EQUATION_MESSAGE));
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