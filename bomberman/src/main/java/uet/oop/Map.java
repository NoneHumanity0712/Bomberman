package uet.oop;

public class Map {
    private int row;
    private int column;
    private char[][] map;

    public Map() {
    };

    public Map(Map m) {
        this.row = m.getRow();
        this.column = m.getColumn();
        this.map = m.getMap();
    }

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

    /**
     * Mô tả cấu trúc tệp cấu hình màn chơi:
     * 1/ Dòng đầu tiên bao gồm 3 số nguyên L, R, C:
     * L - số thứ tự màn chơi,
     * R - số hàng của bản đồ,
     * C - số cột của bản đồ.
     * 
     * 2/ R dòng tiếp theo, mỗi dòng có C kí tự. Mỗi kí tự đại diện cho một đối
     * tượng trên bản đồ:
     * Tiles:
     * # - Wall,
     * - Brick,
     * x - Portal.
     * 
     * Character:
     * p - Bomber,
     * 1 - Balloon,
     * 2 - Oneal.
     * 
     * Items:
     * b - Bomb Item,
     * f - Flame Item,
     * s - Speed Item.
     * 
     * Kí tự khác các kí tự trên - Grass
     * 
     * @return 2d array map
     */
    public char[][] getMap() {
        return map;
    }

    public void setMapFromFile(ReadFromFile inputFile) {
        this.setRow(inputFile.getRow_read());
        this.setColumn(inputFile.getColumn_read());
        this.setMap(inputFile.getMap_read());
    }

    public void Explode(int i, int j) {
        this.map[i][j] = ' ';

        if (this.getMap()[i - 1][j] == '*') {
            this.map[i - 1][j] = ' ';
        }
        if (this.getMap()[i + 1][j] == '*') {
            this.map[i + 1][j] = ' ';
        }
        if (this.getMap()[i][j - 1] == '*') {
            this.map[i][j - 1] = ' ';
        }
        if (this.getMap()[i][j + 1] == '*') {
            this.map[i][j + 1] = ' ';
        }

        if (this.getMap()[i - 1][j] == 'b') {
            this.Explode(i-1, j);
        }
        if (this.getMap()[i + 1][j] == 'b') {
            this.Explode(i+1, j);
        }
        if (this.getMap()[i][j-1] == 'b') {
            this.Explode(i, j-1);
        }
        if (this.getMap()[i][j+1] == 'b') {
            this.Explode(i, j+1);
        }
    }

    public void printMap() {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }
}