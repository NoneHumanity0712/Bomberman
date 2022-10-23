package uet.oop.entities.movingentities;

import java.io.FileNotFoundException;

import javafx.scene.image.Image;

public class Bomber extends MovingEntity {
    private int bombs;
    private int maxBombs;
    private int lifes;

    private final int delaytime = 20;
    private final int startSpeed = 1;
    private int bombRange = 1;

    private Image[] bomber_dead;
    private Image[][] bomber_images;

    @Override
    public void setupImage() throws FileNotFoundException {
        for (int i = 0; i < 3; i++) {
            bomber_images[0][i] = getImage("player_right_" + i + ".png");
            bomber_images[1][i] = getImage("player_down_" + i + ".png");
            bomber_images[2][i] = getImage("player_left_" + i + ".png");
            bomber_images[3][i] = getImage("player_up_" + i + ".png");
        }

        bomber_dead = new Image[3];
        for (int i = 0; i < 3; i++) {
            bomber_dead[i] = getImage("player_dead" + i + ".png");
        }
    }

    public int getBombs() {
        return bombs;
    }

    public void setBombs(int bombs) {
        this.bombs = bombs;
    }

    public void setBombRange(int bombRange) {
        this.bombRange = bombRange;
    }

    public int getBombRange() {
        return bombRange;
    }

    public int getLifes() {
        return lifes;
    }

    public void setLifes(int lifes) {
        this.lifes = lifes;
    }

    public void setMaxBombs(int maxBombs) {
        this.maxBombs = maxBombs;
    }

    public int getMaxBombs() {
        return maxBombs;
    }

    public void setPosition(int x, int y) {
        this.setX(x);
        this.setOldX(x);
        this.setDoubleX(x);

        this.setY(y);
        this.setOldY(y);
        this.setDoubleY(y);

        setImage(bomber_images[0][0]);
        setAlive(true);
        setAbleToPassWall(false);
        super.setAbleToPassWall(false);

        setBombRange(1);
        setMaxBombs(1);
    }

    public Bomber() throws FileNotFoundException {
        super(1, 1, 'p');
        super.setDirection(0);
        super.setAbleToPassWall(false);

        bombs = 20;
        maxBombs = 1;
        lifes = 5;

        bomber_images = new Image[4][3];
        setupImage();
        setSpeed(startSpeed);
        setStep(getSpeed() / 8);
        setStepCount(0);
        setDeadState(0);
        setDelaytime(delaytime);
        setTimeBefore(System.currentTimeMillis());
        setImage(bomber_images[getDirection()][getStepCount() % 2]);
    }

    public Bomber(int x, int y) throws FileNotFoundException {
        super(x, y, 'p');
        super.setDirection(0);
        super.setAbleToPassWall(false);

        bombs = 20;
        maxBombs = 1;
        lifes = 5;

        bomber_images = new Image[4][3];
        setupImage();
        setSpeed(startSpeed);
        setStep(getSpeed() / 8);
        setStepCount(0);
        setDeadState(0);
        setDelaytime(delaytime);
        setTimeBefore(System.currentTimeMillis());
        setImage(bomber_images[getDirection()][getStepCount() % 2]);
    }

    public Bomber(Bomber bomber) throws FileNotFoundException {
        super(bomber.getX(), bomber.getY(), 'p');
        super.setAbleToPassWall(false);

        bombs = 20;
        maxBombs = 1;
        lifes = 5;

        bomber_images = new Image[4][3];
        setupImage();
        setSpeed(startSpeed);
        setStep(getSpeed() / 8);
        setDeadState(0);
        setStepCount(0);
        setDelaytime(delaytime);
        setTimeBefore(System.currentTimeMillis());
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
        switch (getDeadState()) {
            case 0:
                setDeadState(1);
                setImage(bomber_dead[0]);
                break;
            case 1:
                setDeadState(2);
                setImage(bomber_dead[1]);
                break;
            case 2:
                setDeadState(3);
                setImage(bomber_dead[2]);
                break;
            case 3:
                setImage(null);
                break;
        }
        setDeadBeforeTime(System.currentTimeMillis());
    }

}
