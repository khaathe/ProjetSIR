package nf;

public class CompteRendu {
    private String compteRendu;
    private String idExam;


    public CompteRendu(String compteRendu, String idExam){
        this.idExam=idExam;
        this.compteRendu= compteRendu;
    }

    public String getIdExam() {
        return idExam;
    }

    public String getCompteRendu() {
        return compteRendu;
    }

}
