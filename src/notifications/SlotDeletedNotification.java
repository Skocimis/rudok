package notifications;

import model.Slot;

public class SlotDeletedNotification extends SlotNotification{

    public SlotDeletedNotification(Slot slot) {
        super(slot);
    }
}
