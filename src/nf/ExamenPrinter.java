package nf;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


public class ExamenPrinter implements Printable {
    private Examen examen;
    private int lastPage;
    private int indexCr;
    private int indexImage;
    private Patient patient;
    private  PersonnelServiceRadio practicien;
    private SimpleDateFormat dateFormat;
    private boolean crImprimer;
    private int oldPageIndex;
    private List<AbstractImage> listeImage;
    private int margeX;
    private int margeY;

    private  static String prenom = "Prenom";
    private static String police = "Serif";
    private static final Font TITLE = new Font(police, Font.PLAIN, 24);
    private static final Font SUBTITLE = new Font(police, Font.PLAIN, 18);
    private static final Font TEXT = new Font(police, Font.PLAIN, 12);

    public ExamenPrinter (Examen examen){
        this.examen = examen;
        patient = examen.getPatient();
        practicien = examen.getPraticien();
        indexCr = 0;
        indexImage = 0;
        oldPageIndex = 0;
        crImprimer = false;
        listeImage = examen.getImages();
        dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    }

    @Override
    /**
     * Methode appele par la classe job printer.
     * L'entier y correspond a la hauteur courrante. Cette variable est utilisee pour calculer
     * la hauteur y a laquelle sera dessine le String.
     * La premiere page sert a l'impression des infos patientes et du practicien. Une partie du compte rendu est aussi imprime.
     * Si le compte-rendu ne tient pas sur une page, celui-ci est imprime sur une autre page.
     * Un indice est utilise pour permettre de sauvegarder l'avancement dans l'etat du compte rendu.
     * Pour imprimer les differentes parties d'un examen trois methodes sont appelees : printInfoPatientPraticien, printCR et printImage
     *
     * @param graphics
     *      graphique de la page
     * @param pageFormat
     *      format de la page
     * @param pageIndex
     *      index de la page en cours
     * @return si la page existe ou non
     * @throws PrinterException
     */
    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
        int res = Printable.NO_SUCH_PAGE;
        Graphics2D g2 = (Graphics2D) graphics;
        margeX = (int) pageFormat.getImageableWidth()/20;
        margeY = (int) pageFormat.getImageableHeight()/20;
        int y = margeY;
        int w = (int) pageFormat.getImageableWidth();
        int h = (int) pageFormat.getImageableHeight();
        g2.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
        if (pageIndex == 0) {
            y = printInfoPatientPracticien(g2, y, w);
            indexCr = 0;
            printCR(g2, y, w, h, indexCr);
            lastPage = pageIndex + 1 + listeImage.size();
            oldPageIndex = pageIndex;
            res = Printable.PAGE_EXISTS;
        }
        else if ( !crImprimer ){
            indexCr = printCR(g2, y, w, h, indexCr);
            lastPage = pageIndex +1 + listeImage.size();
            oldPageIndex = pageIndex;
            res = Printable.PAGE_EXISTS;
        }
        else if ( listeImage != null && !listeImage.isEmpty() && pageIndex<lastPage ){
            printImage(g2, y, w, h);
            res = Printable.PAGE_EXISTS;
            if (pageIndex > oldPageIndex && oldPageIndex>0 && indexImage < listeImage.size()-1)
                indexImage++;
            if ( pageIndex > oldPageIndex )
                oldPageIndex = pageIndex;
        }
        return res;
    }

    /**
     * Methode qui imprimer les infos du patient et du praticien en double colonne
     * Elle imprime aussi l'entete de la premiere page du compte rendu
     *
     * @param g2
     *      Graphic de la page envoye a la methode print
     * @param y
     *      La hauteur en cours sur la page
     * @param w
     *      La largeur de la page
     *
     * @return la hauteur a laquelle toutes les informations ont ete imprimes
     */
    public int printInfoPatientPracticien (Graphics2D g2, int y, int w){
        String title = "Examen du " + dateFormat.format( examen.getDate().getTime() );
        g2.setFont(TITLE);
        FontMetrics metrics = g2.getFontMetrics();
        int lineHeigth = metrics.getHeight();
        int xCentered = (w - metrics.stringWidth(title) )/2;
        g2.drawString(title, xCentered, y);
        y += lineHeigth;

        g2.setFont(SUBTITLE);
        metrics = g2.getFontMetrics();
        lineHeigth = metrics.getHeight();
        y += lineHeigth;
        g2.drawString("Num archivage : " + examen.getNumArchivage(), margeX, y);
        y += lineHeigth;

        g2.drawString("Patient : ", margeX, y);
        g2.drawString("Medecin : ", w/2, y);
        y += lineHeigth;

        g2.setFont(TEXT);
        metrics = g2.getFontMetrics();
        lineHeigth = metrics.getHeight();
        g2.drawString("Id Radiologique : " + patient.getIdPR(), margeX, y);
        g2.drawString("Id Medical : " + practicien.getIdMedical(), w/2, y);
        y += lineHeigth;

        g2.drawString("Nom :" + patient.getNom(), margeX, y);
        g2.drawString("Nom : " + practicien.getNom(), w/2, y);
        y += lineHeigth;

        g2.drawString( prenom + patient.getPrenom(), margeX, y);
        g2.drawString(prenom + practicien.getPrenom(), w/2, y);
        y += lineHeigth;

        g2.drawString("Id : " + patient.getIdPatient(), margeX, y);
        g2.drawString("Profession : " + practicien.getProfession(), w/2, y);
        y += lineHeigth;

        g2.drawString("Date de naissance : " + dateFormat.format(patient.getNaissance().getTime()), margeX, y);
        y += 2*lineHeigth;

        return y;
    }

    /**
     * Imprime le compte rendu a la suite des informations patientes et praticien.
     * Les mots du compte rendu sont d'abord rentre dans un tableau.
     * Une boucle va alors parcourir le tableau et creer des lignes dont la taille ne depasse pas
     * celle de la longueuer de la page. Chaque lignes est sauvegardees dans une liste.
     * Une seconde boucle parcour la liste et imprime les lignes du compte rendu
     * tant que la fin de la page n'a pas ete atteinte.
     * @param g2
     *      Graphic de la page
     * @param y
     *      Hauteur en cours de la page
     * @param w
     *      Largeur de la page
     * @param h
     *      Hauteur de la page
     * @param indexCr
     *      Indice sauvegardant la progressin dans l'etat d'impression du compte rendu
     *
     * @return L'indice
     */
    public int printCR (Graphics2D g2, int y, int w, int h, int indexCr){
        g2.setFont(SUBTITLE);
        FontMetrics metrics = g2.getFontMetrics();
        int lineHeigth = metrics.getHeight();
        String title = "Compte-rendu medical";
        g2.drawString( title, margeX, y);
        y += lineHeigth;

        g2.setFont(TEXT);
        metrics = g2.getFontMetrics();
        lineHeigth = metrics.getHeight();
        String[] words = examen.getCr().getCompteRendu().split("\\s");

        List<String> allLines = new ArrayList<>();
        StringBuilder builder = new StringBuilder();

        int i=0;
        while( i<words.length ){
            int x=margeX + metrics.stringWidth(words[i]);
            while( i<words.length && x < w-(2*margeX) ){
                if (i<words.length-1)
                    x += metrics.stringWidth(words[i+1]+" ");
                builder.append(words[i] + " ");
                i++;
            }
            allLines.add(builder.toString());
            builder = new StringBuilder();
        }

        while (indexCr<allLines.size() && y + lineHeigth < h - margeY) {
            g2.drawString(allLines.get(indexCr), margeX, y);
            y += lineHeigth;
            indexCr++;
        }
        crImprimer=(indexCr>=allLines.size());
        return indexCr;
    }

    /**
     * Methode qui imprime les images d'un compte et leur informations.
     * Chaque images est imprimees sur une page.
     * @param g2
     *      Graphic de la page
     * @param y
     *      Hauteur en cours sur la page
     * @param w
     *      Largeur de la page
     * @param h
     *      Hauteur de la page
     */
    public void printImage (Graphics2D g2, int y, int w, int h){

        g2.setFont(SUBTITLE);
        FontMetrics metrics = g2.getFontMetrics();
        int lineHeigth = metrics.getHeight();
        AbstractImage image = listeImage.get(indexImage);
        BufferedImage buff = image.getImage();

        g2.drawString("Numero Archivage : "+image.getNumArchivage(), margeX, y);
        y+=lineHeigth;

        g2.drawString(Integer.toString( image.getNumInstance() ), margeX, y);
        y += lineHeigth;

        g2.drawString("Patient : ", margeX, y);
        y += lineHeigth;
        g2.setFont(TEXT);
        metrics = g2.getFontMetrics();
        lineHeigth = metrics.getHeight();
        g2.drawString("Id Radiologique : " + patient.getIdPR(), margeX, y);
        y += lineHeigth;
        g2.drawString("Nom : " + patient.getNom(), margeX, y);
        y += lineHeigth;
        g2.drawString("Prenom : " + patient.getPrenom(), margeX, y);
        y += lineHeigth;
        g2.drawString("Id : " + patient.getIdPatient(), margeX, y);
        y += lineHeigth;
        g2.drawString("Date de naissance : " + dateFormat.format(patient.getNaissance().getTime()), margeX, y);
        y += 2*lineHeigth;

        double horizontalRatio = (double) (w - 2*margeX)/buff.getWidth();
        double verticalRatio = (double) (h - y)/buff.getHeight();
        if ( horizontalRatio > verticalRatio )
            g2.drawImage(buff, (int) ( (w - 2*margeX) - buff.getWidth() * verticalRatio ) /2 + margeY, y, (int) (buff.getWidth() * verticalRatio), (int) (buff.getHeight() * verticalRatio) - margeY, null);
        else
            g2.drawImage(buff, margeX, y, (int) (buff.getWidth() * horizontalRatio), (int) (buff.getHeight() * horizontalRatio) - margeY, null );
    }
}

