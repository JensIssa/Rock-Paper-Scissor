package rps.gui;

// Java imports
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

/**
 * JavaFX implementation of the RPS game
 *
 * @author smsj
 */
public class JavaFXApp extends Application {

    public static void launch() {
        Application.launch();
    }

    @Override
    public void start(Stage stage) throws Exception {

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/rps/gui/view/DesignForslag.fxml")));
        stage.setTitle("Welcome to the not-implemented Rock-Paper-Scissor game!");
        stage.setScene(new Scene(root));
        stage.show();
    }
}
