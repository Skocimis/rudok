package database;

import model.Presentation;
import model.Project;
import model.Slide;
import model.Workspace;
import observer.IPublisher;
import observer.ISubscriber;

import java.util.ArrayList;
import java.util.List;

public class Database implements IPublisher {
    private static Database instance;
    private Workspace activeWorkspace;
    private List<ISubscriber> subscribers;

    private Database(){

    }
    private void initialise(){
        loadData();
    }
    private void loadData(){
        //Load workspacea
        activeWorkspace = new Workspace("Workspace", null);

    }

    public static Database getInstance(){
        if(instance==null){
            instance = new Database();
            instance.initialise();
        }
        return instance;
    }

    @Override
    public void addSubscriber(ISubscriber sub) {
        if(sub==null){
            return;
        }
        if(this.subscribers==null){
            this.subscribers = new ArrayList<>();
        }
        if(this.subscribers.contains(sub))
            return;
        this.subscribers.add(sub);
    }

    @Override
    public void removeSubscriber(ISubscriber sub) {
        if(sub==null||this.subscribers==null||!this.subscribers.contains(sub)){
            return;
        }
        this.subscribers.remove(sub);
    }

    @Override
    public void notifySubscribers(Object notification) {
        if(notification==null||this.subscribers==null||this.subscribers.isEmpty()){
            return;
        }
        for (ISubscriber listener:subscribers){
            listener.update(notification);
        }
    }


    public Workspace getActiveWorkspace() {
        return activeWorkspace;
    }

    public void setActiveWorkspace(Workspace activeWorkspace) {
        this.activeWorkspace = activeWorkspace;
    }
}
