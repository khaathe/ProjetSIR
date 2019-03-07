package nf;

import library.interfaces.ClientHL7;
import library.interfaces.MessageInterface;
import library.interfaces.ServeurHL7;
import library.interfaces.Patient;

import java.util.Date;

public class HL7 {
    private static int portSeveur = 6516;
    private static int portClient = 6516;
    private static String host = "localhost";
    private ServeurHL7 serveurHL7;
    private ClientHL7 clientHL7;
    private Thread thread;

    public HL7() {
        serveurHL7 = new ServeurHL7();
        clientHL7 = new ClientHL7();
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("j'ecoute");
                serveurHL7.ecoute();
                String messageHL7 = serveurHL7.protocole();
                System.out.println(messageHL7);
                serveurHL7.fermer();
            }
        });
    }

    public void connection() {
        serveurHL7.connection(portSeveur);
        thread.start();
    }

    public void deconnection() {

    }

    public void sendMessage() {
        clientHL7.connexion(host, portClient);
        Patient patient = new Patient(1488488, "Track", 'N');
		patient.setFirstName("Toto");
		patient.setBirth(new Date(System.currentTimeMillis()));
		patient.setSex('M');
		clientHL7.endPat(patient);
		MessageInterface accuse = clientHL7.getMsg();
        System.out.println(accuse.getType());
        clientHL7.close();
    }
}


