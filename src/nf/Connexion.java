package nf;

import com.mysql.cj.jdbc.Blob;

import javax.imageio.ImageIO;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

public class Connexion {

    private Connection con;


    private static String url = "jdbc:mysql://localhost:3306/sir";

   // private static String url="jdbc:mysql://db4free.net:3306/projet_sir";

    // l'url pour la base de donnees en ligne devrait etre "jdbc:mysql://db4free.net:3306/projet_sir"

    private static String driver = "com.mysql.cj.jdbc.Driver";
    private String argument = "?serverTimezone=UTC";

    private static final String NAME = "prenom";
    private static final String ID_PERSONNEL = "idPersonnel";

    public Connexion() {
        con = null;
    }

    public void connection(String user, String mdp) throws SQLException, ClassNotFoundException {
        Class.forName(driver);
        con = DriverManager.getConnection(url+argument, user, mdp);
    }

    public void disconnection() throws SQLException {
        con.close();
    }

    public List<DMR> getDMR() throws SQLException {
        String query = "SELECT * FROM patient";
        List<DMR> array = new ArrayList<>();
        try (Statement st = con.createStatement()){
            try (ResultSet rs = st.executeQuery(query)) {
                while (rs.next()) {
                    String idPR = rs.getString("idPR");
                    String idP = rs.getString("idP");
                    GregorianCalendar naissance = new GregorianCalendar();
                    naissance.setTime(rs.getDate("date"));
                    String nom = rs.getString("nom");
                    String prenom = rs.getString(NAME);
                    String sexe = rs.getString("sexe");
                    Patient p = new Patient(idPR, idP, nom, prenom, naissance, sexe);
                    array.add(new DMR(p));
                }
            }
        }
        return array;
    }

    public PersonnelServiceRadio getPersonnelServiceRadio(String id) throws SQLException {
        String query = "SELECT * FROM personnelhospitalier where idPersonnel=?";
        PersonnelServiceRadio personnel = null;
       try  ( PreparedStatement statement = con.prepareStatement(query) ) {
           statement.setString(1, id);
           try (ResultSet rs = statement.executeQuery()) {
               while (rs.next()) {
                   String idPersonnel = rs.getString(ID_PERSONNEL);
                   String nom = rs.getString("nom");
                   String prenom = rs.getString(NAME);
                   Profession profession = Profession.valueOf(rs.getString("profession").toUpperCase());
                   personnel = new PersonnelServiceRadio(idPersonnel, nom, prenom, profession);
               }
           }
       }
        if (personnel == null)
            throw new NullPointerException("Aucun professionnel avec l'id : " + id + " n'a ete trouve");
        return personnel;
    }

    public List<Examen> getExamen(Patient patient) throws SQLException {
        String query = "SELECT * FROM examen natural join compterendu WHERE idPR=?";
        ArrayList<Examen> listeExamen = new ArrayList<>();
        // create the java statement
         try ( PreparedStatement st = con.prepareStatement(query) ) {
             st.setString(1,patient.getIdPR());
             try (ResultSet rs = st.executeQuery()){
                 // iterate through the java resultset
                 while (rs.next()) {
                     GregorianCalendar date = new GregorianCalendar();
                     date.setTime(rs.getDate("date"));
                     String numArchivage = rs.getString("numArchivage");
                     ServiceHosp service = ServiceHosp.valueOf(rs.getString("service").toUpperCase());
                     TypeExamen typeexam = TypeExamen.valueOf(rs.getString("typeExamen").toUpperCase());
                     PersonnelServiceRadio personnel = getPersonnelServiceRadio(rs.getString(ID_PERSONNEL));
                     CompteRendu cr = new CompteRendu(numArchivage, rs.getString("compteRendu"));
                     Examen examen = new Examen(
                             date,
                             numArchivage,
                             typeexam,
                             patient,
                             personnel,
                             service,
                             cr
                     );
                     listeExamen.add(examen);
                 }
             }
         }
        return listeExamen;
    }

    public void addExamen (Examen examen) throws SQLException, IOException {
        insertExamen(examen);
        insertCompteRendu(examen.getCr());
        insertImage(examen.getImages());
    }

    public void insertExamen (Examen exam) throws SQLException {
        String query = "INSERT INTO examen (date, idPR, idPersonnel, numArchivage, typeExamen, service) VALUES (?, ?, ?, ?, ?, ?)";

        // create the mysql insert preparedstatement
        try( PreparedStatement preparedStmt = con.prepareStatement(query) ) {
            preparedStmt.setDate(1, new java.sql.Date(exam.getDate().getTimeInMillis()));
            preparedStmt.setString(2, exam.getPatient().getIdPR());
            preparedStmt.setString(3, exam.getPraticien().getIdMedical());
            preparedStmt.setString(4, exam.getNumArchivage());
            preparedStmt.setString(5, exam.getTypeExamen().toString());
            preparedStmt.setString(6, exam.getService().toString());

            // execute the preparedstatement
            preparedStmt.execute();
        }
    }



    public void insertCompteRendu(CompteRendu cr) throws SQLException {
        String query = " insert into compterendu (numArchivage, compteRendu)"
                + " values (?, ?)";
        try (PreparedStatement preparedStmt = con.prepareStatement(query) ) {
            preparedStmt.setString(1, cr.getNumArchivage());
            preparedStmt.setString(2, cr.getCompteRendu());
            preparedStmt.execute();
        }
    }

    public void addPatient(Patient patient) throws SQLException{
        String query = "insert into patient (idPR, idP, date, nom, prenom,sexe)"
                + "values (?, ?, ?, ?, ?, ?)";
        //create the mysql insert preparedstatement
        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setString(1, patient.getIdPR());
            preparedStatement.setString(2, patient.getIdPatient());
            preparedStatement.setDate(3, new java.sql.Date(patient.getNaissance().getTimeInMillis()));
            preparedStatement.setString(4, patient.getNom());
            preparedStatement.setString(5, patient.getPrenom());
            preparedStatement.setString(6, patient.getSexe());
            preparedStatement.execute();
        }

    }

    public void addPersonnelServiceRadio(PersonnelServiceRadio personnel) throws SQLException {
        String query = " insert into personnelhospitalier (idPersonnel, nom,prenom, profession)"
                + " values (?, ?, ?, ?)";

        // create the mysql insert preparedstatement
        try (PreparedStatement preparedStmt = con.prepareStatement(query)) {
            preparedStmt.setString(1, personnel.getIdMedical());
            preparedStmt.setString(2, personnel.getNom());
            preparedStmt.setString(3, personnel.getPrenom());
            preparedStmt.setString(4, personnel.getProfessionString());

            // execute the preparedstatement
            preparedStmt.execute();
        }
    }



    public void insertImage (List<AbstractImage> listImage) throws SQLException, IOException {
        try(PreparedStatement statement = this.con.prepareStatement("INSERT INTO image (numArchivage, numInstance, image, annotation) VALUES (?, ?, ?,?)")) {
            for (AbstractImage image : listImage) {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                statement.setString(1, image.getNumArchivage());
                statement.setInt(2, image.getNumInstance());
                ImageIO.write(image.getImage(), "jpg", baos);
                byte[] imageInByte = baos.toByteArray();
                com.mysql.cj.jdbc.Blob blob = new Blob(imageInByte, null);
                statement.setBlob(3, blob);
                statement.setString(4, image.getAnnotation());
                statement.execute();
                baos.close();
            }
        }
    }

    public List<AbstractImage> getImage (String numArchivage) throws SQLException, IOException {
        List<AbstractImage> listImage = new ArrayList<>();
        String query = "select * from image where numArchivage=?";
        try ( PreparedStatement statement = this.con.prepareStatement(query) ) {
            statement.setString(1, numArchivage);
            try (ResultSet res = statement.executeQuery()) {
                while (res.next()) {
                    int numInstance = res.getInt("numInstance");
                    byte[] imageData = res.getBytes("image");
                    Image image = new Image(numArchivage);
                    image.setNumInstance(numInstance);
                    image.setImage(ImageIO.read(new ByteArrayInputStream(imageData)));
                    String annotation = res.getString("annotation");
                    image.setAnnotation(annotation);
                    listImage.add(image);
                }
            }
        }
        return listImage;
    }

    public void addAnnotation (List<AbstractImage> lesImages) throws SQLException {
        String query = "update image " +
                "set annotation=?" +
                "where numArchivage=? and numInstance=?";
        // create the mysql insert preparedstatement
        try (PreparedStatement preparedStmt = con.prepareStatement(query)) {
            for (AbstractImage img : lesImages) {
                preparedStmt.setString(1, img.getAnnotation());
                preparedStmt.setString(2, img.getNumArchivage());
                preparedStmt.setInt(3, img.getNumInstance());
                preparedStmt.executeUpdate();
            }
        }
    }
}