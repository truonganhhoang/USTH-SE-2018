package controllers;

import main.GameConfig;
import main.GameMap;
import models.Wall;
import views.ImageView;

public class WallController extends SingleController {
    private WallType wallType;

    public void checkDefault() {
        gameView.setImage("bullet.png");
        int tileLength = GameMap.getInstance().getTILE_LENGTH();
        if (this.wallType == WallType.DOWN) {
            setWidth(GameConfig.TILE_LENGTH);
            setHeight(GameConfig.WALL_DOWN_HEIGHT);
            setX(tileLength * getColumn() + tileLength/2);
            setY(tileLength * getRow() + tileLength);
        }

        if (this.wallType == WallType.RIGHT) {
            setWidth(GameConfig.WALL_RIGHT_WIDTH);
            setHeight(GameConfig.TILE_LENGTH);
            setX(tileLength * getColumn() + tileLength);
            setY(tileLength * getRow() + tileLength/2);
        }
    }

    private WallController() {

    }

    private WallController(int column, int row, WallType wallType) {
        gameObject = new Wall();
        gameView = new ImageView("bullet.png");
        setColumn(column);
        setRow(row);
        this.wallType = wallType;
        checkDefault();
    }

    public static WallController create(int column,int row, WallType wallType) {
        return new WallController(column,row,wallType);
    }

    public void run() {

    }

}
