package gui.slots;

import gui.SlotView;
import model.slots.EmptySlot;
import model.slots.ImageSlot;
import windows.frames.MainFrame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;

public class ImageSlotSlideshowView extends SlotView {
    ImageSlot slot;
    Image image;
    Image imageScaled;
    public ImageSlotSlideshowView(ImageSlot slot){
        this.slot = slot;
        this.image = new ImageIcon(slot.getImage()).getImage();
    }

    public ImageSlot getSlot() {
        return slot;
    }

    @Override
    public void paint(Graphics g) {
        System.out.println("Pozivam pt");
        g.setColor(slot.getBackgroundColor());
        ((Graphics2D)(g)).setStroke(slot.getGraphicStroke());
        int width = MainFrame.getInstance().getProjectView().getOpenPresentationView().getjPanelSlideshowView().getWidth();
        int height = MainFrame.getInstance().getProjectView().getOpenPresentationView().getjPanelSlideshowView().getHeight();
        ((Graphics2D)(g)).setStroke(new BasicStroke());
        g.setColor(Color.black);
        //g.fillRect((int)(width*slot.getPosition().getX()), (int)(height*slot.getPosition().getY()), (int)(width*slot.getSize().getWidth()), (int)(height*slot.getSize().getHeight()));
        //g.setColor(slot.getColor());
        //g.drawRect((int)(width*slot.getPosition().getX()), (int)(height*slot.getPosition().getY()), (int)(width*slot.getSize().getWidth()), (int)(height*slot.getSize().getHeight()));
        //g.drawImage(this.imageScaled, 50, 50, null);
        g.drawImage(image, (int)(width*slot.getPosition().getX()), (int)(height*slot.getPosition().getY()), (int)(width*slot.getSize().getWidth()),
                (int)(height*slot.getSize().getHeight()), null);
        //g.drawImage(new ImageIcon(slot.getImage()).getImage().getScaledInstance((int)(width*slot.getSize().getWidth()),  (int)(height*slot.getSize().getHeight()), Image.SCALE_SMOOTH), (int)(width*slot.getPosition().getX()), (int)(height*slot.getPosition().getY()), null);
    }

    public void setSlot(ImageSlot slot) {
        this.slot = slot;
    }
}
