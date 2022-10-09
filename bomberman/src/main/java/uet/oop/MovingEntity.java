package uet.oop;

import java.io.FileNotFoundException;

public abstract class MovingEntity extends Entity {

    private int stepCount;
    private int speed; // in pixel

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setStepCount(int stepCount) {
        this.stepCount = stepCount;
    }

    public int getStepCount() {
        return stepCount;
    }

    public MovingEntity() {
    };

    public MovingEntity(int x, int y) {
        super(x, y);
        stepCount = 0;
    }

    public MovingEntity(int x, int y, char type) {
        super(x, y, type);
        stepCount = 0;
    }

    public boolean legal_move(Map map, int y, int x) {
        if (y >= 0 && y < map.getRow() && x >= 0 && x < map.getColumn()) {
            if (map.getMap()[y][x] != '#' && map.getMap()[y][x] != '*') {
                return true;
            }
        }
        return false;
    }

    public abstract void STEP_RIGHT();

    public boolean MOVE_RIGHT(Map map) {
        if (legal_move(map, this.getY(), this.getX() + getSpeed() / 8)) {

            STEP_RIGHT();

            setX(this.getX() + getSpeed() / 8);

            return true;
        } else {
            return false;
        }
    }

    public abstract void STEP_LEFT();

    public boolean MOVE_LEFT(Map map) {
        if (legal_move(map, this.getY(), this.getX() - 1)) {

            setY(this.getY());
            setX(this.getX() - 1);

            return true;
        } else {
            return false;
        }
    }

    public abstract void STEP_UP();

    public boolean MOVE_UP(Map map) {
        if (legal_move(map, this.getY() - 1, this.getX())) {
            setY(this.getY() - 1);
            setX(this.getX());

            return true;
        } else {
            return false;
        }
    }

    public abstract void STEP_DOWN();

    public boolean MOVE_DOWN(Map map) {
        if (legal_move(map, this.getY() + 1, this.getX())) {

            setY(this.getY() + 1);
            setX(this.getX());

            return true;
        } else {
            return false;
        }
    }

    public abstract void DEAD();

    @Override
    public void setupImage() throws FileNotFoundException {
    }
}
