package model.nodefactory;

import model.Presentation;
import model.Slide;
import model.rutree.RuNode;
import model.rutree.RuNodeC;

public class SlideFactory extends AbstractNodeFactory{
    @Override
    protected RuNode createNode(RuNodeC parent) {
        if(parent instanceof Presentation presentation){
            return new Slide("New slide", presentation);
        }
        return new Slide("New slide", null);
    }
}
