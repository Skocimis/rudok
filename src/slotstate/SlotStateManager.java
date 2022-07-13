package slotstate;

import notifications.SlotManipulationStateChangedNotification;
import observer.IPublisher;
import observer.ISubscriber;
import observer.MyPublisher;
import presentationstate.MyState;
import presentationstate.states.Editor;
import presentationstate.states.SlideShow;
import slotstate.states.*;

import java.util.ArrayList;
import java.util.List;

public class SlotStateManager extends MyPublisher {
    private SlotState currentState;
    private AddSlotState addSlotState;
    private DeleteSlotState deleteSlotState;
    private MoveSlotState moveSlotState;
    private ResizeSlotState resizeSlotState;
    private DoNothingState doNothingState;
    private List<ISubscriber> subscribers;

    public void initStates(){
        subscribers = new ArrayList<>();
        addSlotState = new AddSlotState();
        deleteSlotState = new DeleteSlotState();
        moveSlotState = new MoveSlotState();
        resizeSlotState = new ResizeSlotState();
        doNothingState = new DoNothingState();
        currentState = doNothingState;
    }

    public SlotStateManager(){
        initStates();
    }

    public SlotState getCurrentState() {
        return currentState;
    }

    public boolean inAddSlotState() {
        return addSlotState==currentState;
    }

    public void setAddSlotState() {
        SlotState previous = currentState;
        currentState = addSlotState;
        notifySubscribers(new SlotManipulationStateChangedNotification(previous, currentState));
    }

    public boolean inDeleteSlotState() {
        return deleteSlotState==currentState;
    }

    public void setDeleteSlotState() {
        SlotState previous = currentState;
        currentState = deleteSlotState;
        notifySubscribers(new SlotManipulationStateChangedNotification(previous, currentState));
    }

    public boolean inMoveSlotState() {
        return moveSlotState==currentState;
    }

    public void setMoveSlotState() {
        SlotState previous = currentState;
        currentState = moveSlotState;
        notifySubscribers(new SlotManipulationStateChangedNotification(previous, currentState));
    }

    public boolean inDoNothingState() {
        return doNothingState==currentState;
    }

    public void setDoNothingState() {
        SlotState previous = currentState;
        currentState = doNothingState;
        notifySubscribers(new SlotManipulationStateChangedNotification(previous, currentState));
    }


    public boolean inResizeSlotState() {
        return resizeSlotState==currentState;
    }

    public void setResizeSlotState() {
        SlotState previous = currentState;
        currentState = resizeSlotState;
        notifySubscribers(new SlotManipulationStateChangedNotification(previous, currentState));
    }

    public List<ISubscriber> getSubscribers() {
        return subscribers;
    }

    public void setSubscribers(List<ISubscriber> subscribers) {
        this.subscribers = subscribers;
    }
}
