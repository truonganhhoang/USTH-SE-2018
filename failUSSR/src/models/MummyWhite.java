package models;

import controllers.PlayerController;
import controllers.movement.Move;
import controllers.movement.MoveType;
import main.GameMap;
import utilities.Utils;

import java.awt.*;

public class MummyWhite extends Mummy{

    protected MummyWhite(int column, int row) {
        super(column,row);
        setPowerLevel(2);
        setHealth(2);
    }

    public MoveType findDirection() {
        int targetColumn, targetRow, tileLength = GameMap.getInstance().getTILE_LENGTH(), best = 10000000;
        MoveType moveType = null;
        int startColumn = column, startRow = row;
        GameObject player = PlayerController.getInstance().getGameObject();

        // 4 move directions
        for (int i=0;i<=4;i++) {
            targetColumn = startColumn + dx[i];
            targetRow = startRow + dy[i];
            if (Utils.manDistance(targetColumn,targetRow,player.getColumn(),player.getRow()) <= best) {
                best = Utils.manDistance(targetColumn,targetRow,player.getColumn(),player.getRow());
                if (Utils.canMoveTo(startColumn,startRow,targetColumn,targetRow,GameMap.getInstance().exitX,GameMap.getInstance().exitY)) {
                    moveType = moveTypes[i];
                    /*beginPoint = new Point(gameObject.getX(),gameObject.getY());
                    targetPoint = new Point(targetColumn*tileLength-tileLength/2,targetRow*tileLength-tileLength/2);
                    targetGrid = new Point(targetColumn,targetRow);*/
                }
            }
        }
        return moveType;
    }

    public void moveGameObject() {
        Move moveDirection = Move.create(moveType);
        moveDirection.move(this);
    }

    public void run() {
        if (isMoving) moveGameObject();
        if (GameMap.getInstance().playerTurn) return;


    }
}
