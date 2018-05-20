package models;

import controllers.movement.MoveType;
import main.GameMap;

public class GameObject {
    protected int x = 0, y = 0; // position of the center of the unit
    protected int row = 0, column = 0; // position of the unit on the grid map
    protected int width = 0, height = 0, cornerX = 0, cornerY = 0; // cornerX, cornerY are top left corner of the unit
    protected int health = 1;
    protected boolean isAlive = true;
    public static final int[] dx = {0, 0, 0, 1, -1};
    public static final int[] dy = {0, 1, -1, 0, 0};
    public static final MoveType[] moveTypes = {MoveType.STAY, MoveType.DOWN, MoveType.UP, MoveType.RIGHT, MoveType.LEFT};

    protected int powerLevel = 0;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getCornerX() {
        return cornerX;
    }

    public int getCornerY() {
        return cornerY;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getPowerLevel() {
        return powerLevel;
    }

    public int getHealth() {
        return health;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setX(int v) {
        x = v;
        cornerX = x - width / 2;
    }

    public void setY(int v) {
        y = v;
        cornerY = y - height / 2;
    }

    public void setWidth(int width) {
        this.width = width;
        this.cornerX = x - width / 2;
    }

    public void setHeight(int height) {
        this.height = height;
        this.cornerY = y - height / 2;
    }

    public void setColumn(int v) {
        column = v;
        int tileLength = GameMap.getInstance().getTILE_LENGTH();
        setX(column * tileLength - tileLength / 2);
    }

    public void setRow(int v) {
        row = v;
        int tileLength = GameMap.getInstance().getTILE_LENGTH();
        setY(row * tileLength - tileLength / 2);
    }

    public void setPowerLevel(int v) {
        powerLevel = v;
    }

    public void setHealth(int v) {
        health = v;
        if (health <= 0) isAlive = false;
    }

    public void takeDamage(int v) {
        setHealth(health - v);
    }

    public void setIsAlive(boolean v) {
        isAlive = v;
    }

    public void move(int dx, int dy) {
        column += dx;
        row += dy;
    }

    public GameObject() {

    }

    public GameObject(int column, int row) {
        setColumn(column);
        setRow(row);
        int tileLength = GameMap.getInstance().getTILE_LENGTH();
        int width = tileLength;
        int height = tileLength;
        setWidth(width);
        setHeight(height);
    }
}
