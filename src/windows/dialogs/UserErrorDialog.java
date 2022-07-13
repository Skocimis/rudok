package windows.dialogs;

import usererror.UserError;

import javax.swing.*;
import java.awt.*;

public class UserErrorDialog extends JDialog {
    private JLabel errorLabel;
    private JButton dismissButton;
    private JPanel jPanel;
    public UserErrorDialog(){
        super((Frame) null, "Error", true);
        UserErrorDialog self = this;
        setSize(250,150);
        setLocationRelativeTo(null);

        jPanel = new JPanel();
        errorLabel = new JLabel("Error");
        dismissButton = new JButton("OK");
        jPanel.setLayout(new BoxLayout(jPanel,BoxLayout.Y_AXIS));
        jPanel.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        jPanel.setAlignmentY(JComponent.CENTER_ALIGNMENT);
        add(jPanel);
        jPanel.add(errorLabel);
        dismissButton.addActionListener((actionEvent)-> self.setVisible(false));
        jPanel.add(dismissButton);
    }

    public void showDialog(UserError userError){
        errorLabel.setText(userError.getText());
        this.setVisible(true);

    }

    public JLabel getErrorLabel() {
        return errorLabel;
    }

    public void setErrorLabel(JLabel errorLabel) {
        this.errorLabel = errorLabel;
    }

    public JButton getDismissButton() {
        return dismissButton;
    }

    public void setDismissButton(JButton dismissButton) {
        this.dismissButton = dismissButton;
    }

    public JPanel getjPanel() {
        return jPanel;
    }

    public void setjPanel(JPanel jPanel) {
        this.jPanel = jPanel;
    }
}
