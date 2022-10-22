package uet.oop;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class StartStage extends Stage {

    public Button startButton;
    public Button exitButton;
    public Button howtoplayButton;

    Group buttons;

    Scene scene;

    ScrollPane howtoplayPane;
    Stage subStage;
    Scene subScene;

    public StartStage() {

        startButton = new Button("Start Game");
        startButton.setLayoutX(150);
        startButton.setLayoutY(100);

        exitButton = new Button("Exit");
        exitButton.setLayoutX(150);
        exitButton.setLayoutY(200);

        howtoplayButton = new Button("How To Play");
        howtoplayButton.setLayoutX(150);
        howtoplayButton.setLayoutY(300);

        buttons = new Group(startButton, exitButton, howtoplayButton);

        buttons.getChildren().forEach(button -> {
            ((Button) button).setPrefSize(200, 50);
            ((Button) button).setStyle("-fx-text-fill: #38393D; -fx-font: 21 Consolas;");

        });
        scene = new Scene(buttons, 500, 500, Color.LINEN);

        this.setScene(scene);
        this.setTitle("MENU");
    }

    public void howtoplay() {
        Rectangle rect = new Rectangle(500, 1000, Color.MAROON);
        howtoplayPane = new ScrollPane();
        howtoplayPane.setContent(rect);
        howtoplayPane.setMaxSize(500, 1000);
        howtoplayPane.setHbarPolicy(ScrollBarPolicy.AS_NEEDED);
        howtoplayPane.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);

        Stage subStage = new Stage();
        subStage.setTitle("How to play Bomberman");
        Scene subScene = new Scene(howtoplayPane, 520, 700, Color.DARKCYAN);
        subStage.setScene(subScene);
        subStage.show();
    }

}