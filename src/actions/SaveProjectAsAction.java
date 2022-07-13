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
import javax.swing.filechooser.FileFilter;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class SaveProjectAsAction extends AbstractRudokAction {
    public SaveProjectAsAction(){
        putValue(SMALL_ICON, loadIcon("images/saveProject.png"));
        putValue(NAME, "Save as");
        putValue(SHORT_DESCRIPTION, "Save project as");
    }

    public void actionPerformed(ActionEvent actionEvent) {
        ProjectView projectView = MainFrame.getInstance().getProjectView();
        System.out.println("Save as");
        String projectLocation;
        if(projectView.getProject()!=null){
            Project project = projectView.getProject();
            if(project==null) return;
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileFilter(new FileFilter() {

                public String getDescription() {
                    return "Rudok Project (*.rud)";
                }

                public boolean accept(File f) {
                    if (f.isDirectory()) {
                        return true;
                    } else {
                        String filename = f.getName().toLowerCase();
                        return filename.endsWith(".rud");
                    }
                }
            });
            fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")+ "/Desktop"));
            int result = fileChooser.showSaveDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();

                project.setFile(selectedFile);
                projectLocation = selectedFile.getAbsolutePath();
                if(!projectLocation.endsWith(".rud")){
                    projectLocation=projectLocation+".rud";
                    project.setFile(new File(projectLocation));
                }
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
