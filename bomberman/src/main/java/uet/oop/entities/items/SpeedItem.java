package uet.oop.entities.items;

import java.io.FileNotFoundException;

import javafx.scene.image.Image;
import uet.oop.entities.movingentities.Bomber;

public class SpeedItem extends Item{

    Image speedItemImage;
    private final int speed_to_increase = 1;

    public SpeedItem(int x, int y) {
        super(x, y, 's');
    }

    @Override
    public void setupImage() throws FileNotFoundException {
        speedItemImage = getImage("sprites/powerup_speed.png");
    }

    @Override
    public void beingReceived(Bomber bomber) {
        bomber.setSpeed(bomber.getSpeed() + speed_to_increase);
    }

    @Override
    public void APPEAR() {
        setHide(false);
        setImage(speedItemImage);
    }
}
