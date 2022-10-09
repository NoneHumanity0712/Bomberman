package uet.oop;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class demo extends Application{

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root, 1200, 720);

        primaryStage.setScene(scene);
        primaryStage.setTitle("DEMO");

        primaryStage.show();
    }

}
