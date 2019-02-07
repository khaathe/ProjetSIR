package nf;
import nf.Profession;

public class PersonnelServiceRadio {
    private String nom;
    private String prenom;
    private String idMedical;
    private Profession profession;

    public PersonnelServiceRadio(String nom, String prenom, String idMedical, Profession profession){
        this.nom=nom;
        this.prenom= prenom;
        this.idMedical = idMedical;
        this.profession= profession;
    }

    // constructeur pour initialiser le professionel correspondant à un examen, l'identifiant n'est pas nécessaire
    public PersonnelServiceRadio(String nom, String prenom, Profession profession){
        this.nom=nom;
        this.prenom=prenom;
        this.profession=profession;
    }

}
