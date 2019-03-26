package nf;

public class PersonnelServiceRadio {
    private String nom;
    private String prenom;
    private String idMedical;
    private Profession profession;

    /**
     * Constructeur par défaut initialisant tous les attributs de la classe à des valeurs par défaut
     */
    public PersonnelServiceRadio (){
        nom = "";
        prenom = "";
        idMedical = "";
        profession = Profession.UNKNOWN;
    }

    /**
     * Constructeur permettant d'initialiser tous les attributs de la classe à des valeurs définies en paramètres
     */
    public PersonnelServiceRadio(String idMedical, String nom, String prenom, Profession profession) {
        this.idMedical = idMedical;
        this.nom = nom;
        this.prenom = prenom;
        this.profession = profession;
    }

    /**
     *  Méthode permettant l'affichage d'un string comportant les nom et prénom du personnel dans le cas
     *  où il s'agit médecin ou d'un manipulateur radiologique, donc de la forme : "Dr : DUPONT Patrick"
     * @return String
     */
    public String toString (){
        return "Dr " + nom + " " + prenom;
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

    /**
     *  Méthode permettant de retourner la profession sous forme d'un string.
     *  Renvoi "secrétaire" dans le cas des sécrétaires médicales, et l'intitulé de la profession
     *  comme décrite dans l'énumération Profession pour les deux autres types d'utilisateurs.
     */
    public String getProfessionString(){
        Profession p;
        String s;
        p=getProfession();
        if (p.toString()=="SECRETAIRE_MEDICALE"){
            s="secretaire";
        }
        else{s=p.toString();}
        return s;
    }

    public void setProfession(Profession profession) {
        this.profession = profession;
    }

}
