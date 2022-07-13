package windows.dialogs;

import components.ImagePanel;

import javax.swing.*;

public class InfoDialog extends SimpleDialog {
    public InfoDialog() {
        super(null, "Prozor", true);
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new BoxLayout(jPanel,BoxLayout.Y_AXIS));
        jPanel.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        jPanel.setAlignmentY(JComponent.CENTER_ALIGNMENT);
        add(jPanel);
        JLabel lbl = new JLabel("Aleksandar Spremo RN 11/2020");
        ImagePanel imagePanel = new ImagePanel("images/Ja.png");
        jPanel.add(lbl);
        jPanel.add(imagePanel);
    }
}
