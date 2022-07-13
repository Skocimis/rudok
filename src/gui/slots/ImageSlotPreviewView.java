package gui.slots;

import gui.SlotView;
import model.slots.EmptySlot;
import model.slots.ImageSlot;
import windows.frames.MainFrame;

import javax.swing.*;
import java.awt.*;

public class ImageSlotPreviewView extends SlotView {
    ImageSlot slot;
    Image image;

    public ImageSlotPreviewView(ImageSlot slot){
        this.slot = slot;
        this.image = new ImageIcon(slot.getImage()).getImage();
    }

    public ImageSlot getSlot() {
        return slot;
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(slot.getBackgroundColor());
        int width = MainFrame.getInstance().getProjectView().getOpenPresentationView().getNavigationView().getComponent(0).getWidth();
        int height = MainFrame.getInstance().getProjectView().getOpenPresentationView().getNavigationView().getComponent(0).getHeight();
        //g.fillRect((int)(width*slot.getPosition().getX()), (int)(height*slot.getPosition().getY()), (int)(width*slot.getSize().getWidth()), (int)(height*slot.getSize().getHeight()));

        ((Graphics2D)g).drawImage(image, (int)(width*slot.getPosition().getX()), (int)(height*slot.getPosition().getY()), (int)(width*slot.getSize().getWidth()),
                (int)(height*slot.getSize().getHeight()), null);
    }

    public void setSlot(ImageSlot slot) {
        this.slot = slot;
    }
}
