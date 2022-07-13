package model;

import model.rutree.RuNode;
import model.rutree.RuNodeC;

import java.io.File;
import java.util.ArrayList;

public class Workspace extends RuNodeC {
    transient File file;
    transient boolean edited = false;

    public Workspace(String name, RuNodeC parent){
        super(name, null);
        //file = new File(System.getenv("APPDATA")+"\\RuDok\\"+"defaultWorkspace.txt");
        //System.out.println("APPDATA");
        //System.out.println(file.getAbsolutePath());
        setChildren(new ArrayList<>());
    }
    public String toString() {
        return "Workspace";
    }


    @Override
    public void addChild(RuNode node) {
        if(node instanceof Project){
            getChildren().add(node);
        }
    }

    @Override
    public void addChild(int index, RuNode node) {
        if(node instanceof Project){
            getChildren().add(index, node);
        }
    }

    @Override
    public void removeChild(RuNode node) {
        if(node instanceof Project){
            getChildren().remove(node);
        }
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
