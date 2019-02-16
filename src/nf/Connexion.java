package nf;

import com.mysql.cj.jdbc.Blob;

import javax.imageio.ImageIO;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Connexion {

    private Connection con;
    private static String url = "jdbc:mysql://localhost:3306/sir";
    private static String driver = "com.mysql.cj.jdbc.Driver";
    private String argument = "?serverTimezone=UTC";

    public Connexion() {
        con = null;
    }

    public void connection(String user, String mdp) throws Exception {
        boolean test = true;
        Class.forName(driver);
        con = DriverManager.getConnection(url+argument, user, mdp);
    }

    public void Disconnection() throws Exception {
        con.close();
    }

    public Connection getCon() {
        return con;
    }

    public ArrayList<DMR> getDMR() throws Exception {
        String query = "SELECT * FROM patient";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);
        ArrayList<DMR> array = new ArrayList<>();
        while (rs.next()) {
            String idPR = rs.getString("idPR");
            String idP = rs.getString("idP");
            GregorianCalendar naissance = new GregorianCalendar();
            naissance.setGregorianChange(rs.getDate("date"));
            String nom = rs.getString("nom");
            String prenom = rs.getString("prenom");
            Patient p = new Patient(idPR, idP, nom, prenom, naissance);
            array.add(new DMR(p));
        }
        st.close();
        return array;
    }

    public ArrayList<PersonnelServiceRadio> getListePersonnel() throws Exception {
        String query = "SELECT * FROM personnelhospitalier";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);
        ArrayList<PersonnelServiceRadio> array = new ArrayList<>();
        while (rs.next()) {
            String idPersonnel = rs.getString("idPersonnel");
            String nom = rs.getString("nom");
            String prenom = rs.getString("prenom");
            Profession profession = Profession.valueOf(rs.getString("profession").toUpperCase());
            PersonnelServiceRadio personnel = new PersonnelServiceRadio(idPersonnel, nom, prenom, profession);
            array.add(personnel);
        }
        st.close();
        return array;
    }


    public PersonnelServiceRadio getPersonnelServiceRadio(String id) throws Exception {
        String query = "SELECT * FROM personnelhospitalier where idPersonnel=?";
        PreparedStatement statement = con.prepareStatement(query);
        statement.setString(1, id);
        ResultSet rs = statement.executeQuery();
        PersonnelServiceRadio personnel = null;
        while (rs.next()) {
            String idPersonnel = rs.getString("idPersonnel");
            String nom = rs.getString("nom");
            String prenom = rs.getString("prenom");
            Profession profession = Profession.valueOf(rs.getString("profession").toUpperCase());
            personnel = new PersonnelServiceRadio(idPersonnel, nom, prenom, profession);
        }
        statement.close();
        return personnel;
    }

    /*public ArrayList<Examen> getExamens(Patient ID) throws Exception{
        String query = "SELECT * FROM examen where idpatient="+ID;
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);
        ArrayList<Examen> array = new ArrayList<>();
        int i = 1;
        String s = "";
        while (rs.next()) {
            s = rs.getString("idexam");
            array.add(new Examen(getExamens(Integer.toString(i)).getIdPatient(), getPatient(Integer.toString(i)).getNom(), getPatient(Integer.toString(i)).getPrenom(), getPatient(Integer.toString(i)).getNaissance(), getPatient(Integer.toString(i)).getNumSS()));
            i++;

        }



        st.close();
        return array;

    }*/


    public Examen getExamen(String idExamen) throws Exception {
        String query = "SELECT * FROM examen WHERE idExamen=" + idExamen;

        // create the java statement
        Statement st = con.createStatement();

        // execute the query, and get a java resultset
        ResultSet rs = st.executeQuery(query);
        String id = "";
        String archiv = "";
        TypeExamen typeexam = null;

        GregorianCalendar date = new GregorianCalendar();
        ServiceHosp service = null;
        // iterate through the java resultset

        String idpr = "";
        String nom = "";
        String prenom = "";
        Profession prof = null;

        Examen e = null;

        while (rs.next()) {
            id = rs.getString("idExamen");
            archiv = rs.getString("numArchivage");
            service = ServiceHosp.valueOf(rs.getString("service").toUpperCase());
            idpr = rs.getString("idPR");
            typeexam = TypeExamen.valueOf(rs.getString("typeExamen").toUpperCase());

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
            System.out.println(id + "  " + archiv + "  " + service + "  " + idpr + "   " + typeexam);
        }

        st.close();
        PersonnelServiceRadio p = new PersonnelServiceRadio(nom, prenom, id, prof);

        return e;
    }

    public void addExamen (Examen examen) throws Exception{
        insertExamen(examen);
        insertCompteRendu(examen.getCr());
        insertImage(examen.getImages());
    }

    private void insertExamen (Examen exam) throws Exception {
        String query = "INSERT INTO examen (date, idPR, idPersonnel, numArchivage, typeExamen, service) VALUES (?, ?, ?, ?, ?, ?)";

        // create the mysql insert preparedstatement
        PreparedStatement preparedStmt = con.prepareStatement(query);
        preparedStmt.setDate(1, new java.sql.Date( exam.getDate().getTimeInMillis() ));
        preparedStmt.setString(2, exam.getPatient().getIdPR());
        preparedStmt.setString(3, exam.getPraticien().getIdMedical());
        preparedStmt.setString(4, exam.getNumArchivage());
        preparedStmt.setString(5, exam.getTypeExamen().toString());
        preparedStmt.setString(6, exam.getService().toString());

        // execute the preparedstatement
        preparedStmt.execute();
    }



    public void insertCompteRendu(CompteRendu cr) throws Exception {
        String query = " insert into compterendu (numArchivage, compteRendu)"
                + " values (?, ?)";
        PreparedStatement preparedStmt = con.prepareStatement(query);
        preparedStmt.setString(1, cr.getNumArchivage());
        preparedStmt.setString(2, cr.getCompteRendu());
        preparedStmt.execute();
    }

    public Patient getPatient(String id) throws Exception {

        //Connexion conn = con.getCon();
        // our SQL SELECT query.
        // if you only need a few columns, specify them by name instead of using "*"
        String query = "SELECT * FROM patient WHERE idP=" + id;

        // create the java statement
        Statement st = con.createStatement();

        // execute the query, and get a java resultset
        ResultSet rs = st.executeQuery(query);
        String idPR = "";
        String idPatient = "";
        String nom = "";
        String prenom = "";
        String num = "";
        GregorianCalendar date = new GregorianCalendar();

        // iterate through the java resultset
        while (rs.next()) {
            idPR = rs.getString("idPR");
            idPatient = rs.getString("idP");
            //pour recup une date
            date.setGregorianChange(rs.getDate("date"));
            nom = rs.getString("nom");
            prenom = rs.getString("prenom");
        }
        st.close();
        Patient p = new Patient(idPR, idPatient, nom, prenom, date);
        return p;
    }


    public void addPatient(Patient patient) throws Exception{
        String query = "insert into patient (idPR, idP, date, nom, prenom)"
                + "values (?, ?, ?, ?, ?)";

        //create the mysql insert preparedstatement

        PreparedStatement preparedStatement = con.prepareStatement(query);
        preparedStatement.setString(1,patient.getIdPR());
        preparedStatement.setString(2,patient.getIdPatient());
        preparedStatement.setDate(3, new java.sql.Date( patient.getNaissance().getTimeInMillis() ) );
        preparedStatement.setString(4, patient.getNom());
        preparedStatement.setString(5, patient.getPrenom());

        preparedStatement.execute();


    }

    public void addPersonnelServiceRadio(PersonnelServiceRadio personnel) throws Exception {
        String query = " insert into personnelhospitalier (idPersonnel, nom,prenom, profession)"
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



    public void insertImage (ArrayList<Image> listImage) throws Exception{
        PreparedStatement statement = this.con.prepareStatement("INSERT INTO image (numArchivage, numInstance, image, annotation) VALUES (?, ?, ?,?)");
        for(Image image : listImage){
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            statement.setString(1, image.getNumArchivage());
            statement.setInt(2, image.getNumInstance());
            ImageIO.write(image.getImage(), "jpg", baos);
            byte[] imageInByte = baos.toByteArray();
            com.mysql.cj.jdbc.Blob blob = new Blob(imageInByte, null);
            statement.setBlob(3,  blob);
            statement.setString(4, image.getAnnotation());
            statement.execute();
            baos.close();
        }
        statement.close();
    }

    public ArrayList<Image> getImage (Examen e) throws Exception{
        ArrayList<Image> listImage = new ArrayList<Image>();
        String query = "select * from image where numArchivage=?";
        PreparedStatement statement = this.con.prepareStatement(query);
        statement.setString(1, e.getNumArchivage());
        ResultSet res = statement.executeQuery(query);
        while (res.next()){
            String numArchivage = res.getString("numArchivage");
            int numInstance = res.getInt("numInstance");
            byte[] imageData = res.getBytes("image");
            Image image = new Image(numArchivage);
            image.setImage( ImageIO.read(new ByteArrayInputStream(imageData)) );
            String annotation = res.getString("annotation");
            listImage.add(image);
        }
        res.close();
        return listImage;
    }
}