package model.nodefactory;

import model.Project;
import model.Workspace;
import model.rutree.RuNode;
import model.rutree.RuNodeC;

public class ProjectFactory extends AbstractNodeFactory{
    @Override
    protected RuNode createNode(RuNodeC parent) {
        if(parent instanceof Workspace workspace){
            return new Project("New project", workspace);
        }
        return new Project("New project", null);
    }
}
