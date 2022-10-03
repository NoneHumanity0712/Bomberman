package uet.oop;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class demo extends Application implements HandleImage{
    public static void main(String[] args) throws FileNotFoundException {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FileInputStream inputstream = new FileInputStream("sprites/player_down.png");
        Image demoImage = new Image(inputstream);
        Image newImage = makeTransparent(demoImage);

        ImageView imageView = new ImageView(newImage);
        imageView.setX(0);
        imageView.setY(0);

        imageView.setFitHeight(48);
        imageView.setFitWidth(48);

        imageView.setPreserveRatio(true);

        Group root = new Group(imageView);

        Scene scene = new Scene(root, 50, 50);

        primaryStage.setTitle("DEMO");
        primaryStage.setScene(scene);

        primaryStage.show();
    }

    
}
