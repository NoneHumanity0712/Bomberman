package uet.oop.gameprocess;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ReadFromFile {
    private int level_read; // level number from file
    private int row_read; // row number from file
    private int column_read; // column number from file
    private char[][] map_read; // 2D char array read from file

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

    /**
     * Read input file
     * 
     * @param input File
     * @throws FileNotFoundException
     */
    public void readFile(File input) throws FileNotFoundException {
        Scanner myReader = new Scanner(input);

        if (myReader.hasNext()) {
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

        myReader.close();
    }
}