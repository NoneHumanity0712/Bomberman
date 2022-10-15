package uet.oop.entities;

import uet.oop.Map;

public class Balloom extends Enemy {

    public Balloom() {
        super();
    }

    public Balloom(Balloom balloom) {
        super(balloom);
    }

    public Balloom(int x, int y) {
        super(x, y, '1');
    }

    public boolean checkDirection(Map map) {
        // if (super.getDirection() == 0) {
        //     return this.MOVE_RIGHT(map);
        // } else if (super.getDirection() == 1) {
        //     return this.MOVE_DOWN(map);
        // } else if (super.getDirection() == 2) {
        //     return this.MOVE_LEFT(map);
        // } else if (super.getDirection() == 3) {
        //     return this.MOVE_UP(map);
        // }
        return false;
    }

    public void MOVE(Map map) {
        while (!this.checkDirection(map)) {
            super.setDirection((getDirection() + 1) % 4);
        }
    }
}
