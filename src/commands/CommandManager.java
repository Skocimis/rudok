package commands;

import main.Main;
import windows.frames.MainFrame;

import java.util.ArrayList;

public class CommandManager {
    private ArrayList<AbstractCommand> commands = new ArrayList<>();
    int currentCommand = 0;

    public CommandManager(){
        MainFrame.getInstance().getActionManager().getRedoAction().setEnabled(false);
        MainFrame.getInstance().getActionManager().getUndoAction().setEnabled(false);
    }

    public void addCommand(AbstractCommand command){
        while (currentCommand<commands.size()){
            commands.remove(currentCommand);
        }
        commands.add(command);
        doCommand();
    }
    public void doCommand(){
        if(currentCommand<commands.size()){
            commands.get(currentCommand++).doCommand();

            MainFrame.getInstance().getActionManager().getUndoAction().setEnabled(true);
        }
        if(currentCommand==commands.size()){
            MainFrame.getInstance().getActionManager().getRedoAction().setEnabled(false);
        }
    }
    public void undoCommand(){
        if(currentCommand>0){
            MainFrame.getInstance().getActionManager().getRedoAction().setEnabled(true);
            commands.get(--currentCommand).undoCommand();
        }
        if(currentCommand==0){
            MainFrame.getInstance().getActionManager().getUndoAction().setEnabled(false);
        }
    }
}
