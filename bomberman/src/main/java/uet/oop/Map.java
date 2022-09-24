package uet.oop;

public class Map{
    private int row;
    private int column;
    private char[][] map;

    public void setRow(int row) {
        this.row = row;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public void setMap(char[][] map) {
        this.map = map;
    }

    public char[][] getMap() {
        return map;
    }

    public void printMap() {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
    }
}