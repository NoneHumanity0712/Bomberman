package uet.oop.entities;

import java.io.FileNotFoundException;

import javafx.scene.image.Image;
import uet.oop.Map;

public class Bomb extends Entity {
    private boolean explode;
    private int range;

    Image[][] bomb_images;

    @Override
    public void setupImage() throws FileNotFoundException {
        for (int i = 0; i < 3; i++) {
            bomb_images[0][i] = getImage("sprites/bomb_" + i +".png");
            bomb_images[1][i] = getImage("sprites/bomb_exploded" + i +".png");
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

    public Bomb(int x, int y) {
        super(x, y, 'b');
        explode = false;
        bomb_images = new Image[2][3];
    }

    public Bomb(Entity e) {
        super(e);
        explode = false;
        bomb_images = new Image[2][3];
    }

    public void Explode(Map map) {
        explode = true;
        char[][] temp = map.getMap();
        int i = this.getY();
        int j = this.getX();

        temp[i][j] = ' ';
        if (map.portal.getY() == i && map.portal.getX() == j){
            map.portal.APPEAR();
        }

        if (temp[i - 1][j] == '*') {
            temp[i - 1][j] = ' ';
        } 
        if (map.portal.getY() == i - 1 && map.portal.getX() == j) {
            map.portal.APPEAR();
        }

        if (temp[i + 1][j] == '*') {
            temp[i + 1][j] = ' ';
        } 
        if (map.portal.getY() == i + 1 && map.portal.getX() == j) {
            map.portal.APPEAR();
        }

        if (temp[i][j - 1] == '*') {
            temp[i][j - 1] = ' ';
        } 
        if (map.portal.getY() == i && map.portal.getX() == j - 1) {
            map.portal.APPEAR();
        }

        if (temp[i][j + 1] == '*') {
            temp[i][j + 1] = ' ';
        } 
        if (map.portal.getY() == i && map.portal.getX() == j + 1) {
            map.portal.APPEAR();
        }
    }

}
