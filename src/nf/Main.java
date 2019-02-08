package nf;

import java.sql.*;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        //test amandine
        System.out.println("jecris du test !");
        Orthanc orthanc = new Orthanc();

        // Test pour voir si je peux écrire sur la BD
    try{
        Statement stmt = null;
        int rs = 0;
        Connexion con = new Connexion();
        con.Connection();
        Connection conn = con.getCon();
        //public void insert(String name, double capacity) {
        // the mysql insert statement
        String query = " insert into patient (ID, Nom,Prenom,Adresse)"
                + " values (?, ?, ?, ?)";

        // create the mysql insert preparedstatement
        PreparedStatement preparedStmt = conn.prepareStatement(query);
        preparedStmt.setString(1, "1");
        preparedStmt.setString(2, "Andrews");
        preparedStmt.setString(3, "Rupy");
        preparedStmt.setString(4, "Adresse");


        // execute the preparedstatement
        preparedStmt.execute();

        conn.close();}

    catch(Exception e)
        {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
        }
    }}

