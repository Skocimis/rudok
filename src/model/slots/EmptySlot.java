package model.slots;

import graphics.MyStroke;
import model.Slot;
import vector.Dimension2D;
import vector.Point2D;

import java.awt.*;

public class EmptySlot extends Slot {

    @Override
    public void setContent(String content) {

    }

    public EmptySlot() {
    }

    public EmptySlot(Point2D position, Dimension2D size, Color color, Color backgroundColor, MyStroke stroke) {
        super(position, size, color, backgroundColor, stroke);
    }
}
