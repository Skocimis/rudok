package actions;

import windows.dialogs.InfoDialog;
import windows.dialogs.SimpleDialog;
import windows.frames.MainFrame;

import java.awt.event.ActionEvent;

public class UndoAction extends AbstractRudokAction {
    public UndoAction(){
        putValue(SMALL_ICON, loadIcon("images/undoAction.png"));
        putValue(NAME, "Undo");
        putValue(SHORT_DESCRIPTION, "Undo last change");
    }

    public void actionPerformed(ActionEvent actionEvent) {
        MainFrame.getInstance().getCommandManager().undoCommand();
    }
}
