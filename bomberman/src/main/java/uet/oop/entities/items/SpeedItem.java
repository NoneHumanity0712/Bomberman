package uet.oop.entities.items;

import java.io.FileNotFoundException;

import javafx.scene.image.Image;
import javafx.scene.media.MediaPlayer.Status;
import uet.oop.entities.movingentities.Bomber;

public class SpeedItem extends Item{

    Image speedItemImage;

    public SpeedItem(int x, int y) throws FileNotFoundException {
        super(x, y);
        setupImage();
    }

    @Override
    public void setupImage() throws FileNotFoundException {
        speedItemImage = getImage("powerup_speed.png", "sprites");
    }

    @Override
    public void beingReceived(Bomber bomber) {
        double speed_to_increase = 0.5;
        bomber.setSpeed(bomber.getSpeed() + speed_to_increase);
        setReceived(true);
        
        setupSound();
        if (!beingreceived.getStatus().equals(Status.PLAYING)) {
            beingreceived.play();
        }
    }

    @Override
    public void APPEAR() {
        setHide(false);
        setImage(speedItemImage);
    }
}
