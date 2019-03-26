package nf;

public class CompteRendu {
    private String cr;
    private String numArchivage;


    /**Constructeur de la classe
     * @param numArchivage et cr
     *       un numero d'archivage lui permettant de correspondre a
     *       un examen particulier et un texte en String correspondant au contenu du compte-rendu
     *
     */
    public CompteRendu(String numArchivage, String cr){
        this.numArchivage=numArchivage;
        this.cr= cr;
    }

    public String getNumArchivage() {
        return numArchivage;
    }

    public String getCompteRendu() {
        return cr;
    }

}
