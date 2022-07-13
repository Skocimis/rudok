package actions;

import database.Database;
import gui.PresentationView;
import gui.ProjectView;
import model.Presentation;
import model.Project;
import model.rutree.RuNode;
import windows.dialogs.PresentationSettingsDialog;
import windows.dialogs.SimpleDialog;
import windows.frames.MainFrame;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.event.ActionEvent;
import java.io.*;
import java.util.ArrayList;

public class OpenProjectAction extends AbstractRudokAction {
    public OpenProjectAction(){
        putValue(SMALL_ICON, loadIcon("images/openProject.png"));
        putValue(NAME, "Open");
        putValue(SHORT_DESCRIPTION, "Open project");
    }

    public void actionPerformed(ActionEvent actionEvent) {
        Project project;

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
        int result = fileChooser.showOpenDialog(null);
        if(result==JFileChooser.APPROVE_OPTION){
            try {
                File f = fileChooser.getSelectedFile();
                FileInputStream fileIn = new FileInputStream(f.getAbsolutePath());
                ObjectInputStream in = new ObjectInputStream(fileIn);
                project = (Project) in.readObject();
                project.setFile(f);
                project.setEdited(false);
                in.close();
                fileIn.close();
                for(RuNode ruNode:project.getChildren()){
                    if(ruNode instanceof Presentation presentation){
                        presentation.setProjects(new ArrayList<>());
                        presentation.addProject(project);
                    }
                }
                project.setParent(Database.getInstance().getActiveWorkspace());
                Database.getInstance().getActiveWorkspace().addChild(project);
                Database.getInstance().getActiveWorkspace().setEdited(true);

                SwingUtilities.updateComponentTreeUI(MainFrame.getInstance());
                System.out.println("JUHU");
                System.out.println(project);
            } catch (IOException i) {
                i.printStackTrace();
            } catch (ClassNotFoundException c) {
                System.out.println("Klasa ne postoji");
                c.printStackTrace();
            }
        }


    }
}
