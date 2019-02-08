package nf;

import java.util.GregorianCalendar;

public class CompteRendu {
    private PersonnelServiceRadio editeur;
    private String contenu;
    private GregorianCalendar date;

    public CompteRendu(String nom, String prenom, Profession profession, GregorianCalendar date){
        this.contenu=null;
        this.editeur= new PersonnelServiceRadio(nom,prenom,profession);
        this.date=date;
    }

    public String ajouterContenu(){

        contenu+="contenu du compte-rendu";
        return contenu;

    }
}
