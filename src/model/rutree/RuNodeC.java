package model.rutree;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class RuNodeC extends RuNode implements Serializable {
    private List<RuNode> children;

    public RuNodeC(String name, RuNodeC parent) {
        super(name, parent);
        this.children = new ArrayList<>();
    }

    public abstract void addChild(RuNode node);
    public abstract void addChild(int index, RuNode node);
    public abstract void removeChild(RuNode node);


    public List<RuNode> getChildren() {
        return children;
    }

    public void setChildren(List<RuNode> children) {
        this.children = children;
    }


}
