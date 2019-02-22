package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ImagePanel extends JPanel {

    private BufferedImage img;

    public ImagePanel(BufferedImage img){
        this.img = img;
    }

    public void setImg (BufferedImage img) { this.img = img; }

    public void paintComponent (Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(this.img, 0, 0, this.getWidth(), this.getHeight(), null);
    }

}
