package uet.oop.entities.movingentities.enemies;

import java.io.FileNotFoundException;

import javafx.scene.image.Image;
import uet.oop.entities.movingentities.MovingEntity;
import uet.oop.gameprocess.Map;

public abstract class Enemy extends MovingEntity {

    Image[][] enemy_images = new Image[2][3];
    Image[] enemy_dead;

    public Enemy(int x, int y) throws FileNotFoundException {
        super(x, y);
    }

    public Enemy(Enemy enemy) throws FileNotFoundException {
        super(enemy.getX(), enemy.getY());
        super.setDirection(enemy.getDirection());
    }

    @Override
    public void setupImage() throws FileNotFoundException {
        enemy_dead = new Image[4];
        for (int i = 1; i < 4; i++) {
            enemy_dead[i] = getImage("mob_dead" + i + ".png", "sprites");
        }
    }

    abstract public void MOVE(Map map);

    @Override
    public void STEP_RIGHT() {
        switch (super.getStepCount()) {
            case 0:
                setStepCount(1);
                setImage(enemy_images[0][0]);
                break;
            case 1:
                setStepCount(2);
                setImage(enemy_images[0][1]);
                break;
            case 2:
                setStepCount(3);
                setImage(enemy_images[0][2]);
                break;
            case 3:
                setStepCount(0);
                setImage(enemy_images[0][0]);
                break;
        }
        setTimebeforeeachstep(System.currentTimeMillis());
    }

    @Override
    public void STEP_LEFT() {
        switch (super.getStepCount()) {
            case 0:
                setStepCount(1);
                setImage(enemy_images[1][0]);
                break;
            case 1:
                setStepCount(2);
                setImage(enemy_images[1][1]);
                break;
            case 2:
                setStepCount(3);
                setImage(enemy_images[1][2]);
                break;
            case 3:
                setStepCount(0);
                setImage(enemy_images[1][0]);
                break;
        }
        setTimebeforeeachstep(System.currentTimeMillis());
    }

    @Override
    public void STEP_UP() {
        switch (super.getStepCount()) {
            case 0:
                setStepCount(1);
                setImage(enemy_images[1][0]);
                break;
            case 1:
                setStepCount(2);
                setImage(enemy_images[1][1]);
                break;
            case 2:
                setStepCount(3);
                setImage(enemy_images[1][2]);
                break;
            case 3:
                setStepCount(0);
                setImage(enemy_images[1][0]);
                break;
        }
        setTimebeforeeachstep(System.currentTimeMillis());
    }

    @Override
    public void STEP_DOWN() {
        switch (super.getStepCount()) {
            case 0:
                setStepCount(1);
                setImage(enemy_images[0][0]);
                break;
            case 1:
                setStepCount(2);
                setImage(enemy_images[0][1]);
                break;
            case 2:
                setStepCount(3);
                setImage(enemy_images[0][2]);
                break;
            case 3:
                setStepCount(0);
                setImage(enemy_images[0][0]);
                break;
        }
        setTimebeforeeachstep(System.currentTimeMillis());
    }

    @Override
    public void DEAD() {
        switch (getDeadState()) {
            case 0:
                setDeadState(1);
                setImage(enemy_dead[0]);
                break;
            case 1:
                setDeadState(2);
                setImage(enemy_dead[1]);
                break;
            case 2:
                setDeadState(3);
                setImage(enemy_dead[2]);
                break;
            case 3:
                setImage(enemy_dead[3]);
                break;
        }
        setDeadBeforeTime(System.currentTimeMillis());
    }
}
