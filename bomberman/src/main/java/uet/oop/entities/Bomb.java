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
    private Image[] explodedbrickImage = new Image[3];
    public Image tempBrickImage;

    private List<Bomb> edges;

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

        edges = new ArrayList<>();

        place = true;
        explode = false;
        range = bomber.getBombRange();

        placedState = 0;

        bomb_images = new Image[2][3];
        edge_images = new Image[4][3];

        try {
            setupImage();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

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

    public List<Bomb> getEdges() {
        return edges;
    }

    public void setTime_since_exploded(long time_since_exploded) {
        this.time_since_exploded = time_since_exploded;
    }

    public void setupEdge() {
        // right edge
        edgeX[0] = this.getX() + range;
        edgeY[0] = this.getY();

        Bomb rightEdge = new Bomb(edgeX[0], edgeY[0], 'b', place, explode, 0, time_since_placed, placedState,
                explodedState);
        edges.add(rightEdge);

        // down edge
        edgeX[1] = this.getX();
        edgeY[1] = this.getY() + range;

        Bomb downEdge = new Bomb(edgeX[1], edgeY[1], 'b', place, explode, 0, time_since_placed, placedState,
                explodedState);
        edges.add(downEdge);

        // left edge
        edgeX[2] = this.getX() - range;
        edgeY[2] = this.getY();

        Bomb leftEdge = new Bomb(edgeX[2], edgeY[2], 'b', place, explode, 0, time_since_placed, placedState,
                explodedState);
        edges.add(leftEdge);

        // up edge
        edgeX[3] = this.getX();
        edgeY[3] = this.getY() - range;

        Bomb upEdge = new Bomb(edgeX[3], edgeY[3], 'b', place, explode, 0, time_since_placed, placedState,
                explodedState);
        edges.add(upEdge);
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
                for (int i = 0; i < 4; i++) {
                    edges.get(i).setImage(edge_images[i][0]);
                }

                break;
            case 1:
                setExplodedState(2);
                setImage(bomb_images[1][1]);
                tempBrickImage = explodedbrickImage[1];
                for (int i = 0; i < 4; i++) {
                    edges.get(i).setImage(edge_images[i][1]);
                }
                break;
            case 2:
                setExplodedState(3);
                setImage(bomb_images[1][2]);
                tempBrickImage = explodedbrickImage[2];
                for (int i = 0; i < 4; i++) {
                    edges.get(i).setImage(edge_images[i][2]);
                }
                break;
            case 3:
                setExplodedState(0);
                setImage(bomb_images[1][0]);
                tempBrickImage = explodedbrickImage[0];
                for (int i = 0; i < 4; i++) {
                    edges.get(i).setImage(edge_images[i][0]);
                }
                break;
        }
        timebeforeeachframe = System.currentTimeMillis();
    }

    public void update(Map map) {
        long now = System.currentTimeMillis();
        if (!explode && place) {
            if (now - time_since_placed < 2000) {
                if (now - timebeforeeachframe > 100){
                    bomb_placing();}
            } else {
                explode = true;
                place = false;
                setupEdge();
                time_since_exploded = System.currentTimeMillis();
            }
        } else if (explode && !place) {
            if (now - time_since_exploded < 500) {
                if (now - timebeforeeachframe > 100){
                    bomb_exploding();
                }
            } else {
                Explode(map);
                explode = false;
            }
        }
    }

    public void Explode(Map map) {
        char[][] temp = map.getMap();
        int x = this.getX();
        int y = this.getY();
        
        if (x == (int) map.getBomber().getOldX() && y == (int) map.getBomber().getOldY()) {
            map.getBomber().setAlive(false);
        }
        
        for (Enemy enemy : map.getEnemy()) {
            if (x == (int) enemy.getOldX() && y == (int) enemy.getOldY()) {
                enemy.setAlive(false);
            }
        }

        for (Bomb edge : edges) {
            x = edge.getX();
            y = edge.getY();

            if (x == (int) map.getBomber().getOldX() && y == (int) map.getBomber().getOldY()) {
                map.getBomber().setAlive(false);
            }

            if (x == map.getPortal().getX() && y == map.getPortal().getY()) {
                map.getPortal().APPEAR();
            }

            if (temp[y][x] == '*') {
                temp[y][x] = ' ';
            }

            for (Enemy enemy : map.getEnemy()) {
                if (x == (int) enemy.getOldX() && y == (int) enemy.getOldY()) {
                    enemy.DEAD();
                }
            }

            for (Bomb bomb : map.getBombs()) {
                if (x == bomb.getX() && y == bomb.getY()) {
                    bomb.setExplode(true);
                    bomb.setPlace(false);
                    bomb.setupEdge();
                    bomb.setTime_since_exploded(System.currentTimeMillis());
                }
            }

            for (Item item : map.getItems()){
                if (x == item.getX() && y == item.getY()){
                    item.APPEAR();
                }
            }
        }
    }
}
