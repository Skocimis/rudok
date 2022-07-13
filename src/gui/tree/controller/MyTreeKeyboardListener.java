package gui.tree.controller;

import gui.tree.view.MyTree;
import main.Main;
import windows.frames.MainFrame;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MyTreeKeyboardListener implements KeyListener {
    public static MyTreeKeyboardListener instance;
    private MyTree tree;
    public MyTreeKeyboardListener(MyTree tree){
        instance = this;
        this.tree = tree;
    }
    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    public void keyPressed(int keyEvent) {
        if (keyEvent==2){
            tree.setRenameMode(true);
        }
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        if (keyEvent.getKeyCode()==KeyEvent.VK_F2){
            tree.setRenameMode(true);
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }
}
