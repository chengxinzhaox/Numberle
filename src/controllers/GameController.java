package controllers;

import models.GameModel;
import models.CalculationException;
import views.Messages;
import views.CLIView;

import java.util.Scanner;

public class GameController {

    private final CLIView gameView;
    private final GameModel gameModel;
    private final Scanner scanner;
    private final boolean showErrorFlag;
    private final boolean showEquationFlag;
    private final boolean randomEquationFlag;

    /**
     * Constructor
     *
     * @param gameModel     the game manager
     * @param gameView the game view
     */
    public GameController(GameModel gameModel, CLIView gameView) {
        this.gameModel = gameModel;
        this.gameView = gameView;
        this.scanner = new Scanner(System.in);

        showErrorFlag = getFlag(Messages.SHOW_ERROR_MESSAGE);
        showEquationFlag = getFlag(Messages.SHOW_EQUATION_MESSAGE);
        randomEquationFlag = getFlag(Messages.RANDOM_EQUATION_MESSAGE);
    }

    private boolean getFlag(String message) {
        String input;
        while (true) {
            gameView.printMessage(message);
            input = scanner.nextLine().trim().toLowerCase();
            if (input.equals("y") || input.equals("yes")) {
                return true;
            } else if (input.equals("n") || input.equals("no")) {
                return false;
            } else {
                gameView.printMessage(Messages.INVALID_INPUT);
            }
        }
    }


    /**
     * Start the game
     */
    public void startGame() {

        gameModel.initializeGame(randomEquationFlag);
        gameView.printGameStart();
        String guess;

        do {
            gameView.printMessage(Messages.ENTER_GUESS);
            if (showEquationFlag) {
                gameView.printMessage(Messages.TARGET_EQUATION_MESSAGE + gameModel.getEquation());
            }
            guess = scanner.nextLine();

            try {
                if (gameModel.guessVerification(guess)) {
                    gameModel.updateUserLog(guess);
                    gameView.printUserLog(gameModel.getUserLog(), gameModel.getEquation());
                }
            } catch (CalculationException e) {
                if (showErrorFlag) {
                    gameView.printWarn(e.getMessage());
                }
            }
        } while (!gameModel.ifOver(guess));

        if (gameModel.ifWin(guess)) {
            gameView.printGreen(Messages.WIN_MESSAGE);
        } else {
            gameView.printWarn(Messages.LOSE_MESSAGE);
        }
    }
}