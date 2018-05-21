package controllers;

import main.GameMap;
import main.gameScreens.PlayGameScreen;
import models.GameObject;
import utilities.Utils;

import java.awt.*;

public class EnemyControllerWhite extends EnemyController implements Colliable {

    //**************************** CONSTRUCTOR ****************************
    private EnemyControllerWhite(int column, int row) {
        super(column, row);
        unitName = "mummy";
        moveDirections = 4;
        maxMoveStep = 2;
        moveStep = maxMoveStep;
        gameObject.setPowerLevel(2);
        gameObject.setHealth(2);
    }

    public static EnemyControllerWhite create(int column, int row) {
        return new EnemyControllerWhite(column, row);
    }


    public void init() {
        moveStep = maxMoveStep;
    }


    //**************************** INTERATION ****************************

    // White mummy prioritize horizontal movement over vertical movement
    // White mummy move 2 step
    @Override
    Point findDirection(int startColumn, int startRow) {
        int targetColumn, targetRow, tileLength = GameMap.getInstance().getTILE_LENGTH(), best = 10000000;
        Point destination = new Point(startColumn, startRow);
        GameObject player = PlayerController.getInstance().getGameObject();

        for (int i = 0; i <= moveDirections; i++) {
            targetColumn = startColumn + dx[i];
            targetRow = startRow + dy[i];
            if (Utils.manDistance(targetColumn, targetRow, player.getColumn(), player.getRow()) <= best) {
                best = Utils.manDistance(targetColumn, targetRow, player.getColumn(), player.getRow());
                if (Utils.canMoveTo(startColumn, startRow, targetColumn, targetRow, GameMap.getInstance().exitX, GameMap.getInstance().exitY)) {
                    destination = new Point(targetColumn, targetRow);
                    moveType = moveTypes[i];
                    beginPoint = new Point(gameObject.getX(), gameObject.getY());
                    targetPoint = new Point(targetColumn * tileLength - tileLength / 2, targetRow * tileLength - tileLength / 2);
                    targetGrid = new Point(targetColumn, targetRow);
                }
            }
        }
        return destination;
    }

    public void move() {
        GameObject go = gameObject;
        Point target = findDirection(go.getColumn(), go.getRow());
        if (target.x == getColumn() && target.y == getRow()) return; // do nothing because already there
        isMoving = true;
        animationView.firstImage = System.currentTimeMillis();
    }

    public boolean finished() {
        return (!isMoving) && (!isFighting) && moveStep == 0;
    }

    public void run() {
        if (isMoving) {
            moveAnimation();
            return;
        }
        if (GameMap.getInstance().playerTurn) return;
        if (moveStep > 0) {
            moveStep--;
            move();
        }
    }


    //**************************** RENDERING ****************************
    public void draw(Graphics g) {
        if (animationView.nImage == 0) animationView.setSheet("mummy_down.png", 5);
        if (isMoving) animationView.drawImage(g, gameObject);
        else animationView.drawImage(g, gameObject, true);
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


    //********* UNIT TESTING **************************************************************//
    // By HuynhVinhNam 6/5/2018

    public static void main(String[] args) {
        EnemyControllerWhite controller = new EnemyControllerWhite(1, 1);
        GameObject player = PlayerController.getInstance().getGameObject();

        int x1 = 5, y1 = 4, x2 = 3, y2 = 6;
        boolean success = true;

        for (int k = 1; k <= 1000; k++) {
            x1 = (x1 * 5 + 5) % 6 + 1;
            y1 = (y1 * 7 + 4) % 6 + 1;
            x2 = (x2 * 11 + 7) % 6 + 1;
            y2 = (y2 * 13 + 9) % 6 + 1;

            player.setColumn(x1);
            player.setRow(y1);
            player.setHealth(1);
            controller.setColumn(x2);
            controller.setRow(y2);
            controller.setHealth(10);

            Point ans1 = controller.findDirection(x2, y2);
            Point ans2 = new Point(x2, y2);
            int best = Utils.manDistance(x1, y1, x2, y2);
            for (int i = 0; i <= 4; i++) {
                int x = x2 + dx[i];
                int y = y2 + dy[i];
                if (Utils.manDistance(x1, y1, x, y) <= best) {
                    best = Utils.manDistance(x1, y1, x, y);
                    if (x > 0 && y > 0 && x <= 10 && y <= 10) ans2 = new Point(x, y);
                }
            }
            if (!ans1.equals(ans2)) {
                System.out.println("Wrong movement at: " + ans1.x + " " + ans1.y + " " + ans2.x + " " + ans2.y);
                success = false;
            }

            if (controller.getX() == player.getX() && controller.getY() == player.getY()) {
                controller.onCollideWith((Colliable) player);
                if (player.getHealth() > 0) {
                    System.out.println("Wrong player health at: " + x1 + " " + y1 + " " + x2 + " " + y2);
                    success = false;
                }
            }
        }

        if (success) System.out.println("Successful");
    }
}
