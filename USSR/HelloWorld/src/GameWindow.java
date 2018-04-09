import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


/**
 * Created by Le Huy Duc on 02/10/2016.
 */
public class GameWindow extends Frame {
    //**********
    //********** IMAGES/DRAWING VARIABLES  ***************************************************************
    Image backgroundImage = null;
    private final int BACKGROUND_WIDTH = 1000, BACKGROUND_HEIGHT = 600;

    public GameWindow() {
        this.setVisible(true);
        this.setSize(BACKGROUND_WIDTH,BACKGROUND_HEIGHT);
        this.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
                System.out.println("windowopening");
            }

            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("windowclosing");
                System.exit(0);
            }

            @Override
            public void windowClosed(WindowEvent e) {
                System.out.println("windowclosed");
            }

            @Override
            public void windowIconified(WindowEvent e) {
                System.out.println("windowiconfied");
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
                System.out.println("windowdeiconfied");
            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });


        try {
            backgroundImage = ImageIO.read(new File("src/helloworld.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        System.out.println("Drawing Images");
    }


    @Override
    public void update(Graphics g) {
        g.drawImage(backgroundImage,0,0,BACKGROUND_WIDTH,BACKGROUND_HEIGHT,null);
    }

}


