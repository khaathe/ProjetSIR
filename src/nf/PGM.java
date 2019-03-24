package nf;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Scanner;

public class PGM extends AbstractImage {


    public PGM(String numArchivage){
        super(numArchivage);
    }

    //Méthode permettant de lire une image PGM en indiquant le fichier dans lequel elle se trouve
    //Utilise la classe Scanner, en initialisant un attribut scanner avec le fichier indiqué en paramètre.
    //Vérifie que scanner lit bien toutes les lignes du fichier image PGM.
    //Récupère les attributs de l'image PGM lue dans des int width, height et max et s'en sert initialiser une  nouvelle image de type BufferedImage RGB.
    //S'assure que l'ensemble des éléments seront bien retrouvés dans la nouvelle image à l'aide de boucles for parcourant la largeur et la hauteur du fichier PGM lu.
    //Suit 2 conditions d'erreur fichier, à savoir, que l'attribut scanner ne détecte pas d'int suivant alors que le parcours de width n'est pourtant pas terminé
    //Ou bien que l'attribut pixel initialisé à 0 et servant à la création de la nouvelle image, se retrouve < 0 ou > max de int du fichier PGM
    public void setImage (File file) throws Exception {
        Scanner scanner = new Scanner(file);
        if ( !scanner.nextLine().equals("P2") )
            throw new Exception("mauvais format PGM");
        while(scanner.hasNext("#\\.*")){
            scanner.nextLine();
        }
        int width = scanner.nextInt();
        int height = scanner.nextInt();
        int max = scanner.nextInt();
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        int pixel = 0;
        for ( int h=0; h<height; h++){
            for ( int w=0; w<width; w++){
                pixel = scanner.nextInt();
                if ( !scanner.hasNextInt() && w < width-1)
                    throw new Exception("Probleme fichier, donnees manquante");
                else if (pixel < 0 || pixel > max)
                    throw new Exception("Probleme fichier, donnees corrompu");
                else
                    img.setRGB(w, h, new Color(pixel, pixel, pixel).getRGB() );
            }
        }
        scanner.close();
        this.image = img;
    }

}
