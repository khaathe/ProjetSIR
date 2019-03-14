package nf;

import library.interfaces.ClientHL7;
import library.interfaces.MessageInterface;
import library.structure.groupe.messages.Message;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.GregorianCalendar;

public class HL7 {
    private static String host = "localhost";
    private MyServeurHL7 serveurHL7;
    private ClientHL7 clientHL7;
    private Patient patient;
    private Thread thread;
    private PropertyChangeSupport callback;


    private static int portServeur = 6516;
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
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                serveurHL7.connection(portServeur);
                serveurHL7.ecoute();
                serveurHL7.protocole();
                library.interfaces.Patient p = serveurHL7.getPatient();
                GregorianCalendar dateNaissance = new GregorianCalendar();
                dateNaissance.setTime(p.getBirth());

                patient = new Patient(Patient.generateIdPR(), p.getID().toString(), p.getFamillyName(), p.getFirstName(), dateNaissance, p.getSex());
                Message message = serveurHL7.getMessage();
                serveurHL7.fermer();
                callback.firePropertyChange("New Hl7 message", null, patient);
            }
        });
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
        clientHL7.connexion("localhost", 6516);
        Patient patient = examen.getPatient();
        library.interfaces.Patient p = new library.interfaces.Patient(Integer.parseInt(patient.getIdPatient()), patient.getNom(), 'N');
		p.setFirstName(patient.getPrenom());
		p.setBirth(patient.getNaissance().getTime());
        if (type == ADMIT_PATIENT)
            clientHL7.admit(p);
        else if (type == END_PAT)
            clientHL7.endPat(p);
        else
            clientHL7.transPat(p);
		MessageInterface accuse = clientHL7.getMsg();
        clientHL7.close();
    }

    public void setPortSeveur (int portSeveur){
        HL7.portServeur = portSeveur;
    }
}


