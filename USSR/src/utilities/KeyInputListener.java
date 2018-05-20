package utilities;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyInputListener implements KeyListener {
    private KeyInput keyInput;

    public KeyInputListener(KeyInput ki) {
        keyInput = ki;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                keyInput.keyLeft = true;
                break;
            case KeyEvent.VK_RIGHT:
                keyInput.keyRight = true;
                break;
            case KeyEvent.VK_DOWN:
                keyInput.keyDown = true;
                break;
            case KeyEvent.VK_UP:
                keyInput.keyUp = true;
                break;
            case KeyEvent.VK_SPACE:
                keyInput.keySpace = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                keyInput.keyLeft = false;
                break;
            case KeyEvent.VK_RIGHT:
                keyInput.keyRight = false;
                break;
            case KeyEvent.VK_DOWN:
                keyInput.keyDown = false;
                break;
            case KeyEvent.VK_UP:
                keyInput.keyUp = false;
                break;
            case KeyEvent.VK_SPACE:
                keyInput.keySpace = false;
        }
    }
}
