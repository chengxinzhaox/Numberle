
import controllers.GameController;
import models.GameModel;
import views.CLIView;

public class NumberleCLI {
    public static void main(String[] args) {

        GameModel gameModel = new GameModel();
        CLIView gameView = new CLIView();

        GameController controller = new GameController(gameModel, gameView);

        controller.startGame();
    }
}