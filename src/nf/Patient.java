package nf;

import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;
import java.util.GregorianCalendar;

public class Patient {
    private String idPatient;
    private String nom;
    private String prenom;
    private GregorianCalendar naissance;
    private String numSS;

    public Patient(){
    };
    public Patient(String idPatient, String nom, String prenom, GregorianCalendar naissance, String numSS){
        this.idPatient=idPatient;
        this.nom=nom;
        this.prenom=prenom;
        this.naissance=naissance;
        this.numSS=numSS;
    }

    public void getPatient(String idPatient) {
        //Patient p= new Patient();
        Connexion con = new Connexion();
        con.Connection();
        Connection conn = con.getCon();
        try{
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
        }
    }

