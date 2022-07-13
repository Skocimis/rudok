package windows.dialogs;

import model.Presentation;
import model.Project;
import model.Slide;
import notifications.PublisherChangedNotification;
import observer.ISubscriber;
import windows.frames.MainFrame;

import javax.swing.*;
import java.io.File;

public class PresentationSettingsDialog extends SimpleDialog implements ISubscriber {
    public PresentationSettingsDialog(Presentation presentation) {
        super(null, "Presentation settings \""+presentation.getName()+"\"", true);
        PresentationSettingsDialog self = this;
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new BoxLayout(jPanel,BoxLayout.Y_AXIS));
        jPanel.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        jPanel.setAlignmentY(JComponent.CENTER_ALIGNMENT);

        JPanel jPanelAuthor = new JPanel();
        JLabel jLabelAuthor = new JLabel("Author: ");
        JTextField jTextFieldAuthor = new JTextField(presentation.getAuthor());
        jPanelAuthor.add(jLabelAuthor);
        jPanelAuthor.add(jTextFieldAuthor);
        jPanel.add(jPanelAuthor);
        JPanel jPanelTheme = new JPanel();
        JButton jButtonTheme = new JButton("Select theme");
        final String[] currentTheme = {presentation.getTheme()};
        jButtonTheme.addActionListener(e->{
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
            int result = fileChooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                currentTheme[0] = selectedFile.getAbsolutePath();
            }
        });
        jPanelTheme.add(jButtonTheme);
        /*JLabel jLabelTheme = new JLabel("Theme: ");
        JTextField jTextFieldTheme = new JTextField(presentation.getTheme());
        jPanelTheme.add(jLabelTheme);
        jPanelTheme.add(jTextFieldTheme);*/
        jPanel.add(jPanelTheme);

        JButton button = new JButton("OK");
        button.addActionListener(actionEvent->{
            if(!presentation.getTheme().equals(currentTheme[0])||!presentation.getAuthor().equals(jTextFieldAuthor.getText())){
                ((Project)presentation.getParent()).setEdited(true);
                SwingUtilities.updateComponentTreeUI(MainFrame.getInstance());
            }
            presentation.setAuthor(jTextFieldAuthor.getText());
            presentation.setTheme(currentTheme[0]);
            presentation.notifySelfAndParentSubscribers(new PublisherChangedNotification(presentation));
            self.dispose();
        });
        jPanel.add(button);
        add(jPanel);
    }

    @Override
    public void update(Object notification) {

    }
}
