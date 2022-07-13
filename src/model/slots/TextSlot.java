package model.slots;

import graphics.MyStroke;
import model.Slot;
import vector.Dimension2D;
import vector.Point2D;

import java.awt.*;

public class TextSlot extends Slot {
    private String text = "";
    private boolean bold=false;
    private boolean italic=false;
    private boolean underline=false;
    @Override
    public void setContent(String content) {
        this.bold=content.charAt(0)=='1';
        this.italic=content.charAt(1)=='1';
        this.underline=content.charAt(2)=='1';
        this.text = content.substring(3);
    }

    public TextSlot() {
    }

    public TextSlot(Point2D position, Dimension2D size, Color color, Color backgroundColor, MyStroke stroke) {
        super(position, size, color, backgroundColor, stroke);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isBold() {
        return bold;
    }

    public void setBold(boolean bold) {
        this.bold = bold;
    }

    public boolean isItalic() {
        return italic;
    }

    public void setItalic(boolean italic) {
        this.italic = italic;
    }

    public boolean isUnderline() {
        return underline;
    }

    public void setUnderline(boolean underline) {
        this.underline = underline;
    }
}
