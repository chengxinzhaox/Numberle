package controllers;

import models.Game;
import models.CalculationException;
import views.Messages;
import views.CLIView;

import java.util.Scanner;

public class GameController {

    private final CLIView gameView;
    private final Game game;
    private final Scanner scanner;

    /**
     * Constructor
     *
     * @param game the game manager
     * @param gameView    the game view
     */
    public GameController(Game game, CLIView gameView) {
        this.game = game;
        this.gameView = gameView;
        this.scanner = new Scanner(System.in);
    }

    /**
     * Start the game
     */
    public void startGame() {

        game.initializeGame();
        gameView.printGameStart();
        String guess;

        do {
            gameView.printMessage(Messages.ENTER_GUESS);
            guess = scanner.nextLine();

            try {
                if (game.guessVerification(guess)) {
                    game.updateUserLog(guess);
                    gameView.printUserLog(game.getUserLog(), game.getEquation());
                }
            } catch (CalculationException e) {
                gameView.printWarn(e.getMessage());
            }
        } while (!game.ifOver(guess));

        if (game.ifWin(guess)) {
            gameView.printGreen(Messages.WIN_MESSAGE);
        } else {
            gameView.printWarn(Messages.LOSE_MESSAGE);
        }
    }
}