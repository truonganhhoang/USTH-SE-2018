package controllers;

import models.GameObject;

public interface Colliable {
    GameObject getCollisionObject();

    void onCollideWith(Colliable col);
}
