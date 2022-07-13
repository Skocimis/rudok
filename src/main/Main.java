package main;

import database.Database;
import model.Presentation;
import model.Project;
import model.Workspace;
import model.rutree.RuNode;
import notifications.PublisherDeletedNotification;
import windows.frames.MainFrame;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.xml.crypto.Data;
import java.io.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Object[] options = {"Yes",
                "No"};
        int n = JOptionPane.showOptionDialog(MainFrame.getInstance(),
                "Would you like to open an existing workspace? ",
                "Open workspace",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[1]);
        if(n==0){
            Project project;
            Workspace workspace = Database.getInstance().getActiveWorkspace();
            workspace.getChildren().clear();

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
            int result = fileChooser.showOpenDialog(null);
            if(result==JFileChooser.APPROVE_OPTION){
                try
                {
                    File file=fileChooser.getSelectedFile();
                    Database.getInstance().getActiveWorkspace().setFile(file);
                    FileReader fr=new FileReader(file);
                    BufferedReader br=new BufferedReader(fr);
                    String line;
                    while((line=br.readLine())!=null)
                    {
                        System.out.println(line);

                        try{
                            File f = new File(line);
                            FileInputStream fileIn = new FileInputStream(line);
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

                        }
                        catch (Exception e){
                            System.out.println("ERROR");
                        }
                    }
                    Database.getInstance().getActiveWorkspace().setEdited(false);
                    SwingUtilities.updateComponentTreeUI(MainFrame.getInstance());
                    MainFrame.getInstance().getProjectView().update(new PublisherDeletedNotification());
                    fr.close();
                    //System.out.println("Contents of File: ");
                    //System.out.println(sb.toString());
                }
                catch(IOException e)
                {
                    e.printStackTrace();
                }
            }

        }
        MainFrame.getInstance().setVisible(true);
    }
}
