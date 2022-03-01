package rps;

//Project imports
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import rps.gui.ConsoleApp;
import rps.gui.JavaFXApp;

import java.io.IOException;
import java.util.Objects;

/**
 * Main class where we start
 *
 * @author smsj
 */
public class Main {


    /**
     * Main start
     * @param args
     */
    public static void main(String[] args) throws IOException {
        //Console version
        //startRPSConsoleGame();

        //JavaFX version
        startRPSJavaFXGame();
    }

    /**
     * Start a JavaFX version of the game
     */
    private static void startRPSJavaFXGame() throws IOException {


        JavaFXApp.launch();

    }

    /**
     * Start a console version of the game
     */
    public static void startRPSConsoleGame() {
        new ConsoleApp().startGame();
    }
}



