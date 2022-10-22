package uet.oop.entities.movingentities;

import java.io.FileNotFoundException;

import javafx.scene.image.Image;
import uet.oop.gameprocess.Map;

public class Minvo extends Enemy {

    Image[][] minvo_images = new Image[2][3];
    Image minvo_dead;

    @Override
    public void setupImage() throws FileNotFoundException {
        super.setupImage();

        for (int i = 0; i < 3; i++) {
            super.enemy_images[0][i] = getImage("minvo_right" + i + ".png");
            super.enemy_images[1][i] = getImage("minvo_left" + i + ".png");
        }
        super.enemy_dead[0] = getImage("minvo_dead.png");
    }

    public Minvo() throws FileNotFoundException {
        super();
        super.setType('4');
        super.setDirection(0);

        setDelaytime(100);
        setSpeed(2);
        setStep(getSpeed() / 8);
        setStepCount(0);

        setupImage();
        setImage(enemy_images[0][0]);
    }

    public Minvo(int x, int y) throws FileNotFoundException {
        super(x, y, '4');
        super.setDirection(0);

        setDelaytime(100);
        setSpeed(2);
        setStep(getSpeed() / 8);
        setStepCount(0);

        setupImage();
        setImage(enemy_images[0][0]);
    }

    public Minvo(Enemy enemy) throws FileNotFoundException {
        super(enemy);
        super.setType('4');
        super.setDirection(0);

        setDelaytime(100);
        setSpeed(2);
        setStep(getSpeed() / 8);
        setStepCount(0);

        setupImage();
        setImage(enemy_images[0][0]);
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
