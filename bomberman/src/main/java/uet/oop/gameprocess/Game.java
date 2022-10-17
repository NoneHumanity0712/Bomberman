package uet.oop.gameprocess;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import uet.oop.entities.Bomb;
import uet.oop.entities.items.Item;
import uet.oop.entities.movingentities.Balloom;
import uet.oop.entities.movingentities.Bomber;
import uet.oop.entities.movingentities.Enemy;

public class Game implements HandleImage {
    private int level;
    private long score;
    private boolean GameOver;
    private boolean QuitGame;

    private Map gameMap;
    private Bomber bomber;

    public AnchorPane gamePane;
    public GameCanvas gameCanvas;

    private Image grassImage;
    private Image wallImage;
    private Image brickImage;

    private long before;

    public Game(Map map, GameCanvas canvas) throws FileNotFoundException {
        this.gameMap = new Map(map);
        this.bomber = gameMap.getBomber();

        this.gameCanvas = canvas;
        gameCanvas.setPlayer(bomber);

        level = 1;
        score = 0;
        GameOver = false;
        QuitGame = false;

        setGrassImage(getImage("sprites/grass.png"));
        setBrickImage(getImage("sprites/brick.png"));
        setWallImage(getImage("sprites/wall.png"));
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

    public void setGameOver(boolean gameOver) {
        GameOver = gameOver;
    }

    public void setBefore(long before) {
        this.before = before;
    }

    public long getBefore() {
        return before;
    }

    private void drawBackground() {

    };

    public void drawMap() {
        for (int i = 0; i < gameMap.getRow(); i++) {
            for (int j = 0; j < gameMap.getColumn(); j++) {
                if (gameMap.getMap()[i][j] == '#') {

                    render(gameCanvas.context, wallImage, j, i);

                } else if (gameMap.getMap()[i][j] == '*') {

                    render(gameCanvas.context, brickImage, j, i);

                } else {

                    render(gameCanvas.context, grassImage, j, i);

                }
            }
        }
    };

    public void drawItem() {
        if (!gameMap.getPortal().isHide()) {
            render(gameCanvas.context, gameMap.getPortal().getImage(), gameMap.getPortal().getX(),
                    gameMap.getPortal().getY());
        }

        for (Item item : gameMap.getItems()) {
            if (!item.isHide()) {
                render(gameCanvas.context, item.getImage(), item.getX(), item.getY());
            }
        }
    }

    public void drawMovingEntities() {
        render(gameCanvas.context, bomber.getImage(), bomber.getOldX(), bomber.getOldY());

        for (Enemy enemy : gameMap.getEnemy()) {
            render(gameCanvas.context, enemy.getImage(), enemy.getOldX(), enemy.getOldY());
        }
    }

    private void drawBomb() {
        for (Bomb bomb : gameMap.getBombs()) {
            render(gameCanvas.context, bomb.getImage(), bomb.getX(), bomb.getY());

            for (Bomb edge : bomb.getEdges()) {
                if (gameMap.getMap()[edge.getY()][edge.getX()] != '#') {
                    if (gameMap.getMap()[edge.getY()][edge.getX()] == '*') {
                        render(gameCanvas.context, bomb.tempBrickImage, edge.getX(), edge.getY());
                    }
                    render(gameCanvas.context, edge.getImage(), edge.getX(), edge.getY());
                }
            }

            for (Bomb flame : bomb.getRightFlames()) {
                render(gameCanvas.context, flame.getImage(), flame.getX(), flame.getY());
            }

            for (Bomb flame : bomb.getDownFlames()) {
                render(gameCanvas.context, flame.getImage(), flame.getX(), flame.getY());
            }

            for (Bomb flame : bomb.getLeftFlames()) {
                render(gameCanvas.context, flame.getImage(), flame.getX(), flame.getY());
            }

            for (Bomb flame : bomb.getUpFlames()) {
                render(gameCanvas.context, flame.getImage(), flame.getX(), flame.getY());
            }
        }
    }

    public void drawScene() {
        gameCanvas.context.clearRect(0, 0, gameCanvas.getWidth(), gameCanvas.getHeight());

        drawBackground();
        drawMap();
        drawItem();
        drawMovingEntities();

        if (!gameMap.getBombs().isEmpty()) {
            drawBomb();
        }
    };

    public void update() {
        bomber.update(gameMap);

        List<Enemy> toRemoveEnemies = new ArrayList<>();
        for (Enemy enemy : gameMap.getEnemy()) {

            enemy.update(gameMap);

            if (!enemy.isAlive()) {
                if (System.currentTimeMillis() - enemy.getTimesincedead() > 100) {
                    toRemoveEnemies.add(enemy);
                }
            }
        }
        gameMap.getEnemy().removeAll(toRemoveEnemies);

        List<Bomb> toRemoveBombs = new ArrayList<>();
        for (Bomb bomb : gameMap.getBombs()) {
            if (!bomb.isExplode() && !bomb.isPlace()) {
                toRemoveBombs.add(bomb);
            } else {
                bomb.update(gameMap);
            }
        }
        gameMap.getBombs().removeAll(toRemoveBombs);

        List<Item> toRemoveItems = new ArrayList<>();
        for (Item item : gameMap.getItems()) {
            if (bomber.getX() == item.getX() && bomber.getY() == item.getY()){
                item.beingReceived(bomber);
            }

            if (item.isReceived()) {
                toRemoveItems.add(item);
            }
        }
        gameMap.getItems().removeAll(toRemoveItems);
    }

    public void handle() {
        gameCanvas.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case RIGHT:
                case D:
                    if (bomber.legal_move(gameMap, bomber.getY(), bomber.getX() + 1)) {
                        bomber.setDirection(0);
                        bomber.setStepCount(0);
                        bomber.setOldX(bomber.getDoubleX());
                        bomber.setDoubleX(bomber.getDoubleX() + 1);
                        bomber.setX((int) bomber.getDoubleX());
                        bomber.setMoving(true);
                    }
                    break;
                case DOWN:
                case S:
                    if (bomber.legal_move(gameMap, bomber.getY() + 1, bomber.getX())) {
                        bomber.setDirection(1);
                        bomber.setStepCount(0);
                        bomber.setOldY(bomber.getDoubleY());
                        bomber.setDoubleY(bomber.getDoubleY() + 1);
                        bomber.setY((int) bomber.getDoubleY());
                        bomber.setMoving(true);
                    }
                    break;
                case LEFT:
                case A:
                    if (bomber.legal_move(gameMap, bomber.getY(), bomber.getX() - 1)) {
                        bomber.setDirection(2);
                        bomber.setStepCount(0);
                        bomber.setOldX(bomber.getDoubleX());
                        bomber.setDoubleX(bomber.getDoubleX() - 1);
                        bomber.setX((int) bomber.getDoubleX());
                        bomber.setMoving(true);
                    }
                    break;
                case UP:
                case W:
                    if (bomber.legal_move(gameMap, bomber.getY() - 1, bomber.getX())) {
                        bomber.setDirection(3);
                        bomber.setStepCount(0);
                        bomber.setOldY(bomber.getDoubleY());
                        bomber.setDoubleY(bomber.getDoubleY() - 1);
                        bomber.setY((int) bomber.getDoubleY());
                        bomber.setMoving(true);
                    }
                    break;

                case ESCAPE:
                    QuitGame = true;
                    break;
                case SPACE:
                    if (bomber.getBombs() > 0) {
                        Bomb bomb = new Bomb(bomber);
                        gameMap.getBombs().add(bomb);
                    }
                    break;
                default:
                    break;
            }
        });
        for (Enemy enemy : gameMap.getEnemy()) {
            if (enemy instanceof Balloom) {
                long now = System.currentTimeMillis();
                if (now - ((Balloom) enemy).getTimeBefore() > 2000) {
                    ((Balloom) enemy).MOVE(gameMap);
                }
            }
        }
        before = System.nanoTime();
    }
}
