package nf;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

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

    public boolean connection(String user, String mdp) throws Exception {
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

    public Connection getCon() {

        return con;
    }

    public ArrayList<DMR> getDMR() {
        return null;
    }

    public ArrayList<Examen> getExamens(Patient ID) {
        return null;
    }

<<<<<<< HEAD
    public Examen getExamen(String idExamen) throws Exception{
        String query = "SELECT * FROM examen WHERE idexam="+idExamen;

        // create the java statement
        Statement st = con.createStatement();

        // execute the query, and get a java resultset
        ResultSet rs = st.executeQuery(query);
        String id = "";
        String archiv = "";
        TypeExamen typeexam = null;

        GregorianCalendar date = new GregorianCalendar();
        ServiceHosp service=null;
        // iterate through the java resultset

        String idp = "";
        String nom = "";
        String prenom = "";
        Profession prof = null;

        while (rs.next()) {
            id = rs.getString("idExam");
            archiv = rs.getString("numarchivage");
            service = ServiceHosp.valueOf(rs.getString("service").toUpperCase());
            idp = rs.getString("idpatient");
            typeexam = TypeExamen.valueOf(rs.getString("typeexam").toUpperCase());

            //pour recup une date
            GregorianCalendar cal = new GregorianCalendar();
            java.sql.Date sqlDate = rs.getDate("date");
            GregorianCalendar calendar = (GregorianCalendar) Calendar.getInstance();
            cal.setTimeInMillis(sqlDate.getTime());
            date = cal;


            //idp = rs.getString("idp");
            //nom = rs.getString("nom");
            //prenom = rs.getString("prenom");
            //prof = Profession.valueOf(rs.getString("profession").toUpperCase());
            // print the results
            System.out.println(id+"  "+archiv+"  "+service+"  "+idp+"   "+typeexam);
        }

        st.close();
        PersonnelServiceRadio p = new PersonnelServiceRadio(nom, prenom, id, prof);
        Examen e= new Examen(id,date,archiv,typeexam,p,service);

        return e;
    }
    public void addExamen(Examen exam, Patient patient) throws Exception {
=======
    public boolean addExamen(Examen exam, Patient patient) throws Exception {
>>>>>>> 0edd378c240caf547cf617435793f327dba2b5fd
        String query = " insert into examen (idexam, numarchivage,date, typeexam, idpatient, service)"
                + " values (?, ?, ?, ?, ?, ?)";

        // create the mysql insert preparedstatement
        PreparedStatement preparedStmt = con.prepareStatement(query);
        preparedStmt.setString(1, exam.getIdExam());
        preparedStmt.setString(2, exam.getNumArchivage());
        preparedStmt.setDate(3, new Date(System.currentTimeMillis()));
        preparedStmt.setString(4, exam.getTypeExamen().toString());
        preparedStmt.setString(5, patient.getIdPatient());
        preparedStmt.setString(6, exam.getService().toString());


            // execute the preparedstatement
            preparedStmt.execute();
            return true;




        }






    public Patient getPatient(String idPatient) throws Exception {

        //Connexion conn = con.getCon();
        // our SQL SELECT query.
        // if you only need a few columns, specify them by name instead of using "*"
        String query = "SELECT * FROM patient WHERE idPatient=" + idPatient;

        // create the java statement
        Statement st = con.createStatement();

        // execute the query, and get a java resultset
        ResultSet rs = st.executeQuery(query);
        String id = "";
        String nom = "";
        String prenom = "";
        String num = "";
        GregorianCalendar date = new GregorianCalendar();

        // iterate through the java resultset
        while (rs.next()) {
            id = rs.getString("idPatient");
            nom = rs.getString("nom");
            prenom = rs.getString("prenom");

            //pour recup une date
            GregorianCalendar cal = new GregorianCalendar();
            java.sql.Date sqlDate = rs.getDate("date");
            GregorianCalendar calendar = (GregorianCalendar) Calendar.getInstance();
            cal.setTimeInMillis(sqlDate.getTime());
            date = cal;

            num = rs.getString("numss");
            // print the results
            System.out.println(id + nom + prenom + date.get(GregorianCalendar.DAY_OF_MONTH) + "/" + (date.get(GregorianCalendar.MONTH) + 1) + "/" + date.get(GregorianCalendar.YEAR) + "  " + num);
        }

        st.close();
        Patient p = new Patient(id, nom, prenom, date, num);
        return p;
    }


    public PersonnelServiceRadio getPersonnelServiceRadio(String idMed) throws Exception {


        String query = "SELECT * FROM personnelhospitalier WHERE idp=" + idMed;
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);

        String id = "";
        String nom = "";
        String prenom = "";
        Profession prof = null;

        while (rs.next()) {
            id = rs.getString("idp");
            nom = rs.getString("nom");
            prenom = rs.getString("prenom");
            prof = Profession.valueOf(rs.getString("profession").toUpperCase());
        }
        st.close();
        PersonnelServiceRadio p = new PersonnelServiceRadio(nom, prenom, id, prof);
        return p;

    }

<<<<<<< HEAD
    public void addPersonnelServiceRadio(PersonnelServiceRadio personnel) throws Exception {
=======

    public void addPersonnelServiceRadio(PersonnelServiceRadio personnel) throws Exception{
>>>>>>> 0edd378c240caf547cf617435793f327dba2b5fd


        String query = " insert into personnelhospitalier (idp, nom,prenom, profession)"
                + " values (?, ?, ?, ?)";

        // create the mysql insert preparedstatement
        PreparedStatement preparedStmt = con.prepareStatement(query);
        preparedStmt.setString(1, personnel.getIdMedical());
        preparedStmt.setString(2, personnel.getNom());
        preparedStmt.setString(3, personnel.getPrenom());
        preparedStmt.setString(4, personnel.getProfession().toString());

        // execute the preparedstatement
        preparedStmt.execute();


    }

    public void addCompteRendu(Examen e,CompteRendu cr,PersonnelServiceRadio p) throws Exception{
        String query = " insert into compte_rendu (idp, idexam,cr)"
                + " values (?, ?, ?)";
        PreparedStatement preparedStmt = con.prepareStatement(query);
        preparedStmt.setString(1, p.getIdMedical());
        preparedStmt.setString(2, e.getIdExam());
        preparedStmt.setString(3, cr.getContenu());


        preparedStmt.execute();

    }
}