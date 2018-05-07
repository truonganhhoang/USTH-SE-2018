package controllers.movement;

import models.GameObject;

public class MoveLeft extends Move {

    public void move(GameObject go) {
        go.setX(go.getX() - moveSpeed);
    }
}
