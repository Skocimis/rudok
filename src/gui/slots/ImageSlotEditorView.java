
package gui.slots;

import gui.SlideView;
import gui.SlotView;
import model.slots.EmptySlot;
import model.slots.ImageSlot;
import windows.frames.MainFrame;

import javax.swing.*;
import java.awt.*;

public class ImageSlotEditorView extends SlotView {
    ImageSlot slot;
    Image image;

    public ImageSlotEditorView(ImageSlot imageSlot){
        this.slot = imageSlot;
        this.image = new ImageIcon(slot.getImage()).getImage();
    }

    public ImageSlot getSlot() {
        return slot;
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(slot.getBackgroundColor());
        ((Graphics2D)(g)).setStroke(slot.getGraphicStroke());
        SlideView selectedComponent = MainFrame.getInstance().getProjectView().getOpenPresentationView().getjPanelSlidesView().getSelectedComponent();
        int width = selectedComponent.getWidth();
        int height = selectedComponent.getHeight();
        ((Graphics2D)(g)).setStroke(new BasicStroke());
        g.setColor(Color.black);
        g.drawImage(image, (int)(width*slot.getPosition().getX()), (int)(height*slot.getPosition().getY()), (int)(width*slot.getSize().getWidth()),
                (int)(height*slot.getSize().getHeight()), null);
        /*g.fillRect((int)(width*slot.getPosition().getX()), (int)(height*slot.getPosition().getY()), (int)(width*slot.getSize().getWidth()), (int)(height*slot.getSize().getHeight()));
        g.setColor(slot.getColor());
        g.drawRect((int)(width*slot.getPosition().getX()), (int)(height*slot.getPosition().getY()), (int)(width*slot.getSize().getWidth()), (int)(height*slot.getSize().getHeight()));*/
    }

    public void setSlot(ImageSlot slot) {
        this.slot = slot;
    }
}
