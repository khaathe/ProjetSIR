package nf;

import java.sql.*;
import java.util.GregorianCalendar;

public class Main {

    public static void main(String[] args) {
        Patient p = new Patient();
        p.setIdPatient("2");
        Examen ex = new Examen(
                "32765026587469",
                new GregorianCalendar(2018, 2, 5),
                "879880983",
                TypeExamen.SCANNER,
                new PersonnelServiceRadio("Spinicci", "Kevin", "7274289", Profession.PH),
                ServiceHosp.NEUROLOGIE
        );
        Connexion c = new Connexion();
        try {
            c.Connection();
            c.addExamen(ex, p);
            c.Disconnection();
        } catch (Exception e){
            e.printStackTrace();
        }

       /* //test getPatient
        Patient p= new Patient();
        p.getPatient("1");
*/
        // Test pour voir si je peux écrire sur la BD
    /*try{
        Statement stmt = null;
        int rs = 0;
        Connexion con = new Connexion();
        con.Connection();
        Connection conn = con.getCon();
        //public void insert(String name, double capacity) {
        // the mysql insert statement
        String query = " insert into patient (idpatient, nom,prenom,date, numss)"
                + " values (?, ?, ?, ?, ?)";

        // create the mysql insert preparedstatement
        PreparedStatement preparedStmt = conn.prepareStatement(query);
        preparedStmt.setString(1, "2");
        preparedStmt.setString(2, "Amandine");
        preparedStmt.setString(3, "Robert");
        preparedStmt.setDate(4, new Date(System.currentTimeMillis()) );
        preparedStmt.setString(5, "17264187463");


        // execute the preparedstatement
        preparedStmt.execute();

        conn.close();}

    catch(Exception e)
        {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
        }*/

    }



}

