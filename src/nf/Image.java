package nf;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Image extends AbstractImage {

    /**
     * Constructeur de la classe heritant du constructeur de la mere abstraite AbstractImage
     * @param numArchivage
     * Numero d'archivage de l'image a considerer
     */
    public Image (String numArchivage){
        super(numArchivage);
    }


    /**
     * Methode permettant a la classe de lire l'image choisie en lui donnant le chemin a suivre pour la trouver
     * @param file
     * Chemin pour trouver l'image consideree
     */

    public void setImage (File file) {
        try {
            this.image = ImageIO.read(file);
        } catch (IOException e) {
            Logger.getAnonymousLogger().log(Level.WARNING, e.getMessage());
        }

    }
}
