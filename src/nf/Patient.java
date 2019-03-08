package nf;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

public class Patient {
    private String idPR;
    private String idPatient;
    private String nom;
    private String prenom;
    private GregorianCalendar naissance;

    public  Patient (){
        idPR = "";
        idPatient = "";
        nom = "";
        prenom = "";
        naissance = new GregorianCalendar();
    }

    public Patient(String idPR, String idPatient, String nom, String prenom, GregorianCalendar naissance){
        this.idPR = idPR;
        this.idPatient = idPatient;
        this.nom = nom;
        this.prenom = prenom;
        this.naissance = naissance;
    }

    public  void setIdPatient (String idPatient) { this.idPatient = idPatient; }

    public String getIdPatient() {
        return idPatient;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public GregorianCalendar getNaissance() {
        return naissance;
    }

    public String getIdPR(){return idPR;}

    public String toString (){
        String info = idPR + ", "
                + nom + " " + prenom +", "
                + "Ne le : " + new SimpleDateFormat("yyyy-MM-dd").format(naissance.getTime());
        return info;
    }

    public static String generateIdPR () {
        double random = Math.random();
        random = random * Math.pow(10, 15);
        DecimalFormat df = new DecimalFormat("000000000000000");
        df.setRoundingMode(RoundingMode.HALF_UP);
        return df.format(random);
    }
}

