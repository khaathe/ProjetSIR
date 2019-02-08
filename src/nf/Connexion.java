package nf;

import java.sql.*;
import java.util.ArrayList;

public class Connexion {

    private Connection con;
    private static String url = "jdbc:mysql://localhost:3306/sir";
    private static String driver = "com.mysql.cj.jdbc.Driver";
    /*private static String protocol = "jdbc:mysql";
    private String host = "localhost";
    private static String port = "3306";
    private String DB = "test";
    private String argument = "?serverTimezone=UTC";
    private String url="jdbc:mysql://localhost:3306/test"; */

    public Connexion() {
        con = null;
    }

    public boolean Connection(String user, String mdp) throws Exception {
        boolean test = true;
        url += "?serverTimezone=UTC";
        Class.forName(driver);
        con = DriverManager.getConnection(url, user, mdp); /*jdbc:mysql://localhost:3306/test*/
        return test;
    }

    public boolean Disconnection() throws Exception {
        con.close();
        return true;
    }

    public Connection getCon(){

        return con;
    }

    public ArrayList<DMR> getDMR() {
        return null;
    }

    public ArrayList<Examen> getExamens(Patient ID) {
        return null;
    }

    public boolean addExamen(Examen exam, Patient patient) throws Exception {
        String query = " insert into examen (idexam, numarchivage,date, typeexam, idpatient, service)"
                    + " values (?, ?, ?, ?, ?, ?)";

            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setString(1, exam.getIdExam());
            preparedStmt.setString(2, exam.getNumArchivage());
            preparedStmt.setDate(3, new Date(System.currentTimeMillis()) );
        preparedStmt.setString(4, exam.getTypeExamen().toString());
        preparedStmt.setString(5, patient.getIdPatient());
        preparedStmt.setString(6, exam.getService().toString());

            // execute the preparedstatement
            preparedStmt.execute();
            return true;
    }
}


