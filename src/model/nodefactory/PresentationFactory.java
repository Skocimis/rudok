package model.nodefactory;

import model.Presentation;
import model.Project;
import model.Workspace;
import model.rutree.RuNode;
import model.rutree.RuNodeC;

public class PresentationFactory extends AbstractNodeFactory{
    @Override
    protected RuNode createNode(RuNodeC parent) {
        if(parent instanceof Project project){
            return new Presentation("New presentation", project);
        }
        return new Presentation("New presentation", null);
    }
}
