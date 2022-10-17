package uet.oop;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.stage.Stage;
import uet.oop.gameprocess.Game;
import uet.oop.gameprocess.GameCanvas;
import uet.oop.gameprocess.HandleImage;
import uet.oop.gameprocess.Map;
import uet.oop.gameprocess.ReadFromFile;

public class Main extends Application implements HandleImage {

    /**
     * launch game.
     * 
     * @param args String[]
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        File input = new File("src/main/java/uet/oop/level1.txt");
        Scanner scanner;
        try {
            scanner = new Scanner(input);
            ReadFromFile readFromFile = new ReadFromFile();
            if (scanner.hasNext()) {
                readFromFile.readFile(scanner);
            }

            Map map = new Map();
            map.setRow(readFromFile.getRow_read());
            map.setColumn(readFromFile.getColumn_read());
            map.setMap(readFromFile.getMap_read());

            GameCanvas canvas = new GameCanvas();

            Game bombermanGame = new Game(map, canvas);
            bombermanGame.setLevel(readFromFile.getLevel_read());

            Group root = new Group();
            root.getChildren().add(canvas);

            Scene scene = new Scene(root);

            primaryStage.setTitle("BOMBERMAN");

            primaryStage.setScene(scene);

            primaryStage.show();

            AnimationTimer timer = new AnimationTimer() {

                @Override
                public void handle(long now) {
                    if (now - bombermanGame.getBefore() > 2e6) {
                        bombermanGame.handle();
                        bombermanGame.update();
                        bombermanGame.drawScene();
                    }
                }
            };

            timer.start();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
