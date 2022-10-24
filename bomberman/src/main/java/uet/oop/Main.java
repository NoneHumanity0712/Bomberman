package uet.oop;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
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
            startGame(primaryStage);
        });
    }

    public void startGame(Stage primaryStage) {
        List<ReadFromFile> maplevels = new ArrayList<>();
        try {
            List<Map> maps = new ArrayList<>();

            for (int i = 0; i < 5; i++) {
                ReadFromFile maplevel = new ReadFromFile();
                maplevel.readFile(new File("src/main/resources/levels/level" + String.valueOf(i + 1) + ".txt"));
                maplevels.add(maplevel);

                Map map = new Map(maplevel.getRow_read(), maplevel.getColumn_read(), maplevel.getMap_read());
                maps.add(map);
            }

            GameCanvas canvas = new GameCanvas(maps.get(0).getColumn() * Entity.size,
                    maps.get(0).getRow() * Entity.size + 100);

            bombermanGame = new Game(maps, canvas);
            bombermanGame.setLevel(maplevels.get(0).getLevel_read());

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
                            toNextLevel(primaryStage);
                        }

                        if (bombermanGame.isQuitGame()) {
                            System.out.println("Quit Game");
                            Platform.exit();
                        }
                    } else {
                        this.pause();
                        gameOver(primaryStage);
                    }
                }

            };
            gameTimer.start();

        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
    }

    public void gameOver(Stage primaryStage) {
        MediaPlayer gameover = new MediaPlayer(new Media(getClass().getResource("/sound/gameover.mp3").toString()));

        Image gameoverImage = new Image("file:src/main/resources/banners/gameover.png");
        ImageView gameoverView = new ImageView(gameoverImage);

        Button replay = new Button("REPLAY");
        replay.setPrefSize(150, 30);
        replay.setLayoutX(175);
        replay.setLayoutY(150);
        replay.setStyle("-fx-text-fill: #38393D; -fx-font: 18 Consolas;");

        Group root = new Group(gameoverView, replay);

        Stage subStage = new Stage();
        Scene subScene = new Scene(root, gameoverImage.getWidth(), gameoverImage.getHeight());

        subStage.setScene(subScene);
        subStage.show();
        gameover.play();

        replay.setOnAction(e -> {
            try {
                start(new Stage());
                subStage.close();
                primaryStage.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }

        });

        subStage.setOnCloseRequest(e -> {
            Platform.exit();
            System.exit(0);
        });
    }

    public void toNextLevel(Stage primaryStage) {
        try {
            bombermanGame.update();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        bombermanGame.drawScene();

        MediaPlayer passlevel = new MediaPlayer(new Media(getClass().getResource("/sound/nextlevel.mp3").toString()));

        if (bombermanGame.getMaps().size() >= bombermanGame.getLevel() + 1) {

            PassLevelStage passLevelStage = new PassLevelStage("Score: " + bombermanGame.getScore());
            passLevelStage.show();
            passlevel.play();

            passLevelStage.toNextLevel.setOnAction(e -> {
                passLevelStage.close();
                bombermanGame.toNextLevel();
                gameTimer.play();
            });

            passLevelStage.setOnCloseRequest(e -> {
                Platform.exit();
                System.exit(0);
            });
        } else {
            MediaPlayer wingame = new MediaPlayer(new Media(getClass().getResource("/sound/wingame.mp3").toString()));

            WinStage winStage = new WinStage();
            winStage.show();
            wingame.play();

            winStage.replay.setOnAction(e -> {
                try {
                    start(new Stage());
                    winStage.close();
                    primaryStage.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            });
        }
    }
}
