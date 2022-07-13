package actions;

import gui.SlideView;
import slotstate.SlotStateManager;
import windows.frames.MainFrame;

import java.awt.event.ActionEvent;

public class ToggleDeleteSlotStateAction extends AbstractRudokAction {
    public ToggleDeleteSlotStateAction(){
        putValue(SMALL_ICON, loadIcon("images/toggleDeleteSlotStateAction.png"));
        putValue(NAME, "Toggle delete slot");
        putValue(SHORT_DESCRIPTION, "Delete slot from slide");
    }

    public void actionPerformed(ActionEvent actionEvent) {
        if(MainFrame.getInstance().getProjectView().getOpenPresentationView().getPresentation().getChildren().size()==0) return;

        SlideView slideView = MainFrame.getInstance().getProjectView().getOpenPresentationView().getjPanelSlidesView().getSelectedComponent();
        if(slideView==null) return;
        SlotStateManager slotStateManager = slideView.getSlotManipulationStateMouseAdapter().getSlotStateManager();
        if(slotStateManager.inDeleteSlotState()) {
            slotStateManager.setDoNothingState();
        }
        else{
            slotStateManager.setDeleteSlotState();
        }
    }
}
