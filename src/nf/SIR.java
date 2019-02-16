package nf;

import java.util.ArrayList;



public class SIR {


    private ArrayList<DMR> listeDMR;
    Connexion conn;
    private PersonnelServiceRadio personneConnecte;


    public SIR() {
        conn = new Connexion();
        listeDMR = null;
        personneConnecte = null;
}

    public void connection (String id, String mdp) throws Exception{
        conn.connection(id, mdp);
        listeDMR = conn.getDMR();
        personneConnecte = conn.getPersonnelServiceRadio(id);
    }

    public void deconnection () throws Exception{
        conn.Disconnection();
    }

//créer un nouveau DMR, implique d'associer un patient et un examen. On vérifie d'abord qu'un DMR pour ce patient n'est pas déjà présent dans le SIR
    public void creationDMR(DMR dmr){

    }



/*
    public List<DMR> getListeDMR() throws Exception {
        return connexion.getDMR();
*/
    public ArrayList<DMR> getListeDMR() {
        return listeDMR;

    }
    public Connexion getConn(){
        return conn;
    }

    public void setListeDMR(ArrayList<DMR> listeDMR) {
        this.listeDMR = listeDMR;
    }

    public ArrayList<PersonnelServiceRadio> getListePersonnel(){
        return  listePersonnel;
    }
    public void setListePersonnel(ArrayList<PersonnelServiceRadio> listePersonnel){
        this.listePersonnel= listePersonnel;
    }

    /*public String openDMR(DMR dmr){
        String result="";
        result+="Mr/Mme "+dmr.patient.nom + " "+dmr.patient.prenom+ "\n";
        result+="Identifiant : "+dmr.patient.idPatient+". Date de naissance : "+dmr.patient.naissance+ "\n";
        result+="Numéro de sécurité sociale : "+dmr.patient.numSS;
        return result;
    }


    public void loadDMR(DMR dmr){
        if(listeDMR.contains(dmr)){
            openDMR(dmr);
        }
    }
*/

}
