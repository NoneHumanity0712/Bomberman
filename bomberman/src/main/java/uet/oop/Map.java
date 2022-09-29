package uet.oop;

public class Map {
    private int row;
    private int column;
    private char[][] map;
    Portal portal;
    private int enemy = 0;

    public Map() {
        this.row = 1;
        this.column = 1;
    };

    public Map(Map m) {
        this.row = m.getRow();
        this.column = m.getColumn();
        this.map = new char[row][column];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                this.map[i][j] = m.getMap()[i][j];
            }
        }
        this.setPortal();
        this.setEnemy();
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
        this.map = new char[this.row][this.column];
        for (int i = 0; i < this.row; i++) {
            for (int j = 0; j < this.column; j++) {
                this.map[i][j] = map[i][j];
            }
        }
    }

    public void setPortal() {
        for (int i = 0; i < this.row; i++) {
            for (int j = 0; j < this.column; j++) {
                if (this.map[i][j] == 'p') {
                    this.portal = new Portal(j, i);
                    break;
                }
            }
        }
    }
    
    public void checkPortal() {
        if (this.enemy == 0) {
            portal.ACTIVATE();
        }
    }

    public void setEnemy() {
        for (int i = 0; i < this.row; i++) {
            for (int j = 0; j < this.column; j++) {
                if (this.map[i][j] == '1' || this.map[i][j] == '2') {
                    enemy = enemy + 1;
                }
            }
        }
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