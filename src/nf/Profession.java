package nf;

public enum Profession {
   //Liste des valeurs pouvant être prises par l'enumération avec un déclaration sous forme
    // de valeur fixe et une sous forme de string pour correspondre à la base de données

    UNKNOWN("inconu"),
    PH("Practicien Hospitalier"),
    MANIPULATEUR("Manipulateur"),
    SECRETAIRE("Secretaire");


    private String nom;

    Profession(String nom) {
        this.nom = nom;
    }

}
