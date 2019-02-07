package nf;

import java.util.ArrayList;
import java.util.List;

public class SIR {

    List<DMR> listeDMR;
    Connection connection;

    public SIR(){
        listeDMR = new ArrayList<DMR>();
    }

//créer un nouveau DMR, implique d'associer un patient et un examen. On vérifie d'abord qu'un DMR pour ce patient n'est pas déjà présent dans le SIR
    public void creationDMR(DMR dmr){
        if(!listeDMR.contains(dmr){
            listeDMR.add(dmr);
        }
    }

    public void openDMR(DMR dmr){
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


}
