package utilities;

import main.GameConfig;
import main.GameMap;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Utils {
    public static BufferedImage getImage(String link) {
        BufferedImage res = null;
        try {
            res = ImageIO.read(new File("images/" + link));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }

    public static boolean insideMap(int column, int row) {
        int column0 = 0, row0 = 0;
        int width = GameConfig.MAP_TILE_SIZE, height = GameConfig.MAP_TILE_SIZE;
        return (column > column0 && row > row0 && column <= column0 + width && row <= row0 + height);
    }

    public static boolean hasWall(int x1, int y1, int x2, int y2) {
        if (!insideMap(x2, y2)) return true;
        GameMap gameMap = GameMap.getInstance();
        if (x1 == x2) {
            if (y1 < y2) return gameMap.wallDown[x1][y1];
            else return gameMap.wallDown[x2][y2];
        }
        if (y1 == y2) {
            if (x1 < x2) return gameMap.wallRight[x1][y1];
            else return gameMap.wallRight[x2][y2];
        }
        return true;
    }

    public static boolean canMoveTo(int column1, int row1, int column2, int row2, int exitX, int exitY) {
        if (column2 == exitX && row2 == exitY) return true;
        return !hasWall(column1, row1, column2, row2) && insideMap(column2, row2);
    }

    public static int manDistance(int x1, int y1, int x2, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }
}
