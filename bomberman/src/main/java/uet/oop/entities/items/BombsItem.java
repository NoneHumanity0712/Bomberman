package uet.oop.entities.items;

import java.io.FileNotFoundException;

import javafx.scene.image.Image;
import uet.oop.entities.movingentities.Bomber;

public class BombsItem extends Item{

    Image bombsItemImage;

    private final int bomb_num_to_increase = 1;

    public int getBomb_num_to_increase() {
        return bomb_num_to_increase;
    }

    public BombsItem(int x, int y) throws FileNotFoundException {
        super(x, y);
        setupImage();
    }

    @Override
    public void setupImage() throws FileNotFoundException {
        bombsItemImage = getImage("powerup_bombs.png");
    }

    @Override
    public void beingReceived(Bomber bomber) {
        bomber.setMaxBombs(bomber.getMaxBombs() + bomb_num_to_increase);
        setReceived(true);
        
    }

    @Override
    public void APPEAR() {
        setHide(false);
        setImage(bombsItemImage);
    }
    
}
