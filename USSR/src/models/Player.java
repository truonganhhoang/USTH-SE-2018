package models;

public class Player extends GameObject implements Colliable {

    public Player() {

    }

    public Player(int column,int row) {
        super(column,row);
    }

    @Override
    public GameObject getCollisionObject() {
        return this;
    }

    @Override
    public void onCollide(Colliable col) {

    }
}
