package models;

public interface Colliable {
    GameObject getCollisionObject();
    void onCollide(Colliable col);
}
