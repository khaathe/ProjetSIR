package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ImagePanel extends JPanel {

    private transient BufferedImage img;

    /**
     * Constructeur de la classe prenant en parametre la BufferedImage qui s'affichera dans le Panel.
     *     Initialisation de cet image
     * @param img
     * L'image a afficher dans le panel
     */

    public ImagePanel(BufferedImage img){
        this.img = img;
    }

    public void setImg (BufferedImage img) {
        this.img = img;
        this.repaint();
    }

    public BufferedImage getImg(){ return img; }


    /**
     * Methode permettant d'optimiser l'affichage de l'image dans le Panel
     * Permet de calculer un ratio horizontal et un vertical pour mieux gerer la place de l'image dans le panel
     * @param g
     *
     */
    @Override

    public void paintComponent (Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.WHITE);
        g2.fillRect(0, 0, this.getWidth(), this.getHeight());
        if (img != null){
            double horizontalRatio = (double) this.getWidth()/img.getWidth();
            double verticalRatio = (double) this.getHeight()/img.getHeight();
            if ( horizontalRatio > verticalRatio )
                g2.drawImage(img, (int) (this.getWidth() - img.getWidth() * verticalRatio ) /2, 5, (int) (img.getWidth() * verticalRatio), (int) (img.getHeight() * verticalRatio), null);
            else
                g2.drawImage(img, 5,5, (int) (img.getWidth() * horizontalRatio), (int) (img.getHeight() * horizontalRatio), null );

        }
    }

}
