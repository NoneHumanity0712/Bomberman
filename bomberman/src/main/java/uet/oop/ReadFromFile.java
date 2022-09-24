package uet.oop;

import java.io.File;
import java.io.FileNotFoundException;
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

    public static void main(String[] args) {
        File input = new File("src/main/java/uet/oop/level.txt");
        ReadFromFile inputFile = new ReadFromFile();
        try {
            Scanner myReader = new Scanner(input);
            if (myReader.hasNext()) {
                inputFile.setLevel_read(myReader.nextInt());
                inputFile.setRow_read(myReader.nextInt());
                inputFile.setColumn_read(myReader.nextInt());
                myReader.nextLine();
                char[][] temp = new char[inputFile.getRow_read()][inputFile.getColumn_read()];
                for (int i = 0; i < inputFile.getRow_read(); i++) {
                    String s = myReader.nextLine();
                    for (int j = 0; j < s.length(); j++) {
                        temp[i][j] = s.charAt(j);
                    }
                }
                inputFile.setMap_read(temp);
            }
            Map gameMap = new Map();
            gameMap.setRow(inputFile.getRow_read());
            gameMap.setColumn(inputFile.getColumn_read());
            gameMap.setMap(inputFile.getMap_read());
            System.out.println(gameMap.getRow() + " " + gameMap.getColumn());
            gameMap.printMap();
            myReader.close();

        } catch (FileNotFoundException e) {
            System.err.println("Error: File not found.");
            e.printStackTrace();
        }
    }
}
