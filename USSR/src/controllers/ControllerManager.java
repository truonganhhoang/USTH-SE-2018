package controllers;

import sun.util.calendar.BaseCalendar;

import java.awt.*;
import java.util.Iterator;
import java.util.Vector;

public class ControllerManager {

    protected Vector<SingleController> singleControllers;

    public ControllerManager() {
        singleControllers = new Vector<>();
    }

    public Vector<SingleController> getManager() {
        return singleControllers;
    }

    public int size() {
        return singleControllers.size();
    }

    public void add(SingleController singleController) {
        singleControllers.add(singleController);
        if (singleController instanceof Colliable) CollisionManager.getInstance().add((Colliable) singleController);
    }

    public void remove(SingleController sc) {
        singleControllers.remove(sc);
    }

    public void remove() {
        Iterator<SingleController> it = singleControllers.iterator();
        while (it.hasNext()) {
            SingleController sc = it.next();
            if (sc.deleteNow()) it.remove();
        }
    }

    public void clear() {
        singleControllers.clear();
    }

    // draw all unit onto the Graphics g
    public void draw(Graphics g) {
        for (SingleController singleController : singleControllers)
            singleController.draw(g);
    }

    // After each update run, it checks if any element can be deleted, if it exists then delete it
    public void run() {
        for (SingleController singleController : singleControllers)
            singleController.run();
        remove();
    }
}
