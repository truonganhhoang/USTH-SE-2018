package models;

public class Mummy extends GameObject implements Colliable {
    private static int WIDTH =  50, HEIGHT = 50;
    private Mummy(int column,int row) {super(row,column);}

    @Override
    public GameObject getCollisionObject() {
        return this;
    }

    @Override
    public void onCollide(Colliable col) {

    }
}
