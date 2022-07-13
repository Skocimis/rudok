package slotstate.states;

import gui.SlideView;
import gui.SlotView;
import model.Project;
import model.Slide;
import model.Slot;
import notifications.SlotChangedNotification;
import notifications.SlotSelectedNotification;
import notifications.SlotUnselectedNotification;
import slotstate.SlotState;
import vector.Point2D;
import windows.frames.MainFrame;

import javax.swing.*;
import java.awt.*;

public class MoveSlotState extends SlotState {
    public MoveSlotState(){
        super();
        setMouseOverSlotCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
    }
    private double proslox = 0,prosloy = 0;
    @Override
    public void mousePressed(double x, double y) {
        SlideView slideView = MainFrame.getInstance().getProjectView().getOpenPresentationView().getjPanelSlidesView().getSelectedComponent();
        Slide slide = slideView.getSlide();
        Slot forSelection = null;
        for (Slot slot:slide.getSlots()){
            if(slot.getPosition().getX()<=x && slot.getPosition().getY()<=y && slot.getPosition().getX()+slot.getSize().getWidth()>=x&&slot.getPosition().getY()+slot.getSize().getHeight()>=y){
                forSelection = slot;
            }
        }
        if(forSelection==null){
            return;
        }
        proslox = x;
        prosloy = y;
        slideView.getSlide().notifySelfAndParentSubscribers(new SlotSelectedNotification(forSelection));
    }

    @Override
    public void mouseReleased(double x, double y) {
        SlideView slideView = MainFrame.getInstance().getProjectView().getOpenPresentationView().getjPanelSlidesView().getSelectedComponent();
        SlotView selectedSlotView = slideView.getSelectedSlotView();
        if(selectedSlotView==null) return;
        slideView.getSlide().notifySelfAndParentSubscribers(new SlotUnselectedNotification(selectedSlotView.getSlot()));
        proslox = 0;
        prosloy = 0;

        Project project = (Project) MainFrame.getInstance().getProjectView().getOpenPresentationView().getPresentation().getParent();
        if(!project.isEdited()){
            project.setEdited(true);
            SwingUtilities.updateComponentTreeUI(MainFrame.getInstance());
        }
    }

    @Override
    public void mouseDragged(double x, double y) {
        SlideView slideView = MainFrame.getInstance().getProjectView().getOpenPresentationView().getjPanelSlidesView().getSelectedComponent();
        SlotView selectedSlotView = slideView.getSelectedSlotView();
        if(selectedSlotView==null) return;
        Slot changedSlot = selectedSlotView.getSlot();
        changedSlot.setPosition(new Point2D(changedSlot.getPosition().getX()+x-proslox, changedSlot.getPosition().getY()+y-prosloy));
        slideView.getSlide().notifySelfAndParentSubscribers(new SlotChangedNotification(changedSlot));
        proslox = x;
        prosloy = y;
    }
}
