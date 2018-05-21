package main.gameScreens;

import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public abstract class GameScreen implements KeyListener, MouseListener, MouseMotionListener {
    // this is base class for different game scenes
    public GameScreen() {

    }

    abstract public void init();

    abstract public void update(Graphics g);

    abstract public void run();
}
