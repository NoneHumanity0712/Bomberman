package uet.oop.entities;

import java.io.FileNotFoundException;
import java.util.Random;

import javafx.scene.image.Image;
import uet.oop.Map;

public class Balloom extends Enemy {

    private boolean dead;

    private long timeBefore;

    Image[][] balloom_images;
    Image balloom_dead;

    public void setTimeBefore(long timeBefore) {
        this.timeBefore = timeBefore;
    }

    public long getTimeBefore() {
        return timeBefore;
    }

    @Override
    public void setupImage() throws FileNotFoundException {
        for (int i = 0; i < 3; i++) {
            balloom_images[0][i] = getImage("sprites/balloom_right" + i + ".png");
            balloom_images[1][i] = getImage("sprites/balloom_left" + i + ".png");
        }
        balloom_dead = getImage("sprites/balloom_dead.png");
    }

    public Balloom() throws FileNotFoundException {
        super();
        super.setDirection(0);
        dead = false;

        setSpeed(0.5);
        setStep(getSpeed() / 8);

        balloom_images = new Image[2][3];
        setupImage();
        setImage(balloom_images[0][getStepCount() % 2]);
    }

    public Balloom(Balloom balloom) throws FileNotFoundException {
        super(balloom);
        super.setDirection(0);
        setSpeed(0.5);
        setStep(getSpeed() / 8);
        setStepCount(0);

        balloom_images = new Image[2][3];
        setupImage();
        setImage(balloom_images[0][getStepCount() % 2]);
    }

    public Balloom(int x, int y) throws FileNotFoundException {
        super(x, y, '1');
        super.setDirection(0);
        setSpeed(0.5);
        setStep(getSpeed() / 8);
        setStepCount(0);

        balloom_images = new Image[2][3];
        setupImage();
        setImage(balloom_images[0][getStepCount() % 2]);

    }

    public void MOVE(Map map) {
        Random random = new Random();
        int nextDirection = random.nextInt(4) - 1;

        switch (nextDirection) {
            case 0:
                if (legal_move(map, getY(), getX() + 1)) {
                    setDirection(nextDirection);
                    setOldX(getDoubleX());
                    setDoubleX(getDoubleX() + 1);
                    setMoving(true);
                }
                break;
            case 1:
                if (legal_move(map, getY() + 1, getX())) {
                    setDirection(nextDirection);
                    setOldY(getDoubleY());
                    setDoubleY(getDoubleY() + 1);
                    setMoving(true);
                }
                break;
            case 2:
                if (legal_move(map, getY(), getX() - 1)) {
                    setDirection(nextDirection);
                    setOldX(getDoubleX());
                    setDoubleX(getDoubleX() - 1);
                    setMoving(true);
                }
                break;
            case 3:
                if (legal_move(map, getY() - 1, getX())) {
                    setDirection(nextDirection);
                    setOldY(getDoubleY());
                    setDoubleY(getDoubleY() - 1);
                    setMoving(true);
                }
                break;
            default:
                break;
        }

        timeBefore = System.currentTimeMillis();
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
        setImage(balloom_images[getDirection() % 2][getStepCount() % 2]);
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
        setImage(balloom_images[getDirection() % 2][getStepCount() % 2]);
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
        setImage(balloom_images[getDirection() % 2][getStepCount() % 2]);
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
        setImage(balloom_images[getDirection() % 2][getStepCount() % 2]);
    }

}
