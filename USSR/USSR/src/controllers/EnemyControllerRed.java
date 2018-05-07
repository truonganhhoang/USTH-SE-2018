package controllers;

import main.GameMap;
import main.gameScreens.PlayGameScreen;
import models.GameObject;
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
    // Mummy đỏ đi dọc trước ngang sau
    @Override
    Point findDirection(int startColumn,int startRow) {
        int targetColumn, targetRow, tileLength = GameMap.getInstance().getTILE_LENGTH(), best = 10000000;
        Point destination = new Point(startColumn,startRow);
        GameObject player = PlayerController.getInstance().getGameObject();

        for (int i=moveDirections;i>=0;i--) {
            targetColumn = startColumn + dx[i];
            targetRow = startRow + dy[i];
            if (Utils.manDistance(targetColumn,targetRow,player.getColumn(),player.getRow()) <= best) {
                best = Utils.manDistance(targetColumn,targetRow,player.getColumn(),player.getRow());
                if (Utils.canMoveTo(startColumn,startRow,targetColumn,targetRow,GameMap.getInstance().exitX,GameMap.getInstance().exitY)) {
                    destination = new Point(targetColumn,targetRow);
                    moveType = moveTypes[i];
                    beginPoint = new Point(gameObject.getX(),gameObject.getY());
                    targetPoint = new Point(targetColumn*tileLength-tileLength/2,targetRow*tileLength-tileLength/2);
                    targetGrid = new Point(targetColumn,targetRow);
                }
            }
        }
        return destination;
    }

    public void move(GameObject go) {
        Point target = findDirection(go.getColumn(),go.getRow());
        if (target.x==getColumn() && target.y==getRow()) return;
        isMoving = true;
        animationView.firstImage = System.currentTimeMillis();
    }

    public boolean finished() {
        return (!isMoving) && (!isFighting) && moveStep==0;
    }

    public void run() {
        if (isMoving) {moveAnimation(); return;}
        if (GameMap.getInstance().playerTurn) return;
        if (moveStep > 0) {moveStep--; move(gameObject);}
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
