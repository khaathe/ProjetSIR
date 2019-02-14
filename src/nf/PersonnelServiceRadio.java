package nf;

public class PersonnelServiceRadio {
    private String nom;
    private String prenom;
    private String idMedical;
    private Profession profession;


    public PersonnelServiceRadio(String nom, String prenom, String idMedical, Profession profession) {
        this.setNom(nom);
        this.setPrenom(prenom);
        this.setIdMedical(idMedical);
        this.setProfession(profession);
    }

    // constructeur pour initialiser le professionel correspondant à un examen, l'identifiant n'est pas nécessaire
    public PersonnelServiceRadio(String nom, String prenom, Profession profession) {
        this.setNom(nom);
        this.setPrenom(prenom);
        this.setProfession(profession);
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

        /*
        // constructeur de base, initialisant tous les attributs
        public PersonnelServiceRadio(String nom, String prenom, String idMedical, Profession profession) {
            this.nom = nom;
            this.prenom = prenom;
            this.idMedical = idMedical;
            this.profession = profession;
        }

        // constructeur pour initialiser le professionel correspondant à un examen, l'identifiant n'est pas nécessaire
    public PersonnelServiceRadio(String nom, String prenom, Profession profession) {
            this.nom = nom;
            this.prenom = prenom;
            this.profession = profession;
        }


    }
    */
}
