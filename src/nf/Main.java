package nf;

import java.sql.*;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        //test amandine
        System.out.println("jecris du test !");
        Orthanc orthanc = new Orthanc();

        // Test pour voir si je peux écrire sur la BD

        Statement stmt = null;
        int rs = 0;
        Connexion con = new Connexion();
        con.Connection();
        Connection conn= con.getCon();
        //public void insert(String name, double capacity) {
        String sql = "INSERT INTO patient(ID,Nom,Prenom,Adresse) VALUES(1,Andrews,Rupy,3 rue louis vidal)";

            try (
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.executeUpdate("INSERT INTO patient(ID,Nom,Prenom,Adresse) VALUES(1,Andrews,Rupy,3 rue louis vidal)");
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

