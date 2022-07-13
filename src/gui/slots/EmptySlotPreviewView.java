package gui.slots;

import gui.SlotView;
import model.slots.EmptySlot;
import windows.frames.MainFrame;

import java.awt.*;

public class EmptySlotPreviewView extends SlotView {
    EmptySlot slot;

    public EmptySlotPreviewView(EmptySlot emptySlot){
        this.slot = emptySlot;
    }

    public EmptySlot getSlot() {
        return slot;
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(slot.getBackgroundColor());
        int width = MainFrame.getInstance().getProjectView().getOpenPresentationView().getNavigationView().getComponent(0).getWidth();
        int height = MainFrame.getInstance().getProjectView().getOpenPresentationView().getNavigationView().getComponent(0).getHeight();
        g.fillRect((int)(width*slot.getPosition().getX()), (int)(height*slot.getPosition().getY()), (int)(width*slot.getSize().getWidth()), (int)(height*slot.getSize().getHeight()));
    }

    public void setSlot(EmptySlot slot) {
        this.slot = slot;
    }
}
