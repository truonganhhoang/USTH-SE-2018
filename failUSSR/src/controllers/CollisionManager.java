package controllers;

import models.GameObject;

import java.util.Iterator;
import java.util.Vector;

public class CollisionManager {
    protected Vector<Colliable> pool;

    private CollisionManager() {
        pool = new Vector<>();
    }


    public int size() {
        return pool.size();
    }

    public void add(Colliable col) {
        pool.add(col);
    }

    public void remove(Colliable col) {
        pool.remove(col);
    }

    public void remove() {
        Iterator<Colliable> it = pool.iterator();
        while (it.hasNext()) {
            Colliable col = it.next();
            GameObject go = col.getCollisionObject();
            if (!go.isAlive()) it.remove();
        }
    }

    public void clear() {
        pool.clear();
    }

    public void run() {
        if (!pool.contains(PlayerController.instance)) pool.add(PlayerController.instance);

        int n = pool.size();
        for (int i=0;i<n-1;i++)
            for (int j=i+1;j<n;j++) {
                Colliable col1 = pool.get(i);
                Colliable col2 = pool.get(j);

                GameObject go1 = col1.getCollisionObject();
                GameObject go2 = col2.getCollisionObject();
                if (!go1.isAlive() || !go2.isAlive()) continue;
                if (go1.getColumn()!=go2.getColumn() || go1.getRow()!=go2.getRow()) continue;

                //col1.onCollideWith(col2) nghĩa là col1 tác dụng lên col2
                if (go1.getPowerLevel() >= go2.getPowerLevel()) col1.onCollideWith(col2);
                else col2.onCollideWith(col1);
            }

        remove();
    }


    private static CollisionManager instance;
    public static final CollisionManager getInstance() {
        if (instance==null) instance = new CollisionManager();
        return instance;
    }
}
