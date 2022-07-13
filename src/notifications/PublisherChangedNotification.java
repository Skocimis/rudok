package notifications;

import observer.IPublisher;

public class PublisherChangedNotification extends MyNotification {
    private IPublisher newPublisher;


    public PublisherChangedNotification(IPublisher newPublisher){
        this.newPublisher = newPublisher;
    }

    public IPublisher getNewPublisher() {
        return newPublisher;
    }

    public void setNewPublisher(IPublisher newPublisher) {
        this.newPublisher = newPublisher;
    }
}
