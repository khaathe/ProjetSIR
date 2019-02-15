package nf;

import javax.naming.NamingException;
import java.util.ArrayList;
import java.util.List;



public class SIR {

    private List<DMR> listeDMR;
    Connexion connexion = new Connexion();

    public SIR() throws NamingException {
        setListeDMR(new ArrayList<DMR>());
    }


//créer un nouveau DMR, implique d'associer un patient et un examen. On vérifie d'abord qu'un DMR pour ce patient n'est pas déjà présent dans le SIR
    public void creationDMR(DMR dmr){
    }

    public List<DMR> getListeDMR() throws Exception {
        return connexion.getDMR();
    }

    public void setListeDMR(List<DMR> listeDMR) {
        this.listeDMR = listeDMR;
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
