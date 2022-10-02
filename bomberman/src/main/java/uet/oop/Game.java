package uet.oop;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.event.Event;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class Game {
    private int level;
    private long score;
    private boolean GameOver;
    private boolean QuitGame;

    private boolean BombPlace;

    private Map gameMap;
    private Bomber bomber;
    private Enemy enemy;

    private Image grassImage;
    private Image wallImage;
    private Image brickImage;
    private Image[] bombImage;
    private Image[] bomberImage;
    public Group imageGroup;

    public final int pixel = 16 * 3;

    public Game() throws FileNotFoundException {
        level = 1;
        score = 0;
        GameOver = false;
        QuitGame = false;
        BombPlace = false;

        imageGroup = new Group();
    }

    public Game(Map map) throws FileNotFoundException {
        this.gameMap = map;
        this.bomber = map.bomber;
        this.enemy = map.enemy;
        imageGroup = new Group();

        level = 1;
        score = 0;
        GameOver = false;
        QuitGame = false;
        BombPlace = false;
    }

    public void setBombImage(Image[] bombImage) {
        this.bombImage = bombImage;
    }

    public void setBrickImage(Image brickImage) {
        this.brickImage = brickImage;
    }

    public void setBomberImage(Image[] bomberImage) {
        this.bomberImage = bomberImage;
    }

    public void setGrassImage(Image grassImage) {
        this.grassImage = grassImage;
    }

    public void setWallImage(Image wallImage) {
        this.wallImage = wallImage;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public boolean isGameOver() {
        return GameOver;
    }

    public boolean isQuitGame() {
        return QuitGame;
    }

    public boolean isBombPlace() {
        return BombPlace;
    }

    public void setBombPlace(boolean bombPlace) {
        BombPlace = bombPlace;
    }

    public void setGameOver(boolean gameOver) {
        GameOver = gameOver;
    }

    private void drawBackground() {
    };

    private void drawMap() {
        for (int i = 0; i < gameMap.getRow(); i++) {
            for (int j = 0; j < gameMap.getColumn(); j++) {
                if (gameMap.getMap()[i][j] == '#') {
                    ImageView wallView = new ImageView(wallImage);

                    wallView.setFitHeight(pixel);
                    wallView.setFitWidth(pixel);
                    wallView.setPreserveRatio(true);

                    wallView.setY(i * pixel);
                    wallView.setX(j * pixel);

                    imageGroup.getChildren().add(wallView);
                } else if (gameMap.getMap()[i][j] == '*') {
                    ImageView brickView = new ImageView(brickImage);

                    brickView.setFitHeight(pixel);
                    brickView.setFitWidth(pixel);
                    brickView.setPreserveRatio(true);

                    brickView.setY(i * pixel);
                    brickView.setX(j * pixel);

                    imageGroup.getChildren().add(brickView);
                } else {
                    ImageView grassView = new ImageView(grassImage);

                    grassView.setFitHeight(pixel);
                    grassView.setFitWidth(pixel);
                    grassView.setPreserveRatio(true);

                    grassView.setY(i * pixel);
                    grassView.setX(j * pixel);

                    imageGroup.getChildren().add(grassView);
                }
            }
        }
    };

    private void drawMovingEntity() {
    };

    private void drawBomb() {
    }

    public void drawScene() {
        drawBackground();
        drawMap();
        drawMovingEntity();
        if (isBombPlace())
            drawBomb();
    };

    public void handle(Event e) throws IOException {
        if (e instanceof KeyEvent) {
            switch (((KeyEvent) e).getCode()) {
                case RIGHT:
                    bomber.MOVE_RIGHT(gameMap);
                    bomber.setDirection(0);
                    break;
                case DOWN:
                    bomber.MOVE_DOWN(gameMap);
                    bomber.setDirection(1);
                    break;
                case LEFT:
                    bomber.MOVE_LEFT(gameMap);
                    bomber.setDirection(2);
                    break;
                case UP:
                    bomber.MOVE_UP(gameMap);
                    bomber.setDirection(3);
                    break;

                case ESCAPE:
                    QuitGame = true;
                    break;
                case SPACE:
                    bomber.placeBomb(gameMap);
                    break;
                default:
                    break;
            }
        } else if (e instanceof MouseEvent) {
        }
        ;
    }

}
