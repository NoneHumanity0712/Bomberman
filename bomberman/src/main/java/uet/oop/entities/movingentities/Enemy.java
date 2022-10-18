package uet.oop.entities.movingentities;

import java.io.FileNotFoundException;

import javafx.scene.image.Image;
import uet.oop.gameprocess.Map;

public abstract class Enemy extends MovingEntity {

    Image[] enemy_dead;

    public Enemy() throws FileNotFoundException{
        super();
    };

    public Enemy(int x, int y) throws FileNotFoundException{
        super(x, y);
    }

    public Enemy(int x, int y, char type) throws FileNotFoundException{
        super(x, y, type);
    }

    public Enemy(Enemy enemy) throws FileNotFoundException {
        super(enemy.getX(), enemy.getY(), enemy.getType());
        super.setDirection(enemy.getDirection());
    }

    @Override
    public void setupImage() throws FileNotFoundException {
        enemy_dead = new Image[4];
        for (int i = 1; i < 4; i++) {
            enemy_dead[i] = getImage("sprites/mob_dead" + i + ".png");
        }
    }

    abstract public void MOVE(Map map);

    @Override
    public void STEP_RIGHT() {
        
    }

    @Override
    public void STEP_LEFT() {
        
    }

    @Override
    public void STEP_UP() {
        
    }

    @Override
    public void STEP_DOWN() {
        
    }

    @Override
    public void DEAD() {
        switch (getDeadState()) {
            case 0:
                setDeadState(1);
                setImage(enemy_dead[0]);
                break;
            case 1:
                setDeadState(2);
                setImage(enemy_dead[1]);
                break;
            case 2:
                setDeadState(3);
                setImage(enemy_dead[2]);
                break;
            case 3:
                setImage(enemy_dead[3]);
                break;
        }
        setDeadBeforeTime(System.currentTimeMillis());
    }
}
