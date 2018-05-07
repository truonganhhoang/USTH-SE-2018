package controllers.movement;

import models.GameObject;

public class MoveDown extends Move {

    public void move(GameObject go) {
        go.setY(go.getY() + moveSpeed);
    }
}
