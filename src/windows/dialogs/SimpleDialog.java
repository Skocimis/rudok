package windows.dialogs;

import observer.ISubscriber;

import javax.swing.*;
import java.awt.*;

public class SimpleDialog extends JDialog implements ISubscriber {

    public SimpleDialog(Frame parent, String title, boolean modal){
        super(parent, title, modal);

        setSize(250,250);
        setLocationRelativeTo(parent);



    }

    @Override
    public void update(Object notification) {

    }
}