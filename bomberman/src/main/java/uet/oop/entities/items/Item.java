package uet.oop.entities.items;

import java.io.FileNotFoundException;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import uet.oop.entities.Entity;
import uet.oop.entities.movingentities.Bomber;

public abstract class Item extends Entity{
    private boolean received;
    private boolean hide;

    protected MediaPlayer beingreceived;

    public boolean isHide() {
        return hide;
    }

    public void setHide(boolean hide) {
        this.hide = hide;
    }

    public Item() {
        super();
        received = false;
        hide = true;
    }

    public Item(int x, int y) {
        super(x, y);
        received = false;
        hide = true;
    }

    public Item(int x, int y, char type) {
        super(x, y, type);
        received = false;
        hide = true;
    }

    protected void setupSound() {
        beingreceived = new MediaPlayer(new Media(getClass().getResource("/sound/receive_item.wav").toString()));
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
