package uet.oop;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class demo {
    public static void main(String[] args) throws FileNotFoundException {
        File input = new File("src/main/java/uet/oop/level1.txt");
        Scanner scanner;

        scanner = new Scanner(input);
        ReadFromFile readFromFile = new ReadFromFile();
        if (scanner.hasNext()) {
            readFromFile.readFile(scanner);
        }

        Map map = new Map();
        map.setRow(readFromFile.getRow_read());
        map.setColumn(readFromFile.getColumn_read());
        map.setMap(readFromFile.getMap_read());

        for (int i = 0; i < map.getRow(); i++) {
            for (int j = 0; j < map.getColumn(); j++) {
                System.out.print(map.getMap()[i][j]);
            }
            System.out.println();
        }
    }

}
