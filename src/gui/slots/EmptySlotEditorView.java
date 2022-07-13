package gui.slots;

import gui.SlideView;
import gui.SlotView;
import model.slots.EmptySlot;
import windows.frames.MainFrame;

import java.awt.*;

public class EmptySlotEditorView extends SlotView {
    EmptySlot slot;

    public EmptySlotEditorView(EmptySlot emptySlot){
        this.slot = emptySlot;
    }

    public EmptySlot getSlot() {
        return slot;
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(slot.getBackgroundColor());
        ((Graphics2D)(g)).setStroke(slot.getGraphicStroke());
        SlideView selectedComponent = MainFrame.getInstance().getProjectView().getOpenPresentationView().getjPanelSlidesView().getSelectedComponent();
        int width = selectedComponent.getWidth();
        int height = selectedComponent.getHeight();
        g.fillRect((int)(width*slot.getPosition().getX()), (int)(height*slot.getPosition().getY()), (int)(width*slot.getSize().getWidth()), (int)(height*slot.getSize().getHeight()));
        g.setColor(slot.getColor());
        g.drawRect((int)(width*slot.getPosition().getX()), (int)(height*slot.getPosition().getY()), (int)(width*slot.getSize().getWidth()), (int)(height*slot.getSize().getHeight()));
    }

    public void setSlot(EmptySlot slot) {
        this.slot = slot;
    }
}
