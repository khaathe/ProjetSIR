package nf;

public class CompteRendu {
    private String compteRendu;
    private String numArchivage;


    public CompteRendu(String numArchivage, String compteRendu){
        this.numArchivage=numArchivage;
        this.compteRendu= compteRendu;
    }

    public String getNumArchivage() {
        return numArchivage;
    }

    public String getCompteRendu() {
        return compteRendu;
    }

}
