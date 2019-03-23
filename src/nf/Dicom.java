package nf;

import java.io.File;

public class Dicom extends AbstractImage {

    public Dicom (String numArchivage){
        super(numArchivage);
    }

    @Override
    public void setImage(File file) {
        //Non implemente pour cause d'un bug inconnu
        //empechant l'utilisation de la methodes ImageIO.read()
        //avec un ByteArrayInputStream()
        //cette methode est utilisee pour recuperer les images de la base de donnees
    }
}