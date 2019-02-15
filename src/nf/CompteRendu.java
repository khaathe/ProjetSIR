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

    public String ajouterContenu(String text){

        contenu+=text;
        return contenu;

    }

    public String getContenu() {
        return contenu;
    }

    public PersonnelServiceRadio getEditeur() {
        return editeur;
    }

    public GregorianCalendar getDate() {
        return date;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public void setDate(GregorianCalendar date) {
        this.date = date;
    }

    public void setEditeur(PersonnelServiceRadio editeur) {
        this.editeur = editeur;
    }
}
