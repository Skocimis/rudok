package actions;

import database.Database;
import gui.ProjectView;
import model.Presentation;
import model.Project;
import windows.frames.MainFrame;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class ExportPresentationAction extends AbstractRudokAction {
    public ExportPresentationAction(){
        putValue(SMALL_ICON, loadIcon("images/exportPresentation.png"));
        putValue(NAME, "Export");
        putValue(SHORT_DESCRIPTION, "Export presentation to file");
    }

    public void actionPerformed(ActionEvent actionEvent) {
        ProjectView projectView = MainFrame.getInstance().getProjectView();
        System.out.println("Save as");
        if(projectView.getProject()!=null){
            Project project = projectView.getProject();
            Presentation presentation = null;
            if(projectView.getOpenPresentationView()!=null)
                presentation = projectView.getOpenPresentationView().getPresentation();
            if(presentation==null) return;

            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileFilter(new FileFilter() {

                public String getDescription() {
                    return "Rudok Presentation (*.rudp)";
                }

                public boolean accept(File f) {
                    if (f.isDirectory()) {
                        return true;
                    } else {
                        String filename = f.getName().toLowerCase();
                        return filename.endsWith(".rudp");
                    }
                }
            });
            fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")+ "/Desktop"));
            int result = fileChooser.showSaveDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                String exportLocation;
                exportLocation = selectedFile.getAbsolutePath();
                if(!exportLocation.endsWith(".rudp")){
                    exportLocation=exportLocation+".rudp";
                }
                try {
                    FileOutputStream fileOut =
                            new FileOutputStream(exportLocation);
                    ObjectOutputStream out = new ObjectOutputStream(fileOut);
                    out.writeObject(presentation);
                    out.close();
                    fileOut.close();
                } catch (IOException i) {
                    i.printStackTrace();
                }
                System.out.println("NOVI URL: "+exportLocation);
            }
        }
    }
}
