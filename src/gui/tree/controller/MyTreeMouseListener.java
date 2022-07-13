package gui.tree.controller;

import gui.ProjectView;
import gui.tree.model.MyTreeNode;
import gui.tree.view.MyTree;
import model.Project;
import model.rutree.RuNode;
import notifications.PublisherChangedNotification;
import windows.frames.MainFrame;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MyTreeMouseListener implements MouseListener {
    MyTree tree;
    public MyTreeMouseListener(MyTree myTree){
        tree = myTree;
    }
    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        int row=tree.getRowForLocation(mouseEvent.getX(),mouseEvent.getY());
        if(row==-1)
        {
            tree.clearSelection();
            tree.setSelectionPath(null);
            MainFrame.getInstance().setMyTreeSelectedValue(null);
            tree.setRenameMode(false);
        }
        if(mouseEvent.getClickCount()==2){
            if(tree.getLeadSelectionPath()==null) return;
            RuNode node = ((MyTreeNode)(tree.getLeadSelectionPath().getLastPathComponent())).getNode();
            if(node instanceof Project project){
                ProjectView projectView = MainFrame.getInstance().getProjectView();
                if(projectView.getProject()==node){
                    return;
                }
                if(projectView.getProject()!=null){
                    projectView.getProject().removeChildrenSubscribers();
                }
                node.addSubscriber(projectView);
                project.notifySubscribers(new PublisherChangedNotification(project));
            }

        }
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }
}
