package nf;

import java.util.ArrayList;
import java.util.List;

public class DMR {
    private Patient patient;
    private List<Examen> listeExamen ;

    //Constructeur de la classe prenant en paramètre un patient. Permet de créer le DMR
    // d'un patient particulier, dans lequel seront stockés tous ses examens
    public DMR(Patient patient){
        this.patient = patient;
        listeExamen = new ArrayList<>();
    }

    //La méthode toString permet de retourner un affichage de la forme : "DMR de : DUPONT Patrick".
    // Elle peut être directement appelée dans les interfaces pour faciliter l'affichage de l'identité du patien auquel appartient du DMR
    public String toString () {
        return "DMR de : " + patient.getNom() + " " + patient.getPrenom();
    }

    //Méthode permettant d'ajouter un examen (pris en paramètre) à la liste d'examen du patient listeExamen
    public void ajouterExamen(Examen exam){
        listeExamen.add(exam);
    }

    ////Méthode permettant d'accéder au patient titulaire du DMR en le retournant
    public Patient getPatient() {
        return patient;
    }

    //Méthode permettant de modifier le patient titulaire du DMR en question. Prend donc un patient en paramètre
    public void setPatient(Patient patient) { this.patient = patient; }

    //Methode permettant d'accéder à la liste d'examens, en la retournant
    public List<Examen> getListeExamen() {
        return listeExamen;
    }

    //Méthode permettant d'affecter une nouvelle liste (celle en paramètre) au DMR considéré.
    public void setListeExamen(List<Examen> listeExamen) {
        this.listeExamen = listeExamen;
    }
}
