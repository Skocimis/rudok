package slotstate.states;

import gui.SlideView;
import model.Presentation;
import model.Project;
import model.Slide;
import model.Slot;
import model.slots.EmptySlot;
import notifications.SlotAddedNotification;
import slotstate.SlotState;
import vector.Dimension2D;
import vector.Point2D;
import windows.frames.MainFrame;

import javax.swing.*;
import java.awt.*;

public class AddSlotState extends SlotState {
    public AddSlotState(){
        super();
        setMouseOverSlotCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }

    @Override
    public void mouseClicked(double x, double y) {
        Slot slot = new EmptySlot(new Point2D(x, y), new Dimension2D(0.1, 0.1), MainFrame.getInstance().getSlotDefaultColor(), MainFrame.getInstance().getSlotDefaultBackgroundColor(), MainFrame.getInstance().getSlotDefaultStroke());

        SlideView slideView = MainFrame.getInstance().getProjectView().getOpenPresentationView().getjPanelSlidesView().getSelectedComponent();
        slideView.getSlide().addSlot(slot);
        Project project = (Project) MainFrame.getInstance().getProjectView().getOpenPresentationView().getPresentation().getParent();
        if(!project.isEdited()){
            project.setEdited(true);
            SwingUtilities.updateComponentTreeUI(MainFrame.getInstance());
        }
        slideView.getSlide().notifySelfAndParentSubscribers(new SlotAddedNotification(slot));
    }


}
