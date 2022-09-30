package uet.oop;

public class MovingEntity extends Entity{

    public MovingEntity(){
        super();
    }

    public MovingEntity(int x, int y, char type){
        super(x, y, type);
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

            temp[this.getY()][this.getX()] = this.getType();

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

            temp[this.getY()][this.getX()] = this.getType();

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

            temp[this.getY()][this.getX()] = this.getType();

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

            temp[this.getY()][this.getX()] = this.getType();

            map.setMap(temp);
            map.printMap();

            return true;
        } return false;
    }
}
