package nf;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Image extends AbstractImage {

    /**
     * Constructeur de la classe héritant du constructeur de la mère abstraite AbstractImage
     * @param numArchivage
     * Numéro d'archivage de l'image à considérer
     */
    public Image (String numArchivage){
        super(numArchivage);
    }


    /**
     * Méthode permettant à la classe de lire l'image choisie en lui donnant le chemin à suivre pour la trouver
     * @param file
     * Chemin pour trouver l'image considérée
     */

    public void setImage (File file) {
        try {
            this.image = ImageIO.read(file);
        } catch (IOException e) {
            Logger.getAnonymousLogger().log(Level.WARNING, e.getMessage());
        }

    }
}
