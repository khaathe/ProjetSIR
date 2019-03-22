package nf;

import library.interfaces.ClientHL7;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.GregorianCalendar;

public class HL7 {
    private MyServeurHL7 serveurHL7;
    private ClientHL7 clientHL7;
    private Patient patient;
    private Thread thread;
    private PropertyChangeSupport callback;


    private static int port = 6516;
    public static final int ADMIT_PATIENT = 0;
    public static final int END_PAT = 1;
    public static final int TRANS_PAT = 2;

    public HL7() {
        serveurHL7 = new MyServeurHL7();
        clientHL7 = new ClientHL7();
        patient = null;
        thread = null;
        callback = new PropertyChangeSupport(this);
    }

    public void ecoute () {
        thread = new Thread( () -> {
            serveurHL7.connection(port);
            serveurHL7.ecoute();
            serveurHL7.protocole();
            library.interfaces.Patient p = serveurHL7.getPatient();
            GregorianCalendar dateNaissance = new GregorianCalendar();
            dateNaissance.setTime(p.getBirth());

            patient = new Patient(Patient.generateIdPR(), p.getID().toString(), p.getFamillyName(), p.getFirstName(), dateNaissance, p.getSex());
            serveurHL7.fermer();
            callback.firePropertyChange("New Hl7 message", null, patient);
        } );
        thread.setName("ServeurHL7");
        thread.start();
    }

    public void addPropertyChangeListener(PropertyChangeListener propertyChangeListener){
        callback.addPropertyChangeListener(propertyChangeListener);
    }

    public void deconnection () {
        serveurHL7.fermer();
    }

    public Patient getPatient () { return this.patient; }

    public void sendMessage(Examen examen, int type) {
        clientHL7.connexion(examen.getService().getHost(), examen.getService().getPort());
        Patient onePatient = examen.getPatient();
        library.interfaces.Patient p = new library.interfaces.Patient(Integer.parseInt(onePatient.getIdPatient()), onePatient.getNom(), 'N');
		p.setFirstName(onePatient.getPrenom());
		p.setBirth(onePatient.getNaissance().getTime());
        if (type == ADMIT_PATIENT)
            clientHL7.admit(p);
        else if (type == END_PAT)
            clientHL7.endPat(p);
        else if (type == TRANS_PAT)
            clientHL7.transPat(p);
        clientHL7.close();
    }
}


