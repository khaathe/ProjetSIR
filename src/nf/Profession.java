package nf;

public enum Profession {
   //Liste des valeurs pouvant etre prises par l'enumeration avec un declaration sous forme
    // de valeur fixe et une sous forme de string pour correspondre a la base de donnees

    UNKNOWN("inconu"),
    PH("Practicien Hospitalier"),
    MANIPULATEUR("Manipulateur"),
    SECRETAIRE("Secretaire");


    private String nom;

    Profession(String nom) {
        this.nom = nom;
    }

}
