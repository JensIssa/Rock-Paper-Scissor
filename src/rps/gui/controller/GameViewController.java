package rps.gui.controller;

// Java imports
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import java.io.File;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 *
 * @author smsj
 */
public class GameViewController implements Initializable {
    @FXML
    private MediaView viewer;
    private  File file;
    @FXML
    private  Media media;
    @FXML
    private  MediaPlayer mediaPlayer;
    /**
     * Initializes the controller class.
     */
    public GameViewController() {
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        music();
    }


    public void music()  {
        file = new File("Music/Disaster.mp3");
        media = new Media(file.toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        System.out.println(mediaPlayer);
        System.out.println(mediaPlayer.isMute());

    }



}
