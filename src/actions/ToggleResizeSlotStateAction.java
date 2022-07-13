package actions;

import gui.SlideView;
import slotstate.SlotStateManager;
import windows.frames.MainFrame;

import java.awt.event.ActionEvent;

public class ToggleResizeSlotStateAction extends AbstractRudokAction {
    public ToggleResizeSlotStateAction(){
        putValue(SMALL_ICON, loadIcon("images/toggleResizeSlotStateAction.png"));
        putValue(NAME, "Resize slot");
        putValue(SHORT_DESCRIPTION, "Resize slot");
    }

    public void actionPerformed(ActionEvent actionEvent) {
        if(MainFrame.getInstance().getProjectView().getOpenPresentationView().getPresentation().getChildren().size()==0) return;
        SlideView slideView = MainFrame.getInstance().getProjectView().getOpenPresentationView().getjPanelSlidesView().getSelectedComponent();
        if(slideView==null) return;
        SlotStateManager slotStateManager = slideView.getSlotManipulationStateMouseAdapter().getSlotStateManager();
        if(slotStateManager.inResizeSlotState()) {
            slotStateManager.setDoNothingState();
        }
        else{
            slotStateManager.setResizeSlotState();
        }
    }
}
