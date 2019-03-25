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

    public void connection(String id, String mdp) throws SQLException, ClassNotFoundException {
        conn.connection(id, mdp);
        listeDMR = conn.getDMR();
        personneConnecte = conn.getPersonnelServiceRadio(id);
        hl7.ecoute();
    }

    public void deconnection() throws SQLException {
        conn.disconnection();
        hl7.deconnection();
        listeDMR = null;
        personneConnecte = null;
    }

    //créer un nouveau DMR, implique d'associer un patient et un examen. On vérifie d'abord qu'un DMR pour ce patient n'est pas déjà présent dans le SIR
    //on l'utilisera pour l'admission uniquement
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


    //Méthode permettant d'ajouter une image à un examen. Prend en paramètre l'examen concerné et l'image à ajouter
    //Ajoute l'image à l'examen précisé en paramètre, crée une nouvelle liste d'AbstractImage à laquelle l'image en paramètre est ajoutée
    //Appelle la méthode insertImage de la classe Connexion, pour inserer l'image sous forme d'une liste dans la base de données
        public void addImageToExam (Examen examen, AbstractImage image) throws IOException, SQLException {
            examen.addImage(image);
            List<AbstractImage> list = new ArrayList<>();
            list.add(image);
            conn.insertImage(list);
        }

        public HL7 getHl7 () {
            return hl7;
        }

/*
    //Methode permettant de rechercher un patient ou des patients dans la liste de patient contenue dans le SIR,
    // et renvoie une nouvelle liste contenant tous les patients correspondant à l'attribut utilisé pour la recherche (pris en paramètre).
    //Une boucle permet de parcourir la liste de patients du SIR en comparant le prénom, le nom (ou les 2 ensembles),
    // ou l'un des identifiants avec le string en paramètre.
    //Quand une correspondance est détectée, le patient correspondant est ajouté à la nouvelle liste ensuite retournée
    public ArrayList<DMR> rechercheDMR(String p) throws Exception {
        ArrayList<DMR> dmr = new ArrayList<DMR>();

        for(int i=0; i<this.listeDMR.size();i++){
            String np = this.listeDMR.get(i).getPatient().getNom()+ " "+this.listeDMR.get(i).getPatient().getPrenom();
            if(this.listeDMR.get(i).getPatient().getNom().equalsIgnoreCase(p) || this.listeDMR.get(i).getPatient().getPrenom().equalsIgnoreCase(p) || this.listeDMR.get(i).getPatient().getIdPR().matches(p+"(.*)") || this.listeDMR.get(i).getPatient().getIdPatient().matches(p+"(.*)")){
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

