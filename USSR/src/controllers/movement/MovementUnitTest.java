package controllers.movement;

import models.GameObject;

import java.util.Random;

public class MovementUnitTest {

    // By HuynhVinhNam 6/5/2018
    public static void main(String[] args) {
        GameObject gameObject = new GameObject(5, 5);
        MoveType[] moveTypes = {MoveType.UP, MoveType.DOWN, MoveType.RIGHT, MoveType.LEFT, MoveType.STAY};
        boolean passTest = true;

        for (int i = 1; i <= 1000; i++) {
            Random random = new Random();
            MoveType moveType = moveTypes[random.nextInt(5)]; // get a random move type
            Move mover = Move.create(moveType);

            int expectedX = gameObject.getX(), expectedY = gameObject.getY();
            int moveSpeed = Move.moveSpeed;
            if (moveType == MoveType.UP) expectedY -= moveSpeed;
            if (moveType == MoveType.DOWN) expectedY += moveSpeed;
            if (moveType == MoveType.LEFT) expectedX -= moveSpeed;
            if (moveType == MoveType.RIGHT) expectedX += moveSpeed;
            mover.move(gameObject);

            if (expectedX != gameObject.getX() || expectedY != gameObject.getY()) {
                System.out.println("Something went wrong");
                System.out.println(expectedX + " " + expectedY + " " + gameObject.getX() + " " + gameObject.getY());
                passTest = false;
            }
        }

        if (passTest)
            System.out.println("Successful");
    }
}
