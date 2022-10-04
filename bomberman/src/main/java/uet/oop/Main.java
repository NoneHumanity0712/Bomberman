package uet.oop;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;

public class Main extends Application implements HandleImage{

    /** 
     * launch game.
     * @param args String[]
     */
    public static void main(String[] args) {
        launch(args);
    }

    /* (non-Javadoc)
     * @see javafx.application.Application#start(javafx.stage.Stage)
     */
    @Override
    public void start(Stage primaryStage) {
        File input = new File("src/main/java/uet/oop/level1.txt");
        Scanner scanner;
        try {
            scanner = new Scanner(input);
            ReadFromFile readFromFile = new ReadFromFile();
            if (scanner.hasNext()) {
                readFromFile.readFile(scanner);
            }

            Map map = new Map();
            map.setRow(readFromFile.getRow_read());
            map.setColumn(readFromFile.getColumn_read());
            map.setMap(readFromFile.getMap_read());

            Game bombermanGame = new Game(map);
            bombermanGame.setLevel(readFromFile.getLevel_read());

            Image grassImage = getImage("sprites/grass.png");
            Image wallImage = getImage("sprites/wall.png");
            Image brickImage = getImage("sprites/brick.png");
            Image portalImage = getImage("sprites/portal.png");

            Image[] bombImage = new Image[2];
            bombImage[0] = getImage("sprites/bomb.png");
            bombImage[1] = getImage("sprites/bomb_exploded.png");

            Image[] bomberImage = new Image[4];
            bomberImage[0] = getImage("sprites/player_right.png");
            bomberImage[1] = getImage("sprites/player_down.png");
            bomberImage[2] = getImage("sprites/player_left.png");
            bomberImage[3] = getImage("sprites/player_up.png");

            Image[] enemyImage = new Image[2];
            enemyImage[0] = getImage("sprites/balloom_right1.png");

            bombermanGame.setBombImage(bombImage);
            bombermanGame.setBomberImage(bomberImage);
            bombermanGame.setBrickImage(brickImage);
            bombermanGame.setGrassImage(grassImage);
            bombermanGame.setWallImage(wallImage);
            bombermanGame.setPortalImage(portalImage);
            bombermanGame.setEnemyImage(enemyImage);

            bombermanGame.drawScene();

            Scene scene1 = new Scene(bombermanGame.imageGroup,
                    bombermanGame.pixel * map.getColumn(), bombermanGame.pixel * map.getRow());
            primaryStage.setTitle("DEMO");

            scene1.addEventHandler(KeyEvent.KEY_PRESSED, (Event event) -> {
                try {
                    bombermanGame.handle(event);
                    bombermanGame.drawScene();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            primaryStage.setScene(scene1);
            primaryStage.show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
