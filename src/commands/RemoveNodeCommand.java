package commands;

import model.Presentation;
import model.Project;
import model.Slide;
import model.rutree.RuNode;
import notifications.PublisherChangedNotification;
import windows.frames.MainFrame;

import javax.swing.*;

public class RemoveNodeCommand extends AbstractCommand{
    private RuNode removedNode;
    private int index;

    public RemoveNodeCommand(RuNode node){
        removedNode = node;
        index = node.getParent().getChildren().indexOf(node);
    }

    @Override
    public void doCommand() {
        if(removedNode instanceof Presentation){
            ((Project)removedNode.getParent()).setEdited(true);
        }
        if(removedNode instanceof Slide){
            ((Project)removedNode.getParent().getParent()).setEdited(true);
        }
        removedNode.getParent().removeChild(removedNode);
        removedNode.getParent().notifySubscribers(new PublisherChangedNotification(removedNode.getParent()));
        SwingUtilities.updateComponentTreeUI(MainFrame.getInstance());
    }

    @Override
    public void undoCommand() {
        //System.out.println(index);
        //System.out.println(removedNode);
        if(removedNode instanceof Presentation){
            ((Project)removedNode.getParent()).setEdited(true);
        }
        if(removedNode instanceof Slide){
            ((Project)removedNode.getParent().getParent()).setEdited(true);
        }
        removedNode.getParent().addChild(index, removedNode);
        removedNode.getParent().notifySubscribers(new PublisherChangedNotification(removedNode.getParent()));
        SwingUtilities.updateComponentTreeUI(MainFrame.getInstance());
    }
}
