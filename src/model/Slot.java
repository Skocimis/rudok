package model;

import graphics.MyStroke;
import vector.Dimension2D;
import vector.Point2D;

import javax.swing.border.StrokeBorder;
import java.awt.*;
import java.io.Serializable;

public abstract class Slot implements Serializable {
    private Point2D position;
    private Dimension2D size;
    private Color color;
    private  Color backgroundColor;
    private MyStroke stroke;


    public abstract void setContent(String content);
    public Slot(){
        this.position = new Point2D(0, 0);
        this.size = new Dimension2D(0.1, 0.1);
        this.color = Color.black;
        this.stroke = new MyStroke(false, 1);
    }

    public Slot(Point2D position, Dimension2D size, Color color, Color backgroundColor, MyStroke stroke) {
        this.position = position;
        this.size = size;
        this.color = color;
        this.backgroundColor = backgroundColor;
        this.stroke = stroke;
    }

    public Point2D getPosition() {
        return position;
    }

    public void setPosition(Point2D position) {
        this.position = position;
    }

    public Dimension2D getSize() {
        return size;
    }

    public void setSize(Dimension2D size) {
        this.size = size;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Stroke getGraphicStroke(){
        return stroke.getStroke();
    }

    public MyStroke getStroke() {
        return stroke;
    }

    public void setStroke(MyStroke stroke) {
        this.stroke = stroke;
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }
}
