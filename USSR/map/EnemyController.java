package controllers;

import main.GameMap;
import models.GameObject;
import views.AnimationView;

import java.awt.*;

public class EnemyController extends SingleControllerWithAnimation {
    protected int moveDirections = 4;
    protected int maxMoveStep = 0;
    protected int moveStep = 0;

    EnemyController(int column,int row) {
        super();
        gameObject = new GameObject(column,row);
        gameView = new AnimationView();
        animationView = (AnimationView)gameView;
    }

    public void draw(Graphics g) {gameView.drawImage(g,gameObject);}

    public void run() {
        if (GameMap.getInstance().getPlayerTurn()) return;
    }

    @Override
    void findDirection() {

    }
}
