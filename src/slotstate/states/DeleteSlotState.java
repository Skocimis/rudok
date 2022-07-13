package slotstate.states;

import gui.SlideView;
import model.Project;
import model.Slide;
import model.Slot;
import notifications.SlotDeletedNotification;
import slotstate.SlotState;
import windows.frames.MainFrame;

import javax.swing.*;
import java.awt.*;

public class DeleteSlotState extends SlotState {
    public DeleteSlotState(){
        super();
        setMouseOverSlotCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    @Override
    public void mouseClicked(double x, double y) {
        SlideView slideView = MainFrame.getInstance().getProjectView().getOpenPresentationView().getjPanelSlidesView().getSelectedComponent();
        Slide slide = slideView.getSlide();
        Slot forDeletion = null;
        for (Slot slot:slide.getSlots()){
            if(slot.getPosition().getX()<=x && slot.getPosition().getY()<=y && slot.getPosition().getX()+slot.getSize().getWidth()>=x&&slot.getPosition().getY()+slot.getSize().getHeight()>=y){
                forDeletion = slot;
            }
        }
        if(forDeletion==null){
            return;
        }
        slide.getSlots().remove(forDeletion);

        Project project = (Project) MainFrame.getInstance().getProjectView().getOpenPresentationView().getPresentation().getParent();
        if(!project.isEdited()){
            project.setEdited(true);
            SwingUtilities.updateComponentTreeUI(MainFrame.getInstance());
        }
        slideView.getSlide().notifySelfAndParentSubscribers(new SlotDeletedNotification(forDeletion));
    }
}
