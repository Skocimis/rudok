package model.slots;

import graphics.MyStroke;
import model.Slot;
import vector.Dimension2D;
import vector.Point2D;

import javax.swing.*;
import java.awt.*;

public class ImageSlot extends Slot {
    private String image = "images/empty.png";
    @Override
    public void setContent(String content) {
        this.image = content;
    }

    public ImageSlot() {
    }

    public ImageSlot(Point2D position, Dimension2D size, Color color, Color backgroundColor, MyStroke stroke) {
        super(position, size, color, backgroundColor, stroke);
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
