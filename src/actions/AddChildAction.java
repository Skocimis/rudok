package actions;

import commands.AddNodeCommand;
import gui.tree.model.MyTreeNode;
import model.Presentation;
import model.Project;
import model.Slide;
import model.Workspace;
import model.nodefactory.AbstractNodeFactory;
import model.nodefactory.PresentationFactory;
import model.nodefactory.ProjectFactory;
import model.nodefactory.SlideFactory;
import model.rutree.RuNode;
import model.rutree.RuNodeC;
import notifications.PublisherChangedNotification;
import windows.frames.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class AddChildAction extends AbstractRudokAction{
    private ProjectFactory projectFactory;
    private PresentationFactory presentationFactory;
    private SlideFactory slideFactory;
    public AddChildAction(){
        projectFactory = new ProjectFactory();
        presentationFactory = new PresentationFactory();
        slideFactory = new SlideFactory();
        putValue(SMALL_ICON, loadIcon("images/addChildAction.png"));
        putValue(NAME, "Add child");
        putValue(SHORT_DESCRIPTION, "Add child to node");
    }
    private AbstractNodeFactory getFactory(RuNodeC parent){
        if(parent instanceof Workspace){
            return projectFactory;
        }
        else if(parent instanceof Project){
            return presentationFactory;
        }
        else if(parent instanceof Presentation){
            return slideFactory;
        }
        return null;
    }
    public void actionPerformed(ActionEvent actionEvent) {
        Object selected = MainFrame.getInstance().getMyTreeSelectedValue();
        if(selected instanceof MyTreeNode treeNode){
            if(treeNode.getNode() instanceof RuNodeC ruNodeC){
                MainFrame.getInstance().getCommandManager().addCommand(new AddNodeCommand(ruNodeC, getFactory(ruNodeC).getNodeForTree(ruNodeC)));
            }
        }
    }

}
