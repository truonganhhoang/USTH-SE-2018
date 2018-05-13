/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg2dgamesframework;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

/**
 *
 * @author phamn
 */
public class AFrameOnImage {

    private boolean enablePaintRect = false;
    private int[] dimsBounds = new int[4];

    public AFrameOnImage(int xOnImage, int yOnImage, int w, int h) {
        dimsBounds[0] = xOnImage;
        dimsBounds[1] = yOnImage;
        dimsBounds[2] = w;
        dimsBounds[3] = h;
    }

    public void visibleRectDebug(boolean enable) {
        enablePaintRect = enable;
    }
    public int[] getBounds() {
        return dimsBounds;
    }

    public void paint(int x, int y, BufferedImage image, Graphics2D g2, int anchor, float rotation) {

        BufferedImage imageDraw = image.getSubimage(dimsBounds[0], dimsBounds[1], dimsBounds[2], dimsBounds[3]);

        AffineTransform tx = new AffineTransform();
        tx.rotate(rotation, imageDraw.getWidth() / 2, imageDraw.getHeight() / 2);

        AffineTransformOp op = new AffineTransformOp(tx,
                AffineTransformOp.TYPE_BILINEAR);
        imageDraw = op.filter(imageDraw, null);

        g2.drawImage(imageDraw, x, y, null);
        if (enablePaintRect) {
            paintBound(g2);
        }
    }

    private void paintBound(Graphics2D g) {
    }
}
