package nf;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class SIR {


    private List<DMR> listeDMR;
    private Connexion conn;
    private PersonnelServiceRadio personneConnecte;
    private HL7 hl7;


    public SIR() {
        conn = new Connexion();
        hl7 = new HL7();
        listeDMR = null;
        personneConnecte = null;
    }

    /**
     * Methode permettant de se connecter, appelle la methode connection() de la classe connexion
     * @param id
     *      Identifiant de l'utilisateur
     * @param mdp
     *      Mot de passe de l'utilisateur
     * @throws SQLException
     *      levee par la methode connection de la classe connexion
     * @throws ClassNotFoundException
     *      levee par la methode connection de la classe connexion
     */
    public void connection(String id, String mdp) throws SQLException, ClassNotFoundException {
        conn.connection(id, mdp);
        listeDMR = conn.getDMR();
        personneConnecte = conn.getPersonnelServiceRadio(id);
        hl7.ecoute();
    }

    /**
     * Permet a l'utilisateur de se deconnecter
     * @throws SQLException
     *      Levee quand il y a un problème avec la base de données
     */
    public void deconnection() throws SQLException {
        conn.disconnection();
        hl7.deconnection();
        listeDMR = null;
        personneConnecte = null;
    }

    /**
     * cree un nouveau DMR, implique d'associer un patient et un examen. On verifie d'abord qu'un DMR
     *  pour ce patient n'est pas deja present dans le SIR on l'utilisera pour l'admission uniquement
     */

    public void admitPatient() throws SQLException {
        conn.addPatient(hl7.getPatient());
        listeDMR.add(new DMR(hl7.getPatient()));
    }

    public List<DMR> getListeDMR() {
        return listeDMR;
    }

    public Connexion getConn() {
        return conn;
    }

    public PersonnelServiceRadio getPersonneConnecte() {
        return personneConnecte;
    }


    /**
     * Methode permettant d'ajouter une image a un examen. Prend en parametre l'examen concerne et l'image a ajouter
     * Ajoute l'image a l'examen precise en parametre, cree une nouvelle liste d'AbstractImage a laquelle l'image en parametre est ajoutee
     * Appelle la methode insertImage de la classe Connexion, pour inserer l'image sous forme d'une liste dans la base de donnees
     * @param examen, image
     *        Examen auquel l'image est ajoutee
     */

        public void addImageToExam (Examen examen, AbstractImage image) throws IOException, SQLException {
            examen.addImage(image);
            List<AbstractImage> list = new ArrayList<>();
            list.add(image);
            conn.insertImage(list);
        }

        public HL7 getHl7 () {
            return hl7;
        }

    /**
     *  Methode permettant de rechercher un patient ou des patients dans la liste de patient contenue dans le SIR,
     *  et renvoie une nouvelle liste contenant tous les patients correspondant a l'attribut utilise pour la recherche (pris en parametre).
     *  Quand une correspondance est detectee, le patient correspondant est ajoute a la nouvelle liste ensuite retournee
     * @param p
     * String utilise pour effectue a la recherche
     * @return la nouvelle liste generee
     */
    public List<DMR> rechercheDMR (String p){
            List<DMR> dmr = new ArrayList<>();
            Pattern pattern = Pattern.compile("^" + p + "\\d*");
            for (int i = 0; i < this.listeDMR.size(); i++) {
                Patient patient = this.listeDMR.get(i).getPatient();
                if (patient.getNom().equalsIgnoreCase(p)
                        || patient.getPrenom().equalsIgnoreCase(p)
                        || pattern.matcher(patient.getIdPR()).matches()
                        || pattern.matcher(patient.getIdPatient()).matches()
                ) {
                    dmr.add(listeDMR.get(i));
                }
            }
            return dmr;
        }
    }

