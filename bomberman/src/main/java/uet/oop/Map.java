package uet.oop;

public class Map {
    private int row;
    private int column;
    private char[][] map;
    private int enemyNumber = 0;

    Bomber bomber;
    Enemy enemy;
    Portal portal;

    public Map() {
        this.row = 1;
        this.column = 1;

        bomber = new Bomber();
        enemy = new Enemy();
        portal = new Portal();
    };

    public Map(Map m) {
        setRow(m.getRow());
        setColumn(m.getColumn());
        setMap(m.getMap());

        bomber = new Bomber();
        enemy = new Enemy();
        portal = new Portal();
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

    public Bomber getBomber() {
        return bomber;
    }

    public Enemy getEnemy() {
        return enemy;
    }

    public Portal getPortal() {
        return portal;
    }

    public void setMap(char[][] map) {
        this.map = new char[this.row][this.column];
        for (int i = 0; i < this.row; i++) {
            for (int j = 0; j < this.column; j++) {
                if (this.map[i][j] == 'x') {
                    this.portal = new Portal(j, i);
                    this.map[i][j] = '*';
                } else if (this.map[i][j] == 'p') {
                    this.bomber = new Bomber(j, i);
                    this.map[i][j] = ' ';
                } else if (this.map[i][j] == '1' || this.map[i][j] == '2') {
                    this.enemyNumber = this.enemyNumber + 1;
                    this.enemy = new Enemy(j, i);
                    this.map[i][j] = ' ';
                } else {
                    this.map[i][j] = map[i][j];
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