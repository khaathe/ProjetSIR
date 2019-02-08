package nf;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public class Connexion {

    private Connection con;
    private static String url = "jdbc:mysql://localhost:3306/test";
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

    public boolean Connection() {
        Boolean test = new Boolean(true);
        url += "?serverTimezone=UTC";

        try {
            Class.forName(driver);
            try {
                con = DriverManager.getConnection(url, "", ""); /*jdbc:mysql://localhost:3306/test*/
            } catch (Exception e) {
                test = false;
            }
        } catch (ClassNotFoundException ex) {
            System.out.println("Driver not found.");
            test = false;
        }
        return test;
    }

    public boolean Disconnection() throws Exception {
        con.close();
        return true;
    }

    public ArrayList<DMR> getDMR() {
        return null;
    }

    public ArrayList<Examen> getExamens(Patient ID) {
        return null;
    }
}
