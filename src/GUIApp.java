import controllers.Controller;
import models.IModel;
import models.Model;
import views.View;

public class GUIApp {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(
                GUIApp::createAndShowGUI
        );
    }

    public static void createAndShowGUI() {
        IModel model = new Model();
        Controller controller = new Controller(model);
        new View(model, controller);
    }
}