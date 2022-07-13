package commands;

import model.Presentation;
import model.Project;
import model.Slide;
import model.rutree.RuNode;
import notifications.PublisherChangedNotification;
import windows.frames.MainFrame;

import javax.swing.*;

public class RenameNodeCommand extends AbstractCommand{
    private RuNode renamedNode;
    private String oldName;
    private String newName;

    public RenameNodeCommand(RuNode node, String newName){
        renamedNode = node;
        oldName = node.getName();
        this.newName = newName;
    }

    @Override
    public void doCommand() {
        if(renamedNode instanceof Project){
            ((Project)renamedNode).setEdited(true);
        }
        if(renamedNode instanceof Presentation){
            ((Project)renamedNode.getParent()).setEdited(true);
        }
        if(renamedNode instanceof Slide){
            ((Project)renamedNode.getParent().getParent()).setEdited(true);
        }
        renamedNode.setName(newName);
        renamedNode.notifySubscribers(new PublisherChangedNotification(renamedNode));
        if(renamedNode.getParent()!=null){
            renamedNode.getParent().notifySubscribers(new PublisherChangedNotification(renamedNode.getParent()));
        }
        SwingUtilities.updateComponentTreeUI(MainFrame.getInstance());
    }

    @Override
    public void undoCommand() {
        if(renamedNode instanceof Project){
            ((Project)renamedNode).setEdited(true);
        }
        if(renamedNode instanceof Presentation){
            ((Project)renamedNode.getParent()).setEdited(true);
        }
        if(renamedNode instanceof Slide){
            ((Project)renamedNode.getParent().getParent()).setEdited(true);
        }
        renamedNode.setName(oldName);
        renamedNode.notifySubscribers(new PublisherChangedNotification(renamedNode));
        if(renamedNode.getParent()!=null){
            renamedNode.getParent().notifySubscribers(new PublisherChangedNotification(renamedNode.getParent()));
        }
        SwingUtilities.updateComponentTreeUI(MainFrame.getInstance());
    }
}
