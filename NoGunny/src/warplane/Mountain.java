/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package warplane;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author duanp
 */
public class Mountain {
    private BufferedImage mountainImage;
    
    private int x1,x2;
    private final int y1,y2;
    
    public Mountain() {
        try {
            mountainImage= ImageIO.read(new File("Assets/moutain.png"));
        } catch (IOException ex) { }
        
        x1=-10;
        y1=270;
        x2=x1+990;
        y2=270;
        
    }
    
    public void Update(){
        x1-=2;
        x2-=2;
         if(x1<0) x2=x1+990;
        if(x2<-0) x1=x2+990;
       
    }
    
    public void Paint( Graphics2D  g2){
        
       g2.drawImage(mountainImage, x1, y1, null);
       g2.drawImage(mountainImage, x2, y2, null);
    
    }
    
    
}
