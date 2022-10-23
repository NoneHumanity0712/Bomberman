package uet.oop.entities.movingentities.enemies;

import java.io.FileNotFoundException;

import javafx.scene.image.Image;
import uet.oop.gameprocess.Map;

public class Oneal extends Enemy {

    Image[][] oneal_images = new Image[2][3];
    Image oneal_dead;

    public Oneal() throws FileNotFoundException {
        super();
        super.setType('2');
        super.setDirection(0);
        super.setAbleToPassWall(false);

        setDelaytime(100);
        setSpeed(1);
        setStep(getSpeed() / 8);
        setStepCount(0);

        setupImage();
        setImage(enemy_images[0][0]);
    }

    public Oneal(int x, int y) throws FileNotFoundException {
        super(x, y, '2');
        super.setDirection(0);
        super.setAbleToPassWall(false);

        setDelaytime(100);
        setSpeed(1);
        setStep(getSpeed() / 8);
        setStepCount(0);

        setupImage();
        setImage(enemy_images[0][0]);
    }

    public Oneal(Oneal oneal) throws FileNotFoundException {
        super(oneal);
        super.setType('2');
        super.setDirection(0);
        super.setAbleToPassWall(false);

        setDelaytime(100);
        setSpeed(1);
        setStep(getSpeed() / 8);
        setStepCount(0);

        setupImage();
        setImage(enemy_images[0][0]);
    }

    @Override
    public void setupImage() throws FileNotFoundException {
        super.setupImage();

        for (int i = 0; i < 3; i++) {
            super.enemy_images[0][i] = getImage("oneal_right" + i + ".png");
            super.enemy_images[1][i] = getImage("oneal_left" + i + ".png");
        }

        super.enemy_dead[0] = getImage("oneal_dead.png");
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
            setDirection(3); // go up
            setOldY(getDoubleY());
            setDoubleY(getDoubleY() - 1);
            setY((int) getDoubleY());
            setMoving(true);
        }
        setTimeBefore(System.currentTimeMillis());
        setTimebeforeeachstep(System.currentTimeMillis());
    }
}
