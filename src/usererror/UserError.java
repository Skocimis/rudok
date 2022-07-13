package usererror;

import components.ImagePanel;

import javax.swing.*;
import java.awt.*;

public abstract class UserError {
    private String text;

    public UserError(String text){
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
