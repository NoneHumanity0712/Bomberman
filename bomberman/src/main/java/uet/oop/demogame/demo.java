package uet.oop.demogame;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class demo extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        GameCanvas game = new GameCanvas();
        
        Group root = new Group(game);

        Scene scene = new Scene(root, game.getWidth(), game.getHeight(), Color.BLANCHEDALMOND);

        primaryStage.setScene(scene);
        primaryStage.setTitle("DEMO");

        primaryStage.show();

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                                
                game.render();
                
            }
            
        };
        timer.start();
    }
}
