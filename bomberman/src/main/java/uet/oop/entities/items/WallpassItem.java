package uet.oop.entities.items;

import java.io.FileNotFoundException;

import javafx.scene.image.Image;
import javafx.scene.media.MediaPlayer.Status;
import uet.oop.entities.movingentities.Bomber;

public class WallpassItem extends Item{

    Image wallpassItemImage;
    private boolean able_to_pass_wall = true; //brick

    public WallpassItem(int x, int y) throws FileNotFoundException {
        super(x, y);
        setupImage();
    }

    @Override
    public void setupImage() throws FileNotFoundException {
        wallpassItemImage = getImage("powerup_wallpass.png");
    }

    @Override
    public void beingReceived(Bomber bomber) {
        bomber.setAbleToPassWall(able_to_pass_wall);
        setReceived(true);
        
        setupSound();
        if (!beingreceived.getStatus().equals(Status.PLAYING)) {
            beingreceived.play();
        }
    }

    @Override
    public void APPEAR() {
        setHide(false);
        setImage(wallpassItemImage);
    }
}
