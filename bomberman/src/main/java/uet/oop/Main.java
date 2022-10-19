package uet.oop;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import uet.oop.entities.Entity;
import uet.oop.gameprocess.Game;
import uet.oop.gameprocess.GameCanvas;
import uet.oop.gameprocess.GameLoopTimer;
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

        StartStage startMenu = new StartStage();
        startMenu.show();

        startMenu.quitButton.setOnAction(e -> {
            System.out.println("Quit Game");
            Platform.exit();
        });

        startMenu.startButton.setOnAction(e -> {
            System.out.println("Start Game");
            startMenu.close();

            ReadFromFile maplevel1 = new ReadFromFile();
            try {
                maplevel1.readFile(new File("src/main/java/uet/oop/level1.txt"));

                Map map = new Map();
                map.setRow(maplevel1.getRow_read());
                map.setColumn(maplevel1.getColumn_read());
                map.setMap(maplevel1.getMap_read());

                GameCanvas canvas = new GameCanvas(map.getColumn() * Entity.size + 100, map.getRow() * Entity.size + 100);

                Game bombermanGame = new Game(map, canvas);
                bombermanGame.setLevel(maplevel1.getLevel_read());

                Group root = new Group();
                root.getChildren().add(canvas);

                Scene scene = new Scene(root);

                primaryStage.setTitle("BOMBERMAN");

                primaryStage.setScene(scene);

                primaryStage.show();

                GameLoopTimer gameTimer = new GameLoopTimer() {

                    @Override
                    public void tick(float secondsSinceLastFrame) {
                        bombermanGame.handle();

                        try {
                            bombermanGame.update();
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }

                        bombermanGame.drawScene();

                        if (bombermanGame.isQuitGame()) {
                            System.out.println("Quit Game");
                            Platform.exit();
                        }
                    }

                };
                gameTimer.start();


                // AnimationTimer timer = new AnimationTimer() {

                // @Override
                // public void handle(long now) {
                // if (now - bombermanGame.getBefore() > 2e6 && !bombermanGame.isPauseGame()) {

                // bombermanGame.handle();

                // try {
                // bombermanGame.update();
                // } catch (FileNotFoundException e) {
                // e.printStackTrace();
                // }

                // bombermanGame.drawScene();

                // if (bombermanGame.isQuitGame()) {
                // System.out.println("Quit Game");
                // Platform.exit();
                // }

                // }
                // }
                // };

                // timer.start();
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            }
        });
    }
}
