package uet.oop.entities.movingentities;

import java.io.FileNotFoundException;

import javafx.scene.image.Image;
import uet.oop.gameprocess.Map;

public class Oneal extends Enemy {

    Image[][] oneal_images;
    Image oneal_dead;

    public Oneal() throws FileNotFoundException {
        super();
        super.setType('2');
        super.setDirection(0);

        setDelaytime(100);
        setSpeed(1);
        setStep(getSpeed() / 8);
        setStepCount(0);

        oneal_images = new Image[2][3];
        setupImage();
        setImage(oneal_images[0][getStepCount() % 2]);
        super.enemy_dead[0] = oneal_dead;
    }

    public Oneal(int x, int y) throws FileNotFoundException {
        super(x, y);
        super.setType('2');
        super.setDirection(0);

        setDelaytime(100);
        setSpeed(1);
        setStep(getSpeed() / 8);
        setStepCount(0);

        oneal_images = new Image[2][3];
        setupImage();
        setImage(oneal_images[0][getStepCount() % 2]);
        super.enemy_dead[0] = oneal_dead;
    }

    public Oneal(Oneal oneal) throws FileNotFoundException {
        super(oneal);
        super.setType('2');
        super.setDirection(0);

        setDelaytime(100);
        setSpeed(1);
        setStep(getSpeed() / 8);
        setStepCount(0);

        oneal_images = new Image[2][3];
        setupImage();
        setImage(oneal_images[0][getStepCount() % 2]);
        super.enemy_dead[0] = oneal_dead;
    }

    @Override
    public void setupImage() throws FileNotFoundException {
        super.setupImage();

        for (int i = 0; i < 3; i++) {
            oneal_images[0][i] = getImage("sprites/oneal_right" + i + ".png");
            oneal_images[1][i] = getImage("sprites/oneal_left" + i + ".png");
        }

        oneal_dead = getImage("sprites/oneal_dead.png");
    }

    @Override
    public void STEP_DOWN() {
        switch (super.getStepCount()) {
            case 0:
                setStepCount(1);
                setImage(oneal_images[0][0]);
                break;
            case 1:
                setStepCount(2);
                setImage(oneal_images[0][1]);
                break;
            case 2:
                setStepCount(3);
                setImage(oneal_images[0][2]);
                break;
            case 3:
                setStepCount(0);
                setImage(oneal_images[0][0]);
                break;
        }
        setTimebeforeeachstep(System.currentTimeMillis());
    }

    @Override
    public void STEP_LEFT() {
        switch (super.getStepCount()) {
            case 0:
                setStepCount(1);
                setImage(oneal_images[1][0]);
                break;
            case 1:
                setStepCount(2);
                setImage(oneal_images[1][1]);
                break;
            case 2:
                setStepCount(3);
                setImage(oneal_images[1][2]);
                break;
            case 3:
                setStepCount(0);
                setImage(oneal_images[1][0]);
                break;
        }
        setTimebeforeeachstep(System.currentTimeMillis());
    }

    @Override
    public void STEP_RIGHT() {
        switch (super.getStepCount()) {
            case 0:
                setStepCount(1);
                setImage(oneal_images[0][0]);
                break;
            case 1:
                setStepCount(2);
                setImage(oneal_images[0][1]);
                break;
            case 2:
                setStepCount(3);
                setImage(oneal_images[0][2]);
                break;
            case 3:
                setStepCount(0);
                setImage(oneal_images[0][0]);
                break;
        }
        setTimebeforeeachstep(System.currentTimeMillis());
    }

    @Override
    public void STEP_UP() {
        switch (super.getStepCount()) {
            case 0:
                setStepCount(1);
                setImage(oneal_images[1][0]);
                break;
            case 1:
                setStepCount(2);
                setImage(oneal_images[1][1]);
                break;
            case 2:
                setStepCount(3);
                setImage(oneal_images[1][2]);
                break;
            case 3:
                setStepCount(0);
                setImage(oneal_images[1][0]);
                break;
        }
        setTimebeforeeachstep(System.currentTimeMillis());
    }

    @Override
    public void MOVE(Map map) {
        if (!isAlive()) {
            return;
        }

        if (this.getX() < map.getBomber().getX()
                && (legal_move(map, getY(), getX() + 1))) {

            setDirection(0); // go right
            setOldX(getDoubleX());
            setDoubleX(getDoubleX() + 1);
            setX((int) getDoubleX());
            setMoving(true);
        }

        else if (this.getX() > map.getBomber().getX()
                && (legal_move(map, getY(), getX() - 1))) {

            setDirection(2); // go left
            setOldX(getDoubleX());
            setDoubleX(getDoubleX() - 1);
            setX((int) getDoubleX());
            setMoving(true);
        }

        else if (this.getY() < map.getBomber().getY()
                && (legal_move(map, getY() + 1, getX()))) {

            setDirection(1); // go down
            setOldY(getDoubleY());
            setDoubleY(getDoubleY() + 1);
            setY((int) getDoubleY());
            setMoving(true);

        }
        
        else if (this.getY() > map.getBomber().getY()
                && (legal_move(map, getY() - 1, getX()))) {
            setDirection(3); //go up
            setOldY(getDoubleY());
            setDoubleY(getDoubleY() - 1);
            setY((int) getDoubleY());
            setMoving(true);
        }
        setTimeBefore(System.currentTimeMillis());
        setTimebeforeeachstep(System.currentTimeMillis());
    }
}
