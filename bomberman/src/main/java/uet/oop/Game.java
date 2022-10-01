package uet.oop;

import java.io.IOException;

import javafx.event.Event;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class Game {
    private int level;
    private long score;
    private boolean GameOver;
    private boolean QuitGame;

    private Map gameMap;
    private Bomber bomber;

    private Image grassImage;
    private Image wallImage;
    private Image brickImage;
    private Image[] bombImage;
    private Image[] bomberImage;

    public Game() {
        level = 1;
        score = 0;
        GameOver = false;
        QuitGame = false;
        gameMap = new Map();
        bomber = new Bomber();

        grassImage = new Image("sprites/grass.png");
        wallImage = new Image("sprites/wall.png");
        brickImage = new Image("sprites/brick.png");

        bombImage = new Image[2];
        bombImage[0] = new Image("sprites/bomb.png");
        bombImage[1] = new Image("sprites/bomb_exploded.png");

        bomberImage = new Image[4];
        bomberImage[0] = new Image("sprites/player_right.png");
        bomberImage[1] = new Image("sprites/player_down.png");
        bomberImage[2] = new Image("sprites/player_left.png");
        bomberImage[3] = new Image("sprites/player_up.png");

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

    public void setGameOver(boolean gameOver) {
        GameOver = gameOver;
    }

    public void drawBackground() {
    };

    public void drawMap() {
    };

    public void drawMovingEntity() {
    };

    public void drawScene() {
        drawBackground();
        drawMap();
        drawMovingEntity();
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
