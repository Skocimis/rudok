package slotstate;

import gui.SlideView;
import model.Slide;
import model.Slot;
import windows.frames.MainFrame;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SlotManipulationStateMouseAdapter extends MouseAdapter {
    SlotStateManager slotStateManager;

    public SlotManipulationStateMouseAdapter(){
        slotStateManager = new SlotStateManager();
    }

    public SlotStateManager getSlotStateManager() {
        return slotStateManager;
    }

    public void setSlotStateManager(SlotStateManager slotStateManager) {
        this.slotStateManager = slotStateManager;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int width = MainFrame.getInstance().getProjectView().getOpenPresentationView().getjPanelSlidesView().getSelectedComponent().getWidth();
        int height = MainFrame.getInstance().getProjectView().getOpenPresentationView().getjPanelSlidesView().getSelectedComponent().getHeight();
        if(e.getClickCount()==1){
            slotStateManager.getCurrentState().mouseClicked(1.0*e.getX()/width, 1.0*(e.getY()+4)/height);
        }
        else if(e.getClickCount()==2){
            slotStateManager.getCurrentState().mouseDoubleClicked(1.0*e.getX()/width, 1.0*(e.getY()+4)/height);
        }
        super.mouseClicked(e);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int width = MainFrame.getInstance().getProjectView().getOpenPresentationView().getjPanelSlidesView().getSelectedComponent().getWidth();
        int height = MainFrame.getInstance().getProjectView().getOpenPresentationView().getjPanelSlidesView().getSelectedComponent().getHeight();
        slotStateManager.getCurrentState().mousePressed(1.0*e.getX()/width, 1.0*(e.getY()+4)/height);
        super.mousePressed(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        int width = MainFrame.getInstance().getProjectView().getOpenPresentationView().getjPanelSlidesView().getSelectedComponent().getWidth();
        int height = MainFrame.getInstance().getProjectView().getOpenPresentationView().getjPanelSlidesView().getSelectedComponent().getHeight();
        slotStateManager.getCurrentState().mouseReleased(1.0*e.getX()/width, 1.0*(e.getY()+4)/height);
        super.mouseReleased(e);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        int width = MainFrame.getInstance().getProjectView().getOpenPresentationView().getjPanelSlidesView().getSelectedComponent().getWidth();
        int height = MainFrame.getInstance().getProjectView().getOpenPresentationView().getjPanelSlidesView().getSelectedComponent().getHeight();
        double x = 1.0*e.getX()/width;
        double y = 1.0*(e.getY()+4)/height;

        SlideView slideView = MainFrame.getInstance().getProjectView().getOpenPresentationView().getjPanelSlidesView().getSelectedComponent();
        Slide slide = slideView.getSlide();
        Slot forSelection = null;
        for (Slot slot:slide.getSlots()){
            if(slot.getPosition().getX()<=x && slot.getPosition().getY()<=y && slot.getPosition().getX()+slot.getSize().getWidth()>=x&&slot.getPosition().getY()+slot.getSize().getHeight()>=y){
                forSelection = slot;
            }
        }
        if(forSelection==null){
            slideView.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        }
        else{
            slideView.setCursor(slotStateManager.getCurrentState().getMouseOverSlotCursor());
        }
        slotStateManager.getCurrentState().mouseMoved(x, y);
        super.mouseMoved(e);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        int width = MainFrame.getInstance().getProjectView().getOpenPresentationView().getjPanelSlidesView().getSelectedComponent().getWidth();
        int height = MainFrame.getInstance().getProjectView().getOpenPresentationView().getjPanelSlidesView().getSelectedComponent().getHeight();
        slotStateManager.getCurrentState().mouseDragged(1.0*e.getX()/width, 1.0*(e.getY()+4)/height);
        super.mouseDragged(e);
    }
}
