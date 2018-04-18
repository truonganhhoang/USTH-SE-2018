package controllers;

public class EnemyControllerWhite extends EnemyController {

    public EnemyControllerWhite(int column,int row) {
        super(column,row);
        unitName = "mummy";
        moveDirections = 4;
        maxMoveStep = 2;
        gameObject.setPowerLevel(2);
        gameObject.setHealth(2);
    }

    public void init() {moveStep = maxMoveStep;}
}
