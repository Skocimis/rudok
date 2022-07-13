package gui;

import components.SlideEditorScroller;
import main.Main;
import model.Presentation;
import model.Slide;
import model.rutree.RuNode;
import notifications.MyNotification;
import notifications.PublisherChangedNotification;
import notifications.PublisherDeletedNotification;
import observer.ISubscriber;
import presentationstate.StateManager;
import windows.frames.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.ArrayList;
import java.util.Arrays;

public class PresentationView extends JPanel implements ISubscriber {
    private Presentation presentation;
    private JPanel authorPanel;
    private JLabel authorLabel;
    private JPanel mainView;
    private JPanel navigationView;
    private SlideEditorScroller jPanelSlidesView;
    private SlideEditorScroller jPanelSlideshowView;
    private JScrollPane jScrollPaneNavigation;
    private StateManager stateManager;
    private ComponentListener navigationComponentListener;
    private boolean rsz = false;

    public void startEditorMode(){
        stateManager.setEditor();

        render();
    }
    public void startSlideShowMode(){
        stateManager.setSlideShow();
        render();
    }

    public boolean inEditorMode(){
        return stateManager.isEditor();
    }
    public boolean inSlideShowMode(){
        return stateManager.isSlideShow();
    }


    public void removeSlideViewSubscribers(){
        if(this.presentation==null) return;
        for(RuNode s:presentation.getChildren()){
            if(s instanceof Slide slide){
                if(slide.getSubscribers()!=null){
                    ArrayList<ISubscriber> forDeleting = new ArrayList<>();
                    for(ISubscriber subscriber:slide.getSubscribers()){
                        if(subscriber instanceof SlideView||subscriber instanceof SlidePreview||subscriber instanceof SlideShowView)
                            forDeleting.add(subscriber);
                    }
                    for(ISubscriber iSubscriber:forDeleting){
                        slide.removeSubscriber(iSubscriber);
                    }
                }
            }
        }
    }
    void render(){
        removeSlideViewSubscribers();
        this.removeAll();
        JPanel infoControlPanel = new JPanel();
        infoControlPanel.setLayout(new BorderLayout());
        JToolBar jToolBar = new JToolBar();

        infoControlPanel.add(jToolBar, BorderLayout.NORTH);
        this.add(infoControlPanel, BorderLayout.NORTH);
        if(inEditorMode()){
            this.remove(jPanelSlideshowView);
            this.add(mainView, BorderLayout.CENTER);
            //this.add(mainView, BorderLayout.CENTER);
            jToolBar.add(MainFrame.getInstance().getActionManager().getStartSlideshowModeAction());
            jToolBar.add(MainFrame.getInstance().getActionManager().getToggleAddSlotStateAction());
            jToolBar.add(MainFrame.getInstance().getActionManager().getToggleDeleteSlotStateAction());
            jToolBar.add(MainFrame.getInstance().getActionManager().getToggleMoveSlotStateAction());
            jToolBar.add(MainFrame.getInstance().getActionManager().getToggleResizeSlotStateAction());
            for(var comp:jToolBar.getComponents()){
                comp.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }
            infoControlPanel.add(authorPanel, BorderLayout.CENTER);
            if(presentation!=null){
                showPresentation();
            }
            this.repaint();
            this.revalidate();
        }
        else if(inSlideShowMode()){
            this.remove(mainView);
            this.add(jPanelSlideshowView, BorderLayout.CENTER);
            jToolBar.add(MainFrame.getInstance().getActionManager().getStartEditorModeAction());
            if(presentation!=null){
                slideShowPresentation();
            }
            this.repaint();
            this.revalidate();
        }
    }

    public PresentationView(){
        super();
        stateManager = new StateManager();
        setLayout(new BorderLayout());
        navigationComponentListener = new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                System.out.println(e.getComponent().getSize());
                if(!rsz&&65*presentation.getChildren().size()<e.getComponent().getSize().height){
                    navigationView.add(Box.createRigidArea(new Dimension(0, e.getComponent().getSize().height-65*presentation.getChildren().size())));
                    rsz = true;
                }
            }
        };
        presentation = null;
        authorPanel = new JPanel();
        authorLabel = new JLabel("Author name");
        mainView = new JPanel();
        mainView.setLayout(new BorderLayout());
        jPanelSlidesView = new SlideEditorScroller();
        jPanelSlideshowView = new SlideEditorScroller();

        navigationView = new JPanel();
        navigationView.addComponentListener(navigationComponentListener);
        navigationView.setLayout(new BoxLayout(navigationView, BoxLayout.Y_AXIS));
        jScrollPaneNavigation = new JScrollPane(navigationView,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        mainView.add(jPanelSlidesView, BorderLayout.CENTER);
        mainView.add(jScrollPaneNavigation, BorderLayout.WEST);
        //jScrollPaneNavigation.setPreferredSize(new Dimension(120, 0));
        authorPanel.add(authorLabel);

        render();
    }

    void showPresentation(){
        authorLabel.setText(presentation.getAuthor());
        jPanelSlidesView.removeAll();
        jPanelSlidesView.setSelectedIndex(0);
        navigationView.removeAll();
        /*var a = e->{

        };
        navigationView.removeComponentListener();*/
        int id = 0;
        for(RuNode node : presentation.getChildren()){
            if(node instanceof Slide slide){
                SlidePreview slidePreview = new SlidePreview();
                slide.addSubscriber(slidePreview);
                navigationView.add(slidePreview);
                navigationView.add(Box.createRigidArea(new Dimension(0, 5)));
                SlideView slideView = new SlideView(slidePreview);
                slide.addSubscriber(slideView);
                jPanelSlidesView.add(Integer.toString(id), slideView);
                slide.notifySubscribers(new PublisherChangedNotification(slide));
                id = id + 1;
            }

            System.out.println("vulkan");
            System.out.println();
            System.out.println(65*id);



        }
        if(65*presentation.getChildren().size()<navigationView.getSize().height){
            rsz = true;
            navigationView.add(Box.createRigidArea(new Dimension(0, navigationView.getSize().height-65*presentation.getChildren().size())));
        }
    }
    void slideShowPresentation(){
        jPanelSlideshowView.removeAll();
        jPanelSlideshowView.setSelectedIndex(0);
        int id = 0;
        for(RuNode node : presentation.getChildren()){
            if(node instanceof Slide slide){
                SlideShowView slideShowView = new SlideShowView();
                slide.addSubscriber(slideShowView);
                jPanelSlideshowView.add(Integer.toString(id), slideShowView);
                slide.notifySubscribers(new PublisherChangedNotification(slide));
                id = id + 1;
            }
        }
    }

    @Override
    public void update(Object notification) {
        if(notification instanceof PublisherDeletedNotification){
            authorLabel.setText("Please open a project to start working on it");
            jPanelSlidesView.removeAll();
            navigationView.removeAll();
        }
        if(notification instanceof PublisherChangedNotification publisherChangedNotification){
            if(publisherChangedNotification.getNewPublisher() instanceof Presentation){
                presentation = (Presentation) publisherChangedNotification.getNewPublisher();
                render();
            }
        }
    }

    public Presentation getPresentation() {
        return presentation;
    }

    public void setPresentation(Presentation presentation) {
        this.presentation = presentation;
    }

    public SlideEditorScroller getjPanelSlidesView() {
        return jPanelSlidesView;
    }

    public void setjPanelSlidesView(SlideEditorScroller jPanelSlidesView) {
        this.jPanelSlidesView = jPanelSlidesView;
    }

    public JPanel getNavigationView() {
        return navigationView;
    }

    public SlideEditorScroller getjPanelSlideshowView() {
        return jPanelSlideshowView;
    }

    public void setjPanelSlideshowView(SlideEditorScroller jPanelSlideshowView) {
        this.jPanelSlideshowView = jPanelSlideshowView;
    }

    public void setNavigationView(JPanel navigationView) {
        this.navigationView = navigationView;
    }
}
