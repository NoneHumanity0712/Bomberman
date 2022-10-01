package uet.oop;

public class MovingEntity extends Entity {

    public MovingEntity() {
        super();
    }

    public MovingEntity(int x, int y, char type) {
        super(x, y, type);
    }

    public boolean legal_move(Map map, int y, int x) {
        if (y >= 0 && y < map.getRow() && x >= 0 && x < map.getColumn()) {
            if (map.getMap()[y][x] == ' ') {
                return true;
            }
        }
        return false;
    }

    public boolean MOVE_RIGHT(Map map) {
        if (legal_move(map, this.getY(), this.getX() + 1)) {

            setY(this.getY());
            setX(this.getX() + 1);

            map.printMap();

            return true;
        }
        return false;
    }

    public boolean MOVE_LEFT(Map map) {
        if (legal_move(map, this.getY(), this.getX() - 1)) {

            setY(this.getY());
            setX(this.getX() - 1);

            map.printMap();

            return true;
        }
        return false;
    }

    public boolean MOVE_UP(Map map) {
        if (legal_move(map, this.getY() - 1, this.getX())) {
            setY(this.getY() - 1);
            setX(this.getX());

            map.printMap();

            return true;
        }
        return false;
    }

    public boolean MOVE_DOWN(Map map) {
        if (legal_move(map, this.getY() + 1, this.getX())) {

            setY(this.getY() + 1);
            setX(this.getX());

            map.printMap();
            return true;
        }
        return false;
    }
}
