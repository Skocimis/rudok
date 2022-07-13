package gui;

import components.ImagePanel;
import gui.slots.EmptySlotEditorView;
import gui.slots.ImageSlotEditorView;
import gui.slots.TextSlotEditorView;
import model.Presentation;
import model.Slide;
import model.Slot;
import model.slots.EmptySlot;
import model.slots.ImageSlot;
import model.slots.TextSlot;
import notifications.*;
import observer.ISubscriber;
import slotstate.SlotManipulationStateMouseAdapter;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class SlideView extends JPanel implements ISubscriber {
    private Slide slide;
    private ImagePanel imagePanel;
    private List<SlotView> slotViews;
    private SlotView selectedSlotView;
    private SlotManipulationStateMouseAdapter slotManipulationStateMouseAdapter;
    SlidePreview preview;

    public SlideView(SlidePreview preview){
        super();
        this.preview = preview;
        slotManipulationStateMouseAdapter = new SlotManipulationStateMouseAdapter();
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
                imagePanel.addMouseListener(slotManipulationStateMouseAdapter);
                imagePanel.addMouseMotionListener(slotManipulationStateMouseAdapter);
                add(imagePanel);

                slotViews = new ArrayList<>();
                for(Slot slot:slide.getSlots()){
                    if(slot instanceof EmptySlot emptySlot){
                        SlotView slotView = new EmptySlotEditorView(emptySlot);
                        slotViews.add(slotView);
                    }
                    else if(slot instanceof TextSlot textSlot){
                        SlotView slotView = new TextSlotEditorView(textSlot);
                        slotViews.add(slotView);
                    }
                    else if(slot instanceof ImageSlot imageSlot){
                        SlotView slotView = new ImageSlotEditorView(imageSlot);
                        slotViews.add(slotView);
                    }
                }
                selectedSlotView = null;
                System.out.println("REPAINTTTTTTT");
                repaint();
                revalidate();
            }
        }
        if(notification instanceof SlotAddedNotification slotAddedNotification){
            if(slotAddedNotification.getSlot() instanceof EmptySlot emptySlot){
                SlotView slotView = new EmptySlotEditorView(emptySlot);
                slotViews.add(slotView);
            }
            else if(slotAddedNotification.getSlot() instanceof TextSlot textSlot){
                SlotView slotView = new TextSlotEditorView(textSlot);
                slotViews.add(slotView);
            }
            else if(slotAddedNotification.getSlot() instanceof ImageSlot imageSlot){
                SlotView slotView = new ImageSlotEditorView(imageSlot);
                slotViews.add(slotView);
            }
            this.repaint();
        }
        if(notification instanceof SlotChangedNotification slotMovedNotification){
            this.repaint();
        }
        if(notification instanceof SlotDeletedNotification slotDeletedNotification){
            slotViews.removeIf(e->e.getSlot()==slotDeletedNotification.getSlot());
            this.repaint();
        }
        if(notification instanceof SlotSelectedNotification slotSelectedNotification){
            for(SlotView slotView:slotViews){
                if(slotSelectedNotification.getSlot()==slotView.getSlot()){
                    selectedSlotView = slotView;
                    return;
                }
            }
        }
        if(notification instanceof SlotUnselectedNotification slotUnselectedNotification){
            selectedSlotView = null;
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        for(SlotView slotView:slotViews){
            System.out.println("PAINTUJEM SV");
            slotView.paint(g);
        }
    }

    public Slide getSlide() {
        return slide;
    }

    public void setSlide(Slide slide) {
        this.slide = slide;
    }

    public SlotManipulationStateMouseAdapter getSlotManipulationStateMouseAdapter() {
        return slotManipulationStateMouseAdapter;
    }

    public void setSlotManipulationStateMouseAdapter(SlotManipulationStateMouseAdapter slotManipulationStateMouseAdapter) {
        this.slotManipulationStateMouseAdapter = slotManipulationStateMouseAdapter;
    }

    public SlotView getSelectedSlotView() {
        return selectedSlotView;
    }

    public void setSelectedSlotView(SlotView active) {
        this.selectedSlotView = active;
    }
}