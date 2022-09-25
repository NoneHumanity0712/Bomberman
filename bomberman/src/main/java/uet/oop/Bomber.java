package uet.oop;

public class Bomber extends Entity {
    private int bombs;

    public int getBombs() {
        return bombs;
    }

    public void setBombs(int bombs) {
        this.bombs = bombs;
    }

    public Bomber() {
        super(1, 1);
        bombs = 15;
    }

    public boolean legal_move(Map map, int y, int x) {
        if (map.getMap()[y][x] == ' ') {
            return true;
        }
        return false;
    }

    public void MOVE_RIGHT(Map map) {
        if (legal_move(map, this.getY(), this.getX() + 1)) {
            char[][] temp = new char[map.getRow()][map.getColumn()];
            temp = map.getMap();
            temp[this.getY()][this.getX()] = ' ';

            setY(this.getY());
            setX(this.getX() + 1);

            temp[this.getY()][this.getX()] = 'p';

            map.setMap(temp);
        }
    }

    public void MOVE_LEFT(Map map) {
        if (legal_move(map, this.getY(), this.getX() - 1)) {
            char[][] temp = new char[map.getRow()][map.getColumn()];
            temp = map.getMap();
            temp[this.getY()][this.getX()] = ' ';

            setY(this.getY());
            setX(this.getX() - 1);

            temp[this.getY()][this.getX()] = 'p';

            map.setMap(temp);
        }
    }

    public void MOVE_UP(Map map) {
        if (legal_move(map, this.getY() - 1, this.getX())) {
            char[][] temp = new char[map.getRow()][map.getColumn()];
            temp = map.getMap();
            temp[this.getY()][this.getX()] = ' ';

            setY(this.getY() - 1);
            setX(this.getX());
            
            temp[this.getY()][this.getX()] = 'p';

            map.setMap(temp);
        }
    }

    public void MOVE_DOWN(Map map) {
        if (legal_move(map, this.getY() + 1, this.getX())) {
            char[][] temp = new char[map.getRow()][map.getColumn()];
            temp = map.getMap();
            temp[this.getY()][this.getX()] = ' ';

            setY(this.getY() + 1);
            setX(this.getX());
                        
            temp[this.getY()][this.getX()] = 'p';

            map.setMap(temp);
        }
    }

    public Entity placeBomb(Map map) {
        bombs = bombs - 1;
        Entity en = new Entity();
        if (bombs < 0) {
            System.err.println("There are no more bombs!");
            return en;
        }
        en.setX(this.getX());
        en.setY(this.getY());
        return en;
    }
}
