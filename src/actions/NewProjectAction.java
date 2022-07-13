package actions;

import gui.tree.model.MyTreeNode;
import model.Presentation;
import model.Project;
import model.Slide;
import model.Workspace;
import model.rutree.RuNode;
import usererror.UserErrorFactory;
import windows.frames.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class NewProjectAction extends AbstractRudokAction {
    public NewProjectAction(){
        putValue(SMALL_ICON, loadIcon("images/new.png"));
        putValue(NAME, "New project");
        putValue(SHORT_DESCRIPTION, "New project");
    }

    public void actionPerformed(ActionEvent actionEvent) {
        Object selected = MainFrame.getInstance().getMyTreeSelectedValue();
        if(selected instanceof  MyTreeNode treeNode){
            RuNode node = treeNode.getNode();
            if(node instanceof Workspace workspace){
                workspace.addChild(new Project("New Project", workspace));
                SwingUtilities.updateComponentTreeUI(MainFrame.getInstance());
            }
            else if(node instanceof Project project){
                Workspace workspace = (Workspace) project.getParent();
                workspace.addChild(workspace.getChildren().indexOf(project) + 1, new Project("New Project", workspace));
                SwingUtilities.updateComponentTreeUI(MainFrame.getInstance());
            }
            else if(node instanceof Presentation presentation){
                    Project project = (Project) presentation.getParent();
                    Workspace workspace = (Workspace) project.getParent();
                    workspace.addChild(new Project("New Project", workspace));
                    SwingUtilities.updateComponentTreeUI(MainFrame.getInstance());
            }
            else  if(node instanceof Slide slide){
                Presentation presentation = (Presentation)slide.getParent();
                Project project = (Project) presentation.getParent();
                Workspace workspace = (Workspace) project.getParent();
                workspace.addChild(new Project("New Project", workspace));
                SwingUtilities.updateComponentTreeUI(MainFrame.getInstance());

            }
        }
        else {
            MainFrame.getInstance().getUserErrorFactory().createUserError("CHILDTONOTHING");
        }
    }
}
