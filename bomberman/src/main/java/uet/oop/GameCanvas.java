package uet.oop;

import java.io.FileNotFoundException;

import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import uet.oop.entities.Bomber;
import uet.oop.entities.Entity;

public class GameCanvas extends Canvas {

    final int numsOfRows = 15;
    final int numsofCols = 25;

    final int screenWidth = numsofCols * Entity.spriteSize;
    final int screenHeight = numsOfRows * Entity.spriteSize;

    public GraphicsContext context;

    Bomber player;

    public void setPlayer(Bomber player) {
        this.player = player;
    }

    public GameCanvas(Node... children) throws FileNotFoundException {
        super();
        this.setWidth(screenWidth * Entity.SCALE);
        this.setHeight(screenHeight * Entity.SCALE);
        this.setFocusTraversable(true);
        context = this.getGraphicsContext2D();
    }

    public GameCanvas() throws FileNotFoundException {
        super();
        this.setWidth(screenWidth * Entity.SCALE);
        this.setHeight(screenHeight * Entity.SCALE);
        this.setFocusTraversable(true);
        context = this.getGraphicsContext2D();
    }

    public void render() {
        context.save();

        context.drawImage(player.getImage(), player.getOldX()*Entity.size, player.getOldY()*Entity.size, Entity.size, Entity.size);

        context.restore();
    }

}
