package gui.tree.model;

import database.Database;
import gui.tree.model.MyTreeNode;
import model.Project;
import model.Workspace;
import model.rutree.RuNode;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;

public class MyTreeModel extends DefaultTreeModel {
    public MyTreeModel() {
        super(new MyTreeNode(Database.getInstance().getActiveWorkspace()));
    }
}
