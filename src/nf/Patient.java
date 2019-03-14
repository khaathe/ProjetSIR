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
    private String sexe;

    public  Patient (){
        idPR = "";
        idPatient = "";
        nom = "";
        prenom = "";
        naissance = new GregorianCalendar();
        sexe = "";
    }

    public Patient(String idPR, String idPatient, String nom, String prenom, GregorianCalendar naissance, String sexe){
        this.idPR = idPR;
        this.idPatient = idPatient;
        this.nom = nom;
        this.prenom = prenom;
        String m="M";
        String f="F";
        String u="Inconnu";
        if(sexe.equalsIgnoreCase(m)|sexe.equalsIgnoreCase(f)){
        this.sexe=sexe;}
        else{this.sexe=u;}
        this.naissance = naissance;
    }

    public  void setIdPatient (String idPatient) { this.idPatient = idPatient; }

    public String getIdPatient() {
        return idPatient;
    }

    public String getNom() {
        return nom;
    }

    public String getSexe() {
        return sexe;
    }

    public String getPrenom() {
        return prenom;
    }

    public GregorianCalendar getNaissance() {
        return naissance;
    }

    public String getIdPR(){return idPR;}

    public String toString (){
        String Newligne=System.getProperty("line.separator");
        String info = idPR + ", "
                + nom + " " + prenom +", "
                + "Ne le : " + new SimpleDateFormat("yyyy-MM-dd").format(naissance.getTime())+Newligne+", Sexe: "+getSexe();
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

