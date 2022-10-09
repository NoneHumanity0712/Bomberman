package uet.oop;

import java.io.FileNotFoundException;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Bomber extends MovingEntity implements HandleImage {
    private int bombs;
    private int direction;
    private boolean dead;

    Image[] bomber_right;
    Image[] bomber_down;
    Image[] bomber_left;
    Image[] bomber_up;
    Image[] bomber_dead;
    

    @Override
    public void setupImage() throws FileNotFoundException {
        bomber_right = new Image[3];
        for (int i = 0; i < 3; i++) {
            bomber_right[i] = getImage("sprites/player_right_" + String.valueOf(i) + ".png");
        }

        bomber_down = new Image[3];
        for (int i = 0; i < 3; i++) {
            bomber_down[i] = getImage("sprites/player_down_" + String.valueOf(i) + ".png");
        }

        bomber_left = new Image[3];
        for (int i = 0; i < 3; i++) {
            bomber_left[i] = getImage("sprites/player_left_" + String.valueOf(i) + ".png");
        }

        bomber_up = new Image[3];
        for (int i = 0; i < 3; i++) {
            bomber_up[i] = getImage("sprites/player_up_" + String.valueOf(i) + ".png");
        }

        bomber_dead = new Image[3];
        for (int i = 0; i < 3; i++) {
            bomber_dead[i] = getImage("sprites/player_dead" + String.valueOf(i) + ".png");
        }
    }

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

    public Bomber() throws FileNotFoundException {
        super(1, 1, 'p');
        direction = 0;
        bombs = 50;
        dead = false;

        setupImage();
        setSpeed(16);
    }

    public Bomber(int x, int y) throws FileNotFoundException {
        super(x, y, 'p');
        direction = 0;
        bombs = 50;
        dead = false;

        setupImage();
        setSpeed(16);
    }

    public Bomber(Bomber bomber) throws FileNotFoundException {
        super(bomber.getX(), bomber.getY(), 'p');
        direction = 0;
        bombs = 50;
        dead = false;

        setupImage();
        setSpeed(16);
    }

    public boolean isDead() {
        return dead;
    }

    public Bomb placeBomb(Map map) {
        Bomb en = new Bomb(this.getX(), this.getY());
        if (bombs < 0) {
            System.err.println("There are no more bombs!");
            return en;
        }
        bombs = bombs - 1;

        return en;
    }

    @Override
    public void STEP_RIGHT() {
        switch (super.getStepCount()) {
            case 0:
                setImage(bomber_right[0]);
                setStepCount(1);
                break;
            case 1:
                setImage(bomber_right[1]);
                setStepCount(2);
                break;
            case 2:
                setImage(bomber_right[2]);
                setStepCount(3);
                break;
            default:
                setImage(bomber_right[1]);
                setStepCount(0);
                break;
        }
    }

    @Override
    public void STEP_LEFT() {
        switch (super.getStepCount()) {
            case 0:
                setImage(bomber_left[0]);
                setStepCount(1);
                break;
            case 1:
                setImage(bomber_left[1]);
                setStepCount(2);
                break;
            case 2:
                setImage(bomber_left[2]);
                setStepCount(3);
                break;
            default:
                setImage(bomber_left[1]);
                setStepCount(0);
                break;
        }
    }

    @Override
    public void STEP_UP() {
        switch (super.getStepCount()) {
            case 0:
                setImage(bomber_up[0]);
                setStepCount(1);
                break;
            case 1:
                setImage(bomber_up[1]);
                setStepCount(2);
                break;
            case 2:
                setImage(bomber_up[2]);
                setStepCount(3);
                break;
            default:
                setImage(bomber_up[1]);
                setStepCount(0);
                break;
        }
    }

    @Override
    public void STEP_DOWN() {
        switch (super.getStepCount()) {
            case 0:
                setImage(bomber_down[0]);
                setStepCount(1);
                break;
            case 1:
                setImage(bomber_down[1]);
                setStepCount(2);
                break;
            case 2:
                setImage(bomber_down[2]);
                setStepCount(3);
                break;
            default:
                setImage(bomber_down[1]);
                setStepCount(0);
                break;
        }
    }

    @Override
    public void DEAD() {
        // TODO Auto-generated method stub

    }

}
