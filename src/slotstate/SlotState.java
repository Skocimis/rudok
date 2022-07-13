package slotstate;

import gui.SlideView;
import model.slots.EmptySlot;
import windows.frames.MainFrame;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public abstract class SlotState {
    private Cursor mouseOverSlotCursor;

    public void mouseClicked(double x, double y) {

    }
    public void mouseDoubleClicked(double x, double y){

    }

    public void mousePressed(double x, double y) {
        
    }

    public void mouseReleased(double x, double y) {

    }

    public void mouseMoved(double x, double y) {

    }

    public void mouseDragged(double x, double y) {

    }

    public Cursor getMouseOverSlotCursor() {
        return mouseOverSlotCursor;
    }

    public void setMouseOverSlotCursor(Cursor mouseOverSlotCursor) {
        this.mouseOverSlotCursor = mouseOverSlotCursor;
    }
}
