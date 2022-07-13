package graphics;

import java.awt.*;
import java.io.Serializable;

public class MyStroke implements Serializable {
    boolean dashed;
    int width;
    public MyStroke(boolean dashed, int width){
        this.dashed = dashed;
        this.width = width;
    }
    public MyStroke(MyStroke stroke){
        this.dashed = stroke.dashed;
        this.width = stroke.width;
    }
    public Stroke getStroke(){
        if(dashed){
            return new BasicStroke(width, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL,
                    0, new float[]{9}, 0);
        }
        else{
            return new BasicStroke(width);
        }
    }

    public boolean isDashed() {
        return dashed;
    }

    public void setDashed(boolean dashed) {
        this.dashed = dashed;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }
}
