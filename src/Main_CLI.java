
import controllers.GameController;
import models.Game;
import views.CLIView;

public class Main_CLI {
    public static void main(String[] args) {

        Game game = new Game();
        CLIView gameView = new CLIView();

        GameController controller = new GameController(game, gameView);

        controller.startGame();
    }
}
