package components;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

public class ImagePanel extends JPanel {

    private Image img;

    public ImagePanel(String img) {
        this(new ImageIcon(img).getImage());
        setLayout(new BorderLayout());
    }

    public ImagePanel(Image img) {
        this.img = img;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(img, 0, 0, getPreferredSize().width, getPreferredSize().height,  null);
    }
    @Override
    public Dimension getPreferredSize()
    {
        return new Dimension(getParent().getSize().width, getParent().getSize().height);
    }
}