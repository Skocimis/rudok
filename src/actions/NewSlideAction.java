package actions;

import gui.tree.model.MyTreeNode;
import model.Presentation;
import model.Project;
import model.Slide;
import model.Workspace;
import model.rutree.RuNode;
import notifications.PublisherChangedNotification;
import windows.frames.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class NewSlideAction extends AbstractRudokAction {
    public NewSlideAction(){
        putValue(SMALL_ICON, loadIcon("images/new.png"));
        putValue(NAME, "New slide");
        putValue(SHORT_DESCRIPTION, "New slide");
    }

    public void actionPerformed(ActionEvent actionEvent) {
        Object selected = MainFrame.getInstance().getMyTreeSelectedValue();
        if(selected instanceof  MyTreeNode treeNode){
            RuNode node = treeNode.getNode();
            if(node instanceof Presentation presentation){
                presentation.addChild(new Slide("New Slide", presentation));
                presentation.notifySelfAndParentSubscribers(new PublisherChangedNotification(presentation));
                SwingUtilities.updateComponentTreeUI(MainFrame.getInstance());
            }
            else if(node instanceof Slide slide){
                Presentation presentation = (Presentation) slide.getParent();
                presentation.addChild(presentation.getChildren().indexOf(slide) + 1, new Slide("New Slide ", presentation));
                presentation.notifySelfAndParentSubscribers(new PublisherChangedNotification(presentation));
                SwingUtilities.updateComponentTreeUI(MainFrame.getInstance());

            }
            else if(node instanceof Project){
                MainFrame.getInstance().getUserErrorFactory().createUserError("SLIDETOPROJECT");
            }
            else if(node instanceof Workspace){
                MainFrame.getInstance().getUserErrorFactory().createUserError("SLIDETOWORKSPACE");
            }
        }
        else {
            MainFrame.getInstance().getUserErrorFactory().createUserError("CHILDTONOTHING");
        }
    }
}
