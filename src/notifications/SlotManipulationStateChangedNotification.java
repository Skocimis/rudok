package notifications;

import slotstate.SlotState;
import slotstate.SlotStateManager;

public class SlotManipulationStateChangedNotification extends MyNotification{
    private SlotState previous;
    private SlotState current;

    public SlotManipulationStateChangedNotification(SlotState previous, SlotState current){
        this.previous = previous;
        this.current = current;
    }

    public SlotState getCurrent() {
        return current;
    }

    public void setCurrent(SlotState current) {
        this.current = current;
    }

    public SlotState getPrevious() {
        return previous;
    }

    public void setPrevious(SlotState previous) {
        this.previous = previous;
    }
}
