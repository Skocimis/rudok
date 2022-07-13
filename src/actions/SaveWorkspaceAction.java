package actions;

import database.Database;
import model.Presentation;
import model.Project;
import model.Workspace;
import model.rutree.RuNode;
import windows.frames.MainFrame;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.event.ActionEvent;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class SaveWorkspaceAction extends AbstractRudokAction {
    public SaveWorkspaceAction() {
        putValue(SMALL_ICON, loadIcon("images/saveProject.png"));
        putValue(NAME, "Save");
        putValue(SHORT_DESCRIPTION, "Save workspace");
    }

    public void actionPerformed(ActionEvent actionEvent) {
        Workspace workspace = Database.getInstance().getActiveWorkspace();
        String workspaceLocation;

        StringBuilder stringBuilder = new StringBuilder();
        for (RuNode ruNode : workspace.getChildren()) {
            if (ruNode instanceof Project project) {
                if(project.getFile()==null){
                    MainFrame.getInstance().getUserErrorFactory().createUserError("UNSAVEDPROJECTS", project.getName());
                }
                else{
                    System.out.println("REC");
                    stringBuilder.append(project.getFile().getAbsolutePath()+"\n");
                }
            }
        }
        if(workspace.getFile()==null){
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileFilter(new FileFilter() {

                public String getDescription() {
                    return "RuDok Workspace (*.txt)";
                }

                public boolean accept(File f) {
                    if (f.isDirectory()) {
                        return true;
                    } else {
                        String filename = f.getName().toLowerCase();
                        return filename.endsWith(".txt");
                    }
                }
            });
            fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")+ "/Desktop"));
            int result = fileChooser.showSaveDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                workspace.setFile(selectedFile);
                workspaceLocation = selectedFile.getAbsolutePath();
            }
            else{
                return;
            }
        }
        else {
            File selectedFile = workspace.getFile();
            workspaceLocation = selectedFile.getAbsolutePath();
        }
        /*try {
            File myObj = new File(workspaceLocation);
            System.out.println("AP");
            System.out.println(myObj.getParent());
            myObj.getParentFile().mkdir();
            myObj.createNewFile();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }*/
        try {
            FileOutputStream fileOut =
                    new FileOutputStream(workspaceLocation);
            Database.getInstance().getActiveWorkspace().setEdited(false);
            SwingUtilities.updateComponentTreeUI(MainFrame.getInstance());
            System.out.println(stringBuilder.toString());
            fileOut.write(stringBuilder.toString().getBytes(StandardCharsets.UTF_8));
            fileOut.flush();
        } catch (IOException i) {
            i.printStackTrace();
        }
        System.out.println("NOVI URL: " + workspaceLocation);


    }
}
