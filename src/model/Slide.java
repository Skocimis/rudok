package model;

import model.rutree.RuNode;
import model.rutree.RuNodeC;

import java.util.ArrayList;
import java.util.List;

public class Slide extends RuNode {
    private Presentation presentation;
    private List<Slot> slots;
    private int id;

    @Override
    public String toString() {
        return "Slide";
    }

    public Slide(String name, RuNodeC parent, List<Slot> slots) {
        super(name, parent);
        this.slots = slots;
    }
    public Slide(String name, RuNodeC parent) {
        super(name, parent);
        this.slots = new ArrayList<>();
    }

    public List<Slot> getSlots() {
        return slots;
    }

    public void setSlots(List<Slot> slots) {
        this.slots = slots;
    }
    public void addSlot(Slot slot){
        this.slots.add(slot);
    }
}
