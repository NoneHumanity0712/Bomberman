package uet.oop.entities.movingentities;

import java.io.FileNotFoundException;

import javafx.scene.image.Image;

public class Bomber extends MovingEntity {
    private int bombs;

    private final int delaytime = 25;
    private final int speed = 1;

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

        bomber_images = new Image[4][3];
        setupImage();
        setSpeed(speed);
        setStep(getSpeed() / 8);
        setStepCount(0);
        setDelaytime(delaytime);
        setImage(bomber_images[getDirection()][getStepCount() % 2]);
    }

    public Bomber(int x, int y) throws FileNotFoundException {
        super(x, y, 'p');
        super.setDirection(0);
        bombs = 50;

        bomber_images = new Image[4][3];
        setupImage();
        setSpeed(speed);
        setStep(getSpeed() / 8);
        setStepCount(0);
        setDelaytime(delaytime);
        setImage(bomber_images[getDirection()][getStepCount() % 2]);
    }

    public Bomber(Bomber bomber) throws FileNotFoundException {
        super(bomber.getX(), bomber.getY(), 'p');
        bombs = 50;

        bomber_images = new Image[4][3];
        setupImage();
        setSpeed(speed);
        setStep(getSpeed() / 8);
        setStepCount(0);
        setDelaytime(delaytime);
        setImage(bomber_images[getDirection()][getStepCount() % 2]);
    }

    @Override
    public void STEP_RIGHT() {
        switch (super.getStepCount()) {
            case 0:
                setStepCount(1);
                setImage(bomber_images[getDirection()][0]);
                break;
            case 1:
                setStepCount(2);
                setImage(bomber_images[getDirection()][1]);
                break;
            case 2:
                setStepCount(3);
                setImage(bomber_images[getDirection()][2]);
                break;
            case 3:
                setStepCount(0);
                setImage(bomber_images[getDirection()][0]);
                break;
        }
        setTimebeforeeachstep(System.currentTimeMillis());
    }

    @Override
    public void STEP_LEFT() {
        switch (super.getStepCount()) {
            case 0:
                setStepCount(1);
                setImage(bomber_images[getDirection()][0]);
                break;
            case 1:
                setStepCount(2);
                setImage(bomber_images[getDirection()][1]);
                break;
            case 2:
                setStepCount(3);
                setImage(bomber_images[getDirection()][2]);
                break;
            case 3:
                setStepCount(0);
                setImage(bomber_images[getDirection()][0]);
                break;
        }
        setTimebeforeeachstep(System.currentTimeMillis());
    }

    @Override
    public void STEP_UP() {
        switch (super.getStepCount()) {
            case 0:
                setStepCount(1);
                setImage(bomber_images[getDirection()][0]);
                break;
            case 1:
                setStepCount(2);
                setImage(bomber_images[getDirection()][1]);
                break;
            case 2:
                setStepCount(3);
                setImage(bomber_images[getDirection()][2]);
                break;
            case 3:
                setStepCount(0);
                setImage(bomber_images[getDirection()][0]);
                break;
        }
        setTimebeforeeachstep(System.currentTimeMillis());
    }

    @Override
    public void STEP_DOWN() {
        switch (super.getStepCount()) {
            case 0:
                setStepCount(1);
                setImage(bomber_images[getDirection()][0]);
                break;
            case 1:
                setStepCount(2);
                setImage(bomber_images[getDirection()][1]);
                break;
            case 2:
                setStepCount(3);
                setImage(bomber_images[getDirection()][2]);
                break;
            case 3:
                setStepCount(0);
                setImage(bomber_images[getDirection()][0]);
                break;
        }
        setTimebeforeeachstep(System.currentTimeMillis());
    }

    @Override
    public void DEAD() {

    }

}
