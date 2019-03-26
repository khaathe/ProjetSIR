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

    /**
     * Permet de se connecter a la base de donnee grace a identifiant et un mot de passe
     *
     * @param user
     *          Correspond au login de l'utilisateur
     * @param mdp
     *          Correspond au mot de passe de l'utilisateur
     * @throws SQLException
     *          Generee par une erreur sql
     * @throws ClassNotFoundException
     *          Generee par la methode forName si la classe demandee n'est pas trouvee
     */
    public void connection(String user, String mdp) throws SQLException, ClassNotFoundException {

        Class.forName(driver);
        con = DriverManager.getConnection(url+argument, user, mdp);
    }

    /**
     * Permet de se deconnecter de la base de donnee
     *
     * @throws SQLException
     *          Generee par une erreur sql
     */
    public void disconnection() throws SQLException {

        con.close();
    }

    /**
     *  Recupere dans la base de donnee la liste des patients de la base de donnee et en fait une liste de DMR
     * @return
     *          Renvoie la liste de tout les DMR
     * @throws SQLException
     *          Generee par une erreur sql
     */
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

    /**
     * Recupere un personnel du service radio
     * @param id
     *          Correspond a l'identifiant du medecin
     * @return
     *          Renvoie le personnel correspondant au parametre id
     * @throws SQLException
     *          Generee par une erreur sql
     */
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

    /**
     * Recupere dans la base de donnee la liste d'examens correspondant au parametre patient
     * @param patient
     *          Correspond au patient pour qui on souhaite recuperer les examens
     * @return
     *          Renvoie la liste d'examens correspondant a un patient donne
     * @throws SQLException
     *  Generee par une erreur sql
     */
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

    /**
     * Ajoute un examen ainsi que le compte-rendu et les images correspondantes, dans la base de donnee
     * @param examen
     *         Correspond a l'examen que l'on souhaite ajouter
     * @throws SQLException
     *           Generee par une erreur sql
     * @throws IOException
     *  Generee par une erreur dans le flux
     */
    public void addExamen (Examen examen) throws SQLException, IOException {
        insertExamen(examen);
        insertCompteRendu(examen.getCr());
        insertImage(examen.getImages());
    }

    /**
     * Ajoute un Examen a la base de donnee
     * @param exam
     * Correspond a l'examen que l'on souhaite ajouter
     * @throws SQLException
     * Generee par une erreur sql
     */
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

    /**
     * Ajoute un compte-rendu a la base de donnee
     * @param cr
     *  Correspond au compte-rendu que l'on souhaite ajouter
     * @throws SQLException
     * Generee par une erreur sql
     */

    public void insertCompteRendu(CompteRendu cr) throws SQLException {
        String query = " insert into compterendu (numArchivage, compteRendu)"
                + " values (?, ?)";
        try (PreparedStatement preparedStmt = con.prepareStatement(query) ) {
            preparedStmt.setString(1, cr.getNumArchivage());
            preparedStmt.setString(2, cr.getCompteRendu());
            preparedStmt.execute();
        }
    }

    /**
     * Ajoute un patient a la base de donnee
     * @param patient
     *      Correspond au patient que l'on souhaite ajouter
     * @throws SQLException
     * Generee par une erreur sql
     */
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


    /**
     * Ajoute une image dans la base de donnee
     * @param listImage
     *          Correspond aux images que l'on souhaite ajouter
     * @throws SQLException
     *          Generee par une erreur sql
     * @throws IOException
     *          Generee par une erreur dans le flux
     */
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

    /**
     * Recupere dans la base de donnee la liste des images correspondant au numero d'archivage recherche
     * @param numArchivage
     * Correspond au numero d'archivage que l'on souhaite recuperer
     * @return
     * Renvoie une liste d'images
     * @throws SQLException
     * Generee par une erreur sql
     * @throws IOException
     *  Generee par une erreur dans le flux
     */
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

    /**
     * Ajoute une annotation a la base de donnee
     * @param lesImages
     * Correspond aux images auxquelles on souhaite ajouter une annotation
     * @throws SQLException
     * Generee par une erreur sql
     */
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