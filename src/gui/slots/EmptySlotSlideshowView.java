package gui.slots;

import gui.SlotView;
import model.slots.EmptySlot;
import windows.frames.MainFrame;

import java.awt.*;

public class EmptySlotSlideshowView extends SlotView {
    EmptySlot slot;

    public EmptySlotSlideshowView(EmptySlot slot){
        this.slot = slot;
    }

    public EmptySlot getSlot() {
        return slot;
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(slot.getBackgroundColor());
        ((Graphics2D)(g)).setStroke(slot.getGraphicStroke());
        int width = MainFrame.getInstance().getProjectView().getOpenPresentationView().getjPanelSlideshowView().getWidth();
        int height = MainFrame.getInstance().getProjectView().getOpenPresentationView().getjPanelSlideshowView().getHeight();
        g.fillRect((int)(width*slot.getPosition().getX()), (int)(height*slot.getPosition().getY()), (int)(width*slot.getSize().getWidth()), (int)(height*slot.getSize().getHeight()));
        g.setColor(slot.getColor());
        g.drawRect((int)(width*slot.getPosition().getX()), (int)(height*slot.getPosition().getY()), (int)(width*slot.getSize().getWidth()), (int)(height*slot.getSize().getHeight()));
    }

    public void setSlot(EmptySlot slot) {
        this.slot = slot;
    }
}
