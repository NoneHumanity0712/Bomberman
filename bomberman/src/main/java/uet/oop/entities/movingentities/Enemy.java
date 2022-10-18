package uet.oop.entities.movingentities;

import uet.oop.gameprocess.Map;

public abstract class Enemy extends MovingEntity {
    public Enemy(){
        super();
    };

    public Enemy(int x, int y){
        super(x, y);
        
    }

    public Enemy(int x, int y, char type){
        super(x, y, type);
        
    }

    public Enemy(Enemy enemy) {
        super(enemy.getX(), enemy.getY(), enemy.getType());
        super.setDirection(enemy.getDirection());

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
        
    }
}
