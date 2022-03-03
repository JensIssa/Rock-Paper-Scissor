package rps.gui.controller;

// Java imports

import javafx.animation.PathTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import rps.bll.game.*;
import rps.bll.player.IPlayer;
import rps.bll.player.Player;
import rps.bll.player.PlayerType;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * @author smsj
 */
public class GameViewController implements Initializable {
    public TextField nameInput;
    public AnchorPane nameAnchorPane;
    public AnchorPane contentAnchorPane;
    @FXML
    private ImageView playerChoice;
    @FXML
    private ImageView aiChoice;
    @FXML
    private Label playerNameLbl;
    @FXML
    private Label playerScore;
    @FXML
    private Label aiScore;
    @FXML
    private Label aiNameLbl;

    @FXML
    private MediaView viewer;
    private File file;
    @FXML
    private Media media;
    @FXML
    private MediaPlayer mediaPlayer;

    private IPlayer bot;
    private IPlayer human;
    private GameManager gm;

    private final String ROCK_PATH = "/Resources/Sten.png";
    private final String L_ROCK_PATH = "/Resources/StenL.png";
    private final String PAPER_PATH = "/Resources/Papir.png";
    private final String L_PAPER_PATH = "/Resources/PapirL.png";
    private final String SCISSOR_PATH = "/Resources/Saks.png";
    private final String L_SCISSOR_PATH = "/Resources/SaksL.png";


    public GameViewController() {
        this.human = new Player("human", PlayerType.Human);
        this.bot = new Player("Ai", PlayerType.AI);
        this.gm = new GameManager(human, bot);
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        music();
        startImage();

    }


    public void music() {
        file = new File("Music/Disaster.mp3");
        media = new Media(file.toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        System.out.println(mediaPlayer);
        System.out.println(mediaPlayer.isMute());
    }

    public void moveHand(String imagePath, ImageView hand, ImageView playerImageView) {
        Circle circle = new Circle(12);
        PathTransition transition = new PathTransition();

        startImage();

        transition.setNode(hand);
        transition.setDuration(Duration.seconds(0.3));
        transition.setPath(circle);
        transition.setCycleCount(3);
        transition.play();
        transition.setOnFinished(event -> {
            changeImage(imagePath,playerImageView);
        });
    }

    public void startImage() {
        Image image = new Image(ROCK_PATH);
        Image imageL = new Image(L_ROCK_PATH);
        playerChoice.setImage(image); // default img
        aiChoice.setImage(imageL);
    }

    private void handleAIHand() {
        ArrayList<Result> results = (ArrayList<Result>) gm.getGameState().getHistoricResults();
        Result latestResult = results.get(results.size() - 1);

        Move aiMove = null;
        //ai win
        if (latestResult.getWinnerPlayer().getPlayerType() == PlayerType.AI) {
            aiMove = latestResult.getWinnerMove();
        }

        //player win
        if (latestResult.getWinnerPlayer().getPlayerType() == PlayerType.Human) {
            aiMove = latestResult.getLoserMove();
        }

        //tie
        if (latestResult.getType() == ResultType.Tie) {
            aiMove = latestResult.getLoserMove(); //test
        }

        System.out.println(aiMove);
        String imagePath = "";
        switch (aiMove){
            case Paper ->imagePath = L_PAPER_PATH;
            case Rock ->imagePath = L_ROCK_PATH;
            case Scissor ->imagePath = L_SCISSOR_PATH;
        }

        moveHand(imagePath,aiChoice,aiChoice);
    }

    public void changeImage(String imagePath, ImageView playerImageView) {
        Image chosen = new Image(imagePath);
        playerImageView.setImage(chosen);

    }


    public void handleStoneClicked(MouseEvent mouseEvent) {
        moveHand(ROCK_PATH, playerChoice,playerChoice);
        gm.playRound(Move.Rock);
        handleAIHand();
    }

    public void handleScissorClicked(MouseEvent mouseEvent) {
        moveHand(SCISSOR_PATH, playerChoice,playerChoice);
        gm.playRound(Move.Scissor);
        handleAIHand();
    }


    public void handlePaperClicked(MouseEvent mouseEvent) {
        moveHand(PAPER_PATH, playerChoice,playerChoice);
        gm.playRound(Move.Paper);
        handleAIHand();
    }

    public void handleDoneClicked(ActionEvent actionEvent) {
        String name = nameInput.getText().trim();
        playerNameLbl.setText(name);
        if (!name.isEmpty()) {
            contentAnchorPane.getChildren().remove(nameAnchorPane);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Enter a name");
            alert.showAndWait();
        }

    }


}

