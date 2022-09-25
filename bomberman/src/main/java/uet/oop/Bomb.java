package uet.oop;

public class Bomb extends Entity {
    public Bomb(int x, int y) {
        super(x, y);
    }

    public Bomb(Entity e) {
        super(e);
    }

    public void Explode(Map map) {
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

        if (temp[i - 1][j] == 'b') {
            Bomb newBomb = new Bomb(i - 1, j);
            newBomb.Explode(map);
        }
        if (temp[i + 1][j] == 'b') {
            Bomb newBomb = new Bomb(i + 1, j);
            newBomb.Explode(map);
        }
        if (temp[i][j - 1] == 'b') {
            Bomb newBomb = new Bomb(i, j - 1);
            newBomb.Explode(map);
        }
        if (temp[i][j + 1] == 'b') {
            Bomb newBomb = new Bomb(i, j + 1);
            newBomb.Explode(map);
        }
    }
}
