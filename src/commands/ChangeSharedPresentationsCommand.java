package commands;

import model.Presentation;
import model.Project;
import model.rutree.RuNode;
import notifications.PublisherChangedNotification;
import windows.frames.MainFrame;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class ChangeSharedPresentationsCommand extends AbstractCommand{
    private Presentation presentation;
    private List<Project> changedSharing;

    public ChangeSharedPresentationsCommand(Presentation presentation, List<Project> changedSharing){
        this.presentation = presentation;
        this.changedSharing = changedSharing;
    }

    private void changeSharingState(Presentation presentation, Project project){
        if(presentation.getProjects().contains(project)){
            presentation.removeProject(project);
        }
        else {
            presentation.addProject(project);
        }
    }

    @Override
    public void doCommand() {
        for(Project project:changedSharing) {
            changeSharingState(presentation, project);
        }
        MainFrame.getInstance().getProjectView().update(new PublisherChangedNotification(MainFrame.getInstance().getProjectView().getProject()));
        SwingUtilities.updateComponentTreeUI(MainFrame.getInstance());
    }

    @Override
    public void undoCommand() {
        for(Project project:changedSharing) {
            changeSharingState(presentation, project);
        }
        MainFrame.getInstance().getProjectView().update(new PublisherChangedNotification(MainFrame.getInstance().getProjectView().getProject()));
        SwingUtilities.updateComponentTreeUI(MainFrame.getInstance());
    }
}
