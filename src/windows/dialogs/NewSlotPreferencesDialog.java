package windows.dialogs;

import graphics.MyStroke;
import main.Main;
import model.Presentation;
import notifications.PublisherChangedNotification;
import observer.ISubscriber;
import windows.frames.MainFrame;

import javax.swing.*;
import java.awt.*;

public class NewSlotPreferencesDialog extends SimpleDialog implements ISubscriber {
    public NewSlotPreferencesDialog() {
        super(null, "New slot preferences", true);
        NewSlotPreferencesDialog self = this;
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new BoxLayout(jPanel,BoxLayout.Y_AXIS));
        jPanel.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        jPanel.setAlignmentY(JComponent.CENTER_ALIGNMENT);

        JPanel jPanelColor = new JPanel();
        final Color[] lineColor = {MainFrame.getInstance().getSlotDefaultColor()};
        JButton lineColorButton = new JButton("Choose line color");
        lineColorButton.setBackground(new Color(lineColor[0].getRed(), lineColor[0].getGreen(), lineColor[0].getBlue()));
        lineColorButton.setForeground(new Color(255-lineColor[0].getRed(), 255-lineColor[0].getGreen(), 255-lineColor[0].getBlue()));
        lineColorButton.addActionListener(e->{
            Color color = JColorChooser.showDialog(null, "Choose a color", lineColor[0]);
            if(color!=null){
                lineColor[0] = color;
                lineColorButton.setBackground(new Color(lineColor[0].getRed(), lineColor[0].getGreen(), lineColor[0].getBlue()));
                lineColorButton.setForeground(new Color(255-lineColor[0].getRed(), 255-lineColor[0].getGreen(), 255-lineColor[0].getBlue()));
            }
        });
        jPanelColor.add(lineColorButton);
        jPanel.add(jPanelColor);

        JPanel jPanelBgColor = new JPanel();
        final Color[] backgroundColor = {MainFrame.getInstance().getSlotDefaultBackgroundColor()};
        JButton backgroundColorButton = new JButton("Choose background color");
        backgroundColorButton.setBackground(new Color(backgroundColor[0].getRed(), backgroundColor[0].getGreen(), backgroundColor[0].getBlue()));
        backgroundColorButton.setForeground(new Color(255-backgroundColor[0].getRed(), 255-backgroundColor[0].getGreen(), 255-backgroundColor[0].getBlue()));
        backgroundColorButton.addActionListener(e->{
            Color color = JColorChooser.showDialog(null, "Choose a color", backgroundColor[0]);
            if(color!=null){
                backgroundColor[0] = color;
                backgroundColorButton.setBackground(new Color(backgroundColor[0].getRed(), backgroundColor[0].getGreen(), backgroundColor[0].getBlue()));
                backgroundColorButton.setForeground(new Color(255-backgroundColor[0].getRed(), 255-backgroundColor[0].getGreen(), 255-backgroundColor[0].getBlue()));
            }
        });
        jPanelBgColor.add(backgroundColorButton);
        jPanel.add(jPanelBgColor);


        JPanel jPanelWidth = new JPanel();
        JLabel jLabelWidth = new JLabel("Width: ");
        JTextField jTextFieldWidth = new JTextField(Integer.toString(MainFrame.getInstance().getSlotDefaultStroke().getWidth()));
        jTextFieldWidth.setPreferredSize(new Dimension(70, 20));
        jPanelWidth.add(jLabelWidth);
        jPanelWidth.add(jTextFieldWidth);
        jPanel.add(jPanelWidth);


        JPanel jPanelDash = new JPanel();
        JLabel jLabelDash = new JLabel("Dashed: ");
        JCheckBox jCheckBoxDash = new JCheckBox();
        jPanelDash.add(jLabelDash);
        jPanelDash.add(jCheckBoxDash);
        jCheckBoxDash.setSelected(MainFrame.getInstance().getSlotDefaultStroke().isDashed());
        jPanel.add(jPanelDash);


        JButton button = new JButton("OK");
        button.addActionListener(actionEvent->{
            //BasicStroke stroke = (BasicStroke) MainFrame.getInstance().getSlotDefaultStroke();
            //TODO MOGUCI ERROR
            MainFrame.getInstance().setSlotDefaultColor(lineColor[0]);
            MainFrame.getInstance().setSlotDefaultBackgroundColor(backgroundColor[0]);
            MainFrame.getInstance().setSlotDefaultStroke(new MyStroke(jCheckBoxDash.isSelected(), Integer.parseInt(jTextFieldWidth.getText())));
            self.dispose();
        });
        jPanel.add(button);
        add(jPanel);
    }

    @Override
    public void update(Object notification) {

    }
}
