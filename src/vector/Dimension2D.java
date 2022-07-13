package vector;

import java.io.Serializable;

public class Dimension2D implements Serializable {
    private double width;
    private double height;

    public Dimension2D(double width, double height){
        this.width = width;
        this.height = height;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }
}
