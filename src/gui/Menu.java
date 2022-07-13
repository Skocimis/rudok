package gui;

import windows.frames.MainFrame;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class Menu extends JMenuBar {
    public Menu(){
        super();

        /*TODO
            JMenu mnuFile = new JMenu("File");
            mnuFile.setMnemonic(KeyEvent.VK_F);
            Ovde stoji ucitavanje i cuvanje podataka
            add(mnuFile);*/
        JMenu mnuEdit = new JMenu("Edit");
        mnuEdit.setMnemonic(KeyEvent.VK_E);
        mnuEdit.add(MainFrame.getInstance().getActionManager().getRenameNodeAction());
        mnuEdit.add(MainFrame.getInstance().getActionManager().getDeleteNodeAction());
        add(mnuEdit);

        JMenu mnuWorkspace = new JMenu("Workspace");
        mnuWorkspace.add(MainFrame.getInstance().getActionManager().getOpenWorkspaceAction());
        mnuWorkspace.add(MainFrame.getInstance().getActionManager().getSaveWorkspaceAction());
        mnuWorkspace.add(MainFrame.getInstance().getActionManager().getSaveWorkspaceAsAction());
        add(mnuWorkspace);

        JMenu mnuProject = new JMenu("Project");
        mnuProject.add(MainFrame.getInstance().getActionManager().getOpenProjectAction());
        mnuProject.add(MainFrame.getInstance().getActionManager().getSaveProjectAction());
        mnuProject.add(MainFrame.getInstance().getActionManager().getSaveProjectAsAction());
        mnuProject.add(MainFrame.getInstance().getActionManager().getImportPresentationAction());
        add(mnuProject);

        JMenu mnuPresentation = new JMenu("Presentation");
        mnuPresentation.add(MainFrame.getInstance().getActionManager().getPresentationSettingsAction());
        mnuPresentation.add(MainFrame.getInstance().getActionManager().getSharePresentationAction());
        mnuPresentation.add(MainFrame.getInstance().getActionManager().getExportPresentationAction());
        add(mnuPresentation);

        JMenu mnuHelp = new JMenu("Help");
        mnuHelp.add(MainFrame.getInstance().getActionManager().getInfoAction());
        add(mnuHelp);




    }
}
