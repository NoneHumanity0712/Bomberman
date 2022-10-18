package uet.oop.entities;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.Image;
import uet.oop.entities.items.Item;
import uet.oop.entities.movingentities.Bomber;
import uet.oop.entities.movingentities.Enemy;
import uet.oop.gameprocess.Map;

public class Bomb extends Entity {
    private boolean place;
    private boolean explode;
    private int range;

    private long time_since_placed;
    private long time_since_exploded;

    private int placedState;
    private int explodedState;

    private int[] edgeX = new int[4];
    private int[] edgeY = new int[4];

    private Image[][] bomb_images;
    private Image[][] edge_images;
    private Image[][] flame_images;
    private Image[] explodedbrickImage = new Image[3];
    public Image tempBrickImage;

    private Bomb[] edges;
    private List<Bomb> rightFlames;
    private List<Bomb> downFlames;
    private List<Bomb> leftFlames;
    private List<Bomb> upFlames;

    private long timebeforeeachframe;

    public Bomb(int x, int y, char type, boolean place, boolean explode, int range, long time_since_placed,
            int placedState, int explodedState) {
        super(x, y, type);
        this.place = place;
        this.explode = explode;
        this.range = range;
        this.time_since_placed = time_since_placed;
        this.placedState = placedState;
        this.explodedState = explodedState;
    }

    public Bomb(Bomber bomber) {
        super(bomber.getX(), bomber.getY(), 'b');
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
            bomb_images[0][i] = getImage("sprites/bomb_" + i + ".png");
            bomb_images[1][i] = getImage("sprites/bomb_exploded" + i + ".png");

            edge_images[0][i] = getImage("sprites/explosion_horizontal_right_last" + i + ".png");
            edge_images[1][i] = getImage("sprites/explosion_vertical_down_last" + i + ".png");
            edge_images[2][i] = getImage("sprites/explosion_horizontal_left_last" + i + ".png");
            edge_images[3][i] = getImage("sprites/explosion_vertical_top_last" + i + ".png");

            flame_images[0][i] = getImage("sprites/explosion_horizontal" + i + ".png");
            flame_images[1][i] = getImage("sprites/explosion_vertical" + i + ".png");

            explodedbrickImage[i] = getImage("sprites/brick_exploded" + i + ".png");
        }
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

    public void setTime_since_exploded(long time_since_exploded) {
        this.time_since_exploded = time_since_exploded;
    }

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

    public void setupEdge(Map map) {
        // right edge
        edgeX[0] = this.getX() + range;
        edgeY[0] = this.getY();

        // down edge
        edgeX[1] = this.getX();
        edgeY[1] = this.getY() + range;

        // left edge
        edgeX[2] = this.getX() - range;
        edgeY[2] = this.getY();

        // up edge
        edgeX[3] = this.getX();
        edgeY[3] = this.getY() - range;

        for (int i = 0; i < 4; i++) {
            if (edgeX[i] >= 0 && edgeX[i] < map.getColumn() && edgeY[i] >= 0 && edgeY[i] < map.getRow()) {
                Bomb edge = new Bomb(edgeX[i], edgeY[i], 'b', place, explode, 0, time_since_placed, placedState,
                        explodedState);
                edges[i] = edge;
            } else
                edges[i] = null;
        }

        if (range > 1) {

            for (int i = 0; i < range - 1; i++) {
                int x = this.getX() + 1 + i;
                int y = this.getY();

                if (checkflame(map, x, y)) {
                    Bomb rightflame = new Bomb(x, y, 'b', place, explode, 0, time_since_placed, placedState,
                            explodedState);
                    rightFlames.add(rightflame);
                } else {
                    if (edges[0] != null)
                        edges[0].setX(x);
                    break;
                }
            }

            for (int i = 0; i < range - 1; i++) {
                int x = this.getX();
                int y = this.getY() + 1 + i;

                if (checkflame(map, x, y)) {
                    Bomb down = new Bomb(x, y, 'b', place, explode, 0, time_since_placed, placedState, explodedState);
                    downFlames.add(down);
                } else {
                    if (edges[1] != null)
                        edges[1].setY(y);
                    break;
                }
            }

            for (int i = 0; i < range - 1; i++) {
                int x = this.getX() - 1 - i;
                int y = this.getY();

                if (checkflame(map, x, y)) {
                    Bomb leftFlame = new Bomb(x, y, 'b', place, explode, 0, time_since_placed, placedState,
                            explodedState);
                    leftFlames.add(leftFlame);
                } else {
                    if (edges[2] != null)
                        edges[2].setX(x);
                    break;
                }
            }

            for (int i = 0; i < range - 1; i++) {
                int x = this.getX();
                int y = this.getY() - 1 - i;

                if (checkflame(map, x, y)) {
                    Bomb upFlame = new Bomb(this.getX(), this.getY() - 1 - i, 'b', place, explode, 0,
                            time_since_placed, placedState, explodedState);
                    upFlames.add(upFlame);
                } else {
                    if (edges[3] != null)
                        edges[3].setY(y);
                }
            }
        }
    }

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

                    for (Bomb rightFlame : rightFlames) {
                        rightFlame.setImage(flame_images[0][1]);
                    }

                    for (Bomb leftFlame : leftFlames) {
                        leftFlame.setImage(flame_images[0][1]);
                    }

                    for (Bomb upFlame : upFlames) {
                        upFlame.setImage(flame_images[1][1]);
                    }

                    for (Bomb downFlame : downFlames) {
                        downFlame.setImage(flame_images[1][1]);
                    }
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

                    for (Bomb rightFlame : rightFlames) {
                        rightFlame.setImage(flame_images[0][2]);
                    }

                    for (Bomb leftFlame : leftFlames) {
                        leftFlame.setImage(flame_images[0][2]);
                    }

                    for (Bomb upFlame : upFlames) {
                        upFlame.setImage(flame_images[1][2]);
                    }

                    for (Bomb downFlame : downFlames) {
                        downFlame.setImage(flame_images[1][2]);
                    }
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
                explode = false;
            }
        }
    }

    public void Explode(Map map) {
        int x = this.getX();
        int y = this.getY();

        if (map.getMap()[y][x] == '*') {
            map.getMap()[y][x] = ' ';
        }

        if (((double) x == map.getBomber().getOldX() && (double) y == map.getBomber().getOldY())
                || (x == Math.ceil(map.getBomber().getDoubleX()) && y == Math.ceil(map.getBomber().getDoubleY()))) {
            map.getBomber().setAlive(false);
            map.getBomber().setTimesincedead(System.currentTimeMillis());
        }

        for (Enemy enemy : map.getEnemy()) {

            if (((double) x == enemy.getOldX() && (double) y == enemy.getOldY())
                    || (x == Math.ceil(enemy.getOldX()) && y == Math.ceil(enemy.getOldY()))) {
                enemy.setAlive(false);
                enemy.DEAD();

                System.out.println("Bomb: " + this.getX() + ", " + this.getY() + "; Enemy: "
                        + enemy.getOldX() + ", "
                        + enemy.getOldY());
            }
        }

        if (x == map.getPortal().getX() && y == map.getPortal().getY()) {
            map.getPortal().APPEAR();
        }

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

        for (Item item : map.getItems()) {
            if (x == item.getX() && y == item.getY()) {
                item.APPEAR();
            }
        }

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
