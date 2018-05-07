package main;

import utilities.Utils;

import java.awt.*;

public class GameMap {
    int MAP_TILE_SIZE; // số lượng ô vuông trên bản đồ
    int MAP_SIZE = 360; // bản đồ chơi kích thước 360x360
    int TILE_LENGTH = 60;
    public boolean[][] wallDown = new boolean[30][30];
    public boolean[][] wallRight = new boolean[30][30];
    public int exitX = 0, exitY = 0;
    public boolean playerTurn = true;

    public static Image mapBackground;

    private GameMap() {init(); }

    public void getMapInfo() {
        MAP_TILE_SIZE = 6;
        TILE_LENGTH = MAP_SIZE / MAP_TILE_SIZE;
    }

    public void init() {
        getMapInfo();
        mapBackground = Utils.getImage("floor6.jpg");// + MAP_TILE_SIZE + ".jpg");
    }

    public void draw(Graphics g) {
        g.drawImage(mapBackground,0,0,MAP_SIZE,MAP_SIZE,null);
    }

    private static GameMap instance;

    public static GameMap getInstance() {
        if (instance==null) instance = new GameMap();
        return instance;
    }


    //********************* GETTER AND SETTER
    public int getTILE_LENGTH() {
        return TILE_LENGTH;
    }

    public boolean getPlayerTurn() {
        return playerTurn;
    }
}
