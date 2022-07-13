package windows.dialogs;

import components.EmptySlotEditor;
import components.ImageSlotEditor;
import components.SlotEditor;
import components.TextSlotEditor;
import main.Main;
import model.Presentation;
import model.Project;
import model.Slide;
import model.Slot;
import model.slots.EmptySlot;
import model.slots.ImageSlot;
import model.slots.TextSlot;
import notifications.PublisherChangedNotification;
import notifications.SlotChangedNotification;
import observer.ISubscriber;
import windows.frames.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class SlotEditorDialog extends JDialog implements ISubscriber {
    Slot slot;
    Slot formerSlot;
    SlotEditor editor;
    JPanel mainPanel;
    JPanel typeSelectorPanel;
    JRadioButton imageRadioButton;
    JRadioButton textRadioButton;
    ButtonGroup group;
    JButton saveButton;

    private void changeSlotClass(Slot slot){
        List<Slot> slots = MainFrame.getInstance().getProjectView().getOpenPresentationView().getjPanelSlidesView().getSelectedComponent().getSlide().getSlots();
        int index = slots.indexOf(this.slot);
        slots.set(index, slot);
        //this.slot = slots.get(index);
        //showDialog(slot);
    }

    public SlotEditorDialog() {
        super((Frame) null, "Slot settings", true);
        setSize(400,300);
        setLocationRelativeTo(null);
        SlotEditorDialog self = this;
        formerSlot = null;
        editor=new EmptySlotEditor();
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        group = new ButtonGroup();
        imageRadioButton = new JRadioButton("Image");
        imageRadioButton.addActionListener(e->{
            if(self.formerSlot==null){
                self.formerSlot = self.slot;
            }
            self.slot = new ImageSlot(slot.getPosition(), slot.getSize(), slot.getColor(), slot.getBackgroundColor(), slot.getStroke());
            self.render();
            //self.changeSlotClass(new ImageSlot(slot.getPosition(), slot.getSize(), slot.getColor(), slot.getBackgroundColor(), slot.getStroke()));
        });
        textRadioButton = new JRadioButton("Text");
        textRadioButton.addActionListener(e->{
            if(self.formerSlot==null){
                self.formerSlot = self.slot;
            }
            self.slot = new TextSlot(slot.getPosition(), slot.getSize(), slot.getColor(), slot.getBackgroundColor(), slot.getStroke());
            self.render();
            //self.changeSlotClass(new TextSlot(slot.getPosition(), slot.getSize(), slot.getColor(), slot.getBackgroundColor(), slot.getStroke()));
        });
        group.add(imageRadioButton);
        group.add(textRadioButton);
        typeSelectorPanel = new JPanel();
        typeSelectorPanel.add(imageRadioButton);
        typeSelectorPanel.add(textRadioButton);
        mainPanel.add(typeSelectorPanel, BorderLayout.NORTH);

        saveButton = new JButton("Save");
        saveButton.addActionListener(e->{
            //cuvanje podataka u self slot
            slot.setContent(self.editor.exportContent());
            Slide slide = MainFrame.getInstance().getProjectView().getOpenPresentationView().getjPanelSlidesView().getSelectedComponent().getSlide();
            if(self.formerSlot!=null){
                List<Slot> slots = slide.getSlots();
                int index = slots.indexOf(self.formerSlot);
                slots.set(index, self.slot);
            }
            //slide.notifySubscribers(new SlotChangedNotification(self.slot));
            slide.notifySubscribers(new PublisherChangedNotification(slide));//Potreban je novi view
            //MainFrame.getInstance().getProjectView().getOpenPresentationView().getjPanelSlidesView().getSelectedComponent().update(new PublisherChangedNotification(slide));

            Project project = (Project) MainFrame.getInstance().getProjectView().getOpenPresentationView().getPresentation().getParent();
            if(!project.isEdited()){
                project.setEdited(true);
                SwingUtilities.updateComponentTreeUI(MainFrame.getInstance());
            }
            self.setVisible(false);
        });
        mainPanel.add(saveButton,BorderLayout.SOUTH);
        mainPanel.add(editor, BorderLayout.CENTER);
        add(mainPanel);
    }
    public void showDialog(Slot editedSlot){
        System.out.println(editedSlot.getPosition().getX());
        slot = editedSlot;
        formerSlot = null;
        render();
        setVisible(true);
    }
    private void render(){
        //remove(editor);
        mainPanel.remove(editor);
        if(slot instanceof EmptySlot){
            System.out.println("EMPTY");
            editor = new EmptySlotEditor();
            group.clearSelection();
        }
        else if(slot instanceof ImageSlot imageSlot){
            System.out.println("IMAGE");
            editor = new ImageSlotEditor(imageSlot);
            imageRadioButton.setSelected(true);
        }
        else if(slot instanceof TextSlot textSlot){
            System.out.println("TEKST");
            editor = new TextSlotEditor(textSlot);
            textRadioButton.setSelected(true);
        }
        mainPanel.add(editor, BorderLayout.CENTER);
        this.repaint();
        this.revalidate();
    }

    @Override
    public void update(Object notification) {

    }
}
