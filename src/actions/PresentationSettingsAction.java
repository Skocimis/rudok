package actions;

import database.Database;
import gui.PresentationView;
import gui.ProjectView;
import model.Presentation;
import model.Project;
import windows.dialogs.PresentationSettingsDialog;
import windows.dialogs.SimpleDialog;
import windows.frames.MainFrame;

import javax.swing.text.Style;
import java.awt.event.ActionEvent;

public class PresentationSettingsAction extends AbstractRudokAction {
    public PresentationSettingsAction(){
        putValue(SMALL_ICON, loadIcon("images/presentationSettings.png"));
        putValue(NAME, "Presentation settings");
        putValue(SHORT_DESCRIPTION, "Presentation settings");
    }

    public void actionPerformed(ActionEvent actionEvent) {
        ProjectView projectView = MainFrame.getInstance().getProjectView();
        if(projectView.getProject()!=null&&projectView.getjTabbedPane().getTabCount()>0){
            Presentation presentation = ((PresentationView)projectView.getjTabbedPane().getComponentAt(projectView.getjTabbedPane().getSelectedIndex())).getPresentation();
            SimpleDialog dialog = new PresentationSettingsDialog(presentation);
            dialog.setVisible(true);
        }
    }
}
