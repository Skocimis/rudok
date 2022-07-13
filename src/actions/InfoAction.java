package actions;

import usererror.UnimplementedError;
import usererror.UserErrorFactory;
import windows.dialogs.InfoDialog;
import windows.dialogs.SimpleDialog;

import java.awt.event.ActionEvent;

public class InfoAction extends AbstractRudokAction {
    public InfoAction(){
        putValue(SMALL_ICON, loadIcon("images/infoAction.png"));
        putValue(NAME, "Info");
        putValue(SHORT_DESCRIPTION, "App info");
    }

    public void actionPerformed(ActionEvent actionEvent) {
        SimpleDialog dialog = new InfoDialog();
        dialog.setVisible(true);
    }
}
