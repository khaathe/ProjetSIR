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
     * Constructeur par defaut de la classe. Permet d'inititaliser tous les attributs a des valeurs par defaut.
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
     * Constructeur permettant d'initialiser tous les attributs de la classe a des valeurs definies dans les parametres.
     * Affecte a l'attribut sexe la valeur F ou H si elle est reellement trouvee, et I sinon.
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
     *  Methode permettant de retourner l'ensemble des informations concernant le patient dans un string structure
     * @return String
     */
    public String toString (){
        return idPR + ", "
                + nom + " " + prenom +", "
                + "Ne le : " + new SimpleDateFormat("yyyy-MM-dd").format(naissance.getTime())
                + System.getProperty("line.separator") +", Sexe: "+getSexe();
    }

    /**
     * Methode permettant de generer un identifiant radiologique aleatoirement.
     * Utilise la methode Math.random() pour la generation aleatoire, et limite l'identifiant a 15 chiffres au format decimal
     * @return String
     * Le numero IdPR
     */

    public static String generateIdPR () {
        double random = Math.random();
        random = random * Math.pow(10, 15);
        DecimalFormat df = new DecimalFormat("000000000000000");
        df.setRoundingMode(RoundingMode.HALF_UP);
        return df.format(random);
    }
}

