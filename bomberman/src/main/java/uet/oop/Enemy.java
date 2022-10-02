package uet.oop;

public class Enemy extends MovingEntity {
    private int speed;
    private int direction;

    Enemy(int x, int y){
        super(x, y, 'x');
        direction = 0;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getSpeed() {
        return speed;
    }

    public int getDirection() {
        return direction;
    }

    public boolean checkDirection(Map map) {
        if (direction == 0) {
            return this.MOVE_RIGHT(map);
        } else if (direction == 1) {
            return this.MOVE_DOWN(map);
        } else if (direction == 2) {
            return this.MOVE_LEFT(map);
        } else if (direction == 3) {
            return this.MOVE_UP(map);
        }
        return false;
    }

    public void MOVE(Map map) {
        while (!this.checkDirection(map)) {
            direction = (direction + 1) % 4;
        }
    }
}
