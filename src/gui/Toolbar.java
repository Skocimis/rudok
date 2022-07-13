package gui;

import windows.frames.MainFrame;

import javax.swing.*;
import java.awt.*;

public class Toolbar extends JToolBar{

    public Toolbar(){

        super(JToolBar.HORIZONTAL);
        setFloatable(false);

        add(MainFrame.getInstance().getActionManager().getAddChildAction());
        add(MainFrame.getInstance().getActionManager().getOpenProjectAction());
        add(MainFrame.getInstance().getActionManager().getSaveProjectAction());
        add(MainFrame.getInstance().getActionManager().getUndoAction());
        add(MainFrame.getInstance().getActionManager().getRedoAction());
        add(MainFrame.getInstance().getActionManager().getInfoAction());
        add(MainFrame.getInstance().getActionManager().getRenameNodeAction());
        add(MainFrame.getInstance().getActionManager().getDeleteNodeAction());
        add(MainFrame.getInstance().getActionManager().getNewSlotPreferencesAction());
        for (var a:getComponents()){
            a.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }

    }
}
