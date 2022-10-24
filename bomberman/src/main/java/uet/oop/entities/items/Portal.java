package uet.oop.entities.items;

import java.io.FileNotFoundException;

import javafx.scene.image.Image;
import uet.oop.entities.Entity;

public class Portal extends Entity{
    private boolean activate;
    private boolean hide;

    Image portalImage;

    public Portal() throws FileNotFoundException{
        activate = false;
        hide = true;

        setupImage();
    };

    public Portal(int x, int y) throws FileNotFoundException{
        super(x, y);
        activate = false;
        hide = true;

        setupImage();
    }

    public Portal(Portal portal) throws FileNotFoundException {
        super(portal.getX(), portal.getY());
        activate = false;
        hide = true;

        setupImage();
    }

    public boolean isActivate() {
        return activate;
    }

    public boolean isHide() {
        return hide;
    }

    public void setActivate(boolean activate) {
        this.activate = activate;
    }

    public void setHide(boolean hide) {
        this.hide = hide;
    }

    public void APPEAR(){
        setHide(false);
        setImage(portalImage);
    }

    public void ACTIVATE(){
        setActivate(true);
    }

    @Override
    public void setupImage() throws FileNotFoundException {
        portalImage = getImage("portal.png", "sprites");
    }
}
