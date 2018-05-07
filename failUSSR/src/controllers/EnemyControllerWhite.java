package controllers;

import controllers.movement.MoveType;
import main.GameMap;
import main.gameScreens.PlayGameScreen;
import models.GameObject;
import models.MummyWhite;
import utilities.Utils;

import java.awt.*;

public class EnemyControllerWhite extends EnemyController implements Colliable {

    //**************************** CONSTRUCTOR ****************************
    private EnemyControllerWhite(int column,int row) {
        super(column,row);
        unitName = "mummy";
        moveDirections = 4;
        maxMoveStep = 2;
        moveStep = maxMoveStep;
        gameObject.setPowerLevel(2);
        gameObject.setHealth(2);
    }

    public static EnemyControllerWhite create(int column, int row) {
        return new EnemyControllerWhite(column,row);
    }


    public void init() {moveStep = maxMoveStep;}


    //**************************** INTERATION ****************************


    public void move() {
        MoveType moveType = ((MummyWhite)gameObject).findDirection();
        if (moveType==MoveType.STAY) return; // do nothing because already there
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
        if (animationView.nImage==0) animationView.setSheet("mummy_down.png",5);
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

    }
}
