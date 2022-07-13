package actions;

import gui.PresentationView;
import gui.SlideView;
import slotstate.SlotStateManager;
import windows.frames.MainFrame;

import java.awt.*;
import java.awt.event.ActionEvent;

public class ToggleAddSlotStateAction extends AbstractRudokAction {
    public ToggleAddSlotStateAction(){
        putValue(SMALL_ICON, loadIcon("images/toggleAddSlotStateAction.png"));
        putValue(NAME, "Add slot");
        putValue(SHORT_DESCRIPTION, "Add slot to slide");
    }

    public void actionPerformed(ActionEvent actionEvent) {
        if(MainFrame.getInstance().getProjectView().getOpenPresentationView().getPresentation().getChildren().size()==0) return;
        SlideView slideView = MainFrame.getInstance().getProjectView().getOpenPresentationView().getjPanelSlidesView().getSelectedComponent();
        if(slideView==null) return;

        SlotStateManager slotStateManager = slideView.getSlotManipulationStateMouseAdapter().getSlotStateManager();
        if(slotStateManager.inAddSlotState()) {
            slotStateManager.setDoNothingState();
        }
        else{
            slotStateManager.setAddSlotState();
        }
    }
}
