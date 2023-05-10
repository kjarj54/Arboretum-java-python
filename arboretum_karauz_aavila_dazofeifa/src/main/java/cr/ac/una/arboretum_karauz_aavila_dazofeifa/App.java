package cr.ac.una.arboretum_karauz_aavila_dazofeifa;

import cr.ac.una.arboretum_karauz_aavila_dazofeifa.util.FlowController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import javafx.scene.image.Image;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        FlowController.getInstance().InitializeFlow(stage,null);
        stage.getIcons().add(new Image("cr/ac/una/arboretum_karauz_aavila_dazofeifa/resources/Usuario-48.png"));
        stage.setTitle("UNA Arboretum");
        FlowController.getInstance().goViewInWindow("PrincipalView");
    }

    public static void main(String[] args) {
        launch();
    }

}
