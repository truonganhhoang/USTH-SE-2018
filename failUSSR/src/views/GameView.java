package views;

import models.GameObject;

import java.awt.*;

public interface GameView {
    void setImage(String link);
    void setImage(Image im);
    Image getImage();
    void drawImage(Graphics g, GameObject gameObject);
}
