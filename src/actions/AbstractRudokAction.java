package actions;

import javax.swing.*;
import java.net.URL;

public abstract class AbstractRudokAction extends AbstractAction {
    public Icon loadIcon(String fileName){
        return new ImageIcon(fileName);
    }
}
