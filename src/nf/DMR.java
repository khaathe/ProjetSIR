package nf;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

public class DMR {
    private Patient patient;
    private List<Examen> listeExamen ;

    public DMR(String idPatient,String nom, String prenom, GregorianCalendar naissance, String numSS){
        this.setPatient(new Patient(idPatient, nom, prenom, naissance, numSS));
        setListeExamen(new ArrayList<Examen>());
    }

    public void ajouterExamen(Examen exam){

    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public List<Examen> getListeExamen() {
        return listeExamen;
    }

    public void setListeExamen(List<Examen> listeExamen) {
        this.listeExamen = listeExamen;
    }
}
