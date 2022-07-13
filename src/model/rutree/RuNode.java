package model.rutree;

import notifications.PublisherChangedNotification;
import observer.IPublisher;
import observer.ISubscriber;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class RuNode implements IPublisher, Serializable {
    transient List<ISubscriber> subscribers;
    private String name;
    private RuNodeC parent;
    public RuNode(String name, RuNodeC parent){
        setName(name);
        setParent(parent);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RuNodeC getParent() {
        return parent;
    }

    public void setParent(RuNodeC parent) {
        this.parent = parent;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        return this == o;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, parent);
    }

    @Override
    public void addSubscriber(ISubscriber sub) {
        if(sub == null)
            return;
        if(this.subscribers ==null)
            this.subscribers = new ArrayList<>();
        if(this.subscribers.contains(sub))
            return;
        this.subscribers.add(sub);
    }

    @Override
    public void removeSubscriber(ISubscriber sub) {
        if(sub == null || this.subscribers == null || !this.subscribers.contains(sub))
            return;
        this.subscribers.remove(sub);
    }


    @Override
    public void notifySubscribers(Object notification) {
        if(notification == null || this.subscribers == null || this.subscribers.isEmpty())
            return;

        for(ISubscriber listener : subscribers){
            listener.update(notification);
        }
    }

    public void notifySelfAndParentSubscribers(Object notification) {
        if(parent!=null){
            parent.notifySelfAndParentSubscribers(notification);
        }
        this.notifySubscribers(notification);
    }
    public void removeChildrenSubscribers(){
        if(this instanceof RuNodeC){
            for(RuNode child: ((RuNodeC) this).getChildren()){
                child.removeChildrenSubscribers();
            }
        }
        subscribers.removeIf(p->true);
    }

    public List<ISubscriber> getSubscribers() {
        return subscribers;
    }
}
