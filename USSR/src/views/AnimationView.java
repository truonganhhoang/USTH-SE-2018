package views;

import models.GameObject;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class AnimationView implements GameView {
    public int sizeX = 60, sizeY = 60;
    public long firstImage = 0;
    public BufferedImage spriteSheetImage;
    public Image[] sheet = new Image[7];
    public int nImage, currentImage = 0;

    public void setImage(String link) {

    }

    public void setImage(Image im) {

    }

    public Image getImage() {
        return null;
    }

    public void setSheet(String link, int n) {
        try {
            spriteSheetImage = ImageIO.read(new File("images/" + link));
        } catch (IOException e) {
            e.printStackTrace();
        }

        nImage = n;
        currentImage = 0;
        for (int i = 0; i < n; i++) sheet[i] = spriteSheetImage.getSubimage(i * sizeX, 0, sizeX, sizeY);
    }

    // cycle through image sheet
    public void drawImage(Graphics g, GameObject go) {
        currentImage %= nImage;
        g.drawImage(sheet[currentImage], go.getCornerX(), go.getCornerY(), go.getWidth(), go.getHeight(), null);
    }

    public void drawImage(Graphics g, GameObject go, boolean stayingStillAlwaysTrue) {
        g.drawImage(sheet[0], go.getCornerX(), go.getCornerY(), go.getWidth(), go.getHeight(), null);
    }

    public void run() {

    }
}
