package uet.oop;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Menu extends Stage{

    public Button startButton;
    public Button quitButton;

    Group buttons;

    Scene scene;

    public Menu() {

        startButton = new Button("START GAME");
        startButton.setLayoutX(150);
        startButton.setLayoutY(100);

        quitButton = new Button("QUIT GAME");
        quitButton.setLayoutX(150);
        quitButton.setLayoutY(300);

        buttons = new Group(startButton, quitButton);

        buttons.getChildren().forEach(button -> {
            ((Button) button).setPrefSize(200, 50);
            ((Button) button).setStyle("-fx-text-fill: #38393D; -fx-font: 21 Consolas;");

        });
        scene = new Scene(buttons, 500, 500, Color.DARKSLATEGRAY);

        this.setScene(scene);
        this.setTitle("MENU");
    }

}