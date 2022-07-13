package actions;

import gui.PresentationView;
import gui.ProjectView;
import model.Presentation;
import windows.dialogs.PresentationSettingsDialog;
import windows.dialogs.SharePresentationDialog;
import windows.dialogs.SimpleDialog;
import windows.frames.MainFrame;

import java.awt.event.ActionEvent;

public class SharePresentationAction extends AbstractRudokAction {
    public SharePresentationAction(){
        putValue(SMALL_ICON, loadIcon("images/sharePresentation.png"));
        putValue(NAME, "Share presentation");
        putValue(SHORT_DESCRIPTION, "Share presentation to other project");
    }

    public void actionPerformed(ActionEvent actionEvent) {
        ProjectView projectView = MainFrame.getInstance().getProjectView();
        if(projectView.getProject()!=null&&projectView.getjTabbedPane().getTabCount()>0){
            Presentation presentation = ((PresentationView)projectView.getjTabbedPane().getComponentAt(projectView.getjTabbedPane().getSelectedIndex())).getPresentation();
            SimpleDialog dialog = new SharePresentationDialog(presentation);
            dialog.setVisible(true);
        }
    }
}
