package uet.oop;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javafx.scene.Group;
import javafx.scene.Scene;
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
import uet.oop.stages.GameoverStage;
import uet.oop.stages.PassLevelStage;
import uet.oop.stages.StartStage;
import uet.oop.stages.WinStage;

public class Main extends Application implements HandleImage {

    Game bombermanGame;
    boolean isMuteSound = false;
    boolean isLightMode = true;
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
        primaryStage.getIcons().add(getImage("logo.png", "banners"));

        StartStage startMenu = new StartStage();
        startMenu.show();

        startMenu.setOnCloseRequest(e -> Platform.exit());

        startMenu.howtoplayButton.setOnAction(e -> {
            try {
                startMenu.howtoplay();
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            }
        });

        startMenu.startButton.setOnAction(e -> {
            System.out.println("Start Game");
            isMuteSound = !startMenu.sound.isState();
            isLightMode = startMenu.theme.isState();
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

            bombermanGame = new Game(maps, canvas, isMuteSound, isLightMode);
            System.out.println("main:" + isLightMode);
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

                            try {
                                toNextLevel(primaryStage);
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            }
                        }

                        if (bombermanGame.isQuitGame()) {
                            System.out.println("Quit Game");
                            Platform.exit();
                        }
                    } else {
                        this.pause();

                        try {
                            gameOver(primaryStage);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                }

            };
            gameTimer.start();

        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
    }

    public void gameOver(Stage primaryStage) throws FileNotFoundException {

        GameoverStage gameoverStage = new GameoverStage();
        gameoverStage.show();

        gameoverStage.replay.setOnAction(e -> {
            try {
                start(new Stage());
                gameoverStage.close();
                primaryStage.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }

        });

        gameoverStage.setOnCloseRequest(e -> {
            Platform.exit();
            System.exit(0);
        });
    }

    public void toNextLevel(Stage primaryStage) throws FileNotFoundException {
        try {
            bombermanGame.update();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        bombermanGame.drawScene();

        MediaPlayer passlevel = new MediaPlayer(
                new Media(Objects.requireNonNull(getClass().getResource("/sound/nextlevel.mp3")).toString()));

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
            MediaPlayer wingame = new MediaPlayer(
                    new Media(Objects.requireNonNull(getClass().getResource("/sound/wingame.mp3")).toString()));

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

            winStage.setOnCloseRequest(e -> Platform.exit());
        }
    }
}
