package presentationstate;

import presentationstate.states.Editor;
import presentationstate.states.SlideShow;

public class StateManager {
    private MyState currentState;
    private SlideShow slideShow;
    private Editor editor;

    public void initStates(){
        slideShow = new SlideShow();
        editor = new Editor();
        currentState = editor;
    }

    public StateManager(){
        initStates();
    }

    public MyState getCurrentState() {
        return currentState;
    }

    public void setSlideShow() { currentState = slideShow; }

    public void setEditor() {
        currentState = editor;
    }

    public boolean isEditor(){ return currentState == editor; }
    public boolean isSlideShow(){ return currentState == slideShow; }
}
