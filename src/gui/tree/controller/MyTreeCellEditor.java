package gui.tree.controller;

import commands.RenameNodeCommand;
import gui.tree.model.MyTreeNode;
import gui.tree.view.MyTree;
import model.rutree.RuNode;
import notifications.PublisherChangedNotification;
import windows.frames.MainFrame;

import javax.swing.*;
import javax.swing.tree.DefaultTreeCellEditor;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventObject;

public class MyTreeCellEditor extends DefaultTreeCellEditor implements ActionListener {
    private Object item =null;
    private JTextField edit=null;
    private MyTree tree;
    public MyTreeCellEditor(MyTree arg0, DefaultTreeCellRenderer arg1) {
        super(arg0, arg1);
        tree = arg0;
    }

    public Component getTreeCellEditorComponent(JTree arg0, Object arg1, boolean arg2, boolean arg3, boolean arg4, int arg5) {
        tree = (MyTree) arg0;
        item =arg1;

        edit=new JTextField(arg1.toString());
        edit.addActionListener(this);
        return edit;
    }



    public boolean isCellEditable(EventObject arg0) {
        return tree.isRenameMode();
    }



    public void actionPerformed(ActionEvent e){
        String name = e.getActionCommand();
        if(name.length()==0){
            MainFrame.getInstance().getUserErrorFactory().createUserError("RENAMETONULL");
        }
        else {
            if(item instanceof MyTreeNode){
                /*RuNode rn = ((MyTreeNode) item).getNode();
                rn.setName(e.getActionCommand());

                rn.notifySubscribers(new PublisherChangedNotification(rn));
                if(rn.getParent()!=null){
                    rn.getParent().notifySubscribers(new PublisherChangedNotification(rn.getParent()));
                }*/
                MainFrame.getInstance().getCommandManager().addCommand(new RenameNodeCommand(((MyTreeNode) item).getNode(), e.getActionCommand()));
            }
            tree.setRenameMode(false);
            //SwingUtilities.updateComponentTreeUI(MainFrame.getInstance());
        }

    }
}
