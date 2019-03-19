package nf;

public enum Profession {
    /*PH,
    MANIPULATEUR,
    SECRETAIRE
*/
    UNKNOWN("inconu"),
    PH("Practicien Hospitalier"),
    MANIPULATEUR("Manipulateur"),
    SECRETAIRE("Secretaire");


    private String nom;

    Profession(String nom) {
        this.nom = nom;
    }

}
