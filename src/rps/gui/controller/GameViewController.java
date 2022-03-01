package rps.gui.controller;

// Java imports
import javafx.fxml.Initializable;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

/**
 *
 * @author smsj
 */
public class GameViewController implements Initializable {

    private File file;

    String url = "C:\\Users\\Bruger\\IdeaProjects\\Rock-Paper-Scissor\\src\\Bilag\\Disaster.mp3";
    private Media media;
    private MediaPlayer mediaPlayer;

    public void music() {
        file = new File(url);
        media = new Media(file.toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setVolume(100);
        mediaPlayer.play();
    }



    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        music();
    }
}
