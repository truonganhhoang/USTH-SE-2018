package controllers;

import javax.naming.ldap.Control;
import java.awt.*;

public class ControllerController {

    private ControllerController() {

    }

    public void init() {
        EnemyControllerManager.getInstance().clear();
        WallControllerManager.getInstance().clear();
        CollisionManager.getInstance().clear();
        PlayerController.getInstance().setX(-1);
        PlayerController.getInstance().setY(-1);
    }

    public void draw(Graphics g) {
        PlayerController.getInstance().draw(g);
        EnemyControllerManager.getInstance().draw(g);
        WallControllerManager.getInstance().draw(g);
    }

    public void run() {
        PlayerController.getInstance().run();
        EnemyControllerManager.getInstance().run();
        CollisionManager.getInstance().run();
        WallControllerManager.getInstance().run();
    }

    public static final ControllerController instance = new ControllerController();
}
