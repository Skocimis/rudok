package windows.dialogs;

import commands.RenameNodeCommand;
import model.rutree.RuNode;
import notifications.PublisherChangedNotification;
import windows.frames.MainFrame;

import javax.swing.*;
import java.awt.*;

public class RenameNodeDialog extends SimpleDialog {
    public RenameNodeDialog(RuNode ruNode) {
        super(null, "Rename node \""+ruNode.getName()+"\"", true);
        RenameNodeDialog self = this;

        JPanel jPanel = new JPanel();
        jPanel.setLayout(new FlowLayout());
        jPanel.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        jPanel.setAlignmentY(JComponent.CENTER_ALIGNMENT);

        add(jPanel);
        JLabel lbl = new JLabel("New name: ");
        JTextField jTextField = new JTextField();
        jTextField.setText(ruNode.getName());
        JButton jButton = new JButton("OK");
        jButton.addActionListener(actionEvent -> {
            String newname = jTextField.getText();
            if(newname.length()==0){
                MainFrame.getInstance().getUserErrorFactory().createUserError("RENAMETONULL");
            }
            else{
                MainFrame.getInstance().getCommandManager().addCommand(new RenameNodeCommand(ruNode, newname));
                self.dispose();
            }
        });

        jPanel.add(lbl);
        jPanel.add(jTextField);
        jPanel.add(jButton);


    }

}
