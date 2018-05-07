package controllers.movement;

import models.GameObject;

public class Move {

    public static final int moveSpeed = 4;

    public void move(GameObject go) {

    }

    public static Move create(MoveType moveType) {
        if (moveType==MoveType.UP) return new MoveUp();
        if (moveType==MoveType.DOWN) return new MoveDown();
        if (moveType==MoveType.RIGHT) return new MoveRight();
        if (moveType==MoveType.LEFT) return new MoveLeft();
        return new MoveUp();
    }
}
