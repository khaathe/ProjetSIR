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

    private Profession (String nom) {
        this.nom = nom;
    }

}
