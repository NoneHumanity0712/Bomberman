package uet.oop;

import java.io.FileNotFoundException;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import uet.oop.gameprocess.HandleImage;

public class StartStage extends Stage implements HandleImage{

    public Button startButton;
    public Button exitButton;
    public Button howtoplayButton;

    Font font = new Font("Consolas", 21);

    Group root;

    Scene scene;

    ScrollPane howtoplayPane;
    Stage subStage;
    Scene subScene;

    public StartStage() throws FileNotFoundException {
        Image image = getImage("startmenu.png", "banners");
        ImageView imageView = new ImageView(image);

        startButton = new Button("Start Game");
        startButton.setLayoutX(150);
        startButton.setLayoutY(250);

        exitButton = new Button("Exit");
        exitButton.setLayoutX(150);
        exitButton.setLayoutY(310);

        howtoplayButton = new Button("How To Play");
        howtoplayButton.setLayoutX(150);
        howtoplayButton.setLayoutY(370);

        root = new Group(imageView, startButton, exitButton, howtoplayButton);

        root.getChildren().forEach(button -> {
            if (button instanceof Button) {
                ((Button) button).setPrefSize(200, 40);
                ((Button) button).setFont(font);
                ((Button) button).setStyle("-fx-text-fill: #38393D;");
            }
        });
        scene = new Scene(root, 500, 500, Color.valueOf("EAEAEA"));

        this.setScene(scene);
        this.setTitle("MENU");

        this.getIcons().add(getImage("logo.png", "banners"));
    }

    public void howtoplay() throws FileNotFoundException {
        Stage subStage = new Stage();

        Image howtoplay_image = getImage("how_to_play.png", "banners");
        ImageView howtoplayImageView = new ImageView(howtoplay_image);

        Button backButton = new Button("Back");
        backButton.setLayoutX(210);
        backButton.setLayoutY(1120);
        backButton.setPrefSize(100, 30);
        backButton.setFont(font);
        backButton.setStyle("-fx-text-fill: #38393D;");
        backButton.focusTraversableProperty().set(false);

        Pane root = new Pane(howtoplayImageView, backButton);

        howtoplayPane = new ScrollPane(root);
        howtoplayPane.pannableProperty().set(true);
        howtoplayPane.setMaxSize(500, 1200);
        howtoplayPane.setHbarPolicy(ScrollBarPolicy.AS_NEEDED);
        howtoplayPane.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);

        subStage.setTitle("How to play Bomberman");

        Scene subScene = new Scene(howtoplayPane, 520, 700, Color.valueOf("EAEAEA"));
        subStage.setScene(subScene);
        subStage.getIcons().add(getImage("logo.png", "banners"));
        subStage.show();

        backButton.setOnAction(e -> {
            subStage.close();
        });
    }

}