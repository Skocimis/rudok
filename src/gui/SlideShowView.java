package gui;

import components.ImagePanel;
import gui.slots.EmptySlotPreviewView;
import gui.slots.EmptySlotSlideshowView;
import gui.slots.ImageSlotSlideshowView;
import gui.slots.TextSlotSlideshowView;
import model.Presentation;
import model.Slide;
import model.Slot;
import model.slots.EmptySlot;
import model.slots.ImageSlot;
import model.slots.TextSlot;
import notifications.MyNotification;
import notifications.PublisherChangedNotification;
import notifications.PublisherDeletedNotification;
import notifications.SlotNotification;
import observer.ISubscriber;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class SlideShowView extends JPanel implements ISubscriber {
    private Slide slide;
    private ImagePanel imagePanel;
    private List<SlotView> slotViews;

    public SlideShowView(){
        super();
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        setAlignmentX(JComponent.CENTER_ALIGNMENT);
        setAlignmentY(JComponent.CENTER_ALIGNMENT);
        imagePanel = new ImagePanel("images/prazno.png");
        add(imagePanel);
    }

    @Override
    public void update(Object notification) {
        if(notification instanceof PublisherChangedNotification publisherChangedNotification){
            if(publisherChangedNotification.getNewPublisher() instanceof Slide){
                slide = (Slide) publisherChangedNotification.getNewPublisher();
                remove(imagePanel);
                imagePanel = new ImagePanel(((Presentation)slide.getParent()).getTheme());
                add(imagePanel);

                slotViews = new ArrayList<>();
                for(Slot slot:slide.getSlots()){
                    if(slot instanceof EmptySlot emptySlot){
                        SlotView slotView = new EmptySlotSlideshowView(emptySlot);
                        slotViews.add(slotView);
                    }
                    else if(slot instanceof TextSlot textSlot){
                        SlotView slotView = new TextSlotSlideshowView(textSlot);
                        slotViews.add(slotView);
                    }
                    else if(slot instanceof ImageSlot imageSlot){
                        SlotView slotView = new ImageSlotSlideshowView(imageSlot);
                        slotViews.add(slotView);
                    }
                }
                repaint();
                revalidate();
            }
        }
        if(notification instanceof SlotNotification){
            remove(imagePanel);
            imagePanel = new ImagePanel(((Presentation)slide.getParent()).getTheme());
            add(imagePanel);

            slotViews = new ArrayList<>();
            for(Slot slot:slide.getSlots()){
                if(slot instanceof EmptySlot emptySlot){
                    SlotView slotView = new EmptySlotSlideshowView(emptySlot);
                    slotViews.add(slotView);
                }
                else if(slot instanceof TextSlot textSlot){
                    SlotView slotView = new TextSlotSlideshowView(textSlot);
                    slotViews.add(slotView);
                }
                else if(slot instanceof ImageSlot imageSlot){
                    SlotView slotView = new ImageSlotSlideshowView(imageSlot);
                    slotViews.add(slotView);
                }
            }
            repaint();
            revalidate();
        }
    }


    @Override
    public Dimension getPreferredSize() {
        return new Dimension(100, 60);
    }

    public Slide getSlide() {
        return slide;
    }

    public void setSlide(Slide slide) {
        this.slide = slide;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        for(SlotView slotView:slotViews){
            slotView.paint(g);
        }
    }
}
