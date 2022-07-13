package slotstate.states;

import gui.SlideView;
import main.Main;
import model.Project;
import model.Slide;
import model.Slot;
import notifications.SlotDeletedNotification;
import slotstate.SlotState;
import windows.dialogs.SlotEditorDialog;
import windows.frames.MainFrame;

import javax.swing.*;
import java.awt.*;

public class DoNothingState extends SlotState {
    public DoNothingState(){
        super();
        setMouseOverSlotCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }

    @Override
    public void mouseDoubleClicked(double x, double y) {
        SlideView slideView = MainFrame.getInstance().getProjectView().getOpenPresentationView().getjPanelSlidesView().getSelectedComponent();
        Slide slide = slideView.getSlide();
        Slot forEditing = null;
        for (Slot slot:slide.getSlots()){
            if(slot.getPosition().getX()<=x && slot.getPosition().getY()<=y && slot.getPosition().getX()+slot.getSize().getWidth()>=x&&slot.getPosition().getY()+slot.getSize().getHeight()>=y){
                forEditing = slot;
            }
        }
        if(forEditing==null){
            return;
        }
        System.out.println("TODO Dupli klik na slot");
        MainFrame.getInstance().getSlotEditorDialog().showDialog(forEditing);

        //TODO Otvorim dijalog i prosledim mu forEditing, u tom prozoru se bira tip slota (tekst, slika, prazan)
        //TODO Ovo treba da uradim kad se izedituje, vrv moze samo i moved notification
        //TODO slideView.getSlide().notifySelfAndParentSubscribers(new SlotEditedNotification(forEditing));
    }
}
