package nf;

import library.interfaces.ClientHL7;
import library.interfaces.ServeurHL7;
import library.interfaces.Patient;

public class HL7 {
    private static int portSeveur = 82;
    private static int portClient = 4444;
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
        String messageHL7 = serveurHL7.protocole();
        System.out.println(messageHL7);

    }

    public void deconnection() {
        serveurHL7.fermer();
    }

    public void sendMessage() {

        Patient patient = new Patient(1488488, "Track", 'N');
        clientHL7.connexion(host, portClient);
       /* clientHL7.admit(patient);
        System.out.println(clientHL7.admit(patient));
        patient.setFirstName("Toto");
		clientHL7.endPat(patient);
        clientHL7.close();*/
    }
}


