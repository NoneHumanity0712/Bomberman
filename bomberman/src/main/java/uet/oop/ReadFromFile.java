package uet.oop;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class ReadFromFile {
    private int level_read;
    private int row_read;
    private int column_read;
    private char[][] map_read;

    public void setLevel_read(int level_read) {
        this.level_read = level_read;
    }

    public int getLevel_read() {
        return level_read;
    }

    public void setRow_read(int row_read) {
        this.row_read = row_read;
    }

    public int getRow_read() {
        return row_read;
    }

    public void setColumn_read(int column_read) {
        this.column_read = column_read;
    }

    public int getColumn_read() {
        return column_read;
    }

    public void setMap_read(char[][] map_read) {
        this.map_read = map_read;
    }

    public char[][] getMap_read() {
        return map_read;
    }

    public void readFile(Scanner myReader) {
        this.setLevel_read(myReader.nextInt());
        this.setRow_read(myReader.nextInt());
        this.setColumn_read(myReader.nextInt());
        myReader.nextLine();
        char[][] temp = new char[this.getRow_read()][this.getColumn_read()];
        for (int i = 0; i < this.getRow_read(); i++) {
            String s = myReader.nextLine();
            for (int j = 0; j < s.length(); j++) {
                temp[i][j] = s.charAt(j);
            }
        }
        this.setMap_read(temp);
    }

    public static void main(String[] args) {
        File input = new File("src/main/java/uet/oop/level1.txt");
        ReadFromFile inputFile = new ReadFromFile();

        try {
            FileOutputStream out = new FileOutputStream("src/main/java/uet/oop/output.txt");
            System.setOut(new PrintStream(out));

            Scanner myReader = new Scanner(input);
            if (myReader.hasNext()) {
                inputFile.readFile(myReader);
            }
            // Map[] gameMapsLevels = new Map[10];
            Map gameMap = new Map();
            gameMap.setMapFromFile(inputFile);
            System.out.println(gameMap.getRow() + " " + gameMap.getColumn());
            gameMap.printMap();
            // gameMapsLevels[inputFile.getLevel_read() - 1] = new Map(gameMap);
            // gameMapsLevels[inputFile.getLevel_read() - 1].printMap();
            myReader.close();

            Bomber bomber = new Bomber();

            System.out.println(1);
            bomber.MOVE_RIGHT(gameMap);

            System.out.println(2);
            bomber.MOVE_RIGHT(gameMap);

            System.out.println(3);
            bomber.MOVE_RIGHT(gameMap);

            System.out.println(4);
            bomber.MOVE_RIGHT(gameMap);

            System.out.println(5);
            Bomb bomb = new Bomb(bomber.placeBomb(gameMap)); //print bomb behind bomber
            if (bomb.legalPosition(gameMap)) {
                char[][] temp = gameMap.getMap();
                temp[bomb.getY()][bomb.getX()] = 'b';
                gameMap.setMap(temp);
            }
            gameMap.printMap();

            System.out.println(6);
            bomber.MOVE_LEFT(gameMap);

            System.out.println(7);
            bomber.MOVE_LEFT(gameMap);

            System.out.println(8);
            if (bomb.legalPosition(gameMap)) bomb.Explode(gameMap);
            gameMap.printMap();

        } catch (FileNotFoundException e) {
            System.err.println("Error: File not found.");
            e.printStackTrace();
        }
    }
}
