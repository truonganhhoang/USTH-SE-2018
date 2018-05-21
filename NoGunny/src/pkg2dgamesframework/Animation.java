/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg2dgamesframework;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 *
 * @author phamn
 */
public class Animation {
    
    private long beginTime = 0;
    private long mesure = 20;
    private AFrameOnImage[] frames;
    private int numOfFrame = 0;
    private int currentFrame = 0;
    
    public Animation(long mesure){
        this.mesure = mesure;
    }
    
    public void updateMe(long deltaTime){
        if(numOfFrame > 0) {
            if(deltaTime - beginTime > mesure) {
                currentFrame++;
                if(currentFrame>=numOfFrame) 
                    currentFrame = 0;
                beginTime = deltaTime;
            }
        }
    } 
    public void addFrame(AFrameOnImage sprite){
        AFrameOnImage[] bufferSprites = frames;
        frames = new AFrameOnImage[numOfFrame+1];
        for(int i = 0;i<numOfFrame;i++) frames[i] = bufferSprites[i];
        frames[numOfFrame] = sprite;
        numOfFrame++;
    }   
    public void paintAnims(int x, int y, BufferedImage image, Graphics2D g2, int anchor, float rotation){
        frames[currentFrame].paint(x, y, image, g2, anchor, rotation);
    }
}
