package controllers;

import main.GameMap;
import models.GameObject;
import utilities.Utils;

import java.awt.*;

public class EnemyControllerScorpion extends EnemyController implements Colliable{
    private EnemyControllerScorpion(int column,int row) {
        super(column,row);
        unitName = "scorpion";
        moveDirections = 4;
        maxMoveStep = 1;
        moveStep = maxMoveStep;
        gameObject.setPowerLevel(2);
        gameObject.setHealth(2);
    }

    public static EnemyControllerScorpion create(int column, int row) {
        return new EnemyControllerScorpion(column,row);
    }

    @Override
    Point findDirection(int startColumn, int startRow) {
        int targetColumn, targetRow, tileLength = GameMap.getInstance().getTILE_LENGTH(), best = 10000000;
        Point destination = new Point(startColumn,startRow);
        GameObject player = PlayerController.getInstance().getGameObject();

        for (int i=0;i<=moveDirections;i++) {
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
