package gui;

import model.Slot;

import javax.swing.*;
import java.awt.*;

public abstract class SlotView extends JPanel {
    private Slot slot;

    public Slot getSlot() {
        return slot;
    }

    public void setSlot(Slot slot) {
        this.slot = slot;
    }

    public abstract void paint(Graphics g);
}
