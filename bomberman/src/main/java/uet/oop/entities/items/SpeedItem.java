package uet.oop.entities.items;

import java.io.FileNotFoundException;

import javafx.scene.image.Image;
import uet.oop.entities.movingentities.Bomber;

public class SpeedItem extends Item{

    Image speedItemImage;
    private final double speed_to_increase = 0.5;

    public SpeedItem(int x, int y) throws FileNotFoundException {
        super(x, y, 's');
        setupImage();
    }

    @Override
    public void setupImage() throws FileNotFoundException {
        speedItemImage = getImage("powerup_speed.png");
    }

    @Override
    public void beingReceived(Bomber bomber) {
        bomber.setSpeed(bomber.getSpeed() + speed_to_increase);
        setReceived(true);
    }

    @Override
    public void APPEAR() {
        setHide(false);
        setImage(speedItemImage);
    }
}
