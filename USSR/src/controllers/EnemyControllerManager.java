package controllers;

import main.GameMap;
import main.gameScreens.PlayGameScreen;

public class EnemyControllerManager extends ControllerManager {

    private EnemyControllerManager() {
        super();
    }

    private boolean inited = false;

    private boolean finished() {
        for (SingleController singleController : singleControllers)
            if (!singleController.finished()) return false;
        return true;
    }

    public void run() {
        if (!PlayerController.getInstance().finished()) return;
        if (!inited) {
            for (SingleController singleController : singleControllers)
                singleController.init();
            inited = true;
        }

        for (SingleController singleController : singleControllers)
            singleController.run();
        remove();

        if (finished()) {
            GameMap.getInstance().playerTurn = true;
            inited = false;
        }
    }

    private static EnemyControllerManager instance;

    public static EnemyControllerManager getInstance() {
        if (instance == null) instance = new EnemyControllerManager();
        return instance;
    }

}
