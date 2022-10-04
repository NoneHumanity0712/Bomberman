package uet.oop;

public class Bomber extends MovingEntity {
    private int bombs;
    private int direction;

    private boolean dead;

    public int getBombs() {
        return bombs;
    }

    public void setBombs(int bombs) {
        this.bombs = bombs;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getDirection() {
        return direction;
    }

    public Bomber() {
        super(1, 1, 'p');
        direction = 0;
        bombs = 50;
        dead = false;
    }

    public Bomber(int x, int y) {
        super(x, y, 'p');
        direction = 0;
        bombs = 50;
        dead = false;
    }

    public Bomber(Bomber bomber) {
        super(bomber.getX(), bomber.getY(), 'p');
        direction = 0;
        bombs = 50;
        dead = false;
    }

    public boolean isDead() {
        return dead;
    }

    public Entity placeBomb(Map map) {
        Entity en = new Entity(this.getX(), this.getY());
        if (bombs < 0) {
            System.err.println("There are no more bombs!");
            return en;
        }
        bombs = bombs - 1;

        return en;
    }
}
