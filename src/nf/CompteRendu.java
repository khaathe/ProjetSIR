package nf;

public class CompteRendu {
    private String compteRendu;
    private String numArchivage;


    public CompteRendu(String compteRendu, String idExam){
        this.numArchivage=idExam;
        this.compteRendu= compteRendu;
    }

    public String getNumArchivage() {
        return numArchivage;
    }

    public String getCompteRendu() {
        return compteRendu;
    }

}
