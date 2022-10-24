package uet.oop;

import java.io.FileNotFoundException;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import uet.oop.gameprocess.HandleImage;

public class PassLevelStage extends Stage implements HandleImage{

    Image passlevelImage;
    ImageView passlevelView;
    public Button toNextLevel;
    Text score;
    Group root;
    Scene passlevelScene;

    public PassLevelStage(String scores) throws FileNotFoundException {
        passlevelImage = getImage("nextlevel.png", "banners");
        passlevelView = new ImageView(passlevelImage);

        toNextLevel = new Button("Next Level");
        toNextLevel.setPrefSize(150, 30);
        toNextLevel.setLayoutX(75);
        toNextLevel.setLayoutY(150);
        toNextLevel.setStyle("-fx-text-fill: #38393D; -fx-font: 18 Consolas;");

        score = new Text(scores);
        score.setTextAlignment(TextAlignment.CENTER);
        score.setX(120 - score.getLayoutBounds().getWidth() / 2);
        score.setY(140);

        score.setStyle("-fx-text-fill: #38393D; -fx-font: 21 Consolas;");

        root = new Group(passlevelView, toNextLevel, score);

        passlevelScene = new Scene(root, 300, 200, Color.valueOf("EAEAEA"));

        this.setScene(passlevelScene);
        this.setTitle("Pass Level");
        this.getIcons().add(getImage("logo.png", "banners"));
    }
}
