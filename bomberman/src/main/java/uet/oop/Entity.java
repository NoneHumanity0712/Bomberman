package uet.oop;

import java.io.FileNotFoundException;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public abstract class Entity {
    public final static int SCALE = 3;

    /**
     * x position from the top left corner.
     */
    private int x;

    /**
     * y position from the top left corner.
     */
    private int y;

    protected Image image;

    /**
     * # Wall,
     * * Brick,
     * x Portal,
     * p Bomber,
     * 1 Balloom,
     * 2 Oneal,
     * b Bomb Item,
     * f Flame Item,
     * s Speed Item.
     * 
     * @throws FileNotFoundException
     */
    public abstract void setupImage() throws FileNotFoundException;

    public void setImage(Image image) {
        this.image = image;
    }

    public Image getImage() {
        return image;
    }

    public void render(GraphicsContext context) {
        context.drawImage(image, x * SCALE, y * SCALE,
                image.getWidth() * SCALE, image.getHeight() * SCALE);
    }

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
    };

    public Entity(int x, int y) {
        this.x = x;
        this.y = y;
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