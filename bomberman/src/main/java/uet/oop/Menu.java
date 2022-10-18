package uet.oop;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Font;
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
        Font font = new Font(21);

        buttons.getChildren().forEach(button -> {
            ((Button) button).setPrefSize(200, 100);
            ((Button) button).setFont(font);
        });
        scene = new Scene(buttons, 500, 500);

        this.setScene(scene);
        this.setTitle("MENU");
    }

}