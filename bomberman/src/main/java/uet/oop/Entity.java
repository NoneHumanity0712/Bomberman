package uet.oop;

public class Entity {
    /**
     * x position from the top left corner.
     */
    private int x;

    /**
     * y position from the top left corner.
     */
    private int y;

    /**
     * #    Wall,
     * *    Brick,
     * x    Portal,
     * p    Bomber,
     * 1    Balloom,
     * 2    Oneal,
     * b    Bomb Item,
     * f    Flame Item,
     * s    Speed Item.
     */
    private final char[] types = { '#', '*', 'x', 'p',
            '1', '2', 'b', 'f', 's' };

    private char type;

    public void setX(int x) {
        this.x = x;
    }

    public int getX() {
        return x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getY() {
        return y;
    }

    public void setType(char type) {
        this.type = type;
    }

    public char getType() {
        return type;
    }

    public Entity() {
        this.x = -1;
        this.y = -1;
    }

    public Entity(int x, int y, char type) {
        this.x = x;
        this.y = y;
        this.type = type;
    }

    public Entity(Entity e) {
        this.x = e.getX();
        this.y = e.getY();
    }

    public boolean legal_move(Map map, int y, int x) {
        if (map.getMap()[y][x] == ' ') {
            return true;
        }
        return false;
    }

    public boolean MOVE_RIGHT(Map map) {
        if (legal_move(map, this.getY(), this.getX() + 1)) {
            char[][] temp = new char[map.getRow()][map.getColumn()];
            temp = map.getMap();

            if (temp[this.getY()][this.getX()] != 'b')
                temp[this.getY()][this.getX()] = ' ';

            setY(this.getY());
            setX(this.getX() + 1);

            temp[this.getY()][this.getX()] = this.type;

            map.setMap(temp);
            map.printMap();

            return true;
        }
        return false;
    }

    public boolean MOVE_LEFT(Map map) {
        if (legal_move(map, this.getY(), this.getX() - 1)) {
            char[][] temp = new char[map.getRow()][map.getColumn()];
            temp = map.getMap();

            if (temp[this.getY()][this.getX()] != 'b')
                temp[this.getY()][this.getX()] = ' ';

            setY(this.getY());
            setX(this.getX() - 1);

            temp[this.getY()][this.getX()] = this.type;

            map.setMap(temp);
            map.printMap();

            return true;
        }
        return false;
    }

    public boolean MOVE_UP(Map map) {
        if (legal_move(map, this.getY() - 1, this.getX())) {
            char[][] temp = new char[map.getRow()][map.getColumn()];
            temp = map.getMap();

            if (temp[this.getY()][this.getX()] != 'b')
                temp[this.getY()][this.getX()] = ' ';

            setY(this.getY() - 1);
            setX(this.getX());

            temp[this.getY()][this.getX()] = this.type;

            map.setMap(temp);
            map.printMap();

            return true;
        }
        return false;
    }

    public boolean MOVE_DOWN(Map map) {
        if (legal_move(map, this.getY() + 1, this.getX())) {
            char[][] temp = new char[map.getRow()][map.getColumn()];
            temp = map.getMap();

            if (temp[this.getY()][this.getX()] != 'b')
                temp[this.getY()][this.getX()] = ' ';

            setY(this.getY() + 1);
            setX(this.getX());

            temp[this.getY()][this.getX()] = this.type;

            map.setMap(temp);
            map.printMap();

            return true;
        } return false;
    }
}