package uet.oop;

public class Bomb extends Entity {
    public Bomb(int x, int y) {
        super(x, y, 'b');
    }

    public Bomb(Entity e) {
        super(e);
    }

    public boolean legalPosition(Map map) {
        if (this.getX() < 0 || this.getY() < 0
                || this.getX() >= map.getColumn() || this.getY() >= map.getRow()) {
            return false;
        }
        return true;
    }

    public void Explode(Map map) {
        char[][] temp = map.getMap();
        int i = this.getY();
        int j = this.getX();

        temp[i][j] = ' ';

        if (temp[i - 1][j] == '*') {
            temp[i - 1][j] = ' ';
        } else if (temp[i - 1][j] == 'b') {
            Bomb newBomb = new Bomb(i - 1, j);
            newBomb.Explode(map);
        } else if (temp[i - 1][j] == 'p') {
            temp[i - 1][j] = 'P';
        }

        if (temp[i + 1][j] == '*') {
            temp[i + 1][j] = ' ';
        } else if (temp[i + 1][j] == 'b') {
            Bomb newBomb = new Bomb(i + 1, j);
            newBomb.Explode(map);
        } else if (temp[i + 1][j] == 'p') {
            temp[i + 1][j] = 'P';
        }

        if (temp[i][j - 1] == '*') {
            temp[i][j - 1] = ' ';
        } else if (temp[i][j - 1] == 'b') {
            Bomb newBomb = new Bomb(i, j - 1);
            newBomb.Explode(map);
        } else if (temp[i][j - 1] == 'p') {
            temp[i][j - 1] = 'P';
        }

        if (temp[i][j + 1] == '*') {
            temp[i][j + 1] = ' ';
        } else if (temp[i][j + 1] == 'b') {
            Bomb newBomb = new Bomb(i, j + 1);
            newBomb.Explode(map);
        } else if (temp[i][j + 1] == 'p') {
            temp[i][j + 1] = 'P';
        }
    }
}
