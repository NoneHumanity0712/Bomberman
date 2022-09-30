package uet.oop;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class demo extends Application{
    public static void main(String[] args) throws FileNotFoundException {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FileInputStream inputstream = new FileInputStream("C:\\Users\\HP\\Downloads\\bqcuong bomberman-starter starter-2 res\\demo.png");
        Image demoImage = new Image(inputstream);

        ImageView imageView = new ImageView(demoImage);
        imageView.setX(50);
        imageView.setY(50);

        imageView.setFitHeight(722);
        imageView.setFitWidth(697);

        imageView.setPreserveRatio(true);

        Group root = new Group(imageView);

        Scene scene = new Scene(root, 800, 800);

        primaryStage.setTitle("DEMO");
        primaryStage.setScene(scene);

        primaryStage.show();
    }

    
}
