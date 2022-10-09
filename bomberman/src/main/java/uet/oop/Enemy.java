package uet.oop;

public class Enemy extends MovingEntity {
    private int speed;
    private int direction;

    public Enemy(){
        speed = 1;
        direction = 0;
    };

    public Enemy(int x, int y){
        super(x, y);
        direction = 0;
    }

    public Enemy(int x, int y, char type){
        super(x, y, type);
        direction = 0;
    }

    public Enemy(Enemy enemy) {
        super(enemy.getX(), enemy.getY(), enemy.getType());
        this.speed = enemy.speed;
        this.direction = enemy.direction;

    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getSpeed() {
        return speed;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    @Override
    public void STEP_RIGHT() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void STEP_LEFT() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void STEP_UP() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void STEP_DOWN() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void DEAD() {
        // TODO Auto-generated method stub
        
    }
}
