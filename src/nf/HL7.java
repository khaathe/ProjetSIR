package nf;

import library.interfaces.ClientHL7;
import library.interfaces.Patient;
import library.interfaces.ServeurHL7;

public class HL7 {
    private static int portSeveur = 82;
    private static int portClient = 83;
    private static String host = "localhost";
    private ServeurHL7 serveurHL7;
    private ClientHL7 clientHL7;

    public HL7() {
        serveurHL7 = new ServeurHL7();
        clientHL7 = new ClientHL7();
    }

    public void connection() {
        serveurHL7.connection(portSeveur);
        serveurHL7.ecoute();
    }

    public void deconnection() {
        serveurHL7.fermer();
    }

    public void sendMessage() {
        clientHL7.connexion(host, portClient);
        Patient patient = new Patient(1488488, "Track", 'N');
		patient.setFirstName("Toto");
		clientHL7.endPat(patient);
        clientHL7.close();
    }
}


