package views;

import models.GameObject;
import utilities.Utils;

import java.awt.*;

public class ImageView implements GameView {
    private Image image;

    public void setImage(String link) {
        image = Utils.getImage(link);
    }

    public void setImage(Image im) {
        this.image = im;
    }

    public Image getImage() {
        return image;
    }

    public ImageView(String link) {
        this.image = Utils.getImage(link);
    }

    public void drawImage(Graphics g, GameObject go) {
        g.drawImage(image, go.getCornerX(), go.getCornerY(), go.getWidth(), go.getHeight(), null);
    }

    public void run() {

    }
}
