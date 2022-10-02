package uet.oop;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;

public class Main extends Application {

    /**
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        File input = new File("src/main/java/uet/oop/level1.txt");
        Scanner scanner;
        try {
            scanner = new Scanner(input);
            ReadFromFile readFromFile = new ReadFromFile();
            if (scanner.hasNext())
                readFromFile.readFile(scanner);

            Map map = new Map();
            map.setRow(readFromFile.getRow_read());
            map.setColumn(readFromFile.getColumn_read());
            map.setMap(readFromFile.getMap_read());

            Game bombermanGame = new Game(map);
            bombermanGame.setLevel(readFromFile.getLevel_read());

            FileInputStream grassInputStream = new FileInputStream("sprites/grass.png");
            Image grassImage = new Image(grassInputStream);

            FileInputStream wallInputStream = new FileInputStream("sprites/wall.png");
            Image wallImage = new Image(wallInputStream);

            FileInputStream brickInputStream = new FileInputStream("sprites/brick.png");
            Image brickImage = new Image(brickInputStream);

            FileInputStream[] bombInputStreams = new FileInputStream[2];
            Image[] bombImage = new Image[2];
            bombInputStreams[0] = new FileInputStream("sprites/bomb.png");
            bombImage[0] = new Image(bombInputStreams[0]);
            bombInputStreams[1] = new FileInputStream("sprites/bomb_exploded.png");
            bombImage[1] = new Image(bombInputStreams[1]);

            FileInputStream[] bomberInputStreams = new FileInputStream[4];
            Image bomberImage[] = new Image[4];
            bomberInputStreams[0] = new FileInputStream("sprites/player_right.png");
            bomberImage[0] = new Image(bomberInputStreams[0]);
            bomberInputStreams[1] = new FileInputStream("sprites/player_down.png");
            bomberImage[1] = new Image(bomberInputStreams[1]);
            bomberInputStreams[2] = new FileInputStream("sprites/player_left.png");
            bomberImage[2] = new Image(bomberInputStreams[2]);
            bomberInputStreams[3] = new FileInputStream("sprites/player_up.png");
            bomberImage[3] = new Image(bomberInputStreams[3]);

            bombermanGame.setBombImage(bombImage);
            bombermanGame.setBomberImage(bomberImage);
            bombermanGame.setBrickImage(brickImage);
            bombermanGame.setGrassImage(grassImage);
            bombermanGame.setWallImage(wallImage);

            bombermanGame.drawScene();

            Scene scene1 = new Scene(bombermanGame.imageGroup,
                    bombermanGame.pixel * map.getColumn(), bombermanGame.pixel * map.getRow());

            // EventHandler handler = new EventHandler<Event>() {
            //     @Override
            //     public void handle(Event event) {
            //         try {
            //             bombermanGame.handle(event);
            //         } catch (IOException e) {
            //             e.printStackTrace();
            //         }
                    
            //     }
            // };

            primaryStage.setTitle("DEMO");
            primaryStage.setScene(scene1);

            primaryStage.show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
