package main;

import controllers.ControllerController;
import main.gameScreens.PlayGameScreen;

import java.util.logging.Level;

public class LevelManager {

    private int currentLevel = 1;
    public static final int MAX_LEVEL = 50;

    public LevelManager() {
        currentLevel = 0;
    }

    private void newLevel() {
        ControllerController.instance.init();
        for (int i=0;i<30;i++)
            for (int j=0;j<30;j++) GameMap.getInstance().wallDown[i][j] = GameMap.getInstance().wallRight[i][j] = false;
    }

    private void nextLevel() {
        currentLevel++;
    }

    public void run() {
        if (currentLevel==0) {
            currentLevel = 1;
            newLevel();
            GameLevel.getInstance().createLevel(1);
        }

        if (GameLevel.getInstance().hasWon()) {
            newLevel(); nextLevel();
            GameLevel.getInstance().createLevel(currentLevel);
        }

        if (GameLevel.getInstance().hasLose()) {
            newLevel();
            GameLevel.getInstance().createLevel(currentLevel);
        }
    }

    private static LevelManager instance;
    public static LevelManager getInstance() {
        if (instance==null) instance = new LevelManager();
        return instance;
    }

}
