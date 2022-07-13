package windows.dialogs;

import commands.ChangeSharedPresentationsCommand;
import main.Main;
import model.Presentation;
import model.Project;
import model.Workspace;
import model.rutree.RuNode;
import model.rutree.RuNodeC;
import notifications.PublisherChangedNotification;
import observer.ISubscriber;
import windows.frames.MainFrame;

import javax.swing.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class SharePresentationDialog extends SimpleDialog implements ISubscriber {
    public SharePresentationDialog(Presentation presentation) {
        super(null, "Share presentation \""+presentation.getName()+"\"", true);
        SharePresentationDialog self = this;
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new BoxLayout(jPanel,BoxLayout.Y_AXIS));
        jPanel.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        jPanel.setAlignmentY(JComponent.CENTER_ALIGNMENT);
        List<JCheckBox> cbs = new ArrayList<>();
        List<Project> presentationProjects = presentation.getProjects();
        Workspace workspace = (Workspace) presentation.getParent().getParent();


        for(RuNode ruNode:presentation.getParent().getParent().getChildren()){
            if(ruNode instanceof Project project){
                JCheckBox cb = new JCheckBox(project.getName());
                cb.setSelected(false);
                if(presentationProjects.contains(project)){
                    cb.setSelected(true);
                }
                if(project==presentation.getParent()){
                    cb.setEnabled(false);
                }
                cbs.add(cb);
            }
        }
        for(JCheckBox cb:cbs){
            jPanel.add(cb);
        }
        JButton btDone = new JButton("Done");
        btDone.addActionListener(e->{
            List<Project> forChange = new ArrayList<>();
            for(JCheckBox cb:cbs){
                Project cbProject = (Project) workspace.getChildren().get(cbs.indexOf(cb));
                if(cb.isSelected()&&!presentation.getProjects().contains(cbProject)){
                    forChange.add(cbProject);
                }
                if(!cb.isSelected()&&presentation.getProjects().contains(cbProject)){
                    forChange.add(cbProject);
                }
            }
            MainFrame.getInstance().getCommandManager().addCommand(new ChangeSharedPresentationsCommand(presentation, forChange));
            self.dispose();
        });
        jPanel.add(btDone);
        add(jPanel);
    }

    @Override
    public void update(Object notification) {

    }
}
