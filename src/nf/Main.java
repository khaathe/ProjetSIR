package nf;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.sql.*;
import java.util.GregorianCalendar;

public class Main {

    public static void main(String[] args) {
        GregorianCalendar calendar = (GregorianCalendar) Calendar.getInstance();
        java.sql.Date sqlDate = new java.sql.Date(calendar.getTime().getTime()); //récupére un objet date et on recupere le temps en long
        try {
            Connexion connexion = new Connexion();
            connexion.Connection("toto", "1234abcd");
            connexion.Disconnection();
        } catch (Exception e){
            e.printStackTrace();
        }

        /*Patient p = new Patient();
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
        }*/
    }

}

