package controllers;

import javax.naming.ldap.Control;
import java.awt.*;

public class ControllerController {

    private ControllerController() {

    }

    public void init() {

    }

    public void draw(Graphics g) {
        PlayerController.getInstance().draw(g);
    }

    public void run() {
        PlayerController.getInstance().run();
    }

    public static final ControllerController instance = new ControllerController();
}
