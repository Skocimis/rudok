package actions;

import gui.PresentationView;
import gui.ProjectView;
import model.Presentation;
import windows.dialogs.NewSlotPreferencesDialog;
import windows.dialogs.PresentationSettingsDialog;
import windows.dialogs.SimpleDialog;
import windows.frames.MainFrame;

import java.awt.event.ActionEvent;

public class NewSlotPreferencesAction extends AbstractRudokAction {
    public NewSlotPreferencesAction(){
        putValue(SMALL_ICON, loadIcon("images/newSlotPreferences.png"));
        putValue(NAME, "Preferences");
        putValue(SHORT_DESCRIPTION, "Color and stroke of new slots");
    }

    public void actionPerformed(ActionEvent actionEvent) {
        SimpleDialog slotPreferences = new NewSlotPreferencesDialog();
        slotPreferences.setVisible(true);
    }
}
