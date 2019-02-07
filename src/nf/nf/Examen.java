package nf;

import java.util.GregorianCalendar;

public class Examen {
    private String idExam;
    private GregorianCalendar date;
    private String numArchivage;
    private CompteRendu cr;
    private TypeExamen typeExamen;
    private List<Image> images;
    private PersonnelServiceRadio praticien;
    private Service service;


    public Examen(String idExam, GregorianCalendar date, String numArchivage, TypeExamen typeExamen, PersonnelServiceRadio praticien, Service service){
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
        if (!images.contain(image)){
            images.add(image);
        }
    }

   /* public void ajouterCR(CompteRendu cr){
        Connexion.ajouterCR(cr);
    }
*/
}
