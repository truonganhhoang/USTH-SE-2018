package controllers;

import controllers.movement.MoveType;
import main.GameMap;
import main.gameScreens.PlayGameScreen;
import models.GameObject;
import models.MummyRed;
import utilities.Utils;

import java.awt.*;

public class EnemyControllerRed extends EnemyController implements Colliable {

    //**************************** CONSTRUCTOR ****************************
    private EnemyControllerRed(int column, int row) {
        super(column,row);
        unitName = "redmummy";
        moveDirections = 4;
        maxMoveStep = 2;
        moveStep = maxMoveStep;
        gameObject.setPowerLevel(100);
        gameObject.setHealth(100);
    }

    public static EnemyControllerRed create(int column, int row) {
        return new EnemyControllerRed(column,row);
    }


    //**************************** INTERATION ****************************

    public void move() {
        moveType = ((MummyRed)gameObject).findDirection();
        if (moveType==MoveType.STAY) return;
        isMoving = true;
    }

    public boolean finished() {
        return (!isMoving) && (!isFighting) && moveStep==0;
    }

    public void run() {
        if (isMoving) {moveAnimation(); return;}
        if (GameMap.getInstance().playerTurn) return;
        if (moveStep > 0) {moveStep--; move();}
    }

    //**************************** RENDERING ****************************
    public void draw(Graphics g) {
        if (animationView.nImage==0) animationView.setSheet("redmummy_down.png",5);
        if (isMoving) animationView.drawImage(g,gameObject);
        else animationView.drawImage(g,gameObject,true);
    }

    //********* COLLISION **************************************************************//
    @Override
    public GameObject getCollisionObject() {
        return gameObject;
    }

    @Override
    public void onCollideWith(Colliable col) {
        if (col instanceof EnemyController) {
            GameObject go = col.getCollisionObject();
            go.takeDamage(gameObject.getPowerLevel());
        }
        if (col instanceof PlayerController) {
            GameObject go = col.getCollisionObject();
            go.takeDamage(1000);
        }
    }
}
