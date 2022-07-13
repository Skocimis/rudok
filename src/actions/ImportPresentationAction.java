package actions;

import database.Database;
import gui.ProjectView;
import model.Presentation;
import model.Project;
import model.rutree.RuNode;
import notifications.PublisherChangedNotification;
import windows.frames.MainFrame;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.event.ActionEvent;
import java.io.*;
import java.util.ArrayList;

public class ImportPresentationAction extends AbstractRudokAction {
    public ImportPresentationAction(){
        putValue(SMALL_ICON, loadIcon("images/importPresentation.png"));
        putValue(NAME, "Import presentation");
        putValue(SHORT_DESCRIPTION, "Import presentation to project");
    }

    public void actionPerformed(ActionEvent actionEvent) {
        ProjectView projectView = MainFrame.getInstance().getProjectView();
        Project project = projectView.getProject();
        Presentation presentation;
        System.out.println("Save as");
        if(project!=null){
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
            int result = fileChooser.showOpenDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                try {
                    File f = fileChooser.getSelectedFile();
                    FileInputStream fileIn = new FileInputStream(f.getAbsolutePath());
                    ObjectInputStream in = new ObjectInputStream(fileIn);
                    presentation = (Presentation) in.readObject();
                    presentation.setProjects(new ArrayList<>());
                    presentation.addProject(project);
                    presentation.setParent(project);
                    in.close();
                    fileIn.close();
                    project.addChild(presentation);
                    project.setEdited(true);
                    projectView.update(new PublisherChangedNotification(project));

                    SwingUtilities.updateComponentTreeUI(MainFrame.getInstance());
                } catch (IOException i) {
                    i.printStackTrace();
                } catch (ClassNotFoundException c) {
                    System.out.println("Klasa ne postoji");
                    c.printStackTrace();
                }
            }
        }
    }
}
