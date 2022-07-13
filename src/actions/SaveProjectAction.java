package actions;

import database.Database;
import gui.PresentationView;
import gui.ProjectView;
import model.Presentation;
import model.Project;
import windows.dialogs.PresentationSettingsDialog;
import windows.dialogs.SimpleDialog;
import windows.frames.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class SaveProjectAction extends AbstractRudokAction {
    public SaveProjectAction(){
        putValue(SMALL_ICON, loadIcon("images/saveProject.png"));
        putValue(NAME, "Save");
        putValue(SHORT_DESCRIPTION, "Save project");
    }

    public void actionPerformed(ActionEvent actionEvent) {
        ProjectView projectView = MainFrame.getInstance().getProjectView();
        System.out.println("Save");
        if(projectView.getProject()!=null){
            Project project = projectView.getProject();
            if(!project.isEdited()){
                return;
            }
            if(project.getFile()==null){
                MainFrame.getInstance().getActionManager().getSaveProjectAsAction().actionPerformed(actionEvent);
            }
            else{
                String projectLocation = project.getFile().getAbsolutePath();
                try {
                    FileOutputStream fileOut =
                            new FileOutputStream(projectLocation);
                    ObjectOutputStream out = new ObjectOutputStream(fileOut);
                    out.writeObject(project);
                    out.close();
                    fileOut.close();
                    project.setEdited(false);
                    Database.getInstance().getActiveWorkspace().setEdited(true);
                    SwingUtilities.updateComponentTreeUI(MainFrame.getInstance());
                } catch (IOException i) {
                    i.printStackTrace();
                }
                System.out.println("NOVI URL: "+projectLocation);
            }
        }
    }
}
