package uet.oop.gameprocess;

import java.io.FileNotFoundException;

import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import uet.oop.entities.Entity;
import uet.oop.entities.movingentities.Bomber;

public class GameCanvas extends Canvas {

    public GraphicsContext context;

    Bomber player;

    public void setPlayer(Bomber player) {
        this.player = player;
    }

    public GameCanvas(Node... children) throws FileNotFoundException {
        super();
        this.setFocusTraversable(true);
        context = this.getGraphicsContext2D();
    }

    public GameCanvas(int screenWidth, int screenHeight) throws FileNotFoundException {
        super();
        this.setWidth(screenWidth);
        this.setHeight(screenHeight);
        this.setFocusTraversable(true);
        context = this.getGraphicsContext2D();
    }


}
