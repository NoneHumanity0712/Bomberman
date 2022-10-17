package uet.oop.entities.items;

import java.io.FileNotFoundException;

import uet.oop.entities.Entity;
import uet.oop.entities.movingentities.Bomber;

public abstract class Item extends Entity{
    private boolean received;
    private boolean hide;

    public boolean isHide() {
        return hide;
    }

    public void setHide(boolean hide) {
        this.hide = hide;
    }

    public Item() {
        super();
        received = false;
    }

    public Item(int x, int y) {
        super(x, y);
        received = false;
    }

    public Item(int x, int y, char type) {
        super(x, y, type);
        received = false;
    }

    @Override
    public void setupImage() throws FileNotFoundException {
        
    }

    public boolean isReceived() {
        return received;
    }

    public void setReceived(boolean received) {
        this.received = received;
    }

    public abstract void beingReceived(Bomber bomber);
    public abstract void APPEAR();
}
