package windows.frames;

import actions.ActionManager;
import commands.CommandManager;
import database.Database;
import graphics.MyStroke;
import gui.ProjectView;
import gui.tree.view.MyTree;
import gui.tree.model.MyTreeModel;
import gui.Toolbar;

import javax.swing.*;
import gui.Menu;
import model.Project;
import model.rutree.RuNode;
import notifications.UserErrorNotification;
import observer.ISubscriber;
import usererror.UserErrorFactory;
import windows.dialogs.SlotEditorDialog;
import windows.dialogs.UserErrorDialog;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class MainFrame extends JFrame implements ISubscriber {
    private static MainFrame instance;
    private ActionManager actionManager;
    private MyTree myTree;
    private MyTreeModel myTreeModel;
    private Object myTreeSelectedValue;
    private ProjectView projectView;
    private UserErrorFactory userErrorFactory;
    private UserErrorDialog userErrorDialog;
    private Color slotDefaultColor = Color.black;
    private Color slotDefaultBackgroundColor = new Color(1f,0f,0f,.5f );
    private MyStroke slotDefaultStroke = new MyStroke(false, 1);
    private CommandManager commandManager;
    private SlotEditorDialog slotEditorDialog;

    private MainFrame(){
    }

    private void initialise(){
        actionManager = new ActionManager();
        commandManager = new CommandManager();
        userErrorFactory = new UserErrorFactory();
        userErrorFactory.addSubscriber(this);
        userErrorDialog = new UserErrorDialog();
        slotEditorDialog = new SlotEditorDialog();

    }

    private void initialiseGUI(){

        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;

        setSize(screenWidth/2, screenHeight/2);
        Image img = kit.getImage("images/Slika.png");
        setIconImage(img);

        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setTitle("RuDok");
        WindowListener exitListener = new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                boolean allsaved = true;
                if(Database.getInstance().getActiveWorkspace().isEdited()) allsaved = false;
                for(RuNode node:Database.getInstance().getActiveWorkspace().getChildren()){
                    if(node instanceof Project project){
                        if(project.isEdited()){
                            allsaved = false;
                        }
                    }
                }
                if(allsaved){
                    System.exit(0);
                    return;
                }
                else{
                    int confirm = JOptionPane.showOptionDialog(
                            null, "Some projects or workspace have not been saved, do you want to continue? ",
                            "Exit Confirmation", JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE, null, null, null);
                    if (confirm == 0) {
                        System.exit(0);
                    }
                }
            }
        };
        addWindowListener(exitListener);

        Menu menu = new Menu();
        setJMenuBar(menu);

        Toolbar toolBar = new Toolbar();
        add(toolBar, BorderLayout.NORTH);

        initialiseTree();
        JScrollPane jScrollPane = new JScrollPane(myTree);
        jScrollPane.setPreferredSize(new Dimension(300, 0));
        SwingUtilities.updateComponentTreeUI(this);

        projectView = new ProjectView();

        JSplitPane jSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,jScrollPane, projectView);
        add(jSplitPane);
        instance.initialiseTree();
    }
    public void initialiseTree(){
        myTreeModel = new MyTreeModel();
        myTree=new MyTree(myTreeModel);
    }

    public static MainFrame getInstance(){
        if(instance==null){
            instance = new MainFrame();
            instance.initialise();
            instance.initialiseGUI();
        }
        return instance;
    }

    public ActionManager getActionManager() {
        return actionManager;
    }

    public void setActionManager(ActionManager actionManager) {
        this.actionManager = actionManager;
    }

    public MyTree getMyTree() {
        return myTree;
    }

    public void setMyTree(MyTree myTree) {
        this.myTree = myTree;
    }

    public MyTreeModel getMyTreeModel() {
        return myTreeModel;
    }

    public void setMyTreeModel(MyTreeModel myTreeModel) {
        this.myTreeModel = myTreeModel;
    }

    public Object getMyTreeSelectedValue() {
        return myTreeSelectedValue;
    }

    public void setMyTreeSelectedValue(Object myTreeSelectedValue) {
        this.myTreeSelectedValue = myTreeSelectedValue;
    }

    public ProjectView getProjectView() {
        return projectView;
    }

    public void setProjectView(ProjectView projectView) {
        this.projectView = projectView;
    }

    @Override
    public void update(Object notification) {
        if(notification instanceof UserErrorNotification userErrorNotification){
            userErrorDialog.showDialog(userErrorNotification.getUserError());
        }
    }

    public UserErrorFactory getUserErrorFactory() {
        return userErrorFactory;
    }

    public void setUserErrorFactory(UserErrorFactory userErrorFactory) {
        this.userErrorFactory = userErrorFactory;
    }

    public Color getSlotDefaultColor() {
        return slotDefaultColor;
    }

    public void setSlotDefaultColor(Color slotDefaultColor) {
        this.slotDefaultColor = slotDefaultColor;
    }

    public MyStroke getSlotDefaultStroke() {
        return slotDefaultStroke;
    }

    public void setSlotDefaultStroke(MyStroke slotDefaultStroke) {
        this.slotDefaultStroke = slotDefaultStroke;
    }

    public Color getSlotDefaultBackgroundColor() {
        return slotDefaultBackgroundColor;
    }

    public void setSlotDefaultBackgroundColor(Color slotDefaultBackgroundColor) {
        this.slotDefaultBackgroundColor = slotDefaultBackgroundColor;
    }

    public CommandManager getCommandManager() {
        return commandManager;
    }

    public void setCommandManager(CommandManager commandManager) {
        this.commandManager = commandManager;
    }

    public SlotEditorDialog getSlotEditorDialog() {
        return slotEditorDialog;
    }

    public void setSlotEditorDialog(SlotEditorDialog slotEditorDialog) {
        this.slotEditorDialog = slotEditorDialog;
    }
}
