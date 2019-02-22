package nf;

public class PersonnelServiceRadio {
    private String nom;
    private String prenom;
    private String idMedical;
    private Profession profession;

    public PersonnelServiceRadio (){
        nom = "";
        prenom = "";
        idMedical = "";
        profession = Profession.UNKNOWN;
    }


    public PersonnelServiceRadio(String idMedical, String nom, String prenom, Profession profession) {
        this.idMedical = idMedical;
        this.nom = nom;
        this.prenom = prenom;
        this.profession = profession;
    }

    public String toString (){
        String info = "Dr " + nom + " " + prenom;
        return info;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getIdMedical() {
        return idMedical;
    }

    public void setIdMedical(String idMedical) {
        this.idMedical = idMedical;
    }

    public Profession getProfession() {
        return profession;
    }

    public void setProfession(Profession profession) {
        this.profession = profession;
    }

}
