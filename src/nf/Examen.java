package nf;

import java.util.GregorianCalendar;

public class Examen {
    private String idExam;
    private GregorianCalendar date;
    private String numArchivage;
    private CompteRendu cr;
    private TypeExamen typeExamen;


    public Examen(String idExam, GregorianCalendar date, String numArchivage){
        this.idExam= idExam;
        this.date = date;
        this.numArchivage = numArchivage;

    }

    public void imprimerCR(){

    }

    public void imprimerImage(){

    }

   /* public void ajouterCR(CompteRendu cr){
        Connexion.ajouterCR(cr);
    }
*/
}
