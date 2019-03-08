package nf;

import java.util.ArrayList;


public class SIR {


    private ArrayList<DMR> listeDMR;
    private Connexion conn;
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

    public void addImageToExam (Examen examen, AbstractImage image) throws Exception {
        examen.addImage(image);
        ArrayList<AbstractImage> list = new ArrayList<>();
        list.add(image);
        conn.insertImage( list );
    }

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

    /*public List<DMR> rechercheParId (String id){

        return null;
    }

    public List<DMR> rechercheParNom (String nom){
        return null;
    }

    public List<DMR> recherche (String s){
        if (s.matches("\\d+")){
            rechercheParId(s);
        }
        else rechercheParNom(s);
        return null;
    }*/


}
