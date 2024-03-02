import models.CalculationException;
import models.CharType;
import models.GameModel;
import models.IGameModel;
import views.Messages;

import java.util.Scanner;

public class NumberleCLI {

    private final boolean showErrorFlag;
    private final boolean showEquationFlag;
    private final boolean randomEquationFlag;

    private final IGameModel gameModel;
    private final Scanner scanner;

    private NumberleCLI() {
        this.gameModel = new GameModel();
        this.scanner = new Scanner(System.in);

        showErrorFlag = getFlag(Messages.SHOW_ERROR_MESSAGE);
        showEquationFlag = getFlag(Messages.SHOW_EQUATION_MESSAGE);
        randomEquationFlag = getFlag(Messages.RANDOM_EQUATION_MESSAGE);
    }

    /**
     * Start the game, the main logic of the game
     */
    private void startGame() {
        gameModel.initializeGame(randomEquationFlag);
        String guess;
        printGameStart();

        do {
            if (showEquationFlag) {
                printMessage(Messages.TARGET_EQUATION_MESSAGE + gameModel.getEquation());
            }
            printMessage(Messages.ENTER_GUESS);
            guess = scanner.nextLine();

            try {
                if (gameModel.guessVerification(guess)) {
                    gameModel.updateUserLog(guess);
                    printUserLog(gameModel.getUserLog(), gameModel.getEquation());
                }
            } catch (CalculationException e) {
                if (showErrorFlag) {
                    printWarn(e.getMessage());
                }
            }
        } while (!gameModel.ifOver(guess));

        if (gameModel.ifWin(guess)) {
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
        printGreen(Messages.GREEN_INFO + "\n");
        printOrange(Messages.ORANGE_INFO + "\n");
        printGray(Messages.GRAY_INFO + "\n");
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
            CharType charType = gameModel.checkCharType(list.charAt(i), i);
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
        NumberleCLI numberleCLI = new NumberleCLI();
        numberleCLI.startGame();
    }
}