package nf;

import java.util.GregorianCalendar;

public class Patient {
    private String idPatient;
    private String nom;
    private String prenom;
    private GregorianCalendar naissance;
    private String numSS;

    public Patient(String idPatient, String nom, String prenom, GregorianCalendar naissance, String numSS){
        this.idPatient=idPatient;
        this.nom=nom;
        this.prenom=prenom;
        this.naissance=naissance;
        this.numSS=numSS;
    }
}
