package uet.oop.entities;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import uet.oop.entities.items.Item;
import uet.oop.entities.movingentities.Bomber;
import uet.oop.entities.movingentities.enemies.Enemy;
import uet.oop.gameprocess.Map;

public class Bomb extends Entity {
    private boolean place;
    private boolean explode;
    private int range;

    private long time_since_placed;
    private long time_since_exploded;

    private int placedState;
    private int explodedState;

    private Image[][] bomb_images;
    private Image[][] edge_images;
    private Image[][] flame_images;
    private Image[] explodedbrickImage = new Image[3];
    public Image tempBrickImage;

    private MediaPlayer bomb_ticking;
    private MediaPlayer bomb_exploding;

    private Bomb[] edges;
    private List<Bomb> rightFlames;
    private List<Bomb> downFlames;
    private List<Bomb> leftFlames;
    private List<Bomb> upFlames;

    private long timebeforeeachframe;

    /**
     * Create flame from bomb.
     * 
     * @param x                 x-coordinate of the flame
     * @param y                 y-coordinate of the flame
     * @param place             is bomb placed?
     * @param explode           is bomb exploded?
     * @param range             = 0
     * @param time_since_placed time since placed
     * @param placedState       to draw placed images -> make animation
     * @param explodedState     to draw exploded images -> make animation
     */
    public Bomb(int x, int y, boolean place, boolean explode, int range, long time_since_placed,
            int placedState, int explodedState) {
        super(x, y);
        this.place = place;
        this.explode = explode;
        this.range = range;
        this.time_since_placed = time_since_placed;
        this.placedState = placedState;
        this.explodedState = explodedState;
    }

    /**
     * Create the bomb (center) from a bomber position
     * 
     * @param bomber
     */
    public Bomb(Bomber bomber) {
        super(bomber.getX(), bomber.getY());
        bomber.setBombs(bomber.getBombs() - 1);

        edges = new Bomb[4];

        place = true;
        explode = false;
        range = bomber.getBombRange();

        placedState = 0;

        bomb_images = new Image[2][3];
        edge_images = new Image[4][3];
        flame_images = new Image[2][3];

        try {
            setupImage();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        setupSound();

        rightFlames = new ArrayList<>();
        downFlames = new ArrayList<>();
        leftFlames = new ArrayList<>();
        upFlames = new ArrayList<>();

        time_since_placed = System.currentTimeMillis();
        timebeforeeachframe = System.currentTimeMillis();
    }

    public int getPlacedState() {
        return placedState;
    }

    public void setPlacedState(int placedState) {
        this.placedState = placedState;
    }

    public int getExplodedState() {
        return explodedState;
    }

    public void setExplodedState(int explodedState) {
        this.explodedState = explodedState;
    }

    @Override
    public void setupImage() throws FileNotFoundException {
        for (int i = 0; i < 3; i++) {
            bomb_images[0][i] = getImage("bomb_" + i + ".png", "sprites");
            bomb_images[1][i] = getImage("bomb_exploded" + i + ".png", "sprites");

            edge_images[0][i] = getImage("explosion_horizontal_right_last" + i + ".png", "sprites");
            edge_images[1][i] = getImage("explosion_vertical_down_last" + i + ".png", "sprites");
            edge_images[2][i] = getImage("explosion_horizontal_left_last" + i + ".png", "sprites");
            edge_images[3][i] = getImage("explosion_vertical_top_last" + i + ".png", "sprites");

            flame_images[0][i] = getImage("explosion_horizontal" + i + ".png", "sprites");
            flame_images[1][i] = getImage("explosion_vertical" + i + ".png", "sprites");

            explodedbrickImage[i] = getImage("brick_exploded" + i + ".png", "sprites");
        }
    }

    public void setupSound() {
        bomb_ticking = new MediaPlayer(new Media(getClass().getResource("/sound/ticking-clock1.wav").toString()));
        bomb_exploding = new MediaPlayer(new Media(getClass().getResource("/sound/bomb_exploded1.wav").toString()));
    }

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }

    public boolean isExplode() {
        return explode;
    }

    public void setExplode(boolean explode) {
        this.explode = explode;
    }

    public boolean isPlace() {
        return place;
    }

    public void setPlace(boolean place) {
        this.place = place;
    }

    public Bomb[] getEdges() {
        return edges;
    }

    public List<Bomb> getRightFlames() {
        return rightFlames;
    }

    public List<Bomb> getDownFlames() {
        return downFlames;
    }

    public List<Bomb> getLeftFlames() {
        return leftFlames;
    }

    public List<Bomb> getUpFlames() {
        return upFlames;
    }

    /**
     * To make bomb exlosing animation: 500ms from explosion
     * 
     * @param time_since_exploded
     */
    public void setTime_since_exploded(long time_since_exploded) {
        this.time_since_exploded = time_since_exploded;
    }

    /**
     * chcek if able to place a flame at this position
     * 
     * @param map check map entities
     * @param x   x-coordinate of the postion
     * @param y   y-coordinate of the postion
     * @return true if able to place
     */
    private boolean checkflame(Map map, int x, int y) {
        if (x < 0 || x >= map.getColumn() || y < 0 || y >= map.getRow())
            return false;
        if (map.getMap()[y][x] == '*' || map.getMap()[y][x] == '#'
                || x == map.getBomber().getX() && y == map.getBomber().getY()
                || x == map.getPortal().getX() && y == map.getPortal().getY()) {
            return false;
        } else {
            for (Enemy enemy : map.getEnemy()) {
                if (x == enemy.getX() && y == enemy.getY())
                    return false;
            }
        }
        return true;
    }

    /**
     * set up the bomb flame and edge (the end of the flame)
     * 
     * @param map Map
     */
    public void setupEdge(Map map) {

        int x = this.getX();
        int y = this.getY();
        for (int i = 0; i < range; i++) {
            x = this.getX() + 1 + i;
            if (checkflame(map, x, y) && x < this.getX() + range) {
                Bomb flame = new Bomb(x, y, place, explode, 0,
                        time_since_placed, placedState, explodedState);
                rightFlames.add(flame);
            } else {
                break;
            }
        }
        if (x >= 0 && x < map.getColumn() && y >= 0 && y < map.getRow()) {
            edges[0] = new Bomb(x, y, place, explode, 0, time_since_placed, placedState, explodedState);
        }

        x = this.getX();
        y = this.getY();
        for (int i = 0; i < range; i++) {
            y = this.getY() + 1 + i;
            if (checkflame(map, x, y) && y < this.getY() + range) {
                Bomb flame = new Bomb(x, y, place, explode, 0,
                        time_since_placed, placedState, explodedState);
                downFlames.add(flame);
            } else {
                break;
            }
        }
        if (x >= 0 && x < map.getColumn() && y >= 0 && y < map.getRow()) {
            edges[1] = new Bomb(x, y, place, explode, 0, time_since_placed, placedState, explodedState);
        }

        x = this.getX();
        y = this.getY();
        for (int i = 0; i < range; i++) {
            x = this.getX() - 1 - i;
            if (checkflame(map, x, y) && x > this.getX() - range) {
                Bomb flame = new Bomb(x, y, place, explode, 0,
                        time_since_placed, placedState, explodedState);
                leftFlames.add(flame);
            } else {
                break;
            }
        }
        if (x >= 0 && x < map.getColumn() && y >= 0 && y < map.getRow()) {
            edges[2] = new Bomb(x, y, place, explode, 0, time_since_placed, placedState, explodedState);
        }

        x = this.getX();
        y = this.getY();
        for (int i = 0; i < range; i++) {
            y = this.getY() - 1 - i;
            if (checkflame(map, x, y) && y > this.getY() - range) {
                Bomb flame = new Bomb(x, y, place, explode, 0,
                        time_since_placed, placedState, explodedState);
                upFlames.add(flame);
            } else {
                break;
            }
        }
        if (x >= 0 && x < map.getColumn() && y >= 0 && y < map.getRow()) {

            edges[3] = new Bomb(x, y, place, explode, 0, time_since_placed, placedState, explodedState);
        }

    }

    /**
     * bomb placing animation
     */
    public void bomb_placing() {
        switch (getPlacedState()) {
            case 0:
                setPlacedState(1);
                setImage(bomb_images[0][0]);
                break;
            case 1:
                setPlacedState(2);
                setImage(bomb_images[0][1]);
                break;
            case 2:
                setPlacedState(3);
                setImage(bomb_images[0][2]);
                break;
            case 3:
                setPlacedState(0);
                setImage(bomb_images[0][0]);
                break;
        }
        timebeforeeachframe = System.currentTimeMillis();
    }

    public void bomb_exploding() {
        switch (getExplodedState()) {
            case 0:
                setExplodedState(1);

                setImage(bomb_images[1][0]);
                tempBrickImage = explodedbrickImage[0];

                for (int i = 0; i < edges.length; i++) {
                    if (edges[i] != null)
                        edges[i].setImage(edge_images[i][0]);
                }

                for (Bomb rightFlame : rightFlames) {
                    rightFlame.setImage(flame_images[0][0]);
                }

                for (Bomb leftFlame : leftFlames) {
                    leftFlame.setImage(flame_images[0][0]);
                }

                for (Bomb upFlame : upFlames) {
                    upFlame.setImage(flame_images[1][0]);
                }

                for (Bomb downFlame : downFlames) {
                    downFlame.setImage(flame_images[1][0]);
                }

                break;
            case 1:
                setExplodedState(2);

                setImage(bomb_images[1][1]);

                if (range > 0) {
                    tempBrickImage = explodedbrickImage[1];

                    for (int i = 0; i < edges.length; i++) {
                        if (edges[i] != null)
                            edges[i].setImage(edge_images[i][1]);
                    }

                    rightFlames.forEach(rightFlame -> rightFlame.setImage(flame_images[0][1]));

                    leftFlames.forEach(leftFlame -> leftFlame.setImage(flame_images[0][1]));

                    upFlames.forEach(upFlame -> upFlame.setImage(flame_images[1][1]));

                    downFlames.forEach(downFlame -> downFlame.setImage(flame_images[1][1]));
                }
                break;
            case 2:
                setExplodedState(3);

                setImage(bomb_images[1][2]);

                if (range > 0) {
                    tempBrickImage = explodedbrickImage[2];

                    for (int i = 0; i < edges.length; i++) {
                        if (edges[i] != null)
                            edges[i].setImage(edge_images[i][2]);
                    }

                    rightFlames.forEach(rightFlame -> rightFlame.setImage(flame_images[0][2]));

                    leftFlames.forEach(leftFlame -> leftFlame.setImage(flame_images[0][2]));

                    upFlames.forEach(upFlame -> upFlame.setImage(flame_images[1][2]));

                    downFlames.forEach(downFlame -> downFlame.setImage(flame_images[1][2]));

                }
                break;
            case 3:
                setExplodedState(0);

                setImage(bomb_images[1][0]);

                if (range > 0) {
                    tempBrickImage = explodedbrickImage[0];

                    for (int i = 0; i < edges.length; i++) {
                        if (edges[i] != null)
                            edges[i].setImage(edge_images[i][0]);
                    }

                    rightFlames.forEach(rightFlame -> rightFlame.setImage(flame_images[0][0]));

                    leftFlames.forEach(leftFlame -> leftFlame.setImage(flame_images[0][0]));

                    upFlames.forEach(upFlame -> upFlame.setImage(flame_images[1][0]));

                    downFlames.forEach(downFlame -> downFlame.setImage(flame_images[1][0]));

                }
                break;
        }
        timebeforeeachframe = System.currentTimeMillis();
    }

    public void update(Map map) {
        long now = System.currentTimeMillis();
        if (!explode && place) {
            if (now - time_since_placed < 2000) {
                if (now - timebeforeeachframe > 100) {
                    bomb_placing();
                }
                if (!bomb_ticking.getStatus().equals(Status.PLAYING))
                    bomb_ticking.play();
            } else {
                explode = true;
                place = false;
                if (range > 0)
                    setupEdge(map);
                time_since_exploded = System.currentTimeMillis();
            }
        } else if (explode && !place) {
            if (now - time_since_exploded < 500) {
                if (now - timebeforeeachframe > 100) {
                    bomb_exploding();
                }
            } else {
                Explode(map);
                if (!bomb_exploding.getStatus().equals(Status.PLAYING))
                    bomb_exploding.play();
                explode = false;
            }
        }
    }

    /**
     * bomb exloding 
     * @param map Map
     */
    public void Explode(Map map) {
        int x = this.getX();
        int y = this.getY();

        if (map.getMap()[y][x] == '*') {
            map.getMap()[y][x] = ' ';
        }

        // if bomber in the explosion range
        if (((double) x == map.getBomber().getOldX() && (double) y == map.getBomber().getOldY())
                || (x == Math.ceil(map.getBomber().getDoubleX()) && y == Math.ceil(map.getBomber().getDoubleY()))) {

            map.getBomber().setLifes(map.getBomber().getLifes() - 1);
            map.getBomber().setAlive(false);
            map.getBomber().setMoving(false);
            map.getBomber().setTimesincedead(System.currentTimeMillis());
        }

        // if enemies in the explosion range
        for (Enemy enemy : map.getEnemy()) {

            if (((double) x == enemy.getOldX() && (double) y == enemy.getOldY())
                    || (x == Math.ceil(enemy.getOldX()) && y == Math.ceil(enemy.getOldY()))) {
                enemy.setAlive(false);
                enemy.setMoving(false);
                enemy.setTimesincedead(System.currentTimeMillis());

                System.out.println("Bomb: " + this.getX() + ", " + this.getY() + "; Enemy: "
                        + enemy.getOldX() + ", "
                        + enemy.getOldY());
            }
        }

        // if portal in the explosion range
        if (x == map.getPortal().getX() && y == map.getPortal().getY() && map.getPortal().isHide()) {
            map.getPortal().APPEAR();
        }

        // the flame
        if (range == 0) {
            for (Bomb bomb : map.getBombs()) {
                if (x == bomb.getX() && y == bomb.getY()) {
                    bomb.setExplode(true);
                    bomb.setPlace(false);
                    bomb.setupEdge(map);
                    bomb.setTime_since_exploded(System.currentTimeMillis());
                }
            }
        }

        // make item appear
        for (Item item : map.getItems()) {
            if (x == item.getX() && y == item.getY()) {
                item.APPEAR();
            }
        }

        // the bomb
        if (range > 0) {
            for (Bomb edge : edges) {
                if (edge != null)
                    edge.Explode(map);
            }

            for (Bomb rightFlame : rightFlames) {
                rightFlame.Explode(map);
            }

            for (Bomb downFlame : downFlames) {
                downFlame.Explode(map);
            }

            for (Bomb leftFlame : leftFlames) {
                leftFlame.Explode(map);
            }

            for (Bomb upFlame : upFlames) {
                upFlame.Explode(map);
            }
        }
    }
}
