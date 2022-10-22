package uet.oop.entities;

import java.io.FileNotFoundException;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.gameprocess.HandleImage;

public abstract class Entity implements HandleImage{
    public final static int spriteSize = 16; // in pixel

    public final static int SCALE = 3;

    public final static int size = spriteSize * SCALE;
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
     * 3 Doll,
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
        render(context, image, x, y);
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