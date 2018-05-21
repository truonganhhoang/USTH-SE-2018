package controllers;

import javax.sound.sampled.Control;

public class WallControllerManager extends ControllerManager {
    // this class controls list of walls , will be used later
    private WallControllerManager() {
        super();
    }

    private static WallControllerManager instance;

    public static WallControllerManager getInstance() {
        if (instance == null) instance = new WallControllerManager();
        return instance;
    }
}
