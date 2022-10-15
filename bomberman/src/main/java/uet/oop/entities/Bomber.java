package uet.oop.entities;

import java.io.FileNotFoundException;

import javafx.scene.image.Image;
import uet.oop.Map;

public class Bomber extends MovingEntity {
    private int bombs;
    private boolean dead;

    private Image[] bomber_dead;
    private Image[][] bomber_images;
    
    @Override
    public void setupImage() throws FileNotFoundException {
        for (int i = 0; i < 3; i++) {
            bomber_images[0][i] = getImage("sprites/player_right_" + String.valueOf(i) + ".png");
            bomber_images[1][i] = getImage("sprites/player_down_" + String.valueOf(i) + ".png");
            bomber_images[2][i] = getImage("sprites/player_left_" + String.valueOf(i) + ".png");
            bomber_images[3][i] = getImage("sprites/player_up_" + String.valueOf(i) + ".png");
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

    public Bomber() throws FileNotFoundException {
        super(1, 1, 'p');
        super.setDirection(0);
        bombs = 50;
        dead = false;

        bomber_images = new Image[4][3];
        setupImage();
        setSpeed(1);
        setStep(getSpeed()/8);
        setStepCount(0);
        setImage(bomber_images[getDirection()][getStepCount() % 2]);
    }

    public Bomber(int x, int y) throws FileNotFoundException {
        super(x, y, 'p');
        super.setDirection(0);
        bombs = 50;
        dead = false;

        bomber_images = new Image[4][3];
        setupImage();
        setSpeed(1);
        setStep(getSpeed()/8);
        setStepCount(0);    
        setImage(bomber_images[getDirection()][getStepCount() % 2]);
    }

    public Bomber(Bomber bomber) throws FileNotFoundException {
        super(bomber.getX(), bomber.getY(), 'p');
        bombs = 50;
        dead = false;

        bomber_images = new Image[4][3];
        setupImage();
        setSpeed(1);
        setStep(getSpeed()/8);
        setStepCount(0);
        setImage(bomber_images[getDirection()][getStepCount() % 2]);
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
                setStepCount(1);
                break;
            case 1:
                setStepCount(2);
                break;
            case 2:
                setStepCount(3);
                break;
            case 3:
                setStepCount(0);
                break;
        }
        setImage(bomber_images[getDirection()][getStepCount() % 2]);
    }

    @Override
    public void STEP_LEFT() {
        switch (super.getStepCount()) {
            case 0:
                setStepCount(1);
                break;
            case 1:
                setStepCount(2);
                break;
            case 2:
                setStepCount(3);
                break;
            case 3:
                setStepCount(0);
                break;
        }
        setImage(bomber_images[getDirection()][getStepCount() % 2]);
    }

    @Override
    public void STEP_UP() {
        switch (super.getStepCount()) {
            case 0:
                setStepCount(1);
                break;
            case 1:
                setStepCount(2);
                break;
            case 2:
                setStepCount(3);
                break;
            case 3:
                setStepCount(0);
                break;
        }
        setImage(bomber_images[getDirection()][getStepCount() % 2]);
    }

    @Override
    public void STEP_DOWN() {
        switch (super.getStepCount()) {
            case 0:
                setStepCount(1);
                break;
            case 1:
                setStepCount(2);
                break;
            case 2:
                setStepCount(3);
                break;
            case 3:
                setStepCount(0);
                break;
        }
        setImage(bomber_images[getDirection()][getStepCount() % 2]);
    }

    @Override
    public void DEAD() {

    }

}
