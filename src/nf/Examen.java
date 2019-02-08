package nf;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

public class Examen {
    private String idExam;
    private GregorianCalendar date;
    private String numArchivage;
    private CompteRendu cr;
    private TypeExamen typeExamen;
    private List<Image> images;
    private PersonnelServiceRadio praticien;
    private ServiceHosp service;


    public Examen(String idExam, GregorianCalendar date, String numArchivage, TypeExamen typeExamen, PersonnelServiceRadio praticien, ServiceHosp service){
        this.idExam= idExam;
        this.date = date;
        this.numArchivage = numArchivage;
        images = new ArrayList<Image>();
        this.cr=null;
        this.typeExamen=typeExamen;
        this.praticien= praticien;
        this.service=service;


    }

    public void imprimerCR(){

    }

    public void imprimerImage(){

    }
    public void ajouterImage(Image image){
        if (!images.contains(image)){
            images.add(image);
        }
    }

   /* public void ajouterCR(CompteRendu cr){
        Connexion.ajouterCR(cr);
    }
*/
}
