package nf;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

public class DMR {
    private Patient patient;
    private List<Examen> listeExamen ;

    public DMR(String idPatient,String nom, String prenom, GregorianCalendar naissance, String numSS){
        this.patient = new Patient(idPatient, nom, prenom, naissance, numSS);
        listeExamen = new ArrayList<Examen>();
    }

    public void ajouterExamen(Examen exam){
        if(!listeExamen.contains(exam)){
            listeExamen.add(exam);
        }
    }
}
