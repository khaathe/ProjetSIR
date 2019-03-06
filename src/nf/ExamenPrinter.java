package nf;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class ExamenPrinter implements Printable {
    private Examen examen;
    private int lastPage;
    private int indexCr;
    private int indexImage;
    private Patient patient;
    private  PersonnelServiceRadio practicien;
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd");
    private boolean crImprimer;
    private int oldPageIndex;
    private ArrayList<AbstractImage> listeImage;
    private static int margeX;
    private static int margeY;
    private static final Font titleFont = new Font("Serif", Font.PLAIN, 24);
    private static final Font subTitleFont = new Font("Serif", Font.PLAIN, 18);
    private static final Font textFont = new Font("Serif", Font.PLAIN, 12);

    public ExamenPrinter (Examen examen){
        this.examen = examen;
        patient = examen.getPatient();
        practicien = examen.getPraticien();
        indexCr = 0;
        indexImage = 0;
        oldPageIndex = 0;
        crImprimer = false;
        listeImage = examen.getImages();
    }

    @Override
    /**Methode appele par la classe job printer.
     * L'entier y correspond a la hauteur courrante. Cette variable est utilisee pour calculer
     * la hauteur y a laquelle sera dessine le String.
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
            y = printInfoPatientPracticien(g2, y, w, h);
            indexCr = 0;
            printCR(g2, y, w, h);
            lastPage = pageIndex + 1 + listeImage.size();
            oldPageIndex = pageIndex;
            res = Printable.PAGE_EXISTS;
        }
        else if ( !crImprimer ){
            printCR(g2, y, w, h);
            lastPage = pageIndex +1 + listeImage.size();
            oldPageIndex = pageIndex;
            res = Printable.PAGE_EXISTS;
        }
        else if ( listeImage != null && listeImage.size()>0 && pageIndex<lastPage ){
            printImage(g2, y, w, h);
            res = Printable.PAGE_EXISTS;
            if (pageIndex > oldPageIndex && oldPageIndex>0 && indexImage < listeImage.size()-1)
                indexImage++;
            if ( pageIndex > oldPageIndex )
                oldPageIndex = pageIndex;
        }
        return res;
    }

    public int printInfoPatientPracticien (Graphics2D g2, int y, int w, int h){
        String title = "Examen du " + dateFormat.format( examen.getDate().getTime() );
        g2.setFont(titleFont);
        FontMetrics metrics = g2.getFontMetrics();
        int lineHeigth = metrics.getHeight();
        int xCentered = (w - metrics.stringWidth(title) )/2;
        g2.drawString(title, xCentered, y);
        y += lineHeigth;

        g2.setFont(subTitleFont);
        metrics = g2.getFontMetrics();
        lineHeigth = metrics.getHeight();
        y += lineHeigth;
        g2.drawString("Num archivage : " + examen.getNumArchivage(), margeX, y);
        y += lineHeigth;

        g2.drawString("Patient : ", margeX, y);
        g2.drawString("Medecin : ", w/2, y);
        y += lineHeigth;

        g2.setFont(textFont);
        metrics = g2.getFontMetrics();
        lineHeigth = metrics.getHeight();
        g2.drawString("Id Radiologique : " + patient.getIdPR(), margeX, y);
        g2.drawString("Id Medical : " + practicien.getIdMedical(), w/2, y);
        y += lineHeigth;

        g2.drawString("Nom :" + patient.getNom(), margeX, y);
        g2.drawString("Nom : " + practicien.getNom(), w/2, y);
        y += lineHeigth;

        g2.drawString("Prenom : " + patient.getPrenom(), margeX, y);
        g2.drawString("Prenom : " + practicien.getPrenom(), w/2, y);
        y += lineHeigth;

        g2.drawString("Id : " + patient.getIdPatient(), margeX, y);
        g2.drawString("Profession : " + practicien.getProfession(), w/2, y);
        y += lineHeigth;

        g2.drawString("Date de naissance : " + dateFormat.format(patient.getNaissance().getTime()), margeX, y);
        y += 2*lineHeigth;

        return y;
    }

    public void printCR (Graphics2D g2, int y, int w, int h){
        g2.setFont(subTitleFont);
        FontMetrics metrics = g2.getFontMetrics();
        int lineHeigth = metrics.getHeight();
        String title = "Compte-rendu medical";
        g2.drawString( title, margeX, y);
        y += lineHeigth;

        g2.setFont(textFont);
        metrics = g2.getFontMetrics();
        lineHeigth = metrics.getHeight();
        int numberOfLine = h/lineHeigth;
        String[] words = examen.getCr().getCompteRendu().split("\\s");

        int i =indexCr;
        while(i < words.length && y + lineHeigth < h - margeY ){
            int x = margeX + metrics.stringWidth(words[i] + " ");
            String s = "";
            while( i<words.length-1 && x + metrics.stringWidth(words[i+1] + " ") < w-margeX && !words[i].equals("")  ){
                x += metrics.stringWidth(words[i+1] + " ");
                s += words[i] + " ";
                i++;
            }
            i++;
            y += lineHeigth;
            g2.drawString(s, margeX, y);
        }
        indexCr = i;
        crImprimer = (indexCr >= words.length);
    }

    public void printImage (Graphics2D g2, int y, int w, int h){

        g2.setFont(subTitleFont);
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
        g2.setFont(textFont);
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

