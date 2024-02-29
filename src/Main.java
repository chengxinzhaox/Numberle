import Models.GameManager;
import Models.CLIColorPrinter;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        //Initialize the game
        GameManager.initializeGame();

        String guess = "";

        do {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter your guess: ");
            guess = scanner.nextLine();

            //Verify the guess
            Boolean isGuessCorrect = GameManager.gussVerification(guess);

            if (isGuessCorrect) {
                GameManager.updateUserLog(guess);
                GameManager.printUserLog();
            } else {
                CLIColorPrinter.printWarn("Invalid guess, try again");
            }
        } while (!GameManager.ifOver(guess));

        if (GameManager.ifWin(guess)) {
            CLIColorPrinter.printGreen("You win!");
        } else {
            CLIColorPrinter.printWarn("You lose!");
        }
    }
}