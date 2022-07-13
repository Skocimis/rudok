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

public class SaveWorkspaceAsAction extends AbstractRudokAction {
    public SaveWorkspaceAsAction(){
        putValue(SMALL_ICON, loadIcon("images/saveProject.png"));
        putValue(NAME, "Save as");
        putValue(SHORT_DESCRIPTION, "Save workspaceAs");
    }

    public void actionPerformed(ActionEvent actionEvent) {
        Workspace workspace = Database.getInstance().getActiveWorkspace();

        StringBuilder stringBuilder = new StringBuilder();
        for(RuNode ruNode:workspace.getChildren()) {
            if (ruNode instanceof Project project) {
                if(project.getFile()==null){
                    MainFrame.getInstance().getUserErrorFactory().createUserError("UNSAVEDPROJECTS", project.getName());

                }
                else{
                    System.out.println("REC");
                    stringBuilder.append(project.getFile().getAbsolutePath()).append("\n");
                }
            }
        }
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
        String workspaceLocation;
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            workspaceLocation = selectedFile.getAbsolutePath();
            Database.getInstance().getActiveWorkspace().setFile(selectedFile);
            if(!workspaceLocation.endsWith(".txt")){
                workspaceLocation=workspaceLocation+".txt";
            }
            try {
                FileOutputStream fileOut =
                        new FileOutputStream(workspaceLocation);
                fileOut.write(stringBuilder.toString().getBytes(StandardCharsets.UTF_8));
                fileOut.flush();
                Database.getInstance().getActiveWorkspace().setEdited(false);
                SwingUtilities.updateComponentTreeUI(MainFrame.getInstance());
            } catch (IOException i) {
                i.printStackTrace();
            }

            System.out.println("NOVI URL: "+workspaceLocation);
    }


    }
}
