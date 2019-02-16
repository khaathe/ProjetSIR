package nf;

import java.sql.PreparedStatement;

public enum Profession {
    UNKNOWN("inconu"),
    PH("Practicien Hospitalier"),
    MANIPULATEUR("Manipulateur"),
    SECRETAIRE_MEDICALE("Secretaire medicale");


    private String nom;

    private Profession (String nom) {
        this.nom = nom;
    }
}
