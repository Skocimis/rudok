package gui;

import model.Presentation;
import model.Project;
import model.Slide;
import model.rutree.RuNode;
import notifications.MyNotification;
import notifications.PublisherChangedNotification;
import notifications.PublisherDeletedNotification;
import observer.ISubscriber;

import javax.swing.*;
import java.awt.*;

public class ProjectView extends JPanel implements ISubscriber {
    private JTabbedPane jTabbedPane;
    private Project project;
    private JPanel namePanel;
    private JLabel nameLabel;

    public ProjectView(){
        super();
        project = null;
        namePanel = new JPanel();
        nameLabel = new JLabel("Please open a project to start working on it");
        jTabbedPane = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.WRAP_TAB_LAYOUT);
        setLayout(new BorderLayout());
        namePanel.add(nameLabel);
        this.add(jTabbedPane, BorderLayout.CENTER);
        this.add(namePanel, BorderLayout.EAST);
    }

    @Override
    public void update(Object notification) {
        if(notification instanceof PublisherDeletedNotification){
            nameLabel.setText("Please open a project to start working on it");
            jTabbedPane.removeAll();
        }
        if(notification instanceof PublisherChangedNotification publisherChangedNotification){
            if(publisherChangedNotification.getNewPublisher() instanceof Project){
                project = (Project) publisherChangedNotification.getNewPublisher();
                nameLabel.setText(project.getName());
                jTabbedPane.removeAll();
                for(RuNode node : project.getAvailablePresentations()){
                    if(node instanceof Presentation presentation){
                        PresentationView presentationView = new PresentationView();
                        node.addSubscriber(presentationView);
                        jTabbedPane.addTab(presentation.getName(), presentationView);
                        presentation.notifySubscribers(new PublisherChangedNotification(presentation));
                    }
                }
            }
        }
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public JTabbedPane getjTabbedPane() {
        return jTabbedPane;
    }

    public void setjTabbedPane(JTabbedPane jTabbedPane) {
        this.jTabbedPane = jTabbedPane;
    }

    public PresentationView getOpenPresentationView(){
        if(jTabbedPane.getSelectedComponent() instanceof PresentationView)
            return (PresentationView) jTabbedPane.getSelectedComponent();
        else return null;
    }
}
