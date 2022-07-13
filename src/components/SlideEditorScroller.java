package components;

import gui.SlideView;
import windows.frames.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class SlideEditorScroller extends JPanel {
    private CardLayout layout;
    private Integer selectedIndex;
    public SlideEditorScroller(){
        super();
        JPanel self = this;
        selectedIndex = 0;
        addMouseWheelListener(mouseWheelEvent -> {
            if(mouseWheelEvent.getWheelRotation()==1){
                if(selectedIndex+1<getComponentCount()){
                    selectedIndex++;
                }
                layout.show(self, selectedIndex.toString());
            }
            else if(mouseWheelEvent.getWheelRotation()==-1){
                if(selectedIndex>0){
                    selectedIndex--;
                }
                layout.show(self, selectedIndex.toString());
            }
        });
        layout = new CardLayout();
        setLayout(layout);
    }

    public SlideView getSelectedComponent(){
        if( getComponent(selectedIndex) instanceof SlideView slideView) return slideView;
        return null;
    }

    public int getSelectedIndex() {
        return selectedIndex;
    }

    public void setSelectedIndex(int selectedIndex) {
        this.selectedIndex = selectedIndex;
        layout.show(this, this.selectedIndex.toString());
    }

}
