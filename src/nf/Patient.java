package nf;

import javax.swing.*;
import java.util.GregorianCalendar;

public class Patient extends JList {
    private String idPatient;
    private String nom;
    private String prenom;
    private GregorianCalendar naissance;
    private String numSS;

    public Patient(){
    };
    public Patient(String idPatient, String nom, String prenom, GregorianCalendar naissance, String numSS){
        this.setIdPatient(idPatient);
        this.setNom(nom);
        this.setPrenom(prenom);
        this.setNaissance(naissance);
        this.setNumSS(numSS);
    }



   /* public void getPatient(String idPatient) {
        //Patient p= new Patient();
        Connexion con = new Connexion();
        try{
            con.connection("","");
            Connection conn = con.getCon();
            // our SQL SELECT query.
            // if you only need a few columns, specify them by name instead of using "*"
            String query = "SELECT * FROM patient WHERE id="+idPatient;

            // create the java statement
            Statement st = conn.createStatement();

            // execute the query, and get a java resultset
            ResultSet rs = st.executeQuery(query);

            // iterate through the java resultset
            while (rs.next())
            {
                int id = rs.getInt("id");
                String nom =rs.getString("nom");
                String prenom =rs.getString("prenom");
                //GregorianCalendar Date =rs.getString("naissance");

                // print the results
                System.out.println("id :"+id+" prenom: "+prenom+" nom :"+nom);
            }
            st.close();
        }
        catch (Exception e)
        {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        //return p;
        } */

    public String getIdPatient() {
        return idPatient;
    }

    public void setIdPatient(String idPatient) {
        this.idPatient = idPatient;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public GregorianCalendar getNaissance() {
        return naissance;
    }

    public void setNaissance(GregorianCalendar naissance) {
        this.naissance = naissance;
    }

    public String getNumSS() {
        return numSS;
    }

    public void setNumSS(String numSS) {
        this.numSS = numSS;
    }
}

