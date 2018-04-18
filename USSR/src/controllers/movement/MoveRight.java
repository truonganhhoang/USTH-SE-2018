package controllers.movement;

import models.GameObject;

public class MoveRight extends Move {

    public void move(GameObject go) {
        go.setX(go.getX() + moveSpeed);
    }
}
