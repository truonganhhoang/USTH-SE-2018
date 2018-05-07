package main;

import controllers.*;
import main.gameScreens.PlayGameScreen;
import utilities.Utils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class GameLevel {
    protected int MAP_TILE_SIZE = 6;
    protected Point[] wallDownPosition;
    protected boolean[][] wallDown;
    protected Point[] wallRightPosition;
    protected boolean[][] wallRight;
    protected Image[][] backgroundTile;
    protected EnemyController[] mummyControllers = new EnemyController[20];
    public static final int[] dx = {0,0,0,1,-1,-1,1,-1,1};
    public static final int[] dy = {0,1,-1,0,0,-1,-1,1,1};

    private int exitX = -124443, exitY = 123123;
    private Scanner input;

    public GameLevel() {
        wallDownPosition = new Point[20];
        wallDown = GameMap.getInstance().wallDown;
        wallRight = GameMap.getInstance().wallRight;
        wallRightPosition = new Point[20];
    }

    private void levelSetting() {
        MAP_TILE_SIZE = input.nextInt();
        MAP_TILE_SIZE = input.nextInt();
        GameMap.getInstance().MAP_TILE_SIZE = MAP_TILE_SIZE;
        GameMap.getInstance().MAP_TILE_SIZE = MAP_TILE_SIZE;
        GameMap.getInstance().TILE_LENGTH = 360 / MAP_TILE_SIZE;
    }

    private void createPlayer() {
        int x,y;
        x = input.nextInt();
        y = input.nextInt();
        PlayerController.instance.setColumn(x);
        PlayerController.instance.setRow(y);
        PlayerController.instance.setIsAlive(true);
        PlayerController.instance.setHealth(1);
        GameMap.getInstance().playerTurn = true;
    }

    private void createExit() {
        exitX = input.nextInt();
        exitY = input.nextInt();
        PlayGameScreen.exitX = exitX;
        PlayGameScreen.exitY = exitY;
        GameMap.getInstance().exitX = exitX;
        GameMap.getInstance().exitY = exitY;

        int x = 0,y = 0;
        if (exitY==0) x = 0*55 + 1;
        if (exitX== MAP_TILE_SIZE +1) x = 1*55 + 2;
        if (exitY== MAP_TILE_SIZE +1) x = 2*55 + 3;
        if (exitX==0) x = 3*55 + 4;

        BufferedImage exitSprite = Utils.getImage("stairs6.gif");
        PlayGameScreen.exitImage = exitSprite.getSubimage(x,y,55,55);
    }

    private void createWallDown() {
        int n, column, row;

        n = input.nextInt();
        for (int i=1;i<=n;i++) {
            column = input.nextInt();
            row = input.nextInt();
            wallDownPosition[i] = new Point(column,row);
        }

        for (int i=1;i<=n;i++) {
            int x = wallDownPosition[i].x;
            int y = wallDownPosition[i].y;
            wallDown[x][y] = true;
            WallControllerManager.getInstance().add(WallController.create(x,y, WallType.DOWN));
        }
    }

    private void createWallRight() {
        int n, column, row;

        n = input.nextInt();
        for (int i=1;i<=n;i++) {
            column = input.nextInt();
            row = input.nextInt();
            wallRightPosition[i] = new Point(column,row);
        }

        for (int i=1;i<=n;i++) {
            int x = wallRightPosition[i].x;
            int y = wallRightPosition[i].y;
            wallRight[x][y] = true;
            WallControllerManager.getInstance().add(WallController.create(x,y, WallType.RIGHT));
        }
    }

    private void createMummy() {
        int n, x, y;
        String type;

        n = input.nextInt();
        for (int i=1;i<=n;i++) {
            x = input.nextInt();
            y = input.nextInt();
            type = input.next();
            if (MummyType.valueOf(type)==MummyType.WHITE)
                mummyControllers[i] = EnemyControllerWhite.create(x,y);
            if (MummyType.valueOf(type)==MummyType.RED)
                mummyControllers[i] = EnemyControllerRed.create(x,y);
          //  if (MummyType.valueOf(type)==MummyType.SCORPION)
            //    mummyControllers[i] = EnemyControllerScorpion.create(x,y);
        }

        for (int i=1;i<=n;i++) {
            EnemyController mummy = mummyControllers[i];
            mummy.setColumn(mummy.getColumn());
            mummy.setRow(mummy.getRow());
            EnemyControllerManager.getInstance().add(mummy);
        }
    }

    public void createLevel(int level) {
        try {
            this.input = new Scanner(new File("map/" + level + ".txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        GameMap.getInstance().init();
        levelSetting();
        createPlayer();
        createExit();
        createWallDown();
        createWallRight();
        createMummy();
    }

    public boolean hasLose() {
        return PlayerController.instance.getHealth() <= 0;
    }

    public boolean hasWon() {
        if (PlayerController.instance.getColumn() == PlayGameScreen.exitX &&
                PlayerController.instance.getRow() == PlayGameScreen.exitY) return true;
        return false;
    }

    private static GameLevel instance;
    public static GameLevel getInstance() {
        if (instance==null) instance = new GameLevel();
        return instance;
    }
}

