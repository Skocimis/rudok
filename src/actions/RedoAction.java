package actions;

import windows.dialogs.InfoDialog;
import windows.dialogs.SimpleDialog;
import windows.frames.MainFrame;

import java.awt.event.ActionEvent;

public class RedoAction extends AbstractRudokAction {
    public RedoAction(){
        putValue(SMALL_ICON, loadIcon("images/redoAction.png"));
        putValue(NAME, "Redo");
        putValue(SHORT_DESCRIPTION, "Redo last undone change");
    }

    public void actionPerformed(ActionEvent actionEvent) {
        MainFrame.getInstance().getCommandManager().doCommand();
    }
}
