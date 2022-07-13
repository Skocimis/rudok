package components;

import model.slots.ImageSlot;
import javax.swing.*;
import java.awt.*;
import java.io.File;

public class ImageSlotEditor extends SlotEditor{
    JPanel imageContainer;
    ImagePanel imagePanel;
    JButton chooseFile;
    String imageUrl;
    public ImageSlotEditor(ImageSlot imageSlot){
        super();
        imageUrl = imageSlot.getImage();
        render();
    }
    private void render(){
        removeAll();
        ImageSlotEditor self = this;
        imageContainer = new JPanel();
        imageContainer.setLayout(new BorderLayout());
        imageContainer.setMinimumSize(new Dimension(50, 50));
        imageContainer.setPreferredSize(new Dimension(50, 50));
        imageContainer.setMaximumSize(new Dimension(50, 50));

        imagePanel = new ImagePanel(imageUrl);
        imageContainer.add(imagePanel);
        add(imageContainer, BorderLayout.CENTER);

        chooseFile = new JButton("Choose file");
        chooseFile.addActionListener(e->{
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")+ "/Desktop"));
            int result = fileChooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                self.imageUrl = selectedFile.getAbsolutePath();
                System.out.println("NOVI URL: "+self.imageUrl);
                render();
            }
        });
        add(chooseFile, BorderLayout.SOUTH);
        /*fileChooser = new JFileChooser();
        fileChooser.addActionListener(e->{
            System.out.println("ODABRAN FJL");
            render();
        });
        add(fileChooser, BorderLayout.SOUTH);*/
        repaint();
        revalidate();
    }

    @Override
    public String exportContent() {
        return imageUrl;
    }
}
