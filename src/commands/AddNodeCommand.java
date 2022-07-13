package commands;

import database.Database;
import model.Presentation;
import model.Project;
import model.Slide;
import model.rutree.RuNode;
import model.rutree.RuNodeC;
import notifications.PublisherChangedNotification;
import windows.frames.MainFrame;

import javax.swing.*;

public class AddNodeCommand extends AbstractCommand{
    private RuNode child;
    private RuNodeC parent;

    public AddNodeCommand(RuNodeC parent, RuNode child){
        this.parent = parent;
        this.child = child;
    }

    @Override
    public void doCommand() {
        if(child instanceof Project){
            ((Project)child).setEdited(true);
            Database.getInstance().getActiveWorkspace().setEdited(true);
        }
        if(child instanceof Presentation){
            ((Project)child.getParent()).setEdited(true);
        }
        if(child instanceof Slide){
            ((Project)child.getParent().getParent()).setEdited(true);
        }
        parent.addChild(child);
        parent.notifySubscribers(new PublisherChangedNotification(parent));
        SwingUtilities.updateComponentTreeUI(MainFrame.getInstance());
    }

    @Override
    public void undoCommand() {
        if(child instanceof Project){
            ((Project)child).setEdited(true);
            Database.getInstance().getActiveWorkspace().setEdited(true);
        }
        if(child instanceof Presentation){
            ((Project)child.getParent()).setEdited(true);
        }
        if(child instanceof Slide){
            ((Project)child.getParent().getParent()).setEdited(true);
        }
        parent.removeChild(child);
        parent.notifySubscribers(new PublisherChangedNotification(parent));
        SwingUtilities.updateComponentTreeUI(MainFrame.getInstance());
    }
}
