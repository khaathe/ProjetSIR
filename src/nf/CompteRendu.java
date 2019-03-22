package nf;

public class CompteRendu {
    private String cr;
    private String numArchivage;


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
