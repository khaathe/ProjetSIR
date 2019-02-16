package nf;

import java.util.GregorianCalendar;

public class CompteRendu {
    private String contenu;

    public CompteRendu(String contenu){
        this.contenu= contenu;
    }

    public String ajouterContenu(String text){
        contenu+=text;
        return contenu;

    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

}
