package nf;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.*;
import java.util.GregorianCalendar;

public class Main {

    public static void main(String[] args) {
        //System.out.println( new SimpleDateFormat("dd/MM/yyyy:HH-mm").format(System.currentTimeMillis()) );
        Date date = new Date( System.currentTimeMillis() );
        GregorianCalendar gregorianCalendar = new GregorianCalendar(2018, 1, 2);
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

    }

}

