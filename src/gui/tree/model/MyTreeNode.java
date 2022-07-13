package gui.tree.model;

import model.Presentation;
import model.Project;
import model.Slide;
import model.Workspace;
import model.rutree.RuNode;
import model.rutree.RuNodeC;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MyTreeNode extends DefaultMutableTreeNode {
    RuNode node;
    RuNodeC parent;//RuNode
    List<RuNode> children;//RuNode
    public MyTreeNode(RuNode ruNode){
        node = ruNode;
        parent = ruNode.getParent();
        if(ruNode instanceof Project project){
            children = project.getAvailablePresentations();

        }
        else{
            children = (ruNode instanceof RuNodeC?((RuNodeC) ruNode).getChildren():new ArrayList<>());
        }
    }
    public MyTreeNode(RuNode ruNode, RuNodeC parent){
        node = ruNode;
        this.parent = parent;
        if(ruNode instanceof Project project){
            children = project.getAvailablePresentations();

            /*Workspace workspace = (Workspace) project.getParent();
            for(RuNode ruNode1:workspace.getChildren()){
                if(ruNode1 instanceof Project p){
                    if(p==project) continue;
                    for(RuNode ruNode2:p.getChildren()){
                        if(ruNode2 instanceof Presentation presentation){
                            if(presentation.getProjects().contains(project)){
                                children.add(presentation);
                            }
                        }
                    }
                }
            }*/
        }
        else{
            children = (ruNode instanceof RuNodeC?((RuNodeC) ruNode).getChildren():new ArrayList<>());
        }
    }



    @Override
    public TreeNode getParent() {
        if(parent==null) return null;
        return new MyTreeNode(parent);
    }

    @Override
    public TreeNode getChildAt(int index) {
        if(getNode() instanceof Project project){
            if(index<project.getChildren().size()){
                return new MyTreeNode(children.get(index));
            }
            else{
                if(getNode() instanceof RuNodeC ruNodeC){
                    return new MyTreeNode(children.get(index), ruNodeC);
                }
                else{
                    return new MyTreeNode(children.get(index));//Vljd nece nikad
                }
            }
        }
        if(getNode() instanceof RuNodeC){
            return new MyTreeNode(((RuNodeC) getNode()).getChildren().get(index));
        }
        else return  null;
    }

    @Override
    public int getChildCount() {
        if(getNode() instanceof RuNodeC){
            return children.size();
        }
        else return 0;
    }

    public int getIndex(MyTreeNode aChild) {
        if(getNode() instanceof RuNodeC){
            return children.indexOf(aChild.getNode());
        }
        else return -1;
    }

    @Override
    public boolean getAllowsChildren() {
        return true;
    }


    @Override
    public boolean isLeaf() {
        return getNode() instanceof Slide;
    }

    @Override
    public String toString() {
        return getNode().getName();
    }

    public RuNode getNode() {
        return node;
    }

    public void setNode(RuNode node) {
        this.node = node;
    }

    @Override
    public boolean equals(Object o) {
        if(o.getClass()!=this.getClass()) return false;
        return getNode()==((MyTreeNode) o).getNode();
    }

    @Override
    public int hashCode() {
        return Objects.hash(node);
    }
}
