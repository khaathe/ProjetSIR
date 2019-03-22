package nf;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PGM extends AbstractImage {


    public PGM(String numArchivage){
        super(numArchivage);
    }

    public void setImage (File file) {
        try (Scanner scanner = new Scanner(file) ) {
            if (!scanner.nextLine().equals("P2"))
                throw new IOException("mauvais format PGM");
            while (scanner.hasNext("#\\.*")) {
                scanner.nextLine();
            }
            int width = scanner.nextInt();
            int height = scanner.nextInt();
            int max = scanner.nextInt();
            BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            int pixel = 0;
            for (int h = 0; h < height; h++) {
                for (int w = 0; w < width; w++) {
                    pixel = scanner.nextInt();
                    if (!scanner.hasNextInt() && w < width - 1)
                        throw new IOException("Probleme fichier, donnees manquante");
                    else if (pixel < 0 || pixel > max)
                        throw new IOException("Probleme fichier, donnees corrompu");
                    else
                        img.setRGB(w, h, new Color(pixel, pixel, pixel).getRGB());
                }
            }
            this.image = img;
        } catch (IOException e){
            Logger.getAnonymousLogger().log(Level.WARNING, e.getMessage());
        }
    }

}
