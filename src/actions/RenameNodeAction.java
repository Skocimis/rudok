package actions;

import gui.tree.controller.MyTreeKeyboardListener;
import gui.tree.model.MyTreeNode;
import gui.tree.view.MyTree;
import model.Presentation;
import model.Slide;
import model.rutree.RuNode;
import windows.dialogs.PresentationSettingsDialog;
import windows.dialogs.RenameNodeDialog;
import windows.dialogs.SimpleDialog;
import windows.frames.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class RenameNodeAction extends AbstractRudokAction {
    public RenameNodeAction(){
        putValue(SMALL_ICON, loadIcon("images/renameNodeAction.png"));
        putValue(NAME, "Rename");
        putValue(SHORT_DESCRIPTION, "Rename node");
    }

    public void actionPerformed(ActionEvent actionEvent) {

        if(MainFrame.getInstance().getMyTreeSelectedValue()==null) return;
        MyTreeNode treeNode = (MyTreeNode) MainFrame.getInstance().getMyTreeSelectedValue();
        RuNode node = treeNode.getNode();
        SimpleDialog dialog = new RenameNodeDialog(node);
        dialog.setVisible(true);
    }
}
