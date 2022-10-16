package uet.oop.entities;

import java.io.FileNotFoundException;

import javafx.scene.image.Image;

public class Portal extends Entity{
    private boolean activate;
    private boolean hide;

    Image portalImage;

    public Portal(){
        activate = false;
        hide = true;
    };

    public Portal(int x, int y){
        super(x, y, 'x');
        activate = false;
        hide = true;
    }

    public Portal(Portal portal) {
        super(portal.getX(), portal.getY(), 'x');
        activate = false;
        hide = true;
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
    }

    public void ACTIVATE(){
        setActivate(true);
    }

    @Override
    public void setupImage() throws FileNotFoundException {
        portalImage = getImage("sprites/portal.png");
    }
}
