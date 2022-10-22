package uet.oop.entities.movingentities;

import java.io.FileNotFoundException;
import java.util.Random;

import javafx.scene.image.Image;
import uet.oop.gameprocess.Map;

public class Doll extends Enemy {

    Image[][] doll_images = new Image[2][3];
    Image doll_dead;

    @Override
    public void setupImage() throws FileNotFoundException {
        super.setupImage();

        for (int i = 0; i < 3; i++) {
            doll_images[0][i] = getImage("doll_right" + i + ".png");
            doll_images[1][i] = getImage("doll_left" + i + ".png");
        }

        doll_dead = getImage("doll_dead.png");
        super.enemy_dead[0] = doll_dead;
    }

    public Doll(int x, int y) throws FileNotFoundException {
        super(x, y, '3');
        super.setDirection(0);

        setDelaytime(100);
        setSpeed(1);
        setStep(getSpeed() / 8);

        setupImage();
        setImage(doll_images[0][0]);
    }

    public Doll() throws FileNotFoundException {
        super();
        super.setType('3');
        super.setDirection(0);

        setDelaytime(100);
        setSpeed(1);
        setStep(getSpeed() / 8);

        setupImage();
        setImage(doll_images[0][0]);
    }

    public Doll(Doll enemy) throws FileNotFoundException {
        super(enemy);
        super.setType('3');
        super.setDirection(0);

        setDelaytime(100);
        setSpeed(1);
        setStep(getSpeed() / 8);

        setupImage();
        setImage(doll_images[0][0]);
    }

    @Override
    public void STEP_DOWN() {
        switch (super.getStepCount()) {
            case 0:
                setStepCount(1);
                setImage(doll_images[0][0]);
                break;
            case 1:
                setStepCount(2);
                setImage(doll_images[0][1]);
                break;
            case 2:
                setStepCount(3);
                setImage(doll_images[0][2]);
                break;
            case 3:
                setStepCount(0);
                setImage(doll_images[0][0]);
                break;
        }
        setTimebeforeeachstep(System.currentTimeMillis());
    }

    @Override
    public void STEP_LEFT() {
        switch (super.getStepCount()) {
            case 0:
                setStepCount(1);
                setImage(doll_images[1][0]);
                break;
            case 1:
                setStepCount(2);
                setImage(doll_images[1][1]);
                break;
            case 2:
                setStepCount(3);
                setImage(doll_images[1][2]);
                break;
            case 3:
                setStepCount(0);
                setImage(doll_images[1][0]);
                break;
        }
        setTimebeforeeachstep(System.currentTimeMillis());
    }

    @Override
    public void STEP_RIGHT() {
        switch (super.getStepCount()) {
            case 0:
                setStepCount(1);
                setImage(doll_images[0][0]);
                break;
            case 1:
                setStepCount(2);
                setImage(doll_images[0][1]);
                break;
            case 2:
                setStepCount(3);
                setImage(doll_images[0][2]);
                break;
            case 3:
                setStepCount(0);
                setImage(doll_images[0][0]);
                break;
        }
        setTimebeforeeachstep(System.currentTimeMillis());
    }

    @Override
    public void STEP_UP() {
        switch (super.getStepCount()) {
            case 0:
                setStepCount(1);
                setImage(doll_images[1][0]);
                break;
            case 1:
                setStepCount(2);
                setImage(doll_images[1][1]);
                break;
            case 2:
                setStepCount(3);
                setImage(doll_images[1][2]);
                break;
            case 3:
                setStepCount(0);
                setImage(doll_images[1][0]);
                break;
        }
        setTimebeforeeachstep(System.currentTimeMillis());
    }

    @Override
    public void MOVE(Map map) {
        if (!isAlive()) {
            return;
        }

        switch (getDirection()) {
            case 0:
                if (legal_move(map, getY(), getX() + 1)) {
                    setDirection(getDirection());
                    setOldX(getDoubleX());
                    setDoubleX(getDoubleX() + 1);
                    setX((int) getDoubleX());
                    setMoving(true);
                } else
                    setDirection((getDirection() + new Random().nextInt(3) + 1) % 4);
                break;
            case 1:
                if (legal_move(map, getY() + 1, getX())) {
                    setDirection(getDirection());
                    setOldY(getDoubleY());
                    setDoubleY(getDoubleY() + 1);
                    setY((int) getDoubleY());
                    setMoving(true);
                } else
                    setDirection((getDirection() + new Random().nextInt(3) + 1) % 4);
                break;
            case 2:
                if (legal_move(map, getY(), getX() - 1)) {
                    setDirection(getDirection());
                    setOldX(getDoubleX());
                    setDoubleX(getDoubleX() - 1);
                    setX((int) getDoubleX());
                    setMoving(true);
                } else
                    setDirection((getDirection() + new Random().nextInt(3) + 1) % 4);
                break;
            case 3:
                if (legal_move(map, getY() - 1, getX())) {
                    setDirection(getDirection());
                    setOldY(getDoubleY());
                    setDoubleY(getDoubleY() - 1);
                    setY((int) getDoubleY());
                    setMoving(true);
                } else
                    setDirection((getDirection() + new Random().nextInt(3) + 1) % 4);
                break;
            default:
                break;
        }
        setTimeBefore(System.currentTimeMillis());
        setTimebeforeeachstep(System.currentTimeMillis());
    }
}
