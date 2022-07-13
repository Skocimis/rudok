package notifications;

import model.Slot;

public abstract class SlotNotification extends MyNotification {

    Slot slot;

    public SlotNotification(Slot slot){
        this.slot = slot;
    }
    public Slot getSlot() {
        return slot;
    }

    public void setSlot(Slot slot) {
        this.slot = slot;
    }
}
