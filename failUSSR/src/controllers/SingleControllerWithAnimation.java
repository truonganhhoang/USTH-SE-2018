package controllers;

import controllers.movement.Move;
import controllers.movement.MoveType;
import main.GameConfig;
import main.GameMap;
import models.GameObject;
import views.AnimationView;

import java.awt.*;

public abstract class SingleControllerWithAnimation extends SingleController {
    protected boolean initAnimation = false;
    protected AnimationView animationView;
    protected Point beginPoint, targetPoint, targetGrid;
    protected String unitName;


    public SingleControllerWithAnimation() {

    }

    public void initAnimation() {
        animationView.setSheet(unitName + "_" + MoveType.fileNameOf(moveType) + ".png",5);
    }

    protected long lastImage = 0;
    protected void moveAnimation() {
        if (!initAnimation) {initAnimation(); initAnimation = true;}
        Move moveDirection = Move.create(moveType);
        moveDirection.move(gameObject);

        long now = System.currentTimeMillis();
        if (now - lastImage >= 15) {
            System.out.println("moving: " + animationView.currentImage);
            animationView.currentImage++;
            lastImage = now;
        }

        //Nếu đã đi >= mục tiêu thì dừng
        boolean doneMoveHorizontal = (targetPoint.x != beginPoint.x &&
                (getX() - beginPoint.x) / (targetPoint.x - beginPoint.x) >= 1);

        boolean doneMoveVertical = (targetPoint.y != beginPoint.y &&
                (getY() - beginPoint.y) / (targetPoint.y - beginPoint.y) >= 1);

        if (doneMoveHorizontal || doneMoveVertical) {
            isMoving = false;
            setColumn(targetGrid.x);
            setRow(targetGrid.y);
            initAnimation = false;
        }
    }

    public void move(GameObject go,Point target) {
        if (target.x==getColumn() && target.y==getRow()) return; // do nothing because already there
        isMoving = true;
        //animationView.firstImage = System.currentTimeMillis();
    }
}
