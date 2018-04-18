package controllers;

import controllers.movement.MoveType;
import main.GameConfig;
import main.GameMap;
import main.gameScreens.PlayGameScreen;
import models.Colliable;
import models.GameObject;
import models.Player;
import utilities.KeyInput;
import utilities.KeyInputListener;
import utilities.Utils;
import views.AnimationView;

import java.awt.*;


public class PlayerController extends SingleControllerWithAnimation {
    private KeyInput keyInput = new KeyInput();
    public KeyInputListener keyInputListener = new KeyInputListener(keyInput);
    long lastMove = System.currentTimeMillis();


    private PlayerController() {
        super();
        gameObject = new Player(1,1);
        gameView = new AnimationView();
        animationView = (AnimationView)gameView;
        unitName =  "explorer";
        gameObject.setPowerLevel(1);
        gameObject.setHealth(1);
    }

    //********* COLLISION **************************************************************//

    static int counter = 0;
    public boolean tryMove(GameObject go,int x2,int y2) {
        int x1 = go.getColumn(), y1 = go.getRow();
        System.out.println("moving from: " + x1 + " " + y1);
        int tileLength = GameMap.getInstance().getTILE_LENGTH();
        GameMap gameMap = GameMap.getInstance();
        if (Utils.canMoveTo(x1,y1,x2,y2,gameMap.exitX,gameMap.exitY)) {
            lastMove = System.currentTimeMillis();
            isMoving = true;
            animationView.firstImage = System.currentTimeMillis();
            beginPoint = new Point(go.getX(), go.getY());
            targetPoint = new Point(x2*tileLength-tileLength/2,y2*tileLength-tileLength/2);
            targetGrid = new Point(x2,y2);
          //  GameMap.playerTurn = false;
            System.out.println("moving to: " + targetGrid.x + " " + targetGrid.y);
            return true;
        }
        return false;
    }

    public void move(GameObject go) {
        int x2 = go.getColumn(), y2 = go.getRow();
        if (keyInput.keyDown) {y2++; if (tryMove(go,x2,y2)) moveType = MoveType.DOWN; return;}
        if (keyInput.keyUp) {y2--; if (tryMove(go,x2,y2)) moveType = MoveType.UP; return;}
        if (keyInput.keyRight) {x2++; if (tryMove(go,x2,y2)) moveType = MoveType.RIGHT; return;}
        if (keyInput.keyLeft) {x2--; if (tryMove(go,x2,y2)) moveType = MoveType.LEFT; return;}
        if (keyInput.keySpace) {GameMap.getInstance().playerTurn = false; lastMove = System.currentTimeMillis();}
    }

    public boolean finished() {
        return !isMoving && !isFighting;
    }

    public void draw(Graphics g) {
        if (animationView.nImage==0) animationView.setSheet("explorer_right.png",5);
        if (isMoving) animationView.drawImage(g,gameObject);
        else animationView.drawImage(g,gameObject,true);
    }

    boolean moveTurn = true;
    public void run() {
        if (isMoving) {moveAnimation(); return;}
        if (!GameMap.getInstance().playerTurn) return;
        long now = System.currentTimeMillis();
        if (now - lastMove < 400) return;
        move(gameObject);
    }

    private static PlayerController instance;

    public static PlayerController getInstance() {
        if (instance==null) instance = new PlayerController();
        return instance;
    }

}
