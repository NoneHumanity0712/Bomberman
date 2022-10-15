package uet.oop.demogame;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

public class KeyHandler implements EventHandler<KeyEvent> {

    public final int[] DRIECTIONS = { 0, 1, 2, 3 }; // right, down, left, up
    private int direction;

    private boolean keyPress;

    public boolean isKeyPress() {
        return keyPress;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    @Override
    public void handle(KeyEvent event) {
        if (KeyEvent.KEY_PRESSED.equals(event.getEventType())) {
            switch (event.getCode()) {
                case RIGHT:
                case D:
                    setDirection(0);
                    System.out.println("RIGHT");
                    break;

                case DOWN:
                case S:
                    setDirection(1);
                    break;

                case LEFT:
                case A:
                    setDirection(2);
                    break;

                case UP:
                case W:
                    setDirection(3);
                    break;
                default:
                    break;
            }
            keyPress = true;
        } 
        // else if (KeyEvent.KEY_RELEASED.equals(event.getEventType())) {
        //     keyPress = false;
        // }
    }
}
