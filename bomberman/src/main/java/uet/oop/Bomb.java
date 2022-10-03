package uet.oop;

public class Bomb extends Entity {
    private boolean explode;

    public boolean isExplode() {
        return explode;
    }

    public Bomb(int x, int y) {
        super(x, y, 'b');
        explode = false;
    }

    public Bomb(Entity e) {
        super(e);
        explode = false;
    }

    public boolean legalPosition(Map map) {
        if (this.getX() < 0 || this.getY() < 0
                || this.getX() >= map.getColumn() || this.getY() >= map.getRow()) {
            return false;
        }
        return true;
    }

    public void Explode(Map map) {
        explode = true;
        char[][] temp = map.getMap();
        int i = this.getY();
        int j = this.getX();

        temp[i][j] = ' ';

        if (temp[i - 1][j] == '*') {
            temp[i - 1][j] = ' ';
        }

        if (temp[i + 1][j] == '*') {
            temp[i + 1][j] = ' ';
        }

        if (temp[i][j - 1] == '*') {
            temp[i][j - 1] = ' ';
        }

        if (temp[i][j + 1] == '*') {
            temp[i][j + 1] = ' ';
        }
    }
}
