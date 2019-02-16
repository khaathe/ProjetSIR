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



    public void setIdExam(String idExam) {
        this.idExam = idExam;
    }



    public void setCompteRendu(String compteRendu) {
        this.compteRendu = compteRendu;
    }
}
