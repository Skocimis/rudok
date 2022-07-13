package model;

import model.rutree.RuNode;
import model.rutree.RuNodeC;

import java.util.ArrayList;
import java.util.List;

public class Presentation extends RuNodeC {
    private String author;
    private String theme;
    private transient List<Project> projects;

    public Presentation(){
        super("aaa", null);
        author = "(Nepotpisan)";
        theme = "images/defaultTheme.png";
        projects = new ArrayList<>();
    }
    public Presentation(String name, Project parent) {
        super(name, parent);
        setChildren(new ArrayList<>());
        author = "(Nepotpisan)";
        theme = "images/defaultTheme.png";
        projects = new ArrayList<>();
        projects.add(parent);

    }
    public void addProject(Project project){
        projects.add(project);
    }
    public void removeProject(Project project){
        projects.remove(project);
    }

    @Override
    public String toString() {
        return "Presentation";
    }

    @Override
    public void addChild(RuNode node) {
        if(node instanceof Slide){
            getChildren().add(node);
        }
    }

    @Override
    public void addChild(int index, RuNode node) {
        if(node instanceof Slide){
            getChildren().add(index, node);
        }
    }

    @Override
    public void removeChild(RuNode node) {
        if(node instanceof Slide){
            getChildren().remove(node);
        }
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }
}