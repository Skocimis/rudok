package model;
import model.rutree.RuNode;
import model.rutree.RuNodeC;
import notifications.PublisherChangedNotification;
import observer.IPublisher;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class Project extends RuNodeC implements IPublisher, Serializable {
    transient File file;
    transient boolean edited = false;

    @Override
    public String toString() {
        return getName();
    }

    public Project(String name, Workspace parent) {
        super(name, parent);
        setChildren(new ArrayList<>());
    }

    @Override
    public void addChild(RuNode node) {
        if (node instanceof Presentation) {
            getChildren().add(node);
        }
    }

    @Override
    public void addChild(int index, RuNode node) {
        if (node instanceof Presentation) {
            getChildren().add(index, node);
        }
    }

    @Override
    public void removeChild(RuNode node) {
        if (node instanceof Presentation) {
            getChildren().remove(node);
        }
    }

    public List<RuNode> getAvailablePresentations() {
        List<RuNode> presentations = new ArrayList<>(getChildren());

        Workspace workspace = (Workspace) getParent();
        for (RuNode ruNode1 : workspace.getChildren()) {
            if (ruNode1 instanceof Project p) {
                if (p == this) continue;
                for (RuNode ruNode2 : p.getChildren()) {
                    if (ruNode2 instanceof Presentation presentation) {
                        if (presentation.getProjects().contains(this)) {
                            presentations.add(presentation);
                        }
                    }
                }
            }
        }
        return presentations;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public boolean isEdited() {
        return edited;
    }

    public void setEdited(boolean edited) {
        this.edited = edited;
    }
}
