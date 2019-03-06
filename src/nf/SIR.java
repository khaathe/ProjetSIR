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
        conn.disconnection();
        listeDMR = null;
        personneConnecte = null;
    }

    //créer un nouveau DMR, implique d'associer un patient et un examen. On vérifie d'abord qu'un DMR pour ce patient n'est pas déjà présent dans le SIR
    //on l'utilisera pour l'admission uniquement
    public void creationDMR(DMR dmr){

    }

    public ArrayList<DMR> getListeDMR() {
        return listeDMR;
    }

    public Connexion getConn(){
        return conn;
    }

    public PersonnelServiceRadio getPersonneConnecte (){ return personneConnecte; }

}
