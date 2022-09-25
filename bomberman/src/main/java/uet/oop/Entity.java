package uet.oop;

/**
 * Entity
 */
public class Entity {
    /**
     * x position from the top left corner.
     */
    private int x; 

    /**
     * y position from the top left corner.
     */
    private int y;

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
    
    public Entity() {
        this.x = -1;
        this.y = -1;
    }

    public Entity(int x, int y){
        this.x = x;
        this.y = y;
    }

    public Entity(Entity e){
        this.x = e.getX();
        this.y = e.getY();
    }
    
}