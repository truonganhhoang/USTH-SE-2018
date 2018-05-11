/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package warplane;

import java.awt.image.BufferedImage;

/**
 *
 * @author duanp
 */
public class IntroGame {
    private BufferedImage bufferedImage;
     public static final int INTROGAME = 0;
     public int tutorialState = INTROGAME;
      public int storyTutorial = 0;
    public String[] texts1 = new String[4];
     public String textTutorial;
    public int currentSize = 1;
    
    public IntroGame(){
        texts1[0] = "We are heros, and our mission is protecting our Home\nEarth....";
        texts1[1] = "There was a Monster from University on Earth in 10 years\n"
                + "and we lived in the scare in that 10 years....";
        texts1[2] = "Now is the time for us, kill it and get freedom!....";
        texts1[3] = "      LET'S GO!.....";
        textTutorial = texts1[0];
    }
    
}
