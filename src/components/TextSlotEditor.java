package components;

import model.slots.TextSlot;

import javax.swing.*;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import javax.swing.text.html.HTMLDocument;
import java.awt.*;

public class TextSlotEditor extends SlotEditor{
    JTextPane jTextPane;
    boolean bold;
    boolean italic;
    boolean underline;
    JButton jButtonBold;
    JButton jButtonItalic;
    JButton jButtonUnderline;
    JPanel jPanelButtons;
    String typedText;
    StyledDocument styledDocument;
    public TextSlotEditor(TextSlot textSlot){
        super();
        typedText = textSlot.getText();
        bold = textSlot.isBold();
        italic = textSlot.isItalic();
        underline = textSlot.isUnderline();
        render();
    }
    public void render(){
        //remove(jPanelButtons);
        //remove(jTextPane);
        if(jTextPane!=null){
            System.out.println("NIJENULL");
            typedText = jTextPane.getText();
        }
        removeAll();
        TextSlotEditor self = this;
        jPanelButtons = new JPanel();
        jPanelButtons.setLayout(new BorderLayout());
        jButtonBold = new JButton(bold?"No bold":"Bold");
        jButtonBold.addActionListener(e->{
            self.bold = !self.bold;
            self.render();
        });
        jButtonItalic = new JButton(italic?"No italic":"Italic");
        jButtonItalic.addActionListener(e->{
            self.italic = !self.italic;
            self.render();
        });
        jButtonUnderline = new JButton(underline?"No underline":"Underline");
        jButtonUnderline.addActionListener(e->{
            self.underline = !self.underline;
            self.render();
        });
        jPanelButtons.add(jButtonBold, BorderLayout.WEST);
        jPanelButtons.add(jButtonItalic, BorderLayout.CENTER);
        jPanelButtons.add(jButtonUnderline, BorderLayout.EAST);

        jTextPane = new JTextPane();
        SimpleAttributeSet attributeSet = new SimpleAttributeSet();
        StyleConstants.setBold(attributeSet, bold);
        StyleConstants.setItalic(attributeSet, italic);
        StyleConstants.setUnderline(attributeSet, underline);
        styledDocument = new DefaultStyledDocument();
        jTextPane.setStyledDocument(new DefaultStyledDocument());
        jTextPane.setCharacterAttributes(attributeSet, true);
        add(jPanelButtons, BorderLayout.NORTH);
        add(jTextPane, BorderLayout.CENTER);
        jTextPane.setText(typedText);
        repaint();
        revalidate();
    }

    @Override
    public String exportContent() {
        return (bold?"1":"0")+(italic?"1":"0")+(underline?"1":"0")+jTextPane.getText();
    }
}
