package model.nodefactory;

import model.rutree.RuNode;
import model.rutree.RuNodeC;

public abstract class AbstractNodeFactory {
    public RuNode getNodeForTree(RuNodeC parent){
        return createNode(parent);
    }
    protected abstract RuNode createNode(RuNodeC parent);
}
