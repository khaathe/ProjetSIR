package nf;

import javax.imageio.ImageIO;
import java.io.File;

public class Image extends AbstractImage {

    //Constructeur de la classe héritant du constructeur de la mère abstraite AbstractImage
    public Image (String numArchivage){
        super(numArchivage);
    }

    //Méthode permettant à la classe de lire l'image choisie en lui donnant le
    public void setImage (File file) throws Exception{
        this.image = ImageIO.read(file);
    }
}
