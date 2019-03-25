package nf;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

public class Patient {
    private String idPR;
    private String idPatient;
    private String nom;
    private String prenom;
    private GregorianCalendar naissance;
    private String sexe;

    /**
     * Constructeur par défaut de la classe. Permet d'inititaliser tous les attributs à des valeurs par défaut.
     */

    public  Patient (){
        idPR = "";
        idPatient = "";
        nom = "";
        prenom = "";
        naissance = new GregorianCalendar();
        sexe = "";
    }

    /**
     * Constructeur permettant d'initialiser tous les attributs de la classe à des valeurs définies dans les paramètres.
     * Affecte à l'attribut sexe la valeur F ou H si elle est réellement trouvée, et I sinon.
     */

    public Patient(String idPR, String idPatient, String nom, String prenom, GregorianCalendar naissance, String sexe){
        this.idPR = idPR;
        this.idPatient = idPatient;
        this.nom = nom;
        this.prenom = prenom;
        if( sexe.equalsIgnoreCase("H")
                || sexe.equalsIgnoreCase("F"))
            this.sexe=sexe;
        else
            this.sexe="I";
        this.naissance = naissance;
    }

    public  void setIdPatient (String idPatient) { this.idPatient = idPatient; }

    public String getIdPatient() {
        return idPatient;
    }

    public String getNom() {
        return nom;
    }

    public String getSexe() {
        return sexe;
    }

    public String getPrenom() {
        return prenom;
    }

    public GregorianCalendar getNaissance() {
        return naissance;
    }

    public String getIdPR(){return idPR;}

    /**
     *  Méthode permettant de retourner l'ensemble des informations concernant le patient dans un string structuré
     * @return String
     */
    public String toString (){
        return idPR + ", "
                + nom + " " + prenom +", "
                + "Ne le : " + new SimpleDateFormat("yyyy-MM-dd").format(naissance.getTime())
                + System.getProperty("line.separator") +", Sexe: "+getSexe();
    }

    /**
     * Méthode permettant de générer un identifiant radiologique aléatoirement.
     * Utilise la méthode Math.random() pour la génération aléatoire, et limite l'identifiant à 15 chiffres au format décimal
     * @return String
     * Le numéro IdPR
     */

    public static String generateIdPR () {
        double random = Math.random();
        random = random * Math.pow(10, 15);
        DecimalFormat df = new DecimalFormat("000000000000000");
        df.setRoundingMode(RoundingMode.HALF_UP);
        return df.format(random);
    }
}

