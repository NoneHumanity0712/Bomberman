package uet.oop.entities.items;

import java.io.FileNotFoundException;

import javafx.scene.image.Image;
import uet.oop.entities.movingentities.Bomber;

public class FlameItem extends Item{
    Image flameItemImage;

    private final int range_to_increase = 1;

    public FlameItem(int x, int y) throws FileNotFoundException {
        super(x, y, 'f');
        setupImage();
    }

    @Override
    public void setupImage() throws FileNotFoundException {
        flameItemImage = getImage("sprites/powerup_flames.png");
    }

    public int getRange_to_increase() {
        return range_to_increase;
    }

    @Override
    public void beingReceived(Bomber bomber) {
        bomber.setBombRange(bomber.getBombRange() + range_to_increase);
    }

    @Override
    public void APPEAR() {
        setHide(false);
        setImage(flameItemImage);
    }
    
}
