package controllers;

import javax.sound.sampled.Control;

public class WallControllerManager extends ControllerManager {
    private WallControllerManager() {
        super();
    }

    private static WallControllerManager instance;
    public static WallControllerManager getInstance() {
        if (instance==null) instance = new WallControllerManager();
        return instance;
    }
}
