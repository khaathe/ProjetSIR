package nf;

import javax.imageio.ImageIO;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Main {

    public static void main(String[] args) {
        //GregorianCalendar calendar = (GregorianCalendar) Calendar.getInstance();
        //java.sql.Date sqlDate = new java.sql.Date(calendar.getTime().getTime()); //récupére un objet date et on recupere le temps en long
        //System.out.println(calendar.get(GregorianCalendar.MONTH)+1);
        try {
            Connexion connexion = new Connexion();
            connexion.connection("rupy","rupy123");
            ArrayList <DMR> s = connexion.getDMR();
            ArrayList <PersonnelServiceRadio> psr;

            PersonnelServiceRadio p=connexion.getPersonnelServiceRadio("2");

            System.out.println(p.getProfession());
            /*Patient claire = new Patient (
                    "983250865",
                    "565694949",
                    "Heissler",
                    "Claire",
                    new GregorianCalendar(1997, 10,7)
                    );

            ArrayList<Examen> lesExamsDeClaire = connexion.getExamen(claire);
            System.out.println(lesExamsDeClaire.size());
            for(DMR dmr : s){
                Patient p = dmr.getPatient();
                ArrayList<Examen> listeExamen = connexion.getExamen(p);
                if (listeExamen.size() > 0)
                    System.out.println(p.getNom() + " a eu un exam le : "+ listeExamen.get(0));
            }
            connexion.Disconnection();
*/
        } catch (Exception e){
            e.printStackTrace();
        }
    }

}

