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
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class Main extends Application implements HandleImage {

    private Canvas canvas;

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

            canvas = new Canvas(1200, 720);

            Game bombermanGame = new Game(map, canvas);
            bombermanGame.setLevel(readFromFile.getLevel_read());

            Group root = new Group();
            root.getChildren().add(canvas);

            Scene scene = new Scene(root);

            scene.setOnKeyPressed(event -> {
                try {
                    bombermanGame.handle(event);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            primaryStage.setTitle("BOMBERMAN");

            primaryStage.setScene(scene);

            primaryStage.show();

            AnimationTimer timer = new AnimationTimer() {

                @Override
                public void handle(long now) {
                    if (!bombermanGame.isQuitGame() & !bombermanGame.isGameOver()) {
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
