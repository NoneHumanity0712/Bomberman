package uet.oop.entities.movingentities.enemies;

import java.io.FileNotFoundException;

import uet.oop.gameprocess.Map;

public class Kondoria extends Enemy {

    @Override
    public void setupImage() throws FileNotFoundException {
        super.setupImage();

        for (int i = 0; i < 3; i++) {
            super.enemy_images[0][i] = getImage("kondoria_right" + i + ".png");
            super.enemy_images[1][i] = getImage("kondoria_left" + i + ".png");
        }
        super.enemy_dead[0] = getImage("kondoria_dead.png");
    }

    public Kondoria() throws FileNotFoundException {
		super();
        super.setType('5');
        super.setDirection(0);
        super.setAbleToPassWall(true);

        setDelaytime(200);
        setSpeed(1);
        setStep(getSpeed()/8);

        setupImage();
        setImage(enemy_images[0][0]);
	}

	public Kondoria(int x, int y) throws FileNotFoundException {
        super(x, y, '5');
        super.setDirection(0);
        super.setAbleToPassWall(true);

        setDelaytime(200);
        setSpeed(1);
        setStep(getSpeed()/8);

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
