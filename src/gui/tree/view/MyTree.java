package gui.tree.view;

import gui.tree.controller.*;
import gui.tree.model.MyTreeModel;
import main.Main;
import windows.frames.MainFrame;

import javax.swing.*;
import javax.swing.tree.TreeSelectionModel;

public class MyTree extends JTree {
    private boolean renameMode;
    public MyTree(MyTreeModel myTreeModel) {
        MyTreeCellRenderer myTreeCellRenderer = new MyTreeCellRenderer();
        MyTreeCellEditor myTreeCellEditor = new MyTreeCellEditor(this, myTreeCellRenderer);
        MyTreeSelectionListener myTreeSelectionListener = new MyTreeSelectionListener(this);
        MyTreeMouseListener myTreeMouseListener = new MyTreeMouseListener(this);
        MyTreeKeyboardListener myTreeKeyboardListener = new MyTreeKeyboardListener(this);
        setModel(myTreeModel);
        getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        setExpandsSelectedPaths(false);
        addMouseListener(myTreeMouseListener);
        addKeyListener(myTreeKeyboardListener);
        addTreeSelectionListener(myTreeSelectionListener);
        setCellEditor(myTreeCellEditor);
        setCellRenderer(myTreeCellRenderer);
        setEditable(true);
        renameMode = false;
    }

    public boolean isRenameMode() {
        return renameMode;
    }

    public void setRenameMode(boolean renameMode) {
        this.renameMode = renameMode;
    }
}
