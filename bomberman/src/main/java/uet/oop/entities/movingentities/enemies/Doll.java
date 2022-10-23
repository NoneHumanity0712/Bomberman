package uet.oop.entities.movingentities.enemies;

import java.io.FileNotFoundException;
import java.util.Random;

import uet.oop.gameprocess.Map;

public class Doll extends Enemy {
    @Override
    public void setupImage() throws FileNotFoundException {
        super.setupImage();

        for (int i = 0; i < 3; i++) {
            super.enemy_images[0][i] = getImage("doll_right" + i + ".png");
            super.enemy_images[1][i] = getImage("doll_left" + i + ".png");
        }

        super.enemy_dead[0] = getImage("doll_dead.png");
    }

    public Doll() throws FileNotFoundException {
        super();
        super.setType('3');
        super.setDirection(0);
        super.setAbleToPassWall(false);

        setDelaytime(100);
        setSpeed(1);
        setStep(getSpeed() / 8);

        setupImage();
        setImage(enemy_images[0][0]);
    }

    public Doll(int x, int y) throws FileNotFoundException {
        super(x, y, '3');
        super.setDirection(0);
        super.setAbleToPassWall(false);

        setDelaytime(100);
        setSpeed(1);
        setStep(getSpeed() / 8);

        setupImage();
        setImage(enemy_images[0][0]);
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
