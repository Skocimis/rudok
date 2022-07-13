package actions;

import gui.PresentationView;
import gui.tree.model.MyTreeNode;
import model.rutree.RuNode;
import windows.dialogs.RenameNodeDialog;
import windows.dialogs.SimpleDialog;
import windows.frames.MainFrame;

import java.awt.event.ActionEvent;

public class StartSlideshowModeAction extends AbstractRudokAction {
    public StartSlideshowModeAction(){
        putValue(SMALL_ICON, loadIcon("images/startSlideshowMode.png"));
        putValue(NAME, "Slideshow");
        putValue(SHORT_DESCRIPTION, "Start slide show");
    }

    public void actionPerformed(ActionEvent actionEvent) {
        PresentationView presentationView = MainFrame.getInstance().getProjectView().getOpenPresentationView();
        presentationView.startSlideShowMode();
    }
}
