package actions;

import commands.ChangeSharedPresentationsCommand;
import commands.RemoveNodeCommand;
import database.Database;
import gui.tree.model.MyTreeNode;
import model.Presentation;
import model.Project;
import model.Workspace;
import model.rutree.RuNode;
import notifications.PublisherChangedNotification;
import notifications.PublisherDeletedNotification;
import windows.frames.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

public class DeleteNodeAction extends AbstractRudokAction {
    public DeleteNodeAction(){
        putValue(SMALL_ICON, loadIcon("images/deleteNodeAction.png"));
        putValue(NAME, "Delete");
        putValue(SHORT_DESCRIPTION, "Delete node");
    }

    public void actionPerformed(ActionEvent actionEvent) {
        if(MainFrame.getInstance().getMyTreeSelectedValue()==null) return;
        MyTreeNode treeNode = (MyTreeNode) MainFrame.getInstance().getMyTreeSelectedValue();
        RuNode node = treeNode.getNode();
        if(node instanceof Workspace){
            MainFrame.getInstance().getUserErrorFactory().createUserError("DELETEWORKSPACE");
        }
        if(node.getParent()==null) return;
        if(node instanceof Project){
            Database.getInstance().getActiveWorkspace().setEdited(true);
            if(node==MainFrame.getInstance().getProjectView().getProject()){
                MainFrame.getInstance().getProjectView().getProject().notifySelfAndParentSubscribers(new PublisherDeletedNotification());
            }
        }
        if(node instanceof Presentation presentation && ((MyTreeNode)treeNode.getParent()).getNode()!=presentation.getParent()){
            List<Project> l = new ArrayList<>();
            l.add((Project) ((MyTreeNode)treeNode.getParent()).getNode());
            MainFrame.getInstance().getCommandManager().addCommand(new ChangeSharedPresentationsCommand(presentation, l));
        }
        else{
            MainFrame.getInstance().getCommandManager().addCommand(new RemoveNodeCommand(node));
        }
    }
}
