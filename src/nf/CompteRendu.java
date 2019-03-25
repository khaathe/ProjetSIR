package nf;

public class CompteRendu {
    private String cr;
    private String numArchivage;


    //Constructeur de la classe, prenant en paramètre un numéro d'archivage lui permettant de correspondre à
    // un examen particulier et un texte en String correspondant au contenu du compte-rendu
    public CompteRendu(String numArchivage, String cr){
        this.numArchivage=numArchivage;
        this.cr= cr;
    }

    //Méthode permettant d'accéder au numéro d'archivage en le retournant
    public String getNumArchivage() {
        return numArchivage;
    }

    //Méthode permettant d'accéder au compte-rendu en le retournant
    public String getCompteRendu() {
        return cr;
    }

}
