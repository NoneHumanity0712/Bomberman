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

    public void Explode(Map map) {
        explode = true;
        char[][] temp = map.getMap();
        int i = this.getY();
        int j = this.getX();

        temp[i][j] = ' ';
        if (map.portal.getY() == i && map.portal.getX() == j){
            map.portal.APPEAR();
        }

        if (temp[i - 1][j] == '*') {
            temp[i - 1][j] = ' ';
        } 
        if (map.portal.getY() == i - 1 && map.portal.getX() == j) {
            map.portal.APPEAR();
        }

        if (temp[i + 1][j] == '*') {
            temp[i + 1][j] = ' ';
        } 
        if (map.portal.getY() == i + 1 && map.portal.getX() == j) {
            map.portal.APPEAR();
        }

        if (temp[i][j - 1] == '*') {
            temp[i][j - 1] = ' ';
        } 
        if (map.portal.getY() == i && map.portal.getX() == j - 1) {
            map.portal.APPEAR();
        }

        if (temp[i][j + 1] == '*') {
            temp[i][j + 1] = ' ';
        } 
        if (map.portal.getY() == i && map.portal.getX() == j + 1) {
            map.portal.APPEAR();
        }
    }
}
