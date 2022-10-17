package uet.oop.entities.movingentities;

import java.io.FileNotFoundException;
import java.util.Random;

import javafx.scene.image.Image;
import uet.oop.gameprocess.Map;

public class Balloom extends Enemy {

    private long timeBefore; //time before each move

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
        super.setType('1');
        super.setDirection(0);
        
        setDelaytime(150);
        setSpeed(0.5);
        setStep(getSpeed() / 8);

        balloom_images = new Image[2][3];
        setupImage();
        setImage(balloom_images[0][getStepCount() % 2]);
    }

    public Balloom(Balloom balloom) throws FileNotFoundException {
        super(balloom);
        super.setType('1');
        super.setDirection(0);

        setDelaytime(100);
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

        setDelaytime(100);
        setSpeed(0.5);
        setStep(getSpeed() / 8);
        setStepCount(0);

        balloom_images = new Image[2][3];
        setupImage();
        setImage(balloom_images[0][getStepCount() % 2]);

    }

    public void MOVE(Map map) {
        if (!isAlive()) {
            DEAD();
            return;
        }

        Random random = new Random();
        int nextDirection = random.nextInt(4);

        switch (nextDirection) {
            case 0:
                if (legal_move(map, getY(), getX() + 1)) {
                    setDirection(nextDirection);
                    setOldX(getDoubleX());
                    setDoubleX(getDoubleX() + 1);
                    setX((int) getDoubleX());
                    setMoving(true);
                }
                break;
            case 1:
                if (legal_move(map, getY() + 1, getX())) {
                    setDirection(nextDirection);
                    setOldY(getDoubleY());
                    setDoubleY(getDoubleY() + 1);
                    setY((int) getDoubleY());
                    setMoving(true);
                }
                break;
            case 2:
                if (legal_move(map, getY(), getX() - 1)) {
                    setDirection(nextDirection);
                    setOldX(getDoubleX());
                    setDoubleX(getDoubleX() - 1);
                    setX((int) getDoubleX());
                    setMoving(true);
                }
                break;
            case 3:
                if (legal_move(map, getY() - 1, getX())) {
                    setDirection(nextDirection);
                    setOldY(getDoubleY());
                    setDoubleY(getDoubleY() - 1);
                    setY((int) getDoubleY());
                    setMoving(true);
                }
                break;
            default:
                break;
        }

        timeBefore = System.currentTimeMillis();
        setTimebeforeeachstep(System.currentTimeMillis());
    }

    @Override
    public void STEP_LEFT() {
        switch (super.getStepCount()) {
            case 0:
                setStepCount(1);
                setImage(balloom_images[1][0]);
                break;
            case 1:
                setStepCount(2);
                setImage(balloom_images[1][1]);
                break;
            case 2:
                setStepCount(3);
                setImage(balloom_images[1][2]);
                break;
            case 3:
                setStepCount(0);
                setImage(balloom_images[1][0]);
                break;
        }
        setTimebeforeeachstep(System.currentTimeMillis());
    }

    @Override
    public void STEP_RIGHT() {
        switch (super.getStepCount()) {
            case 0:
                setStepCount(1);
                setImage(balloom_images[0][0]);
                break;
            case 1:
                setStepCount(2);
                setImage(balloom_images[0][1]);
                break;
            case 2:
                setStepCount(3);
                setImage(balloom_images[0][2]);
                break;
            case 3:
                setStepCount(0);
                setImage(balloom_images[0][0]);
                break;
        }
        setTimebeforeeachstep(System.currentTimeMillis());
    }

    @Override
    public void STEP_DOWN() {
        switch (super.getStepCount()) {
            case 0:
                setStepCount(1);
                setImage(balloom_images[0][0]);
                break;
            case 1:
                setStepCount(2);
                setImage(balloom_images[0][1]);
                break;
            case 2:
                setStepCount(3);
                setImage(balloom_images[0][2]);
                break;
            case 3:
                setStepCount(0);
                setImage(balloom_images[0][0]);
                break;
        }
        setTimebeforeeachstep(System.currentTimeMillis());
    }

    @Override
    public void STEP_UP() {
        switch (super.getStepCount()) {
            case 0:
                setStepCount(1);
                setImage(balloom_images[1][0]);
                break;
            case 1:
                setStepCount(2);
                setImage(balloom_images[1][1]);
                break;
            case 2:
                setStepCount(3);
                setImage(balloom_images[1][2]);
                break;
            case 3:
                setStepCount(0);
                setImage(balloom_images[1][0]);
                break;
        }
        setTimebeforeeachstep(System.currentTimeMillis());
    }

    @Override
    public void DEAD() {
        setImage(balloom_dead);
        
        setTimesincedead(System.currentTimeMillis());
    }
}
