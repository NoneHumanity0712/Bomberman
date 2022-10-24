package uet.oop;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class WinStage extends Stage{
    Image winGameImage;
    ImageView winGameView;
    public Button replay;
    Group root;
    Scene wingameScene;

    public WinStage() {
        winGameImage = new Image("file:src/main/resources/banners/win_game.png");
        winGameView = new ImageView(winGameImage);

        replay = new Button("REPLAY");
        replay.setPrefSize(150, 30);
        replay.setLayoutX(125);
        replay.setLayoutY(250);
        replay.setStyle("-fx-text-fill: #38393D; -fx-font: 18 Consolas;");

        root = new Group(winGameView, replay);

        wingameScene = new Scene(root, winGameImage.getWidth(), winGameImage.getHeight(), Color.valueOf("EAEAEA"));

        this.setScene(wingameScene);
    }
}
