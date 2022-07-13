package actions;

import gui.tree.model.MyTreeNode;
import main.Main;
import model.Presentation;
import model.Project;
import model.Slide;
import model.Workspace;
import model.rutree.RuNode;
import usererror.UserErrorFactory;
import windows.frames.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class NewPresentationAction extends AbstractRudokAction {
    public NewPresentationAction(){
        putValue(SMALL_ICON, loadIcon("images/new.png"));
        putValue(NAME, "New presentation");
        putValue(SHORT_DESCRIPTION, "New presentation");
    }

    public void actionPerformed(ActionEvent actionEvent) {
        Object selected = MainFrame.getInstance().getMyTreeSelectedValue();
        if(selected instanceof  MyTreeNode treeNode){
            RuNode node = treeNode.getNode();
            if(node instanceof Project project){
                project.addChild(new Presentation("New Presentation", project));
                SwingUtilities.updateComponentTreeUI(MainFrame.getInstance());
            }
            else if(node instanceof Presentation presentation){
                Project project = (Project) presentation.getParent();
                project.addChild(project.getChildren().indexOf(presentation) + 1, new Presentation("New Presentation", project));
                SwingUtilities.updateComponentTreeUI(MainFrame.getInstance());
            }
            else if(node instanceof Slide slide){
                Presentation presentation = (Presentation) slide.getParent();
                Project project = (Project) presentation.getParent();
                project.addChild(new Presentation("New Presentation", project));
                SwingUtilities.updateComponentTreeUI(MainFrame.getInstance());
            }
            else if(node instanceof Workspace){
                MainFrame.getInstance().getUserErrorFactory().createUserError("PRESENTATIONTOWORKSPACE");
            }
        }
        else {
            MainFrame.getInstance().getUserErrorFactory().createUserError("CHILDTONOTHING");
        }
    }
}
