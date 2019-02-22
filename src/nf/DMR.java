package nf;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

public class DMR {
    private Patient patient;
    private ArrayList<Examen> listeExamen ;

    public DMR(Patient patient){
        this.patient = patient;
        listeExamen = new ArrayList<>();
    }

    public String toString () {
        return "DMR de : " + patient.getNom() + " " + patient.getPrenom();
    }
    public void ajouterExamen(Examen exam){
        listeExamen.add(exam);
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

    public void setListeExamen(ArrayList<Examen> listeExamen) {
        this.listeExamen = listeExamen;
    }
}
