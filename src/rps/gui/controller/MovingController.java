package rps.gui.controller;

import javafx.animation.PathTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class MovingController implements Initializable {
    @FXML
    private ImageView movingHand;

    Image casting = new Image("/Resources/Sten.png");



    public MovingController() {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        startImage();

    }

    public void moveHand(String imagePath) {

        Circle circle = new Circle(12);


        startImage();
        PathTransition transition = new PathTransition();
        transition.setNode(movingHand);
        transition.setDuration(Duration.seconds(0.3));
        transition.setPath(circle);
        transition.setCycleCount(3);
        transition.play();

        transition.setOnFinished(event -> {
            changeImage(imagePath);
        });


    }

    private void setPlayerScore(){

    }

    public void startImage() {
        movingHand.setImage(casting);
    }

    public void changeImage(String imagePath) {
        Image chosen = new Image(imagePath);
        movingHand.setImage(chosen);
    }

    public void handleSten(MouseEvent mouseEvent) {
        moveHand("/Resources/Sten.png");
    }

    public void handleSaks(MouseEvent mouseEvent) {
        moveHand("/Resources/Saks.png");
    }

    public void handlePapir(MouseEvent mouseEvent) {
        moveHand("/Resources/Papir.png");
    }
}
