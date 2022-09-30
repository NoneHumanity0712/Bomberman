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
}