package uet.oop.entities.movingentities;

import java.io.FileNotFoundException;

import javafx.scene.canvas.GraphicsContext;
import uet.oop.entities.Entity;
import uet.oop.gameprocess.Map;

public abstract class MovingEntity extends Entity {

    private int stepCount;
    private double speed; // in pixel

    private double doubleX;
    private double doubleY;

    private double oldX;
    private double oldY;

    private double step;

    private int direction;

    private boolean moving;
    private boolean alive;

    private long timebeforeeachstep;
    private long delaytime;
    private long timesincedead;

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

    public void setStep(double step) {
        this.step = step;
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

    public void setSpeed(double speed) {
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

    public long getTimesincedead() {
        return timesincedead;
    }

    public void setTimesincedead(long timesincedead) {
        this.timesincedead = timesincedead;
    }
    
    public long getTimebeforeeachstep() {
        return timebeforeeachstep;
    }

    public void setTimebeforeeachstep(long timebeforeeachstep) {
        this.timebeforeeachstep = timebeforeeachstep;
    }

    public long getDelaytime() {
        return delaytime;
    }

    public void setDelaytime(long delaytime) {
        this.delaytime = delaytime;
    }

    public MovingEntity() {
        super();
        stepCount = 0;

        oldX = getDoubleX();
        oldY = getDoubleY();
        
        alive = true;
    };

    public MovingEntity(int x, int y) {
        super(x, y);
        doubleX = (double) x;
        doubleY = (double) y;
        stepCount = 0;

        oldX = getDoubleX();
        oldY = getDoubleY();

        alive = true;
    }

    public MovingEntity(int x, int y, char type) {
        super(x, y, type);
        doubleX = (double) x;
        doubleY = (double) y;
        stepCount = 0;

        oldX = getDoubleX();
        oldY = getDoubleY();

        alive = true;
    }

    public boolean legal_move(Map map, int y, int x) {
        if (y >= 0 && y < map.getRow() && x >= 0 && x < map.getColumn()) {
            if (map.getMap()[y][x] == ' ') {
                return true;
            }
        }
        return false;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
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
        if (isMoving() && System.currentTimeMillis() - timebeforeeachstep >= delaytime) {
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
                setStepCount(0);
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
