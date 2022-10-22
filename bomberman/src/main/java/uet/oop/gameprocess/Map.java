package uet.oop.gameprocess;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import uet.oop.entities.Bomb;
import uet.oop.entities.items.FlameItem;
import uet.oop.entities.items.Item;
import uet.oop.entities.items.Portal;
import uet.oop.entities.items.SpeedItem;
import uet.oop.entities.movingentities.Bomber;
import uet.oop.entities.movingentities.enemies.Balloom;
import uet.oop.entities.movingentities.enemies.Doll;
import uet.oop.entities.movingentities.enemies.Enemy;
import uet.oop.entities.movingentities.enemies.Minvo;
import uet.oop.entities.movingentities.enemies.Oneal;

public class Map {
    private int row;
    private int column;
    private char[][] map;

    private Bomber bomber;
    private List<Enemy> enemies;
    private List<Bomb> bombs;
    private List<Item> items;

    private Portal portal;

    public Map() throws FileNotFoundException {
        this.row = 1;
        this.column = 1;

        this.bomber = new Bomber();
        this.portal = new Portal();
        this.bombs = new ArrayList<Bomb>();
        this.enemies = new ArrayList<Enemy>();
        this.items = new ArrayList<Item>();
    };

    public Map(Map m) throws FileNotFoundException {
        setRow(m.getRow());
        setColumn(m.getColumn());
        setMap(m.getMap());

        this.bomber = new Bomber(m.bomber);
        this.portal = new Portal(m.portal);
        this.bombs = new ArrayList<>(m.getBombs());
        this.enemies = new ArrayList<Enemy>(m.getEnemy());
        this.items = new ArrayList<>(m.getItems());
    }

    public Map(int row, int column, char[][] map) throws FileNotFoundException {
        this.row = row;
        this.column = column;

        this.bomber = new Bomber();
        this.portal = new Portal();
        this.bombs = new ArrayList<Bomb>();
        this.enemies = new ArrayList<Enemy>();
        this.items = new ArrayList<Item>();

        setMap(map);
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

    public void setBomber(Bomber bomber) {
        this.bomber = bomber;
    }

    public Bomber getBomber() {
        return bomber;
    }

    public List<Enemy> getEnemy() {
        return enemies;
    }

    public Portal getPortal() {
        return portal;
    }

    public List<Bomb> getBombs() {
        return bombs;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setMap(char[][] map) throws FileNotFoundException {
        this.map = new char[this.row][this.column];
        for (int i = 0; i < this.row; i++) {
            for (int j = 0; j < this.column; j++) {
                switch (map[i][j]) {
                    case 'x': // portal
                        this.portal.setY(i);
                        this.portal.setX(j);
                        this.map[i][j] = '*';
                        break;
                    case 'p': // bomber
                        this.bomber = new Bomber(j, i);
                        this.map[i][j] = ' ';
                        break;
                    case '1': // ballom
                        Balloom balloom = new Balloom(j, i);
                        enemies.add(balloom);
                        this.map[i][j] = ' ';
                        break;
                    case '2': // oneal
                        Oneal oneal = new Oneal(j, i);
                        enemies.add(oneal);
                        this.map[i][j] = ' ';
                        break;
                    case '3': // doll
                        Doll doll = new Doll(j, i);
                        enemies.add(doll);
                        this.map[i][j] = ' ';
                        break;
                    case '4': // minvo
                        Minvo minvo = new Minvo(j, i);
                        enemies.add(minvo);
                        this.map[i][j] = ' ';
                        break;
                    case 's': // speed item
                        SpeedItem speedItem = new SpeedItem(j, i);
                        items.add(speedItem);
                        this.map[i][j] = '*';
                        break;
                    case 'f': // flame item
                        FlameItem flameItem = new FlameItem(j, i);
                        items.add(flameItem);
                        this.map[i][j] = '*';
                        break;
                    default:
                        this.map[i][j] = map[i][j];
                        break;
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

    public void setMapFromFile(ReadFromFile inputFile) throws FileNotFoundException {
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