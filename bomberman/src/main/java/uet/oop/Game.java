package uet.oop;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import uet.oop.demogame.GameCanvas;
import uet.oop.entities.Balloom;
import uet.oop.entities.Bomb;
import uet.oop.entities.Bomber;
import uet.oop.entities.Enemy;
import uet.oop.entities.Entity;
import uet.oop.entities.Portal;

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
    public GameCanvas gameCanvas;

    private Image grassImage;
    private Image wallImage;
    private Image brickImage;
    private Image portalImage;

    public Game() throws FileNotFoundException {
        level = 1;
        score = 0;
        GameOver = false;
        QuitGame = false;
        BombPlace = false;

        gameMap = new Map();
        gameCanvas = new GameCanvas();
        gameCanvas.setPlayer(bomber);
        enemies = new ArrayList<Enemy>();
    }

    public Game(Map map, GameCanvas canvas) throws FileNotFoundException {
        this.gameMap = new Map(map);
        this.bomber = gameMap.bomber;
        this.portal = gameMap.portal;
        this.enemies = new ArrayList<Enemy>(map.getEnemy());

        this.gameCanvas = canvas;
        gameCanvas.setPlayer(bomber);

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

    private void setPortalImage(Image image) {
        this.portalImage = image;
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

    public List<Enemy> getEnemies() {
        return enemies;
    }

    public void drawMap() {
        for (int i = 0; i < gameMap.getRow(); i++) {
            for (int j = 0; j < gameMap.getColumn(); j++) {
                if (gameMap.getMap()[i][j] == '#') {

                    gameCanvas.context.drawImage(wallImage, j * Entity.size, i * Entity.size, Entity.size, Entity.size);

                } else if (gameMap.getMap()[i][j] == '*') {

                    gameCanvas.context.drawImage(brickImage, j * Entity.size, i * Entity.size, Entity.size,
                            Entity.size);
                } else {
                    gameCanvas.context.drawImage(grassImage, j * Entity.size, i * Entity.size, Entity.size,
                            Entity.size);
                }
            }
        }
    };

    public void drawMovingEntities() {
        gameCanvas.context.drawImage(bomber.getImage(), bomber.getOldX() * Entity.size, bomber.getOldY() * Entity.size,
                Entity.size, Entity.size);

        for (Enemy enemy : enemies) {
            gameCanvas.context.drawImage(enemy.getImage(), enemy.getOldX() * Entity.size, enemy.getOldY() * Entity.size,
                    Entity.size, Entity.size);
        }
    }

    private void drawBomb() {

    }

    public void drawScene() {
        drawBackground();
        drawMap();
        drawMovingEntities();
        if (isBombPlace()) {
            drawBomb();
        }
    };

    public void update() {
        bomber.update(gameMap);
        for (Enemy enemy : enemies) {
            enemy.update(gameMap);
        }
    }

    public void handle() {
        gameCanvas.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case RIGHT:
                case D:
                    if (bomber.legal_move(gameMap, bomber.getY(), bomber.getX() + 1)) {
                        bomber.setDirection(0);
                        bomber.setOldX(bomber.getDoubleX());
                        bomber.setDoubleX(bomber.getDoubleX() + 1);
                        bomber.setMoving(true);
                    }
                    break;
                case DOWN:
                case S:
                    if (bomber.legal_move(gameMap, bomber.getY() + 1, bomber.getX())) {
                        bomber.setDirection(1);
                        bomber.setOldY(bomber.getDoubleY());
                        bomber.setDoubleY(bomber.getDoubleY() + 1);
                        bomber.setMoving(true);
                    }
                    break;
                case LEFT:
                case A:
                    if (bomber.legal_move(gameMap, bomber.getY(), bomber.getX() - 1)) {
                        bomber.setDirection(2);
                        bomber.setOldX(bomber.getDoubleX());
                        bomber.setDoubleX(bomber.getDoubleX() - 1);
                        bomber.setMoving(true);
                    }
                    break;
                case UP:
                case W:
                    if (bomber.legal_move(gameMap, bomber.getY() - 1, bomber.getX())) {
                        bomber.setDirection(3);
                        bomber.setOldY(bomber.getDoubleY());
                        bomber.setDoubleY(bomber.getDoubleY() - 1);
                        bomber.setMoving(true);
                    }
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
        });
        for (Enemy enemy : enemies) {
            if (enemy instanceof Balloom) {
                long now = System.currentTimeMillis();
                if (now - ((Balloom) enemy).getTimeBefore() > 1500) {
                    ((Balloom) enemy).MOVE(gameMap);
                }
                
            }
        }
    }
}
