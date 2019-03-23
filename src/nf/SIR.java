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

    public void connection (String id, String mdp) throws SQLException, ClassNotFoundException {
        conn.connection(id, mdp);
        listeDMR = conn.getDMR();
        personneConnecte = conn.getPersonnelServiceRadio(id);
        hl7.ecoute();
    }

    public void deconnection () throws SQLException {
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

    public Connexion getConn(){
        return conn;
    }

    public PersonnelServiceRadio getPersonneConnecte (){ return personneConnecte; }

    public void addImageToExam (Examen examen, AbstractImage image) throws IOException, SQLException {
        examen.addImage(image);
        List<AbstractImage> list = new ArrayList<>();
        list.add(image);
        conn.insertImage( list );
    }

    public HL7 getHl7 () { return hl7; }

    public List<DMR> rechercheDMR(String p) {
        List<DMR> dmr = new ArrayList<>();
        Pattern pattern = Pattern.compile("^"+p+"\\d*");
        for(int i=0; i<this.listeDMR.size(); i++){
            Patient patient = this.listeDMR.get(i).getPatient();
            if(patient.getNom().equalsIgnoreCase(p)
                    || patient.getPrenom().equalsIgnoreCase(p)
                    || pattern.matcher(patient.getIdPR() ).matches()
                    || pattern.matcher( patient.getIdPatient() ).matches()
            ){
                    dmr.add(listeDMR.get(i));
            }
        }
       return dmr;
    }
}
