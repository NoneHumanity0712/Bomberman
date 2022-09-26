package uet.oop;

public class Enemy extends Entity{
    private int speed;
    private int direction_x;
    private int direction_y;

    Enemy(){
        direction_x = -1;
        direction_y = 0;
    }

    public void UP() {
        direction_x = 0;
        direction_y = -1;
    }

    
}
