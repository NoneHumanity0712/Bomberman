package uet.oop;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.event.Event;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class Game implements HandleImage {
    private int level;
    private long score;
    private boolean GameOver;
    private boolean QuitGame;

    private boolean BombPlace;

    private Map gameMap;
    private Bomber bomber;
    private List<Enemy> enemies;
    public Bomb bomb;
    public Portal portal;

    public AnchorPane gamePane;
    public Canvas gameCanvas;
    private GraphicsContext context;

    private Image grassImage;
    private Image wallImage;
    private Image brickImage;
    private Image portalImage;
    private Image[] enemyImage;

    public final int pixel = 16 * 3;

    public Game() throws FileNotFoundException {
        level = 1;
        score = 0;
        GameOver = false;
        QuitGame = false;
        BombPlace = false;

        gameMap = new Map();
        enemies = new ArrayList<Enemy>();
    }

    public Game(Map map, Canvas canvas) throws FileNotFoundException {
        this.gameMap = new Map(map);
        this.bomber = gameMap.bomber;
        this.portal = gameMap.portal;
        this.enemies = new ArrayList<Enemy>(map.getEnemy());

        this.gameCanvas = canvas;
        this.context = gameCanvas.getGraphicsContext2D();

        level = 1;
        score = 0;
        GameOver = false;
        QuitGame = false;
        BombPlace = false;

        setGrassImage(getImage("sprites/grass.png"));
        setBrickImage(getImage("sprites/brick.png"));
        setWallImage(getImage("sprites/wall.png"));
        setPortalImage(getImage("sprites/portal.png"));
    }

    public void setBrickImage(Image brickImage) {
        this.brickImage = brickImage;
    }

    public void setGrassImage(Image grassImage) {
        this.grassImage = grassImage;
    }

    public void setWallImage(Image wallImage) {
        this.wallImage = wallImage;
    }

    public void setPortalImage(Image portalImage) {
        this.portalImage = portalImage;
    }

    public void setEnemyImage(Image[] enemyImage) {
        this.enemyImage = enemyImage;
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

                    // ImageView wallView = createView(wallImage, pixel, i * pixel, j * pixel);
                    // imageGroup.getChildren().add(wallView);

                    render(context, gameCanvas, wallImage, j * pixel, i * pixel);

                } else if (gameMap.getMap()[i][j] == '*') {

                    // ImageView brickView = createView(brickImage, pixel, i * pixel, j * pixel);
                    // imageGroup.getChildren().add(brickView);

                    render(context, gameCanvas, brickImage, j * pixel, i * pixel);
                } else {

                    // ImageView grassView = createView(grassImage, pixel, i * pixel, j * pixel);
                    // imageGroup.getChildren().add(grassView);

                    render(context, gameCanvas, grassImage, j * pixel, i * pixel);
                }
            }
        }
    };

    private void drawMovingEntity() {
        if (!portal.isHide()) {

            render(context, gameCanvas, portalImage, portal.getX() * pixel,
                    portal.getY() * pixel);
        }

        render(context, gameCanvas, bomber.getImage(), bomber.getX(), bomber.getY());

        // for (Enemy enemy : enemies) {
        // if (enemy.getType() == '1') {

        // render(context, gameCanvas, enemyImage[0], enemy.getX() * pixel, enemy.getY()
        // * pixel);
        // }
        // }
    }

    private void drawBomb() {

    }

    public void drawScene() {
        drawBackground();
        drawMap();
        drawMovingEntity();
        if (isBombPlace()) {
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
