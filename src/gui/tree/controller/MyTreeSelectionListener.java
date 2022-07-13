package gui.tree.controller;

import gui.tree.view.MyTree;
import main.Main;
import windows.frames.MainFrame;

import javax.swing.event.TreeSelectionEvent;

public class MyTreeSelectionListener implements javax.swing.event.TreeSelectionListener {
    MyTree tree;
    public MyTreeSelectionListener(MyTree myTree){
        tree= myTree;
    }
    public void valueChanged(TreeSelectionEvent e) {
        MainFrame.getInstance().getMyTree().setSelectionPath(e.getPath());
        MainFrame.getInstance().setMyTreeSelectedValue(e.getPath().getLastPathComponent());
    }
}
