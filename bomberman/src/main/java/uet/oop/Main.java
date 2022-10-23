package uet.oop;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
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

    Game bombermanGame;
    GameLoopTimer gameTimer;

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

        startMenu.exitButton.setOnAction(e -> {
            System.out.println("Exit");
            Platform.exit();
        });

        startMenu.howtoplayButton.setOnAction(e -> {
            startMenu.howtoplay();
        });

        startMenu.startButton.setOnAction(e -> {
            System.out.println("Start Game");
            startMenu.close();

            ReadFromFile[] maplevels = new ReadFromFile[3];
            try {
                List<Map> maps = new ArrayList<>();

                for (int i = 0; i < 3; i++) {
                    maplevels[i] = new ReadFromFile();
                    maplevels[i].readFile(new File("src/main/java/uet/oop/level" + String.valueOf(i + 1) + ".txt"));
                    Map map = new Map(maplevels[i].getRow_read(), maplevels[i].getColumn_read(), maplevels[i].getMap_read());
                    maps.add(map);
                }

                GameCanvas canvas = new GameCanvas(maps.get(0).getColumn() * Entity.size + 100,
                    maps.get(0).getRow() * Entity.size + 100);

                bombermanGame = new Game(maps, canvas);
                bombermanGame.setLevel(maplevels[0].getLevel_read());

                Group root = new Group();
                root.getChildren().add(canvas);

                Scene scene = new Scene(root);

                primaryStage.setTitle("BOMBERMAN");

                primaryStage.setScene(scene);

                primaryStage.show();

                gameTimer = new GameLoopTimer() {

                    @Override
                    public void tick(float secondsSinceLastFrame) {
                        if (!bombermanGame.isGameOver()) {
                            bombermanGame.handle();

                            try {
                                bombermanGame.update();
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            }

                            bombermanGame.drawScene();

                            if (bombermanGame.isPassLevel()) {
                                this.pause();
                                toNextLevel();
                            }

                            if (bombermanGame.isQuitGame()) {
                                System.out.println("Quit Game");
                                Platform.exit();
                            }
                        } else {
                            this.pause();
                            gameOver();
                        }
                    }

                };
                gameTimer.start();

            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            }
        });
    }

    public void gameOver() {
        Text text = new Text("GAME OVER");
        text.setStyle("-fx-text-fill: #38393D; -fx-font: 21 Consolas;");
        text.setX(150);
        text.setY(70);

        Group root = new Group();
        root.getChildren().addAll(text);

        Stage subStage = new Stage();
        Scene subScene = new Scene(root, 400, 150);

        subStage.setScene(subScene);
        subStage.show();
    }

    public void toNextLevel() {
        try {
            bombermanGame.update();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        bombermanGame.drawScene();

        if (bombermanGame.getMaps().size() >= bombermanGame.getLevel()) {

            Button toNextLevel = new Button("Next Level");
            toNextLevel.setPrefSize(150, 50);
            toNextLevel.setLayoutX(75);
            toNextLevel.setLayoutY(75);
            toNextLevel.setStyle("-fx-text-fill: #38393D; -fx-font: 21 Consolas;");

            Stage subStage = new Stage();

            Group root = new Group(toNextLevel);

            Scene subScene = new Scene(root, 300, 200, Color.BLUEVIOLET);
            subStage.setScene(subScene);

            subStage.show();

            toNextLevel.setOnAction(e -> {
                subStage.close();

                bombermanGame.toNextLevel();
                gameTimer.play();
            });
        }
    }
}
