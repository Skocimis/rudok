package notifications;

import model.Presentation;
import observer.IPublisher;

public class OriginalPresentationChangedNotification extends MyNotification {
    private Presentation original;


    public OriginalPresentationChangedNotification(Presentation original){
        this.original = original;
    }

    public Presentation getNewPublisher() {
        return original;
    }

    public void setNewPublisher(Presentation original) {
        this.original = original;
    }
}
