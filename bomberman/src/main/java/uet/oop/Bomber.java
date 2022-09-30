package uet.oop;

public class Bomber extends MovingEntity {
    private int bombs;

    public int getBombs() {
        return bombs;
    }

    public void setBombs(int bombs) {
        this.bombs = bombs;
    }

    public Bomber() {
        super(1, 1, 'p');
        bombs = 15;
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
