package gui.tree.controller;

import gui.SlideView;
import gui.tree.model.MyTreeNode;
import gui.tree.view.MyTree;
import model.Presentation;
import model.Project;
import model.Slide;
import model.Workspace;
import model.rutree.RuNode;

import javax.swing.*;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;
import java.net.URL;

public class MyTreeCellRenderer extends DefaultTreeCellRenderer {
    public Component getTreeCellRendererComponent(
            JTree tree,
            Object value,
            boolean sel,
            boolean expanded,
            boolean leaf,
            int row,
            boolean hasFocus) {
        super.getTreeCellRendererComponent(tree, value, sel,expanded, leaf, row,hasFocus);
        if(value instanceof MyTreeNode myTreeNode){
            RuNode comp = myTreeNode.getNode();
            if (comp instanceof Workspace workspace) {
                setText((workspace.isEdited()?"*":"")+workspace.getName());
                setIcon(new ImageIcon("images/workspaceIcon.png"));

            } else if (comp instanceof Project project) {
                setText((project.isEdited()?"*":"")+project.getName());
                setIcon(new ImageIcon("images/projectIcon.png"));

            } else if (comp instanceof Presentation presentation) {
                setIcon(new ImageIcon("images/presentationIcon.png"));
                if(((MyTreeNode)myTreeNode.getParent()).getNode()!=presentation.getParent()){
                    setText(getText()+" (From "+presentation.getParent().getName()+")");
                }

            } else if (comp instanceof Slide) {
                setIcon(new ImageIcon("images/slideIcon.png"));

            }
        }
        return this;
    }

}
