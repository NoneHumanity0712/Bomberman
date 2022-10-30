package uet.oop.stages;

import java.io.FileNotFoundException;
import java.util.Objects;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import uet.oop.gameprocess.HandleImage;

public class GameoverStage extends Stage implements HandleImage{
    MediaPlayer gameover;
    Image gameoverImage;
    ImageView gameoverView;
    public Button replay;
    Group root;
    Scene scene;

    public GameoverStage() throws FileNotFoundException{ 
        gameover = new MediaPlayer(
                new Media(Objects.requireNonNull(getClass().getResource("/sound/gameover.mp3")).toString()));
        
        gameoverImage = getImage("gameover.png", "banners");
        gameoverView = new ImageView(gameoverImage);

        replay = new Button("REPLAY");
        replay.setPrefSize(150, 30);
        replay.setLayoutX(gameoverImage.getWidth()/2 - 150/2);
        replay.setLayoutY(150);
        replay.setStyle("-fx-text-fill: #38393D; -fx-font: 18 Consolas;");

        root = new Group(gameoverView, replay);

        scene =  new Scene(root, gameoverImage.getWidth(), gameoverImage.getHeight());

        this.setScene(scene);
        this.getIcons().add(getImage("logo.png", "banners"));
        
        gameover.play();
    }
}
