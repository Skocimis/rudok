package components;

import javax.swing.*;
import java.awt.*;

public abstract class SlotEditor extends JPanel {
    public SlotEditor(){
        setLayout(new BorderLayout());
    }

    public abstract String exportContent();
}
