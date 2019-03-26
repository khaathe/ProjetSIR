package nf;

public class PersonnelServiceRadio {
    private String nom;
    private String prenom;
    private String idMedical;
    private Profession profession;

    /**
     * Constructeur par defaut initialisant tous les attributs de la classe a des valeurs par defaut
     */
    public PersonnelServiceRadio (){
        nom = "";
        prenom = "";
        idMedical = "";
        profession = Profession.UNKNOWN;
    }

    /**
     * Constructeur permettant d'initialiser tous les attributs de la classe a des valeurs definies en parametres
     */
    public PersonnelServiceRadio(String idMedical, String nom, String prenom, Profession profession) {
        this.idMedical = idMedical;
        this.nom = nom;
        this.prenom = prenom;
        this.profession = profession;
    }

    /**
     *  Methode permettant l'affichage d'un string comportant les nom et prenom du personnel dans le cas
     *  ou il s'agit medecin ou d'un manipulateur radiologique, donc de la forme : "Dr : DUPONT Patrick"
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
     *  Methode permettant de retourner la profession sous forme d'un string.
     *  Renvoi "secretaire" dans le cas des secretaires medicales, et l'intitule de la profession
     *  comme decrite dans l'enumeration Profession pour les deux autres types d'utilisateurs.
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
