package uet.oop.entities;

import java.io.FileNotFoundException;

import javafx.scene.canvas.GraphicsContext;
import uet.oop.Map;

public abstract class MovingEntity extends Entity {

    private int stepCount;
    private double speed = 1; // in pixel

    private double doubleX;
    private double doubleY;

    private double oldX;
    private double oldY;

    private final double step = speed/8;

    private int direction;

    private boolean moving;

    public void setOldX(double oldX) {
        this.oldX = oldX;
    }

    public double getOldX() {
        return oldX;
    }

    public void setOldY(double oldY) {
        this.oldY = oldY;
    }

    public double getOldY() {
        return oldY;
    }

    public double getStep() {
        return step;
    }

    public double getDoubleX() {
        return doubleX;
    }

    public void setDoubleX(double doubleX) {
        this.doubleX = doubleX;
    }

    public double getDoubleY() {
        return doubleY;
    }

    public void setDoubleY(double doubleY) {
        this.doubleY = doubleY;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public double getSpeed() {
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

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public MovingEntity() {
        super();
        stepCount = 0;

        oldX = getDoubleX();
        oldY = getDoubleY();
    };

    public MovingEntity(int x, int y) {
        super(x, y);
        doubleX = (double) x;
        doubleY = (double) y;
        stepCount = 0;

        oldX = getDoubleX();
        oldY = getDoubleY();
    }

    public MovingEntity(int x, int y, char type) {
        super(x, y, type);
        doubleX = (double) x;
        doubleY = (double) y;
        stepCount = 0;

        oldX = getDoubleX();
        oldY = getDoubleY();
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

    public void MOVE_RIGHT() {
        STEP_RIGHT();

        oldX += step;
    }

    public abstract void STEP_LEFT();

    public void MOVE_LEFT() {
        STEP_LEFT();

        oldX -= step;
    }

    public abstract void STEP_UP();

    public void MOVE_UP() {
        STEP_UP();

        oldY -= step;
    }

    public abstract void STEP_DOWN();

    public void MOVE_DOWN() {
        STEP_DOWN();

        oldY += step;
    }

    public abstract void DEAD();

    @Override
    public void setupImage() throws FileNotFoundException {
    }

    public void update(Map map) {
        if (isMoving()) {
            switch (direction) {
                case 0:
                    if (oldX < doubleX) {
                        MOVE_RIGHT();
                    }
                    break;
                case 1:
                    if (oldY < doubleY) {
                        MOVE_DOWN();
                    }
                case 2:
                    if (oldX > doubleX) {
                        MOVE_LEFT();
                    }
                case 3:
                    if (oldY > doubleY) {
                        MOVE_UP();
                    }
                default:
                    break;
            }
            if (oldX == getDoubleX() && oldY == getDoubleY()) {
                setX((int) doubleX);
                setY((int) doubleY);

                moving = false;
            }
        }
    }

    @Override
    public void render(GraphicsContext context) {
        context.drawImage(getImage(), oldX, oldY, Entity.size, Entity.size);
    }
}
