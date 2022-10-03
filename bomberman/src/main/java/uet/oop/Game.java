package uet.oop;

import java.io.FileNotFoundException;
import java.io.IOException;

import javafx.event.Event;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class Game implements HandleImage {
    private int level;
    private long score;
    private boolean GameOver;
    private boolean QuitGame;

    private boolean BombPlace;

    private Map gameMap;
    private Bomber bomber;
    private Enemy enemy;
    public Bomb bomb;

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
        this.gameMap = new Map(map);
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

                    ImageView wallView = createView(wallImage, pixel, i * pixel, j * pixel);
                    imageGroup.getChildren().add(wallView);

                } else if (gameMap.getMap()[i][j] == '*') {

                    ImageView brickView = createView(brickImage, pixel, i * pixel, j * pixel);
                    imageGroup.getChildren().add(brickView);
                } else {

                    ImageView grassView = createView(grassImage, pixel, i * pixel, j * pixel);
                    imageGroup.getChildren().add(grassView);
                }
            }
        }
    };

    private void drawMovingEntity() {
        ImageView bomberView = createView(bomberImage[bomber.getDirection()],
                pixel, bomber.getY() * pixel, bomber.getX() * pixel);
        imageGroup.getChildren().add(bomberView);
    }

    private void drawBomb() {
        if (!bomb.isExplode()) {
            ImageView bombView = createView(bombImage[0], pixel,
                    bomb.getY() * pixel, bomb.getX() * pixel);
            imageGroup.getChildren().add(bombView);
            bomb.Explode(gameMap);
        } else {
            setBombPlace(false);

            ImageView bombView = createView(bombImage[1], pixel,
                    bomb.getY() * pixel, bomb.getX() * pixel);
            imageGroup.getChildren().add(bombView);

        }

    }

    public void drawScene() {
        drawBackground();
        drawMap();
        drawMovingEntity();
        if (isBombPlace()){
            drawBomb();
        }
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
                    bomb = new Bomb(bomber.placeBomb(gameMap));
                    BombPlace = true;
                    break;
                default:
                    break;
            }
        } else if (e instanceof MouseEvent) {
        }
        ;
    }

}
