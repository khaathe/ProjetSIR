package nf;

import java.util.ArrayList;
import java.util.List;

public class DMR {
    private Patient patient;
    private List<Examen> listeExamen ;

    /**Constructeur de la classe
     * @param patient
     *   Patient auquel appartient le DMR
     *   Le DMR coontiendra aussi une liste d'examen, initialisee ici
     */
    public DMR(Patient patient){
        this.patient = patient;
        listeExamen = new ArrayList<>();
    }

    /** La methode toString permet de retourner un affichage de la forme : "DMR de : DUPONT Patrick".
     *  Elle peut etre directement appelee dans les interfaces pour faciliter l'affichage de l'identite du patien auquel appartient du DMR
     * @return String
     */
    public String toString () {
        return "DMR de : " + patient.getNom() + " " + patient.getPrenom();
    }

    /**
     * Methode permettant d'ajouter un examen (pris en parametre) a la liste d'examen du patient listeExamen
     */
    public void ajouterExamen(Examen exam){
        listeExamen.add(exam);
    }

    /**
     * Methode permettant d'acceder au patient titulaire du DMR en le retournant
     * @return Patient
     */
    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) { this.patient = patient; }

    /**
     * Methode permettant d'acceder a la liste d'examens, en la retournant
     * @return la liste d'examen
     */
    public List<Examen> getListeExamen() {
        return listeExamen;
    }

    /**
     * Methode permettant d'affecter une nouvelle liste (celle en parametre) au DMR considere.
     */
    public void setListeExamen(List<Examen> listeExamen) {
        this.listeExamen = listeExamen;
    }
}
