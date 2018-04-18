package utilities;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseInputListener implements MouseListener, MouseMotionListener {
    private MouseInput mouseInput;

    public MouseInputListener(MouseInput mi) {
        mouseInput = mi;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        mouseInput.pressed = true;
        mouseInput.mouseLocation = e.getLocationOnScreen();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseInput.mouseLocation = e.getLocationOnScreen();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        mouseInput.pressed = true;
        mouseInput.mouseLocation = e.getLocationOnScreen();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        mouseInput.pressed = false;
        mouseInput.mouseLocation = e.getLocationOnScreen();
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
