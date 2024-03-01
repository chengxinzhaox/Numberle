import Models.GameManager;
import Models.CLIColorPrinter;

import java.util.Scanner;

public class Main_CLI {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        //Initialize the game
        GameManager.initializeGame();
        String guess;
        String message;

        do {
            //Get user's guess
            guess = getGuess();

            //Verify the guess
            boolean isGuessCorrect = GameManager.guessVerification(guess);

            if (isGuessCorrect) {
                GameManager.updateUserLog(guess);
                GameManager.printUserLog();
            }

        } while (!GameManager.ifOver(guess));

        message = GameManager.ifWin(guess) ? "You win!" : "You lose!";
        if (GameManager.ifWin(guess)) {
            CLIColorPrinter.printGreen(message);
        } else {
            CLIColorPrinter.printWarn(message);
        }
    }

    private static String getGuess() {
        System.out.println("Enter your guess: ");
        return scanner.nextLine();
    }
}