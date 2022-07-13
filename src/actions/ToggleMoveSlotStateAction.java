package actions;

import gui.SlideView;
import slotstate.SlotStateManager;
import windows.frames.MainFrame;

import java.awt.event.ActionEvent;

public class ToggleMoveSlotStateAction extends AbstractRudokAction {
    public ToggleMoveSlotStateAction(){
        putValue(SMALL_ICON, loadIcon("images/toggleMoveSlotStateAction.png"));
        putValue(NAME, "Toggle move slot");
        putValue(SHORT_DESCRIPTION, "Move slot in slide");
    }

    public void actionPerformed(ActionEvent actionEvent) {
        if(MainFrame.getInstance().getProjectView().getOpenPresentationView().getPresentation().getChildren().size()==0) return;
        SlideView slideView = MainFrame.getInstance().getProjectView().getOpenPresentationView().getjPanelSlidesView().getSelectedComponent();
        if(slideView==null) return;

        SlotStateManager slotStateManager = slideView.getSlotManipulationStateMouseAdapter().getSlotStateManager();
        if(slotStateManager.inMoveSlotState()) {
            slotStateManager.setDoNothingState();
        }
        else{
            slotStateManager.setMoveSlotState();
        }
    }
}
