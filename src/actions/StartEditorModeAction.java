package actions;

import gui.PresentationView;
import windows.frames.MainFrame;

import java.awt.event.ActionEvent;

public class StartEditorModeAction extends AbstractRudokAction {
    public StartEditorModeAction(){
        putValue(SMALL_ICON, loadIcon("images/startEditorMode.png"));
        putValue(NAME, "Edit presentation");
        putValue(SHORT_DESCRIPTION, "Start presentation editor");
    }

    public void actionPerformed(ActionEvent actionEvent) {
        PresentationView presentationView = MainFrame.getInstance().getProjectView().getOpenPresentationView();
        if(presentationView!=null)
            presentationView.startEditorMode();
    }
}
