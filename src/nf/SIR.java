package nf;

import java.util.ArrayList;
import java.util.List;

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

    public void connection (String id, String mdp) throws Exception{
        conn.connection(id, mdp);
        listeDMR = conn.getDMR();
        personneConnecte = conn.getPersonnelServiceRadio(id);
        hl7.ecoute();
    }

    public void deconnection () throws Exception{
        try {
            conn.disconnection();
            hl7.deconnection();
        } catch (NullPointerException npe){

        }
        listeDMR = null;
        personneConnecte = null;
    }

    //créer un nouveau DMR, implique d'associer un patient et un examen. On vérifie d'abord qu'un DMR pour ce patient n'est pas déjà présent dans le SIR
    //on l'utilisera pour l'admission uniquement
    public void admitPatient() throws Exception {
        conn.addPatient( hl7.getPatient() );
        listeDMR.add( new DMR(hl7.getPatient()) );
    }

    public List<DMR> getListeDMR() {
        return listeDMR;
    }

    public Connexion getConn(){
        return conn;
    }

    public PersonnelServiceRadio getPersonneConnecte (){ return personneConnecte; }

    public void addImageToExam (Examen examen, AbstractImage image) throws Exception {
        examen.addImage(image);
        List<AbstractImage> list = new ArrayList<>();
        list.add(image);
        conn.insertImage( list );
    }

    public HL7 getHl7 () { return hl7; }

    public ArrayList<DMR> rechercheDMR(String p) throws Exception {
        ArrayList<DMR> dmr = new ArrayList<DMR>();

        for(int i=0; i<this.listeDMR.size();i++){
            String np = this.listeDMR.get(i).getPatient().getNom()+ " "+this.listeDMR.get(i).getPatient().getPrenom();
            if(this.listeDMR.get(i).getPatient().getNom().equalsIgnoreCase(p) || this.listeDMR.get(i).getPatient().getPrenom().equalsIgnoreCase(p) || this.listeDMR.get(i).getPatient().getIdPR().matches(p+"(.*)") || this.listeDMR.get(i).getPatient().getIdPatient().matches(p+"(.*)")){
                    dmr.add(listeDMR.get(i));
            }
            else if (np.equals(p)){
                dmr.add(listeDMR.get(i));
            }
        }
       return dmr;
    }
}
